// Servico.h: interface for the Servico class.
//
//////////////////////////////////////////////////////////////////////
#if !defined(AFX_SERVICO_H__7023986E_2B52_490A_BFAE_8F2746459C66__INCLUDED_)
#define AFX_SERVICO_H__7023986E_2B52_490A_BFAE_8F2746459C66__INCLUDED_

#include "../../commons/include/vectorlist.h"
#define MAX_SIZE 256

class Servico{
public:
	Servico();
	Servico(char*nome,char*valor);
	~Servico();
	void setNome(char*nome);
	void setValor(char*valor);
	char*getNome();
	char*getValor();
private:
	char m_nome[MAX_SIZE];
	char m_valor[MAX_SIZE];
};

typedef CVectorList<Servico> ListServicos;

#endif // !defined(AFX_SERVICO_H__7023986E_2B52_490A_BFAE_8F2746459C66__INCLUDED_)