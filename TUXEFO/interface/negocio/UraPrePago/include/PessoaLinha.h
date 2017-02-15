// PessoaLinha.h: interface for the PessoaLinha class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PESSOALINHA_H__5C61E8F2_D81E_46B5_BB2E_3E77EB5136F4__INCLUDED_)
#define AFX_PESSOALINHA_H__5C61E8F2_D81E_46B5_BB2E_3E77EB5136F4__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Pessoa.h"

class PessoaLinha : public Pessoa  
{
public:
	PessoaLinha();
	virtual ~PessoaLinha();
	int getLinha(char*nrLinha,char*cdAreaRegistro,stLinha&linha);
	int estadoLinha(char*nrLinha,char*cdAreaRegistro,char*sgClassificacao);
	int getPessoaLinha(char*nrLinha,char*cdAreaRegistro,char*idPessoa);
	int estadoLinhaTitularidade(char*nrLinha,char*cdAreaRegistro);
	int estadoLinhaAtivo(char*nrLinha,char*cdAreaRegistro);

};

#endif // !defined(AFX_PESSOALINHA_H__5C61E8F2_D81E_46B5_BB2E_3E77EB5136F4__INCLUDED_)
