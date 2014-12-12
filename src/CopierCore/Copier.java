
package CopierCore;

import com.jcabi.github.Repo;

/**
 * Copies repositories from Github and returns them wrapped in an object
 * 
 * @author Ilya Ivanov
 */
public interface Copier {
	/**
	 * @return the requested repository
	 */
	public Repo getRepo();
}
