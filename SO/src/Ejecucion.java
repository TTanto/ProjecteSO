import java.util.ArrayList;
import java.util.List;

public class Ejecucion {
    public List<Proceso> entrada; //Lista de lso procesos a pasar por la cpu
    private List<String> salida; //Lista de las string resultwnte de tratar los procesos con el algoritmo que toque

    private boolean apropiatiu;
    private int quantum = -1;
    private String algoritmo;
    private boolean prioridad ;
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

    private void process(){
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

                case "prioritat":

                    this.prioritatNoApropiatiu();

                    break;
                case "sjf":
                    if(prioridad){
                        this.sjfApropiatiuPrioridad();
                    }
                    else{
                        this.sjfApropiatiuNoPrioridad();
                    }
            }

        }
        else{
            switch (this.algoritmo){
                case "sfj":
                    if(prioridad){
                        this.sfjNoApropiatiuPrioridad();
                    }
                    else{
                        //this.sjfApropiatiuNoPrioridad();
                    }
                case "FIFO"
                    this.algoritme_FCFS();
                    break;
            }

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

    private void prioritatNoApropiatiu(){
        int acabats = 0;
        int arribats=0;
        int [] prior = masPrioritario(); //array de les arribades dels procesos, ordenar per prioritat
        while (acabats<=this.entrada.size()) {
            if (arribats==prior.length) {//quan tots ja han arribat ya no entra més
                for (int i = 0; i < this.entrada.size(); i++) {
                    Proceso p = this.entrada.get(i);
                    procesar(i);
                    arribats++;
                }
            }
            for (int i = 0; i < this.entrada.size(); i++) {
                Proceso p = this.entrada.get(i);
                if(p.getEstadoProceso() == 'C'){
                    procesar(i);
                }

            }
        }
    }
    private int[] masPrioritario() {
        int[] ordenat = new int[15];
        for (int i = 0; i < this.entrada.size(); i++) {
            Proceso p = this.entrada.get(i);
            ordenat[i] = p.getPrioridad();
        }
        for (int i = 0; i < this.entrada.size(); i++) {
            for (int j = 0; j < this.entrada.size(); j++) {
                if (ordenat[i] > ordenat[j]) {
                    int variableauxiliar = ordenat[i];
                    ordenat[i] = ordenat[j];
                    ordenat[j] = variableauxiliar;
                }
            }
        }
        return ordenat;
    }

    private void procesar(int acabats){
        for (int i = 0; i <= entrada.size(); i++) {
            Proceso p = this.entrada.get(i);
            while (p.getEstadoProceso() == 'E') {
                p.doExecute();
            }
            if (p.getEstadoProceso() == 'C') {
                p.doWait();
            }
            else {
                p.doFinish();
                acabats++;
            }
            p.avanzarProceso();
        }
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
    private void sjfApropiatiuNoPrioridad(){
        int qProcesos=this.entrada.size(), indexProces=0, acc=-1,k=0;
        int cont=0, temps=0;
        boolean finished=false;
        Proceso aux;
        int lI[]= new int[qProcesos];

        while(cont<qProcesos) {
            for (int i=0;i<qProcesos;i++){
                lI[i]=0;
            }
            for (int i = 0; i < qProcesos; i++) {


                k=0;
                if (!this.entrada.get(i).isAcabado() && this.entrada.get(i).getEstadoProceso()=='E'){
                    while((this.entrada.get(i).getRafaga().length()>this.entrada.get(i).estado_proc+k) && (this.entrada.get(i).getRafaga().charAt(this.entrada.get(i).estado_proc+k) == 'E')){
                        lI[i]++;
                        k++;
                    }
                }
                else{
                    lI[i]=999;
                }
                System.out.println("indice "+i+" valor "+ lI[i]);
            }
            acc = 1000;
            for (int i = 0; i < lI.length; i++) {
                if (lI[i]<acc) {
                    acc = lI[i];
                    indexProces = i;
                }
            }

            for (int i = 0; i < qProcesos; i++) {
                if (this.entrada.get(i).gettArribada() <= temps) {
                    if (i == indexProces && this.entrada.get(indexProces).getEstadoProceso() == 'E') {
                        this.entrada.get(i).doExecute();
                        this.entrada.get(i).avanzarProceso();
                    } else {
                        if (!this.entrada.get(i).isAcabado()) {
                            if (this.entrada.get(i).getRafaga().charAt(this.entrada.get(i).estado_proc) == 'W') {
                                this.entrada.get(i).doEs();
                                this.entrada.get(i).avanzarProceso();
                            } else {
                                this.entrada.get(i).doWait();
                            }
                        }
                    }
                }
                else{
                    this.entrada.get(i).doNothing();
                }
            }
            for (int i = 0; i < qProcesos; i++) {
                if (this.entrada.get(i).isAcabado()) {
                    cont++;
                }
            }
            temps++;
        }
    }
    private void sjfApropiatiuPrioridad(){
        int qProcesos=this.entrada.size(), indexProces=0, acc=-1,k=0;
        int cont=0, temps=0;
        boolean finished=false;
        Proceso aux;
        int lI[]= new int[qProcesos];

        while(cont<qProcesos) {
            for (int i=0;i<qProcesos;i++){
                lI[i]=0;
            }
            for (int i = 0; i < qProcesos; i++) {


                k=0;
                if (!this.entrada.get(i).isAcabado() && this.entrada.get(i).getEstadoProceso()=='E'){
                    while((this.entrada.get(i).getRafaga().length()>this.entrada.get(i).estado_proc+k) && (this.entrada.get(i).getRafaga().charAt(this.entrada.get(i).estado_proc+k) == 'E')){
                        lI[i]++;
                        k++;
                    }
                }
                else{
                    lI[i]=999;
                }
                System.out.println("indice "+i+" valor "+ lI[i]);
            }
            acc = 1000;
            for (int i = 0; i < lI.length; i++) {
                if (lI[i]==acc) {
                    if(this.entrada.get(i).getPrioridad()>this.entrada.get(indexProces).getPrioridad()){
                        acc=lI[i];
                        indexProces=i;
                    }
                }
                else if(lI[i]<acc){
                    acc = lI[i];
                    indexProces = i;
                }
            }
            for (int i = 0; i < qProcesos; i++) {
                if (this.entrada.get(i).gettArribada() <= temps) {
                    if (i == indexProces) {
                        this.entrada.get(i).doExecute();
                        this.entrada.get(i).avanzarProceso();
                    } else {
                        if (!this.entrada.get(i).isAcabado()) {
                            if (this.entrada.get(i).getRafaga().charAt(this.entrada.get(i).estado_proc) == 'W') {
                                this.entrada.get(i).doEs();
                                this.entrada.get(i).avanzarProceso();
                            } else {
                                this.entrada.get(i).doWait();
                            }
                        }
                    }
                }
                else{
                    this.entrada.get(i).doNothing();
                }
            }
            for(int i=0;i<qProcesos;i++){
                if(this.entrada.get(i).isAcabado()){
                    cont++;
                }
            }
            temps++;
        }
    }

    private void algoritme_FCFS(){

        int acabats = 0;
        int arribats = 0;
        int [] ordenats = ordenarFIFO();

        while (acabats<this.entrada.size()){
            if (arribats < ordenats.length) {

                for (int i = 0; i < this.entrada.size(); i++) {

                    Proceso p = this.entrada.get(i);
                    procesar(i);
                    arribats++;
                }

                for (int i = 0; i < this.entrada.size(); i++) {
                    Proceso p = this.entrada.get(i);
                    if(p.getEstadoProceso() == 'C'){
                        procesar(i);
                    }

                }
            }

        }
    }

    private int[] ordenarFIFO() {
        int[] ordenat = new int[15];
        for (int i = 0; i < this.entrada.size(); i++) {
            Proceso p = this.entrada.get(i);
            ordenat[i] = p.gettArribada();
        }
        for (int i = 0; i < this.entrada.size(); i++) {
            for (int j = 0; j < this.entrada.size(); j++) {
                if (ordenat[i] > ordenat[j]) {
                    int variableauxiliar = ordenat[i];
                    ordenat[i] = ordenat[j];
                    ordenat[j] = variableauxiliar;
                }
            }
        }
        return ordenat;
    }

