package br.com.fitcontrol.fitcontrol.serialcom;

public enum EnumSerialFunctions {

    CHECK_IN("check-in",72),
    CHECK_OUT("check-out",72),
    ERROR("error",37);

    private String name;
    private int packageSize;

    public int getPackageSize(){return this.packageSize;}

    private EnumSerialFunctions(String name,int packageSize)
    {
        this.name = name;
        this.packageSize = packageSize;
    }


}
