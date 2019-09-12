package com.example.mobile_take_home.http.request;

import android.os.AsyncTask;

import com.example.mobile_take_home.http.HttpResponseInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest extends AsyncTask<String, String, String> {

    private HttpResponseInterface httpResponseInterface;

    public HttpRequest(HttpResponseInterface httpResponseInterface) {
        this.httpResponseInterface = httpResponseInterface;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder buffer;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            buffer = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response != null) {
            httpResponseInterface.onSuccess(response);
        } else {
            httpResponseInterface.onError();
        }
    }
}
