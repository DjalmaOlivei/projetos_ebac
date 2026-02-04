/**
 * 
 */
package br.com.dao.jpa;

import br.com.dao.generic.jpa.GenericJpaDAO;
import br.com.domain.jpa.ClienteJpa;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteJpaDAO extends GenericJpaDAO<ClienteJpa, Long> implements IClienteJpaDAO {

	public ClienteJpaDAO() {
		super(ClienteJpa.class);
	}

}
