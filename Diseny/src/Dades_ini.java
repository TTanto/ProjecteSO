import javax.swing.*;
import java.awt.event.*;

//Aquest Formulari es l'entrada del numero de processos, de cpu's i el tipus de algoritme usat.
public class Dades_ini extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField text_pro;
    private JTextField text_cpu;
    private JComboBox combo_al;
    public int num_process;
    public int num_cpu;
    public String algoritme;

    public Dades_ini() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        this.num_process = Integer.parseInt(text_pro.getText());
        this.num_cpu = Integer.parseInt(text_cpu.getText());
        this.algoritme =  combo_al.getSelectedItem().toString();

        switch(algoritme){
            case("FIFO/FCFS"):
                Dades_fin dadesF = new Dades_fin(num_process, num_cpu, algoritme);
                dadesF.pack();
                dadesF.setSize(400,200);
                dadesF.setVisible(true);
                break;
            case("SJF"):
                Dades_fin dadesS = new Dades_fin(num_process, num_cpu, algoritme);
                dadesS.pack();
                dadesS.setSize(400,200);
                dadesS.setVisible(true);
                break;
            case("ROUND ROBIN"):
                Dades_rR dadesR = new Dades_rR(num_process, num_cpu, algoritme);
                dadesR.pack();
                dadesR.setSize(400,200);
                dadesR.setVisible(true);
                break;
        }



        // conexion siguuiente form
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
