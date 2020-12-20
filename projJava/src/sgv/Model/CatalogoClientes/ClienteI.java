package sgv.Model.CatalogoClientes;

import java.io.Serializable;

/**
 * Interface que aglomera classes que representam Clientes.
 */
public interface ClienteI extends Comparable<ClienteI>, Serializable {
    /**
     * Método que retorna o código do Cliente.
     * @return Código do cliente.
     */
    String getCodigo();

    /**
     * Método que altera o código do Cliente.
     * @param codigo Código novo.
     */
    void setCodigo(String codigo);

    /**
     * Método que gera uma cópia deste objeto.
     * @return Cópia.
     */
    Cliente clone();
}
