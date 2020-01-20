import javax.swing.*;
import java.util.List;

public class Formulas_FIFO extends JFrame{
    private int Tesp = 0;
    private int Tres = 0;
    private int Trtn = 0;
    private int Tret = 0;
    private int util = 0;
    int temps = 0;
    private JPanel panel1;
    private JLabel util_CPU;
    private JLabel productivitat;
    private JLabel TesperaMitj;
    private JLabel TretornMitj;
    private JLabel TretornN_Mitj;
    private JLabel sol_Util_CPU;
    private JLabel sol_Prod;
    private JLabel sol_TesperaM;
    private JLabel TretornM;
    private JLabel sol_TretorN_Mitj;

    public Formulas_FIFO(List<Proceso> list) {

        for(int i = 0; i<list.size();i++){
            Tret += list.get(i).gettRetorn();
            Tesp += list.get(i).gettEspera();
            Tres +=  list.get(i).gettResposta();
            Trtn += list.get(i).gettRetorn()/list.get(i).getRafaga().length();
            util += list.get(i).getRafaga().length();
            if (list.get(i).getTemps() > temps){
                temps = list.get(i).getTemps();
            }
        }

        sol_TesperaM.setText(String.valueOf(Tesp/list.size()));
        TretornM.setText(String.valueOf(Tret/list.size()));
        sol_TretorN_Mitj.setText(String.valueOf(Trtn/list.size()));
        sol_Util_CPU.setText(String.valueOf(util/temps));



    }
}