package com.example.slispapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHandler {

    private static RequestHandler mInstance;
    private com.android.volley.RequestQueue requestQueue;
    private static Context mCtx;

    private RequestHandler(Context context){

        mCtx= context;
        requestQueue = getRequestQueue();
    }



    private RequestQueue getRequestQueue() {
        if (requestQueue==null)
            requestQueue = Volley.newRequestQueue( mCtx.getApplicationContext() );
        return requestQueue;
    }

    public static synchronized RequestHandler getInstance(Context context){
        if (mInstance==null){
            mInstance= new RequestHandler( context );
        }
        return mInstance;
    }

    public <T> void addToRequestQue(Request<T> request){
        getRequestQueue().add( request );
    }


}
