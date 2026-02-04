package br.com.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.validation.constraints.Min;


import br.com.anotacao.TipoChave;
import br.com.dao.Persistente;
import br.com.dao.jpa.EstoqueProdutoJpaDAO;
import br.com.domain.Produto;
import br.com.domain.jpa.ProdutoJpa;
import br.com.exceptions.DAOException;
import br.com.exceptions.MaisDeUmRegistroException;
import br.com.exceptions.TableException;

@Entity
@Table(name = "estoque_produto")
public class EstoqueProdutoJpa implements Persistente{

    
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_seq")
	@SequenceGenerator(name="cliente_seq", sequenceName="sq_cliente", initialValue = 1, allocationSize = 1)
	private long id;

    @Column(name = "cadastro", nullable = false, unique = true)
    private Long cadastro;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoJpa produto;

    @Column(name = "quantidade", nullable = false, unique = true)
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private int quantidade;



    public EstoqueProdutoJpa produtoDisponivel(ProdutoJpa produto) throws MaisDeUmRegistroException, TableException, DAOException {
        EstoqueProdutoJpaDAO estoqueProdutoJpaDAO = new EstoqueProdutoJpaDAO();
        EstoqueProdutoJpa ep = estoqueProdutoJpaDAO.EstoquePorProduto(produto);
        this.setId(ep.getId());
        this.setQuantidade(ep.getQuantidade());
        this.setCadastro(ep.getCadastro());
        this.setProduto(ep.getProduto());
        return ep;
    }

    public void zeraEstoque(){
        EstoqueProdutoJpaDAO estoqueProdutoDAO = new EstoqueProdutoJpaDAO();
        try{
            estoqueProdutoDAO.excluir(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void retiraEstoque(int quantidade){
        EstoqueProdutoJpaDAO estoqueProdutoDAO = new EstoqueProdutoJpaDAO();
        this.setQuantidade(getQuantidade() - quantidade);
        try{
            estoqueProdutoDAO.alterar(this);
            }catch(Exception e){
                e.printStackTrace();
            }
    }

    

    public Long getCadastro() {
        return cadastro;
    }

    public void setCadastro(Long cadastro) {
        this.cadastro = cadastro;
    }

    public ProdutoJpa getProduto() {
        return produto;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setProduto(ProdutoJpa produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    

    

}
