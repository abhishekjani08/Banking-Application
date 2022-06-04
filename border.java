import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class border {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    border frame = new border();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void setVisible(boolean b) {
    }

    public border() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setBounds(300, 90, 900, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new BorderLayout());
            try {

                JLabel background = new JLabel(new ImageIcon("xyz.png"));
                background.setLayout(new BorderLayout());
                background.setBounds(100, 100, 690, 268);
                JLabel background2 = new JLabel(new ImageIcon("Images/bg.jpg"));
                background2.setLayout(new BorderLayout());
                JLabel text = new JLabel("Hi Abhishek");
                text.setFont(text.getFont().deriveFont(128f));
                text.setLocation(200, 800);
                text.setForeground(Color.BLACK);
                JButton b1 = new JButton("REGISTER HERE");
                b1.setForeground(Color.BLACK);
                b1.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 20));
                b1.setSize(200, 50);
                b1.setLocation(350, 400);

                b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        // creating one object
                        Collect gh = new Collect();
                        gh.setVisible(true);

                    }
                });
                b1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        b1.setBackground(Color.LIGHT_GRAY);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        b1.setBackground(UIManager.getColor("control"));
                    }
                });

                background2.add(background);
                add(background2);
               

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}