package com.example.slispapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class AddUser extends Fragment {

    private EditText etUsername, etPassword, etConfirmPassword,etphonenumber;
    private Button btnAddUser;

    private  String TAG;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View myview=inflater.inflate(R.layout.activity_add_user, container, false);

        etUsername = myview.findViewById(R.id.username);
        etphonenumber = myview.findViewById(R.id.phone);
        etPassword = myview.findViewById(R.id.password2);
        etConfirmPassword = myview.findViewById(R.id.confirmpassword);
        btnAddUser = myview.findViewById(R.id.adduser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!emptyValidate(etUsername, etPassword, etConfirmPassword, etphonenumber)){
                    if(passwordValidate(etPassword, etConfirmPassword)){

                        if(etphonenumber.getText().length()==10) {

                            if (etPassword.getText().length()>=3) {

                                if (etUsername.getText().length() < 3) {

                                    Toast.makeText(getActivity(),
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

                                    PostResponseAsyncTask task1 =
                                            new PostResponseAsyncTask(getContext(),postData,new AsyncResponse() {
                                                @Override
                                                public void processFinish(String s) {
                                                    Log.d(TAG,s);
                                                    if (s.contains("ErrorInsert")) {

                                                        Toast.makeText(getActivity(),
                                                                "Something went wrong. Data was not inserted.",
                                                                Toast.LENGTH_LONG).show();
                                                    } else {

                                                        Toast.makeText(getActivity(),
                                                                "User added sucessfully.",
                                                                Toast.LENGTH_LONG).show();
                                                    }

                                                }


                                            });
                                    task1.execute( "http://"+Data.serverIP+"/SlispApp/AddUser.php" );

                                }
                            }
                            else {
                                Toast.makeText( getActivity(),"The Password length should be at least three characters",Toast.LENGTH_LONG ).show();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(),
                                    "The Phone Numeber Should Exactly be 10 digits",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                    else{  // not equals
                        Toast.makeText(getActivity(),
                                "Make sure your password is the same to confirm password",
                                Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getActivity(), "Fill out all the fields",
                            Toast.LENGTH_LONG).show();
                }

            }

            private boolean emptyValidate(EditText etUsername,EditText etPassword,EditText etConfirmPassword,EditText etphonenumber) {


                String phonenumber = etphonenumber.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirm = etConfirmPassword.getText().toString();
                return (phonenumber.isEmpty() && password.isEmpty() &&  username.isEmpty() && confirm.isEmpty());
            }

            private boolean passwordValidate(android.widget.EditText etPassword,android.widget.EditText etConfirmPassword) {
                String password = etPassword.getText().toString();
                String confirm = etConfirmPassword.getText().toString();
                return (password.equals(confirm));
            }


        });
        return myview;

    };

}




