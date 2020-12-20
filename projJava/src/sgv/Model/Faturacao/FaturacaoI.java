package sgv.Model.Faturacao;

import java.io.Serializable;
import java.util.*;

/**
 * Interface que aglomera classes que representa Faturacao.
 */
public interface FaturacaoI extends Serializable {
    /**
     * Retorna dados acerca da faturação do sistema.
     * @return Dados.
     */
    Map<String, FaturacaoFilialI> getFaturacao();

    /**
     * Altera os dados de faturação de sistema.
     * @param faturacao Dados novos.
     */
    void setFaturacao(Map<String, FaturacaoFilialI> faturacao);

    /**
     * Cria uma cópia do objeto.
     * @return Cópia.
     */
    FaturacaoI clone();

    /**
     * Limpa a estrutura de dados.
     */
    void clear();

    /**
     * Adiciona uma entrada na estrutura.
     * @param codProduto Código do produto.
     * @param preco Preço de venda.
     * @param quantidade Quantidade vendida.
     * @param promocao Promoção.
     * @param codCliente Código do Cliente.
     * @param mes Mês registado.
     * @param filial Filial registada.
     */
    void add(String codProduto, double preco, int quantidade, char promocao, String codCliente, int mes, int filial);

    /**
     * Devolve a faturação total do sistema.
     * @return Faturação total.
     */
    double getIncome();

    /**
     * Altera a faturação total do sistema.
     * @param income Faturação nova.
     */
    void setIncome(double income);

    /**
     * Retorna um mapeamento de Código de produto para uma matriz de faturação por mês e filial do produto.
     * @return Mapeamento mencionado.
     */
    Map<String, double[][]> profitProd();

    /**
     * Retorna o conjunto de códigos de cliente que fizeram compras.
     * @return Conjunto de codigos de cliente.
     */
    Set<String> getClientsBought();

    /**
     * Altera o conjunto de códigos de cliente que fizeram compras.
     * @param clientsBought Conjunto de códigos de cliente novo.
     */
    void setClientsBought(Set<String> clientsBought);

    /**
     * Retorna o conjunto de códigos de produto que foram comprados.
     * @return Conjunto de codigos de produto.
     */
    Set<String> getProductsBought();

    /**
     * Altera o conjunto de códigos de produto que foram comprados.
     * @param productsBought Conjunto de codigos de produto.
     */
    void setProductsBought(Set<String> productsBought);

    /**
     * Devolve o número de clientes que fizeram compras.
     * @return Número.
     */
    int numberOfClientsBought();

    /**
     * Devolve o número de produtos que foram comprados.
     * @return Número.
     */
    int numberOfProductsBought();

    /**
     * Devolve uma "matriz" de faturação global por mês e por filial.
     * @return Matriz de faturações.
     */
    Map<Integer, List<Double>> getTotalIncomeByMonth();
}
