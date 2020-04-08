package com.example.esppad.calc2.Main;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.esppad.calc2.Main.About.aboutFragment;
import com.example.esppad.calc2.Main.Accounting.AccountingFragment;
import com.example.esppad.calc2.Main.Tools.ToolsFragment;
import com.example.esppad.calc2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //Button btn1;
    final Fragment calcFragment =new CalcFragment();
    final Fragment accountingFragment = new AccountingFragment();
    final Fragment gameFragment = new GameFragment();
    final Fragment toolsFragment = new ToolsFragment();
    final Fragment aboutFragment = new aboutFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment activeFragment = calcFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom_home);
        navigation.setItemIconTintList(null);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fm.beginTransaction().add(R.id.main_container,aboutFragment,"5").hide(aboutFragment).commit();
        fm.beginTransaction().add(R.id.main_container,toolsFragment,"4").hide(toolsFragment).commit();
        fm.beginTransaction().add(R.id.main_container,gameFragment,"3").hide(gameFragment).commit();
        fm.beginTransaction().add(R.id.main_container,accountingFragment,"2").hide(accountingFragment).commit();
        fm.beginTransaction().add(R.id.main_container,calcFragment,"1").commit();

        //if(btn1.getWidth()>btn1.getHeight()){
        //    btn1.setWidth(btn1.getHeight());
        //    Log.i("Log","width is greater");
        //}else{
        //    btn1.setHeight(btn1.getWidth());
        //    Log.i("Log","Height is greater");
        //}
        //resizeBtn(btn1);

        //Log.i("Log","width is: " + width + " - height is: " + height);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_calc:
                    fm.beginTransaction().hide(activeFragment).show(calcFragment).commit();
                    activeFragment = calcFragment;
                    return true;
                case R.id.navigation_accounting:
                    fm.beginTransaction().hide(activeFragment).show(accountingFragment).commit();
                    activeFragment = accountingFragment;
                    return true;
                case R.id.navigation_games:
                    fm.beginTransaction().hide(activeFragment).show(gameFragment).commit();
                    activeFragment = gameFragment;
                    return true;
                case R.id.navigation_tools:
                    fm.beginTransaction().hide(activeFragment).show(toolsFragment).commit();
                    activeFragment = toolsFragment;
                    return true;
                case R.id.navigation_about:
                    fm.beginTransaction().hide(activeFragment).show(aboutFragment).commit();
                    activeFragment = aboutFragment;
                    return true;
            }
            return false;
        }
    };


    public void resizeBtn(Button btn){
        int width = 1800;
        int height = 2560;
        int size = 240;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        int widthNow = displayMetrics.widthPixels;
        int heightNow = displayMetrics.heightPixels;
        float widthRatio = (float) widthNow/width;
        float heightRatio = (float) heightNow/height;
        LinearLayout.LayoutParams layoutParams = new  LinearLayout.LayoutParams(width,height);
        layoutParams.gravity = 1;
        if(widthRatio<heightRatio){
            int newSize = (int) (size*widthRatio);
            layoutParams.width = newSize;
            layoutParams.height = newSize;
            Log.i("Log","params are: " +  layoutParams.height + " - " + layoutParams.width);
        }else{
            int newSize = (int) (size*heightRatio);
            layoutParams.width = newSize;
            layoutParams.height = newSize;
            Log.i("Log","params are: " +  layoutParams.height + " - " + layoutParams.width);
        }
        btn.setLayoutParams(layoutParams);
        Log.i("Log","ratios are: " + heightRatio + " - " + widthRatio);
    }
}
