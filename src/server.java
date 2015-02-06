import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

class server extends Thread{
	static int NZEROS = 5;
	static int NPOLES = 5;
	static double GAIN = 1.095980088e+00;

	double xv[] = new double[NZEROS+1];
	double yv[] = new double[NPOLES+1];
	static Thread t;
	private static String OUTPUT = "Speakers";
	private static SourceDataLine RENDERING_BUFFER;
	public final static AudioFormat audioFormat = new AudioFormat(34100, 8, 1, true, true);
public server() throws Exception
{
	t=new Thread(this);
	t.start();
	
}
public void run() {
	setRenderingBuffer();
	try{
	RENDERING_BUFFER.open(server.audioFormat);
	RENDERING_BUFFER.start();
	DatagramSocket serverSocket = new DatagramSocket(9876);
	byte[] receiveData = new byte[1024];
	while(true){
		DatagramPacket receivePacket =new DatagramPacket(receiveData, 1024);
		serverSocket.receive(receivePacket);
		byte[] data=receivePacket.getData();
		/*for (int i=0; i <data.length; ++i)
		{
		xv[0] = xv[1];
		xv[1] = xv[2];
		xv[2] = data[i] /GAIN;
		yv[0] = yv[1];
		yv[1] = yv[2];
		yv[2] = (xv[0] + xv[2]) - 2 * xv[1] + ( -0.9479259375 *yv[0]) + ( 1.9469976496 * yv[1]);
		data[i] = (byte)yv[2];
		}*/
				RENDERING_BUFFER.write(data,0,receivePacket.getData().length);
				//System.out.println("sdjnsdj");
	}
	}catch(Exception e){
		e.printStackTrace();
	}
}
public  void setRenderingBuffer() {
	int p=1;
    final Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
    for (Mixer.Info info : mixerInfos) {
        final Mixer mixer = AudioSystem.getMixer(info);
        final Line.Info[] sourceLineInfos = mixer.getSourceLineInfo();
        for (final Line.Info sourceLineInfo : sourceLineInfos) {
            if (p==1&&sourceLineInfo.getLineClass() == javax.sound.sampled.SourceDataLine.class
                    && info.getName().startsWith(server.OUTPUT)) {
                try {
                    server.RENDERING_BUFFER = (SourceDataLine) mixer.getLine(sourceLineInfo);
//                    System.out.println("hell02 "+sourceLineInfo.getLineClass() + ": " + info.getName() + " ["
//                            + server.RENDERING_BUFFER + "]");
                    p=0;
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
}