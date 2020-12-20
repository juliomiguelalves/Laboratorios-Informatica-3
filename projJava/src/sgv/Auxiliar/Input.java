package sgv.Auxiliar;

import sgv.Exceptions.LimiteInvalidoException;
import sgv.Exceptions.MesInvalidoException;
import sgv.Exceptions.OpcaoFilialException;
import sgv.Exceptions.OpcaoLoadException;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.in;

public class Input {

    /*
     * Métodos de Classe
     */

    /**
     * Funçao que nos permite ler uma string.
     * @return String dada como input.
     */
    public static String lerString() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        String txt = "";
        while(!ok) {
            try {
                txt = input.nextLine();
                ok = true;
            }
            catch(InputMismatchException e)
            { input.nextLine();
            }
        }
        //input.close();
        return txt;
    }

    /**
     * Funçao que nos permite ler um int.
     * @return Int que foi dado como input.
     */
    public static int lerInt() {
        Scanner input = new Scanner(in);
        int i = 0;
        i = input.nextInt();
        //input.close();
        return i;
    }

    /**
     * Funçao que nos permite ler um inteiro como mes, dando a exceçao se o input dado nao for valido.
     * @return Mes dado como input.
     * @throws MesInvalidoException Exceçao se o mes dado for menor que zero ou maior que doze.
     */
    public static int lerMes() throws MesInvalidoException {
        Scanner input = new Scanner(in);
        int i = 0;
        i = input.nextInt();
        if(i<0 || i> 12) throw new MesInvalidoException(i+"");
        //input.close();
        return i;
    }

    /**
     * Funçao que le um inteiro como um limite, dando a exceçao se o limite nao for valido.
     * @return limite dado como input.
     * @throws LimiteInvalidoException Exceçao se o limite dado for inferior a zero.
     */
    public static int lerLimite() throws LimiteInvalidoException {
        Scanner input = new Scanner(in);
        int i = 0;
        i = input.nextInt();
        if(i<=0 ) throw new LimiteInvalidoException(i+"");
        //input.close();
        return i;
    }

    /**
     * Funçao que le um inteiro como um uma opçao, dando exceçao nao for valida
     * @return Opcao que foi dada como input
     * @throws OpcaoFilialException Exceçao se a opçao for diferente de um e de dois.
     */
    public static int lerOpcaoFilial() throws OpcaoFilialException {
        Scanner input = new Scanner(in);
        int i = 0;
        i = input.nextInt();
        if(i!=1 && i!=2) throw new OpcaoFilialException(i+"");
        //input.close();
        return i;
    }

    /**
     * Funçao que le um inteiro como um uma opçao, dando exceçao nao for valida
     * @return Opcao que foi dada como input
     * @throws OpcaoLoadException Exceçao se a opçao for diferente de um e de dois.
     */
    public static int lerOpcaoLoad() throws OpcaoLoadException {
        Scanner input = new Scanner(in);
        int i = 0;
        i = input.nextInt();
        if(i!=1 && i!=2) throw new OpcaoLoadException(i+"");
        //input.close();
        return i;
    }

    /**
     * Funçao que le um double dado como input.
     * @return Double que foi dado como input.
     */
    public static double lerDouble() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        double d = 0.0;
        while(!ok) {
            try {
                d = input.nextDouble();
                ok = true;
            }
            catch(InputMismatchException e)
            {
                input.nextLine();
            }
        }
       // input.close();
        return d;
    }

    /**
     * Funçao que le um float dado como input.
     * @return Float que foi dado como input.
     */
    public static float lerFloat() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        float f = 0.0f;
        while(!ok) {
            try {
                f = input.nextFloat();
                ok = true;
            }
            catch(InputMismatchException e)
            {
                input.nextLine();
            }
        }
        //input.close();
        return f;
    }

    /**
     * Funçao que le um boolean dado como input.
     * @return Boolean dado como input.
     */
    public static boolean lerBoolean() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        boolean b = false;
        while(!ok) {
            try {
                b = input.nextBoolean();
                ok = true;
            }
            catch(InputMismatchException e)
            {
                input.nextLine();
            }
        }
        //input.close();
        return b;
    }

    /**
     * Funçao que le um short dado como input
     * @return short dado como input.
     */
    public static short lerShort() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        short s = 0;
        while (!ok) {
            try {
                s = input.nextShort();
                ok = true;
            } catch (InputMismatchException e) {
                input.nextLine();
            }
        }
        //input.close();
        return s;
    }
}
