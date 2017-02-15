#ifndef LOGDEVICEMANAGERH
#define LOGDEVICEMANAGERH

#include <tuxfw.h>
#include "Global.h"

class LogDeviceManager
{
public:
    TLogDeviceManager tLogDeviceManager;

    LogDeviceManager(void);
    ~LogDeviceManager(void);

    void setIP(char *pszIP);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setNomeParametroAtualizado(char *pszNomeParametroAtualizado);
    void setEstadoConsulta(char *pszEstadoConsulta);
    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);

    bool insereLogDeviceManager(void);
    bool buscaIPLHLogDeviceManager(void);
};
#endif
