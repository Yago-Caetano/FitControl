package br.com.fitcontrol.fitcontrol.Acesso;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.models.UsuarioModel;

public class Acessologin {
    public Acessologin() {
    }

    private boolean validaSenha(String senhaRepositorio, String senhaDigitada) {
        return senhaRepositorio.equals(senhaDigitada);
    }

    public boolean validaUsuario(UsuarioModel user) throws Exception {
        boolean retorno = false;
        Repositorio repositorio = FabricaRepositorio.Fabrica();
        UsuarioModel usuario = (UsuarioModel)repositorio.login(user.getLogin(),user.getSenha(), EntidadesDisponiveis.USUARIO);
        if (usuario != null) {
            retorno = this.validaSenha(usuario.getSenha(), user.getSenha());
        }

        return retorno;
    }
}
