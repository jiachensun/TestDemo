package com.sjc.mymvvm.viewmodel;

import dagger.Component;

/**
 * @Author sjc
 * @Date 2020/7/8
 * Description：
 */
//添加@Component
@Component(modules = {MyViewModel.class})
public interface MainComponent {
    //写一个方法 绑定Activity /Fragment
    void inject(ViewModelMainActivity activity);
}
