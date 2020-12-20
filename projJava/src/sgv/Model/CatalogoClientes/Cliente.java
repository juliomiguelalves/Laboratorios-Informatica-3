package sgv.Model.CatalogoClientes;

import java.util.Arrays;

/**
 * Classe que representa um Cliente.
 */
public class Cliente implements ClienteI {
    private String codigo;

    public Cliente(String codigo) {
        this.codigo = codigo;
    }

    public Cliente(Cliente c) {
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
    public Cliente clone() {
        return new Cliente(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(ClienteI o) {
        return codigo.compareTo(o.getCodigo());
    }
}
