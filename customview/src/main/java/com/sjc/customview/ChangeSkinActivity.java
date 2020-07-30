package com.sjc.customview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjc.arouter_lib.Arouter;
import com.sjc.arouter_lib.ArouterConstant;

import java.util.ArrayList;
import java.util.List;

public class ChangeSkinActivity extends AppCompatActivity {

    private ArrayList<SkinItem> listViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                // 控件类型
                Log.i("ChangeSkin", "name = " + name);
                // 打印控件的所有属性
                int n = attrs.getAttributeCount();
                for (int i = 0; i < n; i++) {
                    Log.e("ChangeSkin", attrs.getAttributeName(i) + " , " + attrs.getAttributeValue(i));
                }
                // 初始化所有view
                View view = null;
                try {
                    if (-1 == name.indexOf('.')) {	//不带".",说明是系统的View
                        if ("View".equals(name)) {
                            view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                        }
                        if (view == null) {
                            view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                        }
                        if (view == null) {
                            view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                        }
                    } else {	//带".",说明是自定义的View
                        view = LayoutInflater.from(context).createView(name, null, attrs);
                    }
                } catch (Exception e) {
                    view = null;
                }
                // 可以动态修改控件类型，鸡肋
//            if (TextUtils.equals("TextView", name)) {
//                return new Button(context, attrs);
//            }
                if (null != view) {
                    collectViewAttr(view, context, attrs);
                }
                return view;
            }

            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return null;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
    }

    private void collectViewAttr(View view,Context context, AttributeSet attrs) {
        List<SkinAttr> skinAttrs = new ArrayList<>();
        int attCount = attrs.getAttributeCount();
        for (int i = 0;i<attCount;++i){
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            if (isSupportedAttr(attributeName)){
                if (attributeValue.startsWith("@")){
                    int resId = Integer.parseInt(attributeValue.substring(1));
                    String resName = context.getResources().getResourceEntryName(resId);
                    String attrType = context.getResources().getResourceTypeName(resId);
                    skinAttrs.add(new SkinAttr(attributeName,attrType,resName,resId));
                    SkinItem skinItem = new SkinItem(view, skinAttrs);
                    listViews.add(skinItem);
                }
            }
        }
    }

    private boolean isSupportedAttr(String attributeName){
        return "background".equals(attributeName) || "textColor".equals(attributeName);
    }

    public void onClickChangeSkin(View view) {

        for (SkinItem skinItem : listViews) {
            skinItem.apply(getDrawable(R.drawable.ic_round));
        }
    }

    public void onClickStartMyMvvm(View view) {

        // Arouter MVVM
        startActivity(new Intent(this, Arouter.getInstance().getActivity(ArouterConstant.MVVM_PATH)));
    }
}