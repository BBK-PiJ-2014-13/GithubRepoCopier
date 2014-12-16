package copierCore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

import com.jcabi.github.Content;
import com.jcabi.github.Contents;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;

public class CopierTest extends BasicTest {
	CopierImpl copier;
	Github github;
	Repo repo;
	Contents contents;
	Iterator iterator;
	
	@Test
	public void testsCopier() {
		valueExpected = 0;
		valueActual = 1;
		test();
	}
	
	@Test
	public void testsGetRepo() {
		copier = new CopierImpl();
		valueActual = copier.getRepo("BBK-PiJ-2014-13/Test");
		
		github = new RtGithub();
		repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/Test"));
		valueExpected = repo;
		test();
	}
	
	@Test
	public void testsGetContents() {
		copier = new CopierImpl();
		github = new RtGithub();
		repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/Test"));
		valueActual = copier.getContents(repo);

		valueExpected = repo.contents();
		test();
	}
	
	@Test
	public void testsWriteStream() throws IOException {
		copier = new CopierImpl();
		github = new RtGithub();
		repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/Test"));
		Contents contents = repo.contents();
		Content content = contents.get("test.txt");
		InputStream inputStream = content.raw();
		byte[] buffer = new byte[inputStream.available()];
		inputStream.read(buffer);
		File targetFile = new File("targetFile.txt");
		OutputStream outputStream = new FileOutputStream(targetFile);
		outputStream.write(buffer);
		outputStream.close();
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(targetFile));
		valueExpected = bufferedReader.readLine();
		bufferedReader.close();
		
		inputStream = content.raw();
		File returnFile = copier.writeStream(inputStream, content.path());
		bufferedReader = new BufferedReader(new FileReader(returnFile));
		valueActual = bufferedReader.readLine();
		bufferedReader.close();
		test();
	}

	@Test
	public void testsWriteDirectory() throws IOException {
		copier = new CopierImpl();
		github = new RtGithub();
		repo = copier.getRepo("BBK-PiJ-2014-13/Test");
		contents = copier.getContents(repo);
		copier.writeDirectory(contents);
		
		File directory = new File("Test");
		ArrayList<File> fileNames = new ArrayList<File>(Arrays.asList(directory.listFiles()));
		valueExpected = "src";
		valueActual = fileNames.get(1);
		test();
		
		valueExpected = ".classpath";
		valueActual = fileNames.get(2);
		test();
		
		valueExpected = "test.txt";
		valueActual = fileNames.get(5);
		test();
	}
	
	@Test
	public void testsGetIterator() throws IOException {
		copier = new CopierImpl();
		github = new RtGithub();
		repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/Test"));
		Contents contents = repo.contents();
		valueActual = copier.getIterator(contents, "", "master").next();
		
		valueExpected = repo.contents().iterate("", "master").iterator().next();
		test();
	}

	@Test
	public void testsGetRepoName() {
		copier = new CopierImpl();
		github = new RtGithub();
		repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/Test"));
		valueExpected = "Test";
		
		valueActual = copier.getRepoName(repo);
		test();
	}
}
