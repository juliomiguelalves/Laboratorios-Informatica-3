#include "FaturacaoFilial.h"


FaturacaoFilial initFaturacaoFilial(){
    int i;
    FaturacaoFilial f = malloc(NRFILIAIS*sizeof(FaturacaoMes));
    for(i=0;i<NRFILIAIS;i++)
        f[i] = initFaturacaoMes();
    return f;
}

void destroyFaturacaoFilial(FaturacaoFilial f){
    if(f) {
        int i;
        for(i=NRFILIAIS-1; i>=0; i--){
            destroyFaturacaoMes(f[i]);
        }
        free(f);
    }
}

void insertFaturacaoFilial (FaturacaoFilial f, float price, int quant,char prom, int mes, int filial){
    insertFaturacaoMes(f[filial-1],price,quant,prom, mes);
}

void salesAndProfitFiliais (FaturacaoFilial a, double *totalN, double *totalP, int filial,int month) {
    salesAndProfitMes(a[filial],totalN,totalP,month);
}


int monthlysalesAndProfitFiliais(FaturacaoFilial a,double *totalFat,int filial,int mes){
    int totalSales=0;
    totalSales += monthlysalesAndProfitMes(a[filial],totalFat,mes);
    return totalSales;
}


int isProductBoughtFilial(FaturacaoFilial f, int branchID) {
    int bought = 0;
    if(branchID == 0) {
        int i;
        for(i = 0; i<NRFILIAIS && bought == 0; i++) {
            bought = isProductBoughtMes(f[i]);
        }
    }
    else {
        bought = isProductBoughtMes(f[branchID-1]);
    }
    return bought;
}

void getQuantitiesFilial(FaturacaoFilial f,char* IDProduto, ArvoreProdQuant* p) {
    int i;
    for(i = 0; i < NRFILIAIS; i++) {
        ArvoreProdQuant prod = NULL;
        int quant;
        quant = getQuantitiesMes(f[i]);
        p[i] = addArvoreByQuant(p[i], IDProduto, quant, &prod);
    }
}
