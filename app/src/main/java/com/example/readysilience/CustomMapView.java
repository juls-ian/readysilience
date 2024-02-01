package com.example.readysilience;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.google.android.gms.maps.MapView;

public class CustomMapView extends MapView {

    public CustomMapView(Context context) {
        super(context);
    }

    public CustomMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // Intercept touch events to prevent ScrollView interference
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
