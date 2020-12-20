package sgv.Model.Faturacao;

import java.util.Arrays;

/**
 * Classe que contém dados acerca da faturação duma mês.
 */
public class FaturacaoTipo implements FaturacaoTipoI {
    private FaturacaoDadosI[] faturacaoTipo;
    private double income;

    public FaturacaoTipo(){
        this.faturacaoTipo = new FaturacaoDados[2];
        for(int i = 0; i < 2; i++)
            faturacaoTipo[i] = new FaturacaoDados();
        this.income = 0;
    }

    public FaturacaoTipo(FaturacaoDadosI[] f, double income){
        this.faturacaoTipo = new FaturacaoDados[2];
        for(int i = 0; i < 2; i++){
            this.faturacaoTipo[i] = f[i].clone();
        }
        this.income = income;
    }

    public FaturacaoTipo(FaturacaoTipoI f){
        this.faturacaoTipo=f.getFaturacaoTipo();
        this.income = f.getIncome();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {faturacaoTipo,income});
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
    public FaturacaoDadosI[] getFaturacaoTipo() {
        FaturacaoDadosI[] f = new FaturacaoDados[2];
        for(int i = 0; i < 2; i++){
            f[i] = faturacaoTipo[i].clone();
        }
        return f;
    }

    /**
     * {@inheritDoc}
     */
    public void setFaturacaoTipo(FaturacaoDadosI[] f) {
        this.faturacaoTipo = new FaturacaoDados[2];
        for(int i = 0; i < 2; i++){
            this.faturacaoTipo[i] = f[i].clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public FaturacaoTipo clone(){
        return new FaturacaoTipo(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(double preco, int quantidade, char promocao) {
        int i = 0;
        if (promocao == 'P') i = 1;
        if(this.faturacaoTipo[i] == null)
            this.faturacaoTipo[i] = new FaturacaoDados();
        this.faturacaoTipo[i].add(preco,quantidade);
        this.income += quantidade * preco;
    }

    /**
     * {@inheritDoc}
     */
    public void clear(){
        Arrays.fill(this.faturacaoTipo,null);
        this.income = 0;
    }

    /**
     * {@inheritDoc}
     */
    public double profitProd(){
        double f = 0;
        for(int i = 0; i < 2; i++){
            f+=this.faturacaoTipo[i].getFaturacao();
        }
        return f;
    }

    /**
     * {@inheritDoc}
     */
    public int prodMaisVendidos(){
        int un = 0;
        for(int i = 0; i < 2; i++){
            un += this.faturacaoTipo[i].getUnidades();
        }
        return un;
    }
}

