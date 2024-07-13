package NumPuzzle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/* นาย สุรกานต์ มะจันทร์ รหัสนิสิต 6430301010 */
public class BoardGui implements ActionListener
{
    JFrame frame;
    JPanel mainPanel;
    JButton[][] button;
    int rows;
    int cols;
    JLabel [][] labels;
    int [][] board;

    public BoardGui()
    {
        rows= 4;
        cols = 4;
        board = new int[rows][cols];
        initGui();
    }

    public void initGui()
    {
        //สร้าง หน้าต่าง โปรแกรม
        frame = new JFrame("4X4 Puzzle Game");
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridLayout(4,4));
        button = new JButton[rows][cols];
        labels = new JLabel[rows][cols];
        this.shuffleBoard();
        for (int i =0; i<rows; i++)
             {
                 for (int j =0; j<cols; j++)
                 {
                     button[i][j] = new JButton();
                     String text = i+","+j;

                     button[i][j].setText(text);
                     button[i][j].setFont(new Font("TimesRoman",Font.PLAIN,0));
                     button[i][j].addActionListener(this);
                     String fileName;
                     int val = board[i][j];
                     if (val != -1)
                     {
                         fileName = "src/pic/" + val + ".png";
                         labels[i][j] = new JLabel(new ImageIcon(fileName),JLabel.CENTER);
                     }
                     else
                     {
                         labels[i][j] = new JLabel();
                     }
                     button[i][j].add(labels[i][j]);
                     button[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
                     button[i][j].setBackground(new Color(255,126,0));
                     mainPanel.add(button[i][j]); //เพิ่มปุ่มไว้ใน panel
                 }
             }

        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void shuffleBoard()
    {
        Random random = new Random();
        int [] array = new int[16];
        for (int i = 0; i<16; i++)
        {
            array[i] = i + 1; //เป็นการ เซตผลลัพธ์ คือ -1 และ shuffleBoard เป็น array
        }
        array[15] = -1;
        for (int i =0; i< 16;i++)
        {
            int index = random.nextInt(16);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        int count = 0;
        for (int  i =0; i<rows;i++)
        {
            for (int j =0; j<cols; j++)
            {
                board[i][j] = array[count];
                count = count + 1;
                System.out.print(board[i][j] + "\t");
                }
            System.out.println("");
        }
    }
    Boolean isWin()
    {
        int count = 1;
        for (int i = 0;i<rows; i++)
        {
            for (int j =0; j<cols; j++)
            {
                if(board[i][j] != count && board[i][j] != -1)
                {
                    return false;
                }
                count+=1;
            }
        }
        return true;
    }

    public void displaywin()
    {
        JFrame frame1 = new JFrame("Game Win");
        JLabel label = new JLabel("You Solve The Puzzle",JLabel.CENTER);
        label.setFont(new Font("TimesRoman",Font.BOLD,20));
        frame1.add(label);
        frame1.setLayout(new GridLayout(1,1));
        frame1.setSize(300,300);
        frame1.setBackground(Color.WHITE);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean flag = isWin();
        if (flag == flag)
        {
            String s = e.getActionCommand().toString();
            int r = Integer.parseInt(s.split(",")[0]);
            int c = Integer.parseInt(s.split(",")[1]);
            if (board[r][c] != -1)
            {
                if (r+1<rows && board[r+1][c] == -1) //ขึ้น
                {
                    labels[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic/" + board[r][c] + ".png";
                    labels[r+1][c].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                    board[r][c] = board[r+1][c];
                    board[r+1][c] =temp;

                }
                else if (r-1>=0 && board[r-1][c] ==-1) // ลง
                {
                    labels[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic/" + board[r][c] + ".png";
                    labels[r-1][c].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                    board[r][c] = board[r-1][c];
                    board[r-1][c] =temp;
                }
                else if (c+1<cols && board[r][c+1] ==-1) // ขวา
                {
                    labels[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic/" + board[r][c] + ".png";
                    labels[r][c+1].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                    board[r][c] = board[r][c+1];
                    board[r][c+1] =temp;
                }
                else if (c-1>=0 && board[r][c-1] ==-1) // ซ้าย
                {
                    labels[r][c].setIcon(new ImageIcon(""));
                    String fileName = "src/pic/" + board[r][c] + ".png";
                    labels[r][c-1].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                    board[r][c] = board[r][c-1];
                    board[r][c-1] =temp;
                }
            }
            flag = isWin();
            if (flag == true)
            {
                displaywin();
                
            }
        }

    }


}
