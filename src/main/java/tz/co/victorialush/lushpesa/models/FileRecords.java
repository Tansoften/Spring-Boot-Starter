package tz.co.victorialush.lushpesa.models;

public class FileRecords {
    private String []heads;
    private Object [][]rows;

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
