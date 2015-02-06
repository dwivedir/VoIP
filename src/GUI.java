import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI extends JFrame implements ActionListener{
     final JTextField text;
     final JList<String> table;
     final JButton b;
	 client myclient;
	 server myserver;
	 final lan mylan; 
	public GUI(server myserver, final lan mylan,client myclient) throws Exception{
		this.myclient=myclient;
		this.myserver=myserver;
		this.mylan=mylan;
		JPanel panel=new JPanel();
		//this.setSize(500, 500);
		BorderLayout layout=new BorderLayout();
		setLayout(layout);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    b=new JButton();
	    b.setText("connect");
	    b.setActionCommand("connect");
	    b.setPreferredSize(new Dimension(20,20));
	    add(b,BorderLayout.SOUTH);
	    b.addActionListener(this);
	    text=new JTextField("127.0.0.1",20);
	    table=new JList<String>(ping.list1);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JScrollPane scroll=new JScrollPane(table);
	    scroll.setPreferredSize(new Dimension(200,400));
	    table.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				//System.out.println("inside");
				int x=table.getSelectedIndex();
				if(x>0){
					text.setText(ping.list2[x]);
				}
				
			}
	    	
	    });
	    panel.add(text);
	    panel.add(scroll);
	    add(panel);
	    pack();
	    setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String name=ae.getActionCommand();
		if(name.equals("connect")){
			myclient.host=text.getText();
			b.setActionCommand("disconnect");
			b.setText("disconnect");
		}
		
		else if(name.equals("disconnect")){
			myclient.host=null;
			b.setActionCommand("connect");
			b.setText("connect");
		}
			
	}
	
}
