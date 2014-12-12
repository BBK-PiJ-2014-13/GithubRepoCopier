package copierCore;

import org.junit.Test;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;

public class CopierTest extends BasicTest {

	@Test
	public void testsGetRepo() {
		Github github = new RtGithub();
		Repo repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/cw3"));
		test();
	}
}
