package com.example.android.github_search_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText usernam;
Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernam =(EditText)findViewById(R.id.user);
        search =(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = usernam.getText().toString();
                if(TextUtils.isEmpty(value)){
                    Toast.makeText(MainActivity.this,"please",Toast.LENGTH_SHORT).show();
                }else{
                    Intent i=new Intent(MainActivity.this,profile.class);
                    i.putExtra("username",value);
                    startActivity(i);
                }
            }
        });
    }
}