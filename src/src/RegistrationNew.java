package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class RegistrationNew extends JFrame implements ActionListener,FocusListener//,ItemListener
{
	public JLabel lid,lname,ladd,lcity,lcont,ldesg,lhead,lsec,lans,lpas,cpas;
	public JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	public JTextArea a1;
	public JButton b1,b2;
	public JPanel p1;
	public JComboBox city;
	public String query;
	public Connection con;
	public PreparedStatement ps;
	
	public RegistrationNew()
	{
		lid=new JLabel("Employee ID");
		lname=new JLabel("Employee Name");
		ladd=new JLabel("Address");
		lcity=new JLabel("City");
		lcont=new JLabel("Contact Number");
		ldesg=new JLabel("Designation");
		lsec=new JLabel("Secret Question");
		lans=new JLabel("Answer");
		lpas=new JLabel("Password");
		cpas=new JLabel("Confirm Password");
		
		lhead=new JLabel("Employee Information",SwingConstants.CENTER);
		
		
		
		t1=new JTextField("");
		t2=new JTextField("");
		a1=new JTextArea("");
		String a[]={"Kabul","Mazar-i-Sharif","Jalalabad","Herat","Kandahar","Kunduz","Ghazni","Khost","Pol-e-Khomri","Balkh","Farah","Lashkar Gah","Bamyan","Sheberghan","Taleqan","Maymana"};
		city=new JComboBox(a);
		t3=new JTextField("");
		t4=new JTextField("");
		t5=new JTextField("");
		t6=new JTextField("");
		t7=new JTextField("");
		t8=new JTextField("");
		
		t8.addFocusListener(this);
		
		b1=new JButton("Submit");
		b2=new JButton("Reset");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		p1=new JPanel();
		setLayout(new BorderLayout());
		p1.setLayout(new GridLayout(11,2));
		p1.add(lid);
		p1.add(t1);
		p1.add(lname);
		p1.add(t2);
		p1.add(ladd);
		p1.add(a1);
		
		p1.add(lcity);
		p1.add(city);
		
		p1.add(lcont);
		p1.add(t3);
		p1.add(ldesg);
		p1.add(t4);
		p1.add(lsec);
		p1.add(t5);
		p1.add(lans);
		p1.add(t6);
		p1.add(lpas);
		p1.add(t7);
		p1.add(cpas);
		p1.add(t8);
		p1.add(b1);
		p1.add(b2);
		
		add(p1,"Center");
		add(lhead,"North");
	
		setSize(500,400);
		setTitle("Employee RegistrationNew");
		setVisible(true);
		setResizable(false);
		setLocation(200,200);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent ae)
{

String a,b,c,d,e,f,g,h,i;

String msg=ae.getActionCommand();
	if(msg.equalsIgnoreCase("Submit"))
		{
		
		a=t1.getText();
		b=t2.getText();
		c=a1.getText();
		d=city.getItemAt(city.getSelectedIndex()).toString();
		e=t3.getText();
		f=t4.getText();
		g=t5.getText();
		h=t6.getText();
		i=t7.getText();

		try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","0000");
		query="insert into emp values(?,?,?,?,?,?,?,?,?)";
		ps=con.prepareStatement(query);

		ps.setString(1,a);
		ps.setString(2,b);
		ps.setString(3,c);
		ps.setString(4,d);
		ps.setString(5,e);
		ps.setString(6,f);
		ps.setString(7,g);
		ps.setString(8,h);
		ps.setString(9,i);
		

		int n=ps.executeUpdate();
		if(n==1)
		JOptionPane.showMessageDialog(this,"Record Inserted Successfully","Employee Form",JOptionPane.INFORMATION_MESSAGE);
		else
		JOptionPane.showMessageDialog(this,"Error in Insertion","Employee Form",JOptionPane.ERROR_MESSAGE);

		ps.close();
		con.close();
		}
		catch(Exception e1)
		{
		System.out.println(e1);
		}
	}
}

public void focusLost(FocusEvent e)
	{
		if(e.getSource()==t8)
		{
			if(!(t7.getText().equals(t8.getText())))
			{
			JOptionPane.showMessageDialog(this,"Password Not Matching","Employee Form",JOptionPane.ERROR_MESSAGE);			
			t8.setText("");
			t8.requestFocus();
			}
		}
		
	}
public void focusGained(FocusEvent e){}
	
	public static void main(String args[])
	{
		new RegistrationNew();
	}	
}