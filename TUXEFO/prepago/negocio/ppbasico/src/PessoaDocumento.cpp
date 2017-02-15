///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaDocumento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "PessoaDocumento.h"
#include "PessoaDocumentopc.h"
#include "Pessoa.h"
#include "tuxfw.h"


CPessoaDocumento::CPessoaDocumento(void)
{
    memset(&tPessoaDocumento, 0x00, sizeof(TPessoaDocumento));
    memset(&tPessoaDocumentoB01, 0x00, sizeof(TPessoaDocumentoB01));
}

bool CPessoaDocumento::buscaPessoaDocumento(TPessoaDocumento *ptPessoaDocumentoAux)
{
	return(clPessoaDocumentopc.proCBuscaPessoaDocumento(ptPessoaDocumentoAux));
}

bool CPessoaDocumento::buscaPessoaDocumento(void)
{
	return(clPessoaDocumentopc.proCBuscaPessoaDocumento(&tPessoaDocumento));
}

bool CPessoaDocumento::buscaPessoaDocumentoB01(void)
{
	return(clPessoaDocumentopc.proCBuscaPessoaDocumentoB01(&tPessoaDocumentoB01));
}

bool CPessoaDocumento::buscaPessoaDocumentoB01(TPessoaDocumentoB01 *ptPessoaDocumentoB01Aux)
{
	return(clPessoaDocumentopc.proCBuscaPessoaDocumentoB01(ptPessoaDocumentoB01Aux));
}

bool CPessoaDocumento::buscaPessoaDocumentoApagaB01(void)
{
	return(clPessoaDocumentopc.proCBuscaPessoaDocumentoApagaB01(&tPessoaDocumentoB01));
}

void CPessoaDocumento::inserePessoaDocumento(void)
{
    clPessoaDocumentopc.proCInserePessoaDocumento(&tPessoaDocumento);
}

void CPessoaDocumento::atualizaPessoaDocumento(void)
{
    clPessoaDocumentopc.proCAtualizaPessoaDocumento(tPessoaDocumento);
}

void CPessoaDocumento::apagaPessoaDocumento(void)
{
    clPessoaDocumentopc.proCApagaPessoaDocumento(&tPessoaDocumento);
}

void CPessoaDocumento::setIdDocumento(char *pszIdDocumento)
{
    strcpy(tPessoaDocumento.szIdDocumento, pszIdDocumento);
}

char *CPessoaDocumento::getIdDocumento(void)
{
    static char szAux[LEN_IDDOCUMENTO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdDocumento);
    return szAux;
}

void CPessoaDocumento::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaDocumento.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaDocumento::setIdPessoa(char *pszIdPessoa)
{
    strcpy(tPessoaDocumento.szIdPessoa, pszIdPessoa);
}

char *CPessoaDocumento::getIdPessoa(void)
{
    static char szAux[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdPessoa);
    return szAux;
}

void CPessoaDocumento::setSqSincronismo(char *pszSqSincronismo)
{
    strcpy(tPessoaDocumento.szSqSincronismo, pszSqSincronismo);
}

char *CPessoaDocumento::getSqSincronismo(void)
{
    static char szAux[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szSqSincronismo);
    return szAux;
}

void CPessoaDocumento::setTsSincronismo(char *pszTsSincronismo)
{
    strcpy(tPessoaDocumento.szTsSincronismo, pszTsSincronismo);
}

char *CPessoaDocumento::getTsSincronismo(void)
{
    static char szAux[LEN_TSSINCRONISMO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szTsSincronismo);
    return szAux;
}

void CPessoaDocumento::setIdDocumentoSistemaOrigem(char *pszIdDocumentoSistemaOrigem)
{
    strcpy(tPessoaDocumento.szIdDocumentoSistemaOrigem, pszIdDocumentoSistemaOrigem);
}

char *CPessoaDocumento::getIdDocumentoSistemaOrigem(void)
{
    static char szAux[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdDocumentoSistemaOrigem);
    return szAux;
}

void CPessoaDocumento::setIdSistemaOrigem(char *pszIdSistemaOrigem)
{
    strcpy(tPessoaDocumento.szIdSistemaOrigem, pszIdSistemaOrigem);
}

char *CPessoaDocumento::getIdSistemaOrigem(void)
{
    static char szAux[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdSistemaOrigem);
    return szAux;
}

void CPessoaDocumento::setIdPessoaDocumento(char *pszIdPessoaDocumento)
{
    strcpy(tPessoaDocumento.szIdPessoaDocumento, pszIdPessoaDocumento);
}

char *CPessoaDocumento::getIdPessoaDocumento(void)
{
    static char szAux[LEN_IDPESSOADOCUMENTO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdPessoaDocumento);
    return szAux;
}


