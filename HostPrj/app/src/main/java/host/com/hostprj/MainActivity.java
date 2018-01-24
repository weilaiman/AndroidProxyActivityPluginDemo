package host.com.hostprj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by weilai1 on 2018/1/24.
 */

public class MainActivity extends Activity {
    private Button btn_test;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        btn_test = (Button) findViewById(R.id.btn_test);
        nioTransferCopy(new File("/sdcard/test.apk"), new File(getFilesDir().getAbsolutePath() + "/test.apk"));
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PluginProxyActivity.class);
                intent.putExtra("class", "host.com.pluginprj.MainPluginActivity");
                startActivity(intent);
            }
        });
        Log.e("AAA", getFilesDir().getAbsolutePath());
    }

    private void nioTransferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
            inStream.close();
            outStream.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
