package sgv;

import sgv.Auxiliar.MyPair;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ViewInterface {
    /**
     * Print do menu inicial com as queries interativas e com uma opçao para entrar no menu de queries estatisticas
     * @throws IOException IO.
     */
    void printMenuInicial() throws IOException;

    /**
     * Print do menu com as queries estatisticas
     */
    void printMenuEstatistico();

    /**
     * Print que diz ao utilizador para indicar o codigo do cliente pretendido
     */
    void printInsiraCodCli();

    /**
     * Print que diz ao utilizador para indicar o codigo do produto pretendido
     */
    void printInsiraCodProd();

    /**
     * Print que diz ao utilizador para indicar o mes pretendido
     */
    void printInsiraOMes();

    /**
     * Print que diz ao utilizador para indicar o limite pretendido
     */
    void printInsiraLimite();

    /**
     * Print que diz ao utilizador para indicar a pagina pretendida
     */
    void printInsiraPagina();

    /**
     * Print de todos os produtos que nunca foram comprados ordenados alfabeticamente
     * @param l Set com todos os produtos que nunca foram comprados
     * @param page Pagina atual
     * @param maxpage Total de paginas
     * @param prod Produto que desejamos procurar
     */
    void printProdutosNuncaComprados(Set<String> l, int page, int maxpage,String prod);

    /**
     * Print das vendas de cada produto em cada filial e em cada mes
     * @param m Map com os produtos como key e com a faturacao em filial e mes como value
     * @param page Pagina atual
     * @param prod Produto que desejamos procurar
     */
    void printProfitProd(Map<String,double[][]> m,int page,String prod);

    /**
     * Print que diz ao utilizador para indicar a fiial pretendida
     */
    void printOpcaoFilial();

    /**
     * Print que indica qual o ficheiro de vendas atual
     * @param s Ficheiro de vendas
     */
    void printFicheiroVendas(String s);

    /**
     * Print que indica o numero total de registos de venda errados
     * @param total Numero de registos de vendas errados
     */
    void printNmrRgVendasErrado(int total);

    /**
     * Print que indica o numero total de produtos
     * @param total Numero de produtos
     */
    void printNmrTotalProd(int total);

    /**
     * Print que indica o numero total de produtos diferentes que foram comprados
     * @param total Numero de produtos
     */
    void printNmrProdDifComprados(int total);

    /**
     * Print que indica o numero total de produtos que nunca foram comprados
     * @param total Numero de produtos
     */
    void printNmrProdNComprados(int total);

    /**
     * Print que indica o numero total de clientes
     * @param total Numero de clientes
     */
    void nmrTotalClientes(int total);

    /**
     * Print que indica o numero total de clientes que nunca compraram
     * @param total Numero de clientes que nunca compraram
     */
    void printNmrCliNCompraram(int total);

    /**
     * Print que indica o numero total de compras com custo zero
     * @param total Numero total de compras com custo zero
     */
    void nmrTotalCompras0(int total);

    /**
     * Print da faturaçao total
     * @param income Faturaçao total
     */
    void printGlobalIncome(double income);

    /**
     * Print do numero total de compras em cada mes por filial.
     * @param integerListMap map com a filial como key e um list com uma posicao para cada mes como value.
     * @param filial Filial que desejamos procurar.
     */
    void printNmrTotalComprasMes(Map<Integer, List<Integer>> integerListMap,int filial);

    /**
     * Print do numero total de faturacao em cada mes por filial.
     * @param totalIncomeByMonth map com a filial como key e um list com uma posicao para cada mes como value.
     * @param filial Filial que desejamos procurar.
     */
    void printFaturacaoTotalMes(Map<Integer, List<Double>> totalIncomeByMonth,int filial);

    /**
     * Print que diz ao utilizador para indicar quantos clientes deseja que sejam apresentados.
     */
    void printInsiraOnmrDeClientesDesejado();

    /**
     * Print de uma List que tenha um map com string como key e double como value como elemento.
     * @param list List com map como elemento.
     */
    void printListMap(List<Map<String,Double>> list);

    /**
     * Print de um array com 2 posicoes com o numero de vendas na primeira posicao e com o numero de clientes na segunda posicao.
     * @param vendasPorMes Array com uma posicao para o numero de vendas e outra para o numero de clientes.
     */
    void printTotalSalesAndClientsMonth(int[] vendasPorMes);

    /**
     * List com um array de 2 posiçoes (uma para o numero de vendas e outra para o numero de clientes) como elemento.
     * @param vendasPorMes List como o numero de vendas e numero de clientes para cada filial.
     */
    void printTotalSalesAndClientsMonthByBranch(List<int[]> vendasPorMes);

    /**
     * Print do numero de clientes e faturacao para cada filial.
     * @param clientSalesAndWasted List como o numero de clientes e com a faturacao para cada mes.
     * @param mes Mes que desejamos procurar.
     */
    void printClientSalesAndWasted(List<Double[]> clientSalesAndWasted, int mes);
    /**
     * Print do numero de produtos e faturacao para cada filial.
     * @param productSalesAndWasted List como o numero de produtos e com a faturacao para cada mes.
     * @param mes Mes que desejamos procurar.
     */
    void printProductSalesAndWasted(List<Double[]> productSalesAndWasted, int mes);
    /**
     * Imprime os produtos mais vendidos a um certo cliente.
     * @param clientMostBoughtProducts mapeamento em que a chave é a quantidade e o value é um Set de códigos de produtos.
     * @param page inteiro que representa a página.
     * @param prod código do produto.
     */
    void printClientMostBoughtProducts(Map<Integer, Set<String>> clientMostBoughtProducts, int page,String prod);
    /**
     * Imprime os produtos mais vendidos.
     * @param nMostSoldProducts mapeamento em que a chave é a quantidade e o value é um par de código de produto e número de clientes distintos.
     * @param page inteiro que representa a página.
     * @param limite inteiro que representa o número de produtos impressos.
     * @param prod código de produto.
     */
    void printNMostSoldProducts(Map<Integer, List<MyPair<String,Integer>>> nMostSoldProducts, int page, int limite,String prod);
    /**
     * Imprime os produtos mais vendidos a um certo cliente.
     * @param nMostDiverseClients mapeamento em que a chave é a quantidade e o value é uma lista de códigos de clientes.
     * @param page inteiro que representa a página
     * @param limite inteiro que representa o número de clientes impressos.
     * @param cli código do cliente
     */
    void printNMostDiverseClients(Map<Integer, List<String>> nMostDiverseClients,int limite,int page,String cli);
    /**
     * Imprime os clientes que mais compraram um produto.
     * @param nMostBuyers mapeamento em que a chave é a quantidade comprada e o valor é um mapeamento em que a chave é o código do cliente e o valor é o gasto.
     * @param prod código do produto.
     * @param page número da página
     * @param cli código do cliente
     */
    void printNMostBuyers(Map<Integer,Map<String,Double>> nMostBuyers,String prod,int page,String cli);
    /**
     * Imprime os clientes que mais compraram um produto.
     * @param map mapeamento em que a chave é o código do cliente e o valor é o gasto.
     */
    void printAuxNMostBuyers(Map<String,Double> map);
    /**
     * Imprime número de páginas.
     * @param page número da página.
     * @param maxpage número de páginas total
     */
    void printNmrPaginas(int page,int maxpage);
    /**
     * Imprime as opções de controlo de páginas.
     */
    void printControladorPaginacao();
    /**
     * Imprime as opções de controlo de página e procura de um produto.
     */
    void printControladorPaginacaoProduto();
    /**
     * Imprime as opções de controlo de página e procura de um cliente.
     */
    void printControladorPaginacaoCliente();
    /**
     * Imprime as opções de controlo de página e escolha de meses.
     */
    void printControladorPaginacaoMeses();
    /**
     * Print que pede ao utilizador para pressionar enter.
     */
    void printEnterToContinue();
    /**
     * Print que indica ao utilizador que input é inválido.
     */
    void printInputInvalido();
    /**
     * Print que indica ao utilizador que código de cliente não existe.
     * @param s código do cliente
     */
    void printClienteNaoExiste(String s);
    /**
     * Print que indica ao utilizador que código de produto é inválido.
     */
    void printCodInvalido();
    /**
     * Print que indica ao utilizador que código de produto não existe.
     * @param s código do produto
     */
    void printProdutoNaoExiste(String s);
    /**
     * Imprime as opções de carregamento de ficheiros.
     */
    void printOpcaoLoad();
    /**
     * Print que indica ao cliente que o ficheiro não foi encontrado
     */
    void printFileNotFound();
    /**
     * Print que indica ao utilizador que o mês que introduziu é inválido.
     */
    void printMesInvalido();
    /**
     * Print que indica ao utilizador que a página que introduziu é inválida.
     */
    void printPaginaInvalido();
    /**
     * Print que diz ao utilizador para indicar a filial pretendida.
     */
    void printInsiraAFilial();
    /**
     * Imprime o número de clientes diferentes que compraram.
     * @param total número de clientes que compraram.
     */
    void printNmrCliDifComprados(int total);
    /**
     * Print que mostra ao utilizador as opções de filial anterior ou seguinte ou escolher filial.
     */
    void printControladorPaginacaoFilial();
    /**
     * Imprime o número de clientes que compraram num determinado mês e numa determinada filial.
     * @param map Mapeamento em que a chave representa a filial e a lista associada contêm o número de clientes em casa mês
     * @param filial inteiro que representa a filial
     */
    void printTotalClientsBoughtMonth(Map<Integer, List<Integer>> map, int filial);
}
