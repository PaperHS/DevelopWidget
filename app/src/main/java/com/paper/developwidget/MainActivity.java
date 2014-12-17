package com.paper.developwidget;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.paper.developwidget.db.AppPackeget;
import com.paper.developwidget.db.AppPacketManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.edittext)
    EditText mSearchView;
    @InjectView(R.id.listview)
    ListView mListview;

    AppPacketManager manager;
    PackageManager pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        manager = new AppPacketManager(this);
        pm = getPackageManager();
//        List<PackageInfo> list = PackageUtils.getInstalledApplication(this);
//        AppPacketManager manager = new AppPacketManager(this);
//        for (PackageInfo info : list){
//            AppPackeget a = new AppPackeget(info.applicationInfo.name,info.packageName);
//            manager.save(a);
//        }
        mSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s !=null){
                    List<AppPackeget> datas = manager.queryMix(s.toString());
                    for (AppPackeget app :datas){

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
