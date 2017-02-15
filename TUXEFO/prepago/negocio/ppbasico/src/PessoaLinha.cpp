///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinha
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "tuxfw.h"
#include "PessoaLinhapc.h"
#include "PessoaLinha.h"

CPessoaLinha::CPessoaLinha(void)
{
    memset(&tPessoaLinha, 0x00, sizeof(TPessoaLinha));
    memset(&tPessoaLinhaArr, 0x00, sizeof(TPessoaLinhaArr));
}

CPessoaLinha::~CPessoaLinha(void)
{
    clPessoaLinhapc.desaloca(&tPessoaLinhaArr);
}

CPessoaLinha::CPessoaLinha(char *pszIdTipoRelacionamento, char *pszIdLinhaTelefonica) 
{
    memset(&tPessoaLinha, 0x00, sizeof(TPessoaLinha));

    strcpy(tPessoaLinha.szIdTipoRelacionamento, pszIdTipoRelacionamento);
    strcpy(tPessoaLinha.szIdLinhaTelefonica, pszIdLinhaTelefonica);
    strcpy(tPessoaLinha.szIdUsuarioAlteracao, ID_USUARIO_ALTERACAO);
}

void CPessoaLinha::atualizaPessoaLinhaPorIdConta(char *pszIdPessoaDePara, char *pszIdConta)
{
    clPessoaLinhapc.proCAtualizaPessoaLinhaPorIdConta(pszIdPessoaDePara, pszIdConta);
}

bool CPessoaLinha::buscaPessoaLinha(void)
{
    return clPessoaLinhapc.proCBuscaPessoaLinha(&tPessoaLinha);  
}

bool CPessoaLinha::buscaPessoaLinha(TPessoaLinha *ptPessoaLinhaAux)
{
    return clPessoaLinhapc.proCBuscaPessoaLinha(ptPessoaLinhaAux);
}

void CPessoaLinha::falsoInsertPessoaLinha(void)
{
    clPessoaLinhapc.proCInserePessoaLinha(&tPessoaLinha);
}

void  CPessoaLinha::atualizaPessoaLinha(void) 
{
    clPessoaLinhapc.proCAtualizaPessoaLinha(tPessoaLinha);
}

void  CPessoaLinha::inserePessoaLinha(void) 
{
    clPessoaLinhapc.proCInserePessoaLinha(&tPessoaLinha);
}

void CPessoaLinha::apagaPessoaLinha(void)
{
    clPessoaLinhapc.proCApagaPessoaLinha(tPessoaLinha);
}


void CPessoaLinha::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    strcpy(tPessoaLinha.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CPessoaLinha::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tPessoaLinha.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaLinha::setIdTipoRelacionamento(char *pszIdTipoRelacionamento)
{
    strcpy(tPessoaLinha.szIdTipoRelacionamento, pszIdTipoRelacionamento);
}

void CPessoaLinha::setIdPessoaLinha(char *pszIdPessoaLinha)
{
    strcpy(tPessoaLinha.szIdPessoaLinha, pszIdPessoaLinha);
}

void CPessoaLinha::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaLinha.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

char *CPessoaLinha::getIdPessoaDePara(void)
{
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdPessoaDePara);
    return szAux;
}

char *CPessoaLinha::getIdTipoRelacionamento(void)
{
    static char szAux[LEN_IDTIPORELACIONAMENTO + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdTipoRelacionamento);
    return szAux;
}

char *CPessoaLinha::getIdPessoaLinha(void)
{
    static char szAux[LEN_IDPESSOALINHA + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdPessoaLinha);
    return szAux;
}

char *CPessoaLinha::getIdLinhaTelefonica(void)
{
    static char szAux[LEN_IDLINHATELEFONICA + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdLinhaTelefonica);
    return szAux;
}

void CPessoaLinha::setStruct(TPessoaLinha *ptPessoaLinha)
{
    memcpy(&tPessoaLinha, ptPessoaLinha, sizeof(TPessoaLinha));
    memset(ptPessoaLinha, 0x00, sizeof(TPessoaLinha));
}

TPessoaLinha* CPessoaLinha::getRegistro( int iIndex )
{
	if( getQuantidade() > 0 )
	{
		iIndex = ( iIndex >= getQuantidade() ? getQuantidade()-1 : iIndex );
		iIndex = ( iIndex < 0 ? 0 : iIndex );
		return &tPessoaLinhaArr.pztPessoaLinha[iIndex];
	}
	return NULL;
}

bool CPessoaLinha::buscaIdClienteUsuario( void )
{
	return clPessoaLinhapc.proCBuscaIdClienteUsuario( &tPessoaLinhaArr, &tPessoaLinha );
}
