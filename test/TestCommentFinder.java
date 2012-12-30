import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class TestCommentFinder {
	
	CodeFinder cf;
	
	File noCommentFile = new File("testFiles/NoCommentFile.js");
	File oneCommentFile = new File("testFiles/OneCommentFile.js");
	File oneTwoLineCommentFile = new File("testFiles/OneTwoLineCommentFile.js");
	File oneThreeLineCommentFile = new File("testFiles/OneThreeLineCommentFile.js");
	
	File twoCommentFile = new File("testFiles/TwoCommentFile.js");
	
	@Before
	public void setup() {
		 cf = new CodeFinder();
	}

	@Test
	public void testReadFileToString() throws Exception {
		String fileString = cf.fileToString(oneCommentFile);
		assertEquals("/**\nComment\n*/\nfunction x() {\n\n};", fileString);
	}
	
	@Test
	public void testReadBlankFile() throws Exception {
		String fileString = cf.fileToString(noCommentFile);
		assertEquals("", fileString);
	}
	
	@Test
	public void testNoCommentFileReturnsNothing() throws IOException {
		String[] comments = cf.getComments(noCommentFile);
		assertEquals(0, comments.length);
	}
	
	@Test
	public void testCommentIsExtractedFromFile() throws IOException {
		String[] comments = cf.getComments(oneCommentFile);
		assertEquals(1, comments.length);
		assertEquals("Comment", comments[0]);
	}
	
	@Test
	public void testTwoLineCommentIsExtractedFromFile() throws IOException {
		String[] comments = cf.getComments(oneTwoLineCommentFile);
		assertEquals(1, comments.length);
		assertEquals("Comment\non two lines", comments[0]);
	}
	
	@Test
	public void testThreeLineCommentIsExtractedFromFile() throws IOException {
		String[] comments = cf.getComments(oneThreeLineCommentFile);
		assertEquals(1, comments.length);
		assertEquals("Comment\non three\nlines", comments[0]);
	}
	
	@Test
	public void testTwoCommentAreExtractedFromFile() throws IOException {
		String[] comments = cf.getComments(twoCommentFile);
		assertEquals(2, comments.length);
		assertEquals("Comment", comments[0]);
		assertEquals("Comment 2", comments[1]);
	}

}
