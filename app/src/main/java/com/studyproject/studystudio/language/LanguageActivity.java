package com.studyproject.studystudio.language;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.studyproject.studystudio.Application;
import com.studyproject.studystudio.MainActivity;
import com.studyproject.studystudio.R;
import com.studyproject.studystudio.language.utils.MultiLanguageUtil;
import com.studyproject.studystudio.language.utils.SpUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ListView listView = findViewById(R.id.lv_1);
        List<String> stringList = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i){
                case 0:
                    changeLanguage("zh", "CN");
                    break;
                case 1:
                    changeLanguage("en", "US");
                    break;
            }
            }
        });

        stringList.add("chinese");
        stringList.add("english");

        Toolbar toolbar=findViewById(R.id.toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.arrow_back);
        }
        setSupportActionBar(toolbar);
        setTitle("Language Setting");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //修改应用内语言设置
    private void changeLanguage(String language, String area) {
        if (TextUtils.isEmpty(language) && TextUtils.isEmpty(area)) {
            //如果语言和地区都是空，那么跟随系统
            SpUtil.saveString(ConstantGlobal.LOCALE_LANGUAGE, "");
            SpUtil.saveString(ConstantGlobal.LOCALE_COUNTRY, "");
        } else {
            //不为空，那么修改app语言，并true是把语言信息保存到sp中，false是不保存到sp中
            Locale newLocale = new Locale(language, area);
            MultiLanguageUtil.changeAppLanguage(LanguageActivity.this, newLocale, true);
            MultiLanguageUtil.changeAppLanguage(Application.getContext(), newLocale, true);
        }
        //重启app,这一步一定要加上，如果不重启app，可能打开新的页面显示的语言会不正确
        Intent intent = new Intent(Application.getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
       Application.getContext().startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}