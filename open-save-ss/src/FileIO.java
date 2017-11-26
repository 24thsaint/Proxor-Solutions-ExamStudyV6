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
    	for (int i = 0; i < ss.maxRows; i++) {
			for (int j = 0; j < ss.maxCols; j++) {
				ss.setCell(i, j, "");
			}
		}
    	ss.evaluate();
    	
    	CsvReader reader;
    	try {
			reader = new CsvReader(file.toString());
			
			int rowCount = 0;
			while(reader.readRecord()) {
				rowCount++;
			}
			
			reader = new CsvReader(file.toString());
			reader.readRecord();
			int colCount = 0;
			colCount = reader.getColumnCount();
			reader = new CsvReader(file.toString());
			
			for (int i = 0; i < rowCount; i++) {
				reader.readRecord();
				for (int j = 0; j < colCount; j++) {
					ss.setCell(i, j, reader.get(j));
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
    	for (int i = 0; i < ss.getRowCount(); i++) {
			for (int j = 0; j < ss.getColumnCount(i); j++) {
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
