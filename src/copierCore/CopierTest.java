package copierCore;

import java.io.IOException;

import org.junit.Test;

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
	public void testsWriteStream() {
		copier = new CopierImpl();
		github = new RtGithub();
		repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/Test"));
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
