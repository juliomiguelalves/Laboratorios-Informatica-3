#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "Faturacao.h"

/**
 *  @brief Estrutura de dados que guarda as informações relativas às vendas dos produtos
 *  dividas por filial e mês, utilizando árvores balanceadas. * 
 */
struct Faturacao {
    char* key;
    int height;
    FaturacaoFilial array;
    struct Faturacao* right;
    struct Faturacao* left;
};

Faturacao initFaturacao(){
    Faturacao f = NULL;
    return f;
}

void destroyFaturacao(Faturacao* f){
    if(*f) {
    destroyFaturacao(&((*f)->left));
    destroyFaturacao(&((*f)->right));
    destroyFaturacaoFilial((*f)->array);
    free(*f);
    }
}

void insertFaturacao(char *prod, float price, int quant,char prom, int mes, int filial, Faturacao f)
{
    Faturacao fat;
    int flag = 0, cmp = 0;
    for(fat = f; fat != NULL && flag == 0;) {
        cmp = strcmp(prod,fat->key);
        if(cmp > 0) fat = fat->right;
        else if(cmp < 0) fat = fat->left;
        else flag++;
    }
    insertFaturacaoFilial(fat->array,price,quant,prom,mes,filial);
}

/**
 * @brief Calcula a altura da árvore que compõe a estrutura de dados.
 * 
 * @param a Árvore da faturação.
 * @return Inteiro correspondente à altura da árvore.
 */
static int altura(Faturacao a) {
    int i;
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
 * @param y Árvore de faturação.
 * @return Árvore equilibrada de faturação.
 */
static Faturacao rotateR(Faturacao y)
{
    Faturacao x = y->left;
    Faturacao T2 = x->right;
    x->right = y;
    y->left = T2;
    y->height = max(altura(y->left), altura(y->right))+1;
    x->height = max(altura(x->left), altura(x->right))+1;
    return x;
}

/**
 * @brief Roda a árvore em questão para a esquerda de forma a gerir o balanceamento da mesma.
 * 
 * @param x Árvore de faturação.
 * @return Árvore equilibrada de faturação.
 */
static Faturacao rotateL(Faturacao x)
{
    Faturacao y = x->right;
    Faturacao T2 = y->left;
    y->left = x;
    x->right = T2;
    x->height = max(altura(x->left), altura(x->right))+1;
    y->height = max(altura(y->left), altura(y->right))+1;
    return y;
}

/**
 * @brief Insere um novo nodo faturação relativo ao produto com o dado código. 
 * 
 * @param f Árvore de faturação.
 * @param key código do produto.
 * @return Árvore de faturação atualizada.
 */
static Faturacao insertProdutoFaturacaoS (Faturacao f, char* key){
    /** Inserção normal de um elemento numa árvore de procura. */
    if (f == NULL){
        Faturacao new=NULL;
        new=malloc(sizeof(struct Faturacao));
        new->key=strdup(key);
        new->right=new->left=NULL;
        new->height=1;
        new->array=initFaturacaoFilial();
        f=new;
        return f;
    }
    if (strcmp(key,f->key)<0)
        f->left  = insertProdutoFaturacaoS(f->left, key);
    else if (strcmp(key,f->key)>0)
        f->right = insertProdutoFaturacaoS(f->right, key);
    else
        return f;
    /** Atualização de alturas. */
    f->height = 1 + max(altura(f->left),
                           altura(f->right));
    /** Verificação do fator de balanceamento. */
    int balance = (altura(f->left))-(altura(f->right));
    /** Rotações de acordo com os 4 casos possíveis. */
    if (balance > 1 && strcmp(key,f->left->key)<0)
        return rotateR(f);
    if (balance < -1 && strcmp(key,f->right->key)>0)
        return rotateL(f);
    if (balance > 1 && strcmp(key,f->left->key)>0)
    {
        f->left =  rotateL(f->left);
        return rotateR(f);
    }
    if (balance < -1 && strcmp(key,f->right->key)<0)
    {
        f->right = rotateR(f->right);
        return rotateL(f);
    }
    return f;
}

void insertProdutoFaturacao(Faturacao* f, char* key) {
    *f = insertProdutoFaturacaoS(*f,key);
}

void salesAndProfitFaturacao(Faturacao a, int mes,int filial, double *totalN, double *totalP, char* key) {
    int cmp;
    if(a) {
        cmp = strcmp(key, a->key);
        if(cmp<0) salesAndProfitFaturacao(a->left,mes,filial, totalN, totalP, key);
        else if (cmp>0) salesAndProfitFaturacao(a->right, mes, filial, totalN, totalP, key);
        else salesAndProfitFiliais(a->array, totalN, totalP, filial,mes);
    }
}

int monthlySalesAndProfitFaturacao(Faturacao a,int mes,int filial,double *totalFat) {
    
    int totalSales =0;
    if(a)  {
        totalSales += monthlySalesAndProfitFaturacao(a->left,mes,filial,totalFat);
        totalSales += monthlysalesAndProfitFiliais(a->array,totalFat,filial,mes);
        totalSales += monthlySalesAndProfitFaturacao(a->right,mes,filial,totalFat);
    }
    return totalSales;
}

char** getProductsNeverBoughtFact(Faturacao f, int branchID, int*size, char** array,int *index) { 
    if(f) {
        array = getProductsNeverBoughtFact(f->left,branchID,size,array,index);
        int bought;
        bought = isProductBoughtFilial(f->array,branchID);
        if(!bought) {
            if((*index) == (*size)) {
                /** Duplica o tamanho do array. */
                (*size) *= 2;
                array = realloc(array,(*size) * sizeof(char*));
            }
            /** Insere o produto no array. */
            array[(*index)] = strdup(f->key);
            (*index)++;
        }
        array = getProductsNeverBoughtFact(f->right,branchID,size,array,index);
    }
    return array;
}

int productsNeverBoughtCount(Faturacao f) {
    int total = 0;
    if(f) {
        total += productsNeverBoughtCount(f->left);
        if(!(isProductBoughtFilial(f->array,0))) total++;
        total += productsNeverBoughtCount(f->right);
    }
    return total;
}

void getQuantitiesFact(Faturacao f,ArvoreProdQuant* p) {
    if(f) {
        getQuantitiesFact(f->left,p);
        getQuantitiesFilial(f->array,f->key,p);
        getQuantitiesFact(f->right,p);
    }
}
