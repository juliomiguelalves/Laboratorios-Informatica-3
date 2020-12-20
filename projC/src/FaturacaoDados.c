#include "FaturacaoDados.h"

/**
 *  @brief Estrutura de dados que guarda a informmação relativa à faturação de um produto, ou seja, 
 *  unidades vendidas e lucro obtido a partir da sua compra, utilizando num array de duas posições, 
 *  a primeira em relação à compra sem promoção e a segunda à compra com promoção.
 */
struct FaturacaoDados {
    int unidades;
    double faturacao;
};

FaturacaoDados initFaturacaoDados(){
    FaturacaoDados f;
    int i;
    f= malloc(2 * sizeof(struct FaturacaoDados));
    for(i = 0; i<2; i++){
        f[i].unidades=0;
        f[i].faturacao=0;
    }
    return f;
}

void destroyFaturacaoDados(FaturacaoDados f){
    if(f) {
        free(f);
    }
}

void insertFaturacaoDados (FaturacaoDados f, float price, int quant, int i)
{
    f[i].unidades+=quant;
    f[i].faturacao+=price*quant;
}

int monthlySalesAndProfitDados(FaturacaoDados a,double* totalFat){
    int totalSales=0;
    *totalFat += a[0].faturacao + a[1].faturacao;
    totalSales = (a[0].unidades + a[1].unidades); 
    return totalSales;
}

void salesAndProfitDados(FaturacaoDados a, double* totalN, double* totalP){
    *totalN += a[0].faturacao;
    *totalP += a[1].faturacao;
}


int isProductBought(FaturacaoDados f) {
    int bought = 1;
    if(f[0].unidades == 0 && f[1].unidades == 0) bought = 0;
    return bought;
}

int getQuantities(FaturacaoDados f) {
    int i, n = 0;
    for(i = 0; i < 2; i++) {
        n += f[i].unidades;
    }
    return n;
}
