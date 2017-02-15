///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase RegistraContato
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.118.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/30 17:36:13 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <memory.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "PrePagoException.h"
#include "RegistraContato.h"
#include "tuxfw.h"

CRegistraContato::CRegistraContato(void)
{
    memset(&tRegistraContato, 0x00, sizeof(TRegistraContato));
}

void CRegistraContato::atualizaRegistraContato(void)
{
    clRegistraContatopc.proCAtualizaRegistraContato(tRegistraContato);
}

void CRegistraContato::insereRegistraContato(void)
{
    clRegistraContatopc.proCIncluiRegistraContato(&tRegistraContato);
}

bool CRegistraContato::existeRegistraContato(void)
{
    return clRegistraContatopc.proCExisteRegistraContato(tRegistraContato);
}



void CRegistraContato::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tRegistraContato.szIdPessoaDePara, pszIdPessoaDePara);
}

void CRegistraContato::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    strcpy(tRegistraContato.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CRegistraContato::setCdAreaRegistro(char *pszCdAreaRegistro)
{
    strcpy(tRegistraContato.szCdAreaRegistro, pszCdAreaRegistro);
}

void CRegistraContato::setNrLinha(char *pszNrLinha)
{
    strcpy(tRegistraContato.szNrLinha, pszNrLinha);
}

void CRegistraContato::setIdPessoaLinhaHistorico(char *pszIdPessoaLinhaHistorico)
{
    strcpy(tRegistraContato.szIdPessoaLinhaHistorico, pszIdPessoaLinhaHistorico);
}

void CRegistraContato::setXml(char *pszXml)
{
    strcpy(tRegistraContato.szXML, pszXml);
}

void CRegistraContato::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tRegistraContato.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CRegistraContato::setNrTelefone(char *pszNrTelefone)
{
    char szAux[16];

    memset(szAux, 0x00, sizeof(szAux));
    memcpy(szAux, pszNrTelefone, 2);
    this->setCdAreaRegistro(szAux);

    memset(szAux, 0x00, sizeof(szAux));
    //memcpy(szAux, pszNrTelefone+2, 8);
    sprintf(szAux,"%s",(char*)&pszNrTelefone[2]);
    this->setNrLinha(szAux);
}
