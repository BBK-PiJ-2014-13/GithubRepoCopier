
package copierCore;

import com.jcabi.github.Repo;

/**
 * Copies repositories from Github and returns them wrapped in an object
 * 
 * Stores repository address as a String
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
