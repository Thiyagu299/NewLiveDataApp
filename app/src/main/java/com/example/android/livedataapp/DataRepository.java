package com.example.android.livedataapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.livedataapp.DB.ReaderDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    Context context;
    private RequestQueue apiRequestQueue;
    private StringRequest apiStringRequest;
    private String Url_data = "https://reqres.in/api/users";
    UserModel userModel;
    ProgressDialog progressDialog;
    List<UserData> userDataList = new ArrayList<>();

    public DataRepository(Context context, UserModel userModel) {
        this.userModel = userModel;
        this.context = context;

    }

    public void jsonDataValues() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Url_data = "https://reqres.in/api/users?" + "page=1";

        apiStringRequest = new StringRequest(Request.Method.GET, Url_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (progressDialog.isShowing())
                        progressDialog.cancel();

                    JSONObject json_response = new JSONObject(response);
                    JSONArray jsonArray_value = json_response.getJSONArray("data");
                    userDataList.clear();
                    for (int i = 0; i < jsonArray_value.length(); i++) {
                        JSONObject json_data = jsonArray_value.getJSONObject(i);
                        UserData userData = new UserData();
                        userData.id = json_data.getString("id");
                        userData.first_name = json_data.getString("first_name");
                        userData.last_name = json_data.getString("last_name");
                        userData.avatar = json_data.getString("avatar");
                        ReaderDB.insertDatalive(context, userData);
                        userDataList.add(userData);
                        userModel.userModelMutableLiveData.postValue(userDataList);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Response :" + error, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(apiStringRequest);
    }

    public void getDataDB() {

        ReaderDB.getDataLive(context, userModel);
    }

    public static boolean isOnline(Context cont) {
        try {
            ConnectivityManager cm = (ConnectivityManager) cont
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

}
