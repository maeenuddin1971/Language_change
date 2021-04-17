package com.example.language_change;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StsrtClass extends AppCompatActivity {
Button b;
TextView tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadlocale();
        ActionBar ac=getSupportActionBar();
        ac.setTitle(getResources().getString(R.string.app_name));

        b=findViewById(R.id.button);
        tf=findViewById(R.id.textView);
        tf.setText(R.string.Bangla);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showchange();
            }
        });

    }


    public void showchange()
    {
        final String[] lt={"English","Bengali"};

        AlertDialog.Builder m=new AlertDialog.Builder(StsrtClass.this);
        m.setTitle("Choose Language");
        m.setSingleChoiceItems(lt, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0)
                {
                    setLocale("en");
                    recreate();
                }
                if(i==1)
                {
                    setLocale("Bn");
                    recreate();
                }
            }
        });

        AlertDialog mr=m.create();
        m.show();
    }

    public void setLocale(String lang)
    {
        Locale l=new Locale(lang);
        Locale.setDefault(l);
        Configuration c=new Configuration();
        c.locale=l;
        getBaseContext().getResources().updateConfiguration(c,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor e=getSharedPreferences("settings",MODE_PRIVATE).edit();
        e.putString("My_lang",lang);
        e.apply();
    }

    public void loadlocale()
    {
        SharedPreferences p=getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String la=p.getString("My_lang","");
        setLocale(la);
    }
}
