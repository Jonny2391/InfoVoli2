package it.jobinformatica;

import it.jobinformatica.model.RecordDati;
import it.jobinformatica.utils.LettoreDatabase;
import it.jobinformatica.utils.InfoOutput;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class Esecuzione {
    public void start(String citta) {
        ArrayList<RecordDati> records;
        Object[][] dati;

        //Lettura dati da DB
        Main.logger.info("Inizio lettura db");
        records = LettoreDatabase.voliDa(citta);

        if(records.isEmpty()){
            Main.logger.info("ResultSet vuoto - niente da scrivere su file - chiusura programma");
            return;
        }

        //Trasformazione dati per scrittura in excel
        Main.logger.info("Trasformazione dati");
        dati = new Object[records.size() + 2][RecordDati.class.getDeclaredFields().length];
        dati[0] = new RecordDati().ritornaIntestazioniColonne();

        int cont = 2;
        for(RecordDati r : records){
            dati[cont++] = r.ritornaRiga();
        }

        //Scrittura su excel
        Main.logger.info("Scrittura excel");
        InfoOutput.scriviExcel(dati,"nome_file","Nome foglio");
    }
}
