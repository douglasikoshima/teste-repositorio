///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Documento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:23 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "Documento.h"
#include "Documentopc.h"
#include "Pessoa.h"
#include "Tools.h"
#include "PrePagoException.h"

CDocumento::CDocumento(void)
{
    memset(&tDocumento, 0x00, sizeof(TDocumento));
    memset(&tDocumentoArr, 0x00, sizeof(TDocumentoArr));
}

CDocumento::~CDocumento(void)
{
    clDocumentopc.desaloca(&tDocumentoArr);
}

bool CDocumento::buscaDocumento(void)
{
    return(clDocumentopc.proCBuscaDocumento(&tDocumento));
}

bool CDocumento::buscaDocumento(TDocumento *ptDocumento)
{
    return(clDocumentopc.proCBuscaDocumento(ptDocumento));
}

bool CDocumento::buscaDocumentoChaveComposta(TDocumento *ptDocumento)
{
    return(clDocumentopc.proCBuscaDocumentoChaveComposta(ptDocumento));
}

void CDocumento::apagaDocumento(void)
{
    clDocumentopc.proCApagaDocumento(tDocumento);
}

void CDocumento::insereDocumento(void)
{
    clDocumentopc.proCInsereDocumento(&tDocumento);
}

void CDocumento::atualizaDocumento(void)
{
    clDocumentopc.proCAtualizaDocumento(tDocumento);
}

char *CDocumento::getIdDocumento(void)
{
    static char szAux[LEN_IDDOCUMENTO + LEN_EOS];

    strcpy(szAux, tDocumento.szIdDocumento);
    return szAux;
}

char *CDocumento::getNrDocumento(void)
{
    static char szAux[LEN_NRDOCUMENTO + LEN_EOS];

    strcpy(szAux, tDocumento.szNrDocumento);
    return szAux;
}

char *CDocumento::getIdTipoDocumento(void)
{
    static char szAux[LEN_IDTIPODOCUMENTO + LEN_EOS];

    strcpy(szAux, tDocumento.szIdTipoDocumento);
    return szAux;
}

void CDocumento::setIdPais(char *pszIdPais)
{
    strcpy(tDocumento.szIdPais, pszIdPais);
}

void CDocumento::setIdUf(char *pszIdUf)
{
    strcpy(tDocumento.szIdUf, pszIdUf);
}

void CDocumento::setIdDocumento(char *pszIdDocumento)
{
    strcpy(tDocumento.szIdDocumento, pszIdDocumento);
}

void CDocumento::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tDocumento.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CDocumento::setStruct(TDocumento *ptDocumento)
{
    memcpy(&tDocumento, ptDocumento, sizeof(TDocumento));
    memset(ptDocumento, 0x00, sizeof(TDocumento));
}

TDocumento* CDocumento::getRegistro( int iIndex )
{
	if( getQuantidade() > 0 )
	{
		iIndex = ( iIndex >= getQuantidade() ? getQuantidade()-1 : iIndex );
		iIndex = ( iIndex < 0 ? 0 : iIndex );
		return &tDocumentoArr.pztDocumento[iIndex];
	}
	return NULL;
}

void CDocumento::setIdPessoa(char *pszIdPessoa)
{
    strcpy(tDocumentoArr.idPessoa, pszIdPessoa);
}


bool CDocumento::buscaDocumentoPorIdPessoa( void )
{
	return clDocumentopc.proCBuscaDocumentoPorIdPessoa(&tDocumentoArr);
}
