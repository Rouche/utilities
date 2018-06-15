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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Jean-Francois Houle (ldcg28882)
 */
public class PoiTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        PoiTest poiTest = new PoiTest();
        poiTest.process();
    }

    private void process() {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet s = wb.createSheet("sheet 1");
        for(int i = 0; i < 90000; ++i) {
            createRow(s, 0, 10);
            if((i % 1000) == 0) {
                System.out.println("Rows " + i);
            }
        }
        writeFile("c:\\tmp2.xlsx", wb);
    }

	
    private XSSFRow createRow(XSSFSheet onglet, int start, int nbCells) {
        XSSFRow row = onglet.createRow(onglet.getPhysicalNumberOfRows());
        for(int i = start; i < nbCells; ++i) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue("val");
        }
        return row;
    }

    private void writeFile(String nomFichier, XSSFWorkbook workbook) {

        try {
            FileOutputStream fos = new FileOutputStream(nomFichier);

            workbook.write(fos);
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
