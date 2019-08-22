package com.example.slispapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import retrofit2.http.Url;

import static android.app.Activity.RESULT_OK;

public class AddSign extends Fragment {
    private String UPLOAD_URL = "http://"+Data.serverIP+"/SlispApp/Addsign.php";
    public static final String UPLOAD_KEY = "image";

    private int PICK_IMAGE_REQUEST = 1;

   private EditText edtEnglishName, edtLugndaName,edtDescription;
    private Button btnAddSign, btnChooseSign;
     ImageView imageView;
    Bitmap bitmap;

     Uri filePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview=inflater.inflate(R.layout.activity_add_sign, container, false);

        edtEnglishName = myview.findViewById(R.id.EnglishName);
        edtLugndaName = myview.findViewById(R.id.LugandaName);
        edtDescription = myview.findViewById(R.id.Description);
        btnChooseSign = (Button) myview.findViewById(R.id.ChooseSign);
        btnAddSign = (Button) myview.findViewById(R.id.button3);

        imageView = (ImageView) myview.findViewById(R.id.imageView2);


        btnAddSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
                Toast.makeText( getActivity(),"Sign Uploaded successfully",Toast.LENGTH_LONG ).show();

            }
        });
        btnChooseSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });
        return myview;


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Sign"), PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();


            try {
                bitmap = android.provider.MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
               imageView.setImageBitmap(bitmap);
               imageView.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();


        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void uploadImage(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        edtEnglishName.setText("");
                        edtLugndaName.setText("");
                        edtDescription.setText("");
                        imageView.setVisibility(View.GONE);
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("English_Name", edtEnglishName.getText().toString().trim());
                params.put("Luganda_Name", edtLugndaName.getText().toString().trim());
                params.put("Description", edtDescription.getText().toString().trim());
                params.put("Sign", getStringImage(bitmap));
                params.put("User_id", Callback.UserId);
                return params;
            }
        };

        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest);



    }


}


