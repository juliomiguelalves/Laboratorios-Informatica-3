#include "CatalogoCliente.h"


CatalogoCliente initCatalogoCliente(){
    CatalogoCliente c;
    int i;
    c=malloc(26*sizeof(Cliente));
    for(i=0;i<26;i++) {
        c[i]=NULL;
    }
    return c;
}

void destroyCatalogoCliente(CatalogoCliente c){
    int i;
    for(i=25;i>=0;i--) 
        destroyCliente(c[i]);
    free(c);
}

void readFiletoCatalogoCliente(CatalogoCliente c, FILE* f){
    char*buffer;
    buffer=(char*) malloc(9*sizeof(char));
    while(feof(f)==0) {
        buffer= fgets(buffer,8,f);
        if(buffer != NULL) {
            strtok(buffer," \r\n");
            insertCliente(&c[buffer[0]-65],buffer);
        }
    }
    free(buffer);
}

int findCliCat(CatalogoCliente a, char* key){
    return findCli(a[key[0]-65],key);
}

char** getCatalogoClientes(CatalogoCliente c, int* size) {
    int i, index = 0;
    *size = 1;
    char** array;
    array = malloc(sizeof(char*));
    for(i=0;i<26;i++) {
        array = fillArray(c[i],array,&index,size);
    }
    *size = index;
    return array;
}
