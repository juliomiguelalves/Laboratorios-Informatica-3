#include "Filiais.h"


Filiais initFiliais() {
    Filiais a;
    int i;
    a=malloc(NRFILIAIS*sizeof(Meses));
    for(i=0;i<NRFILIAIS;i++)
        a[i]=initMeses();

    return a;
}

void destroyFiliais(Filiais v) {
    int i;
    for(i=NRFILIAIS-1;i>=0;i--) {
        destroyMeses(v[i]);
    }
    free(v);
}

void insertClienteFilial(char* key, int mes, int filial, Filiais f) {
    insertClienteMes(key,mes,f[filial-1]);
}

void insertProdutoFilial(char * cli, char * prod, float price, int quant, char promo, int mes, int filial, Filiais f) {
    insertProdutoMes(cli,prod,price,quant,promo,mes,f[filial-1]);
}


ProdSales getClientTopProfitProductsBranches(Filiais f, char * key, int* n) {
    ProdSales *a;
    int i,length = 0;
    a = initProdSales();
    for(i=0;i<NRFILIAIS;i++) 
        fillProdSalesMonth(f[i],key,a);
    length = a->ocup;
    quickSortNProdSales(a,0,length);
    if(length < *n) *n = length;
    return *a;
}


char** productBuyersOnFiliais (Filiais a,int branch, char* productID,int *contador,char* promo) {
    char** array;
    array=productBuyersOnMeses(a[branch],productID, contador,promo);

    return array;
}


CodigosQuant clientFavouriteProducts(Filiais f, char* clientID, int month){
    int i;
    CodigosQuant* listaCodigos;
    listaCodigos = initCodigosQuant();
    for(i=0; i<NRFILIAIS; i++){
       getClienteFavouriteProductsMeses(f[i], clientID, month, listaCodigos);
    }
    quicksortCodigosQuant(listaCodigos, 0, listaCodigos->ocup);
    return *listaCodigos;
}

int clientsNeverBoughtCount(Filiais f, char** listaClientes, int tam) {
    int bought, total = 0,i,j;
    for(i = 0; i<tam;i++) {
        bought = 0;
        for(j = 0; j < NRFILIAIS && bought == 0; j++) {
            bought = isBoughtMes(f[j],listaClientes[i]);
        }
        if(!bought) total++;
    }
    return total;
        
}

char** clientsOfAllBranches(Filiais f, char** listaClientes, int tamClientes, int*size){
    int i;
    int index=0;
    int filial;
    int vendaIncompleta=0;
    int vendaCompleta=0;
    char** array;
    *size = 1;
    array = malloc(sizeof(char*));
    for(i=0; i<tamClientes; i++)
    {
        vendaCompleta = 0;
        for(filial=0; filial<NRFILIAIS && vendaIncompleta==0; filial++)
        {
            vendaIncompleta = clientsOfAllBranchesMeses(f[filial], listaClientes[i]);
            /** Se o cliente efetuou compra na filial, então incrementa o int vendaCompleta.*/
            if(vendaIncompleta == 0)
                vendaCompleta++;
        }
        /** Se o cliente efetuou compras nas 3 filiais existentes então é adicionado ao array.*/
        if(vendaCompleta==NRFILIAIS) {
            if(index == *size) {
                *size *= 2;
                array = realloc(array,(*size) * sizeof(char*));
            }
            array[index++]=strdup(listaClientes[i]);
        }
    }
    return array;
}

int** getNrProductsBoughtByClient(Filiais f, char* clientID) {
    int i;
    int** m;
    m = malloc(NRFILIAIS * sizeof(int*));
    for(i=0;i<NRFILIAIS;i++) {
        m[i] = getNrProductsBoughtByClientMes(f[i],clientID);
    }
    return m;
}

void fillArvoreProdutos(Filiais f, ArvoreProdKey* p) {
    int i;
    for(i = 0; i < NRFILIAIS; i++) {
        fillArvoreProdutosMes(f[i],&p[i]);
    }
}


int nmrVendasFiliais(Filiais a,int mes,int filial){
    int vendas=0;
    vendas += nmrVendasMeses(a[filial],mes);
    return vendas;
}

void getNrVendasProductFiliais(Filiais f,char* key, int filial, int month, int *totalSalesN, int *totalSalesP){
    getNrVendasProductMeses(f[filial], key, month, totalSalesN, totalSalesP);
}
