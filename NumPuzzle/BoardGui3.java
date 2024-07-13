package NumPuzzle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/* นาย สุรกานต์ มะจันทร์ รหัสนิสิต 6430301010 */
public class BoardGui3 implements ActionListener
{
    JFrame frame3;
    JPanel mainPanel3;
    JButton[][] button3;
    int rows3;
    int cols3;
    JLabel [][] labels3;
    int [][] board3;

    public BoardGui3()
    {
        rows3= 5;
        cols3 = 5;
        board3 = new int[rows3][cols3];
        initGui3();
    }

    public void initGui3()
    {
        //สร้าง หน้าต่าง โปรแกรม
        frame3 = new JFrame("5X5 Puzzle Game");
        mainPanel3 = new JPanel();
        mainPanel3.setBackground(Color.WHITE);
        mainPanel3.setLayout(new GridLayout(5,5));
        button3 = new JButton[rows3][cols3];
        labels3 = new JLabel[rows3][cols3];
        this.shuffleBoard3();
        for (int i =0; i<rows3; i++)
        {
            for (int j =0; j<cols3; j++)
            {
                button3[i][j] = new JButton();
                String text = i+","+j;

                button3[i][j].setText(text);
                button3[i][j].setFont(new Font("TimesRoman",Font.PLAIN,0));
                button3[i][j].addActionListener(this);
                String fileName;
                int val = board3[i][j];
                if (val != -1)
                {
                    fileName = "src/pic3/" + val + ".png";
                    labels3[i][j] = new JLabel(new ImageIcon(fileName),JLabel.CENTER);
                }
                else
                {
                    labels3[i][j] = new JLabel();
                }

                button3[i][j].add(labels3[i][j]);
                button3[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
                button3[i][j].setBackground(new Color(255,126,0));
                mainPanel3.add(button3[i][j]); //เพิ่มปุ่มไว้ใน panel
            }
        }

        frame3.add(mainPanel3);
        frame3.setVisible(true);
        frame3.setSize(500,500);
        frame3.setLocationRelativeTo(null);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void shuffleBoard3()
    {
        Random random = new Random();
        int [] array = new int[25];
        for (int i = 0; i<25; i++)
        {
            array[i] = i + 1; //เป็นการ เซตผลลัพธ์ คือ -1 และ shuffleBoard เป็น array
        }
        array[24] = -1;
        for (int i =0; i< 24;i++)
        {
            int index = random.nextInt(24);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        int count3 = 0;
        for (int  i =0; i<rows3;i++)
        {
            for (int j =0; j<cols3; j++)
            {
                board3[i][j] = array[count3];
                count3 = count3 + 1;
                System.out.print(board3[i][j] + "\t");
            }
            System.out.println("");
        }
    }
    Boolean isWin3()
    {
        int count3 = 1;
        for (int i = 0;i<rows3; i++)
        {
            for (int j =0; j<cols3; j++)
            {
                if(board3[i][j] != count3 && board3[i][j] != -1)
                {
                    return false;
                }
                count3+=1;
            }
        }
        return true;
    }

    public void displaywin3()
    {
        JFrame frame3 = new JFrame("Game Win");
        JLabel label = new JLabel("You Solve The Puzzle",JLabel.CENTER);
        label.setFont(new Font("TimesRoman",Font.BOLD,20));
        frame3.add(label);
        frame3.setLayout(new GridLayout(1,1));
        frame3.setSize(300,300);
        frame3.setBackground(Color.WHITE);
        frame3.setVisible(true);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean flag3 = isWin3();
        if (flag3 == flag3)
        {
            String s = e.getActionCommand().toString();
            int r = Integer.parseInt(s.split(",")[0]);
            int c = Integer.parseInt(s.split(",")[1]);
            if (board3[r][c] != -1)
            {
                if (r+1<rows3 && board3[r+1][c] == -1) //ขึ้น
                {
                    labels3[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic3/" + board3[r][c] + ".png";
                    labels3[r+1][c].setIcon(new ImageIcon(fileName));
                    int temp3 = board3[r][c];
                    board3[r][c] = board3[r+1][c];
                    board3[r+1][c] =temp3;

                }
                else if (r-1>=0 && board3[r-1][c] ==-1) // ลง
                {
                    labels3[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic3/" + board3[r][c] + ".png";
                    labels3[r-1][c].setIcon(new ImageIcon(fileName));
                    int temp3 = board3[r][c];
                    board3[r][c] = board3[r-1][c];
                    board3[r-1][c] =temp3;
                }
                else if (c+1<cols3 && board3[r][c+1] ==-1) // ขวา
                {
                    labels3[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic3/" + board3[r][c] + ".png";
                    labels3[r][c+1].setIcon(new ImageIcon(fileName));
                    int temp3 = board3[r][c];
                    board3[r][c] = board3[r][c+1];
                    board3[r][c+1] =temp3;
                }
                else if (c-1>=0 && board3[r][c-1] ==-1) // ซ้าย
                {
                    labels3[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic3/" + board3[r][c] + ".png";
                    labels3[r][c-1].setIcon(new ImageIcon(fileName));
                    int temp3 = board3[r][c];
                    board3[r][c] = board3[r][c-1];
                    board3[r][c-1] =temp3;
                }
            }
            flag3 = isWin3();
            if (flag3 == true)
            {
                displaywin3();

            }
        }

    }


}
