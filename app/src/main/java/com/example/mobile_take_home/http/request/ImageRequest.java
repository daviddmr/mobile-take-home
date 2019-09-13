package com.example.mobile_take_home.http.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.mobile_take_home.EpisodeDetailActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageRequest extends AsyncTask<String, String, Bitmap> {

    private WeakReference<ImageView> imageViewReference;
    private WeakReference<ProgressBar> progressBarWeakReference;

    public ImageRequest(ImageView imageView) {
        imageViewReference = new WeakReference<>(imageView);
    }

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

            saveBitmapToMemoryCache(params, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private void saveBitmapToMemoryCache(String[] params, Bitmap bitmap) {
        if (params.length > 1) {
            long key = Long.parseLong(params[1]);
            EpisodeDetailActivity.setBitmapToMemoryCache(key, bitmap);
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = imageViewReference.get();
        imageView.setImageBitmap(bitmap);

        if (progressBarWeakReference != null) {
            ProgressBar progressBar = progressBarWeakReference.get();
            progressBar.setVisibility(View.GONE);
        }
    }
}
