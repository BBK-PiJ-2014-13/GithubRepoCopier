
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
	 * Writes a Content object into a file
	 * 
	 * @param content Content object
	 * @return File that was just written
	 */
	public File writeContent(Content content);
	
	/**
	 * Writes target directory in a repository into the local directory
	 * @param contents Contents of the directory
	 * @param path Path of the directory
	 */
	public void writeDirectory(Contents contents, String path);
	
	/**
	 * @param contents Contents of a repository from which an Iterator must be created
	 * @param path Path of the directory
	 * @param branch Branch
	 * @return Iterator that holds Content objects of chosen directory
	 */
	public Iterator<Content> getIterator(Contents contents, String path, String branch);
	
	/**
	 * Returns the name of repository
	 * 
	 * @param repo Repo object
	 * @return Name of the repository
	 */
	public String getRepoName(Repo repo);
}
