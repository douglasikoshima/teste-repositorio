// AtendimentoNotaHistorico.h: interface for the AtendimentoNotaHistorico class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ATENDIMENTONOTAHISTORICO_H__3F4BDC90_AFEE_47A4_BB89_D3DECFC2EAE7__INCLUDED_)
#define AFX_ATENDIMENTONOTAHISTORICO_H__3F4BDC90_AFEE_47A4_BB89_D3DECFC2EAE7__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include<tuxfw.h>

struct stNotaHistorico{
	char idAtendimentoNotaHistorico[21+1];
	char idAtendimentoNota[21+1];
	char idOperacaoNotaAtendimento[21+1];
	char idPessoaUsuario[21+1];
	char dtObservacao[26];
	char dsObservacao[255+1];
	char idMotivo[21+1];
};

class AtendimentoNotaHistorico  
{
public:
	AtendimentoNotaHistorico();
	virtual ~AtendimentoNotaHistorico();
	int inserir(struct stNotaHistorico *buf,char*user);
	int remover(struct stNotaHistorico *buf);
};

#endif // !defined(AFX_ATENDIMENTONOTAHISTORICO_H__3F4BDC90_AFEE_47A4_BB89_D3DECFC2EAE7__INCLUDED_)
