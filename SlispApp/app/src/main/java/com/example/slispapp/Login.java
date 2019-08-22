package com.example.slispapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kosalgeek.android.md5simply.MD5;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity  implements AsyncResponse {
    final String TAG = "Login";
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(" SLISP:  Login (Yingila)");

        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);

        tvRegister = (TextView) findViewById(R.id.register_txt);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),SignUp.class);
                startActivity(in);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(etUsername.getText().length()<=0){
                    Toast.makeText(Login.this, "Enter name\n\nWandiikamu Elinnya", Toast.LENGTH_SHORT).show();
                }

                else if( etPassword.getText().length()<=0){
                    Toast.makeText(Login.this, "Enter password\n\nWandiikamu Passwadi yo", Toast.LENGTH_SHORT).show();
                }
                else{

                HashMap postData = new HashMap();
                postData.put("btnLogin","Login");
                postData.put("mobile","android");
                postData.put("txtUsername",etUsername.getText().toString());
                postData.put("txtPassword",etPassword.getText().toString());

                PostResponseAsyncTask loginTask =
                        new PostResponseAsyncTask(Login.this,postData,
                                Login.this);
                loginTask.execute("http://"+Data.serverIP+"/SlispApp/login.php");


        }


    }

    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //select item
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.signup:
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
                break;

            case R.id.login2:
                Intent intent1 = new Intent(Login.this,Login.class);
                startActivity(intent1);
                break;

            case R.id.about4:
                Intent intent2 = new Intent(Login.this,About.class);
                startActivity(intent2);
                break;
            case R.id.searchsign:
                Intent intent3 = new Intent(Login.this,SearchSign3.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void processFinish(String output) {
        Log.d(TAG,output);
        String output2="";
        try{

            JSONObject  jsonObject =new JSONObject(output);
            output2+=jsonObject.getString("role");

           Callback.UserId= jsonObject.getString("id");

        }catch(JSONException exp){
          exp.printStackTrace();
        }

        if (output2.contains("Admin")) {

                Intent intent2 = new Intent(Login.this,Administrator.class);
                startActivity(intent2);

        }  else if (output2.contains("Local")){


            Intent intent = new Intent(Login.this,User.class);
            startActivity(intent);

        }

        else {
            Toast.makeText(getApplicationContext(),
                    "Login Failed, Password and Username Do not Match \n\nPasiwadi oba Elinnya lyo ffu.",
                    Toast.LENGTH_LONG).show();
        }
    }
}


