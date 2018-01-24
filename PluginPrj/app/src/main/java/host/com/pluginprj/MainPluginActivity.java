package host.com.pluginprj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import host.com.interfaces.IPlugin;
import host.com.interfaces.IPluginActivity;

/**
 * Created by weilai1 on 2018/1/24.
 */

public class MainPluginActivity extends IPluginActivity implements IPlugin{
    private TextView tv_hello;

    @Override
    public void onCreateP(Bundle savedInstanceState) {
        setContentView(R.layout.main_layout);
        tv_hello = (TextView) findViewById(R.id.tv_hello);
        tv_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Bundle(), SecondPluginActivity.class);
            }
        });
    }



}
