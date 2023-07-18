package it.jobinformatica.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class InfoOutput {
    public static void scriviExcel(Object[][] dati, String nomeFile, String nomeFoglio) {
        XSSFWorkbook fileExcel = new XSSFWorkbook();
        XSSFSheet foglioExcel = fileExcel.createSheet(nomeFoglio);

        int nRiga = -1;

        for (Object[] rigaDati : dati) {
            Row riga = foglioExcel.createRow(++nRiga);

            int nColonna = -1;

            for (Object dato : rigaDati) {
                Cell cella = riga.createCell(++nColonna);
                if (dato instanceof String) {
                    cella.setCellValue((String) dato);
                } else if (dato instanceof Long) {
                    cella.setCellValue((Long) dato);
                }
            }

        }

        try (FileOutputStream outputStream = new FileOutputStream(nomeFile + ".xlsx")) {
            fileExcel.write(outputStream);
            //TODO log.info("File xls prodotto");
        } catch (Exception ex) {
            //TODO log.error("Errore scrittura file");
            //TODO log.error(ex.getMessage());
        }
    }
}
