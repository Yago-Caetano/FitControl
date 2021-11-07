package br.com.fitcontrol.fitcontrol.serialcom;

public enum EnumSerialFunctions {

    CHECK_IN("check-in",72),
    CHECK_OUT("check-out",72),
    ERROR("error",37),
    FORCE_GRANT_ACESS("grant-acess",37),
    BLOCK("block",37),
    RESTART("restart",37),
    UNLOCK("unlock",37);

    private String name;
    private int packageSize;

    public int getPackageSize(){return this.packageSize;}

    private EnumSerialFunctions(String name,int packageSize)
    {
        this.name = name;
        this.packageSize = packageSize;
    }


}
