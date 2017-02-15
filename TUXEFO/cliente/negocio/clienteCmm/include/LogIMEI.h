#ifndef LOGIMEIH
#define LOGIMEIH

#include <tuxfw.h>
#include "Global.h"

class LogIMEI
{

	public:
        TLogIMEI tLogIMEI;

        LogIMEI();
        ~LogIMEI();

        void insereLogIMEI(void);

        void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
        void setNrProtocolo(char *pszNrProtocolo);
        void setDsMarca(char *pszDsMarca);
        void setDsModelo(char *pszDsModelo);
        void setNmLoja(char *pszNmLoja);
        void setIMEI(char *pszIMEI);
        void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
};

#endif
