package com.lhk.travel.travel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.lhk.travel.travel.adapter.MyFragmentAdapter;
import com.lhk.travel.travel.fragment.First;
import com.lhk.travel.travel.fragment.MusicList;
import com.lhk.travel.travel.fragment.Thrid;
import com.lhk.travel.travel.fragment.diy.FixedSpeedScroller;
import com.lhk.travel.travel.fragment.translate.RotateDownPageTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class MainActivity extends FragmentActivity{

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findVIewById();
        ArrayList<Fragment> fragments = initFragment();
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments);

        viewPager.setAdapter(adapter);

    }

    private void findVIewById() {
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        //DepthPageTransformer效果，第二张是ZoomOutPageTransformer效果。
        viewPager.setPageTransformer(true,new RotateDownPageTransformer());
        //修改滑动速度
        //注意点：
//        PagerAdapter 的destroyItem 每次会删除上一个页面，导致，如果做自动切换页面时会看不到动画.
//
//                解决办法:
//        destoryItem中不做删除view，instantiateItem中对view是否有parent做为判断条件，这种情况适用于，少量的固定的子View，比如一个Banner，banner中做自动循环播放
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(),
                    new AccelerateInterpolator());
            field.set(viewPager, scroller);
            scroller.setmDuration(2000);
        } catch (Exception e) {

        }
    }

    private ArrayList<Fragment> initFragment(){
        ArrayList<Fragment> myFragment = new ArrayList<Fragment>();
        myFragment.add(new First());
        myFragment.add(new MusicList());
        myFragment.add(new Thrid());
        return myFragment;
    }


    public void tabClick(View v){
        switch (v.getId()){
            case R.id.first_tab:
                viewPager.setCurrentItem(0,true);
                break;
            case R.id.second_tab:
                viewPager.setCurrentItem(1,true);
                break;
            case R.id.third_tab:
                viewPager.setCurrentItem(2,true);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
