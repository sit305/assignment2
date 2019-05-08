package com.example.androidmultichoicesquiz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
//import android.util.DisplayMetrics;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.androidmultichoicesquiz.Adapter.CategoryAdapter;
import com.example.androidmultichoicesquiz.Common.Common;
import com.example.androidmultichoicesquiz.Common.SpaceDecoration;
import com.example.androidmultichoicesquiz.DBHelper.DBHelper;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recycler_category;

    //Ctrl+O

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_menu_settings)
        {
            showSettings();
        }
        return true;
    }

    private void showSettings() {
        View setting_latout = LayoutInflater.from(this)
                .inflate(R.layout.settings_layout,null);
        final CheckBox ckb_online_mode_mode = (CheckBox)setting_latout.findViewById(R.id.ckb_online_mode);

        //Load data from Paper , if not available just init default false
        ckb_online_mode_mode.setChecked(Paper.book().read(Common.KEY_SAVE_ONLINE_MODE,false));

        //Show Dialog
        new MaterialStyledDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.ic_settings_white_24dp)
                .setTitle("Settings")
                .setDescription("Please choose action")
                .setCustomView(setting_latout)
                .setNegativeText("DISMISS")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveText("SAVE")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        if (ckb_online_mode_mode.isChecked())
                        {
                            Common.isOnlineMode = true;
                        }
                        else
                        {
                            Common.isOnlineMode=false;
                        }


                        //Save
                        Paper.book().write(Common.KEY_SAVE_ONLINE_MODE,ckb_online_mode_mode.isChecked());

                    }
                }).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init paper
        Paper.init(this);

        //Get value online Mode
        Common.isOnlineMode = Paper.book().read(Common.KEY_SAVE_ONLINE_MODE,false); // Default false

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Quiz Game SIT305");
        setSupportActionBar(toolbar);

        recycler_category = (RecyclerView) findViewById(R.id.recycler_category);
        recycler_category.setHasFixedSize(true);
        recycler_category.setLayoutManager(new GridLayoutManager(this, 2));

        //Get Screen height
        CategoryAdapter adapter = new CategoryAdapter(MainActivity.this, DBHelper.getInstance(this).getALLCategories());
        int spaceInPixel = 4;
        recycler_category.addItemDecoration(new SpaceDecoration(spaceInPixel));
        recycler_category.setAdapter(adapter);

    }
}
