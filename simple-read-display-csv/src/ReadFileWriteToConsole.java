import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;

public class ReadFileWriteToConsole {
	
	private static final int MAXROW = 3;
	private static final int MAXCOL = 3;
    private static final String inFile = "in.csv";
	   
    public String[][] sheet = new String[MAXROW][MAXCOL];
	   
    public static void main(String args[]) throws IOException {
    	ReadFileWriteToConsole reader = new ReadFileWriteToConsole();
    	reader.readSheet();
    	reader.writeSheet();
    }	
	   
    public void readSheet() throws IOException {
    	CsvReader reader = new CsvReader(ReadFileWriteToConsole.inFile);
    	for (int i = 0; i < ReadFileWriteToConsole.MAXROW; i++) {
			reader.readRecord();
			for (int j = 0; j < ReadFileWriteToConsole.MAXCOL; j++) {
				this.sheet[i][j] = reader.get(j);
			}
		}
	}
	   
	public void writeSheet(){
		for (int i = 0; i < ReadFileWriteToConsole.MAXROW; i++) {
			for (int j = 0; j < ReadFileWriteToConsole.MAXCOL; j++) {
				System.out.print("[" + this.sheet[i][j] + "]");
			}
			System.out.println("");
		}
	}
}
