
public class Main {
	static server myserver;
	static lan mylan;
	static client myclient;
	public static void main(String[] args) throws Exception{
		myclient =new client();
		myserver=new server();
		mylan=new lan();
		Thread.sleep(10000);
		new GUI(myserver,mylan,myclient);
	}
}
