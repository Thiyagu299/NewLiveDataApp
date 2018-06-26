package com.example.android.livedataapp;

import android.arch.lifecycle.ViewModelProviders;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.livedataapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    UserModel userModel;
    EditText editText;

    DataRepository dataRepository;
    ActivityMainBinding activityMainBinding;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        setContentView(activityMainBinding.getRoot());
         recyclerView=findViewById(R.id.reycle_view);
         RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
         recyclerView.setLayoutManager(layoutManager);
         userModel= ViewModelProviders.of(this).get(UserModel.class);
         dataAdapter();
        dataRepository = new DataRepository(MainActivity.this, userModel);

        if (DataRepository.isOnline(MainActivity.this)) {

            dataRepository.jsonDataValues();
        }
        else{
            dataRepository.getDataDB();
        }

    }


    public void dataAdapter() {

            userModel.getUserModelLiveData().observe(this,userlist->{

               DataListAdapter dataListAdapter=new DataListAdapter(MainActivity.this,userlist);
               recyclerView.setAdapter(dataListAdapter);
            });

    }

}
