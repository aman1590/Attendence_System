package com.example.attendencesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, signin;
    DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.user);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.respass);
        signup=(Button)findViewById(R.id.btn_signup);
        signin=(Button)findViewById(R.id.btn_login);
        db=new DB(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1= username.getText().toString();
                String pass= password.getText().toString();
                String repass=repassword.getText().toString();

                if(user1.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser=db.checkusername(user1);
                        if(checkuser==false){
                            Boolean insert=db.insertData(user1,pass);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"User already exist.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
    }
}