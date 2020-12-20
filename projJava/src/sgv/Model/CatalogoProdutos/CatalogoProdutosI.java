package sgv.Model.CatalogoProdutos;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Interface que aglomera classes que representam catalogos de produtos.
 */
public interface CatalogoProdutosI extends Serializable {
    /**
     * Método que adiciona um Produto à classe.
     * @param p Produto.
     */
    void add(ProdutoI p);

    /**
     * Método que retorna uma cópia do objeto.
     * @return Cópia.
     */
    CatalogoProdutosI clone();

    /**
     * Retorna um array com bases de dados dos Produtos.
     * @return Array de dados.
     */
    DistribuicaoProdutoI[] getDistribuicaoProduto();

    /**
     * Altera a base de dados de Produtos.
     * @param produtos Dados a substituir.
     */
    void setDistribuicaoProdutos(DistribuicaoProdutoI[] produtos);

    /**
     * Procura um código de produto na classe.
     * @param codProd Código do produto.
     * @return True se encontrou, false caso contrário.
     */
    boolean search(String codProd);

    /**
     * Método que retorna o número de produtos total.
     * @return Número de produtos.
     */
    int getNumProdutos();

    /**
     * Método que altera o número de produtos total.
     * @param numProdutos Número novo.
     */
    void setNumProdutos(int numProdutos);

    /**
     * Retorna um conjunto de códigos de produto nunca comprados a partir de um conjunto de códigos de produtos.
     * @param prods Conjunto total de códigos de produtos.
     * @return Conjunto de códigos de produto.
     */
    Set<String> getProductsNeverBought(Set<String> prods);
}
