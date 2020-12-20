#ifndef UI_h
#define UI_h

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <unistd.h>
#include <math.h>
#include <sys/ioctl.h>
#include "Auxiliar.h"

#define NRFILIAIS 3

/**
 * @brief Limpa o ecrã.
 */
void clearScreen(void);
/**
 * @brief Imprime o menu.
 */
void printMenu(void);
/**
 * @brief Imprime vista para colocar opção.
 */
void printOpcao(void);
/**
 * @brief Imprime mensagem de input inválido.
 */
void printError(void);
/**
 * @brief Imprime erro de input não encontrado.
 * 
 * @param smth String do que não foi encontrado.
 */
void printErrorSomethingNotFound(char* smth);
/**
 * @brief Imprime que todos os produtos foram comprados.
 */
void printEveryProductBought(void);
/**
 * @brief Imprime que o cliente não fez compras nesse mês e nessa filial.
 */
void printProductDidntBuyMonthBranch(char*productID,int month,int branch);
/**
 * @brief Imprime que o cliente não fez compras nesse mês.
 */
void printClientDidntBuyMonth(char* client, int month);
/**
 * @brief Imprime que o produto não foi comprado num mês
 */
void printProductDidntBuyMonth(char* productID, int month);
/**
 * @brief Imprime opções de carregamento de ficheiros. 
 */
void printLoadSGVoptions(void);
/**
 * @brief Imprime opção de inserir nome de ficheiro.
 */
void selectReadFile(char* fileName);
/**
 * @brief Imprime mensagem de sucesso.
 */
void printSuccessMsg(void);
/**
 * @brief Imprime opção de inserir página.
 */
void selectPage(void);
/**
* @brief Imprime opção de inserir letra.
*/
void selectLetter(void);
/**
 * @brief Imprime opção de inserir mês.
 */
void selectMonth(void);
/**
 * @brief Imprime opção de inserir código de produto.
 */
void selectProduct(void);
/**
 * @brief Imprime opção de inserir código de cliente.
 */
void selectClient(void);
/**
 * @brief Imprime menu de filiais, 0 quando global e 1 quando divididas.
 */
void selectFilialDivided(void);
/**
 * @brief Imprime opção de inserir filial.
 */
void selectFilial(void);
/**
 * @brief Imprime opção de inserir limite de resultados.
 */
void selectLimit(void);
/**
 * @brief Imprime opção de inserir o mês inicial do intervalo de meses.
 */
void selectMonthsToLookFor1(void);
/**
 * @brief Imprime opção de inserir o mês final do intervalo de meses.
 */
void selectMonthsToLookFor2(void);
/**
 * @brief Imprime mensagem de pressionar enter.
 */
void printEnterToContinue(void);
/**
 * @brief Imprime mensagem ao sair do programa.
 */
void printLeavingMsg(void);
/**
 * @brief Imprime a informação sobre os ficheiros utilizados, nomes dos ficheiros dos produtos, clientes e vendas
 * e número de vendas lidas e número de vendas válidas.
 * @param catCli String que representa nome do ficheiro de clientes.
 * @param catProd String que representa nome do ficheiro de produtos.
 * @param sales String que representa nome do ficheiro de vendas.
 * @param nrSales Inteiro que representa número de vendas lidas.
 * @param nrValidSales Inteiro que representa número de vendas válidas.
 */
void printCurrentFilesInfo(char* catCli, char* catProd, char* sales, int nrSales, int nrValidSales);
/**
 * @brief Imprime mensagem de base de dados vazia.
 */
void printSGVEmpty(void);
/**
 * @brief Imprime mensagem de ficheiros não encontrados.
 */
void printFileNotFound(void);
/**
 * @brief Imprime informação sobre as vendas de um produto num determinado mês.
 * @param productID String que representa código do produto.
 * @param month Inteiro que representa o mês.
 * @param totalSalesN Inteiro que reperesenta as vendas totais do produto em modo Normal.
 * @param totalSalesP Inteiro que representa as vendas totais do produto em Promoção.
 * @param totalN Inteiro que representa as vendas normais do produto.
 * @param totalP Inteiro que representa as vendas em promoção do produto.
 * @param filial Inteiro que representa a filial.
 */
