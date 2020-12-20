package sgv.Model.Filiais;

import sgv.Auxiliar.MyPair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que contém dados acerca das vendas realizadas em cada filial.
 */
public class Filiais implements FiliaisI {
    private FiliaisMesI[] filiais;
    private int numVendas;
    private int cost0Sales;

    public Filiais() {
        this.filiais = new FiliaisMes[3];
        for(int i = 0; i < 3; i++)
            filiais[i] = new FiliaisMes();
        this.numVendas = 0;
        this.cost0Sales = 0;
    }

    public Filiais(FiliaisI f){
        this.filiais=f.getFiliais();
        this.numVendas = f.getNumVendas();
        this.cost0Sales = f.getCost0Sales();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {filiais,numVendas,cost0Sales});
    }

    /**
     * {@inheritDoc}
     */
    public int getCost0Sales() {
        return cost0Sales;
    }

    /**
     * {@inheritDoc}
     */
    public void setCost0Sales(int cost0Sales) {
        this.cost0Sales = cost0Sales;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    /**
     * {@inheritDoc}
     */
    public FiliaisMesI[] getFiliais() {
        FiliaisMesI[] aux = new FiliaisMes[3];
        for(int i = 0; i < 3; i++){
            aux[i] = filiais[i].clone();
        }
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    public void setFiliais(FiliaisMesI[] filiais) {
        for(int i = 0; i < 3; i++){
            this.filiais[i] = filiais[i].clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void add(String codProduto, Double preco, int quantidade, char promocao, String codCliente, int mes, int filial){
        if(this.filiais[filial-1] == null)
            this.filiais[filial-1] = new FiliaisMes();
        this.filiais[filial-1].add(codProduto,preco,quantidade,promocao,codCliente,mes);
        this.numVendas++;
        if(preco == 0) this.cost0Sales++;
    }

    /**
     * {@inheritDoc}
     */
    public Filiais clone(){
        return new Filiais(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear(){
        for(FiliaisMesI fm: this.filiais){
            if(fm != null)
                fm.clear();
        }
        Arrays.fill(this.filiais,null);
        this.numVendas = 0;
        this.cost0Sales = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, List<Integer>> getTotalSalesByMonth() {
        Map<Integer,List<Integer>> aux = new HashMap<>();
        int i = 1;
        for(FiliaisMesI m : this.filiais) {
            List<Integer> l = m.getTotalSalesMonth();
            aux.put(i,l);
            i++;
        }
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, List<Integer>> getTotalClientsBoughtByMonth() {
        Map<Integer, List<Integer>> aux = new HashMap<>();
        int i = 1;
        for (FiliaisMesI m : this.filiais) {
            aux.put(i, m.getTotalClientsBoughtMonth());
            i++;
        }
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<int[]> totalSalesAndClientsMonthByBranch(int month) {
        List<int[]> aux = new ArrayList<>();
        for(FiliaisMesI m : this.filiais) {
            aux.add(m.totalSalesAndClientsMonthByBranch(month));
        }
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, List<MyPair<String, Integer>>> nMostSoldProducts(int limit) {
        Map<String,Set<String>> clientsBought = new HashMap<>();
        Map<String,Integer> prodInfo = new HashMap<>();
        Map<Integer,List<MyPair<String,Integer>>> aux = new TreeMap<>(Collections.reverseOrder());
        for(FiliaisMesI m : this.filiais)
            m.nMostSoldProducts(prodInfo,clientsBought);
        Map<Integer,Set<String>> sortingTree = new TreeMap<>(Collections.reverseOrder());
        for(Map.Entry<String,Integer> entry : prodInfo.entrySet()) {
            sortingTree.putIfAbsent(entry.getValue(),new TreeSet<>());
            sortingTree.get(entry.getValue()).add(entry.getKey());
        }
        int count = 0;
        for(Map.Entry<Integer,Set<String>> entry : sortingTree.entrySet()) {
            for(String s : entry.getValue()) {
                MyPair<String,Integer> a = MyPair.of(s,clientsBought.get(s).size());
                if(aux.containsKey(entry.getKey())) {
                    aux.get(entry.getKey()).add(a);
                }
                else {
                    List<MyPair<String,Integer>> b = new ArrayList<>();
                    b.add(a);
                    aux.put(entry.getKey(),b);
                }
                count++;
                if(count == limit) break;
            }
            if(count == limit) break;
        }
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] totalSalesAndClientsMonth(int month) {
        int[] aux = {0,0};
        Set<String> clients = new HashSet<>();
        for(FiliaisMesI m : this.filiais) {
            sumArray(aux,m.totalSalesAndClientsMonth(month,clients),2);
        }
        return aux;
    }

    /**
     * Função que soma dois arrays e coloca o resultado no primeiro array passado.
     * @param dest Array destino.
     * @param origin Array fonte.
     * @param size Tamanho dos arrays (têm de ter o mesmo tamanho).
     */
    private void sumArray(int[] dest, int[] origin, int size) {
        for (int i = 0; i < size; i++)
            dest[i] += origin[i];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getClientSalesAndWasted(String cod, List<Double[]> aux) {
        for(FiliaisMesI m : this.filiais)
            m.getClientSalesAndWasted(cod,aux);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getProductSalesAndWasted(String cod, List<Double[]> aux) {
        List<String>[] clientsB = new List[12];
        for(int i = 0; i < 12; i++) {
            clientsB[i] = new ArrayList<>();
        }
        for(FiliaisMesI m : this.filiais)
            m.getProductSalesAndWasted(cod,aux,clientsB);
    }

    /**
     * {@inheritDoc}
     */
    public List<Map<String,Double>> tresMaioresCompradoresCadaFilial(){
        List<Map<String,Double>> listClientes = new LinkedList<>();
        Map<String,Double> filial1= this.filiais[0].tresMaioresCompradoresCadaFilial();
        Map<String,Double> filial2= this.filiais[1].tresMaioresCompradoresCadaFilial();
        Map<String,Double> filial3= this.filiais[2].tresMaioresCompradoresCadaFilial();
        filial1 = filial1.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        filial2 = filial2.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        filial3 = filial3.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        listClientes.add(filial1);
        listClientes.add(filial2);
        listClientes.add(filial3);
        return listClientes;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, Set<String>> getClientMostBoughtProducts(String cod) {
        Map<String,Integer> info = new TreeMap<>();
        for(FiliaisMesI m: this.filiais)
            m.getClientMostBoughtProducts(cod,info);
        Set<String> a;
        Map<Integer,Set<String>> topProds = new TreeMap<>(Collections.reverseOrder());
        for(Map.Entry<String,Integer> entry : info.entrySet()) {
            if((a = topProds.get(entry.getValue()))!=null) {
                a.add(entry.getKey());
            }
            else {
                a = new TreeSet<>();
                a.add(entry.getKey());
                topProds.put(entry.getValue(),a);
            }
        }
        return topProds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, List<String>> nMostDiverseClients(int limit) {
        Map<Integer,List<String>> aux = new TreeMap<>(Collections.reverseOrder());
        Map<String,Set<String>> prodBought = new TreeMap<>();
        Map<Integer,List<String>> ret = new TreeMap<>(Collections.reverseOrder());
        List<String> a;
        List<String> old;
        for(FiliaisMesI f: this.filiais) {
            f.nMostDiverseClients(prodBought);
        }
        for(Map.Entry<String,Set<String>> entry : prodBought.entrySet()) {
            a = new ArrayList<>();
            a.add(entry.getKey());
            if((old = aux.putIfAbsent(entry.getValue().size(),a)) != null)
                old.add(entry.getKey());
        }
        int count = 0;
        for(Map.Entry<Integer,List<String>> entry : aux.entrySet()) {
            for(String s : entry.getValue()) {
                a = new ArrayList<>();
                a.add(s);
                if ((old = ret.putIfAbsent(entry.getKey(), a)) != null)
                    old.add(s);
                count++;
                if(count == limit) break;
            }
            if(count == limit) break;
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Map<String, Double>> nMostBuyers(int limit, String codProd) {
        Map<String,MyPair<Integer,Double>> info = new TreeMap<>(Collections.reverseOrder());
        Map<Integer,Map<String,Double>> sortingTree = new TreeMap<>(Collections.reverseOrder());
        Map<Integer,Map<String,Double>> ret = new TreeMap<>(Collections.reverseOrder());
        Map<String,Double> a;
        Map<String,Double> old;
        for(FiliaisMesI f : this.filiais)
            f.nMostBuyers(codProd,info);
        for(Map.Entry<String,MyPair<Integer,Double>> entry : info.entrySet()) {
            a = new TreeMap<>();
            a.put(entry.getKey(),entry.getValue().getSecond());
            if((old = sortingTree.putIfAbsent(entry.getValue().getFirst(),a)) != null)
                old.put(entry.getKey(),entry.getValue().getSecond());
        }
        int count = 0;
        for(Map.Entry<Integer,Map<String,Double>> entry : sortingTree.entrySet()) {
            for(Map.Entry<String,Double> sub : entry.getValue().entrySet()) {
                a = new TreeMap<>();
                a.put(sub.getKey(),sub.getValue());
                if ((old = ret.putIfAbsent(entry.getKey(), a)) != null)
                    old.put(sub.getKey(),sub.getValue());
                count++;
                if(count == limit) break;
            }
            if(count == limit) break;
        }
        return ret;
    }

}
