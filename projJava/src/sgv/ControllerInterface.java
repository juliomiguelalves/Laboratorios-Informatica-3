package sgv;

import sgv.Exceptions.CodProdutoInvalidoException;
import sgv.Exceptions.ProdutoNaoExistenteException;

import java.io.IOException;

public interface ControllerInterface {
    /**
     * Retorna dados referentes ao model.
     * @return Dados.
     */
    ModelInterface getModel();
    /**
     * Altera dados referentes ao model.
     * @param model Dados novos.
     */
    void setModel(ModelInterface model);
    /**
     * Retorna dados referentes à vista.
     * @return Dados.
     */
    ViewInterface getView();
    /**
     * Gera uma cópia do objeto.
     * @return Cópia.
     */
    ControllerInterface clone();

    /**
     * Cria uma view nova com os dados fornecidos
     * @param view Dados que queremos implementar na view
     */
    void setView(ViewInterface view);
    /**
     * Verifica se o programa está a correr.
     * @return booleano que indica se o programa está a correr.
     */
    boolean isExec();
    /**
     * Altera dados referentes ao estado do programa.
     * @param exec boleano que indica se o programa está a correr.
     */
    void setExec(boolean exec);
    /**
     * Corre o menu do programa.
     * @throws ProdutoNaoExistenteException Produto não existe.
     * @throws CodProdutoInvalidoException Codigo inválido.
     * @throws IOException IO.
     */
    void run() throws ProdutoNaoExistenteException, CodProdutoInvalidoException, IOException;
    /**
     * Retorna a opção de load dos ficheiros.
     * @return inteiro que indica a opção escolhida.
     */
    int printStateOption();
}
