package host.com.pluginprj;

import android.os.Bundle;

import host.com.interfaces.IPluginActivity;

/**
 * Created by apple on 2018/1/24.
 */

public class SecondPluginActivity extends IPluginActivity {
    @Override
    public void onCreateP(Bundle savedInstanceState) {
        setContentView(R.layout.second_main_layout);
    }
}
