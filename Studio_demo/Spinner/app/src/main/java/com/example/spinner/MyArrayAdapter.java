package com.example.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
public class MyArrayAdapter extends ArrayAdapter {
    String[] titles;
    String[] desc={
        "所谓五千米晨跑，就是不能少于五千米，当然，你可以多跑！",
        "所谓去健身房健身,j就是要亲自练",
        "所谓爬山，山的高度至少要有1000米高",
        "所谓打篮球，是十个人比赛，而不是单打独斗",
    };
    int[] images={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};
    private LayoutInflater mInflater;

    public MyArrayAdapter( @NonNull Context context, int resource,@NonNull CharSequence[] objects) {
        super(context,resource,objects);
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        titles=context.getResources().getStringArray(R.array.habit);
    }
    @Override
    public int getCount(){
        return titles.length;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View v;
        v=mInflater.inflate(R.layout.item,parent,false);
        ImageView iv=(ImageView)v.findViewById(R.id.imageView);
        iv.setImageResource(images[position]);
        TextView tv1=(TextView)v.findViewById(R.id.textView1);
        tv1.setText(titles[position]);
        TextView tv2=(TextView)v.findViewById(R.id.textView2);
        tv2.setText(desc[position]);
        return v;
    }
}
