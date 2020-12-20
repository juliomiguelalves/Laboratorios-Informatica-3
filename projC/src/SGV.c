#include "interface.h"

#define NRFILIAIS 3
/**
 *  @brief Estrutura de dados que guarda a estrutura de catálogo de clientes, a estrutura de catálogo de produtos, 
 *  a gestão de filiais, a estrutura da faturação global, o nome do ficheiro de produtos, o nome do ficheiro de clientes 
 *  e o nome do ficheiro de vendas utilizados nesse momento, o número de vendas lidas e o número de vendas válidas.
 */
struct SGV{
    CatalogoCliente c;
    CatalogoProduto p;
    Filiais f;
    Faturacao fg;
    char*catProd;
    char*catCli;
    char*sales;
    int nrSales;
    int nrValidSales;
};

SGV initSGV() {
    SGV a;
    a = malloc(sizeof(struct SGV));
    a->p = initCatalogoProduto();
    a->c = initCatalogoCliente();
    a->f = initFiliais();
    a->fg = initFaturacao();
    a->nrSales = 0;
    a->nrValidSales = 0;
    a->catCli = NULL;
    a->catProd = NULL;
    a->sales = NULL;
    return a;
}

void destroySGV(SGV a) {
    if(a) {
        destroyCatalogoProduto(a->p);
        destroyCatalogoCliente(a->c);
        destroyFiliais(a->f);
        destroyFaturacao(&(a->fg));
        free(a->catCli);
        free(a->catProd);
        free(a->sales);
        free(a);
    }
}

void getCurrentFilesInfo(SGV a) {
    printCurrentFilesInfo(a->catCli,a->catProd,a->sales,a->nrSales,a->nrValidSales);
}

SGV loadSGVFromFiles(SGV sgv, char* clientsFilePath, char* productsFilePath, char* salesFilePath) {
    FILE* cliente, *produto, *venda;
    int vendasLidas = 0, vendasValidas = 0;
    cliente=fopen(clientsFilePath,"r");
    produto=fopen(productsFilePath,"r");
    venda=fopen(salesFilePath,"r");
    if(cliente==NULL || produto==NULL || venda==NULL) {
        printFileNotFound();
        destroySGV(sgv);
        return NULL;
    }
    sgv->catCli = strdup(clientsFilePath);
    sgv->catProd = strdup(productsFilePath);
    sgv->sales = strdup(salesFilePath);
    /** Carregamento de dados para Catalogo Clientes */
    readFiletoCatalogoCliente(sgv->c,cliente);

    /** Carregamento de dados para Catalogo Clientes e Faturacao */
    char*buffer;
    buffer=(char*) malloc(9*sizeof(char));
    while(feof(produto)==0) {
        buffer=fgets(buffer,8,produto);
        if(buffer != NULL) {
            strtok(buffer," \r\n");
            insertCatalogoProduto(sgv->p,buffer);
            insertProdutoFaturacao(&sgv->fg,buffer);
        }
    }

    /** Inserção de vendas */
    char*prod = NULL, *cli = NULL, prom = '\0', *eti;
    double price = 0;
    int quant = 0, mes = 0, filial = 0;
    buffer=(char*) realloc(buffer,35*sizeof(char));
    while(feof(venda)==0){
        fgets(buffer,35, venda);
        strtok(buffer,"\r\n");
        prod=strdup(strtok(buffer, " "));
        if(((eti=(strtok(NULL, " ")))!=NULL)) price=atof(eti);
        else break;
        quant=atoi(strtok(NULL, " "));
        prom=(*strtok(NULL, " "));
        cli=strdup(strtok(NULL, " "));
        mes=atoi(strtok(NULL, " "));
        filial=atoi(strtok(NULL, " "));
        /** Validação da venda */
        if (valvenda(sgv,prod,price,quant,prom,cli,mes,filial) == 1) {
            /** Inserção dos dados da venda na estrutura de gestão de filial e na estrutura de faturação global */
            insertClienteFilial(cli,mes,filial,sgv->f);
            insertProdutoFilial(cli,prod,price,quant,prom,mes,filial,sgv->f);
            insertFaturacao(prod,price,quant,prom,mes,filial,sgv->fg);
            vendasValidas++;
        }
        vendasLidas++;
        free(prod);
        free(cli);
    }
    sgv->nrSales = vendasLidas;
    sgv->nrValidSales = vendasValidas;
    free(buffer);
    fclose(cliente);
    fclose(produto);
    fclose(venda);
    return sgv;
}

