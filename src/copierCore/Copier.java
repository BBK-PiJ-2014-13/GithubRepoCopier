
package copierCore;

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
}
