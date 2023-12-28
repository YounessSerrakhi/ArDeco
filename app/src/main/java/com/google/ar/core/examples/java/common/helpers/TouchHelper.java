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
 * Helper to detect taps and swipe events using Android GestureDetector, and pass them between
 * UI thread and render thread.
 */
public final class TouchHelper implements OnTouchListener {
    private final GestureDetector gestureDetector;
    private final BlockingQueue<MotionEvent> queuedSingleTaps = new ArrayBlockingQueue<>(16);
    private final BlockingQueue<MotionEvent> queuedSwipeEvents = new ArrayBlockingQueue<>(16);

    /**
     * Creates the touch helper.
     *
     * @param context the application's context.
     */
    public TouchHelper(Context context) {
        gestureDetector =
                new GestureDetector(
                        context,
                        new GestureDetector.SimpleOnGestureListener() {
                            @Override
                            public boolean onSingleTapUp(MotionEvent e) {
                                // Queue tap if there is space. Tap is lost if queue is full.
                                queuedSingleTaps.offer(e);
                                return true;
                            }

                            @Override
                            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                                // Queue the swipe event with start and end coordinates.
                                queuedSwipeEvents.offer(e2);
                                Log.d("TouchHelper", "onScroll: " + e1.getX() + ", " + e1.getY() + ",   to==> " + e2.getX() + ", " + e2.getY());
                                return true;
                            }

                            @Override
                            public boolean onDown(MotionEvent e) {
                                return true;
                            }
                        });
    }

    /**
     * Polls for a tap event.
     *
     * @return If a tap was queued, a MotionEvent for the tap. Otherwise, null if no tap events are queued.
     */
    public MotionEvent pollTap() {
        return queuedSingleTaps.poll();
    }

    /**
     * Polls for a swipe event.
     *
     * @return If a swipe event was queued, a MotionEvent representing the swipe. Otherwise, null if no swipe events are queued.
     */
    public MotionEvent pollSwipe() {
        return queuedSwipeEvents.poll();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
