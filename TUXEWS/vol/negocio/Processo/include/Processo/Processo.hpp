// Pessoa.h: interface for the CPessoa class.
//
//////////////////////////////////////////////////////////////////////

#include <Senha/Senha.hpp>
#include <Endereco/Endereco.hpp>
#include <Util/Util.hpp>

#include <list>
using namespace std;

#ifndef CPROCESSO
#define CPROCESSO

class CProcesso  {
	
public:
	CProcesso();
    virtual ~CProcesso();

	// Metodo da Classe Pessoa para atender pedido de Instanciação de outras classes
	// que possue associação com CPessoa.
    //static CRelacionamento* createInstance();

    
	// Métodos de acesso aos atributos
 	// Getters
	int getIdAtendimentoProc(void);
	char * getDsAtendimentoProc(void);
	char * getDtAberturaProc(void);
	char * getDtConclusaoProc(void);
	char * getHrAberturaProc(void);
	char * getDsStatusProc(void);
	char * getHrConclusaoProc(void);
 
	//Setters
	void setIdAtendimentoProc(int);
	void setDsAtendimentoProc(char *);
	void setDtAberturaProc(char *);
	void setHrAberturaProc(char *);
	void setDtConclusaoProc(char *);
	void setHrConclusaoProc(char *);
	void setDsStatusProc(char *);
	
	//Setters Para Pesquisa
	void setDtProcessoInicio(char *);
	void setDtProcessoFinal(char *);
	void setQtdProcessos(int);
	
	
	//Getters Para Pesquisa
	char * getDtProcessoInicio(void);
	char * getDtProcessoFinal(void);
	int    getQtdProcessos(void);
	
				

	// Operação de Negocio (Interface)
	void consultarUltimosProcessos( int, list < CProcesso > & lstRelac );
	void consultarRangeDateProcessos( int, list < CProcesso > & lstRelac );
	
private:
  
	//Atributos de Negocio de Pesquisa
	char  m_cDtProcessoInicio[32];
	char  m_cDtProcessoFinal[32];
	int   m_QtdProcessos;

	// Atributos de Negocio Saída
	/*Ver o tamanho certo destes camaradas*/
	int m_cIdAtendimento;
	char  m_cDsAtendimento	[ 256 ];
	char  m_cDtAbertura		[ 10+1 ];
	char  m_cHrAbertura		[ 8+1 ];
	char  m_cDtConclusao	[ 10+1 ];
	char  m_cHrConclusao	[ 8+1 ];
	char  m_cDsStatus		[ 256 ];


	// Métodos de acesso a banco de dados
 	void consultarProcessosPeriodoDataDB(int idCliente, list < CProcesso > & lstRelac);
	void consultarUltimosProcessosDB(int idCliente, list < CProcesso > & lstRelac);
	
};
#endif		// !defined( CRELACIONAMENTO )

