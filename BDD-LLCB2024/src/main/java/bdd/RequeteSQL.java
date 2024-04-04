/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bdd;


import java.sql.*;

/**
 *
 * @author Andrea
 */
public class RequeteSQL {

    protected String username;
    protected String password;
    protected Connection conn;
    PreparedStatement stm;
    ResultSet rs;

    public RequeteSQL() {

    }

    //Ajouter des utilisateurs sans aucun droit mais pas rattacher à la bdd
    public void AjouterUtilisateurs(String username, String password, Connection connection) {
        try {
            stm = connection.prepareStatement("CREATE USER '" + username + "'@'localhost' IDENTIFIED WITH mysql_native_password BY '" + password + "'");
            stm.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    //mise en place des grant des utilisateurs nouvellement créer
    public void grant(String user, String SelectItem, Connection conn) {
        if (SelectItem.equals("Select (Maintenance)")) {
            try {
                stm = conn.prepareStatement("GRANT SELECT on maintenance to `" + user + "`@localhost");
                stm.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } else if (SelectItem.equals("Select (TestFaille)")) {
            try {
                stm = conn.prepareStatement("GRANT SELECT on testfaille to `" + user + "`@localhost");
                stm.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } else if (SelectItem.equals("Select, Insert, Update")) {
            try {
                stm =conn.prepareStatement("GRANT Select, insert, update on equipement to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on equipement-vulnerabilites to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on actif-menace to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on impact to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on incident to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on maintenance to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on menace to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on prestataire to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on testfaille to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on todolist to `" + user + "`@localhost`;");
                stm.executeUpdate();
                stm =conn.prepareStatement("GRANT Select, insert, update on vulnerabilites to `" + user + "`@localhost`;");
                stm.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    //Suppresion d'un tuilisateur de la table user et table_priv de la bdd mysql
    public void SupprimerUtilisateurs(String username, Connection connection) {
        try {
            stm = connection.prepareStatement("DELETE FROM `user` WHERE `user`.`Host` = 'localhost' AND `user`.`User` = '" + username + "'");
            stm.executeUpdate();
            stm = connection.prepareStatement("DELETE FROM tables_priv WHERE tables_priv.Host = 'localhost' AND tables_priv.Db = 'bdd-llcb-2024' AND tables_priv.User = '" + username + "'");
            stm.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    //Requete SQL pour faire des tableau
    public ResultSet Tableau(String sql, Connection conn) {
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs;
    }

    public void AjoutePersonnel(String nom, String prenom, String adresse, String mail, String tel, int fonction, Connection conn) {
        try {
            stm = conn.prepareStatement("INSERT INTO `personnel`(`Nom`, `Prenom`, `Mail`, `Tel`, `Adresse`, `IDFonction`) "
                    + "VALUES (?,?,?,?,?,?)");
            stm.setString(1, nom);
            stm.setString(2, prenom);
            stm.setString(3, mail);
            stm.setString(4, tel);
            stm.setString(5, adresse);
            stm.setInt(6, fonction);
            stm.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
