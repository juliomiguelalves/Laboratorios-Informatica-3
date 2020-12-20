package sgv.Model.CatalogoProdutos;

import java.util.Arrays;

/**
 * Classe que representa um Produto.
 */
public class Produto implements ProdutoI {
    private String codigo;

    public Produto(String codigo) {
        this.codigo = codigo;
    }

    public Produto(Produto c) {
        this.codigo = c.getCodigo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {codigo});
    }

    /**
     * {@inheritDoc}
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * {@inheritDoc}
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * {@inheritDoc}
     */
    public Produto clone() {
        return new Produto(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(ProdutoI o) {
        return codigo.compareTo(o.getCodigo());
    }
}
