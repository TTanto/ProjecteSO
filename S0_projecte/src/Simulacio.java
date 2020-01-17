import java.io.*;
import java.util.StringTokenizer;

public class Simulacio
{
    private Proces[] arrayProces;
    private int casellaActual;
    private int quantum;

    //Mètode de llegir fitxer
    public Character[][] llegirFitxer(boolean verbose) throws IOException {

        //Fet per Joan Rialp, Oriol Figueras, Sergi Alsina
        //NOTA : Verbose s'utilitza per si vols veure els printf d'aquesta funcio

        //Path conté la ruta del fitcher que volem llegir
        String path = System.getProperty("user.dir");
        File file = new File(path + "\\src\\procesos.txt");

        //Printem la ruta del fitcher
        if (verbose) {System.out.println("LA RUTA DEL FITXER ÉS : " + file);}


        BufferedReader br = new BufferedReader(new FileReader(file));
        //Separem l'String que ens pasen a partir del ";" que hi hagin
        StringTokenizer st = new StringTokenizer(br.readLine(),";");

        String prova;

        //Creem una array bidimensional amb el total de processos que hi an
        if (verbose) {System.out.println("TOTAL PROCESOS : " + st.countTokens());}
        Character [][] array= new Character[st.countTokens()][];

        int j=0;

        //Mentre hi hagin processos per a posar a l'array...
        while (st.hasMoreTokens())
        {
            prova = st.nextToken();
            array[j] = new Character[prova.length()];

            //Separem l'String en chars i les posem a la posició corresponent a l'array bidimensional
            for (int i=0; i<prova.length();i++)
            {
                array[j][i] = prova.charAt(i);
            }

            j++;
        }

        if(verbose)
        {
            for (int i = 0; i < array.length; i++)
            {
                System.out.println("L'ARRAY A LA POSICIÓ " + i + " CONTÉ: ");

                for (int k = 0; k < array[i].length; k++)
                {
                    System.out.print(array[i][k]);
                }

                System.out.println();
            }
        }

        return array;
    }

    //Canviar l'estat del procès
    public void changeState(int state, Proces proces)
    {

    }

    //Parar la simulació
    public void stop()
    {

    }

    //Continuar la simulació
    public void continue_()
    {

    }

    //Resetejar la simulació
    public void restart()
    {

    }


    //Triar entre diferents planificadors
    public void chosePlan()
    {

    }

}
