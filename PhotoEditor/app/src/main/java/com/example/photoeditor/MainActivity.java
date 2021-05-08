package com.example.photoeditor;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper v_flipper;
    Button btnCam;
    Button btnEdit;
    Button hregister;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.photoEditor);

        String text = "PHOTO EDITOR";

        SpannableString ss = new SpannableString(text);

        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.parseColor("#544BFF"));
        ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.parseColor("#F354FF"));

        ss.setSpan(fcsRed, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsGreen, 6, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);

        // Create Slider

        int[] images = {R.drawable.pager_01, R.drawable.pager_02, R.drawable.pager_03};

        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImages(image);
        }

        // Open Camera
        btnCam = findViewById(R.id.camera);
        btnCam.setOnClickListener(view -> {
            try {
                Intent intent= new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        // Open Gallery
        btnEdit = findViewById(R.id.editor);
        btnEdit.setOnClickListener(v -> openGallery());

        //Link to Website
        hregister = findViewById(R.id.more);
        hregister.setOnClickListener(v -> {
            String url = "https://play.google.com/store/search?q=CHUMOB";

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        // Animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
}