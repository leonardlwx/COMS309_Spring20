package com.example.StudyBuddy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudyBuddy.LocalData.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class addFriendsDialog extends AppCompatDialogFragment {
    private EditText editTextFriendUsername;
    private addFriendListener listener;
    private String friendName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder addF = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_friends_dialog_box, null);

        addF.setView(view)
                .setTitle("Add Friend")
                .setNegativeButton("I have no friends", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setPositiveButton("Add!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        friendName = editTextFriendUsername.getText().toString();
                        listener.applyUsername(friendName);

                    }
                });

        editTextFriendUsername = view.findViewById(R.id.edit_newFriend);
        return addF.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{ listener = (addFriendListener) context; }
        catch (ClassCastException e){ throw new ClassCastException(context.toString() + "must Implement addFriendListener"); }
    }

    public interface addFriendListener{ void applyUsername(String name);}

}
