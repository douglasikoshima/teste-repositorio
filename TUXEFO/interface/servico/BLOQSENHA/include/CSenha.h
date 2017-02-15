// CSenha.h: interface for the CSenha class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_CSENHA_H__2452975F_1FB1_424D_95D8_84124A0B5F1F__INCLUDED_)
#define AFX_CSENHA_H__2452975F_1FB1_424D_95D8_84124A0B5F1F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include<tuxfw.h>
#include"../../../negocio/commons/include/vectorlist.h"
#include<time.h>

struct stPessoaLinha{
	char idPessoa[21+1];
	char idPessoaLinha[21+1];
	char idPessoaDePara[21+1];
	char idTipoRelacionamento[21+1];
	char cdSenha[255+1];
	char idSenha[21+1];
};

class CSenha  
{
public:
	CSenha();
	virtual ~CSenha();
	int execute(char*linha,char*ddd);
private:
	int updateSenha();
	int insertHistoricoSenha(stPessoaLinha*pessoa);
	int bloquearSenha(stPessoaLinha*pessoa);
	int AtualizaSenha(stPessoaLinha*pessoa);
	bool verificaLinha();
	void formatDate(char*cdata);
private:
	CVectorList<stPessoaLinha> m_lista;
	char m_linha[21+1];
	char m_ddd[21+1];
};

#endif // !defined(AFX_CSENHA_H__2452975F_1FB1_424D_95D8_84124A0B5F1F__INCLUDED_)
