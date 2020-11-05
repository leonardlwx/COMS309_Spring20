package com.example.StudyBuddy.LocalData;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalDataStorage
{
    private SharedPreferences userData;
    private static final String sharedPreference = "userDetails";

    public LocalDataStorage(Context context)
    {
        userData = context.getSharedPreferences(sharedPreference, 0);
    }

    public void storeUserData(User user)
    {
        SharedPreferences.Editor editor = userData.edit();

        editor.putString("username", user.getId());
        editor.putString("salt", user.getSalt());
        editor.putInt("password", user.getPassword());
        editor.putInt("tickets", user.getTickets());
        editor.putInt("usedTickets", user.getUsedTickets());

        editor.commit();
    }

    public User getUserData()
    {
        String username = userData.getString("username","");
        String salt = userData.getString("salt","");
        int password = userData.getInt("password",0);
        int tickets = userData.getInt("tickets", 0);
        int usedTickets = userData.getInt("usedTickets", 0);

        return (new User(username, salt, password, tickets, usedTickets));
    }

    public void clearUserData()
    {
        SharedPreferences.Editor editor = userData.edit();
        editor.clear();
        editor.commit();
    }

    public void setUserLoggedInStatus(boolean status)
    {
        SharedPreferences.Editor editor = userData.edit();
        editor.putBoolean("status", status);
        editor.commit();
    }
}
