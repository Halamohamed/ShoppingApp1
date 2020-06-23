package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text_result;
    private ListView list_users;
    private ImageView image_avatar;
    private ArrayAdapter arrayAdapter;
    private Button get_btn, post_btn, put_btn, delete_btn;

    private RequestQueue requestQueue;

    private ArrayList<UserModal> userModals;
    private ArrayList<String> fullNameList;

    private final static String SERVER_URL = "https://reqres.in/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        text_result = findViewById(R.id.myTextResult);
        list_users = findViewById(R.id.myListUsers);
        //image_avatar = findViewById(R.id.myAvatarView);
        get_btn = findViewById(R.id.get_btn);
        post_btn = findViewById(R.id.post_btn);
        put_btn = findViewById(R.id.put_btn);
        delete_btn = findViewById(R.id.delete_btn);

        userModals = new ArrayList<>();
        fullNameList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        updateViews();

        get_btn.setOnClickListener(this);
        post_btn.setOnClickListener(this);
        put_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);

        list_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ApiActivity.this, "Email: " + userModals.get(position).getAvatar(), Toast.LENGTH_SHORT).show();


            }
        });
        //add registerForContextMenu(ListView); on onCreate Method

        //AdapterView.AdapterContextMenuInfo info = item.getMenuInfo();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
        //Switch here
        //code..
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get_btn:
                JsonObjectRequest myGetRequest = new JsonObjectRequest(Request.Method.GET, SERVER_URL + "users?page=2", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        text_result.setText(response.toString());
                        try {
                            JSONArray dataObject = response.getJSONArray("data");
                            for(int i=0; i < dataObject.length(); i++) {
                                JSONObject userObject = dataObject.getJSONObject(i);
                                UserModal user = new UserModal(userObject.getInt("id"),
                                        userObject.getString("email"),
                                        userObject.getString("first_name"),
                                        userObject.getString("last_name"),
                                        userObject.getString("avatar"));
                                userModals.add(user);
                                String fullName = user.getFirstName()+ " " + user.getLastName()+ " | ";
                                fullNameList.add(fullName);
                                //Picasso.get().load(userObject.getString("avatar")).into(image_avatar);
                                updateViews();
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ApiActivity.this,"Faild" + error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                requestQueue.add(myGetRequest);
                //VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myGetRequest);
                break;
            case R.id.post_btn:
                Toast.makeText(this,"POST" , Toast.LENGTH_SHORT).show();
                JSONObject postData = new JSONObject();

                try {
                    postData.put("name", "Hala");
                    postData.put("course","Android developer");
                    postData.put("id", "111");

                    JsonObjectRequest myPostRequest = new JsonObjectRequest(Request.Method.POST, SERVER_URL + "users", postData, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            text_result.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(myPostRequest);
                    //VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myPostRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.put_btn:
                Toast.makeText(this, "PUT", Toast.LENGTH_SHORT).show();
                JSONObject putData = new JSONObject();
                try {
                    putData.put("name","Khalifa");
                    putData.put("course", "Android");

                    JsonObjectRequest myPutRequest = new JsonObjectRequest(Request.Method.PUT, SERVER_URL + "users/2", putData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            text_result.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(myPutRequest);
                    //VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myPutRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.delete_btn:
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();

                JsonObjectRequest myDeleteRequest = new JsonObjectRequest(Request.Method.DELETE,  "https://jsonplaceholder.typicode.com/posts/1", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        text_result.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                requestQueue.add(myDeleteRequest);
               // VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myDeleteRequest);
                break;

        }

    }

    private void updateViews(){
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,fullNameList);
        list_users.setAdapter(arrayAdapter);
    }
}