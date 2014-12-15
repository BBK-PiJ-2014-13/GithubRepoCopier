package copierCore;

import org.junit.Test;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;

public class CopierTest extends BasicTest {

	@Test
	public void testsGetRepo() {
		CopierImpl copier = new CopierImpl();
		Github github = new RtGithub();
		Repo repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/cw3"));
		valueExpected = repo;
		valueActual = copier.getRepo("BBK-PiJ-2014-13/cw3");
		test();
	}
	
	@Test
	public void testsGetContents() {
		
	}
}
