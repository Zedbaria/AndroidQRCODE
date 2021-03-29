package com.example.notreappmspr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connexion extends AppCompatActivity {

    public static EditText pseudo, pass;
    private static Button btn_inscri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        pseudo = (EditText) findViewById(R.id.pseudo);
        pass = (EditText) findViewById(R.id.pass);
        Button btn_send = (Button) findViewById(R.id.btn_send);
        btn_inscri = (Button) findViewById(R.id.btn_inscri);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextActivity();
                //connexion();
            }
        });
        btn_inscri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePage();
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    private void connexion()
    {
        try {
            Statement st = Fonction.connexionSQLBDD();
            String SQL = "SELECT pass FROM utilisateurs WHERE pseudo='"+pseudo.getText().toString()+"'";
            final ResultSet rs = st.executeQuery(SQL);
            rs.next();
            if(rs.getString(1).equals(pass.getText().toString()))
            {
                NextActivity();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void NextActivity()
    {
        Intent intent = new Intent(Connexion.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void ChangePage()
    {
        Intent intent = new Intent(Connexion.this, Inscription.class);
        startActivity(intent);
        finish();
    }
}