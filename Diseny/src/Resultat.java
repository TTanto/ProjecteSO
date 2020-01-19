import javax.swing.*;

public class Resultat  extends JDialog
{
    private  Ejecucion e;
    private JPanel panel1;

    public Resultat(Ejecucion e)
    {
        setContentPane(panel1);
        this.setVisible(true);
        this.setSize(500,500);
        this.e = e;
        show_Results();
    }

    public void show_Results()
    {
        e.process();
        for (int i =0; i < e.entrada.size(); i++)
        {
            System.out.println(e.entrada.get(i).getResolucionProceso());
        }
    }
}
