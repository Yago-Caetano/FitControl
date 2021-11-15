package br.com.fitcontrol.fitcontrol.serialcom;

import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.CatracaErro;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
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
 *
 *   ---------------------------------- CHECK-IN EVENT ------------------------------------
 *
 *                                  Payload description
 *  *     -------------------------------------------------------------------------------------
 *  *    |     STX       |           FUNCTION        |             DATA         |     ETX     |
 *  *    -------------------------------------------------------------------------------------
 *  *    |    0x02       |            0x00           |  Catraca ID  |  User ID  |    0x03   |
 *  *    -----------------------------------------------------------------------------------
 *
 *   CHECK-IN EVENT USER ID b2d9c5c7-6645-484d-9850-f1c7800380e8
 *   Catraca ID: 87eb8029-5e94-405c-b85d-e03f607e3232
 *
 *   02 00 62 38 37 65 62 38 30 32 39 2d 35 65 39 34 2d 34 30 35 63 2d 62 38 35 64 2d 65 30 33 66 36 30 37 65 33 32 33 32 32 64 39 63 35 63 37 2d 36 36 34 35 2d 34 38 34 64 2d 39 38 35 30 2d 66 31 63 37 38 30 30 33 38 30 65 38 03
 *
 *
 *      ---------------------------------- CHECK-OUT EVENT ------------------------------------
 *
 *                                   Payload description
 *    -------------------------------------------------------------------------------------
 *    |     STX       |           FUNCTION        |             DATA         |     ETX     |
 *    -------------------------------------------------------------------------------------
 *    |    0x02       |            0x01           |  Catraca ID  |  User ID  |    0x03   |
 *    -----------------------------------------------------------------------------------
 *
 *   USER ID: b2d9c5c7-6645-484d-9850-f1c7800380e8
 *   Catraca ID: 87eb8029-5e94-405c-b85d-e03f607e3232
 *
 *   02 01 62 38 37 65 62 38 30 32 39 2d 35 65 39 34 2d 34 30 35 63 2d 62 38 35 64 2d 65 30 33 66 36 30 37 65 33 32 33 32 32 64 39 63 35 63 37 2d 36 36 34 35 2d 34 38 34 64 2d 39 38 35 30 2d 66 31 63 37 38 30 30 33 38 30 65 38 03
 *
 *
 *      ---------------------------------- ERROR EVENT ------------------------------------
 *
 *                                    Payload description
 *     --------------------------------------------------------------------------------------------
 *     |     STX       |           FUNCTION        |             DATA               |     ETX     |
 *     -------------------------------------------------------------------------------------------
 *     |    0x02       |            0x02           |  Catraca ID  |  ERROR VALUE    |    0x03     |
 *     -------------------------------------------------------------------------------------------
 *
 *   ERROR Value: 5
 *   Catraca ID: 87eb8029-5e94-405c-b85d-e03f607e3232
 *
 *   02 02 38 37 65 62 38 30 32 39 2d 35 65 39 34 2d 34 30 35 63 2d 62 38 35 64 2d 65 30 33 66 36 30 37 65 33 32 33 32 05 03
 *
 *
 *      ---------------------------------- GRANT ACESS FORCED ------------------------------------
 *
 *                                     Payload description
 *      --------------------------------------------------------------------------------------------
 *      |     STX       |           FUNCTION        |             DATA               |     ETX     |
 *      -------------------------------------------------------------------------------------------
 *      |    0x02       |            0x03           |               Catraca ID      |    0x03     |
 *      -------------------------------------------------------------------------------------------
 *
 *
 *    Catraca ID: 87eb8029-5e94-405c-b85d-e03f607e3232
 *
 *    02 03 38 37 65 62 38 30 32 39 2d 35 65 39 34 2d 34 30 35 63 2d 62 38 35 64 2d 65 30 33 66 36 30 37 65 33 32 33 32 03
 *
 *
 *      ---------------------------------- BLOCK ------------------------------------
 *
 *                                     Payload description
 *      --------------------------------------------------------------------------------------------
 *      |     STX       |           FUNCTION        |             DATA               |     ETX     |
 *      -------------------------------------------------------------------------------------------
 *      |    0x02       |            0x04           |            Catraca ID           |    0x03     |
 *      -------------------------------------------------------------------------------------------
 *
 *
 *    Catraca ID: 87eb8029-5e94-405c-b85d-e03f607e3232
 *
 *    02 04 38 37 65 62 38 30 32 39 2d 35 65 39 34 2d 34 30 35 63 2d 62 38 35 64 2d 65 30 33 66 36 30 37 65 33 32 33 32 03
 *
 *
 *      ---------------------------------- RESTART  ------------------------------------
 *
 *                                     Payload description
 *      --------------------------------------------------------------------------------------------
 *      |     STX       |           FUNCTION        |             DATA               |     ETX     |
 *      -------------------------------------------------------------------------------------------
 *      |    0x02       |            0x05           |            Catraca ID           |    0x03     |
 *      -------------------------------------------------------------------------------------------
 *
 *
 *    Catraca ID: 87eb8029-5e94-405c-b85d-e03f607e3232
 *
 *    02 05 38 37 65 62 38 30 32 39 2d 35 65 39 34 2d 34 30 35 63 2d 62 38 35 64 2d 65 30 33 66 36 30 37 65 33 32 33 32 03
 *
 *
 *      ---------------------------------- UNLOCK  ------------------------------------
 *
 *                                     Payload description
 *      --------------------------------------------------------------------------------------------
 *      |     STX       |           FUNCTION        |             DATA               |     ETX     |
 *      -------------------------------------------------------------------------------------------
 *      |    0x02       |            0x06           |            Catraca ID           |    0x03     |
 *      -------------------------------------------------------------------------------------------
 *
 *
 *    Catraca ID: 87eb8029-5e94-405c-b85d-e03f607e3232
 *
 *    02 06 38 37 65 62 38 30 32 39 2d 35 65 39 34 2d 34 30 35 63 2d 62 38 35 64 2d 65 30 33 66 36 30 37 65 33 32 33 32 03


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

    public boolean isConnected(){
        return FLAG_PORT_OPENNED;
    }

    /**
     * @brief: Close a COM connection
     */
    public void disconnect()
    {
        CurrentPort.closePort();
        FLAG_PORT_OPENNED = false;
    }


    private void checkFunctionAndDispatchEvent(byte[] data,int function) throws Exception {
        if(mPublisher == null)
            return;

        EnumSerialFunctions mFunction = values()[function];

        switch (mFunction)
        {
            case CHECK_IN:
                AcessoModel cInAcesso = montaAcesso(data);
                cInAcesso.setTipo((byte)1);
                mPublisher.CheckInEvent(cInAcesso);
                break;

            case CHECK_OUT:
                AcessoModel cOnAcesso = montaAcesso(data);
                cOnAcesso.setTipo((byte)0);
                mPublisher.CheckInEvent(cOnAcesso);
                break;

            case ERROR:
                CatracaErro erro = new CatracaErro();
                erro.setCodErro(data[38]);
                String converted = new String(data,StandardCharsets.UTF_8);
                erro.setIdCatraca(converted.substring(0,36));
                mPublisher.ErroEvent(erro);
                break;

            default:

                break;
        }
    }


    private AcessoModel montaAcesso(byte[] data){
        AcessoModel ret = new AcessoModel();
        String ConvertedData = new String(data, StandardCharsets.UTF_8);
        ret.setIdCatraca(ConvertedData.substring(0,36));
        ret.setIdCliente(ConvertedData.substring(36,72));
        ret.setData(new Date(System.currentTimeMillis()));

        return ret;
    }

    /**
     * @brief: Handle serial incoming data
     * @param bruteSerialData
     */
    private void handleIncommingData(byte[] bruteSerialData) throws Exception {
        //simple validation
        if((bruteSerialData[0] == 2) && (bruteSerialData[bruteSerialData.length-1] == 3))
        {
            //STX and ETX checked, then take the function
            int function = bruteSerialData[1];
            byte data[] = new byte[bruteSerialData.length-3];

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
    private boolean isAValidPayload(byte[] data,int function)
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
