package adminActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudyBuddy.R;

public class TypeSubmission extends AppCompatActivity implements View.OnClickListener
{
    Spinner rarity;
    EditText type;
    EditText stype;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_submission);

        rarity = findViewById(R.id.spinner);
        type = findViewById(R.id.typeET);
        stype = findViewById(R.id.stypeET);
        confirm = findViewById(R.id.confirmation);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rarities));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rarity.setAdapter(adapter);

        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        String type_s = type.getText().toString();
        String stype_s = stype.getText().toString();
        String rarity_s = rarity.getSelectedItem().toString();

        String URL = "http://coms-309-vb-5.cs.iastate.edu:8080/pettypes/" + type_s + "/" + stype_s + "/" + rarity_s;
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {finish();}
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    error.printStackTrace();
                }
            });

            rq.add(request);
    }
}
