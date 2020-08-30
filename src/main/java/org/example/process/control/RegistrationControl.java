package org.example.process.control;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.example.model.dao.BuchungDAO;
import org.example.model.objects.dto.Role;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Registrierung;
import org.example.process.control.exceptions.DatabaseException;
import org.example.services.db.JDBCConnection;
import org.example.services.util.Views;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationControl {

    private static RegistrationControl process = null;

    private RegistrationControl(){

    }

    public static RegistrationControl getInstance(){
        if(process == null){
            process = new RegistrationControl();
        }
        return process;
    }


    //DB muss noch geändert werden!
    public static void registerUser(String email, String passwort, String name, String rolle) throws DatabaseException {

        String sql = "insert into oemerdb.user (login, password, vorname, nachname) values (?,?,?,?)";

        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);

        //Angaben in user-Tabelle schreiben:

        try {
            statement.setString(1, email);
            statement.setString(2, passwort);
            statement.setString(3, name);
            statement.setString(4, rolle);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error: DB-Fehler!");

        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

        //Zurück zur Login-Seite navigieren:
        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

    }

}


