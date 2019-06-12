/**
 * Fichier : LeaWorkflow.java
 * Package : com.sfr.lea.batch
 *
 * Année   : 2009
 * Projet  : LeaBatch
 * Auteur  : Jean-Francois Houle (ldcg28882)
 */
package org.kitfox.poi;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Jean-Francois Houle (ldcg28882)
 */
public class Sx {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Sx sx = new Sx();
        sx.process();
    }

    private void process() throws Exception {

        Workbook wb = new XSSFWorkbook();

        Sheet s = wb.createSheet("Sheet1");

        try {
            for (int r = 0; r < 90000; ++r) {
                Row row = s.createRow(r);
                for (int c = 0; c < 10; ++c) {
                    row.createCell(c).setCellValue("Mouhaha" + r + c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        wb.write(new FileOutputStream("C:\\Temp\\File.xlsx"));
    }
}
