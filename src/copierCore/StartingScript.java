package copierCore;

import com.jcabi.github.Repo;

public class StartingScript {
public static void main(String[] args) {
	Copier copier = new CopierImpl();
	Repo repo = copier.getRepo("BBK-PiJ-2014-13/cw3");
	System.out.println("Repository name is: " + repo.coordinates().repo());
	System.out.println("Repository user is: " + repo.coordinates().user());
}
}
