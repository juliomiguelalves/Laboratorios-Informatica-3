#include "FiliaisProdutos.h"

/**
 *  @brief Estrutura de dados guarda a informação relativa à venda dos produtos, 
 *  utilizando uma árvore balanceada. 
 */
struct Produtos {
    char* key;
    int height;
    int tam;
    int ocup;
    struct repetidos *array;
    struct Produtos *right;
    struct Produtos *left;
};


/**
 *  @brief Estrutura de dados que guarda a informação acerca dos produtos vendidos. 
 */
struct repetidos {
    double price;
    int quant;
    char promo;
};

/**
 * @brief Calcula a altura da árvore que compõe a estrutura de dados.
 * 
 * @param a Árvore das vendas dos produtos.
 * @return Inteiro correspondente à altura da árvore.
 */

static int alturaP(Produtos a) {
    int i;
    if(a==NULL) {
        i=0;
    }
    else i=a->height;
    return i;
}

/**
 * @brief Máximo entre dois inteiros.
 * 
 * @param a Inteiro número 1.
 * @param b Inteiro número 2.
 * @return Inteiro maior do dois.
 */

static int max(int a,int b) {
    return (a>b?a:b);
}

/**
 * @brief Roda a árvore em questão para a direita de forma a gerir o balanceamento da mesma.
 * 
 * @param y Árvore de vendas dos produtos.
 * @return Árvore equilibrada das vendas dos produtos.
 */

static Produtos rotateRP(Produtos y)
{
    Produtos x = y->left;
    Produtos T2 = x->right;
    /** Realiza a rotação. */
    x->right = y;
    y->left = T2;
    /** Atualiza as alturas. */
    y->height = max(alturaP(y->left), alturaP(y->right))+1;
    x->height = max(alturaP(x->left), alturaP(x->right))+1;
    return x;
}

/**
 * @brief Roda a árvore em questão para a esquerda de forma a gerir o balanceamento da mesma.
 * 
 * @param x Árvore de vendas dos produtos.
 * @return Árvore equilibrada das vendas dos produtos.
 */

static Produtos rotateLP(Produtos x)
{
    Produtos y = x->right;
    Produtos T2 = y->left;
    /** Realiza a rotação. */
    y->left = x;
    x->right = T2;
    /** Atualiza as alturas. */
    x->height = max(alturaP(x->left), alturaP(x->right))+1;
    y->height = max(alturaP(y->left), alturaP(y->right))+1;
    return y;
}




static Produtos insertProdutoVendaS(Produtos node, char* key, float price, int quantity, char promo){
    /** Inserção normal de um elemento numa árvore de procura. */
    if (node == NULL){
        Produtos new=NULL;
        new=malloc(sizeof(struct Produtos));
        new->array=malloc(sizeof(struct repetidos));
        new->key=strdup(key); 
        new->right=new->left=NULL;
        new->height=1;
        new->tam = 1;
        new->ocup = 1; 
        (new->array)[0].price = price;
        (new->array)[0].quant= quantity;
        (new->array)[0].promo = promo;  
        node=new; 
        return node;
    }
    if (strcmp(key,node->key)<0)
        node->left  = insertProdutoVendaS(node->left, key, price, quantity, promo);
    else if (strcmp(key,node->key)>0)
        node->right = insertProdutoVendaS(node->right, key, price, quantity, promo);
    else {
        if(node->ocup == node->tam) {
        node->tam=2*node->tam;
        node->array=realloc(node->array,(node->tam)*sizeof(struct repetidos));
        }
        (node->array)[node->ocup].price=price;
        (node->array)[node->ocup].quant=quantity;
        (node->array)[node->ocup].promo=promo;
        node->ocup++;
        return node; 
    }
    /** Atualização de alturas. */
    node->height = 1 + max(alturaP(node->left),
                           alturaP(node->right));
    /** Verificação do fator de balanceamento. */
    int balance = (alturaP(node->left))-(alturaP(node->right));
    /** Rotações de acordo com os 4 casos possíveis. */
    if (balance > 1 && strcmp(key,node->left->key)<0)
        return rotateRP(node);
    if (balance < -1 && strcmp(key,node->right->key)>0)
        return rotateLP(node);
    if (balance > 1 && strcmp(key,node->left->key)>0)
    {
        node->left =  rotateLP(node->left);
        return rotateRP(node);
    }
    if (balance < -1 && strcmp(key,node->right->key)<0)
    {
        node->right = rotateRP(node->right);
        return rotateLP(node);
    }
    return node;
}

void insertProdutoVenda(Produtos *node,char *key,float price, int quantity, char promo){
    *node=insertProdutoVendaS(*node,key,price,quantity,promo);
}

char getPromo(Produtos a,int index) { 
    return (a->array)[index].promo;
}

int percorreProdutos(Produtos a) {
    int i=0;
    if(a) {
        i+=percorreProdutos(a->left);
        i+=a->ocup;
        i+=percorreProdutos(a->right);
    }
    return i;
}

/**
 * @brief Obtém a estrutura do produto com a chave dada.
 *
 * @param a Estrutura de vendas de produtos
 * @param key chave do produto
 * @return Estrutura de vendas do produto
 */

