#include "Produto.h"

/**
 *  @brief Estrutura de dados que mantém o catálogo de Produtos organizado utilizando árvores balanceadas.
 *  Esta estrutura de dados foi escolhida perante as circunstâncias da situação, uma vez que se quer obter bons tempos
 *  de resposta no varrimento, procura e inserção de dados.
 * 
 */
struct AVL {
    char* key;
    int height;
    struct AVL* right;
    struct AVL* left;
};

void destroyProduto(Produto a) {
    if(a) {
        destroyProduto(a->left);
        destroyProduto(a->right);
        free(a);
    }
}

/**
 * @brief Calcula a altura da árvore que compõe a estrutura de dados.
 * 
 * @param a Árvore de produtos.
 * @return Inteiro correspondente à altura da árvore.
 */

static int altura(Produto a) {
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
 * @param y Árvore de produtos.
 * @return Árvore equilibrada de produtos.
 */
static Produto rotateR(Produto y)
{
    Produto x = y->left;
    Produto T2 = x->right;
    x->right = y;
    y->left = T2;
    y->height = max(altura(y->left), altura(y->right))+1;
    x->height = max(altura(x->left), altura(x->right))+1;
    return x;
}

/**
 * @brief Roda a árvore em questão para a esquerda de forma a gerir o balanceamento da mesma.
 * 
 * @param x Árvore de produtos.
 * @return Nova raiz.
 */

static Produto rotateL(Produto x)
{
    Produto y = x->right;
    Produto T2 = y->left;
    /** Realiza a rotação. */
    y->left = x;
    x->right = T2;
    /** Atualiza as alturas. */
    x->height = max(altura(x->left), altura(x->right))+1;
    y->height = max(altura(y->left), altura(y->right))+1;
    return y;
}

static Produto insertProdutoS(Produto node,char* key)
{
     /** Inserção normal de um elemento numa árvore de procura. */
    if (node == NULL){
        Produto new=NULL;
        new=malloc(sizeof(struct AVL));
        new->key=strdup(key);
        new->right=new->left=NULL;
        new->height=1;
        node=new;
        return node;
    }
    if (strcmp(key,node->key)<0)
        node->left  = insertProdutoS(node->left, key);
    else if (strcmp(key,node->key)>0)
        node->right = insertProdutoS(node->right, key);
    else
        return node;
    /** Atualização de alturas. */
    node->height = 1 + max(altura(node->left),
                           altura(node->right));
    /** Verificação do fator de balanceamento. */
    int balance = (altura(node->left))-(altura(node->right));
    /** Rotações de acordo com os 4 casos possíveis. */
    if (balance > 1 && strcmp(key,node->left->key)<0)
        return rotateR(node);
    if (balance < -1 && strcmp(key,node->right->key)>0)
        return rotateL(node);
    if (balance > 1 && strcmp(key,node->left->key)>0)
    {
        node->left =  rotateL(node->left);
        return rotateR(node);
    }
    if (balance < -1 && strcmp(key,node->right->key)<0)
    {
        node->right = rotateR(node->right);
        return rotateL(node);
    }
    return node;
}

int findProd(Produto a, char* key) {
    Produto b=a;
    int cmp = 0, breakflag = 0;
    /** Procura da chave na Estrutura. */
    while(b!=NULL && breakflag == 0) {
        cmp = strcmp(key,b->key);
        if(cmp > 0) b=b->right;
        else if(cmp < 0) b=b->left;
        else breakflag=1;
    }
    return breakflag;
}

char** fillArrayP(Produto p, char** array, int* index, int* size) {
    if(p) {
        array = fillArrayP(p->left,array,index,size);
        /** Duplica o array se tiver cheio */
        if(*index == *size) {
            *size *= 2;
            array = (char**) realloc(array,((*size)) * sizeof(char*));
        }
        /** Inserção no array */
        array[*index] = strdup(p->key);
        (*index)++;
        array = fillArrayP(p->right,array,index,size);
    }
    return array;
}

void insertProduto(Produto* p, char* key) {
    *p = insertProdutoS(*p,key);
}
