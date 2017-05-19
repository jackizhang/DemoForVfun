package com.jackyzhang.demoforvfun.widgets;

import android.view.View;

/**
 * Created by zhangyunpu on 2017/5/18.
 */

public interface OnCreateContentView<T extends View>{
    public T onCreateContent();
}
