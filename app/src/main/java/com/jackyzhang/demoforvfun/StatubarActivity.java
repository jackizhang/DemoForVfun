package com.jackyzhang.demoforvfun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackyzhang.demoforvfun.utils.ScreenUtils;
import com.jackyzhang.demoforvfun.widgets.OnCreateContentView;
import com.jackyzhang.demoforvfun.widgets.PopEnterOrientation;
import com.jackyzhang.demoforvfun.widgets.PopupBuilder;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StatubarActivity extends AppCompatActivity {

    private Context mContext;
    private List<String> mSongs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        mContext = this;
        setContentView(R.layout.activity_statubar);
        new PopupBuilder(mContext)
                .width((int)(ScreenUtils.getScreenWidth(mContext) * 0.5))
                .height(ScreenUtils.getScreenHeight(mContext))
                .orientation(PopEnterOrientation.RightToLeft)
                .setContentView(new OnCreateContentView<View>() {
                    @Override
                    public View onCreateContent() {
                        LinearLayout ll = new LinearLayout(mContext);
                        ll.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        RecyclerView rv = new RecyclerView(StatubarActivity.this);
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new LinearLayoutManager(StatubarActivity.this));
                        rv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        rv.setAdapter(new CommonAdapter<String>(mContext,R.layout.list_song_item,mSongs) {
                            @Override
                            protected void convert(ViewHolder holder, String str, int position) {
                                holder.setText(R.id.tv_song,mDatas.get(position));
                            }

                        });
                        ll.addView(rv);
                        return ll;
                    }
        }).show();
    }


    private void initData() {
        mSongs.clear();
        for(int i =0;i<100;i++){
            mSongs.add(String.valueOf(i));
        }
    }
}
