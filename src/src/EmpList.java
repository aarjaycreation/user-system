package src;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class EmpList extends JFrame implements ActionListener
{
public JPanel jpan;
public JScrollPane jsp;
public JTable jt;
public DefaultTableModel dtm;

JButton filterrec,closebtn;

String query;
Connection con;
Statement st;
ResultSet rs,rs1;

String cols[] = {"Eno", "Ename", "Address",  "City",  "Contact", "Designation",};
String rows[][] = new String [11][6];
String rows1[][] = new String [11][6];
JLabel lhead;

EmpList()
{
	lhead=new JLabel("List Of Employees",SwingConstants.CENTER);
	lhead.setBounds(200,5,200,20);
	add(lhead);
jpan = new JPanel();
jpan.setLayout(null);

	try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","0000");
		
		st=con.createStatement();
		query="select * from emp";
		

		rs=st.executeQuery(query);
		int i=0;
		while(rs.next())
		{
		rows[i][0]=rs.getString(1);
		rows[i][1]=rs.getString(2);
		rows[i][2]=rs.getString(3);
		rows[i][3]=rs.getString(4);
		rows[i][4]=rs.getString(5);
		rows[i][5]=rs.getString(6);
		i++;
		}

		}
		catch(Exception e){}

		dtm  = new DefaultTableModel (rows, cols);
		
		jt = new JTable (dtm);
		
		(jt.getColumnModel().getColumn(0)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(1)).setPreferredWidth (370);
		(jt.getColumnModel().getColumn(2)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(3)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(4)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(5)).setPreferredWidth (300);
		jt.setRowHeight (20);
		
		jt.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
		
		jsp = new JScrollPane (jt);
		jsp.setBounds (20, 40, 700, 300);
		
		filterrec=new JButton("FILTER");
		filterrec.setBounds (95, 230, 100, 40);
		filterrec.addActionListener(this);
		closebtn=new JButton("CLOSE");
		closebtn.setBounds (295, 350, 100, 40);
		closebtn.addActionListener(this);
		
		
		//jpan.add (filterrec);
		jpan.add (closebtn);
		jpan.add (jsp);
		add(jpan);

		setVisible(true);
		setSize(800,430);
		setTitle("List Of Employees");
		setResizable(false);
		setLocation(200,200);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

		public void actionPerformed(ActionEvent e)
		{
			
			
			if(e.getSource()==filterrec)
			{
				dtm.setRowCount(0);
				dtm.setRowCount(10);
				
				//dtm.getDataVector().removeAllElements();
				
				try
				{
				st=con.createStatement();
				query="select * from emp where sal >=1100";
		
				
				rs1=st.executeQuery(query);
				int i=0;
					while(rs1.next())
					{
					rows1[i][0]=rs1.getString(1);
					rows1[i][1]=rs1.getString(2);
					rows1[i][2]=rs1.getString(3);
					i++;
					}

			}
		catch(Exception e1){}
		
		dtm  = new DefaultTableModel (rows1, cols);
		
		jt = new JTable (dtm);
		
		(jt.getColumnModel().getColumn(0)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(1)).setPreferredWidth (370);
		(jt.getColumnModel().getColumn(2)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(3)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(4)).setPreferredWidth (300);
		(jt.getColumnModel().getColumn(5)).setPreferredWidth (300);
		jt.setRowHeight (20);
		
		jt.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
		
		jsp = new JScrollPane (jt);
		jsp.setBounds (20, 40, 700, 300);
		
		jpan.add (jsp);
		add(jpan);
		
		}
			
			if(e.getSource()==closebtn)
			{
			this.dispose(); 
			}
			
		}  


public static void main(String args[])
{
new EmpList();
}
}