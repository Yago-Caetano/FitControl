package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.serialcom.EnumSerialFunctions;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class CatracaUnlockListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) {
        SerialCommunicatorSingleton ser = SerialCommunicatorSingleton.getInstance();

        if(ser.isConnected())
        {
            CatracaModel catracaModel = (CatracaModel) context.getEntityData();
            ser.sendData(catracaModel.getId().getBytes(StandardCharsets.UTF_8), EnumSerialFunctions.UNLOCK);
        }
        else
        {
            NavigationSingleton navigation = NavigationSingleton.getInstance();
            ErrorPopUpSingleton.getInstance().showError("Não há catraca conectada!");
        }

    }
}
