package copierCore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import com.jcabi.github.Content;
import com.jcabi.github.Contents;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;

public class CopierImpl implements Copier {
	
	@Override
	public void copier(String s) {
		
	}

	@Override
	public Repo getRepo(String s) {
		Github github = new RtGithub();
		Repo repo = github.repos().get(new Coordinates.Simple(s));
		return repo;
	}

	@Override
	public Contents getContents(Repo r) {
		return r.contents();
	}

	@Override
	public File writeStream(InputStream is, String path) {
		return null;
	}

	@Override
	public Iterator<Content> getIterator(Contents c, String path, String branch) {
		Iterator<Content> result = null;
		try {
			result =  c.iterate(path, branch).iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
