/**
 * @Author: Nahin
 * Time: 3:30 am; May 30, 2025
 * @version 1.0
 * Purpose: A Java program to create a Temperature Converter Application with a Graphical User Interface (GUI).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TempConverterApp implements ActionListener {
    JFrame frame;
    Container c;
    JLabel l1, l2, from, to;
    JTextField tempTF, conTF;
    JButton convert,clear;
    JComboBox fromCb, toCb;
    JButton[] bt=new JButton[12];
    JPanel p;

    TempConverterApp()
    {
        frame= new JFrame("Temp Converter");
        frame.setBounds(800,300,420,550);
        c= frame.getContentPane();
        c.setBackground(Color.decode("#2b2d42"));
        c.setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 =new JLabel("Temperature");
        l1.setBounds(10,20,100,30);
        l1.setForeground(Color.white);
        c.add(l1);

        tempTF=new JFormattedTextField();
        tempTF.setBounds(10,50,200,35);
        tempTF.setBackground(Color.decode("#8d99ae"));
        c.add(tempTF);

        l2 = new JLabel("Converted Value");
        l2.setBounds(10,100,100,30);
        l2.setForeground(Color.white);
        c.add(l2);

        conTF=new JFormattedTextField();
        conTF.setBounds(10,130,200,35);
        conTF.setBackground(Color.decode("#2b2d42"));
        c.add(conTF);

        from =new JLabel("From");
        from.setBounds(215,20,200,35);
        from.setForeground(Color.white);
        c.add(from);

        to=new JLabel("To");
        to.setBounds(215,100,100,35);
        to.setForeground(Color.white);
        c.add(to);

        String[] unit={"Celsius", "Fahrenheit", "Kelvin"};

        fromCb = new JComboBox(unit);
        fromCb.setBounds(215,50,170,35);
        c.add(fromCb);

        toCb =new JComboBox(unit);
        toCb.setBounds(215,130,170,35);
        c.add(toCb);

        convert=new JButton("Convert");
        convert.setBounds(10,190,180,35);
        convert.setBackground(Color.decode("#8d99ae"));
        convert.addActionListener(this);
        c.add(convert);


        clear= new JButton("Clear");
        clear.setBounds(205,190,180,35);
        clear.setBackground(Color.decode("#8d99ae"));
        clear.addActionListener(this);
        c.add(clear);

        p=new JPanel();
        p.setBounds(50,250,300,200);
        p.setBackground(Color.decode("#8d99ae"));
        p.setLayout(new GridLayout(3,4,1,1));
        c.add(p);

        for(int i=0;i<bt.length;i++)
        {
            bt[i]=new JButton();
            bt[i].setBackground(Color.decode("#14213d"));
            bt[i].setForeground(Color.white);
            if (i<9)
            {
                bt[i].setText(Integer.toString(i+1));
            }
            bt[i].addActionListener(this);
            p.add(bt[i]);
        }
        bt[9].setText("0");
        bt[10].setText(".");
        bt[11].setText("+-");


        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==bt[0])
            tempTF.setText(tempTF.getText()+"1");
        else if (e.getSource()==bt[1])
            tempTF.setText(tempTF.getText()+"2");
        else if (e.getSource()==bt[2])
            tempTF.setText(tempTF.getText()+"3");
        else if (e.getSource()==bt[3])
            tempTF.setText(tempTF.getText()+"4");
        else if (e.getSource()==bt[4])
            tempTF.setText(tempTF.getText()+"5");
        else if (e.getSource()==bt[5])
            tempTF.setText(tempTF.getText()+"6");
        else if (e.getSource()==bt[6])
            tempTF.setText(tempTF.getText()+"7");
        else if (e.getSource()==bt[7])
            tempTF.setText(tempTF.getText()+"8");
        else if (e.getSource()==bt[8])
            tempTF.setText(tempTF.getText()+"9");
        else if(e.getSource()==bt[9])
            tempTF.setText(tempTF.getText()+"0");
        else if (e.getSource()==bt[10])
            tempTF.setText(tempTF.getText()+".");
        else if (e.getSource()==bt[11])
        {
            String c= tempTF.getText();
            double val=Double.parseDouble(c);
            val=val*(-1);
            tempTF.setText(Double.toString(val));
        }
        else if(e.getSource()==clear)
        {
            tempTF.setText(null);
            conTF.setText(null);
        }
        else if(e.getSource()==convert)
        {
            String g=tempTF.getText();
            try {
                double res = Double.parseDouble(g);
                if (fromCb.getSelectedIndex() == 0 && toCb.getSelectedIndex() == 1) {
                    res = (9 * res / 5) + 32;
                } else if (fromCb.getSelectedIndex() == 0 && toCb.getSelectedIndex() == 2) {
                    res = res + 273.15;
                } else if (fromCb.getSelectedIndex() == 1 && toCb.getSelectedIndex() == 0) {
                    res = (res - 32) * 5 / 9;
                } else if (fromCb.getSelectedIndex() == 1 && toCb.getSelectedIndex() == 2) {
                    res = ((res - 32) * 5 / 9 + 273.15);
                } else if (fromCb.getSelectedIndex() == 2 && toCb.getSelectedIndex() == 0) {
                    res = res - 273.15;
                } else if (fromCb.getSelectedIndex() == 2 && toCb.getSelectedIndex() == 1) {
                    res = (res - 273.15) * 9 / 5 + 32;
                }
                conTF.setForeground(Color.white);
                conTF.setText(Double.toString(res));
            }
            catch (NumberFormatException n){
                JOptionPane.showMessageDialog(frame,"Enter a Valid Number","invalid input",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
    public static void main(String[] args) {
        TempConverterApp c=new TempConverterApp();
    }

}