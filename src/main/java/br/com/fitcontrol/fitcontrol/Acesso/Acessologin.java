package br.com.fitcontrol.fitcontrol.Acesso;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;

public class Acessologin {
    public Acessologin() {
    }

    private boolean validaSenha(String senhaRepositorio, String senhaDigitada) {
        return senhaRepositorio.equals(senhaDigitada);
    }

    public boolean validaUsuario(FuncionarioModel user) throws Exception {
        boolean retorno = false;
        Repositorio repositorio = FabricaRepositorio.Fabrica();
        FuncionarioModel func = (FuncionarioModel)repositorio.login(user.getLogin(),user.getSenha());
        if (func != null) {
            retorno = this.validaSenha(func.getSenha(), func.getSenha());
        }

        return retorno;
    }
}
