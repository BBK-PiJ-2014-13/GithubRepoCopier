package copierCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		Repo repo = github.repos().get(new Coordinates.Simple(s));
		Contents contents = repo.contents();
		
		try {
			Content content = contents.get("./text.txt");
			InputStream inputStream = content.raw();
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			
			File testFile = new File("targetFileTest.txt");
			OutputStream outputStream = new FileOutputStream(testFile);
			outputStream.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
