package br.com.fitcontrol.fitcontrol.serialcom;

import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;

import java.sql.SQLException;
import java.util.Stack;

import br.com.fitcontrol.fitcontrol.publishers.PublisherSerial;
import com.fazecast.jSerialComm.SerialPort;

import static br.com.fitcontrol.fitcontrol.serialcom.EnumSerialFunctions.*;

/**
 * @brief: Class used to Handle Serial communications
 *
 *                              Protocol description
 *     ---------------------------------------------------------------------------
 *    |     STX       |           FUNCTION        |      DATA      |     ETX     |
 *    ----------------------------------------------------------------------------
 *    |    0x02       | Type of data transmitted  |      Data      |      0x03   |
 *    ----------------------------------------------------------------------------
 *    |     1 Byte    |        1 Byte             |     n Byte     |    1 Byte   |
 *    ---------------------------------------------------------------------------
 *
 *
 *                                      Examples
 *   CHECK-IN EVENT USER ID 1515
 *
 *   02 00 15 15 03
 *
 *   CHECK-OUT EVENT USER ID 1515
 *
 *   02 01 15 15 03
 *
 *   ERROR 5:
 *   02 02 05 03
 *
 *
 *
 */

public class SerialCommunicatorSingleton extends Thread {

    private static SerialCommunicatorSingleton instance = new SerialCommunicatorSingleton();

    private static boolean FLAG_PORT_OPENNED = false;

    private SerialPort CurrentPort;
    private final static int BAUD_RATE = 115200;
    private final static int DATA_BYTES = 8;
    private final static int STOP_BITS = 0;
    private final static int DATA_PARITY = 0;

    private PublisherSerial mPublisher;

    private SerialCommunicatorSingleton()
    {

    }

    public static SerialCommunicatorSingleton getInstance()
    {
        return instance;
    }

    /**
     * @brief: Send data throught serial port
     */
    public void sendData(byte[] data,EnumSerialFunctions function){
        if(FLAG_PORT_OPENNED)
        {
            byte[] sendBuffer = new byte[data.length+3];

            sendBuffer[0] = 0x02;
            sendBuffer[1] = (byte) function.ordinal();
            sendBuffer[(sendBuffer.length-1)] = 0x03;

            for(int i = 0; i < data.length;i++)
            {
                sendBuffer[i+2] = data[i];
            }
            CurrentPort.writeBytes(sendBuffer,sendBuffer.length);
        }
    }

    /**
     * @brief: Get All Available COM Ports in System
     * @return Array containing Serial Ports objects
     */
    public SerialPort[] getAvailablePorts(){
        return SerialPort.getCommPorts();
    }

    public void registerPublisher(PublisherSerial publisherSerial)
    {
        mPublisher = publisherSerial;
    }


    /**
     * @brief: Connect to specific serial port
     * @param port Target serial port
     */
    public void connect(SerialPort port)
    {
        CurrentPort = port;
        CurrentPort.setComPortParameters(BAUD_RATE,DATA_BYTES,STOP_BITS,DATA_PARITY);
        CurrentPort.openPort();
        FLAG_PORT_OPENNED = true;
    }

    /**
     * @brief: Close a COM connection
     */
    public void disconnect()
    {
        CurrentPort.closePort();
        FLAG_PORT_OPENNED = false;
    }


    private void checkFunctionAndDispatchEvent(int[] data,int function) throws SQLException {
        if(mPublisher == null)
            return;

        EnumSerialFunctions mFunction = values()[function];
        switch (mFunction)
        {
            case CHECK_IN:
                mPublisher.CheckInEvent();
                break;

            case CHECK_OUT:
                mPublisher.CheckInEvent();
                break;

            case ERROR:
                mPublisher.ErroEvent();
                break;

            default:

                break;
        }
    }


    /**
     * @brief: Handle serial incoming data
     * @param bruteSerialData
     */
    private void handleIncommingData(byte[] bruteSerialData) throws SQLException {
        //simple validation
        if((bruteSerialData[0] == 2) && (bruteSerialData[bruteSerialData.length-1] == 3))
        {
            //STX and ETX checked, then take the function
            int function = bruteSerialData[1];
            int data[] = new int[bruteSerialData.length-3];

            //take data
            for(int k = 0; k < data.length; k++)
            {
                data[k] = bruteSerialData[k+2];
            }
            if(isAValidPayload(data,function))
            {
                //valid package
                checkFunctionAndDispatchEvent(data,function);
            }
        }
    }

    /**
     * @brief Verify if the package data is valid
     * @param data data buffer
     * @param function function received
     * @return
     */
    private boolean isAValidPayload(int[] data,int function)
    {
        boolean isValid = false;
        if((function >= 0) && (function < values().length))
        {
            EnumSerialFunctions mFunc = values()[function];

            if(mFunc.getPackageSize() == data.length)
            {
                isValid = true;
            }

        }
        return isValid;
    }

    @Override
    public void run() {

        while(true)
        {
            try{
                if(FLAG_PORT_OPENNED)
                {
                    if (CurrentPort.bytesAvailable() == 0) {
                        Thread.sleep(20);
                    }
                    else
                    {
                        byte[] readBuffer = new byte[CurrentPort.bytesAvailable()];
                        int numRead = CurrentPort.readBytes(readBuffer, readBuffer.length);
                        handleIncommingData(readBuffer);
                    }

                }
            }
            catch(Exception e)
            {

            }

        }
    }
}
