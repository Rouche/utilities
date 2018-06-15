/**
 * Fichier : LeaWorkflow.java
 * Package : com.sfr.lea.batch
 *
 * Année   : 2009
 * Projet  : LeaBatch
 * Auteur  : Jean-Francois Houle (ldcg28882)
 */
package org.kitfox.poi;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author Jean-Francois Houle (ldcg28882)
 */
public class JExcelTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JExcelTest poiTest = new JExcelTest();
        poiTest.process();
    }

    private void process() {
        WritableWorkbook wb = null;
        try {
            wb = Workbook.createWorkbook(new File("c:\\Temp\\myfile.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            WritableSheet s = wb.createSheet("Sheet 1", 0);
            for (int r = 0; r < 9000; ++r) {
                for (int c = 0; c < 10; ++c) {
                    Label label = new Label(c, r, "Val");
                    s.addCell(label);
                }
            }
            wb.write();
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
