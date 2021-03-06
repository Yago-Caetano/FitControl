package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.serialcom.EnumSerialFunctions;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class CatracaBlockListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context){
        SerialCommunicatorSingleton ser = SerialCommunicatorSingleton.getInstance();
        CatracaModel catracaModel = (CatracaModel) context.getEntityData();
        if(ser.isConnected())
        {
            ser.sendData(catracaModel.getId().getBytes(StandardCharsets.UTF_8), EnumSerialFunctions.BLOCK);

        }
        else
        {
            NavigationSingleton navigation = NavigationSingleton.getInstance();
            ErrorPopUpSingleton.getInstance().showError("Não há catraca conectada!");
        }
    }
}
