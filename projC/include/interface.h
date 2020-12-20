/*
  Venda.h
  LI3

  Created by Rúben Cerqueira, Júlio Alves and Alexandra Reigada on 20/02/2020.
  Copyright © 2020 Rúben Cerqueira, Júlio Alves and Alexandra Reigada. All rights reserved.
*/

#ifndef interface_h
#define interface_h

#include <sys/ioctl.h>
#include "CatalogoCliente.h"
#include "CatalogoProduto.h"
#include "Filiais.h"
#include "Faturacao.h"
#include "UI.h"
#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>


#define NRFILIAIS 3

typedef struct SGV *SGV;
/**
 *  @brief Aloca memória estrutura de dados SGV.
 *  @return Estrutura de dados.
 */
SGV initSGV(void);
/**
 *  @brief Desaloca memória previamente alocada para a estrutura de dados SGV.
 *  @param a Estrutura de dados.
 */
void destroySGV(SGV a); 
/**
 *  @brief Query que obtém a informação sobre os ficheiros lidos.
 *  @param a Estrutura de dados.
 */
void getCurrentFilesInfo(SGV a);
/**
 *  @brief Query que carrega os ficheiros para a estrutura de dados SGV a partir de 3 ficheiros de dados.
 *  @param sgv Estrutura de dados.
 *  @param clientsFilePath String que representa o caminho para o ficheiro de clientes.
 *  @param productsFilePath String que representa o caminho para o ficheiro de produtos.
 *  @param salesFilePath String que representa o caminho para o ficheiro de vendas.
 */
SGV loadSGVFromFiles(SGV sgv, char* clientsFilePath, char* productsFilePath, char* salesFilePath); 
/**
 *  @brief Query que determina a lista e o nº total de produtos cujo código se inicia por uma dada letra;
 *  @param sgv Estrutura de dados.
 *  @param letter Char que representa a letra pelo qual o código de inicia.
 */
void getProductsStartedByLetter (SGV sgv, char letter); 
/**
 *  @brief Query que dado um mês e um código de produto determina o número total de vendas
 *  e o total faturado com esse produto nesse mês, dividindo por tipo de compra
 *  É possível apresentar os resultados por filial ou para as 3 filiais.
 *  @param sgv Estrutura de dados.
 *  @param productID String que representa o código do produto.
 *  @param month Inteiro que representa o mês.
 */
void getProductsSalesAndProfit (SGV sgv, char * productID, int month); 
/**
 *  @brief Query que determina a lista ordenada dos códigos dos produtos e o seu número total, que ninguém comprou.
 *  @param sgv Estrutura de dados
 *  @param branchID Inteiro que se for igual a 0 indica que o utilizador pretende a lista total, 
 *  se igual a 1 indica que pretende a lista dividida por filiais 
 */
void getProductsNeverBought (SGV sgv, int branchID); 
/**
 *  @brief Query que determinar a lista ordenada de códigos de clientes que realizaram compras em todas as filiais.
 *  @param sgv Estrutura de dados.
 */
void getClientsOfAllBranches(SGV sgv); 
/**
 *  @brief Query que determina o número de clientes registados que não realizaram compras e como o número de produtos que ninguém comprou.
 *  @param sgv Estrutura de dados.
 */
void getClientsAndProductsNeverBoughtCount (SGV sgv); 
/**
 *  @brief Query que dado um código de cliente, cria uma tabela com o número total de produtos comprados por mês.
 *  @param sgv Estrutura de dados.
 *  @param clientID String que representa o código do produto.
 */
void getProductsBoughtByClient(SGV sgv,char* clientID); 
/**
 *  @brief Query que dado um intervalo de meses, determina o total de vendas registadas nesse intervalo e o total faturado.
 *  @param sgv Estrutura de dados.
 *  @param minMonth Inteiro que representa o mês inferior.
 *  @param maxMonth Inteiro que representa o mês superior.
 */
void getSalesAndProfit (SGV sgv,int minMonth, int maxMonth); 
/**
 *  @brief Query que dado um código de produto e uma filial, determina os códigos e número total dos clientes que o compraram, 
 *  distinguindo o tipo de compra.
 *  @param sgv Estrutura de dados.
 *  @param productID String que representa o código do produto.
 *  @param branch Inteiro que representa a filial.
 */
void getProductBuyers (SGV sgv, char * productID, int branch); 
/**
 *  @brief Query que dado  um  código  de  cliente  e  um  mês,  determinar a  lista  de códigos
 *  de produtos que mais comprou por quantidade, por ordem descendente.
 *  @param sgv Estrutura de dados.
 *  @param clientID String que representa o código do cliente.
 *  @param month Inteiro que representa o mês.
 */
void getClientFavoriteProducts (SGV sgv, char* clientID, int month); 
/**
 *  @brief Query que determina os N produtos mais vendidos em todo o ano, indicando o número total de clientes 
 *  e o número de unidades vendidas, filial a filial.
 *  @param sgv Estrutura de dados.
 *  @param limit Inteiro que indica quantos produtos devem ser impressos.
 */
void getTopSelledProducts(SGV sgv, int limit); 
/**
 *  @brief Query que dado um código de cliente determina quais os códigos dos N produtos em que mais gastou dinheiro durante o ano.
 *  @param sgv Estrutura de dados.
 *  @param clientID String que representa o código do cliente.
 *  @param limit Inteiro que indica quantos produtos devem ser impressos.
 */
void getClientTopProfitProducts(SGV sgv, char * clientID, int limit); 
/**
 *  @brief Valida a venda.
 *  @param sgv Estrutura de dados
 *  @param prod String que representa o código do produto
 *  @param prec Float que representa o preço
 *  @param un Inteiro que representa as unidades
 *  @param prom Char que representa o tipo de compra
 *  @param cli String que representa código do cliente
 *  @param mes Inteiro que representa o mês
 *  @param super Inteiro que representa a filial
 *  @return Inteiro que representa sucesso quando é igual a 1
 */
int valvenda(SGV sgv,char *prod,float prec,int un,char prom,char *cli,int mes,int super);
/**
 *  @brief Valida a string de input de código do cliente, ou seja se se inicia com uma letra maiúscula seguida de 4 dígitos.
 *  @param cliente String que representa o código do cliente.
 *  @return Inteiro, igual a 1 quando o código é válido.
 */
int validaCliente(char* cliente);
/**
 *  @brief Valida a string de input de código do produto, ou seja se se inicia com duas letras maiúsculas seguidas de 4 dígitos.
 *  @param produto String que representa o código do produto.
 *  @return Inteiro, igual a 1 quando o código é válido.
 */
int validaProduto(char* produto);


#endif 
