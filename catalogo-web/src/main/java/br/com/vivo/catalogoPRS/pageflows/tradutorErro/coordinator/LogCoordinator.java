package br.com.vivo.catalogoPRS.pageflows.tradutorErro.coordinator;

import com.framework.coordinator.Coordinator;
import com.framework.exception.CoordinatorException;
import com.framework.log.Log;

import  br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.LogedUser;

/**
 * Coordenador do ciclo de vida de Logweb
 */
public class LogCoordinator extends Coordinator
{
    private static LogCoordinator instance;
    private Log logall = new Log();

    private LogCoordinator()
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
            instance = new LogCoordinator();
        }

        return instance;
    }

    public void info(LogedUser logedUser, Object from, Object message)
    {
        logall.info(from, logedUser.toString(), message);
    }

    public void erro(LogedUser logedUser, Object from, Object message)
    {
        logall.erro(from, logedUser.toString(), message);
    }

    public void warn(LogedUser logedUser, Object from, Object message)
    {
        logall.info(from, logedUser.toString(), message);
    }

}