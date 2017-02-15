///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Documento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "../include/Documento.h"
#include "../include/Documentopc.h"
#include "../include/TString.h"

CDocumento::CDocumento(void)
{
	memset(&tDocumento, 0x00, sizeof(TDocumento));
}

bool CDocumento::buscaDocumento(void)
{
	return(clDocumentopc.proCBuscaDocumento(&tDocumento));
}

bool CDocumento::buscaDocumento(TDocumento *ptDocumento)
{
	return(clDocumentopc.proCBuscaDocumento(ptDocumento));
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

TString CDocumento::getIdDocumento(void)
{
    TString ret; 

    ret = tDocumento.szIdDocumento; 
    return ret;
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

TString CDocumento::unmask(char *doc)
{
   TString s = doc; 
   s.getNumber(); 
   return s;
}

void CDocumento::setCpf(char *cpf)
{
	TString s = unmask(cpf); 
	TString r; 
	TString d; 

	r = s.SubString( 1, s.Lenght() - 2 ).Trim(); 
	d = s.SubString( s.Lenght() - 1, 2 ); 

	strcpy ( tDocumento.szCdCpfCnpjBase		, r.c_str() ); 
    strcpy ( tDocumento.szCdCpfCnpjControle	, d.c_str() ); 
}


void CDocumento::setData(TPessoaJuridicaXML xm)
{
    sprintf( tDocumento.szIdTipoDocumento, "%d", ID_CNPJ );
    strcpy(tDocumento.szNrDocumento,		xm.dsCNPJ ); 
    strcpy(tDocumento.szIdDocumento,		xm.idCNPJ ); 
    strcpy(tDocumento.szCdCpfCnpjBase,		xm.dsCNPJ ); 
	strcpy(tDocumento.szCdCnpjFilial,		"1" ); 
	setIdUf(xm.idCNPJUF); 
	setIdPais("1"); 
}

void CDocumento::setData(TListaDocumento xm)
{
	TString s; 
    strcpy(tDocumento.szIdTipoDocumento,	xm.idTipoDocumento );

	if ( *xm.idTipoDocumento == '1' )
		  setCpf(xm.dsDocumento);

    strcpy(tDocumento.szNrDocumento,		xm.dsDocumento ); 
    strcpy(tDocumento.szIdDocumento,		xm.idDocumento ); 
	strcpy(tDocumento.szSgOrgaoExpedidor,	xm.dsOrgaoEmissor ); 
	strcpy(tDocumento.szDtEmissao,			xm.dtExpedicao ); 
	setIdUf(xm.idDocumentoUF); 
	setIdPais("1"); 

}

int CDocumento::isGood()
{
	 return strlen(tDocumento.szNrDocumento);  
}
