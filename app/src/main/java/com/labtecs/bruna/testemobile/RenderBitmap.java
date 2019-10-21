package com.labtecs.bruna.testemobile;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RenderBitmap {
    private Bitmap bitmap;

    public RenderBitmap(Resources resources, int imageId) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = BitmapFactory.decodeResource(resources, imageId, options);
    }

    public Bitmap getBitmap() {
        return bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
    }
}
