#ifndef PREPAGO_EXCEPTIONHTMA
#define PREPAGO_EXCEPTIONHTMA

#include "PPGlobalTMA.h"
#include "tuxfw.h"

class PPExceptionTMA
{
    int iTipo;
    char szMessage[LEN_MESSAGE_EXCEPTION+LEN_EOS];

public:
	PPExceptionTMA(int iTipoAUX, char *szMessageAUX);
	~PPExceptionTMA(void);

	char* getMsg(void);
    int getTipo(void);
};

#endif
