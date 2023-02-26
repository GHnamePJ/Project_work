package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class myAdapter extends BaseAdapter {
    private  BookItem[] books={
            new BookItem("java程序设计",R.drawable.p1),
            new BookItem("Android应用开发",R.drawable.p2),
            new BookItem("Oracle数据库管理指南",R.drawable.p3),
            new BookItem("Javaweb程序设计",R.drawable.p4),
            new BookItem("软件工程师之系统工程师之路",R.drawable.p5),
            };
    LayoutInflater inflater;
    int id_item;

    public  myAdapter(Context context, int id_item){
        this.id_item=id_item;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return books.length;
    }

    @Override
    public Object getItem(int position) {
        return books[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")@Override
    public View getView(int position, View view, ViewGroup parent) {
        LinearLayout l1= (LinearLayout) inflater.inflate(id_item,parent,false);

        ImageView imageView=(ImageView) l1.findViewById(R.id.book_photo);
        imageView.setImageResource(books[position].photo);

        TextView textView;
        textView= (TextView) l1.findViewById(R.id.book_name);
        textView.setText(books[position].name);

        return l1;
    }
    private class BookItem{
        String name;
        int photo;
        public BookItem(String name,int photo){
            this.name=name;
            this.photo=photo;
        }
    }
}
