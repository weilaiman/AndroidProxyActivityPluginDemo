package host.com.hostprj;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import host.com.interfaces.IPlugin;
import host.com.interfaces.IPluginActivity;

/**
 * Created by weilai1 on 2018/1/24.
 */

public class LifeCircleController implements IPlugin{
    private IPluginActivity mPluginActivity;
    private Activity mActivityProxy;
    private PluginApk mPluginInfo;

    public LifeCircleController(Activity activity) {
        mActivityProxy = activity;
    }


    public void setPluginInfo(PluginApk info) {
        mPluginInfo = info;
    }

    public ClassLoader getClassLoader() {
        if (mPluginInfo != null) {
            return mPluginInfo.classLoader;
        }
        return null;
    }

    public Resources getResources() {
        if (mPluginInfo != null) {
            return mPluginInfo.resources;
        }

        return null;
    }

    public AssetManager getAssets() {
        if (mPluginInfo != null && mPluginInfo.resources != null) {
            return mPluginInfo.resources.getAssets();
        }

        return null;
    }


    private IPluginActivity createPluginActivity(String className) {
        if (mPluginInfo != null) {
            try {
                return (IPluginActivity) (mPluginInfo.classLoader.loadClass(className)).newInstance();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    @Override
    public void onCreateP(Bundle extras) {
        if (extras != null) {
            String className = extras.getString("class");
            String packageInfo = extras.getString("package");

            mPluginActivity = createPluginActivity(className);
            if (mPluginActivity != null) {
                //唯一一处用到宿主activity的地方
                mPluginActivity.attach(mActivityProxy);
                mPluginActivity.onCreateP(null);
            }

        }

    }

    @Override
    public void onPauseP() {
        if (mPluginActivity != null) {
            mPluginActivity.onPauseP();
        }
    }

    @Override
    public void onRestartP() {
        if (mPluginActivity != null) {
            mPluginActivity.onRestartP();
        }
    }

    @Override
    public void onStartP() {
        if (mPluginActivity != null) {
            mPluginActivity.onStartP();
        }
    }

    @Override
    public void onResumeP() {
        if (mPluginActivity != null) {
            mPluginActivity.onResumeP();
        }
    }

    @Override
    public void onStopP() {
        if (mPluginActivity != null) {
            mPluginActivity.onStopP();
        }
    }

    @Override
    public void onDestroyP() {
        if (mPluginActivity != null) {
            mPluginActivity.onDestroyP();
        }
    }
}
