package sgv.Model.Filiais;

import java.util.Arrays;

/**
 * Classe que guarda dados acerca de uma venda.
 */
public class Venda implements VendaI {
    private double preco;
    private int quantidade;
    private char promocao;

    public Venda(double preco, int quantidade, char promocao) {
        this.preco = preco;
        this.quantidade = quantidade;
        this.promocao = promocao;
    }

    public Venda(){
        this.preco = 0;
        this.quantidade =0;
        this.promocao = '0';
    }

    public Venda(VendaI v){
        this.preco = v.getPreco();
        this.quantidade = v.getQuantidade();
        this.promocao = v.getPromocao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {preco,quantidade,promocao});
    }

    /**
     * {@inheritDoc}
     */
    public double getPreco() {
        return preco;
    }

    /**
     * {@inheritDoc}
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * {@inheritDoc}
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * {@inheritDoc}
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * {@inheritDoc}
     */
    public char getPromocao() {
        return promocao;
    }

    /**
     * {@inheritDoc}
     */
    public void setPromocao(char promocao) {
        this.promocao = promocao;
    }

    /**
     * {@inheritDoc}
     */
    public Venda clone(){
        return new Venda(this);
    }

    /**
     * {@inheritDoc}
     */
    public double getPrecoTotal(){
        return getPreco()*getQuantidade();
    }
}
