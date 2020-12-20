package sgv.Model.CatalogoClientes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que guarda um conjunto de Clientes começados por uma determinada letra.
 */
public class DistribuicaoCliente implements DistribuicaoClienteI {
    private Map<String,ClienteI> clientes;

    public DistribuicaoCliente() {
        this.clientes = new HashMap<>();
    }

    public DistribuicaoCliente(List<ClienteI> cli) {
        this.clientes = new HashMap<>();
        for(ClienteI c : cli)
            this.clientes.put(c.getCodigo(),c);
    }

    public DistribuicaoCliente(DistribuicaoClienteI c) {
        this.clientes = new HashMap<>();
        for(Map.Entry<String,ClienteI> m : c.getClientes().entrySet())
            this.clientes.put(m.getKey(),m.getValue().clone());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {clientes});
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, ClienteI> getClientes() {
        Map<String,ClienteI> m = new HashMap<>();
        for(Map.Entry<String,ClienteI> f : this.clientes.entrySet())
            m.put(f.getKey(),f.getValue().clone());
        return m;
    }

    /**
     * {@inheritDoc}
     */
    public void setClientes(Map<String, ClienteI> clientes) {
        this.clientes = new HashMap<>();
        clientes.forEach((key, value) -> this.clientes.put(key, value));
    }

    /**
     * {@inheritDoc}
     */
    public void add(ClienteI c) {
        clientes.put(c.getCodigo(),c.clone());
    }

    /**
     * {@inheritDoc}
     */
    public DistribuicaoCliente clone() {
        return new DistribuicaoCliente(this);
    }

    /**
     * {@inheritDoc}
     */
    public boolean search(String codCli){
        boolean found = false;
        if(this.clientes.get(codCli)!= null)
            found = true;
        return found;
    }

}
