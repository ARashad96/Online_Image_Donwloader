package com.arashad96.andoriddeveloperadvancedkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Online_Image_Donwloader extends AppCompatActivity {
    Button github;
    Button info;
    Button download;
    ImageView image;
    EditText website;

    public class Imagedownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                //download whole data in one go
                InputStream inputStream = urlConnection.getInputStream();
                //convert data in our inputstream to image
                Bitmap mybBitmap = BitmapFactory.decodeStream(inputStream);

                return mybBitmap;
            }catch (Exception e){
                Log.d("check1","Failed to download image");
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_online__image__donwloader);

        website = findViewById(R.id.website);
        image = findViewById(R.id.image);
        download = findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Imagedownloader task = new Imagedownloader();
                Bitmap myImage;
                String name = "https://www.gqmiddleeast.com/sites/default/files/gqme/styles/766_431_landscape/public/images/2019/04/21/John-Wick-Keanu-Reeves.jpg?itok=tJwVSoRn";
                //another image example
                //String name = "https://uncrate.com/assets_c/2018/03/mclaren-570-black-1-thumb-960xauto-82290.jpg";
                if (website.getText().toString().equals("")){

                }else {
                    name = website.getText().toString();
                }
                try {
                    myImage = task.execute(name).get();
                    image.setImageBitmap(myImage);
                }catch (Exception e){
                    Toast.makeText(Online_Image_Donwloader.this, "Failed", Toast.LENGTH_SHORT).show();
                }

                //Log.i("Inter","Button");
            }
        });

        github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ARashad96/Online_Image_Donwloader"));
                startActivity(intent);
            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(Online_Image_Donwloader.this)
                        .setIcon(R.drawable.profile)
                        .setTitle("App info")
                        .setMessage("This app is retrieving online Images using textview, edittext, button, imageview and linearlayout.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }
}
