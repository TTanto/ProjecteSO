import javax.swing.*;
import java.awt.event.*;


public class Init extends JFrame {
    private JPanel contentPane;
    private JButton button_ini;

    public static void main(String[] args) {
        Init main = new Init();
        main.pack();
        main.setSize(400,200);
        main.setVisible(true);

    }

  public Init(){
        setContentPane(contentPane);



        button_ini.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dades_ini dadesInit = new Dades_ini();
                dadesInit.setSize(400,400);
                dadesInit.setVisible(true);

            }
        });





    }





}
