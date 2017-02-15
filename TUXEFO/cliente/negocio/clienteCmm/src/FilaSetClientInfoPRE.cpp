///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase FilaSetClientInfo
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.130.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/09/04 20:26:11 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <memory.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "../include/FilaSetClientInfoPRE.h"
#include <tuxfw.h>

CFilaSetClientInfo::CFilaSetClientInfo(void)
{
    memset(&tFilaSetClientInfo, 0x00, sizeof(TFilaSetClientInfoPRE));
}

void CFilaSetClientInfo::atualizaXmlFilaSetClientInfo(void)
{
    clFilaSetClientInfopc.proCAtualizaXmlFilaSetClientInfo(tFilaSetClientInfo);
}

void CFilaSetClientInfo::insereFilaSetClientInfo(void)
{
    clFilaSetClientInfopc.proCIncluiFilaSetClientInfo(&tFilaSetClientInfo);
}

/*
 * RECADASTRAMENTO CPF
 */
void CFilaSetClientInfo::insereFilaSetClientInfo_4(void)
{
    clFilaSetClientInfopc.proCIncluiFilaSetClientInfo_4(&tFilaSetClientInfo);
}

bool CFilaSetClientInfo::existeFilaSetClientInfo(void)
{
    return clFilaSetClientInfopc.proCExisteFilaSetClientInfo(tFilaSetClientInfo);
}

void CFilaSetClientInfo::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    strcpy(tFilaSetClientInfo.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CFilaSetClientInfo::setXml(char *pszXml)
{
    strcpy(tFilaSetClientInfo.szXml, pszXml);
}
