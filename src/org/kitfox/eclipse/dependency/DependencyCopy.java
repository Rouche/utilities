package org.kitfox.eclipse.dependency;

import java.io.*;
import java.nio.channels.FileChannel;

public class DependencyCopy {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Usage : DependencyCopy [fichier .classpath] [destination]");
            return;
        }

        File file = new File(args[0]);
        File outputDir = new File(args[1]);

        outputDir.mkdirs();
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Utiliser en plugin Eclipse pour aller chercher les Java Buildpath Variable et
        // convertir en path pour que ce soit plus generique.
        String var = "C:/Documents and Settings/jf.larouche/.ant/cache/";

        int nb = 0;
        String line = null;
        while((line = br.readLine()) != null) {
            int start = line.indexOf("path=\"");
            int end = line.indexOf("sourcepath", start + 6);
            if(end < 0) {
                end = line.indexOf("\"/>", start + 6);
            } else {
                end = end - 2;
            }

            if(start == -1 || end == -1 || end - start < 15) {
                continue;
            }

            String fileName = line.substring(start + 6, end);

            // Remove variable
            fileName = fileName.substring(9);

            File f = new File(var + fileName);
            if(f.exists()) {
                File to = new File(outputDir, fileName.substring(fileName.lastIndexOf("/") + 1));
                try {
                    to.createNewFile();
                } catch(Exception e) {
                    System.out.println("Fichier en erreur : " + to.getAbsolutePath());
                    e.printStackTrace();
                }
                copyFile(f, to);
                ++nb;
            }
        }

        br.close();

        System.out.println("Copied " + nb + " files");
    }

    private static void copyFile(File f, File to) {
        FileChannel in = null; // canal d'entrée
        FileChannel out = null; // canal de sortie

        try {
            // Init
            in = new FileInputStream(f).getChannel();
            out = new FileOutputStream(to).getChannel();

            // Copie depuis le in vers le out
            in.transferTo(0, in.size(), out);
            System.out.println("File [" + f.getAbsolutePath() + "] copied to [" + to.getAbsolutePath() + "]");
        } catch(Exception e) {
            e.printStackTrace(); // n'importe quelle exception
        } finally { // finalement on ferme
            if(in != null) {
                try {
                    in.close();
                } catch(IOException e) {
                }
            }
            if(out != null) {
                try {
                    out.close();
                } catch(IOException e) {
                }
            }
        }
    }

}
