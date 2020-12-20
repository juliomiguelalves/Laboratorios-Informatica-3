package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface que aglomera classes que representa Filiais Mes.
 */
public interface FiliaisMesI extends Serializable {
    /**
     * Devolve os dados contidos no objeto.
     * @return Array de dados.
     */
    FiliaisClientesI[] getFiliaisClientes();

    /**
     * Altera os dados contidos no objeto.
     * @param filiais Array de dados novo.
     */
    void setFiliaisClientes(FiliaisClientesI[] filiais);

    /**
     * Adiciona uma nova entrada na estrutura de dados.
     * @param codProduto Código do produto.
     * @param preco Preço da venda.
     * @param quantidade Quantidade vendida.
     * @param promocao Promoção.
     * @param codCliente Código do cliente.
     * @param mes Mês registado.
     */
    void add(String codProduto, Double preco, int quantidade, char promocao, String codCliente, int mes);

    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    FiliaisMesI clone();

    /**
     * Retotna o número de vendas na filial.
     * @return Número de vendas.
     */
    int getNumVendas();

    /**
     * Altera o número de vendas na filial.
     * @param numVendas Número de vendas novo.
     */
    void setNumVendas(int numVendas);

    /**
     * Limpa a estrutura de dados.
     */
    void clear();

    /**
     * Obtém uma Lista de número de vendas por mês.
     * @return Lista de número de vendas.
     */
    List<Integer> getTotalSalesMonth();

    /**
     * Obtém uma Lista de número de clientes diferentes que compraram por mês.
     * @return Lista de número de compradores.
     */
    List<Integer> getTotalClientsBoughtMonth();

    /**
     * Calcula o número de vendas realizadas e o número de clientes que compraram num determinado mês.
     * @param month Mês em conta.
     * @return Array de inteiros em que a primeira posição corresponde ao numero de vendas e a segunda corresponde ao número de clientes.
     */
    int[] totalSalesAndClientsMonthByBranch(int month);

    /**
     * Método que devolve uma matriz de 12 x 3 em que cada linha corresponde a dados correspondentes a um mês e nas colunas respetivamente
     * corresponde quantas compras o cliente fez, quantos produtos diferentes comprou e quanto gastou no total.
     * @param cod Código do cliente.
     * @param ret Matriz.
     */
    void getClientSalesAndWasted(String cod, List<Double[]> ret);

    /**
     * Método que devolve uma matriz de 12 x 3 em que cada linha corresponde a dados correspondentes a um mês e nas colunas respetivamente
     * corresponde quantas vezes foi comprado, por quantos clientes foi comprado e o total faturado.
     * @param cod Código do cliente.
     * @param ret Matriz.
     * @param clientsB Lista de códigos de clientes que compraram.
     */
    void getProductSalesAndWasted(String cod, List<Double[]> ret, List<String>[] clientsB);

    /**
     * Devolve um Map a que cada código de cliente corresponde ao dinheiro gasto.
     * @return Map código para dinheiro gasto.
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
     * @param month Mês em causa.
     * @param clients Conjunto de códigos de clientes que compraram.
     * @return Array de tamanho 2.
     */
    int[] totalSalesAndClientsMonth(int month,Set<String> clients);

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
