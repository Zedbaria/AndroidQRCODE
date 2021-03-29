package com.example.notreappmspr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inscription extends AppCompatActivity {

    private static Button btn_valid;
    private static EditText pseudo,mail,pass,passC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        btn_valid = (Button)findViewById(R.id.btn_valid);
        pseudo = (EditText)findViewById(R.id.pseudo);
        mail = (EditText)findViewById(R.id.mail);
        pass = (EditText)findViewById(R.id.pass);
        passC = (EditText)findViewById(R.id.passcheck);
        btn_valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        if(Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    public void register(){
        boolean Tpseudo = false;
        boolean Tmail = false;
        boolean Tmail2=false;
        boolean Tpass = false;

        try {
            Fonction fonc = new Fonction();

            /* VERIF PSEUDO EXISTANT */
            Statement stPseudo = Fonction.connexionSQLBDD();
            String sql = "SELECT * FROM utilisateurs WHERE pseudo= '"+pseudo.getText().toString()+"'";
            final ResultSet resultPseudo = stPseudo.executeQuery(sql);
            resultPseudo.next();

            if(!(resultPseudo.getRow()==0))
            {
                Tpseudo=true;
            }

            /* VERIF MAIL EXISTANT */

            Statement stMail = Fonction.connexionSQLBDD();
            String sqlMail="SELECT * FROM utilisateurs WHERE mail = '"+mail.getText().toString()+"'";
            final ResultSet resultMail = stMail.executeQuery(sqlMail);
            resultMail.next();
            if(!(resultMail.getRow()==0))
            {
                Tmail=true;
            }
            String email= mail.getEditableText().toString().trim();
            if(!email.matches("^[_a-z0-9]+(\\.[a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$"))
            {
                Tmail2=true;
            }

            /* VERIF PASS */
            if(!(pass.getText().toString().equals(passC.getText().toString())))
            {
                Tpass=true;
            }

            /* Hachage mdp */

            byte[] password= pass.getText().toString().getBytes();
            BigInteger md5Data = new BigInteger(1, md5.encryptMD5(password));
            String hashed = md5Data.toString(16);

            /* ALL CHECK */
            if((!Tpseudo) && (!Tmail) && (!Tmail2) && (!Tpass) )
            {
                Statement stInsert = fonc.connexionSQLBDD();
                stInsert.executeUpdate("INSERT INTO utilisateurs (pseudo,pass,email) VALUES ('"+pseudo.getText().toString()+"','"+hashed+"','"+mail.getText().toString()+"'");
                finish();
            }

            if (Tpseudo)
            {
                Toast.makeText(Inscription.this, "Le pseudo éxiste déjà", Toast.LENGTH_SHORT).show();
            }
            if (Tmail)
            {
                Toast.makeText(Inscription.this, "Le mail éxiste déjà", Toast.LENGTH_SHORT).show();
            }
            if (Tmail2)
            {
                Toast.makeText(Inscription.this, "Le mail est invalide", Toast.LENGTH_SHORT).show();
            }

            if(Tpass)
            {
                Toast.makeText(Inscription.this, "Les mot de passes sont différents", Toast.LENGTH_SHORT).show();
            }



        }catch (Exception e )
        {
            Toast.makeText(Inscription.this,"L'inscription a échouée",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}