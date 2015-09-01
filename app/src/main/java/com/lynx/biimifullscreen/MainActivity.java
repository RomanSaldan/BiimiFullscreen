package com.lynx.biimifullscreen;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btnShow_AM;
    Button btnHide_AM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHide_AM = (Button) findViewById(R.id.btnHide_AM);
        btnShow_AM = (Button) findViewById(R.id.btnShow_AM);
        btnHide_AM.setOnClickListener(this);
        btnShow_AM.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHide_AM:
                Process proc = null;

                String ProcID = "79"; //HONEYCOMB AND OLDER

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
                    ProcID = "42"; //ICS AND NEWER
                }

                try {
                    proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "service call activity "+ProcID+" s16 com.android.systemui" });
                } catch (Exception e) {
                    Log.d("myLogs", "Failed to kill task bar (1).");
                    e.printStackTrace();
                }
                try {
                    proc.waitFor();
                } catch (Exception e) {
                    Log.d("myLogs", "Failed to kill task bar (2).");
                    e.printStackTrace();
                }
                break;
            case R.id.btnShow_AM:
                Process proc2 = null;
                try {
                    proc2 = Runtime.getRuntime().exec(new String[] { "su", "-c", "am startservice -n com.android.systemui/.SystemUIService" });
                } catch (Exception e) {
                    Log.d("myLogs", "Failed to kill task bar (1).");
                    e.printStackTrace();
                }
                try {
                    proc2.waitFor();
                } catch (Exception e) {
                    Log.d("myLogs","Failed to kill task bar (2).");
                    e.printStackTrace();
                }
                break;
        }
    }
}
