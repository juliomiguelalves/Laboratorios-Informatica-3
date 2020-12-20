#ifndef catalogocliente_h
#define catalogocliente_h

#include "Cliente.h"
/**
 *  @brief Estrutura de dados que para guarda cada cliente dada a sua letra inicial, 
 *  utilizando um array com tantas posições quantas letras existem no alfabeto. 
 */
typedef Cliente *CatalogoCliente;


/**
 * @brief Inicializador da estrutura de dados do Catálogo de Clientes.
 *
 * @return CatalogoCliente 
 */
CatalogoCliente initCatalogoCliente(void);

/**
 * @brief Desalocação de memória de CatálogoCliente.
 * 
 * @param c Catálogo de Clientes.
 */
void destroyCatalogoCliente(CatalogoCliente c);

/**
 * @brief Leitura do ficheiro de clientes para o Catálogo de Clientes.
 * 
 * @param c Catálogo de Clientes.   
 * @param f Ficheiro de clientes.
 */
void readFiletoCatalogoCliente(CatalogoCliente c, FILE* f);

/**
 * @brief Procura de um determinado cliente no Catálogo de Clientes.
 * 
 * @param a Catálogo de Clientes.
 * @param key ID do cliente a procurar.
 * @return 1 se encontrou ou 0 caso contrário.
 */
int findCliCat(CatalogoCliente a, char* key); 
/**
 * @brief Preenchimento de um array com o conteúdo do catálogo de clientes ordenado de forma crescente.
 * 
 * @param c Catálogo de Clientes.
 * @param size Tamanho do array.
 * @return Array de códigos de clientes.
 */
char** getCatalogoClientes(CatalogoCliente c, int* size);




#endif
