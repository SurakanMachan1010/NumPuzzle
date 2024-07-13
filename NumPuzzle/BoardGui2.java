package NumPuzzle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/* นาย สุรกานต์ มะจันทร์ รหัสนิสิต 6430301010 */
public class BoardGui2 implements ActionListener
{
    JFrame frame2;
    JPanel mainPanel2;
    JButton[][] button2;
    int rows2;
    int cols2;
    JLabel [][] labels2;
    int [][] board2;

    public BoardGui2()
    {
        rows2= 3;
        cols2 = 3;
        board2 = new int[rows2][cols2];
        initGui2();
    }

    public void initGui2()
    {
        //สร้าง หน้าต่าง โปรแกรม
        frame2 = new JFrame("3X3 Puzzle Game");
        mainPanel2 = new JPanel();
        mainPanel2.setBackground(Color.WHITE);
        mainPanel2.setLayout(new GridLayout(3,3));
        button2 = new JButton[rows2][cols2];
        labels2 = new JLabel[rows2][cols2];
        this.shuffleBoard2();
        for (int i =0; i<rows2; i++)
        {
            for (int j =0; j<cols2; j++)
            {
                button2[i][j] = new JButton();
                String text = i+","+j;

                button2[i][j].setText(text);
                button2[i][j].setFont(new Font("TimesRoman",Font.PLAIN,0));
                button2[i][j].addActionListener(this);
                String fileName;
                int val = board2[i][j];
                if (val != -1)
                {
                    fileName = "src/pic2/" + val + ".png";
                    labels2[i][j] = new JLabel(new ImageIcon(fileName),JLabel.CENTER);
                }
                else
                {
                    labels2[i][j] = new JLabel();
                }
                button2[i][j].add(labels2[i][j]);
                button2[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
                button2[i][j].setBackground(new Color(255,126,0));
                mainPanel2.add(button2[i][j]); //เพิ่มปุ่มไว้ใน panel
            }
        }

        frame2.add(mainPanel2);
        frame2.setVisible(true);
        frame2.setSize(500,500);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void shuffleBoard2()
    {
        Random random = new Random();
        int [] array = new int[9];
        for (int i = 0; i<9; i++)
        {
            array[i] = i + 1; //เป็นการ เซตผลลัพธ์ คือ -1 และ shuffleBoard เป็น array
        }
        array[8] = -1;
        for (int i =0; i< 9;i++)
        {
            int index = random.nextInt(9);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        int count2 = 0;
        for (int  i =0; i<rows2;i++)
        {
            for (int j =0; j<cols2; j++)
            {
                board2[i][j] = array[count2];
                count2 = count2 + 1;
                System.out.print(board2[i][j] + "\t");
            }
            System.out.println("");
        }
    }
    Boolean isWin2()
    {
        int count2 = 1;
        for (int i = 0;i<rows2; i++)
        {
            for (int j =0; j<cols2; j++)
            {
                if(board2[i][j] != count2 && board2[i][j] != -1)
                {
                    return false;
                }
                count2+=1;
            }
        }
        return true;
    }

    public void displaywin2()
    {
        JFrame frame2 = new JFrame("Game Win");
        JLabel label2 = new JLabel("You Solve The Puzzle",JLabel.CENTER);
        label2.setFont(new Font("TimesRoman",Font.BOLD,20));
        frame2.add(label2);
        frame2.setLayout(new GridLayout(1,1));
        frame2.setSize(300,300);
        frame2.setBackground(Color.WHITE);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean flag2 = isWin2();
        if (flag2 == flag2)
        {
            String s = e.getActionCommand().toString();
            int r = Integer.parseInt(s.split(",")[0]);
            int c = Integer.parseInt(s.split(",")[1]);
            if (board2[r][c] != -1)
            {
                if (r+1<rows2 && board2[r+1][c] == -1) //ขึ้น
                {
                    labels2[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic2/" + board2[r][c] + ".png";
                    labels2[r+1][c].setIcon(new ImageIcon(fileName));
                    int temp2 = board2[r][c];
                    board2[r][c] = board2[r+1][c];
                    board2[r+1][c] =temp2;

                }
                else if (r-1>=0 && board2[r-1][c] ==-1) // ลง
                {
                    labels2[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic2/" + board2[r][c] + ".png";
                    labels2[r-1][c].setIcon(new ImageIcon(fileName));
                    int temp2 = board2[r][c];
                    board2[r][c] = board2[r-1][c];
                    board2[r-1][c] =temp2;
                }
                else if (c+1<cols2 && board2[r][c+1] ==-1) // ขวา
                {
                    labels2[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic2/" + board2[r][c] + ".png";
                    labels2[r][c+1].setIcon(new ImageIcon(fileName));
                    int temp = board2[r][c];
                    board2[r][c] = board2[r][c+1];
                    board2[r][c+1] =temp;
                }
                else if (c-1>=0 && board2[r][c-1] ==-1) // ซ้าย
                {
                    labels2[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic2/" + board2[r][c] + ".png";
                    labels2[r][c-1].setIcon(new ImageIcon(fileName));
                    int temp2 = board2[r][c];
                    board2[r][c] = board2[r][c-1];
                    board2[r][c-1] =temp2;
                }
            }
            flag2 = isWin2();
            if (flag2 == true)
            {
                displaywin2();

            }
        }

    }


}
