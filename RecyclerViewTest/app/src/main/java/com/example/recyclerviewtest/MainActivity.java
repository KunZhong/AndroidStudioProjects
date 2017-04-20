package com.example.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        for (int i = 0; i < 100; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.studio);

            fruitList.add(apple);
        }
    }

    //使用Random对象创造一个1到20之间的随机数，然后将参数中传入的字符串重复随机遍。
    //保证水果名字的长短差距都比较大，子项的高度也就各不相同


    private String getRandomLengthName(String name){

        Random random = new Random();
        int length = random.nextInt(20) + 1;

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i< length; i++){
            builder.append(name);
        }
        return builder.toString();
    }

}












// 获取到recyclerview实例，然后创建一个linearLayout-Manager对象
// 并将它设置到recyclerview中，指定recyclerview的布局方式，线性布局。类似listview的效果

//2,横向滚动效果
// 将 fruit_item里的元素改成垂直排列，并把宽度设为100dp，
// MainActivity中调用LinearLayoutManager的setOrientation()方法来
//设置布局的排列方向，默认是纵向排列的，传入LinearLayoutManager.HORIZONTAL表示让布局横向排列

//ListView的布局排列是由自身去管理的，
// 而RecyclerView则将这个工作交给了LayoutManager，
// LayoutManager中制定了一套可扩展的布局排列接口，
// 子类只要按照接口的规范来实现，就能定制出各种不同排列方式的布局了。

// RecyclerView 提供 LinearLayoutManager，GridLayoutManager和StaggeredGridLayoutManager
// 这两种内置的布局排列方式。
// GridLayoutManager 用于实现网格布局
// StaggeredGridLayoutManager 用于实现瀑布流布局