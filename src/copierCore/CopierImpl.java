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
	public File writeContent(Content content) {
		File output = null;
		try {
			InputStream is = content.raw();
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			String newFilePath = content.repo() + "/" + content.path();
			OutputStream outputStream = new FileOutputStream(newFilePath);
			outputStream.write(buffer);
			outputStream.close();
			output = new File(newFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	@Override
	public void writeDirectory(Contents contents, String path) {
		try {
			Iterator<Content> directoryContents = getIterator(contents, path,
					"master");

			do {
				File currentFile = new File(directoryContents.next().path());
				Content currentFileContent = contents
						.get(currentFile.getPath());
				writeContent(currentFileContent);
			} while (directoryContents.hasNext());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return repo.coordinates().repo();
	}

}
