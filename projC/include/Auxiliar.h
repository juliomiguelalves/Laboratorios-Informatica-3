#ifndef auxiliar_h
#define auxiliar_h

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/**
 *  @brief Estrutura de dados que guarda a informação que relaciona produtos e o respetivo lucro 
 *  utilizando um array. 
 */

typedef struct ProdSales {
    int tam;
    int ocup;
    struct array{
        char* key;
        double profit;
    } *array;
} ProdSales;

/**
 * @brief Aloca memória e inicializa a estrutura.
 * @return Estrutura inicializada.
 */

ProdSales* initProdSales(void);
/**
 * @brief Coloca nas N primeiras posições os N produtos mais lucrativos.
 * @param a Array de estruturas ProdSales.
 * @param low Limite inferior do array.
 * @param max Limite superior do array.
 */
void quickSortNProdSales(ProdSales *a, int low, int max);

/** -------------------------------------------------------------------------------------- */

/**
 *  @brief Estrutura de dados que guarda a quantidade vendida de cada produto, 
 *  utilizando um array dinâmico.  
 */
typedef struct codigosQuant {
    int tam;
    int ocup;
    struct lista{
        char* key;
        int quant;
    } *lista;
} CodigosQuant;

/**
 * @brief Aloca memória e inicializa a estrutura.
 * @return Estrutura inicializada.
 */

CodigosQuant* initCodigosQuant(void);

/**
 * @brief Ordena um array de estruturas.
 * 
 * @param listaCodigos Array de estruturas.
 * @param low Inteiro que representa onde começa a ordenação.
 * @param high Inteiro que representa onde termina a ordenação.
 */
void quicksortCodigosQuant(CodigosQuant *listaCodigos,int low,int high);


/** -------------------------------------------------------------------------------------- */

/**
 * @brief Estrutura auxiliar baseada no algoritmo de árvores AVL que contém
 * campos para a realização da função getTopSelledProducts ordenada pela chave.
 * 
 */
typedef struct ArvoreKey {
    int height;
    char* key;
    int quant;
    char** clients;
    int clientNumb;
    int clientCap;
    struct ArvoreKey *left;
    struct ArvoreKey *right;
} *ArvoreProdKey;
/**
 * @brief Estrutura auxiliar baseada no algoritmo de árvores AVL que contém
 * campos para a realização da função getTopSelledProducts ordenada pela quantidade
 * e que contém chaves de produtos com a mesma quantidade.
 * 
 */
typedef struct ArvoreQuant {
    int height;
    char** keys;
    int * clientNumb;
    int quant;
    int index;
    int size;
    char** clients;
    int clientCap;
    struct ArvoreQuant *left;
    struct ArvoreQuant *right;
} *ArvoreProdQuant;

/**
 * @brief Cria um novo nodo da estrutura Arvore ordenada por quantidade.
 * 
 * @param key ID Produto.
 * @param quant Quantidade associada a esse produto.
 * @return Nodo.
 */
ArvoreProdQuant newNodeByQuant(char*key, int quant);
/**
 * @brief Cria um novo nodo da estrutura Arvore ordenada por chave.
 *
 * @param key ID Produto.
 * @param quant Quantidade associada a esse produto.
 * @return Nodo.
 */
ArvoreProdKey newNodeByKey(char*key, int quant);
/**
 * @brief Adiciona elementos na estrutura Arvore, tendo em conta a quantidade.
 * 
 * @param node Estrutura que vai receber o novo elemento.
 * @param key ID Produto.
 * @param quant Quantidade associada a esse produto.
 * @param a Endereço onde foi alocado o novo nodo.
 * @return ArvoreProd. 
 */
ArvoreProdQuant addArvoreByQuant(ArvoreProdQuant node, char* key, int quant, ArvoreProdQuant* a);
/**
 * @brief Copia Arvore ordenada por chave para outra Arvore ordenada pela quantidade.
 * 
 * @param node Estrutura destino.
 * @param source Estrutura fonte.
 * @return Estrutura final.
 */
ArvoreProdQuant addArvoreByQuantCopy(ArvoreProdQuant node, ArvoreProdKey source);
/**
 * @brief Adiciona elementos na estrutura Arvore, tendo em conta a key.
 * 
 * @param node Estrutura que vai receber o novo elemento.
 * @param key ID Produto.
 * @param quant Quantidade associada a esse produto.
 * @return ArvoreProd. 
 */
ArvoreProdKey addArvoreByKey(ArvoreProdKey node, char* key, int quant);
/**
 * @brief Desaloca memória de Arvore ordenada pela chave.
 * 
 * @param a Arvore a ser 'destruída'.
 */
void destroyArvoreByKey(ArvoreProdKey a);
/**
 * @brief Desaloca memória de Arvore ordenada pela quantidade.
 *
 * @param a Arvore a ser 'destruída'.
 */
void destroyArvoreByQuant(ArvoreProdQuant a);
/**
 * @brief Insere em dest os N produtos mais comprados de ini, sendo N o parâmetro limit.
 * 
 * @param dest Arvore que recebe os elementos.
 * @param ini Arvore que fornece os elementos
 * @param limit Número de elementos a fornecer.
 * @param done Número de elementos que forneceu até ao momento.
 */
void getLimitTree(ArvoreProdKey *dest,ArvoreProdQuant ini,int limit,int *done);
/**
 * @brief Faz uma cópia da Arvore source para dest, tendo em conta a quantidade.
 * 
 * @param dest Estrutura para onde vai ser copiada.
 * @param source Estrutura fonte.
 */
void copyTreeByQuant(ArvoreProdQuant *dest, ArvoreProdKey source);
/**
 * @brief Procura uma key na estrutura Arvore ordenada por chave.
 * 
 * @param a Arvore que vai ser percorrida.
 * @param key Product ID.
 * @return Um endereço se encontrar ou NULL caso contrário.
 */
ArvoreProdKey lookupArvoreByKey(ArvoreProdKey a, char* key);
/**
 * @brief Procura uma key na estrutura Arvore ordenada por quantidade.
 *
 * @param a Arvore que vai ser percorrida.
 * @param key Product ID.
 * @return Um endereço se encontrar ou NULL caso contrário.
 */
ArvoreProdQuant lookupArvoreByQuant(ArvoreProdQuant a, char* key);


/** -------------------------------------------------------------------------------------- */


#endif
