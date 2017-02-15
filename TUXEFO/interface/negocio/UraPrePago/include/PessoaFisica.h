// PessoaFisica.h: interface for the PessoaFisica class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PESSOAFISICA_H__37C26D06_F550_4446_AF68_B872CC138239__INCLUDED_)
#define AFX_PESSOAFISICA_H__37C26D06_F550_4446_AF68_B872CC138239__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Pessoa.h"

class PessoaFisica : public Pessoa  
{
public:
	PessoaFisica();
	virtual ~PessoaFisica();
	int getDadosPessoa(char*idPessoa,stPessoaFisica&pessoaFisica);
	int getSexoBySigla(char*sgSexo,char*idSexo);
private:
};

#endif // !defined(AFX_PESSOAFISICA_H__37C26D06_F550_4446_AF68_B872CC138239__INCLUDED_)
