package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class HomePageNew extends JFrame implements ActionListener
{
	public JMenuBar mymenubar;
	public JMenu file,fclo;
	public JMenuItem addemp,updemp,empdet,empexit;
	public JLabel mylab,l1;
	ImageIcon ic[];
	static int c;
	
	public HomePageNew()
	{
	mylab=new JLabel("");
	mylab.setBounds(50,50,100,50);
	add(mylab);
	
	mymenubar=new JMenuBar();
	
	file=new JMenu("EMPLOYEE");
	fclo=new JMenu("EXIT");
	
	addemp=new JMenuItem("ADD EMPLOYEE DETAILS");
	updemp=new JMenuItem("UPDATE EMPLOYEE DETAILS");
	empdet=new JMenuItem("VIEW LIST OF EMPLOYEES");
	empexit=new JMenuItem("LOGOUT");
	
	file.add(addemp);
	file.add(updemp);
	file.add(empdet);
	
	fclo.add(empexit);
	
	mymenubar.add(file);
	mymenubar.add(fclo);
	
	addemp.addActionListener(this);
	updemp.addActionListener(this);
	empdet.addActionListener(this);
	empexit.addActionListener(this);
	
	showSlider();
	
	
	setJMenuBar(mymenubar);
	setSize(800,600);
	setTitle("Momo");
	setVisible(true);
	setResizable(false);
	setLocation(100,100);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void showSlider()
	{
	//setLayout(null);
	l1=new JLabel();
	l1.setBounds(10,10,400,300);
	add(l1);

	String list[] = {"D:/tybca2020/sem5/corejava/awtprogs/desert.jpg", "D:/tybca2020/sem5/corejava/awtprogs/jellyfish.jpg" , "D:/tybca2020/sem5/corejava/awtprogs/tulips.jpg"};
	ic=new ImageIcon[3];
	try
	{
		for(int i = 0;i<3;i++)
		{
		BufferedImage img = ImageIO.read(new File(list[i]));
		Image dimg = img.getScaledInstance(l1.getWidth(), l1.getHeight(),
		Image.SCALE_SMOOTH);
		ic[i]= new ImageIcon(dimg);
		}
	}catch(Exception ew){}

	l1.setIcon(ic[0]);
	addWindowListener(new WindowAdapter(){
	
	public void windowOpened(WindowEvent we) 
	{
       Timer t  ;	//interval, code
       t=new Timer(3000,new ActionListener(){
		
	
	public void actionPerformed(ActionEvent e) 
		{
			if(c<3)
			{
			l1.setIcon(ic[c]);
			c++;
			}
		}
	});
	
	
	t.start();
    }
	
});
}

	public void actionPerformed(ActionEvent ae)
	{
		Object ob=ae.getSource();
		if(ob==addemp)
		{
		new RegistrationNew();
		}
		
		if(ob==updemp)
		{
		new EmpUpdateNew();
		}
		
		if(ob==empdet)
		{
		new EmpList();
		}
		
		if(ob==empexit)
		{
		System.exit(0);
		}
	
	}
	
	public static void main(String args[])  
	{  
	new HomePageNew();  
	}  
	
}