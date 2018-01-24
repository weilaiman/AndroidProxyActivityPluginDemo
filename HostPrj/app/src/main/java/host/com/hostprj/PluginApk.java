package host.com.hostprj;

import android.content.pm.PackageInfo;
import android.content.res.Resources;

/**
 * Created by weilai1 on 2018/1/24.
 */

public class PluginApk {
    public PackageInfo packageInfo;
    public Resources resources;
    public ClassLoader classLoader;

    public PluginApk(Resources res) {
        resources = res;
    }
}
