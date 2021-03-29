package com.example.notreappmspr;

import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Fonction {

    public static String url = "jdbc:mysql://MySQL:3306/application";
    public static String user = "root";
    public static String pass = "";

    public static Statement connexionSQLBDD(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement st = conn.createStatement();
            return st;
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.println("marche pas");
            return null;
        }

    }
}
