package com.yooiistudios.stevenkim.alarmmanager.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.yooiistudios.stevenkim.alarmmanager.SKAlarmManager;
import com.yooiistudios.stevenkim.sample.alarmmanager.R;

import java.util.Calendar;

public class SKAlarmManagerSampleActivity extends ActionBarActivity {

    private static final String TAG = "SKAlarmManagerSampleActivity";

    // temp alarmId
    int alarmId = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if there is an alarm first
        if (getIntent().getIntExtra(SKAlarmManager.ALARM_ID, -1) != -1) {
            // set Activity can be shown with no restriction
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

            new AlertDialog.Builder(this).setTitle(TAG).setMessage("Alarm invoke this activity").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void onAlarmButtonClicked(View v) {
        Toast.makeText(this, "Alarm set for less than 10 seconds", Toast.LENGTH_SHORT).show();

        // temp alarmId
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        SKAlarmManager.setAlarm(alarmId, calendar, this, SKAlarmManagerSampleActivity.class);
    }

    public void onCancelAlarmButtonClicked(View view) {
        Toast.makeText(this, "Alarm canceled", Toast.LENGTH_SHORT).show();

        SKAlarmManager.cancelAlarm(alarmId, this, SKAlarmManagerSampleActivity.class);
    }
}