static Produtos lookupProdutoVenda(Produtos a, char* key) {
    Produtos r=NULL;
    int cmp=0,flag = 0;
    for(r = a; r != NULL && flag == 0;) {
        cmp = strcmp(key, r->key);
        if(cmp > 0) r=r->right;
        else if(cmp < 0) r = r->left;
        else flag = 1;
    }
    return r;
}



void destroyProdutos(Produtos a) {
    if(a) {
        destroyProdutos(a->left);
        destroyProdutos(a->right);
        free(a);
    }
}

void fillProdSalesProdutos(Produtos a,ProdSales *s) {
    int repetido = 0;
    if(a) {
        fillProdSalesProdutos(a->left,s);
        int i;
        for(i=0; i < s->ocup && repetido != 1;i++) {
            int cmp;
            cmp = strcmp(a->key,s->array[i].key);
            if(cmp == 0) {
                int j;
                for(j=0;j<a->ocup;j++) s->array[i].profit += a->array[j].price * a->array[j].quant;
                repetido = 1;
            }
        }
        if(repetido==0) {
            if(s->ocup == s->tam) {
                s->tam *= 2;
                s->array = realloc(s->array, (s->tam) * sizeof(*(s->array)));
                int m;
                for(m = s->ocup; m < s->tam;m++) s->array[m].profit = 0;
            }
            s->array[s->ocup].key=strdup(a->key);
            int j;
            for(j=0;j<a->ocup;j++) s->array[s->ocup].profit += a->array[j].price * a->array[j].quant;
            (s->ocup)++;
        }
        fillProdSalesProdutos(a->right,s);
    }
}

void getClienteFavouriteProductsProdutos(Produtos p, CodigosQuant *listaCodigos){
    if(p) {
        getClienteFavouriteProductsProdutos(p->left, listaCodigos);
        int i, n, existe = 0;
        for (i = 0; i < listaCodigos->ocup && existe == 0; i++) {
            if (strcmp(p->key, listaCodigos->lista[i].key) == 0) {
                existe = 1;
                for (n = 0; n < p->ocup; n++)
                    listaCodigos->lista[i].quant += p->array[n].quant;
            }
        }
        if(!existe) {
            /**Atualização da memória alocada quando a capacidade máxima é atingida*/
            if (listaCodigos->tam == listaCodigos->ocup) {
                listaCodigos->tam *= 2;
                listaCodigos->lista = realloc(listaCodigos->lista, (listaCodigos->tam) * sizeof(struct lista));
            }
            listaCodigos->lista[listaCodigos->ocup].key = strdup(p->key);
            listaCodigos->lista[listaCodigos->ocup].quant = 0;
            for (n = 0; n < p->ocup; n++)
                listaCodigos->lista[listaCodigos->ocup].quant += p->array[n].quant;
            (listaCodigos->ocup)++;
        }
        getClienteFavouriteProductsProdutos(p->right, listaCodigos);
    }
}

int getnrProductsBought(Produtos p) {
    int total = 0;
    if(p) {
        total += getnrProductsBought(p->left);
        int i;
        for(i=0;i<p->ocup;i++) {
            total += p->array[i].quant;
        }
        total += getnrProductsBought(p->right);
    }
    return total;
}

void fillArvoreProdutosProduto(Produtos p,char* key,ArvoreProdKey* a) {
    if(p) {
        fillArvoreProdutosProduto(p->left, key, a);
        ArvoreProdKey temp = lookupArvoreByKey(*a, p->key);
        if(temp) {
            int found = 0,i,index;
            index = temp->clientNumb;
            /**Atualização da memória alocada quando a capacidade máxima é atingida*/
            if(index == temp->clientCap) {
                temp->clientCap *= 2;
                temp->clients = realloc(temp->clients, (temp->clientCap) * sizeof(char*));
            }
            for(i = 0; i < index && found == 0; i++) {
                if((strcmp(key,temp->clients[i])) == 0) found = 1;
            }
            if(!found) temp->clients[(temp->clientNumb)++] = strdup(key);
        }
        fillArvoreProdutosProduto(p->right, key, a);
    }  
}

char** productBuyersOnClientesProdutos(Produtos p, char *productID,int *contador,char* promo,char** array,int* size,int* index,char* key){
        p = lookupProdutoVenda(p,productID);
        if(p) {
            int i;
            for(i=0;i<p->ocup;i++){
            /**Atualização da memória alocada quando a capacidade máxima é atingida*/ 
               if(*index == *size) {
                *size *= 2;
                array = realloc(array,(*size) * sizeof(char*));
            }
            array[(*index)]=strdup(key);
            promo[(*index)]=getPromo(p,i);
            (*index)++;
            (*contador)++;
            }
        }
        return array;
}

int nmrVendasProdutos(Produtos p){
        int vendas = 0,i;
    if(p) {
        vendas += nmrVendasProdutos(p->left);
        for(i=0;i<p->ocup;i++){
        vendas++;
        }
        vendas += nmrVendasProdutos(p->right);
    }
    return vendas;
}

void getNrVendasProductsProdutos(Produtos p, char* key, int* totalSalesN, int* totalSalesP){
    Produtos a = lookupProdutoVenda(p,key);
    int i;
    if(a)
    {
        for(i=0; i<a->ocup; i++){
            if (a->array[i].promo=='N') (*totalSalesN)++;
            else (*totalSalesP)++;
        }
    }
}
