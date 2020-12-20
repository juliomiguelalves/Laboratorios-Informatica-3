package sgv;

import sgv.Auxiliar.MyPair;
import sgv.Exceptions.*;
import sgv.Model.CatalogoClientes.CatalogoClientesI;
import sgv.Model.CatalogoProdutos.CatalogoProdutosI;
import sgv.Model.Faturacao.FaturacaoI;
import sgv.Model.Filiais.FiliaisI;
import sgv.Model.GestVendas;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface que aglomera classes que representa Modelos no sistema MVC.
 */
public interface ModelInterface extends Serializable {
    /**
     * Retorna dados referentes à faturação.
     * @return Dados.
     */
    FaturacaoI getFaturacao();

    /**
     * Altera dados referentes à faturação.
     * @param fat Dados novos.
     */
    void setFaturacao(FaturacaoI fat);

    /**
     * Retorna dados referentes ao catálogo de clientes.
     * @return Dados.
     */
    CatalogoClientesI getCatalogoClientes();

    /**
     * Altera dados referentes à faturação.
     * @param cc Dados novos.
     */
    void setCatalogoClientes(CatalogoClientesI cc);

    /**
     * Retorna dados referentes ao catálogo de produtos.
     * @return Dados.
     */
    CatalogoProdutosI getCatalogoProdutos();

    /**
     * Altera dados referentes ao catálogo de produtos.
     * @param cp Dados novos.
     */
    void setCatalogoProdutos(CatalogoProdutosI cp);

    /**
     * Retorna dados referentes às filiais.
     * @return Dados.
     */
    FiliaisI getFiliais();

    /**
     * Altera dados referentes ao catálogo de produtos.
     * @param f Dados novos.
     */
    void setFiliais(FiliaisI f);

    /**
     * Carrega dados de venda para o sistema.
     * @throws IOException Io.
     */
    void leituraFicheirosVenda() throws IOException;

    /**
     * Limpa a estrutura das filiais.
     */
    void clearFiliais();

    /**
     * Carrega todos os dados que o sistema necessita por default.
     * @throws IOException IO.
     * @throws InvalidPathException Caminho inválido.
     */
    void defaultLoad() throws IOException, InvalidPathException;

    /**
     * Retorna o nome do ficheiro que contém dados sobre as vendas.
     * @return Nome.
     */
    String getFileVendas();

    /**
     * Altera o nome do ficheiro que contém dados sobre as vendas.
     * @param fileVendas Nome novo.
     */
    void setFileVendas(String fileVendas);

    /**
     * Retorna o nome do ficheiro que contém dados sobre os produtos.
     * @return Nome.
     */
    String getFileProdutos();

    /**
     * Altera o nome do ficheiro que contém dados sobre os produtos.
     * @param fileProdutos Nome novo.
     */
    void setFileProdutos(String fileProdutos);

    /**
     * Retorna o nome do ficheiro que contém dados sobre os clientes.
     * @return Nome.
     */
    String getFileClientes();

    /**
     * Altera o nome do ficheiro que contém dados sobre os clientes.
     * @param fileClientes Nome do ficheiro novo.
     */
    void setFileClientes(String fileClientes);

    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    ModelInterface clone();

    /**
     * Devolve o número de vendas inválidas.
     * @return Número.
     */
    int numberInvalidSales();

    /**
     * Devolve o número de clientes que realizaram compras.
     * @return Número.
     */
    int nmrDifCliBought();

    /**
     * Devolve o número total de produtos no sistema.
     * @return Número.
     */
    int numberOfProducts();

    /**
     * Devolve o número de produtos nunca comprados.
     * @return Número.
     */
    int nmrProductsNeverBought();

    /**
     * Devolve o número de produtos diferentes comprados.
     * @return Número.
     */
    int nmrDifProdBought();

    /**
     * Devolve o número de clientes no sistema.
     * @return Número.
     */
    int numberOfClients();

    /**
     * Devolve o número de clientes não realizaram compras.
     * @return Número.
     */
    int nmrClientsNeverBought();

    /**
     * Devolve número de vendas a custo 0 do sistema.
     * @return Número.
     */
    int cost0Sales();

    /**
     * Devolve a faturação global do sistema.
     * @return Faturação.
     */
    double globalIncome();

    /**
     * Calcula as quantidades de vendas por filial e por mês.
     * @return Matriz com quantidades de compras.
     */
    Map<Integer,List<Integer>> totalSalesByMonth();

    /**
     * Calcula a faturação das vendas por filial e por mês.
     * @return Matrizes com faturação.
     */
    Map<Integer,List<Double>> totalIncomeByMonth();

    /**
     * Calcula o total de clientes que compraram em cada mês e em cada filial.
     * @return Matriz com total clientes.
     */
    Map<Integer,List<Integer>> totalClientsBoughtByMonth();

