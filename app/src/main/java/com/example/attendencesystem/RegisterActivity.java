package com.example.attendencesystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendencesystem.Database.UserHelper;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, signin;
    private UserHelper dbHelper;
    DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.user);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.respass);
        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.btn_login);
        db = new DB(this);

        //For Username
        if (TextUtils.isEmpty(username.getText().toString()))
            username.setError("Blank field");
        else if (username.getText().toString().length() <= 3)
            username.setError("too small for username");

        //For password edittext
        if (TextUtils.isEmpty(password.getText().toString()))
            password.setError("Blank field");
        else if (password.getText().toString().length() <= 3)
            password.setError("too small for username");

        //For Reentering the password
        if (TextUtils.isEmpty(repassword.getText().toString()))
            repassword.setError("Blank field");
        else if (repassword.getText().toString().length() <= 3)
            repassword.setError("too small for username");
        else if (!repassword.getText().toString().equals(password.getText().toString()))
            repassword.setError("Password not matching");

        signup.setOnClickListener(v -> {

            String user1 = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            dbHelper = new UserHelper(this);
            new UserHelper(this).add_details(username.getText().toString(), pass);


            boolean checkuser = db.checkusername(user1);
            if (!checkuser) {
                Boolean insert = db.insertData(user1, pass);
                if (insert) {
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "User already exist.", Toast.LENGTH_SHORT).show();
            }


        });

        signin.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, MainActivity.class)));
    }
}