#ifndef FaturacaoDados_h
#define FaturacaoDados_h

#include <stdio.h>
#include <stdlib.h>
#include "Auxiliar.h"


typedef struct FaturacaoDados *FaturacaoDados;

/**
 * @brief Aloca memória para a estrutura de dados dos dados da faturação. 
 * 
 * @return Estrutura de faturação de dados.
 */
FaturacaoDados initFaturacaoDados(void);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda a faturação de dados.
 * 
 * @param f Estrutura de faturação de dados.
 */
void destroyFaturacaoDados(FaturacaoDados f);
/**
 * @brief Insere informação sobre a faturação do produto na estrutura de faturação de dados.
 * 
 * @param f Estrutura de faturação de dados.
 * @param price Float que representa o preço do produto.
 * @param quant Inteiro que representa as unidades vendidas.
 * @param i Inteiro que repesenta o tipo de compra (0 para compra normal e 1 para compra em promoção).
 */
void insertFaturacaoDados (FaturacaoDados f, float price, int quant, int i);
/**
 * @brief Obtém o lucro e a quantidade vendas relativo a um produto.
 * 
 * @param a Estrutura de faturação de dados.
 * @param totalN Inteiro que guarda o total faturado em compras com promoção.
 * @param totalP Inteiro que guarda o total faturado em compras sem promoção.
 */
void salesAndProfitDados(FaturacaoDados a, double* totalN, double* totalP);
/**
 * @brief Calcula o número de unidades e faturação de um produto.
 * 
 * @param a Estrutura de dados.
 * @param totalFat Guarda a faturação total do produto.
 * @return Número de unidades 
 */
int monthlySalesAndProfitDados(FaturacaoDados a,double* totalFat);
/**
 * @brief Verifica se existem vendas.
 * 
 * @param f Estrutura de faturação de dados.
 * @return 1 se houve vendas ou 0 caso contrário.
 */
int isProductBought(FaturacaoDados f);
/**
 * @brief Obtém a quantidade do produto vendido.
 * 
 * @param f Estrutura de faturacao de dados.
 * @return Quantidade total.
 */
int getQuantities(FaturacaoDados f);

#endif
