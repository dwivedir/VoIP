import java.net.*;
import javax.sound.sampled.*;

public class client extends Thread{
	 Thread t;
	 String host=null;
    public final static AudioFormat audioFormat = new AudioFormat(34100, 8, 1, true, true);
    private static String INPUT = "Microphone";
    private static TargetDataLine PROCESSING_BUFFER;
    public client() throws Exception {
        t=new Thread(this);
        t.start();
    }
    
    public void run() {
    	final Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        for (final Mixer.Info info : mixerInfos) {
            final Mixer mixer = AudioSystem.getMixer(info);
            System.out.println(info.getName());
            final Line.Info[] targetLineInfos = mixer.getTargetLineInfo();
            for (final Line.Info targetLineInfo : targetLineInfos) {
                if (targetLineInfo.getLineClass() == javax.sound.sampled.TargetDataLine.class
                        && info.getName().startsWith(client.INPUT)) {
                    try {
                        client.PROCESSING_BUFFER = (TargetDataLine) mixer.getLine(targetLineInfo);
                        System.out.println("hell01 "+targetLineInfo.getLineClass() + ": " + info.getName() + " ["
                                + client.PROCESSING_BUFFER + "]");
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }
        }
        byte[] sendData = new byte[1024];
        DatagramSocket clientSocket;
        InetAddress IPAddress;
		try {
			clientSocket = new DatagramSocket();
			PROCESSING_BUFFER.open(client.audioFormat);
			PROCESSING_BUFFER.start();
		do{
			
        while(host!=null){
        	
			IPAddress = InetAddress.getByName(host);
			//System.out.print(IPAddress);
        	PROCESSING_BUFFER.read(sendData,0, 1024);
        	DatagramPacket sendPacket =
        			new DatagramPacket(sendData, sendData.length, IPAddress,
        			9876);
				clientSocket.send(sendPacket);
        }
        }while(host==null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PROCESSING_BUFFER.stop();
        PROCESSING_BUFFER.close();
       // System.out.println("khcfvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
		
    
	}
}