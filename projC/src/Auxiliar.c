#include "Auxiliar.h"


ProdSales* initProdSales() {
    ProdSales *a;
    a = malloc(sizeof(struct ProdSales));
    a->array = malloc(sizeof(struct array));
    a->array[0].profit = 0;
    a->tam = 1;
    a->ocup = 0;
    return a;
}

/**
 * @brief Faz o swap de duas estruturas de dados. 
 * 
 * @param a Estrutura de dados
 * @param b Estrutura de dados
 */

static void swap(struct array *a, struct array *b) {
    struct array p;
    p.key = strdup(a->key);
    free(a->key);
    p.profit = a->profit;
    a->key = strdup(b->key);
    free(b->key);
    a->profit=b->profit;
    b->key = strdup(p.key);
    b->profit=p.profit;
    free(p.key);
}

/**
 * @brief Ordena um array entre 2 posições. 
 * 
 * @param a Estrutura de dados
 * @param low inteiro número 1
 * @param max inteiro número 2
 * @return índice da posição onde termina a ordenação
 */

static int partition(ProdSales *a, int low, int max) {
    int i,j=low;
    for(i=low;i<max-1;i++) {
        if(a->array[i].profit>a->array[max-1].profit) swap(&a->array[i],&a->array[j++]);
    }
    swap(&a->array[j],&a->array[i]);
    return j;
}

void quickSortNProdSales(ProdSales *a, int low, int max) {
    int i=0;
    if(low < max) {
        i = partition(a, low, max);
        quickSortNProdSales(a, i+1, max);
        quickSortNProdSales(a, low, i);
    }
}

/** ------------------------------------------------------------------------------------------ */

CodigosQuant* initCodigosQuant(){
    CodigosQuant *a;
    a=malloc(sizeof(struct codigosQuant));
    a->tam=1;
    a->ocup=0;
    a->lista=malloc(sizeof(struct lista));
    return a;
}
/**
* @brief Faz o swap de duas estruturas de dados.
*
* @param a Estrutura de dados
* @param b Estrutura de dados
*/
static void swapCodQuant(struct lista *a,struct lista *b) {
    struct lista p;
    p.quant=a->quant;
    p.key = strdup(a->key);
    free(a->key);
    a->quant=b->quant;
    a->key = strdup(b->key);
    free(b->key);
    b->quant = p.quant;
    b->key = strdup(p.key);
    free(p.key);
}

/**
 * @brief Ordena um array entre 2 posições. 
 * 
 * @param a Estrutura de dados
 * @param low inteiro número 1
 * @param max inteiro número 2
 * @return índice da posição onde termina a ordenação
 */

static int partitionCodQuant(CodigosQuant *a, int low, int max) {
    int i,j=low;
    for(i=low;i<max-1;i++) {
        if(a->lista[i].quant > a->lista[max-1].quant) swapCodQuant(&(a->lista[i]),&(a->lista[j++]));
    }
    swapCodQuant(&(a->lista[j]),&(a->lista[i]));
    return j;
}

void quicksortCodigosQuant(CodigosQuant *listaCodigos,int low,int high) {
    int i = 0;
    if(low < high) {
        i = partitionCodQuant(listaCodigos,low,high);
        quicksortCodigosQuant(listaCodigos,low,i);
        quicksortCodigosQuant(listaCodigos,i+1,high);
    }
}

/** ------------------------------------------------------------------------------------------ */


ArvoreProdQuant newNodeByQuant(char*key, int quant) {
    ArvoreProdQuant p;
    p = malloc(sizeof(struct ArvoreQuant));
    p->clients = malloc(sizeof(char*));
    p->clientNumb = 0;
    p->clientCap = 1;
    p->keys = malloc(sizeof(char*));
    p->keys[0] = strdup(key);
    p->index = 1;
    p->size = 1;
    p->height = 1;
    p->left=p->right=NULL;
    p->quant = quant;
    return p;
}

ArvoreProdKey newNodeByKey(char*key, int quant) {
    ArvoreProdKey p;
    p = malloc(sizeof(struct ArvoreKey));
    p->clients = malloc(sizeof(char*));
    p->clientNumb = 0;
    p->clientCap = 1;
    p->key = strdup(key);
    p->quant = quant;
    p->height = 1;
    p->left=p->right=NULL;
    return p;
}

/**
 * @brief Calcula a altura da árvore que compõe a estrutura de dados.
 *
 * @param a Árvore de clientes.
 * @return Inteiro correspondente à altura da árvore.
 */

static int altura(ArvoreProdQuant a) {
    int i;
    /** A altura será 0 se alcançar uma folha. */
    if(a==NULL) {
        i=0;
    }
    else i=a->height;
    return i;
}

/**
 * @brief Máximo entre dois inteiros.
 *
 * @param a Inteiro número 1.
 * @param b Inteiro número 2.
 * @return Inteiro maior do dois.
 */

static int max(int a,int b) {
    return (a>b?a:b);
}

