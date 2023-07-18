package it.jobinformatica;

import it.jobinformatica.model.RecordDati;
import it.jobinformatica.repository.LettoreDatabase;
import it.jobinformatica.utils.InfoOutput;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class Esecuzione {
    public void start(String citta) {
        ArrayList<RecordDati> records = null;
        Object[][] dati = null;

        //Lettura dati da DB
        //TODO log.info("Inizio lettura db");
        //records = voloRepository.voliDa(citta);
        records = LettoreDatabase.voliDa(citta);

        //Trasformazione dati per scrittura in excel
        //TODO log.info("Trasformazione dati");
        dati = new Object[records.size() + 2][RecordDati.class.getDeclaredFields().length];
        dati[0] = new RecordDati().ritornaIntestazioniColonne();

        int cont = 2;
        for(RecordDati r : records){
            dati[cont++] = r.ritornaRiga();
        }

        //Scrittura su excel
        //TODO log.info("Scrittura excel");
        InfoOutput.scriviExcel(dati,"nome_file","Nome foglio");
    }
}
