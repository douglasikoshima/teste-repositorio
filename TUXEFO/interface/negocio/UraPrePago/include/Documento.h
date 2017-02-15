// Documento.h: interface for the Documento class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DOCUMENTO_H__D936692F_C67E_4A60_A898_4DF111C95474__INCLUDED_)
#define AFX_DOCUMENTO_H__D936692F_C67E_4A60_A898_4DF111C95474__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include"Util.h"
#include"../../commons/include/vectorlist.h"

class Documento  
{
public:
	Documento();
	virtual ~Documento();
	void setIdDocumento(char*);
	void setIdTipoDocumentoSelecionado(char*);
	void setNrDocumento(char*);
	void setDtExpedicao(char*);
	void setDsOrgaoEmissor(char*);
	void setIdUfSelecionado(char*);

	char* getIdDocumento();
	char* getIdTipoDocumentoSelecionado();
	char* getNrDocumento();
	char* getDtExpedicao();
	char* getDsOrgaoEmissor();
	char* getIdUfSelecionado();
private:
	char idDocumento[21+1];
	char idTipoDocumentoSelecionado[256];
	char nrDocumento[256];
	char dtExpedicao[15];
	char dsOrgaoEmissor[256];
	char idUfSelecionado[256];
};

typedef CVectorList<Documento> ListDocumento;

#endif // !defined(AFX_DOCUMENTO_H__D936692F_C67E_4A60_A898_4DF111C95474__INCLUDED_)
