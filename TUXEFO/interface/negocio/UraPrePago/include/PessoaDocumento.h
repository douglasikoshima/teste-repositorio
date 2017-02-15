// PessoaDocumento.h: interface for the PessoaDocumento class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PESSOADOCUMENTO_H__724264F7_D69C_44B4_B5D1_BC8CE95F657F__INCLUDED_)
#define AFX_PESSOADOCUMENTO_H__724264F7_D69C_44B4_B5D1_BC8CE95F657F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include"Pessoa.h"
#include"Documento.h"

class PessoaDocumento : public Pessoa  
{
public:
	PessoaDocumento();
	virtual ~PessoaDocumento();
	int getDocumentos(char*idPessoa,ListDocumento&listDocumento);
};

#endif // !defined(AFX_PESSOADOCUMENTO_H__724264F7_D69C_44B4_B5D1_BC8CE95F657F__INCLUDED_)
