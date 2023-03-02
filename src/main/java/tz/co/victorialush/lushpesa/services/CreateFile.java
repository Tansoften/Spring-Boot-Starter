package tz.co.victorialush.lushpesa.services;

import tz.co.victorialush.lushpesa.models.FileRecords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class CreateFile {
    private String fileName;
    public String getFileName(){return fileName;}

    public String generateAttachment(FileRecords records) throws IOException {
        String randomId = UUID.randomUUID().toString();
        fileName = randomId+".csv";
        String path = "attachments/"+fileName;
        File file = new File(path);
        FileWriter writer = new FileWriter(file);

        //Create columns headers
        for(String cols:records.getHeads()){
            writer.write(cols+ ",");
        }

        writer.write("\n");

        //Create records(rows)
        for(Object []rows:records.getRows()){
            //Populate data into row columns
            for(Object col:rows){
                writer.write(col+",");
            }
            writer.write("\n");
        }

        writer.close();
        return path;
    }
}
