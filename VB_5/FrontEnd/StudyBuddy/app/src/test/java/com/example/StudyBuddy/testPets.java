package com.example.StudyBuddy;

import com.example.StudyBuddy.LocalData.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testPets {
    @Rule public MockitoRule mkRule = MockitoJUnit.rule();

    @Test
    public void testGetPets() throws JSONException{
        getPets test = mock (getPets.class);

        User sample = new User("name", "salt", 32, 3, 4);
        User sample2 = new User("name2", "salt2", 322, 0, 4);

        JSONObject resp = new JSONObject();

    }
}
