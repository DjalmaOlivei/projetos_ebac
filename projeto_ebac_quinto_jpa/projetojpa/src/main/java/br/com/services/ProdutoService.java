/**
 * 
 */
package br.com.services;

import br.com.dao.IProdutoDAO;
import br.com.domain.Produto;
import br.com.services.generic.GenericService;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
