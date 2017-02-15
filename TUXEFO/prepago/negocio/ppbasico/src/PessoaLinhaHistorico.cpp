///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinhaHistorico
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:23 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "tuxfw.h"
#include "PessoaLinhaHistoricopc.h"
#include "Global.h"
#include "PessoaLinhaHistorico.h"

CPessoaLinhaHistorico::CPessoaLinhaHistorico(void)  
{
    memset(&tPessoaLinhaHistorico, 0x00, sizeof(TPessoaLinhaHistorico));
    
    strcpy(tPessoaLinhaHistorico.szIdUsuarioAlteracao, ID_USUARIO_ALTERACAO);
}

void CPessoaLinhaHistorico::inserePessoaLinhaHistorico(void)
{
    clPessoaLinhaHistoricopc.proCInserePessoaLinhaHistorico(&tPessoaLinhaHistorico);
}

void CPessoaLinhaHistorico::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    strcpy(tPessoaLinhaHistorico.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CPessoaLinhaHistorico::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tPessoaLinhaHistorico.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaLinhaHistorico::setIdTipoRelacionamento(char *pszIdTipoRelacionamento)
{
    strcpy(tPessoaLinhaHistorico.szIdTipoRelacionamento, pszIdTipoRelacionamento);
}

void CPessoaLinhaHistorico::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaLinhaHistorico.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaLinhaHistorico::setCdAreaRegistro(char *pszCdAreaRegistro)
{
    strcpy(tPessoaLinhaHistorico.szCdAreaRegistro, pszCdAreaRegistro);
}

void CPessoaLinhaHistorico::setNrLinha(char *pszNrLinha)
{
    strcpy(tPessoaLinhaHistorico.szNrLinha, pszNrLinha);
}

char *CPessoaLinhaHistorico::getIdPessoaLinhaHistorico(void)
{
    static char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];

    strcpy(szIdPessoaLinhaHistorico, tPessoaLinhaHistorico.szIdPessoaLinhaHistorico);

    return szIdPessoaLinhaHistorico;
}
