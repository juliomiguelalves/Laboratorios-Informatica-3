#include "UI.h"


void clearScreen(){
    printf("\e[1;1H\e[2J");
}

void printMenu(){
    clearScreen();
    printf( "\n\nSelecione a opção:\n\n"
            "   1 - Carregar ficheiros\n"
            "   2 - Produtos começados por uma letra\n"
            "   3 - Vendas e faturação de um produto\n"
            "   4 - Produtos nunca comprados\n"
            "   5 - Clientes que compraram em todas as filiais\n"
            "   6 - Número de clientes e produtos que nunca compraram nem foram comprados\n"
            "   7 - Quantidade de produtos comprados por um cliente\n"
            "   8 - Vendas e faturação entre dois meses\n"
            "   9 - Clientes que compraram um produto\n"
            "   10 - Produtos mais comprados por um cliente\n"
            "   11 - Produtos mais vendidos\n"
            "   12 - Produtos onde o cliente gastou mais\n"
            "   13 - Informação sobre os ficheiros lidos\n"
            "   0 - Sair\n\n"
            "Opção: ");
}


void printOpcao() {
    printf("Opção : ");
}

void printError(){
    printf("Input inválido.\n\n");
}

void printErrorSomethingNotFound(char* smth) {
    printf("Erro! %s não encontrado!\n\n",smth);
}

void printLoadSGVoptions(){
        printf("Selecione a opção (Os ficheiros têm que estar na mesma diretoria que o programa):\n"
               "1 - Introduzir ficheiros\n"
               "2 - Ficheiros por omissão\n"
               "Opção: ");
}

void selectReadFile(char* fileName) {
    char ret[100];
    sprintf(ret,"Insira o nome do ficheiro %s (sem a extensão): ",fileName);
    printf("%s",ret);
}

void printSuccessMsg(){
    printf("\n\nDone!\n\n");
}


void selectLetter(){
    printf("Indique que letra deseja procurar: ");
}

void selectMonth(){
    printf("Indique o mês: ");
}

void selectProduct(){
    printf("Introduza ID produto: ");
}

void selectClient(){
    printf("Introduza ID cliente: ");
}

void selectFilialDivided(){
    printf( "Selecione a opção:\n"
            "0 - Todas as filiais\n"
            "1 - Por filial \n"
            "Opção: ");
}

void selectFilial() {
    printf("Selecione a filial: ");
}

void selectLimit(){
    printf("Insira limite: ");
}

void selectMonthsToLookFor1(){
    printf("Indique o mês inicial: ");
}

void selectMonthsToLookFor2(){
    printf("Indique o mês final: ");
}

void printEnterToContinue(){
    printf("Pressione 'enter' para continuar: ");

}

void printLeavingMsg(){
    printf("A sair...\n\n");
}

void printCurrentFilesInfo(char* catCli, char* catProd, char* sales, int nrSales, int nrValidSales){
    printf("Ficheiro Clientes: %s\n"
           "Ficheiro Produtos: %s\n"
           "Ficheiro Vendas: %s\n"
           "Vendas Lidas: %d\n"
           "Vendas Válidas: %d\n\n",catCli,catProd,sales,nrSales,nrValidSales);
}

void printSGVEmpty() {
    printf("Base de dados vazia!\n\n");
}

void printFileNotFound(){
    printf("Erro: ficheiro não encontrado!\n\n");
}

void printProductSalesAndProfit(char* productID, int month, int totalSalesN, int totalSalesP, double totalN, double totalP, int filial){
    if(filial==1) printf("Para o produto %s no mês %d: \n", productID, month);
    if (filial!=0) printf("Na filial %i: \n", filial);
    printf("Vendas totais do tipo normal: %d\n"
    "Vendas totais em promoção: %d\n"
    "Faturação total em venda normal: %0.2f\n"
    "Faturação total em promoção: %0.2f\n\n",totalSalesN, totalSalesP, totalN, totalP);
}

void printProductsBought(int count){
    if(count > 0)
         printf("Número de produtos comprados: %d\n\n",count);
    else printf("Não foram comprados produtos.\n\n");
}

