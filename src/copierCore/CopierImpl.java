package copierCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		try {
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			File targetFile = new File(path);
			OutputStream outputStream = new FileOutputStream(targetFile);
			outputStream.write(buffer);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new File(path);
	}
	
	@Override
	public void writeDirectory() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Iterator<Content> getIterator(Contents c, String path, String branch) {
		Iterator<Content> result = null;
		try {
			result = c.iterate(path, branch).iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getRepoName(Repo repo) {
		// TODO Auto-generated method stub
		return null;
	}

}
