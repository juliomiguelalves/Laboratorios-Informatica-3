package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface que aglomera classes que representa Filiais Clientes.
 */
public interface FiliaisClientesI extends Serializable {
    /**
     * Devolve a estrutura de dados do obejeto.
     * @return Estrutura de dados.
     */
    Map<String, FiliaisProdutosI> getFiliaisClientes();

    /**
     * Altera a estrutura de dados do objeto.
     * @param fcl Estrutura de dados nova.
     */
    void setFiliaisClientes(Map<String, FiliaisProdutosI> fcl);

    /**
     * Adiciona uma nova entrada à estrutura
     * @param codProduto Código do produto.
     * @param preco Preço da venda.
     * @param quantidade Quantidade vendida.
     * @param promocao Promoção.
     * @param codCliente Código do cliente.
     */
    void add(String codProduto, Double preco, int quantidade, char promocao, String codCliente);

    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    FiliaisClientesI clone();

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
     * Devolve o tamanho da estrutura.
     * @return Tamanho.
     */
    Integer size();

    /**
     * Método que devolve os valores de a partir de um cliente, quantas compras fez, quantos produtos comprou e o total faturado.
     * @param cod Código do cliente.
     * @return Array de 3 posições correspondentes aos 3 valores da descrição respetivamente.
     */
    Double[] getClientSalesAndWasted(String cod);

    /**
     * Método que devolve os valores de a partir de um produto, quantas vezes foi comprado, por quantos clientes foi comprado e o total faturado.
     * @param cod Código do cliente.
     * @param list Lista de códigos de clientes que compraram.
     * @return Array de 3 posições correspondentes aos 3 valores da descrição respetivamente.
     */
    Double[] getProductSalesAndWasted(String cod, List<String> list);

    /**
     * Recolhe dados acerca de quanto cada cliente gastou.
     * @return Mapeamento de código de cliente para o total faturado.
     */
    Map<String,Double> tresMaioresCompradoresCadaFilial();

    /**
     * Calcula, para cads código de produto, quantos produtos o cliente comprou.
     * @param cod Código do cliente.
     * @param topProds Map que guarda os dados.
     */
    void getClientMostBoughtProducts(String cod, Map<String, Integer> topProds);

    /**
     * Método que retorna um array de duas posições, na primeira posição contém o número de vendas realizada num determinado mês
     * e na segunda posição contém o número de clientes distintos que as fizeram.
     * @param clients Conjunto de códigos de clientes que compraram.
     * @return Array de tamanho 2.
     */
    int[] totalSalesAndClientsMonth(Set<String> clients);

    /**
     * Calcula, para cada código de produto, a quantidade vendida, e o conjunto de clientes que o compraram.
     * @param prodInfo Estrutura que recebe dados da quantidade
     * @param clientsBought Estrutura que recebe dados dos clientes que compraram.
     */
    void nMostSoldProducts(Map<String, Integer> prodInfo, Map<String, Set<String>> clientsBought);

    /**
     * Calcula o conjunto de produtos que cada código de cliente comprou.
     * @param prodBought Map de código de cliente para conjunto de produtos que comprou.
     */
    void nMostDiverseClients(Map<String, Set<String>> prodBought);

    /**
     * Calcula para cada produto por quantos clientes foi comprado e quanta faturaçao originou.
     * @param codProd Código do produto.
     * @param info Estrutura que vai armazenar os dados.
     */
    void nMostBuyers(String codProd, Map<String, MyPair<Integer, Double>> info);
}