void printClientsAndProductsNeverBoughtCount(int totalC, int totalP){
    printf("Total de Clientes que não fizeram compras: %d\n"
           "Total de Produtos que não foram comprados: %d\n\n",totalC,totalP);
}

void printSalesAndProfit(int minMonth, int maxMonth,int totalSales,int totalVendas, double totalFat){
    printf( "Quantidade total vendida entre o mês %d e o mês %d: %d\n"
            "Número de vendas: %d\n"
            "Faturação total: %0.5f\n",minMonth,maxMonth,totalSales,totalVendas,totalFat);
}

/**
 * @brief Imprime uma tabela de meses.
 */
static void printMonthsInTable(){
    printf("______________________________________________________________________\n"
           "|Meses   |1   |2   |3   |4   |5   |6   |7   |8   |9   |10  |11  |12  |\n");
}

void printQuantByBranch(int** values){
    int i,j;
    printMonthsInTable();
    for(i = 0; i < NRFILIAIS; i++) {
         printf("|Filial %d|",i+1);
         for(j = 0; j < 12; j++) {
            printf("%*d",-4,values[i][j]);
            printf("|");
         }
         newLine();
    }
    printf("▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
   
}

void newLine(){
    printf("\n");
}

void printNumber(int r){
    printf("%d",r);
}

void printProductBuyers(char* prom, char** id,int contador){
    int i;
    if(contador==0)  printf("O produto indicado não foi adquirido nesta filial\n");
    if(contador >0) {
        printf("___________________________________________\n");
        printf("|     Cliente     |      Modo de Compra    |\n");
        for(i=0;i<contador;i++){
            printf("|      %s      |           %c            |",id[i],prom[i]);
            newLine();
        }
        printf("▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
    }
}
void printProductDidntBuyMonthBranch(char* productID,int month,int branch) {
    printf("O produto %s não foi comprado no mês %d, na filial %d.\n\n",productID,month,branch);
}

void printClientDidntBuyMonth(char* client, int month){
    printf("\nO cliente %s não fez compra no mês %d.\n\n", client, month);
}

void printProductDidntBuyMonth(char* productID, int month) {
    printf("\nO produto %s não foi comprado no mês %d\n",productID,month);
}

void printEveryProductBought() {
    printf("\nTodos os produtos foram comprados!\n\n");
}

void printInFilial(int i){
    printf("Na filial %i: \n",i);
}

void printClientDidntBuy(){
    printf("\nEste cliente não comprou!\n");
}

void printProduct(char* key){
    puts(key);
}

int printProductsStartedByLetter(char** array, int size, int *page, char* tok) {
    clearScreen();
    printf("Número total de produtos: %d\n\n",size);
    struct winsize w;
    ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);
    int i,perpage,totalpages,found=0,flag=0;
    perpage = (w.ws_row) - 11;
    if(perpage<1) perpage=1;
    if(size%perpage == 0) totalpages = (size/perpage);
    else totalpages = (size/perpage)+1;
    if(tok != NULL) {
        for(i=0;i<size;i++) {
            if(strcmp(tok,array[i]) == 0){
                 *page = (i/perpage);
                 found=1;
            }   
        }
        if(found==0){
            printf("Produto não foi encontrado!\n");
            flag=1;
        }       
    }
    if(flag==0){
        for(i = perpage * (*page);i < perpage * (*page+1) && i < size;i++) {
            printf("%*s\n",(int) ((w.ws_col/8)-(2*strlen(array[i])+1)),array[i]);
        }
    }
    printf("\n");
    printf("Página %d de %d\n\n",*page+1,totalpages);
    printf("1 - Página seguinte\n"
           "2 - Página anterior\n"
           "3 - Inserir página\n"
           "4 - Procurar produto\n"
           "0 - Voltar\n");
    return totalpages;
}


int printProductsNeverBought(char** array, int size, int filial, int *page, char* tok) {
    clearScreen();
    if(filial!=0)printInFilial(filial);
    printf("Número total de produtos não comprados: %d\n\n",size);
    struct winsize w;
    ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);
    int i,perpage,totalpages,flag=0,found=0;
    perpage = (w.ws_row) - 15;
    if(perpage<1) perpage=1;
    if(size%perpage == 0) totalpages = (size/perpage);
    else totalpages = (size/perpage)+1;
    if(tok != NULL) {
        for(i=0;i<size;i++) {
            if(strcmp(tok,array[i]) == 0){
                 *page = (i/perpage);
                 found=1;
            }   
        }
        if(found==0){
            printf("Produto não foi encontrado!\n");
            flag=1;
        }       
    }
    if(flag==0){
        for(i = perpage * (*page);i < perpage * (*page+1) && i < size;i++) {
            printf("%*s\n",(int) ((w.ws_col/8)-(2*strlen(array[i])+1)),array[i]);
        }
    }
    printf("\n");
    printf("Página %d de %d\n\n",*page+1,totalpages);
    if(filial!=0)
        printf("1 - Página seguinte\n"
               "2 - Página anterior\n"
               "3 - Inserir página\n"
               "4 - Procurar produto\n"
               "5 - Filial seguinte\n"
               "6 - Filial anterior\n"
               "0 - Voltar\n");
    else printf("1 - Página seguinte\n"
                "2 - Página anterior\n"
                "3 - Inserir página\n"
                "4 - Procurar produto\n"
                "0 - Voltar\n");
    return totalpages;
}


