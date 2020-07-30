package com.example.teachingdemo.recyclerview;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.teachingdemo.UserBean;

import java.util.List;

/**
 * @Author sjc
 * @Date 2020/6/6.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class MyDiffUtilCallback extends DiffUtil.Callback {

    private final List<UserBean> oldList;
    private final List<UserBean> newList;

    public MyDiffUtilCallback(List<UserBean> oldList, List<UserBean> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    // 标识是否一样
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

        UserBean oldUserBean = oldList.get(oldItemPosition);
        UserBean newUserBean = newList.get(newItemPosition);

        return TextUtils.equals(oldUserBean.id, newUserBean.id);
    }

    // 内容是否一样
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

        UserBean oldUserBean = oldList.get(oldItemPosition);
        UserBean newUserBean = newList.get(newItemPosition);

        if (!TextUtils.equals(oldUserBean.name, newUserBean.name)) {
            return false;
        }

        if (!TextUtils.equals(oldUserBean.mail, newUserBean.mail)) {
            return false;
        }

        return true;
    }

    // 刷新指定数据
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        Bundle bundle = new Bundle();

        UserBean oldUserBean = oldList.get(oldItemPosition);
        UserBean newUserBean = newList.get(newItemPosition);

        if (!TextUtils.equals(oldUserBean.name, newUserBean.name)) {
            bundle.putString("name", newUserBean.name);
        }

        if (!TextUtils.equals(oldUserBean.mail, newUserBean.mail)) {
            bundle.putString("mail", newUserBean.mail);
        }

        return bundle;
    }
}
