///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinhaHistorico
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/PessoaLinhaHistoricopc.h"
#include "../include/Global.h"
#include "../include/PessoaLinhaHistorico.h"

CPessoaLinhaHistorico::CPessoaLinhaHistorico(void)  
{
    memset(&tPessoaLinhaHistorico, 0x00, sizeof(TPessoaLinhaHistorico));
    
    strcpy(tPessoaLinhaHistorico.szIdUsuarioAlteracao, ID_USUARIO_ALTERACAO);
    // strcpy(tPessoaLinhaHistorico.szDtRelacionamento, "19000101000000");
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
