/**
 * @Author: Nahin
 * Time: 4:00 am; June 15, 2025
 * @version 1.0
 * Purpose: GUI-based calculator with exception handling mechanism.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimpleCalculator implements ActionListener {
    JFrame frame;
    Container c;
    JLabel l1;
    JTextField resTF;
    JButton clr, back, dot, eql;
    JPanel p1, p2;

    JButton[] n_bt = new JButton[12];
    JButton[] a_bt = new JButton[4];

    SimpleCalculator() {
        frame = new JFrame();
        frame.setBounds(800, 300, 350, 550);
        c = frame.getContentPane();
        c.setBackground(Color.decode("#8294C4"));
        c.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Simple Calculator");
        l1.setBounds(220, 10, 100, 50);
        c.add(l1);

        resTF = new JFormattedTextField();
        resTF.setBounds(10, 50, 310, 40);
        resTF.setBackground(Color.decode("#8294C4"));
        c.add(resTF);

        clr = new JButton("Clear");
        clr.setBounds(10, 110, 80, 25);
        clr.addActionListener(this);
        clr.setBackground(Color.decode("#9FB3DF"));
        c.add(clr);

        back = new JButton("->Back");
        back.setBounds(240, 110, 80, 25);
        back.addActionListener(this);
        back.setBackground(Color.decode("#9FB3DF"));
        c.add(back);

        p1 = new JPanel();
        p1.setBounds(10, 155, 230, 270);
        p1.setLayout(new GridLayout(4, 3, 20, 20));
        p1.setBackground(Color.decode("#8294C4"));
        c.add(p1);

        for (int i = 0; i < n_bt.length; i++) {
            n_bt[i] = new JButton();
            if (i < 9) {
                n_bt[i].setText(Integer.toString(i + 1));
            }
            n_bt[i].addActionListener(this);
            n_bt[i].setBackground(Color.decode("#9FB3DF"));
            n_bt[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
            p1.add(n_bt[i]);

        }
        n_bt[9].setText("0");
        n_bt[10].setText("mod");
        n_bt[11].setText("sqrt");

        p2 = new JPanel();
        p2.setBounds(260, 155, 60, 270);
        p2.setLayout(new GridLayout(4, 1, 20, 20));
        p2.setBackground(Color.decode("#8294C4"));
        c.add(p2);

        for (int i = 0; i < a_bt.length; i++) {
            a_bt[i] = new JButton();
            p2.add(a_bt[i]);
            a_bt[i].setBackground(Color.decode("#9FB3DF"));
            a_bt[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
            a_bt[i].addActionListener(this);
        }
        a_bt[0].setText("+");
        a_bt[1].setText("-");
        a_bt[2].setText("X");
        a_bt[3].setText("/");

        dot = new JButton(".");
        dot.setBounds(10, 450, 65, 50);
        dot.setBackground(Color.decode("#9FB3DF"));
        dot.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        dot.addActionListener(this);
        c.add(dot);


        eql = new JButton("=");
        eql.setBounds(95, 450, 225, 50);
        eql.setBackground(Color.decode("#9FB3DF"));
        eql.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        eql.addActionListener(this);
        c.add(eql);


        frame.setVisible(true);
    }

    double num1, num2, result;
    String op;

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == n_bt[i])
                resTF.setText(resTF.getText() + (i + 1));
        }
        if (e.getSource() == n_bt[9])
            resTF.setText(resTF.getText() + 0);
        if (e.getSource() == dot) {
            String currentText = resTF.getText();
            if (!currentText.contains(".")) {
                resTF.setText(currentText + ".");
            }
        }
        if (e.getSource() == clr)
            resTF.setText(null);
        if (e.getSource() == back) {
            String len = resTF.getText();
            if (len.length() > 0) {
                resTF.setText(len.substring(0, len.length() - 1));
            }
        }
        for (int i = 0; i < a_bt.length; i++) {
            if (e.getSource() == a_bt[i]) {
                num1 = Double.parseDouble(resTF.getText());
                op = a_bt[i].getText();
                resTF.setText(null);
            }
        }
        if (e.getSource() == n_bt[10]) {
            num1 = Double.parseDouble(resTF.getText());
            op = n_bt[10].getText();
            resTF.setText(null);
        }
        if (e.getSource() == n_bt[11])
        {
            num1=Double.parseDouble(resTF.getText());
            try
            {
                if (num1<0)
                    throw new ArithmeticException("Cant square negative number");
                else
                    result=Math.sqrt(num1);
                resTF.setText(String.valueOf(result));
            }

            catch (ArithmeticException x)
            {
                JOptionPane.showMessageDialog(frame,x.getMessage(),"Dialog",JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == eql) {
            num2 = Double.parseDouble(resTF.getText());
            try {
                switch (op) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "X":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Cant divide by zero");
                        }
                        result= num1/num2;
                        break;
                    case "mod":
                        if (num2 == 0)
                        {
                            throw new ArithmeticException("Cant mod by zero");
                        }
                        result = num1 % num2;
                        break;

                }
                resTF.setText(String.valueOf(result));
            }
            catch (Exception exc) {
                JOptionPane.showMessageDialog(frame, exc.getMessage(),"Dialog",JOptionPane.ERROR_MESSAGE);
            }


        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
