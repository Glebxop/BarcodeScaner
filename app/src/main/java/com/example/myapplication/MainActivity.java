package com.example.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;


import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 0;
    ImageView imageView;
    Bitmap thumbnailBitmap;

    TextView txtView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            // Фотка сделана, извлекаем картинку
            thumbnailBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(thumbnailBitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imgview);
        txtView=(TextView)findViewById(R.id.txtContent);

    }


    public void tackeaPhoto(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void scan(View view) {
        Barcoder barcoder = new Barcoder();
        String answer="";
                try {
                    answer=barcoder.scan(getApplicationContext(), thumbnailBitmap);
                }catch (RuntimeException e){
                    txtView.setText("Wrong QR");
                }

            txtView.setText(answer);

    }
}
