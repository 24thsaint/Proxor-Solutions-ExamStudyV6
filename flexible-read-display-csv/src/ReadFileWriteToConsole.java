import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.csvreader.CsvReader;

public class ReadFileWriteToConsole {

	private static final String inFile = "in.csv";
	private CsvReader csvReader;
	private String[][] sheet;
	private int rowCount;
	private int colCount;

	public static void main(String args[]) throws IOException {
		ReadFileWriteToConsole reader = new ReadFileWriteToConsole();
		reader.writeSheet();
		return;
	}

	public ReadFileWriteToConsole() {
		try {
			this.makeSheet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCell(int row, int col) {
		if (row > this.getRowCount())
			throw new Error("Accessed row is greater than row count");
		if (col > this.getColCount())
			throw new Error("Accessed col is greater than col count");
		return this.sheet[row][col];
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public int getColCount() {
		return this.colCount;
	}

	public void reloadSheet() throws FileNotFoundException {
		this.csvReader = new CsvReader(ReadFileWriteToConsole.inFile);
	}

	public void makeSheet() throws IOException {
		this.reloadSheet();

		int rowCount = 0;
		while (this.csvReader.readRecord()) {
			rowCount++;
		}
		this.rowCount = rowCount;

		this.reloadSheet();

		this.csvReader.readRecord();
		this.colCount = this.csvReader.getColumnCount();

		this.reloadSheet();
		
		this.sheet = new String[this.getRowCount()][this.getColCount()];

		for (int i = 0; i < this.getRowCount(); i++) {
			this.csvReader.readRecord();
			for (int j = 0; j < this.getColCount(); j++) {
				this.sheet[i][j] = this.csvReader.get(j);
			}
		}
	}

	public void writeSheet() {
		for (int i = 0; i < this.getRowCount(); i++) {
			for (int j = 0; j < this.getColCount(); j++) {
				System.out.print("[" + this.getCell(i, j) + "]");
			}
			System.out.println("");
		}
		return;
	}
}
