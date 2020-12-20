package sgv.Model.CatalogoClientes;

import java.util.Arrays;
import java.util.List;

/**
 * Classe concebida para guardar dados sobre catálogos de clientes e realizar operações sobre estes.
 */
public class CatalogoClientes implements CatalogoClientesI {
    private DistribuicaoClienteI[] clientes;
    private int numClientes;

    public CatalogoClientes() {
        this.clientes = new DistribuicaoCliente[26];
        for(int i = 0; i < 26; i++)
            clientes[i] = new DistribuicaoCliente();
        this.numClientes = 0;
    }

    public CatalogoClientes(List<ClienteI> cli) {
        this.clientes = new DistribuicaoCliente[26];
        for(int i = 0; i < 26; i++)
            clientes[i] = new DistribuicaoCliente();
        numClientes = 0;
        char ch;
        for(ClienteI c : cli) {
            ch = c.getCodigo().charAt(0);
            this.clientes[ch - 65].add(c);
            numClientes++;
        }
    }

    public CatalogoClientes(ClienteI[] cli) {
        this.clientes = new DistribuicaoCliente[26];
        for(int i = 0; i < 26; i++)
            clientes[i] = new DistribuicaoCliente();
        this.numClientes = 0;;
        char ch;
        for(ClienteI c : cli) {
            ch = c.getCodigo().charAt(0);
            this.clientes[ch - 65].add(c);
            numClientes++;
        }
    }

    public CatalogoClientes(CatalogoClientes cc) {
        this.clientes = cc.getDistribuicaoCliente();
        this.numClientes = cc.getNumClientes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {clientes,numClientes});
    }

    /**
     * {@inheritDoc}
     */
    public void add(ClienteI c) {
        char ch = c.getCodigo().charAt(0);
        this.clientes[ch - 65].add(c);
    }

    /**
     * {@inheritDoc}
     */
    public CatalogoClientes clone() {
        return new CatalogoClientes(this);
    }

    /**
     * {@inheritDoc}
     */
    public int getNumClientes() {
        return numClientes;
    }

    /**
     * {@inheritDoc}
     */
    public void setNumClientes(int numClientes) {
        this.numClientes = numClientes;
    }

    /**
     * {@inheritDoc}
     */
    public DistribuicaoClienteI[] getDistribuicaoCliente() {
        DistribuicaoClienteI[] ret = new DistribuicaoCliente[26];
        for(int i = 0; i < 26; i++)
            ret[i] = this.clientes[i].clone();
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public void setDistribuicaoClientes(DistribuicaoClienteI[] clientes) {
        this.clientes = new DistribuicaoCliente[26];
        for(int i = 0; i < 26; i++)
            this.clientes[i] = clientes[i].clone();
    }

    /**
     * {@inheritDoc}
     */
    public boolean search(String codCli){
        return this.clientes[codCli.charAt(0)-65].search(codCli);
    }
}
