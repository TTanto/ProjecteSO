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
    private JPanel pan_quan;
    private JTextField text_qua;
    private JButton URL;
    private String _url;



    public Data() {
        pan_quan.setVisible(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        if (comboBox1.getSelectedItem().toString() == "ROUND ROBIN"){
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
        Simulacio s = new Simulacio();
        s.llegirFitxer(_url, true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