void printProductSalesAndProfit(char* productID, int month, int totalSalesN, int totalSalesP, double totalN, double totalP, int filial);

/**
 * @brief Imprime o número de produtos nunca comprados e de clientes que nunca compraram.
 * @param totalC Inteiro que representa o número de clientes.
 * @param totalP Inteiro que representa o número de produtos.
 */
void printClientsAndProductsNeverBoughtCount(int totalC, int totalP);
/**
 * @brief Imprime o número de vendas entre dois meses.
 * @param minMonth Inteiro que representa o mês. 
 * @param maxMonth Inteiro que representa o mês.
 * @param totalSales Inteiro que representa a quantidade total de produtos vendidos.
 * @param totalVendas Inteiro que representa o total de vendas.
 * @param totalFat Double que representa o total de faturação.
 */
void printSalesAndProfit(int minMonth, int maxMonth,int totalSales,int totalVendas, double totalFat);
/**
 * @brief Imprime as quantidades vendidas por mês e por filial, numa tabela.
 * @param values Matriz, cada linha representa uma filial, cada coluna representa um mês.
 */
void printQuantByBranch(int** values);
/**
 * @brief Imprime nova linha.
 */
void newLine(void);

/**
 * @brief Imprime o tipo de compra de um cliente e o respetivo produto.
 * @param prom String que armazena os tipos de compra de cada cliente.
 * @param id Array com códigos de cliente.
 * @param contador Inteiro que indica se efetuou compra na filial.
 */
void printProductBuyers(char* prom, char** id,int contador);

/**
 * @brief Imprime a filial.
 * 
 * @param i Inteiro que representa a filial.
 */
void printInFilial(int i);

/**
 * @brief Imprime os códigos do array.
 * @param array Array de códigos.
 * @param size Inteiro que representa o tamanho do array.
 * @param page Inteiro que guarda informação da página a imprimir.
 * @param tok Nome do produto a procurar.
 * @return Última página.
 */
int printProductsStartedByLetter(char** array, int size, int *page, char* tok);
/**
 * @brief Imprime os códigos do array.
 * @param array Array de códigos.
 * @param size Inteiro que representa o tamanho do array.
 * @param filial Número da filial.
 * @param page Número da página.
 * @param tok Código do produto.
 * @return Número de páginas. 
 */
int printProductsNeverBought(char** array, int size, int filial, int *page, char* tok);

/**
 * @brief Imprime os códigos de produto e as respetivas quantidades e o mês correspondente.
 * @param l Estrutura de códigos e quantidades.
 * @param month Inteiro que representa o mês.
 * @param page Número da página.
 * @param tok Código do produto.
 * @return Número de páginas.
 */
int printClientFavouriteProducts(CodigosQuant l, int month, int *page, char* tok);
/**
 * @brief Imprime os produtos da estrutura.
 * 
 * @param a Estrutura a ser imprimida.
 * @param n Número de elementos a imprimir.
 * @param page Número da página.
 * @param tok Código do produto.
 * @return Número de páginas.
 */
int printNProductsProdSales(ProdSales a, int n, int *page, char* tok);
/**
 * @brief Imprime os produtos da estrutura.
 * 
 * @param a Estrutura de dados.
 * @param limit Número de elementos a imprimir.
 * @param page Inteiro que guarda informação da página.
 * @param tok Nome do produto a procurar.
 * @return Ultima página.
 */
int printTopSelledProducts(ArvoreProdQuant* a, int limit, int* page, char* tok, int filial);

/**
 * @brief Imprime o conteúdo do array.
 * 
 * @param clientes Array com os clientes.
 * @param size Inteiro que representa o tamanho do array.
 * @param page Número da página atual.
 * @param tok Código do Cliente.
 * @return Número de páginas.
 */
int printClientsOfAllBranches(char** clientes,int size, int *page,char* tok);


#endif
