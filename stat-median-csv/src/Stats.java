import java.util.Arrays;

public class Stats {
	private int rows;
	private int cols;
	private String[][] data;

	public Stats(int rows, int cols, String[][] data) {
		this.rows = rows;
		this.cols = cols;
		this.data = data;
		this.computeMedians();
	}
	
	public String[][] computeMedians() {
		String[] medians = new String[this.cols];
		medians[0] = "";
		medians[1] = "Median";
		for (int i = 2; i < this.cols; i++) {
			String[] temp = new String[this.rows - 1];
			
			for (int j = 1; j < this.rows; j++) {
				 temp[j - 1] = this.data[j][i];
			}
			
			medians[i] = this.calculateMedian(temp);
		}
		this.data[this.rows] = medians;
		return this.data;
	}
	
	public String calculateMedian(String[] data) {
		Arrays.sort(data);
		if (data.length % 2 == 0) {
			int lowerIndex = (data.length - 1) / 2;
			int upperIndex = lowerIndex + 1;
			
			int average = (int) Math.ceil((Double.parseDouble(data[lowerIndex]) + Double.parseDouble(data[upperIndex])) / 2.0);
			return String.valueOf(average);
		} else {
			int middleIndex = (int) Math.ceil(data.length / 2.0);
			return data[middleIndex];
		}
	}
	
	public void writeSheet(int rows, int cols, String[][] data) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(data[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
