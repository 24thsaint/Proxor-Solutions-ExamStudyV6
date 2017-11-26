// StatsCsv.java -- add greatest values to spreadsheet
//
// This is NOT a working program. This is a "skeleton" that
// should be modified and extended to meet the specifications.

import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


public class StatsCsv {
    
    private static final int MAXROW = 16;
    private static final int MAXCOL = 16;
    private int rowsUsed = 0;
    private int colsUsed = 0;
    private static final String inFile = "Data01.csv";
    private static final String outFile = "Data02.csv"; 
   
    public String[][] sheet = new String[MAXROW][MAXCOL];
    
    public static void main(String[] args) throws IOException {

        StatsCsv a = new StatsCsv();
        a.readSheet();
        // a.getRows() is the row count before adding medians
        Stats s = new Stats(a.getRows(), a.getCols(), a.getData());
        a.setRows(a.getRows() + 1); // because we added a row of medians
        s.writeSheet(a.getRows(), a.getCols(), a.getData());
        a.writeSheet();
    }
    
    public void writeSheet() throws IOException {
        CsvWriter writer = new CsvWriter(StatsCsv.outFile);
        for (int i = 0; i < this.rowsUsed; i++) {
			for (int j = 0; j < this.colsUsed; j++) {
				writer.write(this.sheet[i][j]);
			}
			writer.endRecord();
		}
        writer.flush();
        writer.close();
    }

    public void readSheet() throws IOException {
    	CsvReader reader = new CsvReader(StatsCsv.inFile);
    	for (int i = 0; reader.readRecord(); i++) {
			this.colsUsed = reader.getColumnCount();
			this.rowsUsed++;
			for (int j = 0; j < this.colsUsed; j++) {
				this.sheet[i][j] = reader.get(j);
			}
		}
    }

    public int getRows(){
    	return rowsUsed;
    }
    
    public int setRows(int r) {
        rowsUsed = r;
        return rowsUsed;
    }

    public int getCols() {
    	return colsUsed;
    }
    
    public String[][] getData() {
    	return sheet;
    }
}
