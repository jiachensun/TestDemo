package com.example.teachingdemo.recyclerview;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.AsyncListUtil;
import androidx.recyclerview.widget.DiffUtil;

import com.example.teachingdemo.UserBean;

/**
 * @Author sjc
 * @Date 2020/6/6.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
class MyAsyncListDiffer extends DiffUtil.ItemCallback<UserBean> {


    @Override
    public boolean areItemsTheSame(@NonNull UserBean oldUserBean, @NonNull UserBean newUserBean) {
        return TextUtils.equals(oldUserBean.id, newUserBean.id);
    }

    @Override
    public boolean areContentsTheSame(@NonNull UserBean oldUserBean, @NonNull UserBean newUserBean) {
        if (!TextUtils.equals(oldUserBean.name, newUserBean.name)) {
            return false;
        }

        if (!TextUtils.equals(oldUserBean.mail, newUserBean.mail)) {
            return false;
        }

        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull UserBean oldUserBean, @NonNull UserBean newUserBean) {
        Bundle bundle = new Bundle();

        if (!TextUtils.equals(oldUserBean.name, newUserBean.name)) {
            bundle.putString("name", newUserBean.name);
        }

        if (!TextUtils.equals(oldUserBean.mail, newUserBean.mail)) {
            bundle.putString("mail", newUserBean.mail);
        }

        return bundle;
    }
}
