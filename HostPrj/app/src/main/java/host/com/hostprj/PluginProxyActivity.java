package host.com.hostprj;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.io.File;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import dalvik.system.DexClassLoader;
import host.com.interfaces.IPlugin;
import host.com.interfaces.IPluginActivity;

/**
 * Created by weilai1 on 2018/1/24.
 */

public class PluginProxyActivity extends Activity {
    private LifeCircleController mPluginController = new LifeCircleController(this);

    private PluginApk createApk(String apkPath) {
        PluginApk pluginApk = null;
        try {
            // 事实就是跟前面那样动态加载资源的原理是一样的
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, apkPath);
            Resources resources = new Resources(assetManager, super.getResources().getDisplayMetrics(),
                    super.getResources().getConfiguration());
            pluginApk = new PluginApk(resources);
            pluginApk.classLoader = new DexClassLoader(apkPath, new File(apkPath).getParentFile().getAbsolutePath()
                                        , new File(apkPath).getParentFile().getAbsolutePath() + "libs", getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pluginApk;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PluginApk pluginInfo = createApk(getFilesDir().getAbsolutePath() + "/test.apk");
        super.onCreate(savedInstanceState);
        if (mPluginController != null) {
            mPluginController.setPluginInfo(pluginInfo);
            mPluginController.onCreateP(getIntent().getExtras());
        }
    }

    @Override
    public AssetManager getAssets() {
        if (mPluginController != null) {
            AssetManager assetManager = mPluginController.getAssets();
            if (assetManager != null) {
                return assetManager;
            } else {
                return super.getAssets();
            }
        }
        return super.getAssets();
    }

    @Override
    public Resources getResources() {
        if (mPluginController != null) {
            Resources resources = mPluginController.getResources();
            if (resources != null) {
                return resources;
            } else {
                return super.getResources();
            }
        }

        return super.getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mPluginController != null) {
            ClassLoader classLoader = mPluginController.getClassLoader();
            if (classLoader != null) {
                return classLoader;
            } else {
                return super.getClassLoader();
            }
        }

        return super.getClassLoader();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPluginController != null) {
            mPluginController.onStartP();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPluginController != null) {
            mPluginController.onPauseP();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPluginController != null) {
            mPluginController.onStopP();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPluginController != null) {
            mPluginController.onResumeP();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPluginController != null) {
            mPluginController.onDestroyP();
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        if (mPluginController != null) {
            mPluginController.onRestartP();
        }
    }

//    @Override
//    public void setContentView(int layoutId) {
//        Log.e("AAA", "setContentView");
//        super.setContentView(layoutId);
//    }
}
