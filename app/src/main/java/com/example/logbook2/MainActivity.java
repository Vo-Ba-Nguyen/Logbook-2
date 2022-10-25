package com.example.logbook2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText addLink_txt;
    Button back_button, next_button, add_link_button;
    ArrayList<String> arrayList;
    int index;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        back_button = findViewById(R.id.back_button);
        next_button = findViewById(R.id.next_button);
        add_link_button = findViewById(R.id.add_link_button);
        addLink_txt = findViewById(R.id.addLink_txt);

        add_link_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(IsValidUrl(addLink_txt.getText().toString().trim())){
                    arrayList.add(addLink_txt.getText().toString().trim());
                    Glide.with(getApplicationContext())
                            .load(loadLastImg())
                    .placeholder(R.drawable.ic_baseline_image_24).into(imageView);
                    Toast.makeText(MainActivity.this, "Add Successfully!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "URL is not Valid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        arrayList = new ArrayList<>();

        arrayList.add(0,"https://images.vexels.com/media/users/3/263340/isolated/preview/92d75abef1c7523630339a2793eba5eb-pizza-color-stroke-slice.png");
        arrayList.add(1,"https://img.freepik.com/premium-psd/fresh-vegetable-pepperoni-mushroom-pizza-transparent-background_670625-101.jpg?w=2000");
        arrayList.add(2,"https://toppng.com/uploads/preview/pizza-11527809195frqp1qz4zd.png");

        Glide.with(getApplicationContext())
                .load(loadLastImg())
                .placeholder(R.drawable.ic_baseline_image_24).into(imageView);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getApplicationContext())
                        .load(back_button())
                        .placeholder(R.drawable.ic_baseline_image_24).into(imageView);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getApplicationContext())
                        .load(next_button())
                        .placeholder(R.drawable.ic_baseline_image_24).into(imageView);
            }
        });
    }

    String loadLastImg(){
        url = arrayList.get(arrayList.size() - 1 );
        return url;
    }

    String next_button(){
        index = arrayList.indexOf(url);
        if(index == (arrayList.size()-1)){
            url = arrayList.get(0);
        } else {
            index++;
            url = arrayList.get(index);
        }
        return url;
    }

    String back_button(){
        index = arrayList.indexOf(url);
        if(index == 0){
            url = arrayList.get(arrayList.size()-1);
        } else {
            index--;
            url = arrayList.get(index);
        }
        return url;
    }
    public static boolean IsValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
        } catch (MalformedURLException ignored) {
        }
        return false;
    }

}