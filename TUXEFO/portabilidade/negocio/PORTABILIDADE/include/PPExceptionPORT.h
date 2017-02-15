#ifndef PREPAGO_EXCEPTIONHPORT
#define PREPAGO_EXCEPTIONHPORT

#include "PPGlobalPORT.h"
#include "tuxfw.h"

class PPExceptionPORT
{
    int iTipo;
    char szMessage[LEN_MESSAGE_EXCEPTION+LEN_EOS];

public:
	PPExceptionPORT(int iTipoAUX, char *szMessageAUX);
	~PPExceptionPORT(void);

	char* getMsg(void);
    int getTipo(void);
};

#endif
