package com.example.listviewdemo;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 17-4-20.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context,int textViewResourceID,
                        List<Fruit> objects){
        super(context,textViewResourceID,objects);
        resourceId = textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Fruit fruit = getItem(position);

        View view;
        ViewHolder viewHolder;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);

            view.setTag(viewHolder);    //将viewHolder存储在View中
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();    //重新获取ViewHolder
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());

        return view;
    }
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}
//重写父类的构造函数，传入上下文，ListView子项布局的id和数据
//重写getView()，这个方法在每个子项被滚动到屏幕内的时候会被调用

//getItem()得到当前项的实例，使用LayoutInflater为这个子项加载我们传入的布局。

//从一个Context中，获得一个布局填充器，这样你就可以使用这个填充器来把xml布局文件转为View对象了。
//1、加载布局管理器
//       LayoutInflater inflater = LayoutInflater.from(context);
//2、将xml布局转换为view对象
//        convertView = inflater.inflate(R.layout.item_myseallist,parent, false);
//resource：需要加载布局文件的id，意思是需要将这个布局文件中加载到Activity中来操作。
//第三个参数指定成false，表示只让我们在父布局中声明的layout属性生效

//3、利用view对象，找到布局中的组件
//        convertView.findViewById(R.id.delete);

//convertView参数用于将之前加载好的布局进行缓存，以便之后可以进行重用
//ViewHolder 对 每次调用findViewById()方法获取控件的实例 进行优化
//内部类   ViewHolder，用于对控件的实例进行缓存
//