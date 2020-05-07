package com.oosd.team7agentsapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*
    Agents Android App
    Evan Tucker
    May 3 2020
    OOSD Fall Semester
 */
public class MainActivity extends AppCompatActivity {

    private Button btnRefresh;
    private ListView lvAgents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAgents = findViewById(R.id.lvAgents);
        lvAgents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(getApplicationContext(), AgentDetails.class);
                appInfo.putExtra("agent",  (Agents)lvAgents.getAdapter().getItem(position));
                startActivity(appInfo);
            }
        });

        btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        jsonParse();
    }

    @Override
    protected void onResume() {
        super.onResume();
        jsonParse();
    }

    public void jsonParse(){
        String GETALLAGENTS = "http://192.168.1.5:8080/TestService2-1/rs/agent/getallagents";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final ArrayAdapter<Agents> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1);

            JsonArrayRequest request = new JsonArrayRequest(GETALLAGENTS, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {

                        for (int i = 0; i < response.length(); i++){
                            JSONObject agent = response.getJSONObject(i);

                            Agents a = new Agents();

                            a.setAgentId(agent.getInt("agentId"));
                            a.setAgtFirstName(agent.getString("agtFirstName"));
                            //a.setAgtMiddleInitial(agent.getString("agtMiddleInitial"));
                            a.setAgtLastName(agent.getString("agtLastName"));
                            a.setAgtBusPhone(agent.getString("agtBusPhone"));
                            a.setAgtEmail(agent.getString("agtEmail"));
                            a.setAgtPosition(agent.getString("agtPosition"));
                            a.setAgencyId(agent.getInt("agencyId"));

                            adapter.add(a);
                        }
                        lvAgents.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
    private void GoToDetails(long id){
        Intent appInfo = new Intent(getApplicationContext(), AgentDetails.class);
        appInfo.putExtra("agent_details", id);
        startActivity(appInfo);
    }
}