package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface que aglomera classes que representa Filiais Produtos.
 */
public interface FiliaisProdutosI extends Serializable {
    /**
     * Devolve a estrutura de dados do obejeto.
     * @return Estrutura de dados.
     */
    Map<String, List<VendaI>> getFiliaisProdutos();

    /**
     * Altera a estrutura de dados do objeto.
     * @param fpr Estrutura de dados nova.
     */
    void setFiliaisProdutos(Map<String,List<VendaI>> fpr);

    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    FiliaisProdutosI clone();

    /**
     * Adiciona uma nova entrada na estrutura de dados.
     * @param preco Preço da venda.
     * @param quantidade Quantidade vendida.
     * @param promocao Promoção.
     * @param codProduto Código do produto.
     */
    void add(Double preco, int quantidade, char promocao,String codProduto);

    /**
     * Limpa a estrutura de dados.
     */
    void clear();

    /**
     * Devolve o número de vendas do mês.
     * @return Número de vendas.
     */
    int getNumVendas();

    /**
     * Altera o número de vendas do mês.
     * @param numVendas Número de vendas novo.
     */
    void setNumVendas(int numVendas);

    /**
     * Método que devolve os valores de a partir de um cliente, quantas compras fez, quantos produtos comprou e o total faturado.
     * @return Array de 3 posições correspondentes aos 3 valores da descrição respetivamente.
     */
    Double[] getClientSalesAndWasted();

    /**
     * Método que devolve os valores de a partir de um produto, quantas vezes foi comprado, por quantos clientes foi comprado e o total faturado.
     * @param key Código do cliente
     * @param cod Código do produto.
     * @param list Lista de códigos de clientes que compraram.
     * @return Array de 3 posições correspondentes aos 3 valores da descrição respetivamente.
     */
    Double[] getProductSalesAndWasted(String key, String cod, List<String> list);

    /**
     * Recolhe dados acerca de quanto cada cliente gastou.
     * @return Mapeamento de código de cliente para o total faturado.
     */
    Double tresMaioresCompradoresCadaFilial();

    /**
     * Calcula, para cads código de produto, quantos produtos o cliente comprou.
     * @param topProds Map que guarda os dados.
     */
    void getClientMostBoughtProducts(Map<String, Integer> topProds);

    /**
     * Calcula, para cada código de produto, a quantidade vendida, e o conjunto de clientes que o compraram.
     * @param prodInfo Estrutura que recebe dados da quantidade
     * @param clientsBought Estrutura que recebe dados dos clientes que compraram.
     * @param key Código de cliente.
     */
    void nMostSoldProducts(Map<String, Integer> prodInfo, Map<String, Set<String>> clientsBought, String key);

    /**
     * Calcula o conjunto de produtos que cada código de cliente comprou.
     * @param prodBought Map de código de cliente para conjunto de produtos que comprou.
     * @param codCliente Código de cliente.
     */
    void nMostDiverseClients(Map<String, Set<String>> prodBought, String codCliente);

    /**
     * Calcula para cada produto por quantos clientes foi comprado e quanta faturaçao originou.
     * @param codProd Código do produto.
     * @param key Código do cliente.
     * @param info Estrutura que vai armazenar os dados.
     */
    void nMostBuyers(String codProd, String key, Map<String, MyPair<Integer, Double>> info);
}
