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

    public FuncionarioModel validaUsuario(FuncionarioModel user) throws Exception {
        FuncionarioModel retValue = null;
        Repositorio repositorio = FabricaRepositorio.Fabrica();
        FuncionarioModel func = (FuncionarioModel)repositorio.login(user.getLogin(),user.getSenha());
        if (func != null) {
            if(this.validaSenha(func.getSenha(), func.getSenha()))
                retValue = func;
        }

        return retValue;
    }
}
