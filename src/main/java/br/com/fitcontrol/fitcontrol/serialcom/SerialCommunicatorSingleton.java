package br.com.fitcontrol.fitcontrol.serialcom;

import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;

import java.util.Stack;

import com.fazecast.jSerialComm.SerialPort;

public class SerialCommunicatorSingleton extends Thread {

    private static SerialCommunicatorSingleton instance = new SerialCommunicatorSingleton();
    private static boolean FLAG_PORT_OPENNED = false;

    private SerialPort CurrentPort;

    private SerialCommunicatorSingleton()
    {

    }

    public static SerialCommunicatorSingleton getInstance()
    {
        return instance;
    }


    public void sendData(){

    }

    public SerialPort[] getAvailablePorts(){
        return SerialPort.getCommPorts();
    }

    public void connect(SerialPort port)
    {
        CurrentPort = port;
        CurrentPort.setComPortParameters(115200,8,0,0);
        CurrentPort.openPort();
        FLAG_PORT_OPENNED = true;
    }

    public void disconnect()
    {
        CurrentPort.closePort();
        FLAG_PORT_OPENNED = false;
    }

    @Override
    public void run() {

        while(true)
        {
            if(FLAG_PORT_OPENNED)
            {
                while (CurrentPort.bytesAvailable() == 0) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                byte[] readBuffer = new byte[CurrentPort.bytesAvailable()];
                int numRead = CurrentPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        }
    }
}
