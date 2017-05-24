package a.itcast.mobileplayer95.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import a.itcast.mobileplayer95.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.splash_iv_bg)
    ImageView splashIvBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        //播放引导动画 缩放动画 用补间动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            //动画开始
            public void onAnimationStart(Animation animation) {

            }

            @Override
            //动画结束
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            //动画重复
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashIvBg.startAnimation(animation);
    }

    private void startMainActivity() {
        //打开主界面
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        //关闭当前页面 引导界面
        finish();

        //动态设置转场动画 使得不然下个界面生硬.(跳转到主界面,并处理透明渐变转场动画)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

    }
}
