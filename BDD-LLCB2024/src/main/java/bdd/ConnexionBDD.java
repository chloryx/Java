/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdd;

import java.sql.*;

/**
 *
 * @author Andrea
 */
public class ConnexionBDD {
    public Connection conn;
    
    
    public Connection connBdd() {
        try {
            Login login = new Login();
            System.out.println("Connexion à la base ..."); // Etape 1 : Connexion à la base
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bdd-llcb-2024", login.getUsername(), login.getPassword());
            System.out.println("Je suis connectée");
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return conn;
    }

    public void deconnBdd(Connection conn) {
        try {
            if (conn != null) {
                System.out.println("Connexion se ferme");
                conn.close();
            } else {
                System.out.println("Pas de connexion de base");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
