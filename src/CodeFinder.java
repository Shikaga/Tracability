import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class CodeFinder {
	
	private String[] splitFileByComment(String fileString) {
		String[] splitFile = fileString.split("/\\*\\*\n");
		return Arrays.copyOfRange(splitFile, 1, splitFile.length);
	}
	
	public Comment[] getComments(File file) throws IOException {
		String fileString = fileToString(file);
		String[] splitFile = splitFileByComment(fileString);
		
		int numberOfComments = splitFile.length;
		if (numberOfComments == 0) return new Comment[] {};
		
		Comment[] commentsToReturns = new Comment[numberOfComments];
		
		for (int i=0; i < numberOfComments; i++) {
			String[] commentAndCode = splitFile[i].split("\n\\*/\n"); 
			String comment = commentAndCode[0];
			String restOfCode = commentAndCode[1];
			String firstLineOfcode = restOfCode.substring(0, restOfCode.indexOf("\n"));
			commentsToReturns[i] = new Comment(comment, firstLineOfcode);
		}
		
		return commentsToReturns;
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
