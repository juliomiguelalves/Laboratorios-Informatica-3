package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.util.*;

/**
 * Classe que armazena dados relativos a uma filial.
 */
public class FiliaisMes implements FiliaisMesI {
    private FiliaisClientesI[] filiaisClientes;
    private int numVendas;

    public FiliaisMes() {
        this.filiaisClientes = new FiliaisClientes[12];
        for (int i = 0; i < 12; i++) {
            filiaisClientes[i] = new FiliaisClientes();
        }
        this.numVendas = 0;
    }

    public FiliaisMes(FiliaisClientesI[] fc, int numVendas) {
        this.filiaisClientes = new FiliaisClientes[12];
        this.numVendas = 0;
        for (int i = 0; i < 12; i++) {
            filiaisClientes[i] = fc[i].clone();
        }
        this.numVendas = numVendas;
    }

    public FiliaisMes(FiliaisMesI a) {
        this.filiaisClientes = a.getFiliaisClientes();
        this.numVendas = a.getNumVendas();
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
    public FiliaisClientesI[] getFiliaisClientes() {
        FiliaisClientesI[] aux = new FiliaisClientes[12];
        for (int i = 0; i < 12; i++) {
            aux[i] = filiaisClientes[i].clone();
        }
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    public void setFiliaisClientes(FiliaisClientesI[] filiais) {
        for (int i = 0; i < 3; i++) {
            this.filiaisClientes[i] = filiais[i].clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void add(String codProduto, Double preco, int quantidade, char promocao, String codCliente, int mes) {
        if (this.filiaisClientes[mes - 1] == null) {
            this.filiaisClientes[mes - 1] = new FiliaisClientes();
        }
        this.filiaisClientes[mes - 1].add(codProduto, preco, quantidade, promocao, codCliente);
        this.numVendas++;
    }

    /**
     * {@inheritDoc}
     */
    public FiliaisMes clone() {
        return new FiliaisMes(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        for (FiliaisClientesI fc : this.filiaisClientes) {
            fc.clear();
        }
        Arrays.fill(this.filiaisClientes, null);
        this.numVendas = 0;
    }

    @Override
    public List<Integer> getTotalSalesMonth() {
        List<Integer> ret = new ArrayList<>();
        for (FiliaisClientesI f : this.filiaisClientes)
            ret.add(f.getNumVendas());
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getTotalClientsBoughtMonth() {
        List<Integer> l = new ArrayList<>();
        for (FiliaisClientesI c : this.filiaisClientes)
            l.add(c.size());
        return l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] totalSalesAndClientsMonth(int month,Set<String> clients) {
        return this.filiaisClientes[month].totalSalesAndClientsMonth(clients);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostSoldProducts(Map<String, Integer> prodInfo, Map<String, Set<String>> clientsBought) {
        for(FiliaisClientesI f: this.filiaisClientes)
            f.nMostSoldProducts(prodInfo,clientsBought);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostDiverseClients(Map<String, Set<String>> prodBought) {
        for(FiliaisClientesI f : this.filiaisClientes)
            f.nMostDiverseClients(prodBought);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostBuyers(String codProd, Map<String, MyPair<Integer, Double>> info) {
        for(FiliaisClientesI f : this.filiaisClientes)
            f.nMostBuyers(codProd,info);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] totalSalesAndClientsMonthByBranch(int month) {
        return new int[] {this.filiaisClientes[month].getNumVendas(), this.filiaisClientes[month].size()};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getClientSalesAndWasted(String cod, List<Double[]> ret) {
        int i;
        for (i = 0; i < 12; i++)
            sumArray(ret.get(i), this.filiaisClientes[i].getClientSalesAndWasted(cod), 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getProductSalesAndWasted(String cod, List<Double[]> ret, List<String>[] clientsB) {
        for (int i = 0; i < 12; i++)
            sumArray(ret.get(i), this.filiaisClientes[i].getProductSalesAndWasted(cod, clientsB[i]), 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getClientMostBoughtProducts(String cod, Map<String, Integer> topProds) {
        for(FiliaisClientesI f: this.filiaisClientes)
            f.getClientMostBoughtProducts(cod,topProds);
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
        Map<String, Double> mes = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            for (Map.Entry<String, Double> map : this.filiaisClientes[i].tresMaioresCompradoresCadaFilial().entrySet()) {
                if (mes.get(map.getKey()) == null)
                    mes.putIfAbsent(map.getKey(), map.getValue());
                else {
                    Double gasto = mes.get(map.getKey());
                    mes.replace(map.getKey(), map.getValue() + gasto);
                }
            }
        }
        return mes;
    }

}
