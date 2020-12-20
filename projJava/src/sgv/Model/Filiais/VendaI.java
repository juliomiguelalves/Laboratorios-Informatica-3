package sgv.Model.Filiais;

import java.io.Serializable;

/**
 * Interface que aglomera classes que representa Venda.
 */
public interface VendaI extends Serializable {
    /**
     * Retorna o preço da venda.
     * @return Preço.
     */
    double getPreco();

    /**
     * Altera o preço da venda.
     * @param preco Preço novo.
     */
    void setPreco(double preco);

    /**
     * Retorna a quantidade da venda.
     * @return Quantidade.
     */
    int getQuantidade();

    /**
     * Altera a quantidade da venda.
     * @param quantidade Quantidade nova.
     */
    void setQuantidade(int quantidade);

    /**
     * Retorna a promoção da venda.
     * @return Promoção.
     */
    char getPromocao();

    /**
     * Altera a promoção da venda.
     * @param promocao Promoção nova.
     */
    void setPromocao(char promocao);

    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    VendaI clone();

    /**
     * Retorna o preço total de compra.
     * @return Preço total.
     */
    double getPrecoTotal();
}
