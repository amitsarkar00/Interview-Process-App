package com.example.app05012022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app05012022.databinding.ActivityDigitalSignatureBinding;
import com.example.app05012022.databinding.ActivityMainBinding;
import com.example.app05012022.databinding.ActivityTermsBinding;
import com.example.app05012022.databinding.FragmentInterviewBinding;

public class TermsActivity extends AppCompatActivity {
    private ActivityTermsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getVolley();
    }
    void getVolley() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.google.com";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        binding.textView5.setText("Response is: "+ response.substring(0,5000));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.textView5.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
}