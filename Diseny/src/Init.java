import javax.swing.*;
import java.awt.event.*;


public class Init extends JFrame {
    private JPanel contentPane;
    private JButton button_ini;

    public static void main(String[] args) {
        Init main = new Init();
        main.pack();
        main.setSize(300,200);
        main.setVisible(true);

    }

  public Init(){
        setContentPane(contentPane);



        button_ini.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Data dadesInit = new Data();
                dadesInit.pack();
                dadesInit.setSize(600,400);
                dadesInit.setVisible(true);

            }
        });





    }





}
