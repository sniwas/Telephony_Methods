package com.example.telephonymethodsdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        button=findViewById(R.id.button);
        tv=findViewById(R.id.textView2);
        String imei = tm.getDeviceId();
        tv.append("\n\nIMEI:"+imei);
        String sim_serial = tm.getSimSerialNumber();
        tv.append("\nSIM Serial Number:"+sim_serial);
        String network_country = tm.getNetworkCountryIso();
        tv.append("\nNetwork Country:"+network_country);
        String sim_country = tm.getSimCountryIso();
        tv.append("\nSIM Country:"+sim_country);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String voice_mail = tm.getVoiceMailNumber();
        tv.append("\nVoice Mail:"+voice_mail);
        String str_sim_type="";
        int sim_type=tm.getPhoneType();
        switch (sim_type)
        {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                str_sim_type = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                str_sim_type="GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                str_sim_type="NONE";
                break;
        }
        tv.append("\nSim Type:"+str_sim_type);
        String str_sim_state="";
        int sim_state=tm.getSimState();
        switch (sim_state)
        {
            case (TelephonyManager.SIM_STATE_READY):
                str_sim_state="Sim State is ready";
                break;
            case (TelephonyManager.SIM_STATE_PIN_REQUIRED):
                str_sim_state="Pin required";
                break;
            case (TelephonyManager.SIM_STATE_ABSENT):
                str_sim_state="Sim State is Missing";
                break;
            case (TelephonyManager.SIM_STATE_UNKNOWN):
                str_sim_state="Sim State is unknown";
                break;
            case (TelephonyManager.SIM_STATE_NETWORK_LOCKED):
                str_sim_state="Network is locked";
                break;
        }
        tv.append("\nSim State:"+str_sim_state);
        String net_roaming= String.valueOf(tm.isNetworkRoaming());
        tv.append("\nNetwork Roaming:"+net_roaming);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,EmailActivity.class);
                Toast.makeText(getApplicationContext(),"Sending email Activity",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });

    }
}