package com.example.slispapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    final String TAG = "SignUp";
    EditText etUsername, etPassword, etConfirmPassword,etphonenumber;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("SLISP: Sign Up");

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password2);
        etConfirmPassword = findViewById(R.id.confirmpassword);
        etphonenumber = findViewById(R.id.phone);
        register = findViewById(R.id.signup2);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(!emptyValidate(etUsername, etPassword, etConfirmPassword, etphonenumber)){
            if(passwordValidate(etPassword, etConfirmPassword)){

                if(etphonenumber.getText().length()==10) {

                    if (etPassword.getText().length()>=3) {

                        if (etUsername.getText().length() < 3) {

                            Toast.makeText(getApplicationContext(),
                                    "The Username length Should at least be 3 Characters",
                                    Toast.LENGTH_LONG).show();

                        } else {
                            String username = etUsername.getText().toString();
                            String password = etPassword.getText().toString();
                            String phonenumber = etphonenumber.getText().toString();
                           // String password2 = MD5.encrypt(etPassword.getText().toString());

                            HashMap<String, String> postData = new HashMap<>();
                            postData.put( "Username",username );
                            postData.put( "Password",password );
                            postData.put( "Phone_number",phonenumber );

                            PostResponseAsyncTask task1 = new PostResponseAsyncTask( this,postData,new AsyncResponse() {
                                @Override
                                public void processFinish(String s) {
                                    Log.d( TAG,s );
                                    if (s.contains( "ErrorInsert" )) {
                                        Toast.makeText( SignUp.this,"Something went wrong. Data was not inserted.",Toast.LENGTH_LONG ).show();
                                    } else {
                                        Intent in = new Intent( getApplicationContext(),Login.class );
                                        startActivity( in );
                                    }
                                }
                            } );
                            task1.execute( "http://"+Data.serverIP+"/SlispApp/registration.php" );

                        }
                    }
                    else {
                        Toast.makeText( getApplicationContext(),"The Password length should be at least three characters",Toast.LENGTH_LONG ).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "The Phone Numeber Should Exactly be 10 digits",
                            Toast.LENGTH_LONG).show();
                }

            }
            else{  // not equals
                Toast.makeText(getApplicationContext(),
                        "Make sure your password is the same to confirm password",
                        Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(getApplicationContext(), "Fill out all the fields",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean emptyValidate(EditText etUsername, EditText etphonenumber,
                                  EditText etPassword,
                                  EditText etConfirmPassword){
        String phonenumber = etphonenumber.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();
        return (phonenumber.isEmpty() && password.isEmpty() &&  username.isEmpty() && confirm.isEmpty());
    }

    private boolean passwordValidate(EditText etPassword,
                                     EditText etConfirmPassword){
        String password = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();
        return (password.equals(confirm));
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

        switch (item.getItemId()){
            case R.id.signup:
                Intent intent=new Intent(SignUp.this,SignUp.class);
                startActivity(intent);
                break;

            case R.id.login2:
                Intent intent1=new Intent(SignUp.this,Login.class);
                startActivity(intent1);
                break;

            case R.id.about4:
                Intent intent2=new Intent(SignUp.this,About.class);
                startActivity(intent2);
                break;
            case R.id.searchsign:
                Intent intent3 = new Intent(SignUp.this,SearchSign3.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}