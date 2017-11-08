package com.example.pragya.encryption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {


    Button login;
    EditText user;
    EditText pass;
    String u = "root";
    String p = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (Button)findViewById(R.id.login);
        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(user.getText().toString().equals(u)&&pass.getText().toString().equals(p)) {
                startActivity(new Intent(Login.this, Index.class));

            }
            }
        });
    }
}
