package com.example.mobile_take_home.http.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageRequest extends AsyncTask<String, String, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;
    private final WeakReference<ProgressBar> progressBarWeakReference;

    public ImageRequest(ImageView imageView, ProgressBar progressBar) {
        imageViewReference = new WeakReference<>(imageView);
        progressBarWeakReference = new WeakReference<>(progressBar);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = imageViewReference.get();
        ProgressBar progressBar = progressBarWeakReference.get();
        imageView.setImageBitmap(bitmap);
        progressBar.setVisibility(View.GONE);
    }
}
