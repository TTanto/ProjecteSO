public class Proceso {
    private int tArribada;
    private int tEspera = 0;
    private int tRetorn = 0;
    private int tResposta = 0;
    private boolean acabado;
    private String name;
    private int prioridad = 1; //Mayor numero, mayor prioridad
    private String rafaga; // CCEEECCC
    int estado_proc; //Indica en que char de rafaga nos encontramos

    private String resolucionProceso = "";  //Resolucion del proceso por el algoritmo que toque, PPEEEEEF;
    public Proceso(int tArribada, String name, String rafaga) {
        this.tArribada = tArribada;
        this.name = name;
        this.rafaga = rafaga;
    }

    public Proceso(int tArribada, String name, int prioridad, String rafaga) {
        this.tArribada = tArribada;
        this.name = name;
        this.prioridad = prioridad;
        this.rafaga = rafaga;
    }

    public Proceso(int tArribada, int prioridad, String rafaga) {
        this.tArribada = tArribada;
        this.prioridad = prioridad;
        this.rafaga = rafaga;
    }

    public int gettArribada() {
        return tArribada;
    }

    public void settArribada(int tArribada) {
        this.tArribada = tArribada;
    }

    public int gettEspera() {
        return tEspera;
    }

    public void settEspera(int tEspera) {
        this.tEspera = tEspera;
    }

    public int gettRetorn() {
        return tRetorn;
    }

    public void settRetorn(int tRetorn) {
        this.tRetorn = tRetorn;
    }

    public int gettResposta() {
        return tResposta;
    }

    public void settResposta(int tResposta) {
        this.tResposta = tResposta;
    }

    public boolean isAcabado() {
        return acabado;
    }

    public void setAcabado(boolean acabado) {
        this.acabado = acabado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getRafaga() {
        return rafaga;
    }
    public Character getEstadoProceso(){
        return  this.rafaga.charAt(this.estado_proc);
    }

    public void setRafaga(String rafaga) {
        this.rafaga = rafaga;
    }

    public String getResolucionProceso() {
        return resolucionProceso;
    }

    public void setResolucionProceso(String resolucionProceso) {
        this.resolucionProceso = resolucionProceso;
    }

    public int getEstado_proc() {
        return estado_proc;
    }

    public void setEstado_proc(int estado_proc) {
        this.estado_proc = estado_proc;
    }
    public void avanzarProceso(){
        this.estado_proc++;
        if (this.estado_proc == this.getRafaga().length()){
            this.setAcabado(true);
            this.doFinish();
        }
    }

    public boolean can_Execute(int time)
    {
        return (this.tArribada >= time);
    }

    public void doEs(){
        this.resolucionProceso = this.resolucionProceso + "W";
        this.tRetorn++;
    }
    public void doExecute(){
        this.resolucionProceso = this.resolucionProceso + "E";
        this.tRetorn++;
    }


    public void doNoHeArribat() {
        this.resolucionProceso = this.resolucionProceso + "-";
    }


    public void doWait(){
        this.resolucionProceso = this.resolucionProceso + "P";
        this.tEspera++;
        this.tRetorn++;
    }
    public void doFinish(){
        this.resolucionProceso = this.resolucionProceso + "F";
    }
}