/**
 * @brief Roda a árvore em questão para a direita de forma a gerir o balanceamento da mesma.
 *
 * @param y Árvore de clientes.
 * @return Árvore equilibrada de clientes.
 */

static ArvoreProdQuant rotateR(ArvoreProdQuant y)
{
    ArvoreProdQuant x = y->left;
    ArvoreProdQuant T2 = x->right;
    /** Realiza a rotação. */
    x->right = y;
    y->left = T2;
    /** Atualiza as alturas. */
    y->height = max(altura(y->left), altura(y->right))+1;
    x->height = max(altura(x->left), altura(x->right))+1;
    return x;
}

/**
 * @brief Roda a árvore em questão para a esquerda de forma a gerir o balanceamento da mesma.
 *
 * @param x Árvore de clientes.
 * @return Nova raiz.
 */

static ArvoreProdQuant rotateL(ArvoreProdQuant x)
{
    ArvoreProdQuant y = x->right;
    ArvoreProdQuant T2 = y->left;
    /** Realiza a rotação. */
    y->left = x;
    x->right = T2;
    /** Atualiza as alturas. */
    x->height = max(altura(x->left), altura(x->right))+1;
    y->height = max(altura(y->left), altura(y->right))+1;
    return y;
}

ArvoreProdQuant addArvoreByQuant(ArvoreProdQuant node, char* key, int quant ,ArvoreProdQuant* a) {
    /** Inserção normal de um elemento numa árvore de procura. */
    if (node == NULL){
        ArvoreProdQuant c;
        c = newNodeByQuant(key,quant);
        if(a!=NULL)
            *a = c;
        return c;
    }
    if (quant < node->quant)
        node->left  = addArvoreByQuant(node->left, key, quant, a);
    else if (quant > node->quant)
        node->right = addArvoreByQuant(node->right, key, quant, a);
    else {
        if(node->index == node->size) {
            node->size *= 2;
            node->keys = realloc(node->keys,(node->size) * sizeof(char*));
        }
        node->keys[(node->index)++] = strdup(key);
        return node;
    }
    /** Atualização de alturas. */
    node->height = 1 + max(altura(node->left),
                           altura(node->right));
    /** Verificação do fator de balanceamento. */
    int balance = (altura(node->left))-(altura(node->right));
    /** Rotações de acordo com os 4 casos possíveis. */
    if (balance > 1 && quant < node->quant)
        return rotateR(node);
    if (balance < -1 && quant > node->quant)
        return rotateL(node);
    if (balance > 1 && quant > node->left->quant)
    {
        node->left =  rotateL(node->left);
        return rotateR(node);
    }
    if (balance < -1 && quant < node->right->quant)
    {
        node->right = rotateR(node->right);
        return rotateL(node);
    }
    return node;
}

/**
 * @brief Calcula a altura da árvore que compõe a estrutura de dados.
 *
 * @param a Árvore de clientes.
 * @return Inteiro correspondente à altura da árvore.
 */

static int alturaKey(ArvoreProdKey a) {
    int i;
    /** A altura será 0 se alcançar uma folha. */
    if(a==NULL) {
        i=0;
    }
    else i=a->height;
    return i;
}

/**
 * @brief Máximo entre dois inteiros.
 *
 * @param a Inteiro número 1.
 * @param b Inteiro número 2.
 * @return Inteiro maior do dois.
 */

static int maxKey(int a,int b) {
    return (a>b?a:b);
}

/**
 * @brief Roda a árvore em questão para a direita de forma a gerir o balanceamento da mesma.
 *
 * @param y Árvore de clientes.
 * @return Árvore equilibrada de clientes.
 */

static ArvoreProdKey rotateRKey(ArvoreProdKey y)
{
    ArvoreProdKey x = y->left;
    ArvoreProdKey T2 = x->right;
    /** Realiza a rotação. */
    x->right = y;
    y->left = T2;
    /** Atualiza as alturas. */
    y->height = maxKey(alturaKey(y->left), alturaKey(y->right))+1;
    x->height = maxKey(alturaKey(x->left), alturaKey(x->right))+1;
    return x;
}

/**
 * @brief Roda a árvore em questão para a esquerda de forma a gerir o balanceamento da mesma.
 *
 * @param x Árvore de clientes.
 * @return Nova raiz.
 */

static ArvoreProdKey rotateLKey(ArvoreProdKey x)
{
    ArvoreProdKey y = x->right;
    ArvoreProdKey T2 = y->left;
    /** Realiza a rotação. */
    y->left = x;
    x->right = T2;
    /** Atualiza as alturas. */
    x->height = maxKey(alturaKey(x->left), alturaKey(x->right))+1;
    y->height = maxKey(alturaKey(y->left), alturaKey(y->right))+1;
    return y;
}

