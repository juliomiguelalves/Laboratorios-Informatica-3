package sgv.Model.Faturacao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que aglomera classes que representa Faturacao Mes.
 */
public interface FaturacaoMesI extends Serializable {
    /**
     * Retorna a estrtura de dados da classe.
     * @return Estrutura de dados.
     */
    FaturacaoTipoI[] getFaturacaoMes();

    /**
     * Altera a estrutura de dados da classe.
     * @param f Estrutura de dados nova.
     */
    void setFaturacaoMes(FaturacaoTipoI[] f);

    /**
     * Obtém uma cópia do objeto.
     * @return Cópia.
     */
    FaturacaoMesI clone();

    /**
     * Limpa a estrutura de dados.
     */
    void clear();

    /**
     * Adiciona uma nova entrada na estrutura.
     * @param preco Preço da venda.
     * @param quantidade Quantidade vendida.
     * @param promocao Promoção.
     * @param mes Mês registado.
     */
    void add(double preco, int quantidade, char promocao, int mes);

    /**
     * Obtém a faturação da filial correspondente.
     * @return Faturação.
     */
    double getIncome();

    /**
     * Altera a faturação da filial correspondente.
     * @param income Faturação.
     */
    void setIncome(double income);

    /**
     * Retorna a faturação no determinado mês.
     * @param mes Mês em causa.
     * @return Faturação.
     */
    double profitProd(int mes);

    /**
     * Retorna uma lista com as faturações de cada mês.
     * @return Lista de faturações.
     */
    List<Double> getTotalIncome();
}
