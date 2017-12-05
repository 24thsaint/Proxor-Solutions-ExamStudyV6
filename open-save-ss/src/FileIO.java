// FileIOBase.java -- template for FileIO.java.
//
// this file should be modified to implement
// open, save, and save as... commands for SpreadSheet.java
// 
// Do not modify the signatures of these methods.


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class FileIO {
	
    public static boolean open(SpreadSheet ss, File file) {
    	ss.clearCells(); // clears the current sheet to prepare for the new sheet data 
    	
    	CsvReader reader;
    	try {
			reader = new CsvReader(file.toString());
			
			for (int i = 0; reader.readRecord(); i++) {
				String[] rowValues = reader.getValues();
				for (int j = 0; j < rowValues.length; j++) {
					ss.setCell(i, j, rowValues[j]);
				}
			}
			
			ss.evaluate();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }
	
    public static boolean saveAs(SpreadSheet ss, File file) {
    	CsvWriter writer = new CsvWriter(file.toString());
    	for (int i = 0; i <= ss.getRowCount(); i++) { // Inclusive row count
			for (int j = 0; j <= ss.getColumnCount(i); j++) { // Inclusive column count
				try {
					writer.write(ss.getCellFormula(i, j));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				writer.endRecord();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	writer.close();
    	return true;
    }
    
}
