// PessoaComunicacao.h: interface for the PessoaComunicacao class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PESSOACOMUNICACAO_H__FFC91B79_F1E8_4E39_BD9C_E108270044AE__INCLUDED_)
#define AFX_PESSOACOMUNICACAO_H__FFC91B79_F1E8_4E39_BD9C_E108270044AE__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Pessoa.h"
#include "ListaContato.h"

class PessoaComunicacao : public Pessoa  
{
public:
	PessoaComunicacao();
	virtual ~PessoaComunicacao();
	int listPessoaComunicacao(char*idPessoa,ListListaContato&listListaContato);

};

#endif // !defined(AFX_PESSOACOMUNICACAO_H__FFC91B79_F1E8_4E39_BD9C_E108270044AE__INCLUDED_)
