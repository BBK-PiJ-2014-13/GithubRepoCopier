package copierCore;

import com.jcabi.github.Repo;

public class CopierImpl implements Copier{
	private Repo repo;
	
	@Override
	public Repo getRepo() {
		return repo;
	}

}
