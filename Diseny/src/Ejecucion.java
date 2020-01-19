import java.lang.reflect.Array;
import java.util.List;

public class Ejecucion {
    public List<Proceso> entrada; //Lista de lso procesos a pasar por la cpu
    private List<String> salida; //Lista de las string resultwnte de tratar los procesos con el algoritmo que toque

    private boolean apropiatiu;
    private int quantum = -1;
    private String algoritmo;
    private boolean prioridad = true;
    public Ejecucion(List<Proceso> entrada, boolean apropiatiu, int quantum, String algoritmo) {
        this.entrada = entrada;
        this.apropiatiu = apropiatiu;
        this.quantum = quantum;
        this.algoritmo = algoritmo;
    }

    public Ejecucion(List<Proceso> entrada, boolean apropiatiu, String algoritmo) {
        this.entrada = entrada;
        this.apropiatiu = apropiatiu;
        this.algoritmo = algoritmo;
    }

    public void process(){
            //Aqui inspeccio namos lso parametros iniciales, y ejecutamos un metodo u otro dependiendo de ellos. Alguna combinación de parametros no tendra sentido, entonces no la programamos
        if(isApropiatiu()){
            //Por ejemplo, por defincion, sfj no es apropiatiu, no programaos esa posibilidad
            switch (this.algoritmo){
                case "robin":
                    if(prioridad){
                        this.robinApropiatiuPrioridad();
                    }
                    else{
                        //this.sfjApropiatiuNoPrioridad()
                    }
                    break;
            }

        }
        else{
            switch (this.algoritmo)
            {
                case "FCFS/FIFO":
                    this.FIFO();
                case "sfj":
                    if(prioridad){
                        this.sfjNoApropiatiuPrioridad();
                    }
                    else{
                        //this.sfjApropiatiuNoPrioridad()
                    }
                    break;
            }

        }
    }


    boolean is_on_p(Proceso array[], Proceso p)
    {
        for (int i =0; i< array.length; i++)
        {
            if (array[i] != (null))
            {
                if (array[i].getName().equals(p.getName()))
                {
                    return true;
                }
            }
        }

        return false;

    }
    boolean get_Proc_Lliure(Proceso array[], Proceso p)
    {
        if (is_on_p(array,p))
        {
            return true;
        }
        for (int i =0; i< array.length; i++)
        {
            if (array[i] == (null))
            {
                if(p.getRafaga().charAt(p.getEstado_proc())  != 'W')
                {
                    array[i] = p;
                }
                return true;
            }
        }
        return false;
    }

    void set_Proc_Lliure(Proceso array[], Proceso p)
    {
        for (int i =0; i< array.length; i++)
        {
            if (array[i] != null)
            {
                if (array[i].getName().equals(p.getName()))
                {
                    array[i] = null;
                }
             }
        }
    }


    Proceso[] actualitzar_procesos(Proceso array[])
    {
        for (int i =0; i< array.length; i++)
        {
            if (array[i] != null && !array[i].isAcabado())
            {
                if(array[i].getRafaga().charAt(array[i].getEstado_proc()) == 'W')
                {
                    array[i] = null;
                }
            }
        }

        return array;
    }

    void FIFO()
    {
        int p_acabats = 0;
        int time = 0;
        int total_p = entrada.size();

        //El dos es el numero de processadors, falta pasasarli al constructor
        Proceso proc_asignats[] = new Proceso[2];

        for (int i =0; i< proc_asignats.length; i++)
        {
            proc_asignats[i] = null;
        }
        System.out.println("Executatant FIFO...");

        entrada.get(0).setName("0");
        entrada.get(1).setName("1");
        entrada.get(2).setName("2");

        while (p_acabats != total_p)
        {
            p_acabats = 0;
            actualitzar_procesos(proc_asignats);

            for (int i = 0; i < entrada.size(); i++)
            {


                Proceso actual = entrada.get(i);
                if (!actual.isAcabado())
                {
                    if (!actual.can_Execute(time))
                    {
                        System.out.println("proces," + actual.getName() + "es pot executar al temps " + time + "amb rafaga" + actual.getRafaga());
                        if (get_Proc_Lliure(proc_asignats, actual)) //Si hi han procesadors lliures...
                        {
                            System.out.println("Proces " + actual.getName() + "és pot executar");

                            if (actual.getRafaga().charAt(actual.getEstado_proc()) == 'E')
                            {
                                actual.doExecute();
                                actual.avanzarProceso();
                            } else if (actual.getRafaga().charAt(actual.getEstado_proc()) == 'W') {
                                actual.doEs();
                                actual.avanzarProceso();
                                set_Proc_Lliure(proc_asignats, actual);
                            }
                        } else
                            {
                                System.out.println("Proces " + actual.getName() + "No és pot executar");

                                if (actual.getRafaga().charAt(actual.getEstado_proc()) == 'W') {
                                actual.doEs();
                                actual.avanzarProceso();
                            } else {
                                actual.doWait();
                            }
                        }
                    }


                }
                else
                {
                    System.out.println("Proces " + actual.getName() + "ha acabat ");
                    set_Proc_Lliure(proc_asignats, actual);
                    p_acabats++;
                }
            }

            System.out.println("TIME -->" + time);
            if (proc_asignats[0] == null) {System.out.println("NULL````````"); }
            else {System.out.println(proc_asignats[0].getName() + "````````");}
            if (proc_asignats[1] == null) {System.out.println("NULL````````"); }
            else {System.out.println(proc_asignats[1].getName() + "````````");}
            time++;
        }
    }
    private void sfjNoApropiatiuPrioridad() {
        //Comprabamos como condicion de while que no todos lso procesos de la entrada estan acabados por isFinished si,
        //Sino lo estan, vemos que procesos tiene la rafaga mas corta por entrada[i].getRafaga.length, si empatan, como tenemos prioridad desempatamos por ellas
        //Proceso.doEs o doWait o doEXecute, en un for de 0 a length.entrada o al numero de procesos vivos que queden, execute en el escojido en el paso anterior, wait o es donde toquen por proceso.getEstadoProceso
        //Cada Proceso, aun en el for: proces.avanzarProceso()


        //Si hemos acabado, calculamso todos los tiempos de cada proceso, pro contadores o manera a implementar (tRetorn, tespera, etc)
        //En this.Salida dejamos la lista formada por añadir  cada uno de los proces.getResolucionProceso en un array

        //TO-DO: Decidir si implementamos emtodos en la clase ejcucion para los tiempos de respuesta, retorn, etc Medios, no solo de cada proces, si implementamos, crear metodos.
        //TO-DO: Implementar subrutinas para buscar el proceso que mas lelev esperando, cuantos quedan vivos, devolver lista de los procesos vivos que queden, etc..
    }

    private void robinApropiatiuPrioridad() {  //Añadir Parametro quantum?
    }



    public List<Proceso> getEntrada() {
        return entrada;
    }

    public void setEntrada(List<Proceso> entrada) {
        this.entrada = entrada;
    }

    public List<String> getSalida() {
        return salida;
    }

    public void setSalida(List<String> salida) {
        this.salida = salida;
    }

    public boolean isApropiatiu() {
        return apropiatiu;
    }

    public void setApropiatiu(boolean apropiatiu) {
        this.apropiatiu = apropiatiu;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public boolean isPrioridad() {
        return prioridad;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }
}
