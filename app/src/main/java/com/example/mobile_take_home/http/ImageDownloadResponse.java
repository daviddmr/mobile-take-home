package com.example.mobile_take_home.http;

import android.graphics.Bitmap;

public interface ImageDownloadResponse {

    void onFinish(int position, Bitmap bitmap);

}
