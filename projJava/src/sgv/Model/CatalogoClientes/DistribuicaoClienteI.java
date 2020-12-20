package sgv.Model.CatalogoClientes;

import java.io.Serializable;
import java.util.Map;

/**
 * Interface que aglomera classes que representam Distribuicao de Clientes.
 */
public interface DistribuicaoClienteI extends Serializable {
    /**
     * Método que retorna a base de dados de clientes.
     * @return Clientes.
     */
    Map<String, ClienteI> getClientes();

    /**
     * Método que adiciona um Cliente à classe.
     * @param c Cliente.
     */
    void add(ClienteI c);

    /**
     * Método que retorna uma cópia do objeto.
     * @return Cópia.
     */
    DistribuicaoClienteI clone();

    /**
     * Altera a base de dados da classe.
     * @param clientes Clientes.
     */
    void setClientes(Map<String, ClienteI> clientes);

    /**
     * Procura se um dado código de cliente existe na classe.
     * @param codCli Código do cliente.
     * @return True se encontrou, false caso contrário.
     */
    boolean search(String codCli);
}
