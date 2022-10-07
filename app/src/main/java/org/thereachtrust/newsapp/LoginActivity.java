package org.thereachtrust.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView btn;
    EditText inputEmail, inputPassword;
    Button btnLogin;

    UserDao db;
    UserDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn= findViewById(R.id.signUp);
        inputPassword= findViewById(R.id.inputPassword);
        inputEmail= findViewById(R.id.inputEmail);
        btnLogin= findViewById(R.id.btnlogin);

        dataBase = Room.databaseBuilder(this, UserDataBase.class, "my-database.db")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email= inputEmail.getText().toString();
                String password= inputPassword.getText().toString();

                User user = db.getUser(email, password);

                if(user != null) {
                    Intent i = new Intent(LoginActivity.this, NewsActivity.class);
                    i.putExtra("User", user);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Unregistered user, or incorrect input", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}