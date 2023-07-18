package it.jobinformatica.utils;

import it.jobinformatica.Main;
import it.jobinformatica.model.RecordDati;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class LettoreDatabase {
    public static ArrayList<RecordDati> voliDa(String citta) {
        ArrayList<RecordDati> dati = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:db2://db2test.jobinformatica.com:50000/TEST:currentSchema=TEST;", "accesso", "Belen01")) {
            Main.logger.info("Connessione al database creata");
            Statement st = conn.createStatement();

            String query = "select v.idvolo, v.giornosett, v.cittapart, v.tipoaereo, a.numpasseggeri " +
                    "from volo v " +
                    "join aereo a " +
                    "on a.tipoaereo = v.tipoaereo " +
                    "where v.cittapart = " +
                    "'" + citta + "' " +
                    "order by v.idvolo";

            ResultSet rs = st.executeQuery(query);
            Main.logger.info("Query eseguita");

            while (rs.next()) {
                dati.add(
                        new RecordDati(
                                rs.getString("idvolo"),
                                rs.getObject("giornosett", Timestamp.class).toLocalDateTime().toLocalDate(),
                                rs.getString("cittapart"),
                                rs.getString("tipoaereo"),
                                rs.getLong("numpasseggeri"))
                );
            }
        } catch (SQLException ex) {
            Main.logger.log(Level.SEVERE, "Errore di lettura database");
            Main.logger.log(Level.SEVERE, ex.getMessage());
        }

        return dati;
    }
}
