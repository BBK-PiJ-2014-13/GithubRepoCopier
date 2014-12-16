package copierCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.jcabi.github.Content;
import com.jcabi.github.Contents;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;

public class CopierImpl implements Copier {
	private Github github;
	private Repo repository;
	Contents repositoryContents;
	
	@Override
	public void copier(String s) {
		github = new RtGithub();
		repository = github.repos().get(new Coordinates.Simple("s"));
		repositoryContents = repository.contents();
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
	public void writeDirectory(String path) {
		try {
			Iterator<Content> directoryContents = getIterator(repositoryContents, path,
					"master");

			do {
				File currentFile = new File(directoryContents.next().path());
				Content currentFileContent = repositoryContents
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

	
	@Override
	public ArrayList<File> getListOfDirectories(String directoryPath) {
		File directory = new File(directoryPath);
		ArrayList<File> resultList = new ArrayList<File>(
				Arrays.asList(directory.listFiles()));
		for (int i = 0; i < resultList.size(); i++) {
			if (!resultList.get(i).isDirectory()) {
				resultList.remove(i);
			}
		}
		return resultList;
	}


	@Override
	public void goThroughDirectories(boolean hasMoreDirectories,
			String currentDirectory) {
		writeDirectory(currentDirectory);
		ArrayList<File> listOfDirectories = getListOfDirectories(currentDirectory);
		if (listOfDirectories.isEmpty()) {
			return;
		}
	}

}
