package com.example.administrator.netlisterdemo;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.netlisterdemo.annotation.Network;

public class ScrollingActivity extends AppCompatActivity implements NetworkListener {

    private NetStateReciver netStateReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        registerNetReciver();
//        initNetmanage();

        NetworkManage.getDefault().setListener(this);

    }

    private void initNetmanage() {
        NetworkUtils.hasNetworkConnection(this);
    }

    private void registerNetReciver() {
        netStateReciver = new NetStateReciver();
        netStateReciver.setNetworkLisenter(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contanst.NET_ACTION);
        registerReceiver(netStateReciver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(netStateReciver);
    }

    @Override
    public void onConnect(NetType netType) {
        Log.e("ppp", "onConnect: "+netType );
    }

    @Override
    public void onDisConnect() {
        Log.e("ppp", "onDisConnect:");
    }


    @Network
    public void testNetwork(){

    }
}
