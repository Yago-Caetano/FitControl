package br.com.fitcontrol.fitcontrol.serialcom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerialCommunicatorSingletonTest {

    SerialCommunicatorSingleton sSingleton;
    @Test
    public void test() {
         sSingleton = SerialCommunicatorSingleton.getInstance();

    }


}
