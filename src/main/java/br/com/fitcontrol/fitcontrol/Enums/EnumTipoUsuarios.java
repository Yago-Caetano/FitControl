package br.com.fitcontrol.fitcontrol.Enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EnumTipoUsuarios {
    CLIENTE((byte)1),
    FUNCIONARIO((byte)2),
    GERENTE((byte)3);

    private static final Map<Integer, EnumTipoUsuarios> lookup
            = new HashMap<Integer, EnumTipoUsuarios>();

    static {
        for(EnumTipoUsuarios w : EnumSet.allOf(EnumTipoUsuarios.class))
            lookup.put((int) w.getCode(), w);
    }

    private byte code;
    public byte getCode() { return code; }
    private EnumTipoUsuarios(byte code) {
        this.code = code;
    }


}
