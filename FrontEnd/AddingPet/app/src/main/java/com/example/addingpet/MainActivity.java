package com.example.addingpet;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import android.widget.Button;
import android.view.View;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQ;
    private EditText editPetName, editUser, editType;
    private Button button;
    protected String userID = "";
    protected String userPetName = "";
    protected String petType = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
       final EditText owner = (EditText) findViewById(R.id.editUser);
       final EditText name = (EditText) findViewById(R.id.editPetName);
       final EditText type = (EditText) findViewById(R.id.editType);

        Button butt = (Button) findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            Pet pet = new Pet(
                    name.getText().toString(),
                    type.getText().toString(),
                    new User(owner.getText().toString()));


            public void onClick(View v) {

                sendNetworkRequest(pet);

            }

        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }
    private void sendNetworkRequest(Pet pet){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://coms-309-vb-5.cs.iastate.edu:8080/users/pets")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        PetClient client = retrofit.create(PetClient.class);
          Call <Pet> call = client.createAccount(pet);
          call.enqueue(new Callback<Pet>() {
              @Override
              public void onResponse(Call<Pet> call, Response<Pet> response) {
                Toast.makeText(MainActivity.this, "UserID:" + response.body().getId(), Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onFailure(Call<Pet> call, Throwable t) {
                  Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

              }
          });
    }
//    private void Submit(String info) {
//
//        final String PetsOfAUser = info;
//        String url = "http://coms-309-vb-5.cs.iastate.edu:8080/users/pets";
//
//        requestQ = Volley.newRequestQueue(getApplicationContext());
//
//        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//
//                    JSONObject obj = new JSONObject(response);
//                    obj.put("info", new Pet ());
//
//
//                    Toast.makeText(getApplicationContext(), obj.toString(), Toast.LENGTH_LONG).show();
//                } catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }) {
////            @Override
////            public String getBodyContentType(){
////                return"application/json; charset=utf-8";
////            }
////            @Override
////            public byte[] getBody() throws AuthFailureError{
////                try{ return userChosenName == null ? null : userChosenName.getBytes("utf-8");}
////                catch (UnsupportedEncodingException uee){return null;}
////            }
//        };
//
//        requestQ.add(strReq);
//    }
}
