package zj.app.neverland.folenews.web.browserfun;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    private float mStartGestureX;

    private float mStartGestureY;

    private ViewGroup viewGroup ;

    private boolean mHorizontalScrollDetected;

    private CanChildScrollUpCallback mCanChildScrollUpCallback;
    public interface CanChildScrollUpCallback {
        boolean canSwipeRefreshChildScrollUp();
    }
    public CustomSwipeRefreshLayout(Context context) {
        super(context,null);
    }
    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setCanChildScrollUpCallback(CanChildScrollUpCallback canChildScrollUpCallback) {
        mCanChildScrollUpCallback = canChildScrollUpCallback;    }
    @Override
    public boolean canChildScrollUp() {
        if (mCanChildScrollUpCallback != null) {
            return mCanChildScrollUpCallback.canSwipeRefreshChildScrollUp();
        }
        return super.canChildScrollUp();
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartGestureX = event.getX();
                mStartGestureY = event.getY();
                mHorizontalScrollDetected = false;
                break;

            case MotionEvent.ACTION_MOVE:
                mHorizontalScrollDetected = Math.abs(event.getX() - mStartGestureX) >
                        Math.abs(event.getY() - mStartGestureY);
                if (mHorizontalScrollDetected) {
                    return false;
                }
                break;
        }

        return super.onInterceptTouchEvent(event);
    }



    public ViewGroup getViewGroup() {
        return viewGroup;
    }
    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if(null!=viewGroup){
            if(viewGroup.getScrollY()== 0){
                //直接截断时间传播
                return false;
            }else{
                return super.onTouchEvent(arg0);
            }
        }
        return super.onTouchEvent(arg0);
    }
}
