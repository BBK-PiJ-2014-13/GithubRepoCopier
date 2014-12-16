
package copierCore;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;

import com.jcabi.github.Content;
import com.jcabi.github.Contents;
import com.jcabi.github.Repo;

/**
 * Copies root folder from a Github repository to a local directory
 * 
 * @author Ilya Ivanov
 */
public interface Copier {
	/**
	 * Launch method that executes all the needed methods to copy the target repository
	 * @param s Path to the target repository
	 */
	public void copier(String s);
	
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
	 * Writes a stream of bytes into a file
	 * 
	 * @param is InputStream that need to be written into a file
	 * @param path Path where the file needs to be written
	 * @return File that was just written
	 */
	public File writeStream(InputStream is, String path);
	
	/**
	 * Writes target directory on Github into the local directory
	 */
	public void writeDirectory(Contents contents);
	
	/**
	 * Returns an Iterator that holds Content objects
	 * 
	 * @param c Contents from which an Iterator must be created
	 */
	public Iterator<Content> getIterator(Contents c, String path, String branch);
	
	/**
	 * Returns the name of repository
	 * 
	 * @param repo Repo object
	 * @return Name of the repository
	 */
	public String getRepoName(Repo repo);
}
