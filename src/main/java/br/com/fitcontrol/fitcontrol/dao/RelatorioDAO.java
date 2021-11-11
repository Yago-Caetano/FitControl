package br.com.fitcontrol.fitcontrol.dao;

import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class RelatorioDAO <E extends RelatorioModel> {

    protected  Date data1;
    protected  Date data2;



    protected Class<E> entityClass;

    public RelatorioDAO(Date data1, Date data2, Class entityClass) {
        this.data1=data1;
        this.data2=data2;
        this.entityClass=entityClass;
    }

    public abstract void GetRelatorio();


}
