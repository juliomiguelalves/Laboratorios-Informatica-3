package sgv.Model.CatalogoProdutos;

import java.io.Serializable;

/**
 * Interface que aglomera classes que representam Distribuicao de Produtos.
 */
public interface ProdutoI extends Comparable<ProdutoI>, Serializable {
    /**
     * Método que retorna o código do produto.
     * @return Código produto.
     */
    String getCodigo();

    /**
     * Método que altera o código do produto.
     * @param codigo Novo código.
     */
    void setCodigo(String codigo);

    /**
     * Método que gera uma cópia do objeto.
     * @return Cópia.
     */
    ProdutoI clone();
}
