package br.com.fitcontrol.fitcontrol.Enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TipoUsuarios {
    CLIENTE((byte)1),
    FUNCIONARIO((byte)2);

    private static final Map<Integer,TipoUsuarios> lookup
            = new HashMap<Integer,TipoUsuarios>();

    static {
        for(TipoUsuarios w : EnumSet.allOf(TipoUsuarios.class))
            lookup.put((int) w.getCode(), w);
    }

    private byte code;
    public byte getCode() { return code; }
    private TipoUsuarios(byte code) {
        this.code = code;
    }


}
