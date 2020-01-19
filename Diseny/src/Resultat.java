import javax.swing.*;
import java.util.List;
import java.util.ListIterator;

public class Resultat  extends JDialog {
    private Ejecucion e;
    private JPanel panel1;
    private JLabel taula;
    private JLabel[] rows;


    public Resultat(Ejecucion e, String plan) {
        setContentPane(panel1);
        String graella = "";
        rows = new JLabel[e.entrada.size()];
        //show_Results();
        if (plan.equals("FCFS/FIFO")) {
            e.process();
            for (int i = 0; i < e.entrada.size(); i++) {
                System.out.println(e.entrada.get(i).getResolucionProceso());
                graella += i + "     " + e.entrada.get(i).getResolucionProceso()+"\n";

            }
            System.out.println(graella);
            taula.setText(graella);
            taula.setVisible(true);

        }

    }


//    public void show_Results()
//    {
//        e.process();
//        for (int i =0; i < e.entrada.size(); i++)
//        {
//            System.out.println(e.entrada.get(i).getResolucionProceso());
//        }
//    }
    }


