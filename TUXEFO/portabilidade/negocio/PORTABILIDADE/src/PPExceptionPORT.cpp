#include "PPExceptionPORT.h"

PPExceptionPORT::PPExceptionPORT(int iTipoAUX, char *szMessageAUX) {
    iTipo = iTipoAUX;
    strcpy(szMessage, szMessageAUX);
    ULOGE("Exception - Tipo (%d) - Message[%s]", iTipo, szMessage);
}

PPExceptionPORT::~PPExceptionPORT(void) {
};

char* PPExceptionPORT::getMsg(void) {
    return szMessage;
}

int PPExceptionPORT::getTipo(void) {
    return iTipo;
}
