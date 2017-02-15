// Pessoa.h: interface for the Pessoa class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PESSOA_H__DC803AD2_DD9D_47D9_9BBB_43B5B4E89CAE__INCLUDED_)
#define AFX_PESSOA_H__DC803AD2_DD9D_47D9_9BBB_43B5B4E89CAE__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include"Common.h"

class Pessoa  
{
public:
	Pessoa();
	virtual ~Pessoa();
	int getLinhasAtivas(stParametrosUren*param,ListPessoaLinha &listPessoaLinha);
	int getCanal(char*idCanal);
	int getDadosLinha(char*idLinhaTelefonica,char* idPessoaDepara);
public:
	// atributos
};

#endif // !defined(AFX_PESSOA_H__DC803AD2_DD9D_47D9_9BBB_43B5B4E89CAE__INCLUDED_)
