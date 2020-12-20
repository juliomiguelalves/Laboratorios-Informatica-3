package sgv.Model.CatalogoProdutos;

import java.util.*;

/**
 * Classe que contém dados sobre Produtos começados por uma determinada letra.
 */
public class DistribuicaoProduto implements DistribuicaoProdutoI {
    private Map<String,ProdutoI> produtos;
    private int numProdutos;

    public DistribuicaoProduto() {
        this.produtos = new HashMap<>();
        this.numProdutos = 0;
    }

    public DistribuicaoProduto(List<ProdutoI> cli) {
        this.produtos = new HashMap<>();
        this.numProdutos = 0;
        for(ProdutoI p : cli) {
            this.produtos.put(p.getCodigo(), p);
            this.numProdutos++;
        }
    }

    public DistribuicaoProduto(DistribuicaoProduto p) {
        this.produtos = new HashMap<>();
        for(Map.Entry<String,ProdutoI> m : p.getProdutos().entrySet())
            this.produtos.put(m.getKey(),m.getValue().clone());
        this.numProdutos = p.getNumProdutos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {produtos,numProdutos});
    }

    /**
     * {@inheritDoc}
     */
    public int getNumProdutos() {
        return numProdutos;
    }

    /**
     * {@inheritDoc}
     */
    public void setNumProdutos(int numProdutos) {
        this.numProdutos = numProdutos;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, ProdutoI> getProdutos() {
        Map<String,ProdutoI> m = new HashMap<>();
        for(Map.Entry<String,ProdutoI> f : this.produtos.entrySet())
            m.put(f.getKey(),f.getValue().clone());
        return m;
    }

    /**
     * {@inheritDoc}
     */
    public void add(ProdutoI p) {
        produtos.put(p.getCodigo(),p.clone());
        this.numProdutos++;
    }

    /**
     * {@inheritDoc}
     */
    public DistribuicaoProduto clone() {
        return new DistribuicaoProduto(this);
    }

    /**
     * {@inheritDoc}
     */
    public boolean search(String codProd){
        boolean found = false;
        if(this.produtos.get(codProd)!=null) found = true;
        return found;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getProductsNeverBought(Set<String> prods) {
        Set<String> ret = new HashSet<>();
        for(ProdutoI c : this.produtos.values())
            if(!prods.contains(c.getCodigo())) ret.add(c.getCodigo());
        return ret;
    }
}
