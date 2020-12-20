CC = gcc
CFLAGS = -I include -Wall -O3 -ansi -D_GNU_SOURCE -Wno-unused-result
VPATH = src
DOC = docs/Doxyfile

all:
	make allprog

program:
	make allprog

Auxiliar: Auxiliar.c Auxiliar.h
	$(CC) -o Auxiliar.o -c Cliente.c

Cliente: Cliente.c Cliente.h Auxiliar.o
	$(CC) -o Cliente.o -c Cliente.c

Produto: Produto.c Produto.h Auxiliar.o
	$(CC) -o Produto.o -c Produto.c

CatalogoCliente: CatalogoCliente.c Cliente.o CatalogoCliente.h
	$(CC) -o CatalogoCliente.o -c CatalogoCliente.c

CatalogoProduto: CatalogoProduto.c CatalogoProduto.h Produto.o
	$(CC) -o CatalogoProduto.o -c CatalogoProduto.c

FaturacaoDados: FaturacaoDados.c FaturacaoDados.h Auxiliar.o
	$(CC) -o FaturacaoDados.o -c FaturacaoDados.c 

FaturacaoMes: FaturacaoMes.c FaturacaoMes.h FaturacaoDados.o
	$(CC) -o FaturacaoMes.o -c FaturacaoMes.c

FaturacaoFilial: FaturacaoFilial.c FaturacaoFilial.h FaturacaoMes.o
	$(CC) -o FaturacaoFilial.o -c FaturacaoFilial.c

Faturacao: Faturacao.c Faturacao.h FaturacaoFilial.o 
	$(CC) -o Faturacao.o -c Faturacao.c

FiliaisProdutos: FiliaisProdutos.c FiliaisProdutos.h Auxiliar.o
	$(CC) -o FiliaisProdutos.o -c FiliaisProdutos.c

FiliaisClientes: FiliaisProdutos.o FiliaisClientes.c FiliaisClientes.h
	$(CC) -o FiliaisClientes.o -c FiliaisClientes.c

FiliaisMeses: FiliaisMeses.c FiliaisMeses.h FiliaisClientes.o
	$(CC) -o FiliaisMeses.o -c FiliaisMeses.c

Filiais: Filiais.c Filiais.h FiliaisMeses.o
	$(CC) -o Filiais.o -c Filiais.c

UI : UI.c UI.h Auxiliar.o
	$(CC) -o UI.o -c UI.c

SGV: CatalogoCliente.o CatalogoProduto.o Filiais.o Faturacao.o UI.o SGV.c interface.h
	$(CC) -o SGV.o -c SGV.c  

main: main.c SGV.o interface.h
	$(CC) -o main.o -c main.c

allprog: Auxiliar.o Cliente.o CatalogoCliente.o Produto.o CatalogoProduto.o FiliaisProdutos.o FiliaisClientes.o FiliaisMeses.o Filiais.o FaturacaoDados.o FaturacaoMes.o FaturacaoFilial.o Faturacao.o UI.o SGV.o main.o
	$(CC) Auxiliar.o Cliente.o CatalogoCliente.o Produto.o CatalogoProduto.o FaturacaoDados.o FaturacaoMes.o FaturacaoFilial.o Faturacao.o FiliaisProdutos.o FiliaisClientes.o FiliaisMeses.o Filiais.o UI.o SGV.o main.o -o program 

clean:
	rm -f *.o program

.PHONY: doc
doc: Auxiliar.o Cliente.o CatalogoCliente.o Produto.o CatalogoProduto.o FiliaisProdutos.o FiliaisClientes.o FiliaisMeses.o Filiais.o FaturacaoDados.o FaturacaoMes.o FaturacaoFilial.o Faturacao.o UI.o SGV.o main.o
	doxygen $(DOC)