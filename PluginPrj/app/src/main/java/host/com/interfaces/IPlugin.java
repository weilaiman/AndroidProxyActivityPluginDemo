package host.com.interfaces;

import android.os.Bundle;

/**
 * Created by weilai1 on 2018/1/24.
 */

public interface IPlugin {
    void onCreateP(Bundle savedInstanceState);
    void onPauseP();
    void onRestartP();
    void onStartP();
    void onResumeP();
    void onStopP();
    void onDestroyP();
}