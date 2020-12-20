package sgv.Model;

import sgv.Auxiliar.MyPair;
import sgv.Exceptions.*;
import sgv.Model.CatalogoClientes.CatalogoClientes;
import sgv.Model.CatalogoClientes.CatalogoClientesI;
import sgv.Model.CatalogoClientes.Cliente;
import sgv.Model.CatalogoClientes.ClienteI;
import sgv.Model.CatalogoProdutos.CatalogoProdutos;
import sgv.Model.CatalogoProdutos.CatalogoProdutosI;
import sgv.Model.CatalogoProdutos.Produto;
import sgv.Model.CatalogoProdutos.ProdutoI;
import sgv.Model.Faturacao.Faturacao;
import sgv.Model.Faturacao.FaturacaoI;
import sgv.Model.Filiais.Filiais;
import sgv.Model.Filiais.FiliaisI;
import sgv.ModelInterface;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe que faz parte do modelo principal que armazena todo o tipo de dados do sistema
 */
public class GestVendas implements ModelInterface {
    private FaturacaoI faturacao;
    private FiliaisI filiais;
    private CatalogoClientesI clientes;
    private CatalogoProdutosI produtos;
    private String fileVendas;
    private String fileProdutos;
    private String fileClientes;

    public GestVendas(FaturacaoI fat, FiliaisI filiais, CatalogoClientesI clientes, CatalogoProdutosI produtos) {
        this.faturacao = fat.clone();
        this.filiais = filiais.clone();
        this.clientes = clientes.clone();
        this.produtos = produtos.clone();
        this.fileVendas = "";
        this.fileProdutos = "";
        this.fileClientes = "";
    }

    public GestVendas(){
        this.faturacao = new Faturacao();
        this.filiais = new Filiais();
        this.clientes = new CatalogoClientes();
        this.produtos = new CatalogoProdutos();
        this.fileVendas = "";
        this.fileProdutos = "";
        this.fileClientes = "";
    }

    public GestVendas(GestVendas gv){
        this.faturacao = gv.getFaturacao();
        this.filiais = gv.getFiliais();
        this.clientes = gv.getCatalogoClientes();
        this.produtos = gv.getCatalogoProdutos();
        this.fileVendas = gv.getFileVendas();
        this.fileProdutos = gv.getFileProdutos();
        this.fileClientes = gv.getFileClientes();
    }

    /**
     * {@inheritDoc}
     */
    public String getFileVendas() {
        return fileVendas;
    }

    /**
     * {@inheritDoc}
     */
    public void setFileVendas(String fileVendas) {
        this.fileVendas = fileVendas;
    }

    /**
     * {@inheritDoc}
     */
    public String getFileProdutos() {
        return fileProdutos;
    }

    /**
     * {@inheritDoc}
     */
    public void setFileProdutos(String fileProdutos) {
        this.fileProdutos = fileProdutos;
    }

    /**
     * {@inheritDoc}
     */
    public String getFileClientes() {
        return fileClientes;
    }

    /**
     * {@inheritDoc}
     */
    public void setFileClientes(String fileClientes) {
        this.fileClientes = fileClientes;
    }

    /**
     * {@inheritDoc}
     */
    public FaturacaoI getFaturacao(){
        return this.faturacao.clone();
    }

    /**
     * {@inheritDoc}
     */
    public void setFaturacao(FaturacaoI fat){
        this.faturacao = fat.clone();
    }

    /**
     * {@inheritDoc}
     */
    public CatalogoClientesI getCatalogoClientes(){
        return this.clientes.clone();
    }

    /**
     * {@inheritDoc}
     */
    public void setCatalogoClientes(CatalogoClientesI cc){
        this.clientes = cc.clone();
    }

    /**
     * {@inheritDoc}
     */
    public CatalogoProdutosI getCatalogoProdutos(){
        return this.produtos.clone();
    }

    /**
     * {@inheritDoc}
     */
    public void setCatalogoProdutos(CatalogoProdutosI cp){
        this.produtos = cp.clone();
    }

    /**
     * {@inheritDoc}
     */
    public FiliaisI getFiliais(){
        return this.filiais.clone();
    }

    /**
     * {@inheritDoc}
     */
    public void setFiliais(FiliaisI f){
        this.filiais = f.clone();
    }

    /**
     * {@inheritDoc}
     */
    public GestVendas clone(){
        return new GestVendas(this);
    }

    /**
     * {@inheritDoc}
     */
    public void leituraFicheirosVenda() throws IOException{
        String line;
        String[] tok;
        FileReader vendasFR = new FileReader("../projJava/" + this.fileVendas);
        BufferedReader vendasBR = new BufferedReader(vendasFR);
        while ((line = vendasBR.readLine()) != null) {
            tok = line.split(" ");
            if (validaVendas(tok)) {
                this.filiais.add(tok[0], Double.parseDouble(tok[1]), Integer.parseInt(tok[2]), tok[3].charAt(0), tok[4], Integer.parseInt(tok[5]), Integer.parseInt(tok[6]));
                this.faturacao.add(tok[0], Double.parseDouble(tok[1]), Integer.parseInt(tok[2]), tok[3].charAt(0),tok[4], Integer.parseInt(tok[5]), Integer.parseInt(tok[6]));
            }
        }
        vendasBR.close();
        vendasFR.close();
    }

