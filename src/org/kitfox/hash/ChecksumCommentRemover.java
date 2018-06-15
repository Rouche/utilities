package org.kitfox.hash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;

public class ChecksumCommentRemover {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(final String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Usage : ChecksumCommentRemover [directory] [ext1,ext2]");
            return;
        }
        File dir = new File(args[0]);
        
        FilenameFilter filter = new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                String extention = name.substring(name.lastIndexOf(".")+1);
                return args[1].indexOf(extention) != -1;
            }
        };
        for(File file : dir.listFiles(filter)) {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            
            String hash = null; 
            while((hash = br.readLine()) != null) {
                String originalFile = file.getName();
                originalFile = originalFile.substring(0, originalFile.lastIndexOf('.'));
                
                int index = hash.indexOf("*" + originalFile);
                if(index == -1) {
                    continue;
                }
                --index;
                while(hash.charAt(index) == ' ') {
                    --index;
                }
                hash = hash.substring(0, index+1);
                break;
            }
            br.close();
            
            FileWriter writer = new FileWriter(file);
            writer.write(hash);
            writer.flush();
            writer.close();
        }
    }

}
