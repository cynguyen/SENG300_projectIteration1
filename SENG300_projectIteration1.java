
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
 
public class SENG300_projectIteration1 {

	private static int a = 0;
	private static int b = 0;
	
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
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".java")) {
				System.out.println(file.getName());
				System.out.println(readFileToString(file.getAbsolutePath()));
				parse(readFileToString(file.getAbsolutePath()), javaType);
			} 
		}
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
	
	public static void parse(String source, String type) {
		ASTParser parse = ASTParser.newParser(AST.JLS2);
		parse.setKind(ASTParser.K_COMPILATION_UNIT);
		parse.setSource(source.toCharArray());
		
		parse.setResolveBindings(true);
		parse.setBindingsRecovery(true);
		
		CompilationUnit cu = (CompilationUnit)parse.createAST(null);
		
		cu.accept(new ASTVisitor() {
			int countDeclarations = 0;
			int countReferences = 0;
			Set names = new HashSet();
			public boolean visit(VariableDeclarationFragment node) {
				//System.out.println(node.getParent().toString());
				String all = node.getParent().toString();

				SimpleName name = node.getName();
				//System.out.println(name);
				//this.names.add(name.getIdentifier());
				String findStr = type;
				int lastIndex = 0;

				while(lastIndex != -1){

				    lastIndex = all.indexOf(findStr,lastIndex);

				    if(lastIndex != -1){
				        countDeclarations ++;
				        a++;
				        this.names.add(name.getIdentifier());
				        System.out.println(names);
				        lastIndex += findStr.length();
				    }
				}
				
				System.out.println("identifier: " + node.toString());
				System.out.println(" = " + node.toString().lastIndexOf("="));
				System.out.println(" = " + node.toString().length());
				if (node.toString().lastIndexOf("=") != -1) {
					for (int i = 0; i < names.toArray().length; i++) {
						String charc = (String) names.toArray()[i];
						if(node.toString().substring(node.toString().lastIndexOf("="), node.toString().length()).contains(charc)) {
							System.out.println("count");
							countReferences++;
							b++;
						}
					}

				}
				return false;

			}
			
//			public boolean visit(VariableDeclarationStatement node) {
//				System.out.println("identifier: " + node.toString());
//				for (int i = 0; i < names.toArray().length; i++) {
//					String charc = (String) names.toArray()[i];
//					if(node.toString().contains(charc)) {
//						countReferences++;
//						b++;
//					}
//				}
//				
//				return true;
//			}

		});
		System.out.println("Declarations: "+a);
		System.out.println("References: "+b);


	}
 
}