void CPessoaDocumento::setIdPessoaB01(char *pszIdPessoa)
{
    strcpy(tPessoaDocumentoB01.szIdPessoa, pszIdPessoa);
}

char *CPessoaDocumento::getIdPessoaB01(void)
{
    static char szAux[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdPessoa);
    return szAux;
}

void CPessoaDocumento::setIdTipoDocumentoB01(char *pszIdTipoDocumento)
{
    strcpy(tPessoaDocumentoB01.szIdTipoDocumento, pszIdTipoDocumento);
}

char *CPessoaDocumento::getIdTipoDocumentoB01(void)
{
    static char szAux[LEN_IDTIPODOCUMENTO + LEN_EOS];

    strcpy(szAux, tPessoaDocumentoB01.szIdTipoDocumento);
    return szAux;
}

void CPessoaDocumento::setIdPessoaDocumentoB01(char *pszIdPessoaDocumento)
{
    strcpy(tPessoaDocumentoB01.szIdPessoaDocumento, pszIdPessoaDocumento);
}

char *CPessoaDocumento::getIdPessoaDocumentoB01(void)
{
    static char szAux[LEN_IDPESSOADOCUMENTO + LEN_EOS];

    strcpy(szAux, tPessoaDocumentoB01.szIdPessoaDocumento);
    return szAux;
}

void CPessoaDocumento::setIdDocumentoB01(char *pszIdDocumento)
{
    strcpy(tPessoaDocumentoB01.szIdDocumento, pszIdDocumento);
}

char *CPessoaDocumento::getIdDocumentoB01(void)
{
    static char szAux[LEN_IDDOCUMENTO + LEN_EOS];

    strcpy(szAux, tPessoaDocumentoB01.szIdDocumento);
    return szAux;
}

void CPessoaDocumento::setTsSincronismoB01(char *pszTsSincronismo)
{
    strcpy(tPessoaDocumentoB01.szTsSincronismo, pszTsSincronismo);
}

char *CPessoaDocumento::getTsSincronismoB01(void)
{
    static char szAux[LEN_TSSINCRONISMO + LEN_EOS];

    strcpy(szAux, tPessoaDocumentoB01.szTsSincronismo);
    return szAux;
}

void CPessoaDocumento::setSqSincronismoB01(char *pszSqSincronismo)
{
    strcpy(tPessoaDocumentoB01.szSqSincronismo, pszSqSincronismo);
}

char *CPessoaDocumento::getSqSincronismoB01(void)
{
    static char szAux[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy(szAux, tPessoaDocumentoB01.szSqSincronismo);
    return szAux;
}

void CPessoaDocumento::setIdDocumentoSistemaOrigemB01(char *pszIdDocumentoSistemaOrigem)
{
    strcpy(tPessoaDocumentoB01.szIdDocumentoSistemaOrigem, pszIdDocumentoSistemaOrigem);
}

char *CPessoaDocumento::getIdDocumentoSistemaOrigemB01(void)
{
    static char szAux[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tPessoaDocumentoB01.szIdDocumentoSistemaOrigem);
    return szAux;
}

void CPessoaDocumento::setIdSistemaOrigemB01(char *pszIdSistemaOrigem)
{
    strcpy(tPessoaDocumentoB01.szIdSistemaOrigem, pszIdSistemaOrigem);
}

char *CPessoaDocumento::getIdSistemaOrigemB01(void)
{
    static char szAux[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tPessoaDocumentoB01.szIdSistemaOrigem);
    return szAux;
}

void CPessoaDocumento::setNrDocumentoB01(char *pszNrDocumento)
{
    strcpy(tPessoaDocumentoB01.szNrDocumento, pszNrDocumento);
}

void CPessoaDocumento::setIdPessoaSistemaOrigemB01(char *pszIdPessoaSistemaOrigem)
{
    strcpy(tPessoaDocumentoB01.szIdPessoaSistemaOrigem, pszIdPessoaSistemaOrigem);
}

void CPessoaDocumento::setStruct(TPessoaDocumento *ptPessoaDocumento)
{
    memcpy(&tPessoaDocumento, ptPessoaDocumento, sizeof(TPessoaDocumento));
    memset(ptPessoaDocumento, 0x00, sizeof(TPessoaDocumento));
}

void CPessoaDocumento::setStructB01(TPessoaDocumentoB01 *ptPessoaDocumentoB01)
{
    memcpy(&tPessoaDocumentoB01, ptPessoaDocumentoB01, sizeof(TPessoaDocumentoB01));
    memset(ptPessoaDocumentoB01, 0x00, sizeof(TPessoaDocumentoB01));
}
