// AtendimentoConsultor.h: interface for the AtendimentoConsultor class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ATENDIMENTOCONSULTOR_H__97B35BC9_C01C_4A95_A859_E5E43C44F5D1__INCLUDED_)
#define AFX_ATENDIMENTOCONSULTOR_H__97B35BC9_C01C_4A95_A859_E5E43C44F5D1__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include <tuxfw.h>
#include <UtilWorkflow.h>

struct stData{
	char nmLoginUsuarioCTI[255+1];
	char idAtendimento[21+1];
	char nrLinha[21+1];
	char cdAreaRegistro[21+1];	
};

struct stPesquisaNotas{
	char idAtendimento[21+1];
	char dtAberturaIni[11];
	char dtAberturaFim[11];
	char nmUsuario[255+1];
	char reConsultor[255+1];
	char login[255+1];
	char idMotivo[21+1];
	char idOperacao[21+1];
	char idTipoNotaAtendimento[21+1];
	char idAtendimentoNota[21+1];
	char nrLinha[21+1];
	char idUsuario[21+1];
	char notaConsultor[2];
	char cliContatado[3];
	char cliTransferido[3];
	char flgInbox[2];
	int nrRegistros;
	int totalRegistros;
    char idAtendimentoProtocolo[38+1];
};

class AtendimentoConsultor  
{
public:
	AtendimentoConsultor();
	virtual ~AtendimentoConsultor();
	// busca  o consultor pelo processo
	int getConsultorByProcesso(struct stData *buf);
	// busca o consultor pela linha
	int getConsultorByLinha(struct stData *buf);
	// retorna o prazo de atendimento
	int getPrazoAtendimentoCRI(char*dsPrazo);
	// busca as notas para Inbox
	int buscarNotasInbox(struct stPesquisaNotas *buf,XMLGen*xml);
	// busca as notas para atendente
	int buscarNotasAtendimento(struct stPesquisaNotas *buf,XMLGen*xml);
	// buscar dados de uma nota
	int getDadosNota(struct stPesquisaNotas *buf,XMLGen*xml);
	// retorna a operação nota
	int getOperacaoNota(XMLGen*);
protected:
	// retorna o historico de uma nota
	int getNotaHistorico(char*,XMLGen*);
private:
	// atributos
	
};

#endif // !defined(AFX_ATENDIMENTOCONSULTOR_H__97B35BC9_C01C_4A95_A859_E5E43C44F5D1__INCLUDED_)
