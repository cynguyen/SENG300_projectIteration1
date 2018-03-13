
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

 
public class SENG300_projectIteration1 {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the pathname: ");
		String pathname = scanner.next();
		System.out.println("The pathname you entered is " + pathname);
		System.out.print("Enter the qualified name of a Java type: ");
		String javaType = scanner.next();
		System.out.println("The Java type you entered is " + javaType);
		scanner.close();
		File folder = new File(pathname);
		File[] listOfFiles = folder.listFiles();
		//**************
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".java")) {
				System.out.println(file.getName());
				System.out.println(readFileToString(file.getAbsolutePath()));
			} 
		}
		//*******************
	}
 
	// translate file source code into a string
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000); 						// creates a new string builder of capacity 1000 for the data in the file
		BufferedReader reader = new BufferedReader(new FileReader(filePath)); // buffered reader reads text from the file reader. file reader reads the stream of characters from the file
		 
		char[] buffer = new char[10]; // create a new buffer of 10 characters
		int characters_read = 0;
		// while it is not the end of the file
		while ((characters_read = reader.read(buffer)) != -1) {
			System.out.println(characters_read);
			String readData = String.valueOf(buffer, 0, characters_read); // save the data read into a string
			fileData.append(readData); // append the readData into the total fileData
			buffer = new char[1024]; // create a new buffer of more characters
		}
		 
		reader.close(); // close the reader
		 
		return  fileData.toString(); // return the fileData as a string
	}
 
}
