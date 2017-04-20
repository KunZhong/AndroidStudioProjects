package com.example.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by user on 17-4-20.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {

            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    /*
     *   将fruit_item布局加载进来，
     *   然后创建一个viewholder实例，并且加载出来的布局传入到构造函数中
     *   最后将viewholder的实例返回
     * */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "clicked view "+fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "clicked image "+fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    /*
     * 用于对子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行
     * 通过position参数得到当前的Fruit实例
     * 然后将数据设置到viewholder的imageview和textview当中
     * */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}

//RecyclerView 没有提供类似于setOnItemClickListener()这样的注册监听器方法
//需要我们自己给子项具体的View去注册点击事件，

//ListView在点击事件上处理并不人性化，setOnItemClickListener()注册的是子项的点击事件
//如果我想点击的是子项里具体的某一个按钮呢？

//RecyclerVie,所有点击事件都由具体的View去注册
