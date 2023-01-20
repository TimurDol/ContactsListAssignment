package com.tm221260.contactslistassignment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.tm221260.contactslistassignment.R;

public class ContactDetailActivity extends AppCompatActivity {

    private String contactName, contactNumber;
    private TextView contactTV, nameTV;
    private ImageView contactIV, callIV, messageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        contactName = getIntent().getStringExtra("name");
        contactNumber = getIntent().getStringExtra("contact");
        nameTV = findViewById(R.id.idTVName);
        contactIV = findViewById(R.id.idIVContact);
        contactTV = findViewById(R.id.idTVPhone);
        nameTV.setText(contactName);
        contactTV.setText(contactNumber);
        callIV = findViewById(R.id.idIVCall);
        messageIV = findViewById(R.id.idIVMessage);
        callIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(contactNumber);
            }
        });

        messageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(contactNumber);
            }
        });
    }

    private void sendMessage(String contactNumber) {
        //calling an intent to send sms
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactNumber));
        intent.putExtra("sms_body", "Enter your messaage");
        startActivity(intent);
    }

    private void makeCall(String contactNumber) {
        //calling an intent to make a call
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + contactNumber));
        if (ActivityCompat.checkSelfPermission(ContactDetailActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        startActivity(callIntent);
    }

}