void selectPage() {
    printf("Insira a página: ");
}

void printNumberProducts(int count) {
    printf("Número de produtos: %d\n\n",count);
} 

int printClientFavouriteProducts(CodigosQuant l, int month, int *page, char* tok){
    clearScreen();
    int i, perpage,totalpages=0;
    if(l.ocup == 0) return 0;
    else{
        struct winsize w;
        ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);
        perpage = (w.ws_row) - 15;
        if(perpage<1) perpage=1;
        if(l.ocup%perpage == 0) totalpages = (l.ocup/perpage);
        else totalpages = (l.ocup/perpage)+1;
        if(tok != NULL) {
            for(i=0;i<l.ocup;i++) {
                if(strcmp(tok,l.lista[i].key) == 0) *page = (i/perpage);
            }
        }
        printf("______________________________\n"
            "|Código do produto|Quantidade|");
        newLine();
        printf("▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
        for(i = perpage * (*page);i < perpage * (*page+1) && i < l.ocup;i++) {
            printf("|");
            printf("%*s",-17,l.lista[i].key);
            printf("|");
            printf("%*i|\n",-10, l.lista[i].quant);
            printf("▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
        }
        printf("\n");
        printf("Página %d de %d\n\n",*page+1,totalpages);
        printf("1 - Página seguinte\n"
            "2 - Página anterior\n"
            "3 - Inserir página\n"
            "4 - Procurar produto\n"
            "0 - Voltar\n");
    }
    return totalpages;
}

int printNProductsProdSales(ProdSales a, int n, int *page, char* tok){
    int i,perpage,totalpages=0;
    clearScreen();
    if(n == 0) printf("O cliente não fez compras!\n\n");
    else {
        struct winsize w;
        ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);
        perpage = (w.ws_row) - 11;
        if(perpage<1) perpage=1;
        if(n%perpage == 0) totalpages = (n/perpage);
        else totalpages = (n/perpage)+1;
        if(tok != NULL) {
            for(i=0;i<n;i++) {
                if(strcmp(tok,a.array[i].key) == 0) *page = (i/perpage);
            } 
        }
        printf("Produtos em que o cliente mais gastou:\n");
        for(i = perpage * (*page);i < perpage * (*page+1) && i < n;i++) {
            printf("%*s\n",(int) ((w.ws_col/8)-(2*strlen(a.array[i].key)+1)),a.array[i].key);
        }
        printf("\n");
        printf("Página %d de %d\n\n",*page+1,totalpages);
        printf("1 - Página seguinte\n"
           "2 - Página anterior\n"
           "3 - Inserir página\n"
           "4 - Procurar produto\n"
           "0 - Voltar\n");
    } 
    return totalpages;
}
/**
 * @brief Imprime uma determinada página da árvore.
 * @param a Estrutura de dados.
 * @param page Página a imprimir.
 * @param perpage Itens por página.
 * @param atual Quantos nodos percorreu.
 */
