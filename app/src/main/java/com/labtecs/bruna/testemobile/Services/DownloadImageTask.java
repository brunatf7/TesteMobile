package com.labtecs.bruna.testemobile.Services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    double scaleWidth, scaleHeight;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
        scaleHeight = scaleWidth = 0.8;
    }

    public DownloadImageTask(ImageView bmImage, double scaleWidth, double scaleHeight) {
        this.bmImage = bmImage;
        this.scaleHeight = scaleHeight;
        this.scaleWidth = scaleWidth;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        int height = (int) (result.getHeight()*scaleHeight);
        int width = (int) (result.getWidth()*scaleWidth);
        bmImage.setImageBitmap(Bitmap.createScaledBitmap(result, width, height, false));
    }
}