    /**
     * {@inheritDoc}
     */
    public void loadClients() throws IOException,InvalidPathException {
        Path path = Paths.get("../projJava/" + this.fileClientes);
        Stream<String> lines = Files.lines(path);
        List<ClienteI> list = lines.map(Cliente::new).collect(Collectors.toList());
        this.clientes = new CatalogoClientes(list);
        lines.close();
    }

    /**
     * {@inheritDoc}
     */
    public void loadProducts() throws IOException,InvalidPathException {
        Path path = Paths.get("../projJava/" + this.fileProdutos);
        Stream<String> lines = Files.lines(path);
        List<String> f = lines.collect(Collectors.toList());
        List<ProdutoI> list = f.stream().map(Produto::new).collect(Collectors.toList());
        this.produtos = new CatalogoProdutos(list);
        this.faturacao = new Faturacao(f);
        lines.close();
    }

    /**
     * Verifica se venda é válida.
     * @param tok array de parâmetros venda.
     * @return verdadeiro se venda válida, falso se venda inválida.
     */
    private boolean validaVendas(String[] tok){
        return( codProdutoValido(tok[0])
                && produtoExiste(tok [0])
                && Double.parseDouble(tok[1]) >= 0
                && Integer.parseInt(tok[2]) > 0
                && (tok[3].charAt(0) == 'P' || tok[3].charAt(0) =='N')
                && codClienteValido(tok[4])
                && clienteExiste(tok[4])
                && ((Integer.parseInt(tok[5])) > 0 && Integer.parseInt((tok[5])) <13)
                && (Integer.parseInt(tok[6])) >0 && (Integer.parseInt(tok[6])) <4 );
    }

    /**
     * Verifica se código de produto é válido.
     * @param produto código do produto.
     * @return verdadeiro se produto válido, falso se produto inválido.
     */
    private boolean codProdutoValido(String produto){
        return  produto.length()==6
                && Character.isLetter(produto.charAt(0))
                && Character.isLetter(produto.charAt(1))
                && Character.isDigit(produto.charAt(2))
                && Character.isDigit(produto.charAt(3))
                && Character.isDigit(produto.charAt(4))
                && Character.isDigit(produto.charAt(5));
    }

    /**
     * Verifica se produto existe.
     * @param produto código do produto.
     * @return verdadeiro se produto existe, falso se produto não existe.
     */
    private boolean produtoExiste(String produto){
        return produtos.search(produto);
    }

    /**
     * Verifica se código de cliente é válido.
     * @param cliente código do cliente.
     * @return verdadeiro se cliente válido, falso se cliente inválido.
     */
    private boolean codClienteValido(String cliente){
        return  cliente.length()==5
                && Character.isLetter(cliente.charAt(0))
                && Character.isDigit(cliente.charAt(1))
                && Character.isDigit(cliente.charAt(2))
                && Character.isDigit(cliente.charAt(3))
                && Character.isDigit(cliente.charAt(4));
    }

    /**
     * Verifica se cliente existe.
     * @param cliente código do cliente
     * @return verdadeiro se cliente existe, falso se cliente não existe.
     */
    private boolean clienteExiste(String cliente){
        return clientes.search(cliente);
    }

