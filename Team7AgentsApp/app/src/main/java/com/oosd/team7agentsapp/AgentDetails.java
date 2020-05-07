package com.oosd.team7agentsapp;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    Agents Android App
    Evan Tucker
    May 3 2020
    OOSD Fall Semester
 */
public class AgentDetails extends AppCompatActivity {

    //On create that loads when the activity is opened. Sets up the text fields and buttons.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_details);
        final EditText etFN = findViewById(R.id.etFN);
        final EditText etPosition = findViewById(R.id.etPosition);
        final EditText etLN = findViewById(R.id.etLN);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPhone = findViewById(R.id.etPhone);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        //getting the intent from the main activity, with the full Agent Object.
        Intent intent = getIntent();
        final Agents a = (Agents) intent.getSerializableExtra("agent");
        final String id = a.getAgentId().toString();

        //sets the text fields with the appropriate data from the agent class object brought over by the intent.
        etFN.setText(a.getAgtFirstName());
        etLN.setText(a.getAgtLastName());
        etPosition.setText(a.getAgtPosition());
        etPhone.setText(a.getAgtBusPhone());
        etEmail.setText(a.getAgtEmail());


        //button update listener that gets called when the update button is clicked
        //this grabs the text from the text fields, then uses the rest service made by Bilal
        //to update the database.
        btnUpdate.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JSONObject object = new JSONObject();
                try {
                    object.put("agentId", id);
                    object.put("agencyId", String.valueOf(1));
                    object.put("agtFirstName", etFN.getText().toString());
                    object.put("agtLastName", etLN.getText().toString());
                    object.put("agtBusPhone", etPhone.getText().toString());
                    object.put("agtEmail", etEmail.getText().toString());
                    object.put("agtPosition", etPosition.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String postAgent = "http://192.168.1.5:8080/TestService2-1/rs/agent/postagent";
                Log.e("Object", object.toString());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postAgent, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Respone: " + response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                    }
                });
                requestQueue.add(jsonObjectRequest);
                onBackPressed();
            }
        });
        //on click listener for the delete button, that gets called when the delete button
        //is pressed. this will simply delete the agent from the database from the restservice.
        //it only needs the agent ID. BUG: only works on agents with no customers - updates to
        //constraints needed to make this work fully.
        btnDelete.setOnClickListener(new AdapterView.OnClickListener() {
            String deleteURL = "http://192.168.1.5:8080/TestService2-1/rs/agent/deleteagent/" + id;
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            @Override
            public void onClick(View v) {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, deleteURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("Delete:", "Deleted");
                            }
                        }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                    }
                });
                requestQueue.add(request);
            }



















        });

    }
    public AgentDetails(){

    }
}