void getProductsStartedByLetter (SGV sgv, char letter) {
    int i;
    if(isalpha(letter)) {
        int size, opcao = 99,page = 0,maxpage = 0;
        char *prod = NULL;
        size_t len = 0;
        char** produtos = getProductsLetter(sgv->p,&size,letter);
        do{
            clearScreen();
            maxpage = printProductsStartedByLetter(produtos, size, &page, prod);
            if(prod !=NULL){
                free(prod);
                prod=NULL;
            }
            printOpcao();
            scanf(" %d",&opcao);
            getchar();
            switch(opcao) {
                case 1:
                    if(page != maxpage-1) page++;
                    break;
                case 2:
                    if(page != 0) page--;
                    break;
                case 3:
                    selectPage();
                    if(scanf(" %d",&page)){
                        if(page>maxpage) {
                            page=maxpage-1;
                            getchar();
                        }
                        else if (page<1) {
                            page=0;
                            getchar();
                        }
                        else {
                            page--;
                            getchar();
                        }
                    }
                    else{
                        printError();
                        printEnterToContinue();
                        getchar();
                    }

                    break;
                case 4:
                    selectProduct();
                    getline(&prod, &len, stdin);
                    strtok(prod,"\n");
                    if (!validaProduto(prod) || !findProdCat(sgv->p,prod)){
                        free(prod);
                        prod=NULL;
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                    break;
                case 0:
                    break;
                default:
                    printError();
                    break;
            }
        } while (opcao != 0);
        if(prod != NULL) free(prod);
        for(i=size-1; i>=0; i--) free(produtos[i]);
        free(produtos);
    }
}

void getProductsSalesAndProfit (SGV sgv, char * productID, int month) {
    if(month > 0 && month < 13 && validaProduto(productID)) {
        if(findProdCat(sgv->p,productID)==1) {
            int i, totalSalesN, totalSalesP,opcao; 
            double totalN = 0, totalP = 0;
            totalSalesN=0;
            totalSalesP=0;
            selectFilialDivided();
            scanf(" %d",&opcao);
            getchar();
            if(opcao == 0) {
                clearScreen();
                for(i=0;i<NRFILIAIS;i++){
                    salesAndProfitFaturacao(sgv->fg,month-1, i, &totalN, &totalP, productID);
                    getNrVendasProductFiliais(sgv->f, productID, i, month, &totalSalesN, &totalSalesP);
                }
                if(totalN == 0 && totalP == 0) printProductDidntBuyMonth(productID, month);
                else printProductSalesAndProfit(productID, month, totalSalesN, totalSalesP, totalN, totalP,0);
            }
            else if(opcao == 1) {
                clearScreen();
                for(i=0;i<NRFILIAIS;i++){
                    salesAndProfitFaturacao(sgv->fg,month-1, i, &totalN, &totalP, productID);
                    getNrVendasProductFiliais(sgv->f, productID, i, month, &totalSalesN, &totalSalesP);
                    if(totalN == 0 && totalP == 0) printProductDidntBuyMonthBranch(productID,month, i+1);
                    else printProductSalesAndProfit(productID, month, totalSalesN, totalSalesP, totalN, totalP, i+1);
                    totalN=0;
                    totalP=0;
                    totalSalesN=0;
                    totalSalesP=0;
                }
            }
            else
                printError();
        }
        else
            printErrorSomethingNotFound("Produto");
    }
    else 
        printError();
    
}


void getProductsNeverBought (SGV sgv, int branchID){
    int index, size, opcao = 99,page = 0,maxpage = 0, filial, index2 = 0, index3 = 0, size2 = 0, size3 = 0;
    size_t len = 0;
    char *prod = NULL;
    char ** array = NULL,**array2 = NULL,**array3 = NULL;
    if(branchID != 0 && branchID != 1) {
        printError();
        printEnterToContinue();
        getchar();
    }
    else {
        if(branchID == 0) {
            array = malloc(sizeof(char*));
            index = 0;
            size = 1;
            array = getProductsNeverBoughtFact(sgv->fg, branchID, &size, array, &index);
            filial = 0;
        }
        else {
            index = 0;
            size = 1;
            size2 = 1;
            size3 = 1;
            array = malloc(sizeof(char*));
            array2 = malloc(sizeof(char *));
            array3 = malloc(sizeof(char *));
            array = getProductsNeverBoughtFact(sgv->fg, 1, &size, array, &index);
            array2 = getProductsNeverBoughtFact(sgv->fg, 2, &size2, array2, &index2);
            array3 = getProductsNeverBoughtFact(sgv->fg, 3, &size3, array3, &index3);
            filial = 1;
        }
        if(index == 0 && index2 == 0 && index3 == 0) {
        printEveryProductBought();
        printEnterToContinue();
        getchar();
        }
        else {
            do{
                clearScreen();
                if(filial == 0) maxpage = printProductsNeverBought(array,index,0,&page,prod);
                if(filial == 1) maxpage = printProductsNeverBought(array,index,1,&page,prod);
                if(filial == 2) maxpage = printProductsNeverBought(array2,index2,2,&page,prod);
                if(filial == 3) maxpage = printProductsNeverBought(array3,index3,3,&page,prod);
                if(prod != NULL){
                    free(prod);
                    prod=NULL;
                }
                printOpcao();
                scanf(" %d",&opcao);
                getchar();
                switch(opcao) {
                    case 1:
                        if(page != maxpage - 1) page++;
                        break;
                    case 2:
                        if(page != 0) page--;
                        break;
                    case 3:
                        selectPage();
                        if(scanf(" %d",&page)){
                            if(page>maxpage) {
                                page=maxpage-1;
                                getchar();
                            }
                            else if (page<1) {
                                page=0;
                                getchar();
                            }
                            else {
                                page--;
                                getchar();
                            }
                        }
                        else{
                            printError();
                            printEnterToContinue();
                            getchar();
                        }
                        break;
                    case 4:
                        selectProduct();
                        getline(&prod, &len, stdin);
                        strtok(prod,"\n");
                        if (!validaProduto(prod) || !findProdCat(sgv->p,prod)){
                            free(prod);
                            prod=NULL;
                            printError();
                            printEnterToContinue();
                            getchar();
                        }
                        break;
                    case 5:
                        if(filial == 0) printError();
                        else {
                            if(filial != 3) {
                                filial++;
                                page = 0;
                            }
                        }
                        break;
                    case 6:
                        if(filial == 0) printError();
                        else {
                            if(filial != 1) {
                                filial--;
                                page = 0;
                            }
                        }
                        break;
                    case 0:
                        break;
                    default:
                        printError();
                        break;
                }
            } while (opcao != 0);
        }
        if(branchID == 0) {
            for(size = 0; size < index; size++) free(array[size]);
            free(array);
        }
        else {
            for(size = 0; size < index; size++) free(array[size]);
            for(size = 0; size < index2; size++) free(array2[size]);
            for(size = 0; size < index3; size++) free(array3[size]);
            free(array);
            free(array2);
            free(array3);
        }
    }
}

void getClientsOfAllBranches(SGV sgv) {
    int i,size,newsize,opcao=99,page=0,maxpage=0;
    char**array;
    char**clientes;
    char* cli=NULL;
    size_t len=0;
    array = getCatalogoClientes(sgv->c,&size);
    clientes = clientsOfAllBranches(sgv->f, array, size, &newsize);
        do{ 
            clearScreen();
            maxpage = printClientsOfAllBranches(clientes, size, &page, cli);
            if(cli != NULL) {
                free(cli);
                cli=NULL;
            }
            printOpcao();
            scanf(" %d",&opcao);
            switch(opcao) {
                case 1:
                    if(page != maxpage-1) page++;
                    break;
                case 2:
                    if(page != 0) page--;
                    break;
                case 3:
                    selectPage();
                    if(scanf(" %d",&page)){
                        if(page>maxpage) {
                            page=maxpage-1;
                            getchar();
                        }
                        else if (page<1) {
                            page=0;
                            getchar();
                        }
                        else {
                            page--;
                            getchar();
                        }
                    }
                    else{
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                    break;
                case 4:
                    getchar();
                    selectClient();
                    getline(&cli, &len, stdin);
                    strtok(cli,"\n");
                    if (!validaCliente(cli) || !findCliCat(sgv->c,cli)){
                        free(cli);
                        cli=NULL;
                        printError();
                        printEnterToContinue();
                        getchar();
                    }
                    break;
                case 0:
                    break;
                default:
                    printError();
                    break;
            }
        } while (opcao != 0);   
    for(i = 0; i < size; i++) {
        free(array[i]);
        free(clientes[i]);
    }
    free(array);
    free(clientes);
}

void getClientsAndProductsNeverBoughtCount (SGV sgv){
    int totalP, totalC,i,size;
    char**array;
    totalP = productsNeverBoughtCount(sgv->fg);
    array = getCatalogoClientes(sgv->c,&size);
    totalC = clientsNeverBoughtCount(sgv->f,array,size);
    printClientsAndProductsNeverBoughtCount(totalC,totalP);
    for(i = 0; i < size; i++) {
        free(array[i]);
    }
    free(array);
}

void getProductsBoughtByClient(SGV sgv,char* clientID) {
    if(validaCliente(clientID)) {
        if(findCliCat(sgv->c,clientID)) {
            int** array = getNrProductsBoughtByClient(sgv->f,clientID);
            printQuantByBranch(array);
            free(array);
        }
        else printErrorSomethingNotFound("Cliente");
    }
    else printError();
}

void getSalesAndProfit (SGV sgv,int minMonth, int maxMonth) {
    if((minMonth <= maxMonth) && (minMonth>0 && minMonth<13) && (maxMonth>0 && maxMonth<13)){ 
        int filial ,mes, totalSales =0, totalVendas=0;
        double totalFat=0;
        for(mes=minMonth-1;mes<=maxMonth-1;mes++){
            for(filial=0;filial<NRFILIAIS;filial++) {
                totalSales += monthlySalesAndProfitFaturacao(sgv->fg,mes,filial,&totalFat);
                totalVendas += nmrVendasFiliais(sgv->f,mes,filial);
            }
        }
        printSalesAndProfit(minMonth,maxMonth,totalSales,totalVendas,totalFat);
    }
    else printError();
}

void getProductBuyers (SGV sgv, char * productID, int branch) {
    if(branch > 0 && branch <= NRFILIAIS && validaProduto(productID)) {
        if(findProdCat(sgv->p,productID)){
            int contador=0,i;
            char** array;       
            char* promo;
            promo=malloc(sizeof(char));
            array=productBuyersOnFiliais(sgv->f,branch-1,productID,&contador,promo);
            printProductBuyers(promo,array,contador);
            for(i=0;i<contador;i++){
                free(array[i]);
            }
            free(promo);
            free(array);
        }
        else printErrorSomethingNotFound("Produto");
    }
    else printError();
}

void getClientFavoriteProducts (SGV sgv, char* clientID, int month) {
    int opcao = 99,page = 0,maxpage = 0;
    size_t len = 0;
    char *prod = NULL;
    if(validaCliente(clientID) && (month>0 && month<13)){
        if (findCliCat(sgv->c,clientID)==1) {
            int i;
            CodigosQuant listaCodigos;
            listaCodigos = clientFavouriteProducts(sgv->f, clientID, month);
            do{
                maxpage = printClientFavouriteProducts(listaCodigos, month, &page, prod);
                if(prod != NULL) {
                    free(prod);
                    prod=NULL;
                }
                if(maxpage == 0) {
                    opcao = 0;
                    printClientDidntBuyMonth(clientID,month);
                    printEnterToContinue();
                    getchar();
                }
                else {
                    printOpcao();
                    scanf(" %d",&opcao);
                    getchar();
                }
                switch(opcao) {
                    case 1:
                        if(page != maxpage -1) page++;
                        break;
                    case 2:
                        if(page != 0) page--;
                        break;
                    case 3:
                        selectPage();
                        if(scanf(" %d",&page)){
                            if(page>maxpage) {
                                page=maxpage-1;
                                getchar();
                            }
                            else if (page<1) {
                                page=0;
                                getchar();
                            }
                            else {
                                page--;
                                getchar();
                            }
                        }
                        else{
                            printError();
                            printEnterToContinue();
                            getchar();
                        }
                        break;
                    case 4:
                        selectProduct();
                        getline(&prod, &len, stdin);
                        strtok(prod,"\n");
                        if (!validaProduto(prod) || !findProdCat(sgv->p,prod)){
                            free(prod);
                            prod=NULL;
                            printError();
                            printEnterToContinue();
                            getchar();
                        }
                            break;
                    case 0:
                        opcao=0;
                        break;
                    default:
                        printError();
                        break;
                    }
            } while (opcao != 0);
        for(i=0;i<listaCodigos.ocup;i++){
            free(listaCodigos.lista[i].key);
        }
        free(listaCodigos.lista);
        }
        else {
            printErrorSomethingNotFound("Cliente");
            printEnterToContinue();
            getchar();
        }
    }
    else {
        printError();
        printEnterToContinue();
        getchar();
    }
}

void getTopSelledProducts(SGV sgv, int limit) {
    ArvoreProdQuant *p = NULL;
    ArvoreProdKey *lim = NULL;
    int i,done,opcao = 99,page = 0,maxpage = 0,filial = 0;
    char* prod = NULL;
    size_t len = 0;
    p = malloc(NRFILIAIS * sizeof(ArvoreProdQuant));
    lim = malloc(NRFILIAIS * sizeof(ArvoreProdKey));
    for(i = 0; i < NRFILIAIS; i++) {
        p[i] = NULL;
        lim[i] = NULL;
    }
    getQuantitiesFact(sgv->fg, p);
    done = 0;
    for(i = 0; i < NRFILIAIS; i++) {
        getLimitTree(&lim[i],p[i],limit,&done);
        destroyArvoreByQuant(p[i]);
        done = 0;
    }
    fillArvoreProdutos(sgv->f,lim);
    for(i = 0; i < NRFILIAIS; i++) {
        p[i] = NULL;
        copyTreeByQuant(&p[i], lim[i]);
    }
    do{
        clearScreen();
        if(prod!=NULL) {
            if(validaProduto(prod) && filial > 0 && filial <= NRFILIAIS) {
                maxpage = printTopSelledProducts(p, limit, &page, prod, filial);
                free(prod);
                prod = NULL;
            }
            else printError();
        }
        else maxpage = printTopSelledProducts(p, limit, &page, prod, filial);
        filial = 0;
        printOpcao();
        scanf(" %d",&opcao);
        getchar();
        switch(opcao) {
            case 1:
                if(page != maxpage-1) page++;
                break;
            case 2:
                if(page != 0) page--;
                break;
            case 3:
                selectPage();
                if(scanf(" %d",&page)){
                    if(page>maxpage) {
                        page=maxpage-1;
                        getchar();
                    }
                    else if (page<1) {
                        page=0;
                        getchar();
                    }
                    else {
                        page--;
                        getchar();
                    }
                }
                else{
                    printError();
                    printEnterToContinue();
                    getchar();
                }
                break;
            case 4:
                selectProduct();
                getline(&prod, &len, stdin);
                strtok(prod,"\n");
                selectFilial();
                scanf(" %d",&filial);
                getchar();
                break;
            case 0:
                break;
            default:
                printError();
                break;
        }
    } while (opcao != 0);
    for(i = 0; i < NRFILIAIS; i++) {
        destroyArvoreByQuant(p[i]);
        destroyArvoreByKey(lim[i]);
    }
    free(lim);
    free(p);
}

void getClientTopProfitProducts(SGV sgv, char * clientID, int limit) {
    int i, opcao = 99,page = 0,maxpage = 0;
    size_t len = 0;
    char *prod = NULL;
    if(validaCliente(clientID) && (limit>0 && limit<=200000)){
        if (findCliCat(sgv->c,clientID)==1) {
            ProdSales p;
            p = getClientTopProfitProductsBranches(sgv->f,clientID,&limit);
            do{
                clearScreen();
                maxpage = printNProductsProdSales(p,limit, &page, prod);
                if(prod != NULL) {
                    free(prod);
                    prod=NULL;
                }
                printOpcao();
                scanf(" %d",&opcao);
                getchar();
                switch(opcao) {
                    case 1:
                        if(page != maxpage-1) page++;
                        break;
                    case 2:
                        if(page != 0) page--;
                        break;
                    case 3:
                        selectPage();
                        if(scanf(" %d",&page)){
                            if(page>maxpage) {
                                page=maxpage-1;
                                getchar();
                            }
                            else if (page<1) {
                                page=0;
                                getchar();
                            }
                            else {
                                page--;
                                getchar();
                            }
                        }
                        else{
                            printError();
                            printEnterToContinue();
                            getchar();
                        }
                        break;
                    case 4:
                        selectProduct();
                        getline(&prod, &len, stdin);
                        strtok(prod,"\n");
                        if (!validaProduto(prod) || !findProdCat(sgv->p,prod)){
                            free(prod);
                            prod=NULL;
                            printError();
                            printEnterToContinue();
                            getchar();
                        }
                        break;
                    case 0:
                        opcao=0;
                        break;
                    default:
                        printError();
                        break;
                }
            } while (opcao != 0);
            for(i=p.ocup-1; i>=0; i--) free(p.array[i].key);
            free(p.array);
        }
        else {
            printErrorSomethingNotFound("Cliente");
            printEnterToContinue();
            getchar();
        }
    }
    else {
        printError();
        printEnterToContinue();
        getchar();
    }
}

/*------------------------------------------------------------------------------------------------------*/


int valvenda(SGV sgv, char *prod,float price,int quant,char prom,char *cli,int mes,int filial) {
    int i=0;
    if (validaProduto(prod))
        if (price>=0 && quant>=0 && (mes>0 && mes<13) && (filial>0 && filial <4) && (prom=='N' || prom == 'P'))
            if (validaCliente(cli))
                if(findCliCat(sgv->c,cli) && findProdCat(sgv->p,prod)) i=1;
    return i;
}

int validaCliente(char* cliente) {
    return (isalpha(cliente[0]) && isupper(cliente[0]) && isdigit(cliente[1]) && isdigit(cliente[2]) && isdigit(cliente[3]) && isdigit(cliente[4]) && strlen(cliente)==5);
}

int validaProduto(char* produto) {
    return (isalpha(produto[0]) && isalpha(produto[1]) && isupper(produto[0]) && isupper(produto[1]) && isdigit(produto[2]) && isdigit(produto[3]) && isdigit(produto[4]) && isdigit(produto[5]) && strlen(produto)==6);
}
