package com.tm221260.contactslistassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.tm221260.contactslistassignment.R;

public class CreateNewContactActivity extends AppCompatActivity {
    private EditText nameEdt, phoneEdt, emailEdt;
    private Button addContactEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);
        nameEdt = findViewById(R.id.idEdtName);
        phoneEdt = findViewById(R.id.idEdtPhoneNumber);
        emailEdt = findViewById(R.id.idEdtEmail);
        addContactEdt = findViewById(R.id.idBtnAddContact);
        addContactEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                String email = emailEdt.getText().toString();

                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone)) {
                    Toast.makeText(CreateNewContactActivity.this, "Please enter the data in all fields. ", Toast.LENGTH_SHORT).show();
                } else {
                    addContact(name, email, phone);
                }
            }
        });

    }

    private void addContact(String name, String email, String phone) {
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        contactIntent
                .putExtra(ContactsContract.Intents.Insert.NAME, name)
                .putExtra(ContactsContract.Intents.Insert.PHONE, phone)
                .putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        startActivityForResult(contactIntent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CreateNewContactActivity.this, MainActivity.class);
                startActivity(i);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled Added Contact",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}