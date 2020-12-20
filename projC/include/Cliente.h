#ifndef Cliente_h
#define Cliente_h

#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h> 
#include "Auxiliar.h"


typedef struct AVL *Cliente;

/**
 * @brief Insere um cliente em Cliente.
 * 
 * @param node Estrutura de dados que vai receber a nova entrada.
 * @param key ID do cliente a ser adicionado.
 */
void insertCliente(Cliente* node,char* key);
/**
 * @brief Desaloca memória previamente alocada para a estrutura que guarda o catálogo de clientes.
 * 
 * @param a Estrutura de clientes.
 */
void destroyCliente(Cliente a);
/**
 * @brief Procura na Estrutura de dados se existe um Cliente com um dado ID.
 * 
 * @param a Estrutura de Clientes.
 * @param key ID a procurar.
 * @return booleano resultante.
 */
int findCli(Cliente a, char* key);
/**
 * @brief Preenche \p array com os ID de cliente guardados em \p c.
 * 
 * @param c Catálogo de CLientes.
 * @param array Array de ID's de Clientes.
 * @param index Índice do próximo elemento vazio do array.
 * @param size Capacidade do array.
 * @return Array de ID's de Clientes preenchido.
 */
char** fillArray(Cliente c, char** array, int* index, int* size);

#endif 
