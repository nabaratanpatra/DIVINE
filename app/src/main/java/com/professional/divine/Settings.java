package com.professional.divine;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

      Button btPicker,btHistory;
      TextView textview;
      int PLACE_PICKER_REQUEST = 1;
//---------------------NOTIFICATION---------------------
      private TextView textView;
      private ProgressBar progressBar;
      private SeekBar seekBar;

   //private TextView battery;
      Switch aSwitch;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          //ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
          setContentView(R.layout.activity_settings);



          ActionBar actionBar = getSupportActionBar();
          actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.appbar_background));

          textView = (TextView) findViewById(R.id.textView);
          progressBar = (ProgressBar) findViewById(R.id.progressBar);
          seekBar = (SeekBar) findViewById(R.id.seekBar);
          seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  progressBar.setProgress(progress);
                  textView.setText("" + progress + "%");
              }
              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {
              }
              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {
              }
          });

          //---------------------CHARGING ALERT-----------------

          //----------------LOCATION PICKER---------------------

          btPicker = findViewById(R.id.bt_picker);
          btHistory = findViewById(R.id.bt_history);
          textview = findViewById(R.id.text_view);

          btPicker.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                  try {
                      startActivityForResult(builder.build(com.professional.divine.Settings.this),
                              PLACE_PICKER_REQUEST);
                  } catch (GooglePlayServicesRepairableException e) {
                      e.printStackTrace();
                  } catch (GooglePlayServicesNotAvailableException e) {
                      e.printStackTrace();
                  }

              }
          });

          btHistory.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i = new Intent(getApplicationContext(), com.professional.divine.History.class);
                  startActivity(i);
              }
          });


          //-----------------------BOTTOM NAVIGATION---------------------------

          BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

          bottomNavigationView.setSelectedItemId(R.id.settings);

          bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                  switch (menuItem.getItemId()) {
                      case R.id.dashboard:
                          startActivity(new Intent(getApplicationContext(),
                                  Dashboard.class));
                          overridePendingTransition(0, 0);
                          return true;

                      case R.id.about:
                          startActivity(new Intent(getApplicationContext(),
                                  About.class));
                          overridePendingTransition(0, 0);
                          return true;

                      case R.id.settings:
                          return true;

                      case R.id.description:
                          startActivity(new Intent(getApplicationContext(),
                                  com.professional.divine.Description.class));
                          overridePendingTransition(0, 0);
                          return true;

                      case R.id.close_friends:
                          startActivity(new Intent(getApplicationContext(),
                                  Close_Friends.class));
                          overridePendingTransition(0, 0);
                          return true;
                  }
                  return false;
              }
          });
      }

      @Override
      protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
          if(requestCode==PLACE_PICKER_REQUEST){
              if(resultCode==RESULT_OK){
                  Place place = PlacePicker.getPlace(data, this);
                  StringBuilder stringBuilder = new StringBuilder();
                  String latitude = String.valueOf(place.getLatLng().latitude);
                  String longitude = String.valueOf(place.getLatLng().longitude);
                  stringBuilder.append("LATITUDE: ");
                  stringBuilder.append(latitude);
                  stringBuilder.append("\nLONGITUDE: ");
                  stringBuilder.append(longitude);
                  textview.setText(stringBuilder.toString());




              }
          }
      }
  }

