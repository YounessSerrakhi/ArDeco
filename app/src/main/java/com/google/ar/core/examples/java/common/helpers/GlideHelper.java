package com.google.ar.core.examples.java.common.helpers;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Helper to detect swipe or glide events using Android GestureDetector, and pass the events between
 * UI thread and render thread.
 */
public final class GlideHelper implements OnTouchListener {
    private final GestureDetector gestureDetector;
    private final BlockingQueue<MotionEvent> queuedSwipeEvents = new ArrayBlockingQueue<>(16);



    /**
     * Creates the glide helper.
     *
     * @param context the application's context.
     */
    public GlideHelper(Context context) {
        gestureDetector =
                new GestureDetector(
                        context,
                        new GestureDetector.SimpleOnGestureListener() {
                            @Override
                            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                                // Queue the swipe event with start and end coordinates.
                                queuedSwipeEvents.offer(e2);
                                Log.d("GlideHelper", "onScroll: " + e1.getX() + ", " + e1.getY() + ",   to==> " + e2.getX() + ", " + e2.getY());
                                return true;
                            }

                            @Override
                            public boolean onDown(MotionEvent e) {
                                return true;
                            }
                        });
    }

    /**
     * Polls for a swipe event.
     *
     * @return if a swipe event was queued, a SwipeEvent object representing the swipe. Otherwise null if no swipe events are queued.
     */
    public MotionEvent poll() {
        return queuedSwipeEvents.poll();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
