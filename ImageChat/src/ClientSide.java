
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class ClientSide implements ActionListener,Runnable
{  
    JLabel l1,l2;  
    JTextArea areas, areac;  
    JButton b;
    boolean flag=false;
    Socket s;
    StringBuilder sbr=new StringBuilder();
    
    public ClientSide() 
    {  
        JFrame f= new JFrame("Client");  
        l1=new JLabel();  
        //l1.setBounds(50,25,100,30);  
        l1.setText("CLIENT SIDE: ");  
        
        areas=new JTextArea();  
        //areas.setBounds(20,75,250,200);
        areac=new JTextArea();
        
        //areac.setBorder();
        //areac.setBounds();
        b=new JButton("SEND");  
        //b.setBounds(100,300,120,30);  
        b.addActionListener(this);  
        f.add(l1,BorderLayout.NORTH);
        f.add(areac,BorderLayout.CENTER);
        f.add(areas,BorderLayout.SOUTH);
        f.add(b,BorderLayout.EAST);  
        f.setSize(450,450);  
         try
        {
            s=new Socket("localhost",1234);
             System.out.println("connection set up c");
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
         Thread t=new Thread(this,"t1");
        t.start();
        f.setVisible(true);  
        
    }  
    public void run()
    {
        try{
            
            DataInputStream ip=new DataInputStream(s.getInputStream());
           
            String str="";
            while(!str.equalsIgnoreCase("bye"))
            {
                str=ip.readUTF();
                sbr.append("Server : "+str+"\n");
                areac.setText(sbr+"");
            }
            System.out.println("CLOSING CLIENT...");
            
           
            ip.close();
            s.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   
    public void actionPerformed(ActionEvent e)
    {  
        try
        {
            String text=areas.getText();  
              
           
            DataOutputStream op=new DataOutputStream(s.getOutputStream());
       
            
            String st=text;
            op.writeUTF(st);
        
            
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
    }  
    public static void main(String[] args)
    {  
        new ClientSide();
        
    }  
}  
