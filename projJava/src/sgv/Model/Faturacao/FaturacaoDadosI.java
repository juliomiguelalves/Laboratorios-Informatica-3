package sgv.Model.Faturacao;

import java.io.Serializable;

/**
 * Interface que aglomera classes que representa Faturacao Dados.
 */
public interface FaturacaoDadosI extends Serializable {
    /**
     * Devolve o número de unidades da venda.
     * @return Número de unidades.
     */
    int getUnidades();

    /**
     * Devolve a faturação da venda.
     * @return Faturação da venda.
     */
    double getFaturacao();

    /**
     * Altera o número de unidades.
     * @param unidades Número novo.
     */
    void setUnidades(int unidades);

    /**
     * Altera a faturação da venda.
     * @param faturacao Faturação nova.
     */
    void setFaturacao(double faturacao);

    /**
     * Devolve uma cópia do objeto.
     * @return Cópia.
     */
    FaturacaoDadosI clone();

    /**
     * Adiciona à quantidade e à faturação.
     * @param preco Preço da venda.
     * @param quantidade Quantidade da venda.
     */
    void add(double preco, int quantidade);
}
