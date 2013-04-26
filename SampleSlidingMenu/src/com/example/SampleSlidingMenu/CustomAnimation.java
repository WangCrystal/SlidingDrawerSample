package com.example.SampleSlidingMenu;

import android.os.Bundle;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.CanvasTransformer;

public abstract class CustomAnimation extends BaseActivity {
    private final CanvasTransformer mTransformer;

    public CustomAnimation(int resourceTitle, CanvasTransformer transformer) {
        super(resourceTitle);
        mTransformer = transformer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.content_frame, new SampleListFragment()
                ).commit();

        SlidingMenu sm = getSlidingMenu();
        setSlidingActionBarEnabled(true);
        sm.setBehindScrollScale(0.0f);
        sm.setBehindCanvasTransformer(mTransformer);
    }
}
