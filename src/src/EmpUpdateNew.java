package src;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class NoRecordFoundExeption1 extends Exception
{
	String errormsg;
	public NoRecordFoundExeption1(String a)
	{
		errormsg=a;
	}
	
	public String toString()
	{
		return(errormsg);
	}
}


public class EmpUpdateNew extends JFrame implements ActionListener
{
	public Choice t1;
	public JLabel l1,lname,ladd,lcity,lcont,ldesg,lhead,lsec,lans;
	public JTextField t2,t3,t4,t5,t6;
	public JComboBox city;
	public JButton b1,b2,b3;
	public JPanel p1,p2;
	public JTextArea a1;

public String query;
public Connection con;
public PreparedStatement ps;
public Statement st;
public ResultSet rs;
public ResultSet rsf;

public EmpUpdateNew()
{

t1=new Choice();
t2=new JTextField();
t3=new JTextField();
t4=new JTextField();
t5=new JTextField();
t6=new JTextField();
//t1.setEditable(false);
t2.setEditable(false);
t3.setEditable(false);
t4.setEditable(false);
t5.setEditable(false);
t6.setEditable(false);



		l1=new JLabel("Emp No");
		lname=new JLabel("Employee Name");
		ladd=new JLabel("Address");
		lcity=new JLabel("City");
		lcont=new JLabel("Contact Number");
		ldesg=new JLabel("Designation");
		lsec=new JLabel("Secret Question");
		lans=new JLabel("Answer");
		lhead=new JLabel("Update Employee Information",SwingConstants.CENTER);
		a1=new JTextArea("");
		a1.setEditable(false);
		String a[]={"Kabul","Mazar-i-Sharif","Jalalabad","Herat","Kandahar","Kunduz","Ghazni","Khost","Pol-e-Khomri","Balkh","Farah","Lashkar Gah","Bamyan","Sheberghan","Taleqan","Maymana"};
		city=new JComboBox(a);
		city.setEditable(false);
		l1=new JLabel("Emp No");
		
p1=new JPanel();
p2=new JPanel();

b1=new JButton("Fetch");
b2=new JButton("Update");
b3=new JButton("Save");
setLayout(new BorderLayout());
p1.setLayout(new GridLayout(8,2));
p2.setLayout(new GridLayout(1,3));
		p1.add(l1);
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

		p2.add(b1);
		p2.add(b2);
		p2.add(b3);


add(p1,"Center");
add(p2,"South");
add(lhead,"North");
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
fetcheno();
setVisible(true);
setSize(400,300);
setTitle("Employee Update");
setResizable(false);
setLocation(200,200);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

public void fetcheno()
{
			try
			{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","0000");
		st=con.createStatement();
		query="select eno from emp";
		rsf=st.executeQuery(query);
					
					while(rsf.next())
					{
					t1.add(rsf.getString(1));
					}
	
			}
			catch(Exception e){}
			
}
public void actionPerformed(ActionEvent ae)
{

String a,b,c,d,e,f,g,h;

	String msg=ae.getActionCommand();
	
	if(msg.equalsIgnoreCase("Fetch"))
		{
		
		a=t1.getItem(t1.getSelectedIndex());
					
			try{
			query="select * from emp where eno = ?";
			ps=con.prepareStatement(query);

			ps.setString(1,a);
		
		rs=ps.executeQuery();
		
			if(rs.next())
			{
		//JOptionPane.showMessageDialog(this,"Record Found","Employee Form",JOptionPane.INFORMATION_MESSAGE);
		b=rs.getString(2);
		c=rs.getString(3);
		d=rs.getString(4);
		e=rs.getString(5);
		f=rs.getString(6);
		g=rs.getString(7);
		h=rs.getString(8);
		t2.setText(b);
		a1.setText(c);
		t3.setText(e);
		t4.setText(f);
		t5.setText(g);
		t6.setText(h);
			}
			else
			{	
			try
			{
			throw new NoRecordFoundExeption1("Record Not Found");
			}
			catch(NoRecordFoundExeption1 nrf)
			{
			JOptionPane.showMessageDialog(this,nrf,"Employee Form",JOptionPane.INFORMATION_MESSAGE);
			t2.setText("");
			t3.setText("");
		}	}
		ps.close();
		
		}
		catch(Exception e1)
		{
		System.out.println(e1);
		}
	
	
	
	try{
	//con.close();
	}catch(Exception e2){}
	}
	
	if(msg.equalsIgnoreCase("Save"))
		{
		a=t1.getItem(t1.getSelectedIndex());
		b=t2.getText();
		c=a1.getText();
		d=city.getItemAt(city.getSelectedIndex()).toString();
		e=t3.getText();
		f=t4.getText();
		g=t5.getText();
		h=t6.getText();
			if(b.equals("") || c.equals("") || e.equals("") ||f.equals("") ||g.equals("") ||h.equals(""))
			{
				JOptionPane.showMessageDialog(this,"TextBox Cannot be empty","Employee Form",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
			try{
		
	query="update emp set ename=?,address=?,city=?,contact=?,desg=?,secretq=?,ans=? where eno=?";
	ps=con.prepareStatement(query);
	
	ps.setString(1,b);
	ps.setString(2,c);
	ps.setString(3,d);
	ps.setString(4,e);
	ps.setString(5,f);
	ps.setString(6,g);
	ps.setString(7,h);
	ps.setString(8,a);
	
		
	int n=ps.executeUpdate();
	
	if(n==1)
	JOptionPane.showMessageDialog(this,"Record Updated Successfully","Employee Form",JOptionPane.INFORMATION_MESSAGE);
	else
	JOptionPane.showMessageDialog(this,"Error in Updation","Employee Form",JOptionPane.ERROR_MESSAGE);
	}catch(Exception e3){}
}
}	


if(msg.equalsIgnoreCase("Update"))
		{
//t1.setEditable(true);
t2.setEditable(true);
t3.setEditable(true);
t4.setEditable(true);
t5.setEditable(true);
t6.setEditable(true);
a1.setEditable(true);
city.setEditable(true);
		}
}


public static void main(String args[])
{
new EmpUpdateNew();
}
}