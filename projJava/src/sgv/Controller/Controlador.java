package sgv.Controller;

import sgv.Auxiliar.Input;
import sgv.Auxiliar.MyPair;
import sgv.ControllerInterface;
import sgv.Exceptions.*;
import sgv.ModelInterface;
import sgv.ViewInterface;

import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Map;

public class Controlador implements ControllerInterface {
    private ModelInterface model;
    private ViewInterface view;
    private boolean exec;

    public Controlador(ModelInterface model, ViewInterface view,boolean exec) {
        this.model = model;
        this.view = view;
    }

    public Controlador(Controlador cont) {
        this.model = cont.getModel();
        this.view = cont.getView();
        this.exec = cont.isExec();
    }

    public Controlador() {

    }

    /**
     * {@inheritDoc}
     */
    public ModelInterface getModel() {
        return model.clone();
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(ModelInterface model) {
        this.model = model.clone();
    }

    /**
     * {@inheritDoc}
     */
    public ViewInterface getView() {
        return view;
    }
    /**
     * {@inheritDoc}
     */
    public void setView(ViewInterface view) {
        this.view = view;
    }
    /**
     * {@inheritDoc}
     */
    public boolean isExec() {
        return exec;
    }
    /**
     * {@inheritDoc}
     */
    public void setExec(boolean exec) {
        this.exec = exec;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ControllerInterface clone() {
        return new Controlador(this);
    }

    /**
     * {@inheritDoc}
     */
    public void run() throws InputMismatchException,IOException {
        int opcao = -1;
        int op = 0;
        int mes = 0;
        String prod;
        String cli;
        int limite=0;
        boolean ok = false;
        this.exec = true;
        while (exec) {
            view.printMenuInicial();
            opcao = Input.lerInt();
            switch (opcao) {
                case 0:
                    stopExec(exec);
                    break;
                case 1:
                    Set<String> neverBoughtProductsSorted =  this.model.neverBoughtProductsSorted();
                    int controla=-1;
                    prod="";
                    int page =0;
                    int maxpage;
                    int totalEle=neverBoughtProductsSorted.size();
                    if(totalEle%15==0) maxpage = totalEle/15;
                    else maxpage=totalEle/15 +1;
                    while(controla!=0) {
                        view.printProdutosNuncaComprados(neverBoughtProductsSorted, page,maxpage,prod);
                        prod="";
                        view.printControladorPaginacaoProduto();
                        controla = Input.lerInt();
                        switch (controla){
                            case 0:
                                break;
                            case 1:
                                if(page >0) page--;
                                break;
                            case 2:
                                if(page<maxpage-1) page++;
                                break;
                            case 3:
                                int aux =page;
                                view.printInsiraPagina();
                                page = Input.lerInt();
                                if(page > maxpage || page < 1){
                                    view.printPaginaInvalido();
                                    page=aux;
                                }
                                else page--;
                                break;
                            case 4:
                                view.printInsiraCodProd();
                                prod=Input.lerString();
                            default:
                                break;
                        }
                    }
                    break;
                case 2:
                    ok = false;
                    while(!ok) {
                        try {
                            view.printOpcaoFilial();
                            op = Input.lerOpcaoFilial();
                            ok = true;
                        } catch (OpcaoFilialException | InputMismatchException e) {view.printInputInvalido();}
                    }
                    ok = false;
                    while(!ok) {
                        try{
                            view.printInsiraOMes();
                            mes = Input.lerMes();
                            if (op == 1) {
                                int[] totalSalesAndClientsMonth = this.model.totalSalesAndClientsMonth(mes);
                                view.printTotalSalesAndClientsMonth(totalSalesAndClientsMonth);
                                view.printEnterToContinue();
                                String espera = Input.lerString();
                            }
                            if (op == 2) {
                                List<int[]> totalSalesAndClientsMonthByBranch = this.model.totalSalesAndClientsMonthByBranch(mes);
                                view.printTotalSalesAndClientsMonthByBranch(totalSalesAndClientsMonthByBranch);
                                view.printEnterToContinue();
                                String espera = Input.lerString();
                            }
                            ok = true;
                        }
                        catch(MesInvalidoException | InputMismatchException e) {view.printInputInvalido();}
                    }
                    break;
                case 3:
                    ok = false;
                    while (!ok){
                        try {
                            int control = -1;
                            int month = 0;
                            view.printInsiraCodCli();
                            cli = Input.lerString();
                            List<Double[]> clientSalesAndWasted = this.model.clientSalesAndWasted(cli);
                            while (control != 0) {
                                view.printClientSalesAndWasted(clientSalesAndWasted, month);
                                view.printControladorPaginacaoMeses();
                                control = Input.lerInt();
                                switch (control) {
                                    case 0:
                                        break;
                                    case 1:
                                        if (month >= 3) month-=3;
                                        break;
                                    case 2:
                                        if (month <= 8) month+=3;
                                        break;
                                    case 3:
                                        int aux =month;
                                        view.printInsiraOMes();
                                        month = Input.lerInt();
                                        if(month > 12 || month < 1){
                                            view.printMesInvalido();
                                            month=aux;
                                        }
                                        else month--;
                                        break;
                                    default:
                                        break;
                                }
                                ok = true;
                            }
                        } catch (ClienteNaoExistenteException c) {
                            view.printClienteNaoExiste(c.getMessage());
                        } catch (CodClienteInvalidoException c) {
                            view.printCodInvalido();
                        }
                    }
                    break;
                case 4:
                    ok = false;
                    while(!ok) {
                        try {
                            view.printInsiraCodProd();
                            prod = Input.lerString();
                            int month = 0;
                            int control = -1;
                            List<Double[]> productsSalesAndWasted = this.model.productSalesAndWasted(prod);
                            while (control != 0) {
                                view.printProductSalesAndWasted(productsSalesAndWasted, month);
                                view.printControladorPaginacao();
                                control = Input.lerInt();
                                switch (control) {
                                    case 0:
                                        break;
                                    case 1:
                                        if (month >= 3) month-=3;
                                        break;
                                    case 2:
                                        if (month <= 8) month+=3;
                                        break;
                                    case 3:
                                        int aux =month;
                                        view.printInsiraOMes();
                                        month = Input.lerInt();
                                        if(month > 12 || month < 1){
                                            view.printMesInvalido();
                                            month=aux;
                                        }
                                        else month--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            ok = true;
                        } catch (ProdutoNaoExistenteException p) {
                            view.printProdutoNaoExiste(p.getMessage());
                        } catch (CodProdutoInvalidoException p) {
                            view.printCodInvalido();
                        }
                    }
                    break;
                case 5:
                    ok = false;
                    while(!ok) {
                        try {
                            view.printInsiraCodCli();
                            cli = Input.lerString();
                            page = 0;
                            prod="";
                            int control = -1;
                            Map<Integer, Set<String>> clientMostBoughtProducts = this.model.clientMostBoughtProducts(cli);
                            totalEle =clientMostBoughtProducts.size();
                            if(totalEle % 15 ==0) maxpage=totalEle/15;
                            else maxpage= totalEle/15 +1;
                            while (control != 0) {
                                view.printClientMostBoughtProducts(clientMostBoughtProducts, page,prod);
                                prod="";
                                view.printNmrPaginas(page,maxpage);
                                view.printControladorPaginacaoProduto();
                                control = Input.lerInt();
                                switch (control) {
                                    case 0:
                                        break;
                                    case 1:
                                        if (page > 1) page--;
                                        break;
                                    case 2:
                                        if (page < maxpage) page++;
                                        break;
                                    case 3:
                                        int aux =page;
                                        view.printInsiraPagina();
                                        page = Input.lerInt();
                                        if(page > maxpage || page < 1){
                                            view.printPaginaInvalido();
                                            page=aux;
                                        }
                                        else page--;
                                        break;
                                    case 4:
                                        view.printInsiraCodProd();
                                        prod =Input.lerString();
                                    default:
                                        break;
                                }
                            }
                            ok = true;
                        } catch (ClienteNaoExistenteException c) {
                            view.printClienteNaoExiste(c.getMessage());
                        } catch (CodClienteInvalidoException c) {
                            view.printCodInvalido();
                        }
                    }
                    break;
                case 6:
                    ok = false;
                    while(!ok) {
                        try {
                            view.printInsiraLimite();
                            limite = Input.lerLimite();
                            page = 0;
                            prod ="";
                            int control = -1;
                            Map<Integer, List<MyPair<String, Integer>>> nMostSoldProducts = this.model.nMostSoldProducts(limite);
                            totalEle=nMostSoldProducts.size();
                            if (limite > 5) {
                                if(totalEle%5==0)  maxpage = limite / 5;
                                else  maxpage = limite / 5 +1 ;
                            }
                            else maxpage = 1;
                            while (control != 0) {
                                view.printNMostSoldProducts(nMostSoldProducts, page, limite,prod);
                                view.printNmrPaginas(page,maxpage);
                                view.printControladorPaginacaoProduto();
                                control = Input.lerInt();
                                switch (control) {
                                    case 0:
                                        break;
                                    case 1:
                                        if (page > 0) page--;
                                        break;
                                    case 2:
                                        if (page < maxpage-1) page++;
                                        break;
                                    case 3:
                                        int aux =page;
                                        view.printInsiraPagina();
                                        page = Input.lerInt();
                                        if(page > maxpage|| page < 1){
                                            view.printPaginaInvalido();
                                            page=aux;
                                        }
                                        else page--;
                                        break;
                                    case 4:
                                        view.printInsiraCodProd();
                                        prod=Input.lerString();
                                    default:
                                        break;
                                }
                            }
                            ok = true;
                        } catch (LimiteInvalidoException | InputMismatchException | NumberFormatException e) {
                            view.printInputInvalido();
                        }
                    }
                    break;
                case 7:
                    List<Map<String, Double>> tresMaioresCompradoresCadaFilial = this.model.tresMaioresCompradoresCadaFilial();
                    view.printListMap(tresMaioresCompradoresCadaFilial);
                    view.printEnterToContinue();
                    String espera =Input.lerString();
                    break;
                case 8:
                    ok = false;
                    while(!ok) {
                        try {
                            view.printInsiraLimite();
                            limite = Input.lerLimite();
                            cli="";
                            int control = -1;
                            page = 0;
                            Map<Integer, List<String>> nMostDiverseClients = this.model.nMostDiverseClients(limite);
                            totalEle = nMostDiverseClients.values().stream().mapToInt(List::size).sum();
                            if (limite > 5) {
                                if(totalEle% 5 ==0) maxpage=totalEle/ 5;
                                else maxpage = totalEle/ 5 +1;
                            }
                            else maxpage = 1;
                            while (control != 0) {
                                view.printNMostDiverseClients(nMostDiverseClients, limite, page,cli);
                                view.printNmrPaginas(page,maxpage);
                                view.printControladorPaginacaoCliente();
                                control = Input.lerInt();
                                switch (control) {
                                    case 0:
                                        break;
                                    case 1:
                                        if (page > 0) page--;
                                        break;
                                    case 2:
                                        if (page < maxpage-1) page++;
                                        break;
                                    case 3:
                                        int aux =page;
                                        view.printInsiraPagina();
                                        page = Input.lerInt();
                                        if(page > maxpage || page < 1){
                                            view.printPaginaInvalido();
                                            page=aux;
                                        }
                                        else page--;
                                        break;
                                    case 4:
                                        view.printInsiraCodCli();
                                        cli = Input.lerString();
                                    default:
                                        break;
                                }
                            }
                            ok = true;
                        } catch (LimiteInvalidoException | InputMismatchException | NumberFormatException e) {
                            view.printInputInvalido();
                        }
                    }
                    break;
                case 9:
                    ok = false;
                    while(!ok) {
                        try {
                            int control = -1;
                            cli="";
                            page = 0;
                            view.printInsiraCodProd();
                            prod = Input.lerString();
                            view.printInsiraOnmrDeClientesDesejado();
                            limite = Input.lerLimite();
                            Map<Integer, Map<String, Double>> nMostBuyers = this.model.nMostBuyers(limite, prod);
                            totalEle=nMostBuyers.size();
                            if(totalEle%5 == 0 ) maxpage=totalEle/5;
                            else maxpage = totalEle/5 +1;
                            while (control != 0) {
                                view.printNMostBuyers(nMostBuyers, prod,page,cli);
                                view.printNmrPaginas(page,maxpage);
                                view.printControladorPaginacaoCliente();
                                control = Input.lerInt();
                                switch (control) {
                                    case 0:
                                        break;
                                    case 1:
                                        if (page > 0) page--;
                                        break;
                                    case 2:
                                        if (page < maxpage-1) page++;
                                        break;
                                    case 3:
                                        int aux =page;
                                        view.printInsiraPagina();
                                        page = Input.lerInt();
                                        if(page > maxpage || page < 1){
                                            view.printPaginaInvalido();
                                            page=aux;
                                        }
                                        else page--;
                                        break;
                                    case 4:
                                        view.printInsiraCodCli();
                                        cli = Input.lerString();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            ok = true;
                        } catch (LimiteInvalidoException | InputMismatchException | NumberFormatException e) {
                            view.printInputInvalido();
                        } catch (ProdutoNaoExistenteException p) {
                            view.printProdutoNaoExiste(p.getMessage());
                        } catch (CodProdutoInvalidoException p) {
                            view.printCodInvalido();
                        }
                    }
                    break;
                case 10:
                    int control=-1;
                    page=0;
                    prod="";
                    Map<String, double[][]> m1 = this.model.profitProd();
                    while(control!=0){
                        maxpage=m1.size();
                        view.printProfitProd(m1,page,prod);
                        view.printNmrPaginas(page,maxpage);
                        view.printControladorPaginacaoProduto();
                        control = Input.lerInt();
                        switch (control){
                            case 0:
                                break;
                            case 1:
                                if(page >1) page--;
                                break;
                            case 2:
                                if(page<maxpage-1) page++;
                                break;
                            case 3:
                                int aux =page;
                                view.printInsiraPagina();
                                page = Input.lerInt();
                                if(page > maxpage || page < 1){
                                    view.printPaginaInvalido();
                                    page=aux;
                                }
                                else page--;
                                break;
                            case 4:
                                view.printInsiraCodProd();
                                prod=Input.lerString();
                            default:
                                break;
                        }
                    }
                    break;
                case 11:
                    menuEstatistico();
                    break;
                case 12:
                    this.model.saveObject();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void stopExec(boolean exec){
        this.exec=false;
    }

    /**
     * Corre o menu estatístico do programa.
     */
    private void menuEstatistico(){
        int opcao=-1;
        while(opcao!=0){
            view.printMenuEstatistico();
            opcao=Input.lerInt();

            switch (opcao){

                case 0:
                    break;
                case 1:
                    view.printFicheiroVendas(this.model.getFileVendas());
                    view.printEnterToContinue();
                    String espera =Input.lerString();
                    break;
                case 2:
                    view.printNmrRgVendasErrado(this.model.numberInvalidSales());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 3:
                    view.printNmrTotalProd(this.model.numberOfProducts());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 4:
                    view.printNmrProdDifComprados(this.model.nmrDifProdBought());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 5:
                    view.printNmrProdNComprados(this.model.nmrProductsNeverBought());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 6:
                    view.nmrTotalClientes(this.model.numberOfClients());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 7:
                    view.printNmrCliDifComprados(this.model.nmrDifCliBought());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 8:
                    view.printNmrCliNCompraram(this.model.nmrClientsNeverBought());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 9:
                    view.nmrTotalCompras0(this.model.cost0Sales());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 10:
                    view.printGlobalIncome(this.model.globalIncome());
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 11:
                    int filial=1;
                    int control =-1;
                    while(control!=0) {
                        view.printNmrTotalComprasMes(this.model.totalSalesByMonth(), filial);
                        view.printControladorPaginacaoFilial();
                        control=Input.lerInt();
                        switch (control) {
                            case 0:
                                break;
                            case 1:
                                if(filial>1) filial--;
                                break;
                            case 2:
                                if(filial <3) filial++;
                                break;
                            case 3:
                                view.printInsiraAFilial();
                                filial = Input.lerInt();
                                break;
                            default:
                                break;
                        }
                    }
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 12:
                    filial=1;
                    control =-1;
                    while(control!=0) {
                        view.printFaturacaoTotalMes(this.model.totalIncomeByMonth(),filial);
                        view.printControladorPaginacaoFilial();
                        control=Input.lerInt();
                        switch (control) {
                            case 0:
                                break;
                            case 1:
                                if(filial>1) filial--;
                                break;
                            case 2:
                                if(filial <3) filial++;
                                break;
                            case 3:
                                view.printInsiraAFilial();
                                filial = Input.lerInt();
                                break;
                            default:
                                break;
                        }
                    }
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                case 13:
                    filial=1;
                    control =-1;
                    while(control!=0) {
                        view.printTotalClientsBoughtMonth(this.model.totalClientsBoughtByMonth(),filial);
                        view.printControladorPaginacaoFilial();
                        control=Input.lerInt();
                        switch (control) {
                            case 0:
                                break;
                            case 1:
                                if(filial>1) filial--;
                                break;
                            case 2:
                                if(filial <3) filial++;
                                break;
                            case 3:
                                view.printInsiraAFilial();
                                filial = Input.lerInt();
                                break;
                            default:
                                break;
                        }
                    }
                    view.printEnterToContinue();
                    espera =Input.lerString();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public int printStateOption(){
        boolean ok = false;
        int r = 0;
        while(!ok) {
            ok = false;
            try {
                view.printOpcaoLoad();
                r = Input.lerOpcaoLoad();
                ok = true;
            } catch (OpcaoLoadException e) {
                e.printStackTrace();
            }
        }
        return r;
    }

}
