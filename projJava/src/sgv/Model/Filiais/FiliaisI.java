package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface que aglomera classes que representa Filiais.
 */
public interface FiliaisI extends Serializable {
    /**
     * Devolve um array de dados das filiais.
     * @return Array de dados.
     */
    FiliaisMesI[] getFiliais();

    /**
     * Altera o array de dados das filiais.
     * @param filiais Array de dados novo.
     */
    void setFiliais(FiliaisMesI[] filiais);

    /**
     * Adiciona uma nova entrada na estrutura de dados.
     * @param codProduto Código de produto.
     * @param preco Preço da venda.
     * @param quantidade Quantidade vendida.
     * @param promocao Promoção.
     * @param codCliente Código de cliente.
     * @param mes Mês registado.
     * @param filial Filial registada.
     */
    void add(String codProduto, Double preco, int quantidade, char promocao, String codCliente, int mes, int filial);

    /**
     * Devolve uma cópia do objeto.
     * @return Cópia.
     */
    FiliaisI clone();

    /**
     * Limpa a estrutura de dados.
     */
    void clear();

    /**
     * Retorna o numero de vendas global.
     * @return Número de vendas.
     */
    int getNumVendas();

    /**
     * Altera o número de vendas global.
     * @param numVendas Número de vendas novo.
     */
    void setNumVendas(int numVendas);

    /**
     * Retorna o número de vendas a custo 0.
     * @return Número de vendas.
     */
    int getCost0Sales();

    /**
     * Altera o número de vendas a custo 0.
     * @param cost0Sales Número de vendas novo.
     */
    void setCost0Sales(int cost0Sales);

    /**
     * Obtém uma matriz de número de vendas por filial e por mês.
     * @return Matriz de número de vendas.
     */
    Map<Integer, List<Integer>> getTotalSalesByMonth();

    /**
     * Obtém uma matriz de número de clientes diferentes que compraram em cada mês, em cada filial.
     * @return Matriz de número de compradores.
     */
    Map<Integer, List<Integer>> getTotalClientsBoughtByMonth();

    /**
     * Método que retorna um array de duas posições, na primeira posição contém o número de vendas realizada num determinado mês
     * e na segunda posição contém o número de clientes distintos que as fizeram.
     * @param month Mês em causa.
     * @return Array de tamanho 2.
     */
    int[] totalSalesAndClientsMonth(int month);

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
     */
    void getProductSalesAndWasted(String cod, List<Double[]> ret);

    /**
     * Devolve uma lista de três maps ordenados pelo valor, a que cada código de cliente corresponde ao dinheiro gasto.
     * Cada elemento da lista corresponde a uma filial e o map contem os três maiores compradores da filial.
     * @return Lista dos três maiores compradores.
     */
    List<Map<String,Double>> tresMaioresCompradoresCadaFilial();

    /**
     * Calcula os produtos mais comprados por um dado cliente e devolve-os por ordem decrescente de quantidade, na forma de map.
     * @param cod Código do cliente.
     * @return Mapeamento de uma quantidade para uma lista de códigos de produtos.
     */
    Map<Integer, Set<String>> getClientMostBoughtProducts(String cod);

    /**
     * Calcula o número de vendas realizadas e o número de clientes que compraram filial a filial num determinado mês.
     * @param i Mês em conta.
     * @return Lista de 3 arrays de inteiros em que a primeira posição corresponde ao numero de vendas e a segunda corresponde ao número de clientes.
     */
    List<int[]> totalSalesAndClientsMonthByBranch(int i);

    /**
     * Calcula o n produtos mais vendidos do ano.
     * @param limit O limite que se pretende.
     * @return Mapeamento de quantidade para lista de pares de códigos de produto e número de clientes que compraram.
     */
    Map<Integer, List<MyPair<String, Integer>>> nMostSoldProducts(int limit);

    /**
     * Calcula os n clientes que compraram mais produtos diferentes.
     * @param limit O limite que se pretende
     * @return Mapeamento de quantidade para lista de códigos de cliente.
     */
    Map<Integer, List<String>> nMostDiverseClients(int limit);

    /**
     * Calcula os n clientes que mais compraram um determinado produto.
     * @param limit O limite pretendido.
     * @param codProd Código do produto.
     * @return Mapeamento de quantidade para outro mapemanto entre código de cliente e valor gasto.
     */
    Map<Integer, Map<String, Double>> nMostBuyers(int limit, String codProd);
}
