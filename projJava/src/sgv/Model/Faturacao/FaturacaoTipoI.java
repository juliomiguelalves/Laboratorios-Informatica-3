package sgv.Model.Faturacao;

import java.io.Serializable;

/**
 * Interface que aglomera classes que representa Faturacao Tipo.
 */
public interface FaturacaoTipoI extends Serializable {
    /**
     * Devolve um array de estruturas de dados.
     * @return Array de estruturas.
     */
    FaturacaoDadosI[] getFaturacaoTipo();

    /**
     * Altera o array de estruturas de dados.
     * @param f Array de estruturas novo.
     */
    void setFaturacaoTipo(FaturacaoDadosI[] f);

    /**
     * Obtém uma cópia do objeto.
     * @return Cópia.
     */
    FaturacaoTipoI clone();

    /**
     * Limpa as estruturas de dados.
     */
    void clear();

    /**
     * Adiciona uma entrada nova na estrutura de dados.
     * @param preco Preço de venda.
     * @param quantidade Quantidade comprada.
     * @param promocao Promoção.
     */
    void add(double preco, int quantidade, char promocao);

    /**
     * Devolve a faturação do mês.
     * @return Faturação.
     */
    double getIncome();

    /**
     * Altera a faturação do mês.
     * @param income Faturação nova.
     */
    void setIncome(double income);

    /**
     * Devolve a faturação das vendas do mês.
     * @return Faturação.
     */
    double profitProd();
}
