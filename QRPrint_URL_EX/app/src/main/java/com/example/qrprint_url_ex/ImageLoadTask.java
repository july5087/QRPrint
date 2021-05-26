package com.example.qrprint_url_ex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String urlStr;
    private ImageView imageView;

    private static HashMap<String, Bitmap> bitmapHash = new java.util.HashMap<String, Bitmap>();

    public ImageLoadTask(String urlStr, ImageView imageView) {
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;

        try{
            // 이미 같은 URL을 통해 불러온 적이 있다면 이전 Bitmap을 삭제 (캐싱)
            if(bitmapHash.containsKey(urlStr)){
                Bitmap oldBitmap = bitmapHash.remove(urlStr);

                if(oldBitmap != null){
                    oldBitmap.recycle();
                    oldBitmap = null;
                }
            }
            // URL과 서버 연결한 뒤 InputStream을 BitmapFactory로 decode하여 bitmap 변수에 저장
            URL url = new URL(urlStr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            // 새 bitmap을 HashMap에 저장
            bitmapHash.put(urlStr, bitmap);

        } catch (MalformedURLException e) {
            e.printStackTrace();
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
        // ImageView에 bitmap 이미지 넣어준 뒤 최신화
        imageView.setImageBitmap(bitmap);
        imageView.invalidate();
    }
}
