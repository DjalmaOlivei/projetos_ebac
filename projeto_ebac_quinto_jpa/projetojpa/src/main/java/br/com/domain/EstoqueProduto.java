package br.com.domain;

import br.com.anotacao.ColunaTabela;
import br.com.anotacao.Tabela;
import br.com.anotacao.TipoChave;
import br.com.dao.EstoqueProdutoDAO;
import br.com.dao.Persistente;

@Tabela("tb_estoque_produto")
public class EstoqueProduto  implements Persistente{

    
    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private long id;

    @TipoChave("cadastro")
    @ColunaTabela(dbName = "cadastro", setJavaName = "setCadastro")
    private Long cadastro;

    @ColunaTabela(dbName = "id_produto_fk", setJavaName = "setIdProdutoFk")
    private Produto produto;

    @ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade")
    private int quantidade;


    public EstoqueProduto produtoDisponivel(Produto produto){
        EstoqueProdutoDAO estoqueProdutoDAO = new EstoqueProdutoDAO();
        EstoqueProduto ep = estoqueProdutoDAO.getEsoqueProduto(produto);
        this.setId(ep.getId());
        this.setQuantidade(ep.getQuantidade());
        this.setCadastro(ep.getCadastro());
        this.setProduto(ep.getProduto());
        return ep;
    }

    public void zeraEstoque(){
        EstoqueProdutoDAO estoqueProdutoDAO = new EstoqueProdutoDAO();
        try{
            estoqueProdutoDAO.excluir(this.cadastro);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void retiraEstoque(int quantidade){
        EstoqueProdutoDAO estoqueProdutoDAO = new EstoqueProdutoDAO();
        this.setQuantidade(getQuantidade() - quantidade);
        try{
            estoqueProdutoDAO.alterar(this);
            }catch(Exception e){
                e.printStackTrace();
            }
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCadastro() {
        return cadastro;
    }

    public void setCadastro(Long cadastro) {
        this.cadastro = cadastro;
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    

}
