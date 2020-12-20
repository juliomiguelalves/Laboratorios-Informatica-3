#include "FiliaisMeses.h"

Meses initMeses() {
    Meses m;
    int i;
    m = malloc(12 * sizeof(Clientes));
    for(i = 0; i < 12 ; i++)
        m[i] = NULL;
    return m;
}

void destroyMeses(Meses m) {
    int i;
    for(i=11;i>=0;i--) {
        destroyClientes(m[i]);
    }
    free(m);
}

void insertClienteMes(char * key, int mes, Meses m) {
    insertClienteVenda(&m[mes-1],key,NULL);
}

void insertProdutoMes(char * cli, char * prod, float price, int quant, char promo, int mes, Meses m) {
    Clientes c=m[mes-1];
    insertProdutoVendaCliente(c,prod,price,quant,promo,cli);
}

void fillProdSalesMonth(Meses m,char * key,ProdSales* a) {
    int i;
    for(i = 0;i<12;i++) {
        fillProdSales(m[i],key,a);
    }
}
char** productBuyersOnMeses(Meses a ,char *productID,int *contador,char* promo) {
    int i;
    int size,index=0;
    size=1;
    char** array;
    array= malloc(sizeof(char*));
    for(i=0;i<12;i++) array=productBuyersOnClientes(a[i],productID,contador,promo,array,&size,&index);

    return array;

}

void getClienteFavouriteProductsMeses(Meses m, char* clientID, int month, CodigosQuant *listaCodigos){
    getClienteFavouriteProductsClientes(m[month-1], clientID, listaCodigos);
}


int isBoughtMes(Meses m, char * key) {
    int bought = 0,i;
    for(i = 0; i < 26 && bought == 0; i++) {
        bought=isBoughtMesClientes(m[i],key);
    }
    return bought;
}

int clientsOfAllBranchesMeses(Meses m, char* key){
    int i, breakflag = 1;

    for(i=0; i<12 && breakflag == 1; i++)
    {
        breakflag = (isBoughtMesClientes(m[i],key)?0:1);

    }
    return breakflag;
}

int* getNrProductsBoughtByClientMes(Meses f,char *clientID) {
    int i,*arr;
    arr = malloc(12 * sizeof(int));
    for(i = 0; i < 12; i++) 
        arr[i] = getnrProductsBoughtByClient(f[i],clientID);
    return arr;
}

void fillArvoreProdutosMes(Meses f, ArvoreProdKey* a) {
    int i;
    for(i = 0; i < 12; i++) {
        fillArvoreProdutosCliente(f[i],a);
    }
}

int nmrVendasMeses(Meses a,int mes){
    int vendas=0;
    vendas+=nmrVendasClientes(a[mes]);
    return vendas;
}

void getNrVendasProductMeses(Meses m,char* key, int month, int *totalSalesN, int *totalSalesP){
    getNrVendasProductsClientes(m[month-1], key, totalSalesN, totalSalesP);
}
