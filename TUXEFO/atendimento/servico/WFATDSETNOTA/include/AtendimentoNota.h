// AtendimentoNota.h: interface for the AtendimentoNota class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ATENDIMENTONOTA_H__C9975AC0_54A1_4CC2_BFDD_D2E118C15FD8__INCLUDED_)
#define AFX_ATENDIMENTONOTA_H__C9975AC0_54A1_4CC2_BFDD_D2E118C15FD8__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include "tuxfw.h"
#include "string.h"

struct stNota{
	char idAtendimentoNota[21+1];
	char idAtendimento[21+1];
	char idTipoNota[21+1];
	char idPessoaUsuario[21+1];
	char nmLoginUsuarioCTI[255+1];
	char comentario[1500+1];
};

class AtendimentoNota  
{
public:
	AtendimentoNota();
	virtual ~AtendimentoNota();
public:
	int criarNota(stNota*nota);
	int editarNota(stNota*nota);
	int verificaProcesso(stNota*nota);
	int verificaProcessoCRI(stNota*nota);
	int getIdPessoaUsuario(char*nmLoginUsuarioCTI,char*idPessoaUsuario);
	int getUsuarioByProcesso(char*idAtendimento,char*idPessoaUsuario);

};

#endif // !defined(AFX_ATENDIMENTONOTA_H__C9975AC0_54A1_4CC2_BFDD_D2E118C15FD8__INCLUDED_)
