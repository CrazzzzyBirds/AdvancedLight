package com.heima.advancedlight.section3;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;

import com.heima.advancedlight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.moveView)
    MoveView moveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);

         moveView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));   //动画移动
        //moveView.smoothScroller(300,400); //平滑移动
        test();
    }

    private void test() {
/*        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(moveView, "translationX", 200);
        objectAnimator.setDuration(2000);
        objectAnimator.start();*/
        AnimView animView = new AnimView(moveView);
        ObjectAnimator.ofInt(animView, "width", 500).setDuration(2000).start();

    }


}
