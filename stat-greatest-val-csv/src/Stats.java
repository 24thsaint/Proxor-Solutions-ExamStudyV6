
public class Stats {
	private int rows;
	private int cols;
	private String[][] data;
	
	public Stats(int rows, int cols, String[][] data) {
		this.rows = rows;
		this.cols = cols;
		this.data = data;
		this.greatestValue();
	}
	
	public String[] greatestValue() {
		String[] greatestValues = new String[this.cols];
		greatestValues[0] = "Greatest";
		greatestValues[1] = "Value";
		for (int i = 2; i < this.cols; i++) {
			int greatestValue = 0;
			for (int j = 1; j < this.rows; j++) {
				int currentData = Integer.parseInt(this.data[j][i]);
				if (greatestValue <= currentData) {
					greatestValue = currentData;
				}
			}
			greatestValues[i] = String.valueOf(greatestValue);
		}
		this.data[this.rows] = greatestValues;
		return null;
	}
	
	public void writeData(int rows, int cols, String[][] data) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				String tabs = "\t";
				String datum = data[i][j];
				if (datum.length() < 8) {
					tabs += "\t";
				}
				System.out.print(datum + tabs);
			}
			System.out.println("");
		}
	}
}
