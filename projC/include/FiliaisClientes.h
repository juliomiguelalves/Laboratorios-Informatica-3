#ifndef filiaisclientes_h
#define filiaisclientes_h

#include "FiliaisProdutos.h"

typedef struct Clientes *Clientes;

/**
 * @brief Obtém o número de ocorrências da estrutura de dados.
 * 
 * @param a Estrutura da vendas dos clientes.
 * @return Número de ocorrências.
 */
int percorreClientes(Clientes a);
/**
 * @brief Insere um cliente na estrutura de vendas de clientes. 
 * 
 * @param node Apontador para a estrutura do cliente.
 * @param key Chave do cliente.
 * @param a Estrutura de produtos a inserir na estrutura do cliente.
 */
void insertClienteVenda(Clientes *node,char* key, Produtos a);
/**
 * @brief Obtém os produtos que o cliente comprou. 
 * 
 * @param a Estrutura de vendas de clientes.
 * @param key Código do cliente.
 * @param s Array de estruturas onde é guardada a informação dos produtos.
 */
void fillProdSales(Clientes a, char* key, ProdSales *s);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda as vendas distribuídas pelos clientes.
 * 
 * @param a Estrutura de vendas de clientes.
 */
void destroyClientes(Clientes a);
/**
 * @brief Obtém os produtos mais comprados por um cliente. 
 * 
 * @param c Estrutura de vendas de meses.
 * @param clientID Código do cliente.
 * @param listaCodigos Array de estruturas.
 */
void getClienteFavouriteProductsClientes(Clientes c, char* clientID, CodigosQuant *listaCodigos);
/**
 * @brief Insere a informação da venda do produto na estrutura de vendas de clientes. 
 * @param c Estrutura de vendas de clientes. 
 * @param prod Código do produto
 * @param price Float que representa o preço
 * @param quant Inteiro que representa a quantidade de produto
 * @param promo Char que representa o tipo de compra
 * @param id Código do cliente
 */
void insertProdutoVendaCliente(Clientes c, char* prod,float price, int quant, char promo,char* id);
/**
 * @brief Obtém os clientes que compraram um certo produto num dado mês. 
 * 
 * @param a Estrutura de vendas de clientes.
 * @param productID Código do produto.
 * @param contador Inteiro que representa se existiu compra.
 * @param promo String com os tipos de venda.
 * @param array Array com os Clientes que compraram o respetivo produto.
 * @param size Tamanho do array.
 * @param index Indice atual do array.
 * @return Array de ID de produtos.
 */
char** productBuyersOnClientes(Clientes a, char *productID,int *contador,char* promo,char** array,int *size,int* index);
/**
 * @brief Obtém os número de produtos comprados por um cliente. 
 * 
 * @param c Estrutura de vendas de clientes.
 * @param clientID Código do cliente.
 * @return Numero de produtos.
 */
int getnrProductsBoughtByClient(Clientes c,char* clientID);
/**
 * @brief Função que verifica se o cliente fez compras.
 * 
 * @param c Estrutura de Dados do Cliente.
 * @param key ID Cliente.
 * @return 1 se comprou ou 0 caso contrário.
 */
int isBoughtMesClientes(Clientes c,char* key);
/**
 * @brief Preenche a Arvore com dados de Clientes.
 * 
 * @param c Estrutura de dados
 * @param a Estrutura que recebe os dados.
 */
void fillArvoreProdutosCliente(Clientes c,ArvoreProdKey* a);
/**
 * @brief Número de compras registadas por um determinado cliente.
 * 
 * @param a Estrutura de dados.
 * @return Resultado.
 */
int nmrVendasClientes(Clientes a);
/**
 * @brief Calcula o número de vendas de um produto.
 * 
 * @param c Estrutura de dados.
 * @param key ID produto.
 * @param totalSalesN Guarda o número de vendas sem promoção.
 * @param totalSalesP Guarda o número de vendas com promoição.
 */
void getNrVendasProductsClientes(Clientes c, char* key, int *totalSalesN, int *totalSalesP);


#endif
