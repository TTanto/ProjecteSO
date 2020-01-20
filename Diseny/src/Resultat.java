import javax.print.DocFlavor;
import javax.swing.*;
import java.util.List;
import java.util.ListIterator;

public class Resultat  extends JDialog {
    private Ejecucion e;
    private JPanel panel1;
    private JButton veureFuncionsButton;
    private JPanel func;
    private JButton form;
    private JLabel[] rows;


    public Resultat(Ejecucion e, String plan)
    {
        this.e = e;
        setContentPane(panel1);

        rows = new JLabel[e.entrada.size()];

        if (plan.equals("FCFS/FIFO"))
        {
            e.process();
            int col = get_max_columns();
            int rows = e.entrada.size();

            String data[][] = new String[rows][col];
            String[] colname = new String[col];

            for (int i = 0; i < colname.length; i++)
            {
                colname[i] = String.valueOf(i);
                System.out.println(colname[i]);
            }

            for (int i = 0; i < rows; i++)
            {
                Proceso act = e.entrada.get(i);
                System.out.println(act.getResolucionProceso());

                for (int j = 0; j < act.getResolucionProceso().length(); j++)
                {
                    data[i][j] = String.valueOf(act.getResolucionProceso().charAt(j));
                    System.out.printf("%s",data[i][j]);
                }
            System.out.println(i);
            }

            Formulas_FIFO fifo = new Formulas_FIFO(e.entrada);
            fifo.pack();
            fifo.setSize(400,400);
            fifo.setVisible(true);

            JFrame f;
            f=new JFrame();


            JTable table = new JTable(data,colname);
            table.setBounds(30,40,200,300);
            JScrollPane sp=new JScrollPane(table);
            f.add(sp);
            f.setSize(900,400);
            f.setLocationRelativeTo(null);
            f.setVisible(true);






        }

    }

    int get_max_columns()
    {
        int max = 0;
        for (int i = 0; i < e.entrada.size(); i++)
        {
            int llargada_proces = e.entrada.get(i).getResolucionProceso().length();
           if (llargada_proces > max)
           {
               max = llargada_proces;
           }
        }

        return max;
    }
}


