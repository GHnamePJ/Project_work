package com.example.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int positioin) {
        return mThumbIds[positioin];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View converView, ViewGroup parent) {
        ImageView imageView;
        if (converView == null) {
            imageView = new ImageView(mContext);
            int width = GridView.LayoutParams.MATCH_PARENT;
            int height = GridView.LayoutParams.MATCH_PARENT;
            imageView.setLayoutParams(new GridView.LayoutParams(width, height));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            imageView =(ImageView) converView;
        }
        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }

    private Integer[] mThumbIds = {
            R.drawable.p1, R.drawable.p2,
            R.drawable.p3, R.drawable.p4,
            R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8,
            R.drawable.p9, R.drawable.p10,
            R.drawable.p11, R.drawable.p12,
            R.drawable.p13, R.drawable.p14,
            R.drawable.p15, R.drawable.p16
    };
}
