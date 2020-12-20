/*
  main.c
  LI3

  Created by Rúben Cerqueira, Júlio Alves and Alexandra Reigada on 13/02/2020.
  Copyright © 2020 Rúben Cerqueira, Júlio Alves and Alexandra Reigada. All rights reserved.
*/

#include "interface.h"

int main(int argc, const char * argv[]) {
    SGV a;
    char *productID,*clientID,buffer[50], letra=' ';
    int mes=0, numero = 0, opcao = 99, filial=0, limit = 0, minMonth, maxMonth;
    size_t bufsize = 10;
    clientID = (char *)malloc(bufsize * sizeof(char));
    productID = (char *)malloc(bufsize * sizeof(char));
    a = NULL;
    do {
        printMenu();
        if(scanf(" %d",&opcao) == 0) opcao = 14;
        getchar();
        while(opcao < 0 || opcao > 13) {
            getchar();
            printError();
            printOpcao();
            scanf(" %d",&opcao);
            getchar();
            fflush(stdin);
        }
        switch (opcao)
        {
            case 0:
                printLeavingMsg();
                break;
            case 1: {
                char*cliente = NULL,*produto = NULL,*venda = NULL;
                destroySGV(a);
                a = initSGV();
                printLoadSGVoptions();
                scanf(" %d",&numero);
                getchar();
                while(isdigit(numero) && numero != 1 && numero != 2) {
                    printError();
                    scanf(" %d",&numero);
                }
                if(numero == 1) {
                    selectReadFile("Produtos");
                    fgets(buffer,50,stdin);
                    strtok(buffer,"\n");
                    strcat(buffer,".txt");
                    produto = strdup(buffer);
                    selectReadFile("Clientes");
                    fgets(buffer,50,stdin);
                    strtok(buffer,"\n");
                    strcat(buffer,".txt");
                    cliente = strdup(buffer);
                    selectReadFile("Vendas");
                    fgets(buffer,50,stdin);
                    strtok(buffer,"\n");
                    strcat(buffer,".txt");
                    venda = strdup(buffer);
                }
                else if(numero == 2) {
                    cliente = strdup("Clientes.txt");
                    produto = strdup("Produtos.txt");
                    venda = strdup("Vendas_1M.txt");
                }
                a = loadSGVFromFiles(a,cliente,produto,venda);
                if(a) printSuccessMsg();
                printEnterToContinue();
                getchar();
                break;
            }
            case 2:
                if(a) {
                    selectLetter();
                    if(scanf(" %c",&letra) && isalpha(letra) && isupper(letra)) {
                        getchar();
                        fflush(stdin);
                        getProductsStartedByLetter(a,letra);
                    }
                    else {
                        getchar();
                        fflush(stdin);
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 3:
                if(a) {
                    selectMonth();
                    if(scanf(" %d",&mes)){
                        getchar();
                        fflush(stdin);
                        selectProduct();
                        getline(&productID,&bufsize,stdin);
                        strtok(productID,"\n");
                        fflush(stdin);
                        getProductsSalesAndProfit(a,productID,mes);
                        printEnterToContinue();
                        getchar();
                    }
                    else {
                        getchar();
                        fflush(stdin);
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 4:
                if(a) {
                    selectFilialDivided();
                    if(scanf(" %d",&filial)){
                        getchar();
                        fflush(stdin);
                        getProductsNeverBought(a,filial);
                    }
                    else {
                        getchar();
                        fflush(stdin);
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 5:
                if(a) {
                    getClientsOfAllBranches(a);
                    printEnterToContinue();
                    getchar();
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 6: 
                if(a) {
                    getClientsAndProductsNeverBoughtCount(a);
                    printEnterToContinue();
                    getchar();
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 7:
                if(a) {
                    selectClient();
                    getline(&clientID,&bufsize,stdin);
                    strtok(clientID,"\n");
                    getProductsBoughtByClient(a,clientID);
                    printEnterToContinue();
                    getchar();
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 8: 
                if(a) {
                    fflush(stdin);
                    selectMonthsToLookFor1();
                    if(scanf(" %d",&minMonth)){
                        selectMonthsToLookFor2();
                            if(scanf(" %d",&maxMonth)){
                                getchar();
                                fflush(stdin);
                                newLine();
                                getSalesAndProfit(a,minMonth,maxMonth);
                                newLine();
                                printEnterToContinue();
                                getchar();
                            }
                            else {
                                getchar();
                                fflush(stdin);
                                printError();
                                printEnterToContinue();
                                getchar();
                            }
                    }
                    else {
                        getchar();
                        fflush(stdin);
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 9:
                if(a) {
                    selectFilial();
                    if(scanf(" %d",&filial)){
                        getchar();
                        fflush(stdin);
                        selectProduct();
                        getline(&productID,&bufsize,stdin);
                        strtok(productID,"\n");
                        fflush(stdin);
                        printf("\n");
                        getProductBuyers(a,productID,filial);
                        newLine();
                        printEnterToContinue();
                        getchar();
                    }
                    else {
                        getchar();
                        fflush(stdin);
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 10:
                if(a) {
                    selectMonth();
                    if(scanf(" %d",&mes)){
                        getchar();
                        fflush(stdin);
                        selectClient();
                        fgets(clientID,sizeof(clientID),stdin);
                        strtok(clientID,"\n");
                        fflush(stdin);
                        getClientFavoriteProducts(a, clientID, mes);
                    }
                    else {
                        getchar();
                        fflush(stdin);
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 11:
                if(a) {
                    selectLimit();
                    if(scanf(" %d",&limit)){
                        if(limit <=0 || limit > 200000) {
                            getchar();
                            fflush(stdin);
                            newLine();
                            printError();
                            printEnterToContinue();
                            getchar();
                        }
                        else{
                            getchar();
                            fflush(stdin);
                            getTopSelledProducts(a, limit);
                        }
                    }
                    else {
                        getchar();
                        fflush(stdin);
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 12:
                if(a) {
                    selectLimit();
                    if(scanf(" %d",&limit)){
                        getchar();
                        fflush(stdin);
                        selectClient();
                        fgets(clientID,sizeof(clientID),stdin);
                        strtok(clientID,"\n");
                        fflush(stdin);
                        newLine();
                        getClientTopProfitProducts(a,clientID,limit);
                    }       
                    else {
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 13:
                if(a){
                    getCurrentFilesInfo(a);
                    printEnterToContinue();
                    getchar();
                }
                else {
                    printSGVEmpty();
                    printEnterToContinue();
                    getchar();
                }
                break;
            default:
                printError();
                break;
        }
        fflush(stdin);
    } while (opcao!=0);
    free(clientID);
    free(productID);
    destroySGV(a);
    return 0;
}
