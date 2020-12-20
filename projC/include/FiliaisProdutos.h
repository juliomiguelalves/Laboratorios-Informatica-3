#ifndef filiaisprodutos_h
#define filiaisprodutos_h

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Auxiliar.h"


typedef struct Produtos *Produtos;
typedef struct repetidos repetidos;


/**
 * @brief Insere a informação da venda do produto na estrutura de vendas de produtos.
 * 
 * @param node Estrutura de vendas de produtos.
 * @param key Código do produto.
 * @param price Float que representa o preço.
 * @param quantity Inteiro que representa a quantidade de produto.
 * @param promo Char que representa o tipo de compra.
 */
void insertProdutoVenda(Produtos *node,char *key,float price, int quantity, char promo);
/**
 * @brief Obtém promoção de compra do produto.
 * 
 * @param a Estrutura de vendas de produtos.
 * @param index Indice do array.
 * @return Char promoção.
 */
char getPromo(Produtos a,int index);

/**
 * @brief Obtém o número de compras efetuado por um certo cliente.
 * 
 * @param a Estrutura da vendas de produtos.
 * @return Número de ocorrências.
 */
int percorreProdutos(Produtos a);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda as vendas distribuídas pelos produtos.
 * 
 * @param a Estrutura de vendas de produtos.
 */
void destroyProdutos(Produtos a); 
/**
 * @brief Obtém o número de produtos comprados. 
 * 
 * @param p Estrutura de vendas de produtos.
 * @return Resultado.
 */
int getnrProductsBought(Produtos p);
/**
 * @brief Obtém os produtos que o cliente comprou num dado mês. 
 * 
 * @param a Estrutura de vendas de clientes.
 * @param s Array de estruturas onde é guardada a informação dos produtos.
 */
void fillProdSalesProdutos(Produtos a,ProdSales *s);
/**
 * @brief Obtém os produtos mais comprados por um cliente num dado mês. 
 * 
 * @param p Estrutura de vendas de produtos.
 * @param listaCodigos Array de estruturas.
 */
void getClienteFavouriteProductsProdutos(Produtos p, CodigosQuant *listaCodigos);
/**
 * @brief Preenche a estrutura Arvore com os dados contidos na estrutura Produtos.
 * 
 * @param p Estrutura de dados.
 * @param key ID cliente para verificar se não há clientes repetidos.
 * @param a Estrutura que recebe a informação.
 */
void fillArvoreProdutosProduto(Produtos p,char* key,ArvoreProdKey* a);
/**
 * @brief Obtém o array de clientes que compraram um produto.
 * 
 * @param p Estrutura de dados produtos.
 * @param productID Código do produto.
 * @param contador Número de compras feitas.
 * @param promo String com o tipo de compras.
 * @param array Srray que armazena os clientes que compraram e o respetivo tipo de compra.
 * @param size Tamanho do array.
 * @param index Indice do array.
 * @param key Código do cliente.
 * @return Array de clientes.
 */
char** productBuyersOnClientesProdutos(Produtos p, char *productID,int *contador,char* promo,char** array,int* size,int* index,char* key);
/**
 * @brief Número de vendas que um determinado produto registou.
 * 
 * @param p Estrutura de dados.
 * @return Número de ocorrências.
 */
int nmrVendasProdutos(Produtos p);
/**
 * @brief Calcula o número de vendas de um produto.
 * 
 * @param p Estrutura de dados.
 * @param key ID de produto.
 * @param totalSalesN Guarda o número de vendas sem promoção.
 * @param totalSalesP Guarda o número de vendas com promoção.
 */
void getNrVendasProductsProdutos(Produtos p, char* key, int* totalSalesN, int* totalSalesP);

#endif
