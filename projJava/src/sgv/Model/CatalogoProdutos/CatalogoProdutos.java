package sgv.Model.CatalogoProdutos;

import java.util.*;

/**
 * Classe que representa um Catálogo de Produtos, ou seja, contém dados de todos os produtos do sistema.
 */
public class CatalogoProdutos implements CatalogoProdutosI {
    private DistribuicaoProdutoI[] produtos;
    private int numProdutos;

    public CatalogoProdutos() {
        this.produtos = new DistribuicaoProduto[26];
        for(int i = 0; i < 26; i++)
            produtos[i] = new DistribuicaoProduto();
        this.numProdutos = 0;
    }

    public CatalogoProdutos(List<ProdutoI> prod) {
        this.produtos = new DistribuicaoProduto[26];
        for(int i = 0; i < 26; i++)
            produtos[i] = new DistribuicaoProduto();
        this.numProdutos = 0;
        char ch;
        for(ProdutoI p : prod) {
            ch = p.getCodigo().charAt(0);
            this.produtos[ch - 65].add(p);
            this.numProdutos++;
        }
    }

    public CatalogoProdutos(ProdutoI[] prod) {
        this.produtos = new DistribuicaoProduto[26];
        for(int i = 0; i < 26; i++)
            produtos[i] = new DistribuicaoProduto();
        this.numProdutos = 0;
        char ch;
        for(ProdutoI p : prod) {
            ch = p.getCodigo().charAt(0);
            this.produtos[ch - 65].add(p);
            this.numProdutos++;
        }
    }

    public CatalogoProdutos(CatalogoProdutos cc) {
        this.produtos = cc.getDistribuicaoProduto();
        this.numProdutos = cc.getNumProdutos();
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
    public void add(ProdutoI p) {
        char ch = p.getCodigo().charAt(0);
        this.produtos[ch - 65].add(p);
        this.numProdutos++;
    }

    /**
     * {@inheritDoc}
     */
    public CatalogoProdutos clone() {
        return new CatalogoProdutos(this);
    }

    /**
     * {@inheritDoc}
     */
    public DistribuicaoProdutoI[] getDistribuicaoProduto() {
        DistribuicaoProdutoI[] ret = new DistribuicaoProduto[26];
        for(int i = 0; i < 26; i++)
            ret[i] = this.produtos[i].clone();
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public void setDistribuicaoProdutos(DistribuicaoProdutoI[] produtos) {
        this.produtos = new DistribuicaoProduto[26];
        for(int i = 0; i < 26; i++)
            this.produtos[i] = produtos[i].clone();
    }

    /**
     * {@inheritDoc}
     */
    public boolean search(String codProd){
        return this.produtos[codProd.charAt(0)-65].search(codProd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getProductsNeverBought(Set<String> prodsComprados) {
        Set<String> temp =  new TreeSet<>();
        Set<String> aux = new TreeSet<>();

        for(DistribuicaoProdutoI d : this.produtos) {
            temp = d.getProductsNeverBought(prodsComprados);
            if(!temp.isEmpty())
                aux.addAll(temp);
        }
       return aux;
    }
}
