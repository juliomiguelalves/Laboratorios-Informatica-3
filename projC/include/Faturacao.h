#ifndef faturacao_h
#define faturacao_h

#include "FaturacaoFilial.h"


typedef struct Faturacao *Faturacao;

/**
 * @brief Aloca memória para a estrutura de faturação. 
 * 
 * @return Estrutura de faturação.
 */
Faturacao initFaturacao(void);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda a faturação.
 * 
 * @param f Estrutura de faturação.
 */
void destroyFaturacao(Faturacao* f);
/**
 * @brief Insere informação sobre a faturação do produto na estrutura de faturação.
 * 
 * @param prod Código do produto.
 * @param price Float que representa o preço do produto.
 * @param quant Inteiro que representa as unidades vendidas.
 * @param prom Char que representa o tipo de compra.
 * @param mes Inteiro que representa o mês.
 * @param filial Inteiro que representa a filial.
 * @param f Estrutura de faturação de filiais.
 */
void insertFaturacao(char *prod, float price, int quant,char prom, int mes, int filial, Faturacao f);
/**
 * @brief Insere um produto na estrutura de faturação.
 * 
 * @param f Estrutura de faturação.
 * @param key Código do produto. 
 */
void insertProdutoFaturacao (Faturacao* f, char* key);
/**
 * @brief Obtém o lucro e a quantidade vendas relativo a um produto por tipo de compra num dado mês.
 * 
 * @param a Estrutura de faturação.
 * @param mes Inteiro que representa o mês.
 * @param filial Inteiro que representa a filial.
 * @param totalN Inteiro que guarda o total faturado em compras com promoção.
 * @param totalP Inteiro que guarda o total faturado em compras sem promoção.
 * @param key Código do produto.
 */
void salesAndProfitFaturacao(Faturacao a, int mes,int filial, double *totalN, double *totalP, char* key);
/**
 * @brief Obtém o lucro e a quantidade vendas por tipo de compra num mês.
 * 
 * @param a Estrutura de faturação.
 * @param mes Inteiro que representa o mês.
 * @param filial Inteiro que representa a filial.
 * @param totalFat Double que representa o total faturado.
 * @return Inteiro que representa as quantidades vendidas.
 */
int monthlySalesAndProfitFaturacao(Faturacao a,int mes,int filial,double *totalFat);
/**
 * @brief Obtém os produtos não comprados.
 * 
 * @param f Estrutura de faturação.
 * @param branchID Inteiro que representa a filial.
 * @param size Tamanho do array.
 * @param array Estrutura que guarda os códigos dos produtos não comprados.
 * @param index Indice da última posição.
 * @return Array dos produtos não comprados.
 */
char** getProductsNeverBoughtFact(Faturacao f, int branchID, int* size, char** array,int *index);
/**
 * @brief Obtém o número de produtos não comprados.
 * 
 * @param f Estrutura de faturação.
 * @return Total número de produtos não comprados.
 */
int productsNeverBoughtCount(Faturacao f);
/**
 * @brief Preenche a Arvore com os dados contidos em Faturacao.
 * 
 * @param f Fonte de dados.
 * @param c Estrutura que vai receber os dados.
 */
void getQuantitiesFact(Faturacao f,ArvoreProdQuant* c);

#endif
