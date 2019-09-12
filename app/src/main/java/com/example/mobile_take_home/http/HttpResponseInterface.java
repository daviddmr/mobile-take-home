package com.example.mobile_take_home.http;

public interface HttpResponseInterface {
    void onSuccess(String response);
    void onError();
}
