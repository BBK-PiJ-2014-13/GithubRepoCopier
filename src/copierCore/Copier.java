
package copierCore;

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
}
