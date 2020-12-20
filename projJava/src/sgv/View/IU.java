package sgv.View;

import sgv.Auxiliar.MyPair;
import sgv.ViewInterface;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IU implements ViewInterface {

    /**
     * {@inheritDoc}
     */
    public void printMenuInicial() throws IOException {

        System.out.println("0  -  Sair");
        System.out.println("1  -  Produtos nunca comprados ordenados alfabeticamente e o seu total");
        System.out.println("2  -  Número de vendas e clientes diferentes que compraram num dado mês e/ou filial a filial");
        System.out.println("3  -  Registos de um cliente para cada mês");
        System.out.println("4  -  Registos de um produto mês a mês");
        System.out.println("5  -  Lista de produtos que um cliente mais comprou");
        System.out.println("6  -  N produtos mais vendidos");
        System.out.println("7  -  Os 3 maiores compradores (em faturação) filial a filial");
        System.out.println("8  -  N clientes que compraram mais produtos diferentes");
        System.out.println("9  -  N clientes que mais compraram um dado produto");
        System.out.println("10 -  Faturação com cada produto mês a mês e filial a filial");
        System.out.println("11 -  Consulta Estatística");
        System.out.println("12 - Gravar estado");
    }

    /**
     * {@inheritDoc}
     */
    public void printMenuEstatistico() {
        System.out.println("0  -  Sair");
        System.out.println("1  -  Nome do ficheiro de vendas atual");
        System.out.println("2  -  Numero total de registos de venda errados");
        System.out.println("3  -  Numero total de produtos");
        System.out.println("4  -  Numero total de produtos diferentes comprados");
        System.out.println("5  -  Numero total de produtos nao comprados");
        System.out.println("6  -  Numero total de clientes");
        System.out.println("7  -  Numero de clientes que compraram");
        System.out.println("8  -  Numero total de clientes que nao compraram nada");
        System.out.println("9  -  Numero total de compras no valor de zero");
        System.out.println("10 -  Faturacao total");
        System.out.println("11 -  Numero total de compras por mes");
        System.out.println("12 -  Faturacao total por mes");
        System.out.println("13 -  Numero de clientes distintos que compraram em cada mes por filial");
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraCodCli() {
        System.out.print("Insira o código do cliente: ");
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraCodProd() {
        System.out.print("Insira o código do produto: ");
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraONmr() {
        System.out.print("Insira o número pretendido: ");
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraOMes() {
        System.out.print("Insira o mês pretendido: ");
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraLimite() {
        System.out.print("Insira o limite pretendido: ");
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraPagina() {
        System.out.print("Insira a página: ");
    }

    /**
     * {@inheritDoc}
     */
    public void printProdutosNuncaComprados(Set<String> l, int page, int maxpage,String prod) {
        int perpage = 15;
        int iterar = 0;
        int contador=0;
        boolean found=false;
        System.out.println("O número total de produtos não comprados é " + l.size() + ".");
        System.out.println("Os códigos dos produtos nunca comprados são:");
        if(!prod.isEmpty()) {
            for(String s : l){
                if(s.equals(prod)) {
                    page=contador / perpage;
                    found=true;
                }
                contador++;
            }
            if(!found){
                System.out.println("Produto nao encontrado");
            }
        }
        int i = page * perpage;
        for (String s : l) {
            if (iterar++ >= i) {
                if (i < perpage * (page + 1) && i++ < l.size())
                    System.out.println(s);
            }
        }
        System.out.println("Pagina " + (page+1) +" de " + maxpage);
    }

    /**
     * {@inheritDoc}
     */
    public void printProfitProd(Map<String, double[][]> m,int page,String prod) {
        int iterar = 0;
        int contador=0;
        boolean found=false;
        if (prod != null) {
            for (Map.Entry<String, double[][]> f : m.entrySet()) {
                if (f.getKey().equals(prod)) {
                    page = contador;
                    found=true;
                }
                contador++;
            }
            if(!found)
                System.out.println("Produto nao encontrado");
        }
        for (Map.Entry<String, double[][]> f : m.entrySet()) {
            if ((iterar++) == page) {
                System.out.println("Para o produto " + f.getKey() + " a faturação: ");
                System.out.println("Filial    1    2    3");
                for (int mes = 0; mes < 12; mes++) {
                    System.out.print("Mês " + (mes + 1) + "  ");
                    for (int filial = 0; filial < 3; filial++) {
                        System.out.print(f.getValue()[mes][filial] + "  ");
                    }
                    System.out.println();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printOpcaoFilial() {
        System.out.println("1- Valores globais");
        System.out.println("2- Valores filial a filial");
    }

    /**
     * {@inheritDoc}
     */
    public void printFicheiroVendas(String s) {
        System.out.println("Ficheiro atual : " + s);
    }

    /**
     * {@inheritDoc}
     */
    public void printNmrRgVendasErrado(int total) {
        System.out.println("Numero registos de vendas erradas: " + total);
    }

    /**
     * {@inheritDoc}
     */
    public void printNmrTotalProd(int total) {
        System.out.println("Numero total de produtos: " + total);
    }

    /**
     * {@inheritDoc}
     */
    public void printNmrProdDifComprados(int total) {
        System.out.println("Numero de produtos diferentes comprados: " + total);
    }

    /**
     * {@inheritDoc}
     */
    public void printNmrProdNComprados(int total) {
        System.out.println("Numero de produtos nao comprados: " + total);
    }

    /**
     * {@inheritDoc}
     */
    public void nmrTotalClientes(int total) {
        System.out.println("Numero total de clientes: " + total);

    }

    /**
     * {@inheritDoc}
     */
    public void printNmrCliNCompraram(int total) {
        System.out.println("Numero de clientes que nao compraram: " + total);
    }

    /**
     * {@inheritDoc}
     */
    public void nmrTotalCompras0(int total) {
        System.out.println("Numero compras com valor zero: " + total);
    }

    /**
     * {@inheritDoc}
     */
    public void printGlobalIncome(double income) {
        System.out.println("Faturacao global: " + income);
    }

    /**
     * {@inheritDoc}
     */
    public void printNmrTotalComprasMes(Map<Integer, List<Integer>> integerListMap,int filial) {
        for (Map.Entry<Integer, List<Integer>> f : integerListMap.entrySet()) {
            if (f.getKey() == filial) {
                int mes = 1;
                for (Integer i : f.getValue()) {
                    System.out.println("Na filial  " + f.getKey() + " no mes " + mes + " o numero total de compras foi : " + i);
                    mes++;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printFaturacaoTotalMes(Map<Integer, List<Double>> totalIncomeByMonth,int filial) {
        for (Map.Entry<Integer, List<Double>> f : totalIncomeByMonth.entrySet()) {
            if (f.getKey() == filial) {
                int mes = 1;
                for (Double i : f.getValue()) {
                    System.out.println("Na filial  " + f.getKey() + " no mes " + mes + " a faturação total foi : " + i);
                    mes++;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printTotalClientsBoughtMonth(Map<Integer, List<Integer>> map, int filial){
        for (Map.Entry<Integer, List<Integer>> f : map.entrySet()) {
            if (f.getKey() == filial) {
                int mes = 1;
                for (Integer i : f.getValue()) {
                    System.out.println("Na filial  " + f.getKey() + " no mês " + mes + " o número de clientes que compraram foi: " + i);
                    mes++;
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraOnmrDeClientesDesejado() {
        System.out.println("Insira o numero de clientes que deseja: ");
    }

    /**
     * {@inheritDoc}
     */
    public void printNClientesMaisCompradores(Map<String, MyPair<Integer, Double>> clientes, String prod, int n) {
        int tamanho = clientes.size();
        System.out.println("Os " + n + " clientes que mais compraram o produto " + prod + " foram :");

        for (Map.Entry<String, MyPair<Integer, Double>> map : clientes.entrySet()) {
            System.out.println(" " + map.getKey() + " comprou " + map.getValue().getFirst() + " unidades e gastou " + map.getValue().getSecond() + "$");

        }
    }

    /**
     * {@inheritDoc}
     */

    public void printListMap(List<Map<String, Double>> list) {

        int i = 1;
        for (Map<String, Double> map : list) {
            System.out.println("Para a filial " + i);
            i++;
            for (Map.Entry<String, Double> keys : map.entrySet()) {
                System.out.println(keys.getKey() + " gastou " + keys.getValue());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printProdMaisVendidos(Set<Map.Entry<String, MyPair<Integer, Set<String>>>> prodMaisVendidos) {
        System.out.println("Os produtos mais vendidos foram : ");
        for (Map.Entry<String, MyPair<Integer, Set<String>>> m : prodMaisVendidos) {
            System.out.println(m.getKey() + " por " + m.getValue().getSecond().size() + " clientes " + m.getValue().getFirst() + " vezes");
        }
    }

    /**
     * {@inheritDoc}
     */

    public void printTotalSalesAndClientsMonth(int[] vendasPorMes) {
        System.out.println("Numero total de vendas:" + vendasPorMes[0]);
        System.out.println("Numero total de clientes:" + vendasPorMes[1]);
    }

    /**
     * {@inheritDoc}
     */
    public void printTotalSalesAndClientsMonthByBranch(List<int[]> vendasPorMes) {
        int filial = 1;
        for (int[] i : vendasPorMes) {
            System.out.println("Na filial " + filial++);
            printTotalSalesAndClientsMonth(i);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printClientSalesAndWasted(List<Double[]> clientSalesAndWasted, int mes) {
        int it = 0;
        int max = mes + 2;
        int atual = mes;
        for (Double[] i : clientSalesAndWasted) {
            if (it >= atual && it <= max) {
                System.out.println("No mes " + ++mes);
                System.out.println("Numero total de compras:" + i[0]);
                System.out.println("Numero produtos distintos:" + i[1]);
                System.out.println("Gasto total:" + i[2]);
            }
            it++;
        }

    }

    /**
     * {@inheritDoc}
     */
    public void printProductSalesAndWasted(List<Double[]> productSalesAndWasted, int mes) {
        int it = 0;
        int max = mes + 2;
        int atual = mes;
        for (Double[] i : productSalesAndWasted) {
            if (it >= atual && it <= max) {
                System.out.println("No mes " + ++mes);
                System.out.println("Numero total de compras:" + i[0]);
                System.out.println("Numero de clientes distintos:" + i[1]);
                System.out.println("Faturacao total do produto:" + i[2]);
            }
            it++;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printClientMostBoughtProducts(Map<Integer, Set<String>> clientMostBoughtProducts, int page,String prod) {
        int perpage = 15;
        int iterar = 0;
        int contador =0;
        boolean found =false;
        if(!prod.isEmpty()) {
            for(Map.Entry<Integer,Set<String>> s : clientMostBoughtProducts.entrySet()) {
                for (String str : s.getValue())
                    if (str.equals(prod)) {
                        page = contador / perpage;
                        found = true;
                        break;
                    }
                contador++;
            }
            if(!found)
                System.out.println("Produto nao encontrado");
        }
        int i = page * perpage;
        for (Map.Entry<Integer, Set<String>> map : clientMostBoughtProducts.entrySet()) {
            if (iterar++ >= i) {
                if (i < perpage * (page + 1) && i++ < clientMostBoughtProducts.size())
                    System.out.println("Quantidade: " + map.getKey() + " Produto: " + map.getValue());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printNMostSoldProducts(Map<Integer, List<MyPair<String, Integer>>> nMostSoldProducts, int page, int limite, String prod) {
        int perpage = 5;
        int iterar = 0;
        int contador=0;
        boolean found = false;
        if(!prod.isEmpty()){
            for(Map.Entry<Integer,List<MyPair<String, Integer>>> map: nMostSoldProducts.entrySet()) {
                for (MyPair<String, Integer> par : map.getValue()) {
                    if (par.getFirst().equals(prod)) {
                        page = contador / perpage;
                        found =true;
                    }
                    contador++;
                }
            }
            if(!found)
                System.out.println("Produto nao encontrado");
        }
        int i = page * perpage;
        for (Map.Entry<Integer, List<MyPair<String, Integer>>> map : nMostSoldProducts.entrySet()) {
            if (iterar++ >= i) {
                if (i < perpage * (page + 1) && i++ < nMostSoldProducts.size()) {
                    System.out.println("Quantidade: " + map.getKey());
                    printListMyPair(map.getValue());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printListMyPair(List<MyPair<String, Integer>> list) {
        System.out.println(" Código Produto: " + list.get(0).getFirst() + " Número de clientes distintos: " + list.get(0).getSecond());
    }

    /**
     * {@inheritDoc}
     */
    public void printNMostDiverseClients(Map<Integer, List<String>> nMostDiverseClients, int limite,int page,String cli) {
        int perpage = 5;
        int iterar = 0;
        int contador=0;
        boolean found = false;
        if(!cli.isEmpty()) {
            for (Map.Entry<Integer, List<String>> map : nMostDiverseClients.entrySet()) {
                for (String s : map.getValue()) {
                    if (s.equals(cli)) {
                        page = contador / perpage;
                        found = true;
                    }
                    contador++;
                }
            }
            if(!found)
                System.out.println("Cliente nao encontrado");
        }
        int inf = page * perpage;
        int sup = inf + perpage;
        for (Map.Entry<Integer, List<String>> map : nMostDiverseClients.entrySet()) {
            for (String s : map.getValue()) {
                if(iterar>=inf && iterar<sup)
                    System.out.println("Código do cliente: " + s + " Quantidade: " + map.getKey());
                iterar++;
            }
            if(iterar>=sup) break;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printNMostBuyers(Map<Integer, Map<String, Double>> nMostBuyers, String prod, int page,String cli) {
        int perpage = 5;
        int iterar = 0;
        int contador=0;
        boolean found = false;
        if(!cli.isEmpty()) {
            for (Map.Entry<Integer, Map<String, Double>> map : nMostBuyers.entrySet()) {
                for (Map.Entry<String, Double> m : map.getValue().entrySet()) {
                    if (m.getKey().equals(cli)) {
                        page = contador / perpage;
                        found = true;
                    }
                    contador++;
                }
            }
            if(!found)
                System.out.println("Cliente nao encontrado");
        }
        int i = page * perpage;
        System.out.println("Os clientes que mais compraram o produto " + prod + " foram: ");
        for (Map.Entry<Integer, Map<String, Double>> map : nMostBuyers.entrySet()) {
            if (iterar++ >= i) {
                if (i < perpage * (page + 1) && i++ < nMostBuyers.size()) {
                    System.out.print("Quantidade: " + map.getKey());
                    printAuxNMostBuyers(map.getValue());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printAuxNMostBuyers(Map<String,Double> map) {
        for(Map.Entry<String,Double> i : map.entrySet()){
            System.out.println(" Cliente: " + i.getKey() + " Gasto: " + i.getValue());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void printNmrPaginas(int page, int maxpage){
        System.out.println("Pagina "+(page+1) + " de "+maxpage);
    }

    /**
     * {@inheritDoc}
     */
    public void printControladorPaginacaoProduto(){
        System.out.println("0-Sair  1- Página anterior  2- Página seguinte  3- Escolher uma página 4-Escolher código do Produto");
    }

    /**
     * {@inheritDoc}
     */
    public void printControladorPaginacao(){
        System.out.println("0-Sair  1- Página anterior  2- Página seguinte  3- Escolher uma página");
    }

    /**
     * {@inheritDoc}
     */
    public void printControladorPaginacaoCliente(){
        System.out.println("0-Sair  1- Página anterior  2- Página seguinte  3- Escolher uma página 4-Escolher código do Cliente");
    }

    /**
     * {@inheritDoc}
     */
    public void printControladorPaginacaoMeses(){
        System.out.println("0-Sair  1- Meses anteriores  2- Meses seguintes  3- Escolher um mês");
    }

    /**
     * {@inheritDoc}
     */
    public void printEnterToContinue(){
        System.out.println("Pressione Enter para continuar");
    }

    /**
     * {@inheritDoc}
     */
    public void printInputInvalido(){
        System.out.println("Input inválido");
    }

    /**
     * {@inheritDoc}
     */
    public void printClienteNaoExiste(String s){
        System.out.println("O cliente " + s + " não existe.");
    }

    /**
     * {@inheritDoc}
     */
    public void printCodInvalido(){
        System.out.println("O código que inseriu não é válido.");
    }

    /**
     * {@inheritDoc}
     */
    public void printProdutoNaoExiste(String s){
        System.out.println("O produto " + s + " não existe.");
    }

    /**
     * {@inheritDoc}
     */
    public void printOpcaoLoad(){
        System.out.println("Os ficheiros deverão estar na diretoria projJava");
        System.out.println("1- Ler os ficheiros");
        System.out.println("2- Estado gravado");
    }

    /**
     * {@inheritDoc}
     */
    public void printFileNotFound() {
        System.out.println("Ficheiro não existe.");
    }

    /**
     * {@inheritDoc}
     */
    public void printMesInvalido(){
        System.out.println("O mês escolhido é inválido");
    }

    /**
     * {@inheritDoc}
     */
    public void printPaginaInvalido(){
        System.out.println("A página escolhida é inválida");
    }

    /**
     * {@inheritDoc}
     */
    public void printNmrCliDifComprados(int total){
        System.out.println("O número de clientes diferentes que compraram é " + total);
    }

    /**
     * {@inheritDoc}
     */
    public void printInsiraAFilial(){
        System.out.println("Insira a filial");
    }

    /**
     * {@inheritDoc}
     */
    public void printControladorPaginacaoFilial(){
        System.out.println("0-Sair  1- Filial anterior  2- Filial seguinte  3- Escolher uma filial");
    }
}

