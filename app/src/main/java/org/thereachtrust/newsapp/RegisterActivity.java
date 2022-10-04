package org.thereachtrust.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    TextView btn;
    private UserDao userDao;

    private EditText inputUsername, inputPassword,inputEmail, inputConformPassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn= findViewById(R.id.member);
        inputUsername= findViewById(R.id.inputUsername);
        inputPassword= findViewById(R.id.inputPassword);
        inputEmail= findViewById(R.id.inputEmail);
        inputConformPassword= findViewById(R.id.inputConformPassword);

        btnRegister= findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });
        checkCredentials();

        userDao = Room.databaseBuilder(this, UserDataBase.class, "my-database.db").allowMainThreadQueries()
                .build().getUserDao();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    private void checkCredentials() {
        String username= inputUsername.getText().toString();
        String email= inputEmail.getText().toString();
        String password= inputPassword.getText().toString();
        String conformPassword= inputConformPassword.getText().toString();

        if(username.isEmpty()|| username.length()<1){
            showError(inputUsername, "Invalid username");
        }
        else if(email.isEmpty()|| !email.contains("@")){
            showError(inputEmail, "Email not valid");
        }
        else if(password.isEmpty() || password.length()<5){
            showError(inputPassword,"Password must be 5 characters ");
        }
        else if(conformPassword.isEmpty()|| !conformPassword.equals(password)){
            showError(inputConformPassword,"Password not match");

        }

        else{
            User user = new User(username,password,email);
            userDao.insert(user);
            Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(moveToLogin);

            //startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }
    }

    private void showError(EditText input, String e) {
        input.setError(e);
        input.requestFocus();
    }
}