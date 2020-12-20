#ifndef CatalogoProduto_h
#define CatalogoProduto_h

#include "Produto.h"

/**
 *  @brief Estrutura de dados que para guarda cada produto dada a sua letra inicial, 
 *  utilizando um array com tantas posições quantas letras existem no alfabeto. 
 */
typedef Produto *CatalogoProduto;

/**
 * @brief Inicializador da estrutura de dados do Catálogo de Clientes.
 *
 * @return CatalogoProdutos.
 */
CatalogoProduto initCatalogoProduto(void);
/**
 * @brief Desalocação de memória ocupada por cada produto existente no Catálogo.
 * 
 * @param p Catálogo de produtos.
 */
void destroyCatalogoProduto(CatalogoProduto p);
/**
 * @brief Insere um código de produto no catálogo de produtos.
 * 
 * @param a Estrutura de Produtos.
 * @param x Código do produto.
 */
void insertCatalogoProduto (CatalogoProduto a,char* x);
/**
 * @brief Procura de um determinado produto no Catálogo de Produtos.
 * 
 * @param a Catálogo de Produtos.
 * @param key ID do produto a adicionar.
 * @return 1 se encontrou ou 0 se não encontrou.
 */
int findProdCat(CatalogoProduto a, char* key);
/**
 * @brief Preenchimento de um array com o conteúdo do catálogo de produtos ordenado de forma crescente.
 * 
 * @param p Catálogo de Produtos.
 * @param size Tamanho do array.
 * @return Array de códigos de produtos.
 */
char** getCatalogoProdutos(CatalogoProduto p, int* size);
/**
 * @brief Obtém no catálogo de produtos os códigos de produto começados por determinada letra. 
 * 
 * @param p Catálogo de Produtos.
 * @param size Tamanho do array.
 * @param letter Número ASCII correspondente ao carater.
 * @return Array de códigos de produtos começados com a letra dada.
 */
char** getProductsLetter(CatalogoProduto p, int* size, int letter);

#endif
