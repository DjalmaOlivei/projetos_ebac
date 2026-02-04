package br.com.dao.jpa;

import br.com.dao.generic.jpa.GenericJpaDAO;
import br.com.domain.Produto;
import br.com.domain.jpa.EstoqueProdutoJpa;
import br.com.domain.jpa.ProdutoJpa;
import br.com.exceptions.DAOException;
import br.com.exceptions.MaisDeUmRegistroException;
import br.com.exceptions.TableException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EstoqueProdutoJpaDAO extends GenericJpaDAO<EstoqueProdutoJpa, Long> implements IEstoqueProdutoJpaDAO{

    public EstoqueProdutoJpaDAO() {
        super(EstoqueProdutoJpa.class);
    }

    
	public EstoqueProdutoJpa EstoquePorProduto(ProdutoJpa produto) throws MaisDeUmRegistroException, TableException, DAOException {
		openConnection();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EstoqueProdutoJpa> cq = cb.createQuery(EstoqueProdutoJpa.class);
        Root<EstoqueProdutoJpa> root = cq.from(EstoqueProdutoJpa.class);
        Predicate condition = cb.equal(root.get("produto"), produto.getId());
        cq.select(root).where(condition);
        EstoqueProdutoJpa entity = entityManager.createQuery(cq).getSingleResult();
		closeConnection();
		return entity;
	}

}
