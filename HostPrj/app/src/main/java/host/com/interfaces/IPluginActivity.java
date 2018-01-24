package host.com.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Created by weilai1 on 2018/1/24.
 */

public class IPluginActivity extends Activity implements IPlugin, Attachable<Activity>{
    protected Activity mProxyActivity;

    public void startActivity(Bundle extras, Class jumpClass) {
        if (mProxyActivity != null) {
            Intent intent = new Intent(mProxyActivity, mProxyActivity.getClass());
            intent.putExtras(extras);
            intent.putExtra("class", jumpClass.getName());
            mProxyActivity.startActivity(intent);
        }
    }

    public void startActivityForResult(Bundle extras, Class jumpClass, int requestCode) {
        if (mProxyActivity != null) {
            Intent intent = new Intent(mProxyActivity, mProxyActivity.getClass());
            intent.putExtras(extras);
            intent.putExtra("class", jumpClass);
            mProxyActivity.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void attach(Activity data) {
        mProxyActivity = data;
    }

    @Override
    public View findViewById(int resId) {
        if (mProxyActivity != null) {
            return mProxyActivity.findViewById(resId);
        }

        return super.findViewById(resId);
    }

    @Override
    public Window getWindow() {
        if (mProxyActivity != null) {
            return mProxyActivity.getWindow();
        }

        return super.getWindow();
    }
    @Override
    public void setContentView(int layoutId) {
        if (mProxyActivity != null) {
            mProxyActivity.setContentView(layoutId);
        }
    }
    @Override
    public void onCreateP(Bundle savedInstanceState) {

    }

    @Override
    public void onPauseP() {

    }

    @Override
    public void onRestartP() {

    }

    @Override
    public void onStartP() {

    }

    @Override
    public void onResumeP() {

    }

    @Override
    public void onStopP() {

    }

    @Override
    public void onDestroyP() {

    }
}
