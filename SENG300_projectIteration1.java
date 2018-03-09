import java.io.File;
import java.util.Scanner;

public class SENG300_projectIteration1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// create new scanner to return the variables inputed into the console
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the pathname: ");
		
		// sets the pathname as the pathname inputed
		String pathname = scanner.next();
		
		System.out.println("The pathname you entered is " + pathname);
		
		System.out.print("Enter the qualified name of a Java type: ");
		
		// sets the type as the java type inputed
		String javaType = scanner.next();
		
		System.out.println("The Java type you entered is " + javaType);
		
		// close the scanner
		scanner.close();
		
		// create a new folder/directory from the pathname given
		File folder = new File(pathname);
		// obtain the list of files within the directory
		File[] listOfFiles = folder.listFiles();

		// for each of the files within the directory print it out
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			// file must be a java file
			if (file.isFile() && file.getName().endsWith(".java")) {
				System.out.println(file.getName());
				
				// need to parse each file using the AST parser
				// to find any declarations of javaType and any references
			} 
		}	

	}
}
