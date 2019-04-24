package com.heima.advancedlight.section3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heima.advancedlight.R;

import java.io.StringReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleView extends LinearLayout {
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.ll_title_main)
    LinearLayout llTitleMain;
    int mColor = Color.BLUE, mTextColor = Color.WHITE;
    String titleText = "";
    private Bitmap mImage;
    Drawable drawable;

    public TitleView(Context context) {
        super(context);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
        initView(context);
    }

    private void initAttr( AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.titleBar);
        mTextColor = typedArray.getColor(R.styleable.titleBar_title_text_color, Color.BLUE);
        mColor = typedArray.getColor(R.styleable.titleBar_title_text_bg, Color.BLUE);
        titleText = typedArray.getString(R.styleable.titleBar_title_text);
        drawable = ContextCompat.getDrawable(getContext(), typedArray.getResourceId(R.styleable.titleBar_left_src, R.mipmap.ic_launcher_round));
    }



    private void initView(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.view_customtitle, this, true);
        ButterKnife.bind(this, contentView);
         llTitleMain.setBackgroundColor(mColor);
         tvTitle.setTextColor(mTextColor);
        tvTitle.setText(titleText);
        ivTitleLeft.setImageDrawable(drawable);
    }

    public void setTitleText(String title) {
        if (title != null) {
            tvTitle.setText(title);
        }
    }

    public void setLeftListener(OnClickListener onClickListener) {
        ivTitleLeft.setOnClickListener(onClickListener);
    }


}
