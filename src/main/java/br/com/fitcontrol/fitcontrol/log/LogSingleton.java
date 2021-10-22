package br.com.fitcontrol.fitcontrol.log;

import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;



public class LogSingleton {

    private static LogSingleton instance = new LogSingleton();

    private LogSingleton()
    {

    }

    public static LogSingleton getInstance()
    {
        return instance;
    }

    public void GeraLog(String log)
    {
        WriteLog wLog = new WriteLog(log);

        wLog.run();
    }


}