    /**
     * Retorna um mapeamento de Código de produto para uma matriz de faturação por mês e filial do produto.
     * @return Mapeamento mencionado.
     */
    Map<String,double[][]> profitProd();

    /**
     * Devolve os códigos de produtos nunca comprados ordenados.
     * @return Conjunto de códigos de produto.
     */
    Set<String> neverBoughtProductsSorted();

    /**
     * Método que retorna um array de duas posições, na primeira posição contém o número de vendas realizada num determinado mês
     * e na segunda posição contém o número de clientes distintos que as fizeram.
     * @param month Mês em causa.
     * @return Array de tamanho 2.
     */
    int[] totalSalesAndClientsMonth(int month);

    /**
     * Calcula o número de vendas realizadas e o número de clientes que compraram filial a filial num determinado mês.
     * @param month Mês em conta.
     * @return Lista de 3 arrays de inteiros em que a primeira posição corresponde ao numero de vendas e a segunda corresponde ao número de clientes.
     */
    List<int[]> totalSalesAndClientsMonthByBranch(int month);

    /**
     * Devolve uma lista de três maps ordenados pelo valor, a que cada código de cliente corresponde ao dinheiro gasto.
     * Cada elemento da lista corresponde a uma filial e o map contem os três maiores compradores da filial.
     * @return Lista dos três maiores compradores.
     */
    List<Map<String,Double>> tresMaioresCompradoresCadaFilial();

    /**
     * Método que devolve uma matriz de 12 x 3 em que cada linha corresponde a dados correspondentes a um mês e nas colunas respetivamente
     * corresponde quantas compras o cliente fez, quantos produtos diferentes comprou e quanto gastou no total.
     * @param cod Código do cliente.
     * @return Matriz de valores.
     * @throws CodClienteInvalidoException Código de cliente inválido.
     * @throws ClienteNaoExistenteException Cliente não existe.
     */
    List<Double[]> clientSalesAndWasted(String cod) throws CodClienteInvalidoException, ClienteNaoExistenteException;

    /**
     * Método que devolve uma matriz de 12 x 3 em que cada linha corresponde a dados correspondentes a um mês e nas colunas respetivamente
     * corresponde quantas vezes foi comprado, por quantos clientes foi comprado e o total faturado.
     * @param cod Código do cliente.
     * @return Matriz de valores.
     * @throws ProdutoNaoExistenteException Produto não existe.
     * @throws  CodProdutoInvalidoException Código produto invalido.
     */
    List<Double[]> productSalesAndWasted(String cod) throws ProdutoNaoExistenteException, CodProdutoInvalidoException;

    /**
     * Calcula os produtos mais comprados por um dado cliente e devolve-os por ordem decrescente de quantidade, na forma de map.
     * @param cod Código do cliente.
     * @return Mapeamento de uma quantidade para uma lista de códigos de produtos.
     * @throws CodClienteInvalidoException Código de cliente inválido.
     * @throws ClienteNaoExistenteException Cliente não existe.
     */
    Map<Integer, Set<String>> clientMostBoughtProducts(String cod) throws CodClienteInvalidoException, ClienteNaoExistenteException;

    /**
     * Calcula o n produtos mais vendidos do ano.
     * @param limit O limite que se pretende.
     * @return Mapeamento de quantidade para lista de pares de códigos de produto e número de clientes que compraram.
     */
    Map<Integer, List<MyPair<String,Integer>>> nMostSoldProducts(int limit);

    /**
     * Calcula os n clientes que compraram mais produtos diferentes.
     * @param limit O limite que se pretende
     * @return Mapeamento de quantidades para lista de códigos de clientes.
     */
    Map<Integer,List<String>> nMostDiverseClients(int limit);

    /**
     * Calcula os n clientes que mais compraram um determinado produto.
     * @param limit O limite pretendido.
     * @param codProd Código do produto.
     * @return Mapeamento de quantidade para outro mapemanto entre código de cliente e valor gasto.
     * @throws ProdutoNaoExistenteException Produto não existe.
     * @throws CodProdutoInvalidoException Código de produto inválido.
     */
    Map<Integer,Map<String,Double>> nMostBuyers(int limit,String codProd) throws ProdutoNaoExistenteException, CodProdutoInvalidoException;

    /**
     * Salvaguarda os dados do sistema no ficheiro.
     * @throws IOException IO.
     */
    void saveObject() throws IOException;

    /**
     * Carrega os dados do sitema de um ficheiro previamente guardado.
     * @return Dados.
     * @throws IOException IO.
     * @throws ClassNotFoundException Classe não encontrada.
     */
    ModelInterface loadObject() throws IOException, ClassNotFoundException;
}
