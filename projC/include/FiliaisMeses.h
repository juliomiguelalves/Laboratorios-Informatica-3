#ifndef filiaismeses_h
#define filiaismeses_h

#include "FiliaisClientes.h"

/**
 *  @brief Estrutura de dados que para cada mês guarda a informação que relaciona produtos e as vendas no respetivo mês, 
 *  utilizando um array de 12 posições. 
 */
typedef Clientes *Meses;
/**
 * @brief Aloca memória para a estrutura Meses. 
 * 
 * @return Estrutura de vendas Meses.
 */
Meses initMeses(void);
/**
 * @brief Desaloca memória previamente alocada para a estrutura Meses.
 * 
 * @param m Estrutura de vendas Meses.
 */
void destroyMeses(Meses m);
/**
 * @brief Insere um cliente na estrutura Meses. 
 * 
 * @param key Chave do cliente.
 * @param mes Inteiro que representa o mês.
 * @param m Estrutura de vendas de meses.
 */
void insertClienteMes(char * key, int mes, Meses m);
/**
 * @brief Insere a informação da venda do produto na estrutura Meses.
 * 
 * @param cli Código do cliente.
 * @param prod Código do produto.
 * @param price Float que representa o preço.
 * @param quant Inteiro que representa a quantidade de produto.
 * @param promo Char que representa o tipo de compra.
 * @param mes Inteiro que representa o mês.
 * @param m Estrutura de vendas de meses.
 */
void insertProdutoMes(char * cli, char * prod, float price, int quant, char promo, int mes, Meses m);
/**
 * @brief Obtém os produtos mais comprados por um cliente e guarda na estrutura CodigosQuant. 
 * 
 * @param m Estrutura de vendas de meses.
 * @param clientID Código do cliente.
 * @param month Inteiro que representa o mês.
 * @param listaCodigos Array de estruturas.
 */
void getClienteFavouriteProductsMeses(Meses m, char* clientID, int month, CodigosQuant *listaCodigos);
/**
 * @brief Verifica se um dado cliente efetuou compras em algum mês. 
 * 
 * @param m Estrutura de vendas de meses.
 * @param key Código do cliente.
 * @return Inteiro que indica se existiu alguma compra.
 */
int isBoughtMes(Meses m, char * key);
/**
 * @brief Obtém os produtos onde o cliente gastou mais dinheiro numa certa filial. 
 * 
 * @param m Estrutura de vendas de meses.
 * @param key Código do cliente.
 * @param a Array de estruturas onde é guardada a informação dos produtos.
 */
void fillProdSalesMonth(Meses m,char * key,ProdSales* a);
/**
 * @brief Verifica se o cliente comprou numa dada filial. 
 * 
 * @param m Estrutura de vendas de meses.
 * @param key Código do cliente.
 */
int clientsOfAllBranchesMeses(Meses m, char* key);
/**
 * @brief Obtém os clientes que compraram um certo produto. 
 * 
 * @param a Estrutura de vendas de meses.
 * @param productID Código do produto.
 * @param contador Inteiro que representa se existiu compra.
 * @param promo String que possui os tipos de compra.
 * @return Array com os clientes que compraram.
 */
char** productBuyersOnMeses(Meses a ,char *productID,int *contador,char* promo);
/**
 * @brief Obtém número de produtos comprados por um cliente numa dada filial. 
 * 
 * @param f Estrutura de vendas de meses
 * @param clientID código do cliente
 * @return array de int 
 */
int* getNrProductsBoughtByClientMes(Meses f,char *clientID);
/**
 * @brief Preenche a estrutura Arvore com os conteúdos correspondentes da estrutura Meses.
 * 
 * @param f Estrutura de dados.
 * @param a Estrutura que recebe os dados.
 */
void fillArvoreProdutosMes(Meses f, ArvoreProdKey* a);
/**
 * @brief Obtém o número de vendas registadas num determinado mês.
 * 
 * @param a Estrutura de dados.
 * @param mes Inteiro que representa o mês.
 * @return Número de vendas.
 */
int nmrVendasMeses(Meses a,int mes);
/**
 * @brief Calcula o número de vendas de um produto num determinado mês.
 * 
 * @param m Estrutura de dados.
 * @param key ID produto.
 * @param month Representa o mês.
 * @param totalSalesN Guarda o número de vendas sem promoção.
 * @param totalSalesP Guarda o número de vendas com promoção.
 */
void getNrVendasProductMeses(Meses m,char* key, int month, int *totalSalesN, int *totalSalesP);

#endif
