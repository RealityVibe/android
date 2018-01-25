package com.example.yyyyz.winnerblog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.yyyyz.winnerblog.FindFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private List<Fragment> fragList;
    private RecyclerView recyclerView;
    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<Tab> tabList = new ArrayList<Tab>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initTabs();
/*        //初始化碎片列表
        fragList = new ArrayList<Fragment>();
        fragList.add(new FindFragment());
        fragList.add(new MovieFragment());
        fragList.add(new RadioFragment());
        fragList.add(new GroupFragment());
        fragList.add(new MineFragment());

        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), fragList);
        viewPager = (ViewPager) findViewById(R.id.content_viewpager);
        viewPager.setAdapter(fragAdapter);*/
    }

    private void initTabs() {
//        Tab tab_find =  new Tab(R.drawable.nav_home_selected, "发现", FindFragment.class);

        tabList.add(new Tab(R.drawable.nav_home_original, "发现", FindFragment.class));
        tabList.add(new Tab(R.drawable.nav_movie, "电影", MovieFragment.class));
        tabList.add(new Tab(R.drawable.nav_radio, "广播", RadioFragment.class));
        tabList.add(new Tab(R.drawable.nav_group, "团组", GroupFragment.class));
        tabList.add(new Tab(R.drawable.nav_mine, "我的", MineFragment.class));

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);
        mInflater = LayoutInflater.from(this);

        for(Tab tab : tabList){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tab.getText());
            tabSpec.setIndicator(buildView(tab));
            mTabHost.addTab(tabSpec,tab.getFragment(), null);
        }
        //通过这行代码可以去除掉底部菜单5个图表之间的分割线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);    }

    //设置Indicator中的View
    private View buildView(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView Tab_img = (ImageView) view.findViewById(R.id.tab_img);
        TextView Tab_txt = (TextView) view.findViewById(R.id.tab_txt);

        Tab_img.setBackgroundResource(tab.getImage());
        Tab_txt.setText(tab.getText());
        return view;
    }
}
