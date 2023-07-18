package it.jobinformatica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDati {
    private String idVolo;
    private LocalDate giornoSett;
    private String cittaPart;
    private String tipoAereo;
    private long numPasseggeri;

    public Object[] ritornaRiga(){
        return new Object[]{idVolo, giornoSett.toString(), cittaPart, tipoAereo, numPasseggeri};
    }

    public String[] ritornaIntestazioniColonne() {
        return new String[]{"Id volo","Giorno settimana","Citta di partenza","Tipo aereo","Numero passeggeri"};
    }
}
