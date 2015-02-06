import java.net.InetAddress;


public class lan {
public lan() throws Exception{
	InetAddress localhost=InetAddress.getLocalHost();
	byte[] host=localhost.getAddress();
	//System.out.println(host);
	new ping();
	int timeout=6000;
	   for (int i=1;i<255;i++){
	       host[3]=(byte)i;
	       new ping(host,timeout);
	   }
	}
	
}
