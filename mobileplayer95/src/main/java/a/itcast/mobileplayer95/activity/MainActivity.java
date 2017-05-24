package a.itcast.mobileplayer95.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import a.itcast.mobileplayer95.R;
import a.itcast.mobileplayer95.fargment.homepage.HomeFragment;
import a.itcast.mobileplayer95.fargment.TestFragment;
import a.itcast.mobileplayer95.fargment.yuedanpage.YueDanFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private SparseArray<Fragment> sparseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //将ToolBar设置为标题栏
        setSupportActionBar(toolbar);
        //修改ToolBar的属性
        getSupportActionBar().setTitle("VMPlayer");

        //初始化 Fragment集合
        sparseArray = new SparseArray<>();
        sparseArray.append(R.id.bottombar_home,new HomeFragment());//首页
        sparseArray.append(R.id.bottombar_mv,TestFragment.newInstance("MV"));
        sparseArray.append(R.id.bottombar_vbang,TestFragment.newInstance("V榜"));
        sparseArray.append(R.id.bottombar_yuedan,new YueDanFragment());//悦单

        //处理底部栏  attach:表示把我当前底部栏附加到一个界面上去
        //savedInstanceState:用来界面销毁时来保存数据用的.
        BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.bottombar, new OnMainMenuTabClickListener());

    }

    //给ToolBar设置个目录菜单图标
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //创建 menu 菜单,这个菜单会依附到ToolBar上
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //处理菜单的点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //处理 menu 菜单的点击监听

        switch (item.getItemId())
        {
            case R.id.menu_main_settings:
                //Toast.makeText(this, "跳转到设置界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class OnMainMenuTabClickListener implements OnMenuTabClickListener {
        @Override
        /**
         * 选中了某一个 Tap
         */
        public void onMenuTabSelected(@IdRes int menuItemId) {
//                Toast.makeText(MainActivity.this, "选中了一个 条目", Toast.LENGTH_SHORT).show();
//            Fragment fragment = TestFragment.newInstance("这是一个测试界面");

            //切换到当前按钮对应的Fragment
            Fragment fragment = sparseArray.get(menuItemId);
            switchFragment(fragment);
        }

        @Override
        /**
         * 重复选中了某一个 Tap
         */
        public void onMenuTabReSelected(@IdRes int menuItemId) {
//                Toast.makeText(MainActivity.this, "重复选中了一个 条目", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 将参数里的Fragment 显示出来
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container,fragment);



        transaction.commit();
    }


}
