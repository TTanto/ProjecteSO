import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class Data extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JButton URL;
    private JTextField text_ncpu;
    private String _url;
    private int numCPU;



    public Data() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);



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

        URL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);

                if(returnValue == JFileChooser.APPROVE_OPTION){
                    File selectedFile = jfc.getSelectedFile();
                    _url = selectedFile.getAbsolutePath();
                    System.out.println(_url);
                }
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
        numCPU = Integer.parseInt(text_ncpu.getText());
        Simulacio s = new Simulacio();
        s.llegirFitxer(_url, true);
        Data_2 d2 = new Data_2(s.getTotalProc(), comboBox1.getSelectedItem().toString(), s, numCPU);
        d2.pack();
        d2.setSize(600,400);
        d2.setVisible(true);

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }



}
