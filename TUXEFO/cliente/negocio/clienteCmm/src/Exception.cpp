#include "../include/Exception.h"


CException::CException(int iPTipo, char *pPMsg, int iPTam)
{
    iTipo = iPTipo;
    strncpy(sMsg,pPMsg,iPTam);
    sMsg[iPTam] =0;
}

char* CException::getMsg()
{
	return sMsg;
}
