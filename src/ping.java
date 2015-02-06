import java.net.InetAddress;


public class ping extends Thread{
	byte[] host;
	Thread t;
	int timeout;
	static public String[] list1=new String[255];
	static  public String[] list2=new String[255];
	static int len=1;
public ping(){
	list1[0]="hub list:-";
	for(int i=1;i<254;i++)
		list1[i]="...............";
}
 public ping(byte[] host,int timeout){
	 this.host=host;
	 this.timeout=timeout;
	 t=new Thread(this);
	 t.start();
 }
 public void run(){
	 InetAddress ip;
	try {
		ip = InetAddress.getByAddress(host);
	   
     if (ip.isReachable(timeout)){
         System.out.println(ip.getHostName()+"  "+ip.getHostAddress());
         list1[len]=ip.getHostName()+"  "+ip.getHostAddress();
         list2[len++]=ip.getHostAddress();
     }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}
