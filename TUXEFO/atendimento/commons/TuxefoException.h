/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:53 $
 **/

#ifndef __TUXEFOEXCEPTION_H__
#define __TUXEFOEXCEPTION_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <iostream>
#include <string>

using namespace std;

/*******************************************************************************
 * Erros de negócio comuns no ambiente de front-office
 *******************************************************************************/
#define TUXEXCE_ID_OBRIGATORIO             1
#define TUXEXCE_ID_DADOS_OBRIGATORIO       2

//==============================================================================
class TuxefoException
{
	int errCode;
	string errMessage;
	string statusCode;

public:
    //==========================================================================
    TuxefoException(int errCodeAUX, const char *szMessageAUX)
    {
        if ( errCodeAUX >= 0 && errCodeAUX <= 99 )
        {
            errCode = errCodeAUX;
        }
        else
        {
            ULOGE("Exception - Codigo de erro inválido, deve ser entre 0 e 99!");
            errCode = 1;
        }

        errMessage = szMessageAUX ? szMessageAUX : "(null)";

        ULOGE("Exception - Tipo (%d) - Message[%s]", errCode, errMessage.c_str());
    }

    //==========================================================================
    ~TuxefoException() {}

    //==========================================================================
    char *getStatusCode()
    {
        char codErr[32];
        sprintf(codErr,"%02dE9999",errCode);

	    statusCode = codErr;

        return (char*)statusCode.c_str();
    }

    //==========================================================================
    char *getErrMessage() { return (char*)errMessage.c_str(); }

    //==========================================================================
    int getErrCode() { return errCode; }
};

#endif
