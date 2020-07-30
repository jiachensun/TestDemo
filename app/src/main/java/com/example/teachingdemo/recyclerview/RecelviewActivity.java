package com.example.teachingdemo.recyclerview;

import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.teachingdemo.R;
import com.example.teachingdemo.UserBean;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecelviewActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private MyAdapter myAdapter;
    private List<UserBean> myList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recelview);
        ButterKnife.bind(this);

        initAdapter();
    }

    private void initAdapter() {

        myAdapter = new MyAdapter();

        for (int i = 0; i < 10; i ++) {
            int randomInt = new Random().nextInt(4) + 2;
            String id = "id";
            String name = "name";
            String mail = "mail";
            for (int j = 0; j < randomInt; j ++) {
                id = id + "\n";
                name = name + "\n";
                mail = mail + "\n";

            }
            myList.add(new UserBean(id + i, name + i, mail + i));
        }

        myAdapter.setDatas(myList);
        // 以垂直或者水平列表方式展示Item
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 以网格方式展示Item，如果控件的高度为wrap_content的话，每一行是固定高度(以最高的为行高)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 以瀑布流方式展示Item，如果控件的高度为wrap_content的话，会自动位置补充，不会出现空白
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);

        // 使用同步的DiffUtil方式刷新指定位置的adapter
//        MyDiffUtilCallback aa = new MyDiffUtilCallback(myList, myList);
//        DiffUtil.DiffResult result = DiffUtil.calculateDiff(aa);
//        result.dispatchUpdatesTo(myAdapter);

        rvList.setLayoutManager(staggeredGridLayoutManager);
        rvList.setAdapter(myAdapter);
    }

    public void onRefesh(View view) {

        List<UserBean> newList = new ArrayList<>(myList);
        int randomInt = new Random().nextInt(4);
        UserBean userBean3 = newList.get(randomInt);
        newList.add(randomInt, new UserBean(userBean3.id + "100", "100", "100"));
        newList.remove(0);

        UserBean userBean = new UserBean(newList.get(randomInt).id, newList.get(randomInt).name, newList.get(randomInt).mail);
        userBean.name = userBean.name + "1111111111111111111";
        userBean.mail = userBean.mail + "2222222222222222222";
        newList.set(4, userBean);

        UserBean userBeana = new UserBean(newList.get(3).id, newList.get(3).name, newList.get(3).mail);
        userBeana.name = new Random().nextInt(100) + "ccccccccccccc";
        userBeana.mail = new Random().nextInt(100) + "ddddddddddddd";
        newList.set(3, userBeana);

        // 使用asynclistdiffer 就不用diffutil了
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallback(myList, newList));

        myAdapter.setDatas(newList);
        System.gc();
//        diffResult.dispatchUpdatesTo(myAdapter);

//        myAdapter.notifyItemInserted(2);
    }
}