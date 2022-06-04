import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class BackgroundImageJFrame extends JFrame
{
    JButton b1;
    JLabel l1;

    public BackgroundImageJFrame()
    {
        setTitle("Welcome");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        /*
        One way
        -----------------
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\Computer\\Downloads\\colorful design.png"));
        add(background);
        background.setLayout(new FlowLayout());
        l1=new JLabel("Here is a button");
        b1=new JButton("I am a button");
        background.add(l1);
        background.add(b1);
        */

        // Another way
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Images/bg.jpg")));
        setLayout(new FlowLayout());
        JLabel background = new JLabel(new ImageIcon("xyz.png"));
        background.setLayout(new BorderLayout());
        background.setLocation(300,300);
        add(background);
        // Just for refresh :) Not optional!
        setSize(399,399);
        setSize(900,600);


        setLayout(new FlowLayout());
        
        l1=new JLabel("Here is a button");
        b1=new JButton("I am a button");
        l1.setBounds(100, 100, 690, 768);
        b1.setBounds(300, 300, 690, 768);
        add(l1);
        add(b1);
       
        // Just for refresh :) Not optional!
        setSize(599,599);
        setSize(900,600);
    }

    public static void main(String args[])
    {
        new BackgroundImageJFrame();
    }
} 