import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Simulacio
{
    private String[] array_rafaga;
    private List<Proceso> arrayProces;
    private int [] array_tArribada;
    private int [] array_prioridad;
    private int CPU;
    private int quantum;
    private int total_p;

    private List<String> resultats;

    public void printAllData(){
        for(int i = 0; i < total_p; i++){
            System.out.println("Procés "+ i + ",  temps d'arribada : " + arrayProces.get(i).gettArribada());
            System.out.println("Procés "+ i + ",  Rafaga : " + arrayProces.get(i).getRafaga());
            System.out.println("Procés "+ i + ",  prioritat : " + arrayProces.get(i).getPrioridad());
            System.out.println("Procés "+ i + ",  numero de CPU's : " + CPU);
        }

    }
    //Mètode de llegir fitxer
    public void llegirFitxer(String URL, boolean verbose) throws IOException {

        //Fet per Joan Rialp, Oriol Figueras, Sergi Alsina
        //NOTA : Verbose s'utilitza per si vols veure els printf d'aquesta funcio

        //Path conté la ruta del fitcher que volem llegir
//        String path = System.getProperty("user.dir");
        char[] path = URL.toCharArray();
        for( int i = 0; i< URL.length(); i++){
            if(path[i] == '/'){
                path[i] = '\\'  ;
            }
        }
        URL = String.valueOf(path);

        File file = new File(URL);

        //Printem la ruta del fitcher
        if (verbose) {System.out.println("LA RUTA DEL FITXER ÉS : " + file);}


        BufferedReader br = new BufferedReader(new FileReader(file));
        //Separem l'String que ens pasen a partir del ";" que hi hagin
        StringTokenizer st = new StringTokenizer(br.readLine(),";");

        String prova;

        //Creem una array bidimensional amb el total de processos que hi an
        if (verbose) {System.out.println("TOTAL PROCESOS : " + st.countTokens());}
        String[] array= new String[st.countTokens()];
        this.total_p = st.countTokens();

        int j=0;

        //Mentre hi hagin processos per a posar a l'array...
        while (st.hasMoreTokens())
        {
            prova = st.nextToken();
            array[j] = prova;
            j++;
        }

        if(verbose)
        {
            for (int i = 0; i < array.length; i++)
            {
                System.out.println("L'ARRAY A LA POSICIÓ " + i + " CONTÉ: " + array[i] );
            }
        }
        setArray_rafaga(array);
    }

    public List create_List_Proces() throws IOException
    {
        // Un cop llegit el fitxer de text i tenim les dades (temps arribada i prioritat) de
        // cada proces, creem una Llista de procesos amb totes les dades

        //Exemple amb 4 procesos :
        //Array t_Arribada = [0,5,1]
        //Array  prioritats = [1,2,3]
        //Array rafaga = ["EEEE", "EEEEEEEEEEWE", "EWWWWWWWWWWE"]
        //Proces 1 --> temps_arribada = 0,  prioritat = 1,  rafaga = "EEEE"
        //Proces 1 --> temps_arribada = 5,  prioritat = 2,  rafaga = "EEEEEEEEEEWE"
        //Proces 1 --> temps_arribada = 1,  prioritat = 3,  rafaga = "EWWWWWWWWWWE"

        arrayProces = new ArrayList<Proceso>();

        for (int i =0; i < total_p; i++)
        {
            Proceso p = new Proceso(array_tArribada[i], "test", array_prioridad[i], array_rafaga[i]);
            arrayProces.add(p);
        }
        return arrayProces;
    }

    public Simulacio(){
        this.array_rafaga = null;
        this.array_prioridad = null;
        this.quantum = 0;

    }
//    public Simulacio(int[]array_temps_arribada, int[] array_prioritat, int quantum ) throws IOException
//    {
//        //this.array_rafaga = llegirFitxer(,false);
//        set_t_Arribada(array_temps_arribada);
//        set_Prioritat(array_prioritat);
//        set_quantum(quantum);
//        create_List_Proces();
//    }

    public void set_t_Arribada(int[] array)
    {
        this.array_tArribada = array;
    }
    public void setArray_rafaga(String[] array){

        this.array_rafaga = array;
        this.total_p = array.length;

    }

    public void set_Prioritat(int[] array)
    {
        this.array_prioridad = array;
    }

    public void set_quantum(int quantum)
    {
        this.quantum = quantum;
    }

    public int get_quantum()
    {
        return this.quantum;
    }


    public int getTotalProc(){
        return this.total_p;
    }

    public List<Proceso> getListProc(){
        return this.arrayProces;
    }

}
