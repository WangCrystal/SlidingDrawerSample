package com.example.SampleSlidingMenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimationList extends Activity {

    private ListView listview;
    private DisplayMetrics metrics;
    private int mode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        listview = new ListView(this);
        listview.setFadingEdgeLength(0);
        ArrayList<String> strings = new ArrayList<String>();

        for (int i = 0; i < 300; i++) {
            strings.add("Item:#" + (i + 1));
        }

        MainAdapter mAdapter = new MainAdapter(this, strings, metrics);

        listview.setAdapter(mAdapter);

        setContentView(listview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, 1, 0, "1");
        menu.add(Menu.NONE, 2, 0, "2");
        menu.add(Menu.NONE, 3, 0, "3");
        menu.add(Menu.NONE, 4, 0, "4");
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mode = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public class MainAdapter extends ArrayAdapter<String> {
        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<String> mStrings;
        private DisplayMetrics mMetrics;

        private class Holder {
            public TextView textview;
        }

        public MainAdapter(Context context, ArrayList<String> strings, DisplayMetrics metrics) {
            super(context, 0, strings);
            this.mContext = context;
            this.mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mStrings = strings;
            this.mMetrics = metrics;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final String str = this.mStrings.get(position);
            final Holder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null);
                convertView.setBackgroundColor(0xFF202020);

                holder = new Holder();
                holder.textview = (TextView) convertView.findViewById(android.R.id.text1);
                holder.textview.setTextColor(0xFFFFFFFF);

                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.textview.setText(str);

            Animation animation = null;

            switch (mode) {
                case 1:
                    animation = new TranslateAnimation(mMetrics.widthPixels / 8, 0, 0, 0);
                    break;

                case 2:
                    animation = new TranslateAnimation(0, 0, mMetrics.heightPixels, 0);
                    break;

                case 3:
                    animation = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 0, (float) 1.0);
                    break;

                case 4:
                    //non animation
                    animation = new Animation() {
                    };
                    break;
            }

            animation.setDuration(750);
            convertView.startAnimation(animation);
            animation = null;

            return convertView;
        }

    }
}