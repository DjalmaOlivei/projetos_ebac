/**
 * 
 */
package br.com.dao;

import br.com.dao.generic.IGenericDAO;
import br.com.domain.Venda;
import br.com.exceptions.DAOException;
import br.com.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author rodrigo.pires
 *
 */
public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
