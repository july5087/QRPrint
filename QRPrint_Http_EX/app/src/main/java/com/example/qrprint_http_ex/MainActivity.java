package com.example.qrprint_http_ex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ImageView ivImage;
    String imageUrl = "https://itwiki.kr/images/b/b5/IT%EC%9C%84%ED%82%A4_QR_%EC%BD%94%EB%93%9C.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btLoagImage = (Button)findViewById(R.id.bt_load_image);
        ivImage = (ImageView)findViewById(R.id.iv_image);

        btLoagImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                downloadFile(imageUrl);
//                Log.i("jhlee", imageUrl);

                ImageLoadTask task = new ImageLoadTask(imageUrl, ivImage);
                task.execute();
            }
        });
    }

//    Bitmap bitmap;

/*    void downloadFile(String fileUrl) {
        URL myFileUrl = null;

        try{
            myFileUrl = new URL(fileUrl);

        } catch (MalformedURLException me){
            me.printStackTrace();
        }

        try {
            // 서버 접속
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            // 안드로이드 이미지 크기를 불러와 스트림에 저장
            int length = conn.getContentLength();
            // inputstream 객체 이용해 바이트 변환
            InputStream is = conn.getInputStream();

            // bitmap 객체에 디코딩해 저장
            bitmap = BitmapFactory.decodeStream(is);
            // ImageView에 안드로이드 이미지 띄움
            ivImage.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}