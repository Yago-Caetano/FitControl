package br.com.fitcontrol.fitcontrol.serialcom;

public enum EnumSerialFunctions {

    CHECK_IN("check-in",2),
    CHECK_OUT("check-out",2),
    ERROR("error",1);

    private String name;
    private int packageSize;

    public int getPackageSize(){return this.packageSize;}

    private EnumSerialFunctions(String name,int packageSize)
    {
        this.name = name;
        this.packageSize = packageSize;
    }


}
