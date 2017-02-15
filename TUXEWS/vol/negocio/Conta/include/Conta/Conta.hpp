// CConta.hpp: interface for the CConta class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_CONTA__
#define __C_CONTA__

#include <Util/Util.hpp>

#include <vector>
#include <list>
using namespace std;

class CConta
{

public:

	CConta();
	~CConta();

	// Métodos de acesso aos atributos
 	// Getters
	int   getIdConta();
	int   getIdTipoConta();
	int   getIdSistemaOrigem();
	int   getIdPessoaEnderecoAssociado();
	int   getIdTipoEnderecoCobranca();
	int   getUser();
	char *getCdConta(char *cCdConta);
	
	list<CStr> &getListaLinhas();

 	// Setters
	void  setIdConta(int value);
	void  setIdTipoConta(int value);
	void  setIdSistemaOrigem(int value);
	void  setIdPessoaEnderecoAssociado(int value);
	void  setIdTipoEnderecoCobranca(int value);
	void  setUser(int value);
	void  setCdConta(char *value);
			
	// Operação de Negocio (Interface)
	void   inserirAssociacaoEndereco();
	void   excluirAssociacaoEndereco();

private:

	// Dados de Negocio
	int   m_iIdConta;
	int   m_iIdTipoConta;
	int   m_iIdSistemaOrigem;
	int   m_iIdPessoaEnderecoAssociado;
	int   m_iIdTipoEnderecoCobranca;
	int   m_iUser;
	char  m_cCdConta[101];
	
	list<CStr> listaLinhas; 

    // Métodos de acesso a banco de dados
	void  inserirAssociacaoEnderecoDB();
	void  excluirAssociacaoEnderecoDB();
};

#endif // __C_CONTA__
