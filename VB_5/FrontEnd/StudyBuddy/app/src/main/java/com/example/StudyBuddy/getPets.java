package com.example.StudyBuddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudyBuddy.LocalData.LocalDataStorage;
import com.example.StudyBuddy.LocalData.User;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Implements the core feature of exchanging tickets for pets.
 * Also handles the rarity of the pets.
 */
public class getPets extends AppCompatActivity {
    private Button getPet;
    private String rarity;
    private String URL;

    private LocalDataStorage data;
    private User user;

    private AlertDialog notEnoughTickets;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pets);

        final Handler h = new Handler();
        final int delay = 3000; //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                updateTicketCount();
                h.postDelayed(this, delay);
            }
        }, delay);

        data = new LocalDataStorage(getApplicationContext());
        user = data.getUserData();
        URL = "http://coms-309-vb-5.cs.iastate.edu:8080/users/pets/";

        getPet = findViewById(R.id.useTickGP);
        getPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRand();
                URL = URL.concat(rarity);
                if(user.getTickets() > 0){
                    getPet();
                    user.removeTicket();
                    deleteTicket();
                }

                else{displayTicketError();}
            }
        });
    }

    /**
     * provides a randomised string for use in POST request in getPet.
     */
    public void getRand(){
        Random rand = new Random();
        int percentage = rand.nextInt(100);

        if(percentage < 80){rarity = "Common"; }
        else if (percentage < 99){rarity = "Rare";}
        else{rarity = "Ultra Rare"; }

    }

    /**
     * Makes a POST request to assign a new pet to the User's list of available pets.
     */
    public void getPet(){
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                nameAlert(response, rarity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { error.printStackTrace(); }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() {
                Gson gson = new Gson();
                String json = gson.toJson(user);

                try{ return (json).getBytes("utf-8"); }
                catch (UnsupportedEncodingException e) { return null; }
            }
        };

        rq.add(request);
    }

    /**
     * Makes a DELETE request to remove one ticket from the user's available tickets.
     */
    public void deleteTicket(){
        String delURL = "http://coms-309-vb-5.cs.iastate.edu:8080/users/".concat(user.getId()).concat("/tickets");
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.DELETE, delURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });

        rq.add(request);
    }

    /**
     * Tells the user that they do not have enough tickets to obtain a pet through a pop-up dialog
     * box.
     * Available options are "Sorry, you don't have enough tickets." and "OK, I UNDERSTAND"
     */
    public void displayTicketError(){
        builder = new AlertDialog.Builder(getPets.this);
        builder.setTitle("Sorry, you don't have enough tickets.");

        builder.setPositiveButton("OK, I UNDERSTAND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        notEnoughTickets = builder.create();
        notEnoughTickets.show();
    }

    public void updateTicketCount()
    {
        LocalDataStorage data = new LocalDataStorage(this);
        User user = data.getUserData();

        String getURL = "http://coms-309-vb-5.cs.iastate.edu:8080/users/".concat(user.getId()).concat("/tickets");
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

        final int[] tickets = new int[1];
        StringRequest request = new StringRequest(Request.Method.GET, getURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                String tdText = "Tickets: ";
                TextView ticketDisplay = findViewById(R.id.gpTicketsDisplay);
                ticketDisplay.setText(tdText + Integer.parseInt(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });

        rq.add(request);
    }

    public void nameAlert(final String response, String rarity)
    {
        final String[] responses = response.split("_");


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Congratulations");
        alert.setMessage("You have received a " + rarity.toLowerCase() + " " + responses[0] + "! Choose a new for your new pet.");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                renamePet(input.getText().toString(), responses[1]);
            }
        });

        alert.show();
    }

    public void renamePet(String name, String id)
    {
        String getURL = "http://coms-309-vb-5.cs.iastate.edu:8080/users/pets/rename/" + id + "/" + name;
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

        final int[] tickets = new int[1];
        StringRequest request = new StringRequest(Request.Method.POST, getURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });

        rq.add(request);
        finish();
    }
}
