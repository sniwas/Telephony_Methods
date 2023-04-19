package com.example.telephonymethodsdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SMSActivity extends AppCompatActivity {
    EditText phoneno,smsmsg;
    String phone,smsmesg;
    Button bsmssubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsactivity);
        phoneno=findViewById(R.id.etphone);
        smsmsg=findViewById(R.id.etsms);
        bsmssubmit=findViewById(R.id.buttonsendsms);
        bsmssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phoneno.getText().toString();
                String smsmesg=smsmsg.getText().toString();
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("smsto:"));
                sendIntent.setType("vnd.android-dir/mms-sms");
                sendIntent.putExtra("address"  , new String(phone));
                sendIntent.putExtra("sms_body"  , smsmesg);

                try {
                    startActivity(sendIntent);
                    finish();
                }
                catch (android.content.ActivityNotFoundException e1){
                    Toast.makeText(SMSActivity.this,"SMS failed,please try again later",Toast.LENGTH_SHORT).show();
                }
                startActivity(sendIntent);
                /*Using SmsManager API
                SmsManager sm=SmsManager.getDefault();
                sm.sendTextMessage(phone, null,smsmesg, null,null);
                Toast.makeText(SMSActivity.this, "Message Sent successfully!", Toast.LENGTH_LONG).show();
                startActivity(sendIntent);*/
            }
        });
    }
}