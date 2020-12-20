package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que contém os dados relativos às aos clientes nas vendas.
 */
public class FiliaisProdutos implements FiliaisProdutosI {
    private Map<String,List<VendaI>> filiaisProdutos;
    private int numVendas;

    public FiliaisProdutos() {
        this.filiaisProdutos = new HashMap<>();
        this.numVendas = 0;
    }

    public FiliaisProdutos(Map<String,List<VendaI>> fp,int numVendas){
        setFiliaisProdutos(fp);
        this.numVendas = numVendas;
    }

    public FiliaisProdutos(FiliaisProdutosI fp) {
        this.filiaisProdutos = new HashMap<>();
        for(Map.Entry<String,List<VendaI>> lv : fp.getFiliaisProdutos().entrySet())
            this.filiaisProdutos.put(lv.getKey(),lv.getValue());
        this.numVendas = fp.getNumVendas();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {filiaisProdutos,numVendas});
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
    public Map<String,List<VendaI>> getFiliaisProdutos(){
        Map<String,List<VendaI>> aux = new HashMap<>();

        for(Map.Entry<String,List<VendaI>> e:   this.filiaisProdutos.entrySet())
            aux.put(e.getKey(), e.getValue().stream().map(VendaI::clone).collect(Collectors.toList()));
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    public void setFiliaisProdutos(Map<String,List<VendaI>> fpr){
        this.filiaisProdutos = new HashMap<>();
        fpr.forEach((key,value) -> {
            List<VendaI> lv = value.stream().map(VendaI::clone).collect(Collectors.toList());
            this.filiaisProdutos.put(key, lv);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FiliaisProdutos clone(){
        return new FiliaisProdutos(this);
    }

    /**
     * {@inheritDoc}
     */
    public void add(Double preco, int quantidade, char promocao,String codProduto){
        if(this.filiaisProdutos.containsKey(codProduto))
            this.filiaisProdutos.get(codProduto).add(new Venda(preco,quantidade,promocao));
        else{
            List<VendaI> lv = new ArrayList<>();
            VendaI v = new Venda(preco,quantidade,promocao);
            lv.add(v);
            this.filiaisProdutos.put(codProduto,lv);
        }
        numVendas++;
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        for(List<VendaI> a: this.filiaisProdutos.values())
            a.clear();
        this.filiaisProdutos.clear();
        this.numVendas = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double[] getClientSalesAndWasted() {
        double totalSales = 0;
        double wasted = 0;
        for(List<VendaI> v : this.filiaisProdutos.values()) {
            totalSales += v.size();
            for(VendaI venda : v)
                wasted += venda.getPrecoTotal();
        }
        return new Double[]{totalSales, (double) this.filiaisProdutos.size(), wasted};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double[] getProductSalesAndWasted(String codCliente, String codProduto, List<String> list) {
        double numBought = 0, clientBought = 0, totalIncome = 0;
        List<VendaI> v;
        if((v = this.filiaisProdutos.get(codProduto)) != null) {
            if(!list.contains(codCliente)) {
                list.add(codCliente);
                clientBought = 1;
            }
            numBought = v.size();
            for(VendaI venda : v)
                totalIncome = venda.getPrecoTotal();
        }
        return new Double[] {numBought,clientBought,totalIncome};
    }

    /**
     * {@inheritDoc}
     */
    public Double tresMaioresCompradoresCadaFilial() {
        double gasto = 0;
        for (Map.Entry<String, List<VendaI>> map : this.filiaisProdutos.entrySet()) {
            if (map.getValue() != null)
                for (VendaI v : map.getValue()) {
                    gasto += v.getPrecoTotal();
                }
        }
        return gasto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getClientMostBoughtProducts(Map<String, Integer> topProds) {
        Integer a;
        int temp = 0;
        for(Map.Entry<String,List<VendaI>> entry : this.filiaisProdutos.entrySet()) {
            for(VendaI v : entry.getValue())
                temp += v.getQuantidade();
            if((a = topProds.get(entry.getKey()))!= null) {
                topProds.replace(entry.getKey(),a+temp);
            }
            else {
                topProds.put(entry.getKey(),temp);
            }
            temp = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostSoldProducts(Map<String, Integer> prodInfo, Map<String, Set<String>> clientsBought, String codCli) {
        int quant = 0;
        int temp;
        for(Map.Entry<String,List<VendaI>> entry : this.filiaisProdutos.entrySet()) {
            if (clientsBought.get(entry.getKey()) == null) {
                Set<String> a = new HashSet<>();
                a.add(codCli);
                clientsBought.put(entry.getKey(), a);
            }
            else clientsBought.get(entry.getKey()).add(codCli);
            for (VendaI v : entry.getValue()) {
                quant += v.getQuantidade();
            }
            if(prodInfo.get(entry.getKey()) == null) {
                prodInfo.put(entry.getKey(),quant);
            }
            else {
                prodInfo.replace(entry.getKey(),prodInfo.get(entry.getKey()) + quant);
            }
            quant = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostDiverseClients(Map<String, Set<String>> prodBought, String codCliente) {
        Set<String> old;
        for(String s: this.filiaisProdutos.keySet()) {
            if((old = prodBought.get(codCliente)) != null) {
                old.add(s);
            }
            else {
                old = new HashSet<>();
                old.add(s);
                prodBought.put(codCliente,old);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nMostBuyers(String codProd, String key, Map<String, MyPair<Integer, Double>> info) {
        List<VendaI> old;
        int i = 0;
        double d = 0;
        MyPair<Integer,Double> p;
        MyPair<Integer,Double> oldd;
        if((old = this.filiaisProdutos.get(codProd)) != null) {
            for (VendaI v : old) {
                i += v.getQuantidade();
                d += v.getPrecoTotal();
            }
            p = MyPair.of(i,d);
            if((oldd = info.putIfAbsent(key,p)) != null) {
                oldd.setFirst(oldd.getFirst() + i);
                oldd.setSecond(oldd.getSecond() + d);
            }
        }
    }

}
