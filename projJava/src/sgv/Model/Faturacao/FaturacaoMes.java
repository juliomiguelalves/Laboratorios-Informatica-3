package sgv.Model.Faturacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que contém dados acerca da faturação duma filial.
 */
public class FaturacaoMes implements FaturacaoMesI {
    private FaturacaoTipoI[] faturacaoMes;
    private double income;

    public FaturacaoMes(){
        this.faturacaoMes = new FaturacaoTipo[12];
        for(int i = 0; i < 12; i++)
            faturacaoMes[i] = new FaturacaoTipo();
        this.income = 0;
    }

    public FaturacaoMes(FaturacaoTipoI[] f, double income){
        this.faturacaoMes = new FaturacaoTipo[12];
        for(int i = 0; i < 12; i++){
            this.faturacaoMes[i] = f[i].clone();
        }
        this.income = income;
    }

    public FaturacaoMes(FaturacaoMesI f){
        this.faturacaoMes=f.getFaturacaoMes();
        this.income = f.getIncome();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {faturacaoMes,income});
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
    public FaturacaoTipoI[] getFaturacaoMes() {
        FaturacaoTipoI[] f = new FaturacaoTipo[12];
        for(int i = 0; i < 12; i++){
            f[i] = faturacaoMes[i].clone();
        }
        return f;
    }

    /**
     * {@inheritDoc}
     */
    public void setFaturacaoMes(FaturacaoTipoI[] f) {
        this.faturacaoMes = new FaturacaoTipo[12];
        for(int i = 0; i < 12; i++){
            this.faturacaoMes[i] = f[i].clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public FaturacaoMes clone(){
        return new FaturacaoMes(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(double preco, int quantidade, char promocao, int mes) {
        if(this.faturacaoMes[mes-1] == null)
            this.faturacaoMes[mes-1] = new FaturacaoTipo();
        this.faturacaoMes[mes-1].add(preco,quantidade,promocao);
        this.income += quantidade * preco;
    }

    /**
     * {@inheritDoc}
     */
    public void clear(){
        for(FaturacaoTipoI fm: this.faturacaoMes){
            if(fm != null)
                fm.clear();
        }
        Arrays.fill(this.faturacaoMes,null);
        this.income = 0;
    }

    /**
     * {@inheritDoc}
     */
    public double profitProd(int mes) {
        return this.faturacaoMes[mes].profitProd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Double> getTotalIncome() {
        List<Double> ret = new ArrayList<>();
        for(FaturacaoTipoI f: this.faturacaoMes)
            ret.add(f.getIncome());
        return ret;
    }
}
