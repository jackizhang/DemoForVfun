package com.jackyzhang.demoforvfun.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.GridView;

/**
 * Created by zhangyunpu on 2017/5/17.
 */

public class PopupBuilder {
    private Dialog mDialog;
    private FrameLayout mFrameLayout;
    private int mWidth;
    private int mHeight;
    private OnCreateContentView<View> mOnCreateView;
    private int mAnimStyle;
    private PopEnterOrientation mOrientation = PopEnterOrientation.RightToLeft;

    public PopupBuilder(Context context){
        init(context);
    }


    public void init(Context context){
        mFrameLayout = new FrameLayout(context);
        mFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(mWidth,mHeight));
        mDialog = new Dialog(context);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        Window window = mDialog.getWindow();
        window.setWindowAnimations(0);
        window.setGravity(mOrientation == PopEnterOrientation.RightToLeft? Gravity.BOTTOM|Gravity.LEFT:Gravity.BOTTOM|Gravity.RIGHT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setContentView(mFrameLayout);
    }

    public void show(){
        if(isValueEnough())
            mDialog.show();
//        else
            //log output which data is wrong
    }

    public void dismiss(){
        mDialog.dismiss();
    }

    public PopupBuilder orientation(PopEnterOrientation orientation){
        mOrientation = orientation;
        switch (mOrientation){
            case BottomToTop:
                mDialog.getWindow().setGravity(Gravity.BOTTOM);
                break;
            case TopToBottom:
                mDialog.getWindow().setGravity(Gravity.TOP);
                break;
            case LeftToRight:
                mDialog.getWindow().setGravity(Gravity.LEFT);
                break;
            case RightToLeft:
                mDialog.getWindow().setGravity(Gravity.RIGHT);
                break;
            default:
                break;
        }
        return this;
    }


    public PopupBuilder width(int width){
        ViewGroup.LayoutParams params = mFrameLayout.getLayoutParams();
        if(params == null){
            params = new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }else{
            params.width = width;
        }
        mFrameLayout.setLayoutParams(params);
        return this;
    }

    public PopupBuilder height(int height){
        ViewGroup.LayoutParams params = mFrameLayout.getLayoutParams();
        if(params == null){
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
        }else{
            params.height = height;
        }
        mFrameLayout.setLayoutParams(params);
        return this;
    }

    public PopupBuilder setAnimStyle(@StyleRes int animStyle){
        mAnimStyle = animStyle;
        mDialog.getWindow().setWindowAnimations(animStyle);
        return this;
    }

    public PopupBuilder setContentView(OnCreateContentView<View> onCreateContentView){
        mOnCreateView = onCreateContentView;
        mFrameLayout.removeAllViews();
        mFrameLayout.addView(mOnCreateView.onCreateContent());
        return this;
    }

    public boolean isShowing(){
        return mDialog.isShowing();
    }

    public PopupBuilder setSize(int width , int height){
        ViewGroup.LayoutParams params = mFrameLayout.getLayoutParams();
        if(params == null){
            params = new FrameLayout.LayoutParams(width,height);
        }else{
            params.width = width;
            params.height = height;
        }
        mFrameLayout.setLayoutParams(params);
        return this;
    }

    private boolean isValueEnough(){
        return true;
    }


}
