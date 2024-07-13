package NumPuzzle;
/* นาย สุรกานต์ มะจันทร์ รหัสนิสิต 6430301010 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    JFrame frame;

    JButton button;
    JButton button2;
    JButton button3;

    public Main(){
        MainGui();
    }
    public void MainGui()
    {

        frame = new JFrame("NumPuzzle");

        button = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        button.setBackground(new Color(255,126,0));
        button.setText("3X3");
        button.setFont(new Font("TimesRoman",Font.BOLD,50));
        button2.setText("4X4");
        button2.setFont(new Font("TimesRoman",Font.BOLD,50));
        button3.setText("5X5");
        button3.setFont(new Font("TimesRoman",Font.BOLD,50));
        button.setBorder(BorderFactory.createLineBorder(Color.black,2));
        button2.setBorder(BorderFactory.createLineBorder(Color.black,2));
        button3.setBorder(BorderFactory.createLineBorder(Color.black,2));
        button2.setBackground(new Color(255,126,0));
        button3.setBackground(new Color(255,126,0));
        frame.setLayout(new GridLayout(3,1));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardGui2 gui = new BoardGui2();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardGui gui = new BoardGui();
                frame.dispose();
            }

        });

        button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardGui3 gui = new BoardGui3();
                frame.dispose();
            }

        });

        frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.setSize(600,600);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

