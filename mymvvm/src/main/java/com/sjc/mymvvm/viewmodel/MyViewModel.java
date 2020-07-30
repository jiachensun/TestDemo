package com.sjc.mymvvm.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sjc.mymvvm.databinding.ActivityViewModelMainBinding;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import dagger.Module;

/**
 * @Author sjc
 * @Date 2020/7/8
 * Description：
 */
@Module
public class MyViewModel extends ViewModel {

    private WeakReference<ViewModelMainActivity> weakReference;
    // MVVM 结合 Livedata使用
    private MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    // Dagger2架构使用
    @Inject //此处在构造函数添加注解
    public MyViewModel() {

    }

    // MVP架构
    public void attachView(ViewModelMainActivity activity) {
        weakReference = new WeakReference<>(activity);
    }

    public void getData(String s) {
        // 这是通过mvp架构实现的，调用的是view接口
        if (null != weakReference) {
            weakReference.get().updateUi(s);
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        name.setValue(s.toString());
        password.setValue(s.toString());
    }
}
