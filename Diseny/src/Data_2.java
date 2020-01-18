import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.StringTokenizer;

public class Data_2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField text_tarr;
    private JTextField text_pri;
    private JTextField text_qua;
    private JPanel pan_quan;
    public Simulacio s;


    public Data_2(int proces, String plan, Simulacio s) {
        System.out.println(plan);
        this.s = s;
        pan_quan.setVisible(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        if(plan.equals("ROUND ROBIN")){
            pan_quan.setVisible(true);

        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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

    private void onOK() throws IOException {
        // add your code here
        if(pan_quan.isVisible()){
            s.set_quantum(Integer.parseInt(text_qua.getText()));
        }
        int[] arr_tarr = new int[s.getTotalProc()];
        int[] arr_pri = new int[s.getTotalProc()];
        String tarr = text_tarr.getText();
        String tpri = text_pri.getText();

        StringTokenizer token1 = new StringTokenizer(tarr, ";");
        StringTokenizer token2 = new StringTokenizer(tpri, ";");
        int i = 0;
        int j = 0;
        while(token1.hasMoreTokens()){
            if (i < s.getTotalProc()) {
                String str = token1.nextToken();
                arr_tarr[i] = Integer.parseInt(str);
                i++;
            }

        }
        while(token2.hasMoreTokens()){
            if (j < s.getTotalProc()) {
                String str = token2.nextToken();
                arr_pri[j] = Integer.parseInt(str);
                j++;
            }

        }
        s.set_Prioritat(arr_pri);
        s.set_t_Arribada(arr_tarr);

        s.create_List_Proces();
        s.printAllData();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
