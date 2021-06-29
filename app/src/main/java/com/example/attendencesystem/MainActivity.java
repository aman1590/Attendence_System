package com.example.attendencesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        username=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.password1);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnsignup=(Button)findViewById(R.id.btn_signup1);
        db=new DB(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user2=username.getText().toString();
                String pass=password.getText().toString();

                if(user2.equals("")||pass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=db.checkusernamepassword(user2,pass);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}