static void printArvoreProduto(ArvoreProdQuant a, int page, int perpage, int *atual) {
    int ret = 0;
    if(a) {
        printArvoreProduto(a->right, page, perpage, atual);
        int i,inicio,fim;
        inicio = page*perpage;
        fim = (page+1)*perpage;
        for(i = 0; i < a->index && *atual < fim; i++) {
            if(*atual >= inicio)
                printf("%s foi comprado na quantidade de %d por %d clientes.\n",a->keys[i],a->quant,a->clientNumb[i]);
            (*atual)++;
        }
        if(ret < (page+1)*(perpage))
            printArvoreProduto(a->left, page, perpage, atual);
    }
}
/**
 * @brief Procura uma String numa árvore.
 * @param a Estrutura de dados.
 * @param tok String a comparar.
 * @param actual Quantas String leu.
 * @return 1 se encontrou ou 0 caso contrário.
 */
static int findTree(ArvoreProdQuant a, char* tok, int* actual) {
    int ret = 0;
    if(a) {
        ret = findTree(a->right,tok,actual);
        if(ret == 0) {
            int i;
            for(i = 0; i < a->index && ret == 0; i++) {
                (*actual)++;
                if(strcmp(tok, a->keys[i]) == 0) {
                    ret = 1;
                }
            }
        }
        if(ret == 0) findTree(a->left, tok, actual);
    }
    return ret;
}

int printTopSelledProducts(ArvoreProdQuant* a, int limit, int* page, char* tok, int filial) {
    clearScreen();
    struct winsize w;
    ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);
    int i,perpage,totalpages,flag=0,actual = 0;
    perpage = abs(((w.ws_row)-14))/NRFILIAIS;
    if(perpage<1) perpage=1;
    if(limit%perpage == 0) totalpages = (limit/perpage);
    else totalpages = (limit/perpage)+1;
    if(tok != NULL) {
        if(findTree(a[filial-1],tok,&actual)) {
            *page = (actual/perpage);
        }
        else {
            printErrorSomethingNotFound("Produto");
            flag = 1;
        }
    }
    filial = 0;
    if(flag == 0) {
        if(filial == 0)
            for(i = 0; i < NRFILIAIS; i++) {
                actual = 0;
                printf("Os produtos mais vendidos na %d filial: \n",i+1);
                printArvoreProduto(a[i],*page,perpage,&actual);
                newLine();
            }
    }
    printf("\n");
    printf("Página %d de %d\n\n",*page+1,totalpages);
    printf("1 - Página seguinte\n"
           "2 - Página anterior\n"
           "3 - Inserir página\n"
           "4 - Procurar cliente\n"
           "0 - Voltar\n");
    return totalpages;
}

int printClientsOfAllBranches(char** clientes,int size, int *page,char* tok){
    clearScreen();
    struct winsize w;
    ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);
    int i,perpage,totalpages,found=0,flag=0;
    perpage = (w.ws_row) - 11;
    if(perpage<1) perpage=1;
    if(size%perpage == 0) totalpages = (size/perpage);
    else totalpages = (size/perpage)+1;
    if(tok != NULL) {
        for(i=0;i<size;i++) {
            if(strcmp(tok,clientes[i]) == 0){
                 *page = (i/perpage);
                 found=1;
            }   
        }
        if(found==0){
            printf("Cliente não foi encontrado!\n");
            flag=1;
        }       
    }
    if(flag==0){
        for(i = perpage * (*page);i < perpage * (*page+1) && i < size;i++) {
            printf("%*s\n",(int) ((w.ws_col/8)-(2*strlen(clientes[i])+1)),clientes[i]);
        }
    }
    printf("\n");
    printf("Página %d de %d\n\n",*page+1,totalpages);
    printf("1 - Página seguinte\n"
           "2 - Página anterior\n"
           "3 - Inserir página\n"
           "4 - Procurar cliente\n"
           "0 - Voltar\n");
    return totalpages;
}

