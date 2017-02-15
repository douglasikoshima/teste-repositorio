// PessoaEndereco.h: interface for the PessoaEndereco class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PESSOAENDERECO_H__57DF3458_A1CF_4B18_9A57_685F00635B83__INCLUDED_)
#define AFX_PESSOAENDERECO_H__57DF3458_A1CF_4B18_9A57_685F00635B83__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include"Pessoa.h"
#include"EnderecoPrePago.h"

class PessoaEndereco : public Pessoa  
{
public:
	PessoaEndereco();
	virtual ~PessoaEndereco();
	int listEnderecos(char *idPessoa,ListEnderecoPrePago&listEnderecoPrePago);
	int verificaEndereco(char*nrCep,char*logradouro,ListEnderecoPrePago&listEnderecoPrePago);
	int getPais(char*sgPais,char*idPais);
	int getUF(char*sgUF,char*idUF);
	int getTipoEndereco(char*idTipoEndereco);
	void consultarEnderecosDB(char*idPessoa,ListEnderecoPrePago&listEnderecoPrePago);
};

#endif // !defined(AFX_PESSOAENDERECO_H__57DF3458_A1CF_4B18_9A57_685F00635B83__INCLUDED_)
