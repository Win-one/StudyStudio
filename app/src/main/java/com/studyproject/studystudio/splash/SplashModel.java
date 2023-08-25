package com.studyproject.studystudio.splash;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.studyproject.studystudio.BR;


/**
 * Created by Hy on 2019/12/02 16:13
 *
 * @see SplashViewModel
 **/
public class SplashModel extends BaseObservable {

    @Bindable
    private String url;

    @SuppressWarnings("WeakerAccess")
    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    public String getUrl() {
        return url;
    }

}
