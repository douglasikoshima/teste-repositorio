#ifndef LOGFILAPREPAGOH
#define LOGFILAPREPAGOH

#include <tuxfw.h>
#include "Global.h"

class LogFilaSetClientInfo
{
	public:
        TLogFilaSetClientInfo tLogFilaSetClientInfo;

        LogFilaSetClientInfo(char *pszIdUsuarioAlteracao);
        ~LogFilaSetClientInfo(void);

        void clearLogFilaSetClientInfo(void);

        void setXml(char *pszXml);
        void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);

        void insereLogFilaSetClientInfo(void);

    private:
        void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
};

#endif
