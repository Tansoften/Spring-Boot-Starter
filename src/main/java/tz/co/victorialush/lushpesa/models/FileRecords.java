package tz.co.victorialush.lushpesa.models;

public class FileRecords {
    private String fileName;
    private String []heads;
    private Object [][]rows;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getHeads() {
        return heads;
    }

    public void setHeads(String[] heads) {
        this.heads = heads;
    }

    public Object[][] getRows() {
        return rows;
    }

    public void setRows(Object[][] rows) {
        this.rows = rows;
    }
}
