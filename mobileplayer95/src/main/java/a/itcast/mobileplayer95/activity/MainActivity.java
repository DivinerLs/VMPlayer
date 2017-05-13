package a.itcast.mobileplayer95.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import a.itcast.mobileplayer95.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //将ToolBar设置为标题栏
        setSupportActionBar(toolbar);
        //修改ToolBar的属性
        getSupportActionBar().setTitle("VMPlayer");

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

    private static class OnMainMenuTabClickListener implements OnMenuTabClickListener {
        @Override
        /**
         * 选中了某一个 Tap
         */
        public void onMenuTabSelected(@IdRes int menuItemId) {
//                Toast.makeText(MainActivity.this, "选中了一个 条目", Toast.LENGTH_SHORT).show();
        }

        @Override
        /**
         * 重复选中了某一个 Tap
         */
        public void onMenuTabReSelected(@IdRes int menuItemId) {
//                Toast.makeText(MainActivity.this, "重复选中了一个 条目", Toast.LENGTH_SHORT).show();
        }
    }
}
