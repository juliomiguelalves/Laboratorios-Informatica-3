package sgv.Model.CatalogoClientes;

import java.io.Serializable;

/**
 * Interface que aglomera classes que representam catalogos de clientes.
 */
public interface CatalogoClientesI extends Serializable {
    /**
     * Método que gera uma cópia deste objeto.
     * @return Cópia.
     */
    CatalogoClientesI clone();
    /**
     * Retorna uma cópia da base de dados de clientes por array organizado por letra.
     * @return Array de dados.
     */
    DistribuicaoClienteI[] getDistribuicaoCliente();
    /**
     * Altera a base de dados de clientes por array organizado por letra.
     * @param clientes Array de dados.
     */
    void setDistribuicaoClientes(DistribuicaoClienteI[] clientes);
    /**
     * Método que permite adicionar clientes à classe.
     * @param c Cliente a adicionar.
     */
    void add(ClienteI c);
    /**
     * Retorna o numero de clientes no catálogo.
     * @return Númeroclientes.
     */
    int getNumClientes();
    /**
     * Altera o número de clientes no catálogo.
     * @param numClientes Número proposto.
     */
    void setNumClientes(int numClientes);
    /**
     * Verifica se um determinado código de cliente se encontra na classe.
     * @param codCli Código a determinar.
     * @return true se encontrar, false caso contrário.
     */
    boolean search(String codCli);
}
