package sgv;

import sgv.Controller.Controlador;
import sgv.Exceptions.*;
import sgv.Model.GestVendas;
import sgv.View.IU;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class GestVendasAppMVC {
    public static void main(String[] args)  {
        ControllerInterface c;
        ViewInterface v = new IU();
        ModelInterface m = new GestVendas();
        boolean ok = false;
        while(!ok) {
            try {
                c = new Controlador();
                c.setView(v);
                int r = c.printStateOption();
                if (r == 1) {
                    m.defaultLoad();
                    c.setModel(m);
                } else {
                    ModelInterface model;
                    model = new GestVendas((GestVendas) m.loadObject());
                    c.setModel(model);
                }
                //Test t = new Test(m);
                //t.test();
                c.run();
                ok = true;
            } catch (FileNotFoundException e) {
                v.printFileNotFound();
            } catch (IOException | ClassNotFoundException | InvalidPathException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (ProdutoNaoExistenteException | CodProdutoInvalidoException e) {
                e.printStackTrace();
            }
        }
    }
}
