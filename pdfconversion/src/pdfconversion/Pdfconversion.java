/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfconversion;

import com.lowagie.text.DocumentException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author NIHAL SINGH
 */
public class Pdfconversion implements ActionListener {

    JFrame frame;
    int height,width;
    JPanel panel;
    JScrollPane scroll;
    JButton chooseFile,merge;
    FlowLayout flowLayout;
    String filename,storepath;
    String[] str;
    
    void findDeskTopSize()
    {
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        height=(int)dimension.getHeight();
        width=(int)dimension.getWidth();
    }
    
    void createFrame()
    {
        frame=new JFrame("PDF Conversion");
        frame.setSize(width/2+100, height/2+100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLocationRelativeTo(null);
        flowLayout=new FlowLayout(FlowLayout.CENTER);
        flowLayout.setHgap(100);
        flowLayout.setVgap(50);
        frame.setLayout(flowLayout);
    }
    
    void createPanel()
    {
        panel=new JPanel();
        panel.setBounds(0,0,width/2-100, 400);
        panel.setBackground(Color.DARK_GRAY);
    }
    
    void addItemToPanel()
    {
            str=filename.split("/nn");
            for(int i=0;i<str.length;i++)
            {
                JLabel l=new JLabel(new ImageIcon("C:\\Users\\yash ele\\Desktop\\NetBeansProjects\\pdfconversion\\PDF.jpg"));
                l.setText(str[i]);
                l.setForeground(Color.white);
                l.setHorizontalTextPosition(JLabel.CENTER);
                l.setVerticalTextPosition(JLabel.BOTTOM);
                panel.add(l);    
            }
           
    }
    void addScrollViewToPanel()
    {
        scroll=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(width/2-20,height/2-100));
        frame.add(scroll);
        
    }
    
    void addButton()
    {
        chooseFile=new JButton("Choose File");
        chooseFile.setPreferredSize(new Dimension(120,40));
        chooseFile.setBackground(Color.GREEN);
        chooseFile.setForeground(Color.WHITE);
        
        merge=new JButton("Merge");
        merge.setPreferredSize(new Dimension(120,40));
        merge.setBackground(Color.RED);
        merge.setForeground(Color.WHITE);
        
        chooseFile.addActionListener(this);
        merge.addActionListener(this);
        frame.add(chooseFile);
        frame.add(merge);
    }
    Pdfconversion(String filename,String storepath)
    {
        this.filename=filename;this.storepath=storepath;
        findDeskTopSize();
        createFrame();
        addButton();
        createPanel();
        addItemToPanel();
        addScrollViewToPanel();
        //frame.add(panel);
        frame.setVisible(true);
    }
    
   
    
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==chooseFile)
        {
            addItemToPanel();
        }else if(ae.getSource()==merge)
        {
            try {
                MergFile.Merge(filename.split("/nn"), storepath);
                JOptionPane.showMessageDialog(null, "Successfully Mrge");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
