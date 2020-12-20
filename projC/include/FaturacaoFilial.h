#ifndef FaturacaoFilial_h
#define FaturacaoFilial_h

#include "FaturacaoMes.h"


#define NRFILIAIS 3


/**
 *  @brief Estrutura de dados que para guarda cada faturação dada a sua filial, 
 *  utilizando um array com tantas posições quantas filiais existem. 
 */
typedef FaturacaoMes *FaturacaoFilial;

/**
 * @brief Aloca memória para a estrutura de faturação distribuídas pelas filiais existentes. 
 * 
 * @return Estrutura de faturação de filiais.
 */
FaturacaoFilial initFaturacaoFilial(void);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda a faturação distribuída pelas filiais.
 * 
 * @param f Estrutura de faturação de filiais.
 */
void destroyFaturacaoFilial(FaturacaoFilial f);
/**
 * @brief Insere informação sobre a faturação do produto na estrutura de faturação de filiais.
 * 
 * @param f Estrutura de faturação de filiais.
 * @param price Float que representa o preço do produto.
 * @param quant Inteiro que representa as unidades vendidas.
 * @param prom Char que representa o tipo de compra.
 * @param mes Inteiro que representa o mês.
 * @param filial Inteiro que representa a filial.
 */
void insertFaturacaoFilial (FaturacaoFilial f, float price, int quant,char prom, int mes, int filial);
/**
 * @brief Obtém o lucro e a quantidade vendas relativo a um produto por tipo de compra num dado mês e uma dada filial.
 * 
 * @param a Estrutura de faturação de filiais.
 * @param totalN Inteiro que guarda o total faturado em compras com promoção.
 * @param totalP Inteiro que guarda o total faturado em compras sem promoção.
 * @param filial Inteiro que representa a filial.
 * @param month Inteiro que representa o mês.
 */
void salesAndProfitFiliais (FaturacaoFilial a, double *totalN, double *totalP, int filial,int month);
/**
 * @brief Calcula a quantidade e faturação num determinado mês e filial.
 * 
 * @param a Estrutura de dados.
 * @param totalFat Guarda o valor da faturação.
 * @param filial Representa o número da filial.
 * @param mes Representa o mês.
 * @return Quantidade calculada.
 */
int monthlysalesAndProfitFiliais(FaturacaoFilial a,double* totalFat,int filial,int mes);
/**
 * @brief Verifica se existem vendas numa dada filial.
 * 
 * @param f Estrutura de faturação.
 * @param branchID Caso 0, verifica em todas as filiais, caso for outro número verifica nessa filial apenas.
 * @return 1 se foram detetadas vendas ou 0 caso contrário.
 */
int isProductBoughtFilial(FaturacaoFilial f, int branchID);
/**
 * @brief Preenche a Estrutura Arvore com os dados de FaturacaoFilial.
 * 
 * @param f Fonte de dados.
 * @param key Código de produto.
 * @param c Arvore que recebe os dados.
 */
void getQuantitiesFilial(FaturacaoFilial f, char* key, ArvoreProdQuant* c);

#endif
