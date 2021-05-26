package com.example.qrprint_http_ex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String urlStr;
    private ImageView imageView;

    public ImageLoadTask(String urlStr, ImageView imageView){
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        URL url = null;

        try{
            url = new URL(urlStr);
        } catch (MalformedURLException me){
            me.printStackTrace();
        }

        try {
            // 서버 접속
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();

            // 안드로이드 이미지 크기를 불러와 스트림에 저장
            int length = conn.getContentLength();
            // inputstream 객체 이용해 바이트 변환
            InputStream is = conn.getInputStream();

            // bitmap 객체에 디코딩해 저장
            bitmap = BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        // ImageView에 안드로이드 이미지 띄움
        imageView.setImageBitmap(bitmap);
    }
}
