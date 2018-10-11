package com.ibrickedlabs.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.logging.Handler;

public class SlideAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    Snackbar snackbar;

    SlideAdapter(Context context) {
        this.context = context;

    }

    public int imageArray[] = {R.drawable.ken, R.drawable.ned, R.drawable.ty, R.drawable.snow};


    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        //checking if it is our layout that we designed
        return view == (LinearLayout) object;
    }

    /**
     * We need add these methods externally to  use to instantiate the things to pass
     *
     * @param container
     * @param position
     * @return
     */

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.slide_show, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(context).load(imageArray[position]).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.make(view, "Position " + (position + 1) + " Clicked", Snackbar.LENGTH_SHORT).show();

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        //when we slide from one image to another image it is called
        container.removeView((LinearLayout) object);

    }
}
