///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "../include/Global.h"
#include "../include/LinhaContapc.h"
#include "../include/Global.h"
#include <memory.h>
#include "../include/LinhaConta.h"
#include "../include/Exception.h"
#include "../include/TString.h"

CLinhaConta::CLinhaConta(void)
{
    memset(&tLinhaConta, 0x00, sizeof(TLinhaConta));
}

void CLinhaConta::mapeiaLinhaConta(DOMNode *dnode)
{

}

char *CLinhaConta::getIdLinhaSistemaOrigem(void)
{
    static char szAux[LEN_IDLINHASISTEMAORIGEM + LEN_EOS];

   
    return szAux;
}

char *CLinhaConta::getNrConta(void)
{
    TString conta = tLinhaConta.szIdConta; 
    return conta.c_str();
}

char *CLinhaConta::getIdLinhaConta(void)
{
    static char szAux[LEN_IDLINHACONTA + LEN_EOS];

    strcpy(szAux, tLinhaConta.szIdLinhaConta);
    return szAux;
}

char *CLinhaConta::getIdPessoaSistemaOrigem(void)
{
    static char szAux[LEN_IDPESSOASISTEMAORIGEM + LEN_EOS];

  
    return szAux;
}

char *CLinhaConta::getSqSincronismo(void)
{
    static char szAux[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy(szAux, tLinhaConta.szSqSincronismo);
    return szAux;
}

void CLinhaConta::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    strcpy(tLinhaConta.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CLinhaConta::setIdConta(char *pszIdConta)
{
    strcpy(tLinhaConta.szIdConta, pszIdConta);
}

void CLinhaConta::setIdTipoRelacionamento(char *pszTipoRelacionamento)
{
    strcpy(tLinhaConta.szIdTipoRelacionamento, pszTipoRelacionamento);
}

void CLinhaConta::setDtLinhaConta(char *pszDtLinhaConta)
{
    strcpy(tLinhaConta.szDtLinhaConta, pszDtLinhaConta);
}

void CLinhaConta::setIdLinhaConta(char *pszIdLinhaConta)
{
    strcpy(tLinhaConta.szIdLinhaConta, pszIdLinhaConta);
}

void CLinhaConta::setSqSincronismo(char *pszSqSincronismo)
{
    strcpy(tLinhaConta.szSqSincronismo, pszSqSincronismo);
}

void CLinhaConta::setDtExpiracao(char *pszDtExpiracao)
{
    strcpy(tLinhaConta.szDtExpiracao, pszDtExpiracao);
}

void CLinhaConta::atualizaLinhaConta(void)
{
    clLinhaContapc.proCAtualizaLinhaConta(tLinhaConta);
}

void CLinhaConta::insereLinhaConta(void)
{
    clLinhaContapc.proCInsereLinhaConta(&tLinhaConta);
}

bool CLinhaConta::buscaLinhaConta(void){
    return clLinhaContapc.proCBuscaLinhaConta(&tLinhaConta);
}

bool CLinhaConta::buscaLinhaConta(TLinhaConta *ptLinhaContaAux)
{
    return clLinhaContapc.proCBuscaLinhaConta(ptLinhaContaAux);
}

void CLinhaConta::apagaLinhaConta(void)
{
    clLinhaContapc.proCApagaLinhaConta(tLinhaConta);
}

void CLinhaConta::falsoInsertLinhaConta(void)
{
    clLinhaContapc.proCInsereLinhaConta(&tLinhaConta);
}
