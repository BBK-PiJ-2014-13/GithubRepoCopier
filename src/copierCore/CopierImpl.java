package copierCore;

import com.jcabi.github.Content;
import com.jcabi.github.Contents;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;

public class CopierImpl implements Copier{
	
	@Override
	public Repo getRepo(String s) {
		Github github = new RtGithub();
		Repo repo = github.repos().get(new Coordinates.Simple("BBK-PiJ-2014-13/test"));
		Contents contents = repo.contents();
		Content content = contents.get("./text.txt");
		
		return null;
	}

}
