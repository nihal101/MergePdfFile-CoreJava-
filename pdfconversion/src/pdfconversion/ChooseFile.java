/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfconversion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author NIHAL SINGH
 */
public class ChooseFile implements ActionListener{
    JFrame frame;
    JLabel filename,storePath;
    JButton choosefile,proceed,choosestorepath;
    JScrollPane scroll;
    String filepath="",storepath="";
    String linebreak="<html><body>";
    String str="";
    
    
   void createFrame()
   {
       frame=new JFrame("ChooseFile");
       frame.setSize(700,500);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.getContentPane().setBackground(Color.LIGHT_GRAY);
       frame.setLayout(null);
   }
   
   
   void componentToFrame()
   {
       choosefile=new JButton("Choose File");
       choosefile.setBounds(100, 200, 100, 30);
       choosefile.setBackground(Color.cyan);
       
       proceed=new JButton("Proceed");
       proceed.setBounds(400, 200, 100, 30);
       proceed.setBackground(Color.cyan);
       
       choosestorepath=new JButton("Choose Location");
       choosestorepath.setBounds(100, 300,150, 30);
       choosestorepath.setBackground(Color.cyan);
       
       
       storePath=new JLabel("");
       storePath.setBounds(300, 300, 350, 30);
       
       choosefile.addActionListener(this);
       proceed.addActionListener(this);
       choosestorepath.addActionListener(this);
       frame.add(choosefile);
       frame.add(proceed);
       frame.add(choosestorepath);
       frame.add(storePath);
   }
   
   void addLabelToFrame()
   {
       filename=new JLabel();
       filename.setForeground(Color.black);
       scroll=new JScrollPane(filename);
       scroll.setBounds(50, 50, 600, 100);
       scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scroll.setBackground(Color.DARK_GRAY);
       frame.add(scroll);
   }
   ChooseFile()
   {
       createFrame();
       componentToFrame();
       addLabelToFrame();
       frame.setVisible(true);
   }
   
   boolean isSameFile()
   {
       int countT=0,countP=0;
       String[] str=filepath.split("/nn");
       String substring;
       boolean b=false;
       
       
       for(int i=0;i<str.length;i++)
       {
           substring=str[i].substring(str[i].length()-3);
           System.out.println(substring);
           if(substring.equalsIgnoreCase("txt"))
           {
               countT++;
           }else if(substring.equalsIgnoreCase("pdf"))
           {
               countP++;
           }
       }
       if(countT==0&&countP==0)
       {
           b=false;
       }else if((countT==0&&countP!=0)||(countT!=0&&countP==0))
       {
           b=true;
       }else if(countT!=0&&countP!=0)
       {
           b=false;
       }
       return b;
   }
    public static void main(String[] args) {
        new ChooseFile();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==choosefile)
        {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(frame);
            if (i == JFileChooser.APPROVE_OPTION)
            {
                File f = fc.getSelectedFile();
                filepath=filepath+f.getPath()+"/nn";
                str = linebreak+str+f.getPath()+"<br>"+linebreak; 
                filename.setText(str);
            }
        }
        else if(ae.getSource()==proceed)
        {
            if(storepath.equals("")||filepath.equals("")||(!isSameFile()))
            {
                JOptionPane.showMessageDialog(null, "<html><body>Please Enter MergeFile Store Location<br> OR <br> Select File To Be Sorted"
                        + "<br> OR <br> Selected Fie Must Be Same Either .PDF Or .TXT "
                        );
            }else
            {
                new Pdfconversion(filepath,storepath);
            }
        }else if(ae.getSource()==choosestorepath)
        {
            JFileChooser fc=new JFileChooser();
            int i=fc.showOpenDialog(frame);
            
            if(i==JFileChooser.APPROVE_OPTION)
            {
                File f=fc.getSelectedFile();
                storepath=f.getPath();
                storePath.setText(storepath);
            }
        }
    } 
}
