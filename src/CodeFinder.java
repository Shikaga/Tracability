import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CodeFinder {
	
	public String[] getComments(File file) throws IOException {
		String fileString = fileToString(file);
		
		String[] splitFile = fileString.split("/\\*\\*\n");
		int commentCount = splitFile.length;
		if (commentCount == 1) return new String[] {};
		
		String[] comments = new String[commentCount-1];
		
		for (int i=1; i < commentCount; i++) {			
			String comment = splitFile[i].substring(0, splitFile[i].indexOf("\n*/"));
			comments[i-1] = comment;
		}
		
		return comments;
	}

	public String fileToString(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String fileString = "";
		String lineString;
		while ((lineString = br.readLine()) != null) {
			fileString += lineString + "\n"; 
		}
		if (fileString == "") return "";
		return fileString.substring(0, fileString.length()-1);
	}

}
