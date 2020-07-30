package com.sjc.mymvvm;

import com.sjc.arouter_lib.Arouter;
import com.sjc.arouter_lib.ArouterConstant;
import com.sjc.arouter_lib.IArouter;

/**
 * @Author sjc
 * @Date 2020/7/13
 * Description：
 */
public class IArouterMyMvvmImp implements IArouter {
    @Override
    public void initActivity() {
        Arouter.getInstance().setActivity(ArouterConstant.MVVM_PATH, MvvmMainActivity.class);
    }
}