ArvoreProdKey addArvoreByKey(ArvoreProdKey node, char* key, int quant) {
    /** Inserção normal de um elemento numa árvore de procura. */
    if (node == NULL){
        ArvoreProdKey c;
        c = newNodeByKey(key,quant);
        return c;
    }
    if (strcmp(key,node->key)<0)
        node->left  = addArvoreByKey(node->left, key, quant);
    else if (strcmp(key,node->key)>0)
        node->right = addArvoreByKey(node->right, key, quant);
    else return node;
    /** Atualização de alturas. */
    node->height = 1 + maxKey(alturaKey(node->left),
                              alturaKey(node->right));
    /** Verificação do fator de balanceamento. */
    int balance = (alturaKey(node->left))-(alturaKey(node->right));
    /** Rotações de acordo com os 4 casos possíveis. */
    if (balance > 1 && strcmp(key,node->left->key)<0)
        return rotateRKey(node);
    if (balance < -1 && strcmp(key,node->right->key)>0)
        return rotateLKey(node);
    if (balance > 1 && strcmp(key,node->left->key)>0)
    {
        node->left =  rotateLKey(node->left);
        return rotateRKey(node);
    }
    if (balance < -1 && strcmp(key,node->right->key)<0)
    {
        node->right = rotateRKey(node->right);
        return rotateLKey(node);
    }
    return node;
}

ArvoreProdQuant addArvoreByQuantCopy(ArvoreProdQuant node, ArvoreProdKey source) {
    /** Inserção normal de um elemento numa árvore de procura. */
    if (node == NULL){
        ArvoreProdQuant c;
        c = malloc(sizeof(struct ArvoreQuant));
        c->clientNumb = malloc(sizeof(int));
        c->clientNumb[0] = source->clientNumb;
        c->height = 1;
        c->keys = malloc(sizeof(char*));
        c->keys[0] = strdup(source->key);
        c->index = 1;
        c->size = 1;
        c->quant = source->quant;
        c->left=c->right=NULL;
        return c;
    }
    if (source->quant < node->quant)
        node->left  = addArvoreByQuantCopy(node->left, source);
    else if (source->quant > node->quant)
        node->right = addArvoreByQuantCopy(node->right, source);
    else {
        /**Atualização da memória alocada quando a capacidade máxima foi atingida */
        if(node->index == node->size) {
            node->size *= 2;
            node->keys = realloc(node->keys, (node->size) * sizeof(*(node->keys)));
            node->clientNumb = realloc(node->clientNumb, (node->size) * sizeof(*(node->clientNumb)));
        }
        node->clientNumb[(node->index)] = source->clientNumb;
        node->keys[(node->index)++] = strdup(source->key);
        return node;
    }
    /** Atualização de alturas. */
    node->height = 1 + max(altura(node->left),
                           altura(node->right));
    /** Verificação do fator de balanceamento. */
    int balance = (altura(node->left))-(altura(node->right));
    /** Rotações de acordo com os 4 casos possíveis. */
    if (balance > 1 && source->quant < node->quant)
        return rotateR(node);
    if (balance < -1 && source->quant > node->quant)
        return rotateL(node);
    if (balance > 1 && source->quant > node->left->quant)
    {
        node->left =  rotateL(node->left);
        return rotateR(node);
    }
    if (balance < -1 && source->quant < node->right->quant)
    {
        node->right = rotateR(node->right);
        return rotateL(node);
    }
    return node;
}

void destroyArvoreByKey(ArvoreProdKey a) {
    if(a) {
        destroyArvoreByKey(a->left);
        destroyArvoreByKey(a->right);
        int i;
        for(i=a->clientNumb-1;i>=0;i--)
            free(a->clients[i]);
        free(a->clients);
        free(a->key);
        free(a);
    }
}

void destroyArvoreByQuant(ArvoreProdQuant a) {
    if(a) {
        destroyArvoreByQuant(a->left);
        destroyArvoreByQuant(a->right);
        int i;
        for(i=a->index-1;i>=0;i--)
            free(a->keys[i]);
        free(a->keys);
        free(a->clientNumb);
        free(a);
    }
}

void getLimitTree(ArvoreProdKey *dest,ArvoreProdQuant ini,int limit,int *done) {
    if(ini && limit != *done) {
        getLimitTree(dest, ini->right, limit, done);
        if(limit != *done) {
            while(ini->index != 0 && *done != limit) {
                (ini->index)--;
                /**Adiciona à arvore de keys os n maiores elementos */
                *dest = addArvoreByKey(*dest, ini->keys[ini->index], ini->quant);
                (*done)++;
            }
        }
        getLimitTree(dest, ini->left, limit, done);
    }
}

void copyTreeByQuant(ArvoreProdQuant *dest, ArvoreProdKey source) {
    if(source) {
        copyTreeByQuant(dest, source->left);
        *dest = addArvoreByQuantCopy(*dest,source);
        copyTreeByQuant(dest, source->right);
    }
}

ArvoreProdKey lookupArvoreByKey(ArvoreProdKey a, char* key) {
    ArvoreProdKey f = a;
    int breakpoint = 0, cmp;
    while(f != NULL && breakpoint == 0) {
        cmp = strcmp(key,f->key);
        if(cmp > 0) f = f->right;
        else if(cmp < 0) f = f->left;
        else breakpoint = 1;
    }
    return f;
}
