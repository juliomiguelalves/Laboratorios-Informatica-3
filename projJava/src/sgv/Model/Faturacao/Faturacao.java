package sgv.Model.Faturacao;

import java.util.*;
import java.lang.Comparable;

/**
 * Classe que contém dados sobre a faturação total do sistema.
 */
public class Faturacao implements FaturacaoI{
    private Map<String, FaturacaoFilialI> faturacao;
    private Set<String> clientsBought;
    private Set<String> productsBought;
    private double income;

    public Faturacao() {
        this.faturacao = new HashMap<>();
        this.clientsBought = new HashSet<>();
        this.productsBought = new HashSet<>();
        this.income = 0;
    }

    public Faturacao(FaturacaoI f) {
        this.faturacao = f.getFaturacao();
        this.clientsBought = f.getClientsBought();
        this.productsBought = f.getProductsBought();
        this.income = f.getIncome();
    }

    public Faturacao(List<String> l) {
        this.faturacao = new HashMap<>();
        for (String s : l)
            this.faturacao.put(s, new FaturacaoFilial());
        this.income = 0;
        this.productsBought = new HashSet<>();
        this.clientsBought = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {faturacao,clientsBought,productsBought,income});
    }

    /**
     * {@inheritDoc}
     */
    public double getIncome() {
        return income;
    }

    /**
     * {@inheritDoc}
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, FaturacaoFilialI> getFaturacao() {
        Map<String, FaturacaoFilialI> f = new HashMap<>();
        for (Map.Entry<String, FaturacaoFilialI> fat : this.faturacao.entrySet()) {
            f.put(fat.getKey(), fat.getValue().clone());
        }
        return f;
    }

    /**
     * {@inheritDoc}
     */
    public void setFaturacao(Map<String, FaturacaoFilialI> faturacao) {
        this.faturacao = new HashMap<>();
        faturacao.forEach((key, value) -> this.faturacao.put(key, value.clone()));
    }

    /**
     * {@inheritDoc}
     */
    public Set<String> getClientsBought() {
        return new HashSet<>(this.clientsBought);
    }

    /**
     * {@inheritDoc}
     */
    public void setClientsBought(Set<String> clientsBought) {
        this.clientsBought = new HashSet<>(clientsBought);
    }

    /**
     * {@inheritDoc}
     */
    public Set<String> getProductsBought() {
        return new HashSet<>(this.productsBought);
    }

    /**
     * {@inheritDoc}
     */
    public void setProductsBought(Set<String> productsBought) {
        this.productsBought = new HashSet<>(productsBought);
    }

    /**
     * {@inheritDoc}
     */
    public Faturacao clone() {
        return new Faturacao(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(String codProduto, double preco, int quantidade, char promocao, String codCliente,  int mes, int filial) {
        if (this.faturacao.containsKey(codProduto))
            this.faturacao.get(codProduto).add(preco, quantidade, promocao, mes,filial);
        else {
            FaturacaoFilialI fp = new FaturacaoFilial();
            fp.add(preco, quantidade, promocao, mes,filial);
            this.faturacao.put(codProduto, fp);
        }
        this.income += quantidade * preco;
        this.productsBought.add(codProduto);
        this.clientsBought.add(codCliente);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        for (FaturacaoFilialI a : this.faturacao.values())
            a.clear();
        this.faturacao.clear();
        this.income = 0;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, double[][]> profitProd() {
        Map<String, double[][]> profit = new HashMap<>();
        double[][] m = new double[12][3];
        int mes, filial;
        double n;
        for (Map.Entry<String, FaturacaoFilialI> f : this.faturacao.entrySet()) {
            profit.put(f.getKey(), new double[12][3]);
            for (mes = 0; mes < 12; mes++) {
                for (filial = 0; filial < 3; filial++) {
                    n = f.getValue().profitProd(mes, filial);
                    m = profit.get(f.getKey());
                    m[mes][filial] = n;
                    profit.replace(f.getKey(), m);
                }
            }
        }
        return profit;
    }

    /**
     * {@inheritDoc}
     */
    public int numberOfClientsBought() {
        return this.clientsBought.size();
    }

    /**
     * {@inheritDoc}
     */
    public int numberOfProductsBought() {
        return this.productsBought.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, List<Double>> getTotalIncomeByMonth() {
        Map<Integer, List<Double>> ret = new HashMap<>();
        ret.put(1,new ArrayList<>());
        ret.put(2,new ArrayList<>());
        ret.put(3,new ArrayList<>());
        for(int i = 0; i < 12; i++) {
            ret.get(1).add(0.0);
            ret.get(2).add(0.0);
            ret.get(3).add(0.0);
        }
        for(FaturacaoFilialI f: this.faturacao.values()) {
            f.getTotalIncomeByMonth(ret);
        }
        return ret;

    }
}

