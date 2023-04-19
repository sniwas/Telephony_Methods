# Telephony_Methods
Github Link : https://github.com/sniwaserode/Telephony_Methods
AIM:
	To develop an android application that uses telephony methods.

CODE:
Activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Telephony Methods"
        android:textSize="34sp"
        app:layout_constraintBottom_toTopOf="@+id/textView2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.5" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="300dp"
        android:layout_height="450dp"
        android:text="Phone Details"
        android:textSize="20sp"
        app:layout_constraintBottom_toTopOf="@+id/button"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        app:layout_constraintVertical_bias="0.5" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Next"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView2"
        app:layout_constraintVertical_bias="0.5" />

</androidx.constraintlayout.widget.ConstraintLayout>

#MainActivity.java

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
 

EmailActivity.xml:
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".EmailActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="Sending Mail\nRedirecting to Gmail"
        android:textSize="34sp"
        app:layout_constraintBottom_toTopOf="@+id/et_to"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.5" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Compose EMAIL"
        android:textSize="20sp"
        app:layout_constraintBottom_toTopOf="@+id/button3"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/et_mes"
        app:layout_constraintVertical_bias="0.5" />

    <EditText
        android:id="@+id/et_to"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="TO :"
        android:inputType="text"
        app:layout_constraintBottom_toTopOf="@+id/et_cc"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        app:layout_constraintVertical_bias="0.5" />

    <EditText
        android:id="@+id/et_cc"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="CC : "
        android:inputType="text"
        app:layout_constraintBottom_toTopOf="@+id/et_sub"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/et_to"
        app:layout_constraintVertical_bias="0.5" />

    <EditText
        android:id="@+id/et_sub"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="SUBJECT : "
        android:inputType="text"
        app:layout_constraintBottom_toTopOf="@+id/et_mes"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/et_cc"
        app:layout_constraintVertical_bias="0.5" />

    <EditText
        android:id="@+id/et_mes"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:ems="10"
        android:hint="MESSAGE : "
        android:inputType="text"
        app:layout_constraintBottom_toTopOf 
="@+id/button2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/et_sub"
        app:layout_constraintVertical_bias="0.5" />

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Next ->"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button2"
        app:layout_constraintVertical_bias="0.5" />

</androidx.constraintlayout.widget.ConstraintLayout>

EmailActivity.java:
package com.example.telephonymethodsdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class EmailActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Button button2=findViewById(R.id.button2);
        et1=findViewById(R.id.et_cc);
        et2=findViewById(R.id.et_sub);
        et3=findViewById(R.id.et_mes);
        et4=findViewById(R.id.et_to);
        button=findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendemail();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(EmailActivity.this,SMSActivity.class);
                startActivity(inte);
            }
        });
    }
    protected void sendemail()
    {
        String TO=et4.getText().toString();
        String CC=et1.getText().toString();
        String sub=et2.getText().toString();
        String mes=et3.getText().toString();
        Intent in=new Intent(Intent.ACTION_SEND);
        in.setData(Uri.parse("mailto:"));
        in.setType("text/plain");
        in.putExtra(Intent.EXTRA_EMAIL,TO);
        in.putExtra(Intent.EXTRA_CC, CC);
        in.putExtra(Intent.EXTRA_SUBJECT,sub);
        in.putExtra(Intent.EXTRA_TEXT,mes);
        try {
            startActivity(Intent.createChooser(in,"Send mail..."));
            finish();
            //Log.i("Finished sending email","");
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(EmailActivity.this,"There is no email client installed",Toast.LENGTH_LONG).show();
        }
    }
}

SMSActivity.xml:
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#E2F6B2"
    tools:context=".SMSActivity">

    <TextView
        android:id="@+id/textView3"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="Sending SMS using SMS Manager API"
        android:textSize="24sp"
        app:layout_constraintBottom_toTopOf="@+id/etphone"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.5" />

    <EditText
        android:id="@+id/etphone"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="Phone Number :"
        android:inputType="text"
        android:textSize="20sp"
        app:layout_constraintBottom_toTopOf="@+id/etsms"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView3"
        app:layout_constraintVertical_bias="0.5" />
    <EditText
        android:id="@+id/etsms"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:ems="10"
        android:hint="Message : "
        android:inputType="text"
        android:textSize="20sp"
        app:layout_constraintBottom_toTopOf="@+id/buttonsendsms"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/etphone"
        app:layout_constraintVertical_bias="0.5" />
    <Button
        android:id="@+id/buttonsendsms"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Send SMS"
        android:textSize="20sp"
        app:layout_constraintBottom_toTopOf="@+id/buttonnext"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/etsms"
        app:layout_constraintVertical_bias="0.5" />

    <Button
        android:id="@+id/buttonnext"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Next ->"
        android:textSize="20sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/buttonsendsms"
        app:layout_constraintVertical_bias="0.5" />
</androidx.constraintlayout.widget.ConstraintLayout>
SMSActivity.java :
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
                /*
	  Using SmsManager API
                SmsManager sm=SmsManager.getDefault();
                sm.sendTextMessage(phone, null,smsmesg, null,null);
                Toast.makeText(SMSActivity.this, "Message Sent successfully!", Toast.LENGTH_LONG).show();
                startActivity(sendIntent);*/
            }
        });
    }
}

 
Output:
#1.MainActivity.java 

![image](https://user-images.githubusercontent.com/122344020/232997791-8b96c90d-36c6-4a1c-8dee-436aadf5a3d4.png)
 
#2.  EmailActivity.java

![image](https://user-images.githubusercontent.com/122344020/232997845-5b6b4708-4412-4d73-b210-72f25f2f37ec.png)

#3. On clicking compose email button

![image](https://user-images.githubusercontent.com/122344020/232997894-43be549b-12b1-467d-b215-d9948079f5ee.png)
 
#4. SMSActivity.java

![image](https://user-images.githubusercontent.com/122344020/232997925-780640e7-11fa-4fa1-b26d-0f5900a57121.png)

#5. After clicking send SMS button

![image](https://user-images.githubusercontent.com/122344020/232997963-ca1fa360-637f-442a-a183-0c44cda2c681.png)

Result:
Thus, the application to show telephony methods has been build and executed successfully. 
