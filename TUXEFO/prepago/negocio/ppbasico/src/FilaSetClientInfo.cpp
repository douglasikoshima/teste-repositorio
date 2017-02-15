///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase FilaSetClientInfo
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2009/09/29 18:09:18 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <memory.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "PrePagoException.h"
#include "FilaSetClientInfo.h"
#include "tuxfw.h"

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;

CFilaSetClientInfo::CFilaSetClientInfo(void)
{
    memset(&tFilaSetClientInfo, 0x00, sizeof(TFilaSetClientInfo));
}

void CFilaSetClientInfo::atualizaXmlFilaSetClientInfo(void)
{
    clFilaSetClientInfopc.proCAtualizaXmlFilaSetClientInfo(&tFilaSetClientInfo);
}

void CFilaSetClientInfo::atualizaXmlFilaSetClientInfo_2(void)
{
    clFilaSetClientInfopc.proCAtualizaXmlFilaSetClientInfo_2(&tFilaSetClientInfo);
}

void CFilaSetClientInfo::insereFilaSetClientInfo(void)
{
    clFilaSetClientInfopc.proCIncluiFilaSetClientInfo(&tFilaSetClientInfo);
}

void CFilaSetClientInfo::insereFilaSetClientInfo_2(void)
{
    clFilaSetClientInfopc.proCIncluiFilaSetClientInfo_2(&tFilaSetClientInfo);
}

bool CFilaSetClientInfo::existeFilaSetClientInfo(void)
{
    return clFilaSetClientInfopc.proCExisteFilaSetClientInfo(&tFilaSetClientInfo);
}

bool CFilaSetClientInfo::existeFilaSetClientInfo_2(void)
{
    return clFilaSetClientInfopc.proCExisteFilaSetClientInfo_2(&tFilaSetClientInfo);
}

void CFilaSetClientInfo::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    SAFE_STRNCPY(tFilaSetClientInfo.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CFilaSetClientInfo::setXml(char *pszXml)
{
    SAFE_STRNCPY(tFilaSetClientInfo.szXml, pszXml);
}

void CFilaSetClientInfo::setCdAreaRegistro(char *pszCdAreaRegistro)
{
    SAFE_STRNCPY(tFilaSetClientInfo.szCdAreaRegistro, pszCdAreaRegistro);
}

void CFilaSetClientInfo::setNrLinha(char *pszNrLinha)
{
    SAFE_STRNCPY(tFilaSetClientInfo.szNrLinha, pszNrLinha);
}

void CFilaSetClientInfo::setInterceptado(char *pszInterceptado)
{
    SAFE_STRNCPY(tFilaSetClientInfo.szInterceptado, pszInterceptado);
}

void CFilaSetClientInfo::setCdErro(char *pszCdErro)
{
    SAFE_STRNCPY(tFilaSetClientInfo.szCdErro, pszCdErro);
}

void CFilaSetClientInfo::setDsErro(char *pszDsErro)
{
    SAFE_STRNCPY(tFilaSetClientInfo.szDsErro, pszDsErro);
}

char *CFilaSetClientInfo::getInterceptado()
{
    return tFilaSetClientInfo.szInterceptado;
}
