import java.io.*;

/*
 * Reads in the input file into a String array.
 * Makes a Graph object.
 */
public class Project3 {
	public static void main (String[] args) throws IOException {
		FileInputStream fstream = new FileInputStream(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String[] input = new String[1024];
		String line;
		int counter = 0;

		while ((line = br.readLine()) != null) // read in the input file into array input
			input[counter++] = line;

		Graph theGraph = new Graph(input); // make a graph object

	} // main
} // Project3 class
