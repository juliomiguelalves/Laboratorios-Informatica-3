#ifndef Produto_h
#define Produto_h

#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "Auxiliar.h"

typedef struct AVL *Produto;

/**
 * @brief Desaloca memória préviamente alocada para a estrutura que guarda o catálogo de produtos.
 * 
 * @param p Estrutura de produto.
 */
void destroyProduto(Produto p);
/**
 * @brief Procura na Estrutura de dados se existe um Produto com um dado ID.
 * 
 * @param a Estrutura de Produtos.
 * @param key ID a procurar.
 * @return 1 se encontrou e 0 caso contrário.
 */
int findProd(Produto a, char* key); 
/**
 * @brief Insere um produto em Produto.
 * 
 * @param node Estrutura de dados que vai receber a nova entrada.
 * @param key ID do produto a ser adicionado.
 */
void insertProduto(Produto* node,char* key);
/**
 * @brief Preenche \p p com os ID de Produto guardados em \p s.
 * 
 * @param p Catálogo de Produtos.
 * @param array Array de ID's de Produtos.
 * @param index Índice da próxima posição livre do array.
 * @param size Capacidade do array.
 * @return Array de ID's de Produtos preenchido.
 */
char** fillArrayP(Produto p, char** array, int* index, int*size);

#endif 
