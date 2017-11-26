import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Vector;

import com.csvreader.*;

public class WriteHtml {
    public static final String inFileName = "input.csv";
    public static final String outFileName = "output.html";    

    public static void main(String[] args) {    	
    	try {
			CsvReader reader = new CsvReader(WriteHtml.inFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(WriteHtml.outFileName));
			writer.write("<html>\n");
			writer.write("\t<head>\n");
			writer.write("\t\t<title>" + WriteHtml.inFileName + "</title>\n");
			writer.write("\t</head>\n");
			writer.write("\t<body>\n");
			writer.write("\t\t<table style=\" text-align: left;\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">\n");
			for (int i = 0; reader.readRecord(); i++) {
				writer.write("\t\t\t<tr>\n");
				String[] columnData = reader.getValues();
				for (int j = 0; j < columnData.length; j++) {
					writer.write("\t\t\t\t<td>" + EscapeHTML.stringToHTMLString(columnData[j]) + "</td>\n");
				}
				writer.write("\t\t\t</tr>\n");
			}
			writer.write("\t\t</table>\n");
			writer.write("\t</body>\n");
			writer.write("</html>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
