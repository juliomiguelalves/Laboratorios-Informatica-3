package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.util.*;

/**
 * Classe que guarda dados relativos a vendas de um determinado mês.
 */
public class FiliaisClientes implements FiliaisClientesI {
    private Map<String, FiliaisProdutosI> filiaisClientes;
    private int numVendas;

    public FiliaisClientes() {
        this.filiaisClientes = new HashMap<>();
        this.numVendas = 0;
    }

    public FiliaisClientes(Map<String, FiliaisProdutosI> fc, int numVendas) {
        setFiliaisClientes(fc);
        this.numVendas = numVendas;
    }

    public FiliaisClientes(FiliaisClientesI fc) {
        this.filiaisClientes = new HashMap<>();
        for (Map.Entry<String, FiliaisProdutosI> m : fc.getFiliaisClientes().entrySet())
            this.filiaisClientes.put(m.getKey(), m.getValue().clone());
        this.numVendas = fc.getNumVendas();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {filiaisClientes,numVendas});
    }

    /**
     * {@inheritDoc}
     */
    public int getNumVendas() {
        return numVendas;
    }

    /**
     * {@inheritDoc}
     */
    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, FiliaisProdutosI> getFiliaisClientes() {
        Map<String, FiliaisProdutosI> aux = new HashMap<>();

        for (Map.Entry<String, FiliaisProdutosI> e : this.filiaisClientes.entrySet())
            aux.put(e.getKey(), e.getValue().clone());
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    public void setFiliaisClientes(Map<String, FiliaisProdutosI> fcl) {
        this.filiaisClientes = new HashMap<>();
        fcl.forEach((key, value) -> this.filiaisClientes.put(key, value.clone()));
    }

    /**
     * {@inheritDoc}
     */
    public void add(String codProduto, Double preco, int quantidade, char promocao, String codCliente) {

        if (this.filiaisClientes.containsKey(codCliente))
            this.filiaisClientes.get(codCliente).add(preco, quantidade, promocao, codProduto);
        else {
            FiliaisProdutosI fp = new FiliaisProdutos();
            fp.add(preco, quantidade, promocao, codProduto);
            this.filiaisClientes.put(codCliente, fp);
        }
        this.numVendas++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FiliaisClientes clone() {
        return new FiliaisClientes(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        for (FiliaisProdutosI a : this.filiaisClientes.values())
            a.clear();
        this.filiaisClientes.clear();
        this.numVendas = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer size() {
        return this.filiaisClientes.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double[] getClientSalesAndWasted(String cod) {
        if (this.filiaisClientes.containsKey(cod))
            return this.filiaisClientes.get(cod).getClientSalesAndWasted();
        else return new Double[]{0.0, 0.0, 0.0};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double[] getProductSalesAndWasted(String cod, List<String> list) {
        Double[] ret = new Double[]{0.0, 0.0, 0.0};
        for (Map.Entry<String, FiliaisProdutosI> me : this.filiaisClientes.entrySet())
            sumArray(ret, me.getValue().getProductSalesAndWasted(me.getKey(), cod, list), 3);
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getClientMostBoughtProducts(String cod, Map<String, Integer> topProds) {
        if(this.filiaisClientes.get(cod) != null) {
            this.filiaisClientes.get(cod).getClientMostBoughtProducts(topProds);
        }
    }

    /**
     * Função que soma dois arrays e coloca o resultado no primeiro array passado.
     * @param dest Array destino.
     * @param origin Array fonte.
     * @param size Tamanho dos arrays (têm de ter o mesmo tamanho).
     */
    private void sumArray(Double[] dest, Double[] origin, int size) {
        for (int i = 0; i < size; i++)
            dest[i] += origin[i];
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Double> tresMaioresCompradoresCadaFilial() {
        Map<String, Double> comprasCliente = new HashMap<>();
        for (Map.Entry<String, FiliaisProdutosI> map : this.filiaisClientes.entrySet()) {
            double gasto = map.getValue().tresMaioresCompradoresCadaFilial();
            if(comprasCliente.get(map.getKey())== null)
                comprasCliente.putIfAbsent(map.getKey(), gasto);
            else {
                comprasCliente.put(map.getKey(),comprasCliente.get(map.getKey())+gasto);
            }
        }
        return comprasCliente;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] totalSalesAndClientsMonth(Set<String> clients) {
        int numClientes = 0;
        for(String a : this.filiaisClientes.keySet()) {
            if(!clients.contains(a)) {
                numClientes++;
                clients.add(a);
            }
        }
        return new int[] {this.numVendas,numClientes};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostSoldProducts(Map<String, Integer> prodInfo, Map<String, Set<String>> clientsBought) {
        for(Map.Entry<String,FiliaisProdutosI> f : this.filiaisClientes.entrySet())
            f.getValue().nMostSoldProducts(prodInfo,clientsBought,f.getKey());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostDiverseClients(Map<String, Set<String>> prodBought) {
        for(Map.Entry<String,FiliaisProdutosI> f : this.filiaisClientes.entrySet())
            f.getValue().nMostDiverseClients(prodBought,f.getKey());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostBuyers(String codProd, Map<String, MyPair<Integer, Double>> info) {
        for(Map.Entry<String,FiliaisProdutosI> f : this.filiaisClientes.entrySet())
            f.getValue().nMostBuyers(codProd,f.getKey(),info);
    }

}

