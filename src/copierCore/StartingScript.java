package copierCore;

import com.jcabi.github.Repo;

public class StartingScript {
	public static void main(String[] args) {
		Copier copier = new CopierImpl();
		copier.launch("BBK-PiJ-2014-13/TestCopier");
	}
}
