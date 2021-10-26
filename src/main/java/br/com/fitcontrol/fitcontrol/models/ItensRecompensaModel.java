package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;

public class ItensRecompensaModel extends Entidade {

    private String descricao;
    private String foto;

    public ItensRecompensaModel() {
        this.setTipoEntidade(EnumEntidadesDisponiveis.ITENS_RECOMPENSA);

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
