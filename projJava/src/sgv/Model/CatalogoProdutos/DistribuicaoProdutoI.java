package sgv.Model.CatalogoProdutos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface que aglomera classes que representam Distribuicao de Produtos.
 */
public interface DistribuicaoProdutoI extends Serializable {
    /**
     * Adiciona um Produto à estrutura.
     * @param p Produto.
     */
    void add(ProdutoI p);

    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    DistribuicaoProdutoI clone();

    /**
     * Retorna os dados do objeto.
     * @return Dados.
     */
    Map<String, ProdutoI> getProdutos();

    /**
     * Procura um código de produto na estrutura.
     * @param codProd Código de produto.
     * @return True se encontrou, false caso contrário.
     */
    boolean search(String codProd);

    /**
     * Obtem o número de produtos.
     * @return Número de produtos.
     */
    int getNumProdutos();

    /**
     * Altera o número de produtos.
     * @param numProdutos Número novo.
     */
    void setNumProdutos(int numProdutos);

    /**
     * Retorna um conjunto de códigos de produto nunca comprados.
     * @param prods Conjunto de códigos de produto total.
     * @return Conjunto de códigos de produto.
     */
    Set<String> getProductsNeverBought(Set<String> prods);
}
