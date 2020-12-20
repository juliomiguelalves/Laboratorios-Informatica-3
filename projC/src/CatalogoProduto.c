#include "CatalogoProduto.h"


CatalogoProduto initCatalogoProduto(){
    CatalogoProduto p;
    int i;
    p=malloc(26*sizeof(Produto));
    for(i=0;i<26;i++) {
        p[i]=NULL;
    }
    return p;
}


void destroyCatalogoProduto(CatalogoProduto p){
    int i;
    for(i = 0; i < 26; i++) {
       destroyProduto(p[i]);
    }
    free(p);
}


void insertCatalogoProduto(CatalogoProduto p, char* key){
    insertProduto(&p[key[0]-65],key);
}


int findProdCat(CatalogoProduto a, char* key){
    return findProd(a[key[0]-65],key);
}

char** getCatalogoProdutos(CatalogoProduto p, int* size){
    int i;
    char ** array;
    int index = 0;
    *size = 1;
    array = malloc(sizeof(char*));
    for(i=0;i<26;i++) {
        array = fillArrayP(p[i], array, &index, size);
    }
    *size = index;
    return array;
}

char** getProductsLetter(CatalogoProduto p, int* size, int letter) {
    int index = 0;
    char** array;
    *size = 1;
    array = malloc(sizeof(char*));
    array = fillArrayP(p[letter-65],array,&index,size);
    *size = index;
    return array;
}
