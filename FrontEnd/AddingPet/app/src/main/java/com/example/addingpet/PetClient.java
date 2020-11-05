package com.example.addingpet;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface PetClient {
    @POST("pet")
    Call<Pet> createAccount (@Body Pet pet);
}
