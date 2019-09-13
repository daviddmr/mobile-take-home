package com.example.mobile_take_home.http.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.mobile_take_home.EpisodeDetailActivity;
import com.example.mobile_take_home.ImageDownloadResponse;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageRequest extends AsyncTask<Object, String, Bitmap> {

    private WeakReference<ImageView> imageViewReference;
    private WeakReference<ProgressBar> progressBarWeakReference;
    private ImageDownloadResponse listener;
    private int position;

    public ImageRequest(ImageDownloadResponse listener, ImageView imageView, ProgressBar progressBar) {
        this.listener = listener;
        imageViewReference = new WeakReference<>(imageView);
        progressBarWeakReference = new WeakReference<>(progressBar);
    }

    @Override
    protected Bitmap doInBackground(Object... params) {
        Bitmap bitmap = null;
        try {
            URL url = new URL((String) params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);

            long key = (Long) params[1];
            EpisodeDetailActivity.setBitmapToMemoryCache(key, bitmap);

            position = (int) params[2];
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = imageViewReference.get();
        imageView.setImageBitmap(bitmap);

        ProgressBar progressBar = progressBarWeakReference.get();
        progressBar.setVisibility(View.GONE);

        listener.onFinish(position, bitmap);
    }

}
