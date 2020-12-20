#include "FiliaisClientes.h"

/**
 *  @brief Estrutura de dados que guarda a informação que relaciona a venda dos produtos e as compras dos clientes, 
 *  utilizando árvores balanceadas.  
 */
struct Clientes {
    char* key;
    int height;
    Produtos prod;
    struct Clientes *right;
    struct Clientes *left;
};

/**
 * @brief Procura as vendas de um cliente na estrutura de venda de clientes. 
 * 
 * @param a Estrutura da vendas dos clientes
 * @param key Chave do cliente
 * @return Estrutura de vendas do cliente
 */
static Clientes lookupClienteVenda(Clientes a, char* key) {
    Clientes r=NULL;
    int cmp, flag = 0;
    for(r = a;r!=NULL && flag == 0;) {
        cmp = strcmp(key, r->key);
        if(cmp > 0) r = r->right;
        else if(cmp < 0) r = r->left;
        else flag = 1;
    }
    return r;
}


void insertProdutoVendaCliente(Clientes c, char* prod,float price, int quant, char promo,char* cli) {
        c=lookupClienteVenda(c,cli);
        if(c) insertProdutoVenda(&c->prod,prod,price,quant,promo);
}

int percorreClientes(Clientes a) {
    int i=0;
    if(a) {
        i+=percorreClientes(a->left);
        i+=percorreProdutos(a->prod);
        i+=percorreClientes(a->right);
    }
    return i;
}

static int alturaC(Clientes a) {
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
 * @param y Árvore de vendas dos clientes.
 * @return Árvore equilibrada das vendas dos clientes.
 */

static Clientes rotateRC(Clientes y)
{
    Clientes x = y->left;
    Clientes T2 = x->right;
    /** Realiza a rotação. */
    x->right = y;
    y->left = T2;
    /** Atualiza as alturas. */
    y->height = max(alturaC(y->left), alturaC(y->right))+1;
    x->height = max(alturaC(x->left), alturaC(x->right))+1;
    return x;
}

/**
 * @brief Roda a árvore em questão para a esquerda de forma a gerir o balanceamento da mesma.
 * 
 * @param x Árvore de vendas dos clientes.
 * @return Árvore equilibrada das vendas dos clientes.
 */

static Clientes rotateLC(Clientes x)
{
    Clientes y = x->right;
    Clientes T2 = y->left;
    /** Realiza a rotação. */
    y->left = x;
    x->right = T2;
    /** Atualiza as alturas. */
    x->height = max(alturaC(x->left), alturaC(x->right))+1;
    y->height = max(alturaC(y->left), alturaC(y->right))+1;
    return y;
}

static Clientes insertClienteVendaS(Clientes node,char* key, Produtos a)
{
     /** Inserção normal de um elemento numa árvore de procura. */
    if (node == NULL){
        Clientes new=NULL;
        new=malloc(sizeof(struct Clientes));
        new->key=strdup(key);
        new->prod=a;
        new->right=new->left=NULL;
        new->height=1;
        node=new;
        return node;
    }
    if (strcmp(key,node->key)<0)
        node->left  = insertClienteVendaS(node->left, key, a);
    else if (strcmp(key,node->key)>0)
        node->right = insertClienteVendaS(node->right, key, a);
    else
        return node;
    /** Atualização de alturas. */
    node->height = 1 + max(alturaC(node->left),
                           alturaC(node->right));
    /** Verificação do fator de balanceamento. */
    int balance = (alturaC(node->left))-(alturaC(node->right));
    /** Rotações de acordo com os 4 casos possíveis. */
    if (balance > 1 && strcmp(key,node->left->key)<0)
        return rotateRC(node);
    if (balance < -1 && strcmp(key,node->right->key)>0)
        return rotateLC(node);
    if (balance > 1 && strcmp(key,node->left->key)>0)
    {
        node->left = rotateLC(node->left);
        return rotateRC(node);
    }
    if (balance < -1 && strcmp(key,node->right->key)<0)
    {
        node->right = rotateRC(node->right);
        return rotateLC(node);
    }
    return node;
}

void insertClienteVenda(Clientes *node,char* key, Produtos a){
    *node=insertClienteVendaS(*node,key,a);
}


char** productBuyersOnClientes(Clientes a, char *productID,int *contador,char* promo,char** array,int* size,int* index) {
        if(a){
        array=productBuyersOnClientes(a->left, productID,contador,promo,array,size,index);
        array=productBuyersOnClientesProdutos(a->prod,productID,contador,promo,array,size,index,a->key);
        array=productBuyersOnClientes(a->right, productID,contador,promo,array,size,index);
        }

    return array;
}


void fillProdSales(Clientes a, char* key, ProdSales *s) {
    int cmp;
    Clientes b = a;
    int breakflag = 0;
    /**Procura do cliente na estrutura*/
    while(b!=NULL && breakflag == 0) {
        cmp = strcmp(key,b->key);
        if(cmp > 0) b = b->right;
        else if(cmp < 0) b = b->left;
        else breakflag = 1;
    }
    if(breakflag) fillProdSalesProdutos(b->prod,s);
}

void destroyClientes(Clientes a) {
    if(a) {
        destroyClientes(a->left);
        destroyClientes(a->right);
        destroyProdutos(a->prod);
        free(a);
    }
}

void getClienteFavouriteProductsClientes(Clientes c, char* clientID, CodigosQuant *listaCodigos){
    Clientes cli = lookupClienteVenda(c, clientID);
    if(cli)
        getClienteFavouriteProductsProdutos(cli->prod, listaCodigos);
}

int getnrProductsBoughtByClient(Clientes c,char* clientID) {
    Clientes a;
    int ret = 0;
    a = lookupClienteVenda(c,clientID);
    if(a)
        ret = getnrProductsBought(a->prod);
    return ret;
}

void fillArvoreProdutosCliente(Clientes c,ArvoreProdKey* a) {
    if(c) {
        fillArvoreProdutosCliente(c->left, a);
        fillArvoreProdutosProduto(c->prod,c->key,a);
        fillArvoreProdutosCliente(c->right, a);
    }
}
int isBoughtMesClientes(Clientes c,char *key){
    int i=0;
    if(lookupClienteVenda(c,key)) i=1;

    return i;
}

int nmrVendasClientes(Clientes a){
    int vendas=0;
    if(a){
    vendas += nmrVendasClientes(a->left);
    vendas += nmrVendasProdutos(a->prod);
    vendas += nmrVendasClientes(a->right);
    }
    return vendas;
}

void getNrVendasProductsClientes(Clientes c, char* key, int *totalSalesN, int *totalSalesP){
    if(c){
        getNrVendasProductsClientes(c->left, key, totalSalesN, totalSalesP);
        getNrVendasProductsProdutos(c->prod, key, totalSalesN, totalSalesP);
        getNrVendasProductsClientes(c->right, key, totalSalesN, totalSalesP);
    }
}
