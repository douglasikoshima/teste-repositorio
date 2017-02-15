#include "PPExceptionTMA.h"

PPExceptionTMA::PPExceptionTMA(int iTipoAUX, char *szMessageAUX) {
    iTipo = iTipoAUX;
    strcpy(szMessage, szMessageAUX);
    ULOGE("Exception - Tipo (%d) - Message[%s]", iTipo, szMessage);
}

PPExceptionTMA::~PPExceptionTMA(void) {
};

char* PPExceptionTMA::getMsg(void) {
    return szMessage;
}

int PPExceptionTMA::getTipo(void) {
    return iTipo;
}
