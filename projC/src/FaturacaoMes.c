
#include "FaturacaoMes.h"

FaturacaoMes initFaturacaoMes(){
    FaturacaoMes f;
    int i;
    f=malloc(12 * sizeof(FaturacaoDados));
    for(i=0; i<12; i++){
        f[i]=initFaturacaoDados();
    }
    return f;
}

void destroyFaturacaoMes(FaturacaoMes f){
    if(f) {
        int i;
        for(i=11; i>=0; i--){
            destroyFaturacaoDados(f[i]);
        }
        free(f);
    }
}

void insertFaturacaoMes (FaturacaoMes f, float price, int quant,char prom, int mes)
{
    int i=0;
    if(prom=='P') i=1;
    insertFaturacaoDados(f[mes-1],price,quant,i);
}

int monthlysalesAndProfitMes(FaturacaoMes a,double* totalFat,int mes){
    int totalSales=0;
    totalSales += monthlySalesAndProfitDados(a[mes],totalFat);
    return totalSales;
}


void salesAndProfitMes (FaturacaoMes a, double *totalN, double *totalP, int month) {
    salesAndProfitDados(a[month],totalN,totalP);
}

int isProductBoughtMes(FaturacaoMes f) {
    int bought = 0;
    int i;
    for(i = 0; i < 12 && bought == 0; i++) {
        bought = isProductBought(f[i]);
    }
    return bought;
}

int getQuantitiesMes(FaturacaoMes f) {
    int i,n = 0;
    for(i=0;i<12;i++) n += getQuantities(f[i]);
    return n;
}
