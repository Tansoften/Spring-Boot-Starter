package tz.co.victorialush.lushpesa.services;

import tz.co.victorialush.lushpesa.models.FileRecords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class CreateFile {
    private String fileName;
    public String getFileName(){return fileName;}

    private void changeFileName(String name){
        fileName = name.replace(' ', '_')
                .replace('-','_')
                .replace('/', '_');
        long timeStamp = System.currentTimeMillis();
        String []parts = fileName.split("\\.");

        int partNo = parts.length;
        //There is a single dot then start merging timestamp
        if(partNo == 2){
            fileName = parts[0]+"_"+timeStamp+"."+parts[1];
        }else if(partNo > 2){
            //If we have more parts, then we handle differently
            fileName = "";
            StringBuilder stringBuilder = new StringBuilder(fileName);
            for(int index = 0; index < partNo; ++index){

                if(index == (partNo-1)){
                    stringBuilder.append("_").append(timeStamp).append(".").append(parts[index]);
                }else{
                    //When preceding parts show up do concatenation
                    stringBuilder.append(parts[index]);
                }
            }
            fileName = stringBuilder.toString();
        }
    }

    public String generateAttachment(FileRecords records) throws IOException {
        changeFileName(records.getFileName());
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
