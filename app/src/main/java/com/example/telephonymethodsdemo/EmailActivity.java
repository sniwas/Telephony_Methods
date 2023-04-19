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