package com.example.attendencesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin,btnsignup;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.user);
        password=findViewById(R.id.password1);
        btnlogin=findViewById(R.id.btnlogin);
        btnsignup=findViewById(R.id.btn_signup1);
        db=new DB(this);

        btnlogin.setOnClickListener(v -> {
            String user2=username.getText().toString();
            String pass=password.getText().toString();

            if(user2.equals("")||pass.equals(""))
            {
                Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            else{
                boolean checkuserpass=db.checkusernamepassword(user2,pass);
                if(checkuserpass){
                    Toast.makeText(MainActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnsignup.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }
}