// Pessoa.h: interface for the CPessoa class.
//
//////////////////////////////////////////////////////////////////////

#include "Senha.hpp"
#include "Endereco.hpp"
#include "Util.hpp"

#include <list>
using namespace std;

#ifndef CRELACIONAMENTO
#define CRELACIONAMENTO

class CRelacionamento  {
	
public:
	CRelacionamento();
    virtual ~CRelacionamento();

	// Metodo da Classe Pessoa para atender pedido de Instanciação de outras classes
	// que possue associação com CPessoa.
    //static CRelacionamento* createInstance();


	// Métodos de acesso aos atributos
 	// Getters
	char * getDsOperacao(void);
	char * getDtRelacionamento(void);
	char * getHrRelacionamento(void);
	int getIdCanal(void);
	char * getDsCanal(void);
	
 
	//Setters
	void setDsOperacao(char *);
	void setDtRelacionamento(char *);
	void setHrRelacionamento(char *);
	void setIdCanal(int);
	void setDsCanal(char *);
	
	//Setters Para Pesquisa
	void setDtRelacionamentoInicio(char *);
	void setDtRelacionamentoFinal(char *);
	void setQtdRelac(int);
	void setCanalPesquisa(char *);
	
	//Getters Para Pesquisa
	char * getDtRelacionamentoInicio(void);
	char * getDtRelacionamentoFinal(void);
	int getQtdRelac(void);
	char * getCanalPesquisa(void);

	// Operação de Negocio (Interface)
	void consultarUltimosRelacionamentos( int, list < CRelacionamento > & lstRelac );
	void consultarUltimosRelacionamentosCanal( int, list < CRelacionamento > & lstRelac );
	void consultarRelacionamentosPeriodo( int, list < CRelacionamento > & lstRelac );
	
private:
  
	//Atributos de Negocio de Pesquisa
	char  m_cDtRelacionamentoInicio[8+1];
	char  m_cDtRelacionamentoFinal[8+1];
	char  m_cCanalPesquisa[8+1];
	int   m_QtdRelac;

	// Atributos de Negocio Saída
	/*Ver o tamanho certo destes camaradas*/
 	char  m_cDsOperacao[255];
	char  m_cDtRelacionamento[10+1];
	char  m_cHrRelacionamento[8+1];
	int   m_iIdCanal;
	char  m_cDsCanal[255];


	// Métodos de acesso a banco de dados
 	void consultarRelacionamentosPeriodoDataDB(int idCliente, list < CRelacionamento > & lstRelac);
	void consultarUltimosRelacionamentosDB(int idCliente, list < CRelacionamento > & lstRelac);
	void consultarUltimosRelacionamentosCanalDB(int idCliente, list < CRelacionamento > & lstRelac);
};
#endif		// !defined( CRELACIONAMENTO )

