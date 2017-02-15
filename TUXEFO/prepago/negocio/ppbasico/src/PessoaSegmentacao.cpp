///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase PessoaSegmentacao
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1.152.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2015/09/24 17:55:46 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "tuxfw.h"
#include "PessoaSegmentacaopc.h"
#include "PessoaSegmentacao.h"

CPessoaSegmentacao::CPessoaSegmentacao(void)
{
    memset(&tPessoaSegmentacao, 0x00, sizeof(TPessoaSegmentacao));
}


void CPessoaSegmentacao::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tPessoaSegmentacao.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaSegmentacao::setIdPessoaSegmentacao(char *pszIdPessoaSegmentacao)
{
    strcpy(tPessoaSegmentacao.szIdPessoaSegmentacao, pszIdPessoaSegmentacao);
}

void CPessoaSegmentacao::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaSegmentacao.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}


char *CPessoaSegmentacao::getIdPessoaSegmentacao(void)
{
    static char szAux[LEN_IDPESSOASEGMENTACAO + LEN_EOS];

    strcpy(szAux, tPessoaSegmentacao.szIdPessoaSegmentacao);
    return szAux;
}

char *CPessoaSegmentacao::getIdPessoaDePara(void)
{
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaSegmentacao.szIdPessoaDePara);
    return szAux;
}


bool CPessoaSegmentacao::buscaPessoaSegmentacao(void)
{
    return clPessoaSegmentacaopc.proCBuscaPessoaSegmentacao(&tPessoaSegmentacao);
}

bool CPessoaSegmentacao::buscaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacaoAux)
{
    return clPessoaSegmentacaopc.proCBuscaPessoaSegmentacao(ptPessoaSegmentacaoAux);
}

void CPessoaSegmentacao::inserePessoaSegmentacao(void)
{
    clPessoaSegmentacaopc.proCInserePessoaSegmentacao(&tPessoaSegmentacao);
}

void CPessoaSegmentacao::atualizaPessoaSegmentacao(void)
{
    clPessoaSegmentacaopc.proCAtualizaPessoaSegmentacao(&tPessoaSegmentacao);
}



bool CPessoaSegmentacao::ClassificaInfancia( char * vi_idLinhaTelefonica, char * vi_idPessoa , char * vi_idTipoPessoa )
{
    return clPessoaSegmentacaopc.proCClassificaInfancia( vi_idLinhaTelefonica, vi_idPessoa , vi_idTipoPessoa );
}