    /**
     * {@inheritDoc}
     */
    public void clearFiliais(){
            this.filiais.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void clearFaturacao(){
        this.faturacao.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void defaultLoad() throws IOException,InvalidPathException {
        FileReader vendasFR = new FileReader("../projJava/config.cfg");
        BufferedReader vendasBR = new BufferedReader(vendasFR);
        this.fileClientes = vendasBR.readLine();
        this.fileProdutos = vendasBR.readLine();
        this.fileVendas = vendasBR.readLine();
        loadClients();
        loadProducts();
        leituraFicheirosVenda();
    }

    /**
     * {@inheritDoc}
     */
    public int numberInvalidSales() {
        int total = 0;
        if(fileVendas.compareTo("Vendas_1M.txt") == 0) total = 1000000;
        else if(fileVendas.compareTo("Vendas_3M.txt") == 0) total = 3000000;
        else if(fileVendas.compareTo("Vendas_5M.txt") == 0) total = 5000000;
        return total-this.filiais.getNumVendas();
    }

    /**
     * {@inheritDoc}
     */
    public int numberOfProducts() {
        return this.produtos.getNumProdutos();
    }

    /**
     * {@inheritDoc}
     */
    public int nmrProductsNeverBought() {
        return this.produtos.getNumProdutos()-this.faturacao.numberOfProductsBought();
    }

    /**
     * {@inheritDoc}
     */
    public int nmrDifProdBought() {
        return this.faturacao.numberOfProductsBought();
    }

    /**
     * {@inheritDoc}
     */
    public int numberOfClients() {
        return this.clientes.getNumClientes();
    }

    /**
     * {@inheritDoc}
     */
    public int nmrClientsNeverBought() {
        return this.clientes.getNumClientes() - this.faturacao.numberOfClientsBought();
    }

    /**
     * {@inheritDoc}
     */
    public int nmrDifCliBought() {
        return this.faturacao.numberOfClientsBought();
    }

    /**
     * {@inheritDoc}
     */
    public int cost0Sales() {
        return this.filiais.getCost0Sales();
    }

    /**
     * {@inheritDoc}
     */
    public double globalIncome() {
        return this.faturacao.getIncome();
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer,List<Integer>> totalSalesByMonth() {
        return this.filiais.getTotalSalesByMonth();
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer,List<Double>> totalIncomeByMonth() {
        return this.faturacao.getTotalIncomeByMonth();
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer,List<Integer>> totalClientsBoughtByMonth() {
        return this.filiais.getTotalClientsBoughtByMonth();
    }

    /**
     * {@inheritDoc}
     */
    public void saveObject() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gestVendas.dat"));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public GestVendas loadObject() throws IOException, ClassNotFoundException, FileNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gestVendas.dat"));
        GestVendas g = (GestVendas) ois.readObject();
        ois.close();
        return g;
    }

    // 1 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public Set<String> neverBoughtProductsSorted() {
        Set<String> bought = this.faturacao.getProductsBought();
        return this.produtos.getProductsNeverBought(bought);
    }

    // 2 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public List<int[]> totalSalesAndClientsMonthByBranch(int month){
        return this.filiais.totalSalesAndClientsMonthByBranch(month-1);
    }

    /**
     * {@inheritDoc}
     */
    public int[] totalSalesAndClientsMonth(int month) {
        return this.filiais.totalSalesAndClientsMonth(month-1);
    }

    // 3 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public List<Double[]> clientSalesAndWasted(String cod) throws CodClienteInvalidoException, ClienteNaoExistenteException{
        if(!codClienteValido(cod)) throw new CodClienteInvalidoException(cod);
        if(!clienteExiste(cod)) throw new ClienteNaoExistenteException(cod);
        List<Double[]> ret = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            Double[] a = new Double[]{0.0,0.0,0.0};
            ret.add(a);
        }
        this.filiais.getClientSalesAndWasted(cod, ret);
        return ret;
    }

    // 4 QUERIE INTERATIVA RUBEN

    /**
     * {@inheritDoc}
     */
    public List<Double[]> productSalesAndWasted(String cod) throws ProdutoNaoExistenteException, CodProdutoInvalidoException{
        if(!codProdutoValido(cod)) throw new CodProdutoInvalidoException(cod);
        if(!produtoExiste(cod)) throw new ProdutoNaoExistenteException(cod);
        List<Double[]> ret = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            Double[] a = new Double[]{0.0,0.0,0.0};
            ret.add(a);
        }
        this.filiais.getProductSalesAndWasted(cod,ret);
        return ret;
    }


    // 5 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public Map<Integer, Set<String>> clientMostBoughtProducts(String cod) throws CodClienteInvalidoException, ClienteNaoExistenteException{
        if(!codClienteValido(cod)) throw new CodClienteInvalidoException(cod);
        if(!clienteExiste(cod)) throw new ClienteNaoExistenteException(cod);
        return this.filiais.getClientMostBoughtProducts(cod);
    }

    // 6 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public Map<Integer, List<MyPair<String,Integer>>> nMostSoldProducts(int limit) {
        return this.filiais.nMostSoldProducts(limit);
    }

    // 7 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public List<Map<String,Double>> tresMaioresCompradoresCadaFilial() {
        return this.filiais.tresMaioresCompradoresCadaFilial();
    }


    // 8 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public Map<Integer,List<String>> nMostDiverseClients(int limit) {
        return this.filiais.nMostDiverseClients(limit);
    }


    // 9 querie interativa

    /**
     * {@inheritDoc}
     */
    public Map<Integer,Map<String,Double>> nMostBuyers(int limit,String codProd) throws ProdutoNaoExistenteException, CodProdutoInvalidoException {
        if(!codProdutoValido(codProd)) throw new CodProdutoInvalidoException(codProd);
        if(!produtoExiste(codProd)) throw new ProdutoNaoExistenteException(codProd);
        return this.filiais.nMostBuyers(limit,codProd);
    }

    // 10 QUERIE INTERATIVA

    /**
     * {@inheritDoc}
     */
    public Map<String,double[][]> profitProd(){
        return this.faturacao.profitProd();
    }
}
