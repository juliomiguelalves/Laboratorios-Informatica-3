package sgv.Model.Faturacao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface que aglomera classes que representa Faturacao Filial.
 */
public interface FaturacaoFilialI extends Serializable {
    /**
     * Devolve array de dados de faturação.
     * @return Array de dados.
     */
    FaturacaoMesI[] getFaturacaoFilial();

    /**
     * Altera array de dados de faturação.
     * @param f Array de dados novo.
     */
    void setFaturacaoFilial(FaturacaoMesI[] f);

    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    FaturacaoFilialI clone();

    /**
     * Limpa a estrutura de dados.
     */
    void clear();

    /**
     * Adiciona uma entrada nova na estrutura de dados.
     * @param preco Preço da venda.
     * @param quantidade Quantidade vendida.
     * @param promocao Promoção.
     * @param mes Mês registado.
     * @param filial Filial registada.
     */
    void add(double preco, int quantidade, char promocao, int mes, int filial);

    /**
     * Devolve a faturação global do produto.
     * @return Faturação total.
     */
    double getIncome();

    /**
     * Altera a faturaçãi global do produto.
     * @param income Faturação total nova.
     */
    void setIncome(double income);

    /**
     * Devolve a faturação realizada num determinado mes e filial.
     * @param mes Mês em causa.
     * @param filial Filial em causa.
     * @return Faturação.
     */
    double profitProd(int mes, int filial);

    /**
     * Preenche o Map com a faturação por mês do produto.
     * @param ret Map que vai ser preenchido.
     */
    void getTotalIncomeByMonth(Map<Integer, List<Double>> ret);
}
