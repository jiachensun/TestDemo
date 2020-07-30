package com.example.teachingdemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingdemo.annotation.AptActivityLayout;
import com.example.teachingdemo.mvp.view.BaseMvpActivity;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;

import butterknife.ButterKnife;

/**
 * @Author sjc
 * @Date 2020/5/8.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public abstract class BaseActivity extends BaseMvpActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AptActivityLayout aptActivityLayout = this.getClass().getAnnotation(AptActivityLayout.class);
        if (null != aptActivityLayout) {
            int activityLayoutId = aptActivityLayout.activityLayoutId();
            if (activityLayoutId > 0) {
                // 设置内容xml
                setContentView(activityLayoutId);
                bindView();
                initData(savedInstanceState);
            } else {
                throw new RuntimeException("activityLayoutId <= 0");
            }
        } else {
            throw new RuntimeException("aptActivityLayout = null");
        }
    }

    /**
     * 绑定view
     */
    private void bindView() {
        // 绑定butterknife
        ButterKnife.bind(this);
    }

    /**
     * 数据操作
     */
    protected abstract void initData(Bundle bundle);
}
