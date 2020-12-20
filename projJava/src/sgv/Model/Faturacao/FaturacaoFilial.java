package sgv.Model.Faturacao;

import java.util.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que contém os dados de faturação de um determinado produto.
 */
public class FaturacaoFilial implements FaturacaoFilialI {
    private FaturacaoMesI[] faturacaoFilial;
    private double income;

    public FaturacaoFilial(){
        this.faturacaoFilial = new FaturacaoMes[3];
        for(int i = 0; i < 3; i++)
            faturacaoFilial[i] = new FaturacaoMes();
        this.income = 0;
    }

    public FaturacaoFilial(FaturacaoMesI[] f, double income){
        this.faturacaoFilial = new FaturacaoMes[3];
        for(int i = 0; i < 3; i++){
            this.faturacaoFilial[i] = f[i].clone();
        }
        this.income = income;
    }

    public FaturacaoFilial(FaturacaoFilialI f){
        this.faturacaoFilial=f.getFaturacaoFilial();
        this.income = f.getIncome();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {faturacaoFilial,income});
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
    public FaturacaoMesI[] getFaturacaoFilial() {
        FaturacaoMesI[] f = new FaturacaoMes[3];
        for(int i = 0; i < 3; i++){
            f[i] = faturacaoFilial[i].clone();
        }
        return f;
    }

    /**
     * {@inheritDoc}
     */
    public void setFaturacaoFilial(FaturacaoMesI[] f) {
        this.faturacaoFilial = new FaturacaoMes[3];
        for(int i = 0; i < 3; i++){
            this.faturacaoFilial[i] = f[i].clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public FaturacaoFilial clone(){
        return new FaturacaoFilial(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(double preco, int quantidade, char promocao, int mes, int filial) {
        if(this.faturacaoFilial[filial-1] == null)
            this.faturacaoFilial[filial-1] = new FaturacaoMes();
        this.faturacaoFilial[filial-1].add(preco,quantidade,promocao,mes);
        this.income += quantidade * preco;
    }

    /**
     * {@inheritDoc}
     */
    public void clear(){
        for(FaturacaoMesI fm: this.faturacaoFilial){
            if(fm != null)
                fm.clear();
        }
        Arrays.fill(this.faturacaoFilial,null);
        this.income = 0;
    }

    /**
     * {@inheritDoc}
     */
    public double profitProd(int mes, int filial) {
        return this.faturacaoFilial[filial].profitProd(mes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getTotalIncomeByMonth(Map<Integer, List<Double>> ret) {
        int i = 1;
        for(FaturacaoMesI m : this.faturacaoFilial) {
            List<Double> l = m.getTotalIncome();
            addLists(ret.get(i),l);
            i++;
        }

    }

    /**
     * Adiciona duas listas e coloca o resultado na primeira lista dada como parâmetro. As Listas devem ser de igual tamanho.
     * @param dest Lista que fica com o resultado.
     * @param source Lista que vai somar.
     */
    private void addLists(List<Double> dest, List<Double> source) {
        double temp;
        int i;
        for(i = 0;i < dest.size();i++) {
            temp = dest.get(i);
            temp += source.get(i);
            dest.set(i,temp);
        }
    }

}

