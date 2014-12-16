package copierCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		valueExpected = new File("targetFile.txt");
		
		valueActual = copier.writeStream(inputStream, "");
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
}
