package br.com.fitcontrol.fitcontrol.controllers;

public abstract class PadrãoController {

    protected Object DataFromPreviousScreen;

    public  Object getDataFromPreviousScreen(){
        return DataFromPreviousScreen;
    }

    public void setDataFromPreviousScreen(Object data)
    {
        DataFromPreviousScreen = data;
        this.PreviousScreenDataReceived();
    }

    protected abstract void PreviousScreenDataReceived();
}
