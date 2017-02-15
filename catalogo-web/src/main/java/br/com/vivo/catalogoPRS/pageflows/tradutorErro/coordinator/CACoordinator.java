package br.com.vivo.catalogoPRS.pageflows.tradutorErro.coordinator;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.LogedUser;

import com.framework.coordinator.Coordinator;
import com.framework.exception.CoordinatorException;

public class CACoordinator extends Coordinator
{
    private static CACoordinator instance;

    private CACoordinator()
    {
    }

    /**
     * M�todo getInstance() do padr�o Singleton. <br>
     * 
     * @return com.framework.coordinator.Coordinator
     * @throws com.framework.exception.CoordinatorException
     */
    public static synchronized Coordinator getInstance()
            throws CoordinatorException
    {
        if (instance == null)
        {
            instance = new CACoordinator();
        }

        return instance;
    }

    public void validaUsuario(LogedUser logedUser, String senha)
            throws Exception
    {
        // throws CAException("login invalido");
    }

    public void validaPerfil(LogedUser logedUser, String tabela, String operacao)
            throws Exception
    {
        // throws CAException("acesso negado");
    }
}