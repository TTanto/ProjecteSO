import java.lang.reflect.Array;
import java.util.List;
import java.util.Stack;

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


    boolean isOnStack(Stack pila, Proceso p)
    {
        return  pila.contains(p);
    }

    boolean have_Procesor(Stack<Proceso> pila, Proceso p)
    {
        if (!pila.isEmpty())
        {
            return (pila.get(0).equals(p));
        }
        else
        {
            pila.push(p);
            return true;
        }
    }


    void FIFO()
    {
        //Aquest mètode només funciona amb un procesador (no té en compte les prioritats tampoc)
        int p_acabats = 0;
        int time = 1;
        int total_p = entrada.size();
        Stack<Proceso> pila = new Stack<>();

        entrada.get(0).setName("0");
        entrada.get(1).setName("1");
        entrada.get(2).setName("2");

        //Mentre no hagin acabat tots el processos...
        while (p_acabats != total_p)
        {
            p_acabats = 0;
            Proceso procesador = null;
            if (!pila.isEmpty())
            {
                //El proces a la posicio 0 de la pila és el primer que ha entrat
                procesador = pila.get(0);

                //Si el proces actual no acabat i vol fer una W al seguent loop
                //Deixem el processador lliure
                if (!procesador.isAcabado())
                {
                    if (procesador.getRafaga().charAt(procesador.getEstado_proc()) == 'W')
                    {
                        pila.remove(0);
                    }
                }
            }

            //Recorrem la llista de procesos
            for (int i = 0; i < entrada.size(); i++)
            {
                Proceso actual = entrada.get(i);

                if (!actual.isAcabado())
                {
                    //Si el proces actual no ha acabat i pot executarse, és a dir, ja ha arribat
                    if (!actual.can_Execute(time))
                    {
                        //Si vol fer un Write el pot fer (independentment de si te el procesador o no)
                        if (actual.getRafaga().charAt(actual.getEstado_proc()) == 'W')
                        {
                            actual.doEs();
                            actual.avanzarProceso();
                        }
                        else
                        {
                            //Si vol executar-se
                            if (actual.getRafaga().charAt(actual.getEstado_proc()) == 'E')
                            {
                                //Si té el procesador (en cas de que no el tingui ningu retorna true)
                                if (have_Procesor(pila,actual))
                                {
                                    //S'executa
                                    actual.doExecute();
                                    actual.avanzarProceso();
                                }
                                //Si no té el procesador
                                else
                                {
                                    //S'espera
                                    actual.doWait();
                                    //El poso a la pila en cas de que no hi sigui
                                    if (!isOnStack(pila,actual))
                                    {
                                        pila.push(actual);
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        actual.doNoHeArribat();
                    }
                }
                else
                {
                    //Si el proces ja ha acabat i encara esta a la pila...
                    if (isOnStack(pila,actual))
                    {
                        //El treiem de la pila
                        pila.remove(0);
                    }

                    p_acabats++;
                }
            }
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
