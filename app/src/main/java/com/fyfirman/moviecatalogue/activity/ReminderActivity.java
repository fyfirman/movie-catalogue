package com.fyfirman.moviecatalogue.activity;

import static com.loopj.android.http.AsyncHttpClient.log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.feature.reminder.ReminderReceiver;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener {

  private ReminderReceiver reminderReceiver;
  Switch switchDaily, switchNewRelease;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reminder);
    setTitle("R.string.reminder");

    reminderReceiver = new ReminderReceiver();

//TODO: Remove hardcoded string
    switchDaily = findViewById(R.id.switch_daily);
    switchNewRelease = findViewById(R.id.switch_new_release);

    switchDaily.setOnClickListener(this);

    switchNewRelease.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.switch_daily:
        if (switchDaily.isChecked()) {
          Toast.makeText(this, "getString(R.string.daily_enabled)", Toast.LENGTH_SHORT).show();
          reminderReceiver.setDailyReminder(this, "12:09","Go Check Movie Review App Today!");
        } else {
          Toast.makeText(this, "getString(R.string.daily_disabled)", Toast.LENGTH_SHORT).show();
          reminderReceiver.cancelDailyReminder(this);
        }
        break;
      case R.id.switch_new_release:
        if (switchNewRelease.isChecked()) {
          String newReleaseMovie = ReminderReceiver.EXTRA_MESSAGE;
          log.d("newReleaseMovie", newReleaseMovie);
//          Toast.makeText(this, "getString(R.string.new_release_enabled)", Toast.LENGTH_SHORT).show();
          reminderReceiver.setNewReleaseReminder(this, "15:36",newReleaseMovie);
        } else {
          Toast.makeText(this, "getString(R.string.new_release_disabled)", Toast.LENGTH_SHORT).show();
          reminderReceiver.cancelNewReleaseReminder(this);
        }
        break;
    }
  }
}
