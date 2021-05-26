package com.example.qrprint_url_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv_image);

        Button button = findViewById(R.id.bt_request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImageRequest();
            }
        });
    }

    public void sendImageRequest(){
        String url = "https://itwiki.kr/images/b/b5/IT%EC%9C%84%ED%82%A4_QR_%EC%BD%94%EB%93%9C.png";

        // AsyncTask에 url과 imageView를 넘겨준 뒤 실행
        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();
    }
}