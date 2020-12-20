#ifndef filiais_h
#define filiais_h

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "FiliaisMeses.h"

#define NRFILIAIS 3

/**
 *  @brief Estrutura de dados que para cada filial guarda a informação que relaciona produtos e as vendas na respetiva filial, 
 *  utilizando um array com tantas posições quantas filiais existem. 
 */
typedef Meses *Filiais;

/**
 * @brief Aloca memória para a estrutura que guarda as vendas distribuídas pelas filiais. 
 * 
 * @return Estrutura de vendas de filiais.
 */
Filiais initFiliais(void);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda as vendas distribuídas pelas filiais.
 * 
 * @param v Estrutura de vendas de filiais.
 */
void destroyFiliais(Filiais v);
/**
 * @brief Insere um cliente na estrutura de vendas de filial.
 * 
 * @param key Código do cliente.
 * @param mes Inteiro que representa mês da compra.
 * @param filial Inteiro que representa a filial.
 * @param f Estrutura de vendas de filiais.
 */
void insertClienteFilial(char* key, int mes, int filial, Filiais f);
/**
 * @brief Insere a informação da venda do produto na estrutura de vendas de filial.
 * 
 * @param cli código do cliente
 * @param prod código do produto
 * @param price float que representa o preço
 * @param quant inteiro que representa a quantidade de produto
 * @param promo char que representa o tipo de compra
 * @param mes inteiro que representa o mês
 * @param filial inteiro que representa mês da compra
 * @param f Estrutura de vendas de filiais.
 */
void insertProdutoFilial(char * cli, char * prod, float price, int quant, char promo, int mes, int filial, Filiais f);
/**
 * @brief Obtém os produtos onde o cliente gastou mais dinheiro. 
 * 
 * @param f Estrutura de vendas de filiais.
 * @param key Código do cliente.
 * @param n Inteiro que representa limite de produtos.
 * @return Estrutura com os respetivos dados.
 */
ProdSales getClientTopProfitProductsBranches(Filiais f, char * key, int* n);
/**
 * @brief Obtém os clientes que compraram um certo produto. 
 * 
 * @param a Estrutura de vendas de filiais.
 * @param branch Inteiro que representa a filial.
 * @param productID Código do produto.
 * @param contador Inteiro que guarda o numero de produtos comprados.
 * @param promo String que possui os tipos de compra.
 * @return Array com os Clientes que compraram.
 */
char** productBuyersOnFiliais (Filiais a,int branch, char* productID,int *contador,char* promo);
/**
 * @brief Obtém os produtos mais comprados por um cliente. 
 * 
 * @param f Estrutura de vendas de filiais.
 * @param clientID Código do cliente.
 * @param month Inteiro que representa o mês.
 * @return Estrutura de dados.
 */
CodigosQuant clientFavouriteProducts(Filiais f, char* clientID, int month);
/**
 * @brief Obtém os clientes que nunca compraram.
 * 
 * @param f Estrutura de vendas de filiais.
 * @param listaClientes Array de códigos de clientes.
 * @param tam Inteiro que representa o tamanho do array.
 * @return Inteiro que representa os clientes que nunca compraram.
 */
int clientsNeverBoughtCount(Filiais f, char** listaClientes, int tam);
/**
 * @brief Obtém os clientes que compraram em todas as filiais. 
 * 
 * @param f Estrutura de vendas de filiais.
 * @param listaClientes Array de códigos de clientes.
 * @param tamClientes Inteiro que representa o tamanho do array.
 * @param size Tamanho do array.
 * @return Array de ID Clientes que compraram em todas as filiais.
 */
char** clientsOfAllBranches(Filiais f, char** listaClientes, int tamClientes, int* size);
/**
 * @brief Obtém uma matriz de quantidades de produtos comprados por um dado cliente separada por filial e mês.
 * 
 * @param f Estrutura de dados.
 * @param clientID ID do cliente.
 * @return Matriz de quantidades de produtos.
 */
int** getNrProductsBoughtByClient(Filiais f, char* clientID);
/**
 * @brief Preenche a Arvore com os dados da estrutura Filiais.
 * 
 * @param f Estrutura de dados fonte.
 * @param p Estrutura de dados que recebe a informação.
 */
void fillArvoreProdutos(Filiais f, ArvoreProdKey* p);
/**
 * @brief Obtém o número de vendas num determinado mês e filial.
 * 
 * @param a Estrutura de dados.
 * @param mes Inteiro que representa o mês.
 * @param filial Inteiro que representa a filial.
 * @return Número de ocorrências.
 */
int nmrVendasFiliais(Filiais a,int mes,int filial);
/**
 * @brief Obtém o número de vendas, separada por promoção, de um produto, numa filial e num mês especifico.
 * 
 * @param f Estrutura de dados.
 * @param key ID produto.
 * @param filial Representa o número da filial.
 * @param month Representa o número de mês.
 * @param totalSalesN Guarda o número de vendas sem promoção.
 * @param totalSalesP Guarda o número de vendas com promoção.
 */
void getNrVendasProductFiliais(Filiais f,char* key, int filial, int month, int *totalSalesN, int *totalSalesP);

#endif 
