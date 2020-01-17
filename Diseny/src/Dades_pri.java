import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dades_pri extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    JLabel[] label = new JLabel [5];
    JTextField[] t_a = new JTextField [5];
    JTextField[] raf = new JTextField[5];


    private void InitLabels(int P, int C){
        Insert_labels(P,C, label, t_a, raf);

    }

    private void Insert_labels(int P, int C, JLabel[] label, JTextField[] t_a, JTextField[] raf){
        //SHA DE SOLUCIONAR
        for(int i = 0; i<P;i++){
            label[i] = new JLabel();
            t_a[i] = new JTextField();
            raf[i] = new JTextField();
            label[i].setBounds(new Rectangle(15, 20* (i +1), 100, 25));
            int suma =i+1;
            label[i].setText((String.valueOf(suma)));
            t_a[i].setBounds(new Rectangle(100, 20*(i+1), 100, 25));
            raf[i].setBounds(new Rectangle(200, 20*(i+1), 100, 25));
            .add(label[i], null);
            .add(t_a[i], null);
            .add(raf[i], null);

        }

    }

    public Dades_pri(int P, int C, String algoritme) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        InitLabels(P,C);

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
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
