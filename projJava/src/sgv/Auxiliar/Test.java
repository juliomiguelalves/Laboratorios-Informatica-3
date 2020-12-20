package sgv.Auxiliar;

import sgv.Exceptions.*;
import sgv.ModelInterface;

public class Test {
    private ModelInterface m;

    public Test(ModelInterface model) {
        m = model;
    }

    public void test() throws CodClienteInvalidoException, ClienteNaoExistenteException, ProdutoNaoExistenteException, CodProdutoInvalidoException, InputInvalidoException {
        Crono.start();
        m.neverBoughtProductsSorted();
        Crono.stop();
        System.out.println("Time query 1: " + Crono.getTime());
        Crono.start();
        m.totalSalesAndClientsMonth(1);
        Crono.stop();
        System.out.println("Time query 2 global : " + Crono.getTime());
        Crono.start();
        m.totalSalesAndClientsMonthByBranch(1);
        Crono.stop();
        System.out.println("Time query 2 by branch : " + Crono.getTime());
        Crono.start();
        m.clientSalesAndWasted("T1805");
        Crono.stop();
        System.out.println("Time query 3 : " + Crono.getTime());
        Crono.start();
        m.productSalesAndWasted("AA1001");
        Crono.stop();
        System.out.println("Time query 4 : " + Crono.getTime());
        Crono.start();
        m.clientMostBoughtProducts("T1805");
        Crono.stop();
        System.out.println("Time query 5 : " + Crono.getTime());
        Crono.start();
        m.nMostSoldProducts(5);
        Crono.stop();
        System.out.println("Time query 6 limit 5 R : " + Crono.getTime());
        Crono.start();
        m.nMostSoldProducts(1000);
        Crono.stop();
        System.out.println("Time query 6 limit 1000 R : " + Crono.getTime());
        Crono.start();
        m.tresMaioresCompradoresCadaFilial();
        Crono.stop();
        System.out.println("Time query 7 : " + Crono.getTime());
        Crono.start();
        m.nMostDiverseClients(5);
        Crono.stop();
        System.out.println("Time query 8 limit 5 : " + Crono.getTime());
        Crono.start();
        m.nMostDiverseClients(1000);
        Crono.stop();
        System.out.println("Time query 8 limit 1000 : " + Crono.getTime());
        Crono.start();
        m.nMostBuyers(5,"AA1001");
        Crono.stop();
        System.out.println("Time query 9 limit 5 : " + Crono.getTime());
        Crono.start();
        m.nMostBuyers(1000,"AA1001");
        Crono.stop();
        System.out.println("Time query 9 limit 1000 R : " + Crono.getTime());
        Crono.start();
        m.profitProd();
        Crono.stop();
        System.out.println("Time query 10 : " + Crono.getTime());
    }
}
