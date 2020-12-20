package sgv.Model.Faturacao;

import java.util.Arrays;

public class FaturacaoDados implements FaturacaoDadosI {
    private int unidades;
    private double faturacao;

    public FaturacaoDados(){
        this.unidades = 0;
        this.faturacao = 0;
    }

    public FaturacaoDados(int unidades, double faturacao) {
        this.unidades = unidades;
        this.faturacao = faturacao;
    }

    public FaturacaoDados(FaturacaoDadosI f){
        this.unidades = f.getUnidades();
        this.faturacao = f.getFaturacao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {unidades,faturacao});
    }

    /**
     * {@inheritDoc}
     */
    public int getUnidades() {
        return this.unidades;
    }

    /**
     * {@inheritDoc}
     */
    public double getFaturacao() {
        return this.faturacao;
    }

    /**
     * {@inheritDoc}
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     * {@inheritDoc}
     */
    public void setFaturacao(double faturacao) {
        this.faturacao = faturacao;
    }

    /**
     * {@inheritDoc}
     */
    public FaturacaoDados clone(){
        return new FaturacaoDados(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(double preco, int quantidade) {
        this.unidades += quantidade;
        this.faturacao += quantidade*preco;
    }
}
