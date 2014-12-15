
package copierCore;

import java.io.InputStream;

import com.jcabi.github.Contents;
import com.jcabi.github.Repo;

/**
 * Copies root folder from a Github repository to a local directory
 * 
 * @author Ilya Ivanov
 */
public interface Copier {
	/**
	 * @param s repository address
	 * @return the requested repository
	 */
	public Repo getRepo(String s);
	
	/**
	 * @param r target repository
	 * @return contents of target repository
	 */
	public Contents getContents(Repo r);
	
	/**
	 * @param is InputStream that need to be written into a file
	 * @param s Path where the file needs to be written
	 */
	public void writeStream(InputStream is, String s);
	
	/**
	 * @param c Contents from which an Iterator must be created
	 */
	public void getIterator(Contents c, String path, String branch);
	
}
