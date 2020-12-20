#ifndef FaturacaoMes_h
#define FaturacaoMes_h

#include "FaturacaoDados.h"

/**
 *  @brief Estrutura de dados que para guarda cada faturação dado o seu mês, 
 *  utilizando um array com tantas posições quantos meses existem. 
 */
typedef FaturacaoDados *FaturacaoMes;

/**
 * @brief Aloca memória para a estrutura de faturação de meses. 
 * 
 * @return Estrutura de faturação de meses.
 */
FaturacaoMes initFaturacaoMes(void);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda a faturação de meses.
 * 
 * @param f Estrutura de faturação de meses.
 */
void destroyFaturacaoMes(FaturacaoMes f);
/**
 * @brief Insere informação sobre a faturação do produto na estrutura de faturação de meses.
 * 
 * @param f Estrutura de faturação de meses.
 * @param price Float que representa o preço do produto.
 * @param quant Inteiro que representa as unidades vendidas.
 * @param prom Char que representa o tipo de compra.
 * @param mes Inteiro que representa o mês.
 */
void insertFaturacaoMes (FaturacaoMes f, float price, int quant,char prom, int mes);
/**
 * @brief Obtém o lucro e a quantidade vendas relativo a um produto por tipo de compra num dado mês.
 * 
 * @param a Estrutura de faturação de meses.
 * @param totalN Inteiro que guarda o total faturado em compras com promoção.
 * @param totalP Inteiro que guarda o total faturado em compras sem promoção.
 * @param month Inteiro que representa o mês.
 * 
 */
void salesAndProfitMes (FaturacaoMes a, double *totalN, double *totalP, int month);
/**
 * @brief Calcula a quantidade vendida de um produto e a sua faturação num mês.
 * 
 * @param a Estrutura de dados.
 * @param totalFat Guarda a faturação.
 * @param mes Representa o mês.
 * @return Quantidade calculada.
 */
int  monthlysalesAndProfitMes(FaturacaoMes a,double* totalFat,int mes);

/**
 * @brief Verifica se existem vendas na estrutura.
 * 
 * @param f Estrutura de faturação de meses.
 * @return 1 se foi vendido ou 0 caso contrário.
 */
int isProductBoughtMes(FaturacaoMes f);
/**
 * @brief Calcula a quantidade vendida do produto.
 * 
 * @param f Estrutura de dados.
 * @return Quantidade total vendida.
 */
int getQuantitiesMes(FaturacaoMes f);

#endif
