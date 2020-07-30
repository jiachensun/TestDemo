package com.sjc.customview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * 描述:
 * DynamTheme-
 *
 * @Author thinkpad
 * @create 2018-08-25 13:05
 */
public class SkinItem {

    private View view;
    private List<SkinAttr> attrs;

    public SkinItem(View view, List<SkinAttr> attrs) {
        this.view = view;
        this.attrs = attrs;
    }

    public void apply(Drawable drawable) {
        if (view == null || attrs == null)
            return;
        for (SkinAttr attr : attrs) {
            String attrName = attr.getAttrName();
            String attrType = attr.getAttrType();
            String resName = attr.getResName();
            int resId = attr.getResId();
            if ("background".equals(attrName)) {
                if ("color".equals(attrType)) {
                    view.setBackgroundColor(Color.GREEN);
                } else if ("mipmap".equals(attrType)) {
                    view.setBackground(drawable);
                }
            } else if ("textColor".equals(attrName)) {
                if (view instanceof TextView && "color".equals(attrType)) {
                    ((TextView) view).setTextColor(Color.RED);
                }
            }
        }
    }

}
