// Pessoa.h: interface for the CPessoa class.
//
//////////////////////////////////////////////////////////////////////

#include <Senha/Senha.hpp>
#include <Endereco/Endereco.hpp>
#include <Util/Util.hpp>

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
	int    getIdCanal(void);
	char * getDsCanal(void);
	int    getIdContato(void);
	int    getCdAreaRegistro(void);
	int    getNrLinha(void);

	
	int	   getQtVOLPreCliente(void);
	int	   getQtVOLPreUsuario(void);
	int    getQtVOLPosCliente(void);
	int	   getQtVOLPosUsuario(void);
	int	   getQtTAVPreCliente(void);
	int	   getQtTAVPreUsuario(void);
	int	   getQtTAVPosCliente(void);
	int	   getQtTAVPosUsuario(void);

	int    getQtVOLControleCliente(void);
	int    getQtVOLControleUsuario(void);
	int    getQtTAVControleCliente(void);
	int    getQtTAVControleUsuario(void);

	/**********Nova Tecnologia ************/

	int	   getQtVOLPreGsmCliente(void);
	int	   getQtVOLPreGsmUsuario(void);
	int    getQtVOLPosGsmCliente(void);
	int	   getQtVOLPosGsmUsuario(void);
	int	   getQtTAVPreGsmCliente(void);
	int	   getQtTAVPreGsmUsuario(void);
	int	   getQtTAVPosGsmCliente(void);
	int	   getQtTAVPosGsmUsuario(void);

    int	   getQtVOLControleGsmCliente(void);
	int    getQtVOLControleGsmUsuario(void);
	int    getQtTAVControleGsmCliente(void);
	int    getQtTAVControleGsmUsuario(void);

	/********************************/

	float  getQtVOL(void);
	float  getQtTAV(void);

	int    getQtURA(void);
	int	   getIdTipoHistoricoSenha(void);
	char * getDsTipoHistoricoSenha(void);
	int	   getNuNivel(void);
	long   getQtTotalClientes(void);
	long   getIdTempoSessao(void);
	int	   getInTimeout(void);
	char *   getTmVOLPreCliente(void);
	char *   getTmVOLPreUsuario(void);
	char *   getTmVOLPosCliente(void);
	char *   getTmVOLPosUsuario(void);
	char *   getTmTAVPreCliente(void);
	char *   getTmTAVPreUsuario(void);
	char *   getTmTAVPosCliente(void);
	char *   getTmTAVPosUsuario(void);

	char *   getTmTAVControleCliente(void);
	char *   getTmTAVControleUsuario(void);
	char *   getTmVOLControleCliente(void);
	char *   getTmVOLControleUsuario(void);

	char *   getTmTotalUtilizacao(void);
	char *	 getNrIP(void);

	char *   getNmLoja(void);
	char *   getNmTerminal(void);


 
	//Setters
	void setDsOperacao(char *);
	void setDtRelacionamento(char *);
	void setHrRelacionamento(char *);
	void setIdCanal(int);
	void setDsCanal(char *);
	void setIdContato(int);
	void setCdAreaRegistro(int);
	void setNrLinha(int);



	void setQtVOLPreCliente(int);
	void setQtVOLPreUsuario(int);
	void setQtVOLPosCliente(int);
	void setQtVOLPosUsuario(int);
	void setQtTAVPreCliente(int);
	void setQtTAVPreUsuario(int);
	void setQtTAVPosCliente(int);
	void setQtTAVPosUsuario(int);

	void setQtVOLControleCliente(int);
	void setQtVOLControleUsuario(int);
	void setQtTAVControleCliente(int);
	void setQtTAVControleUsuario(int);


	/*************Nova Tecnologia **************/

	void setQtVOLPreGsmCliente(int);
	void setQtVOLPreGsmUsuario(int);
	void setQtVOLPosGsmCliente(int);
	void setQtVOLPosGsmUsuario(int);
	void setQtTAVPreGsmCliente(int);
	void setQtTAVPreGsmUsuario(int);
	void setQtTAVPosGsmCliente(int);
	void setQtTAVPosGsmUsuario(int);

	void setQtVOLControleGsmCliente(int);
	void setQtVOLControleGsmUsuario(int);
	void setQtTAVControleGsmCliente(int);
	void setQtTAVControleGsmUsuario(int);


	void setNmLoja(char *);
	void setNmTerminal(char *);

	/*********************************************/

	void setQtVOL(float);
	void setQtTAV(float);

	void setQtURA(int);
	void setIdTipoHistoricoSenha(int);
	void setDsTipoHistoricoSenha(char *);
	void setNuNivel(int);
	void setQtTotalClientes(long);
	void setIdTempoSessao(long);
	void setInTimeout(int);
	void   setTmVOLPreCliente(char *);
	void   setTmVOLPreUsuario(char *);
	void   setTmVOLPosCliente(char *);
	void   setTmVOLPosUsuario(char *);
	void   setTmTAVPreCliente(char *);
	void   setTmTAVPreUsuario(char *);
	void   setTmTAVPosCliente(char *);
	void   setTmTAVPosUsuario(char *);
	void   setTmTAVControleCliente(char *);
	void   setTmTAVControleUsuario(char *);
	void   setTmVOLControleCliente(char *);
	void   setTmVOLControleUsuario(char *);

	void   setTmTotalUtilizacao(char *);
	void   setNrIP(char *);
	
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
	void consultarUltimosRelacionamentos( long, list < CRelacionamento > & lstRelac );
	void consultarUltimosRelacionamentosCanal( long, list < CRelacionamento > & lstRelac );
	void consultarRelacionamentosPeriodo( long, list < CRelacionamento > & lstRelac );

	void registraContato(XMLGen &xmlInput, char *cUsuario);

	void consultarAcessoPorDia(list < CRelacionamento > & lstRelac, int iIdTipoHistorico, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarAcessoPorDiaLoja(list< CRelacionamento > & lstRelac, int iIdTipoHistorico, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal);



	void consultarAcessoPorHora(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarAcessoPorHoraLoja(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal);
	void consultarAcessoNegado(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarAcessoNegadoLoja(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal);
	
	void consultarServicosEfetuados(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarServicosEfetuadosURA(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarServicosEfetuadosLoja(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdGrupoOperadoraTerminal, int iIdUFOperadoraTerminal,int iIdPessoaDePara, int iIdTerminal);

	void consultarServicosDisponiveis(list < CRelacionamento > & lstRelac);
	void consultarServicosDisponiveisURA(list < CRelacionamento > & lstRelac);
	void consultarPrimeiroAcesso(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha);
	void consultarTotalClientes(int iIdCanal);
	
	void consultarAcessoIncidencia(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iIdTecnologia, int iQtIntervaloInicial, int iQtIntervaloFinal);
	void consultarAcessoIncidenciaLoja(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal, int iQtIntervaloInicial, int iQtIntervaloFinal);

	void registrarLoginSessao(int iCdAreaRegistro, int iNrLinha, int iIdTipoRelacionamento);
	void registrarLogoutSessao(void);
	void consultarTempoSessao(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iIdTecnologia, int iQtIntervaloInicial, int iQtIntervaloFinal);

	void consultarTempoSessaoLoja(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal, int iQtIntervaloInicial, int iQtIntervaloFinal);

	void registrarIP(void);
	void consultarIP(void);
	void remoteCall(char* nomeServico, char* sMsgIn, int lFlags);
	char *itoa(int i,char *dest,int /*radix*/);
	bool consultarUsoServicoVIP(int iNrLinha, int iCdAreaRegistro);
	void consultarCampanhaVip (int iIdUFOperadora, int iIdGrupoOperadora, int iCdAreaRegistro, int iNrLinha, int inAtivadoEnvioMail, XMLGen* xml_g);
	
private:
  
	//Atributos de Negocio de Pesquisa
	char  m_cDtRelacionamentoInicio[10+1];
	char  m_cDtRelacionamentoFinal[10+1];
	char  m_cCanalPesquisa[8+1];
	int   m_QtdRelac;

	int   m_iCdAreaRegistro;
	int   m_iNrLinha;

	// Atributos de Negocio Saída
	/*Ver o tamanho certo destes camaradas*/
 	char  m_cDsOperacao[256];
	char  m_cDtRelacionamento[10+1];
	char  m_cHrRelacionamento[8+1];
	int   m_iIdCanal;
	char  m_cDsCanal[255];
	int   m_iIdContato;
	int	  m_iQtVOLPreCliente;
	int	  m_iQtVOLPreUsuario;
	int   m_iQtVOLPosCliente;
	int	  m_iQtVOLPosUsuario;
	int	  m_iQtTAVPreCliente;
	int	  m_iQtTAVPreUsuario;
	int	  m_iQtTAVPosCliente;
	int	  m_iQtTAVPosUsuario;

	int   m_iQtVOLControleCliente;
	int   m_iQtVOLControleUsuario;
	int   m_iQtTAVControleCliente;
	int   m_iQtTAVControleUsuario;

	/*************nova tecnologia *************/

	int	  m_iQtVOLPreGsmCliente;
	int	  m_iQtVOLPreGsmUsuario;
	int   m_iQtVOLPosGsmCliente;
	int	  m_iQtVOLPosGsmUsuario;
	int	  m_iQtTAVPreGsmCliente;
	int	  m_iQtTAVPreGsmUsuario;
	int	  m_iQtTAVPosGsmCliente;
	int	  m_iQtTAVPosGsmUsuario;

	int   m_iQtVOLControleGsmCliente;
	int   m_iQtVOLControleGsmUsuario;
	int   m_iQtTAVControleGsmCliente;
	int   m_iQtTAVControleGsmUsuario;

	/*******************************************/


	float m_iQtVOL;
	float m_iQtTAV;
	int	  m_iQtURA;
	int	  m_iIdTipoHistoricoSenha;
	char  m_cDsTipoHistoricoSenha[255];
	int	  m_iNuNivel;
	long  m_lQtTotalClientes;
	long  m_lIdTempoSessao;
	int	  m_iInTimeout;
	char  m_cTmVOLPreCliente[24];
	char  m_cTmVOLPreUsuario[24];
	char  m_cTmVOLPosCliente[24];
	char  m_cTmVOLPosUsuario[24];
	char  m_cTmTAVPreCliente[24];
	char  m_cTmTAVPreUsuario[24];
	char  m_cTmTAVPosCliente[24];
	char  m_cTmTAVPosUsuario[24];
	char  m_cTmTAVControleCliente[24];
    char  m_cTmTAVControleUsuario[24];
    char  m_cTmVOLControleCliente[24];
    char  m_cTmVOLControleUsuario[24];



	char  m_cTmTotalUtilizacao[24];
	char  m_cNrIP[256];
	char  m_cNmLoja[256];
	char  m_cNmTerminal[256];


	// Métodos de acesso a banco de dados
 	void consultarRelacionamentosPeriodoDataDB(long idCliente, list < CRelacionamento > & lstRelac);
	void consultarUltimosRelacionamentosDB(long idCliente, list < CRelacionamento > & lstRelac);

	void consultarUltimosRelacionamentosTelaInicialDB(long idCliente, list < CRelacionamento > & lstRelac);
	void consultarUltimosRelacionamentosCanalDB(long idCliente, list < CRelacionamento > & lstRelac);

	void consultarAcessoPorDiaDB(list < CRelacionamento > & lstRelac, int iIdTipoHistorico, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarAcessoPorDiaLojaDB(list < CRelacionamento > & lstRelac, int iIdTipoHistorico, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdTerminal, int iIdPessoaDePara);
	void consultarAcessoPorDiaAgrupadoDB(list < CRelacionamento > & lstRelac, int iIdTipoHistorico, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia ); 
	void consultarAcessoPorDiaLojaAgrupadoDB(list < CRelacionamento > & lstRelac, int iIdTipoHistorico, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdTerminal, int iIdPessoaDePara);
	void consultarAcessoPorHoraDB(list < CRelacionamento > & lstRelac, int idTipoCarteira, int idTipoRelacionamento, int idTipoLinha, int idUFOperadora, int idSegmentacao, int idGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIdTecnologia);
	void consultarAcessoPorHoraLojaDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal);

	
	void consultarAcessoPorHoraLojaAgrupadoDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal);
	

	void consultarAcessoNegadoDB(list < CRelacionamento > & lstRelac, int idTipoCarteira, int idTipoRelacionamento, int idTipoLinha, int idUFOperadora, int idSegmentacao, int idGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarAcessoNegadoAgrupadoDB(list < CRelacionamento > & lstRelac, int idTipoCarteira, int idTipoRelacionamento, int idTipoLinha, int idUFOperadora, int idSegmentacao, int idGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);

	void consultarAcessoNegadoLojaDB(list < CRelacionamento > & lstRelac, int idTipoCarteira, int idTipoRelacionamento, int idTipoLinha, int idUFOperadora, int idSegmentacao, int idGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal);
	void consultarAcessoNegadoLojaAgrupadoDB(list < CRelacionamento > & lstRelac, int idTipoCarteira, int idTipoRelacionamento, int idTipoLinha, int idUFOperadora, int idSegmentacao, int idGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal);
	

	void consultarServicosEfetuadosDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarServicosEfetuadosAgrupadoDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);

	
	void consultarServicosEfetuadosURADB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);
	void consultarServicosEfetuadosAgrupadoURADB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia);

	
	void consultarServicosEfetuadosLojaDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdUFOperadoraTerminal, int iIdGrupoOperadoraTerminal, int iIdPessoaDePara, int iIdTerminal);
	void consultarServicosEfetuadosLojaAgrupadoDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoRelacionamento, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha, int iIsAgrupado, int iIdTecnologia, int iIdUFOperadoraTerminal, int iIdGrupoOperadoraTerminal, int iIdPessoaDePara, int iIdTerminal);
	

	void consultarServicosDisponiveisDB(list < CRelacionamento > & lstRelac);
	void consultarServicosDisponiveisURADB(list < CRelacionamento > & lstRelac);	
	void consultarPrimeiroAcessoDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iCdAreaRegistro, int iNrLinha);
	void consultarTotalClientesDB(int iIdCanal);
	void consultarAcessoIncidenciaDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iIdTecnologia, int iQtIntervaloInicial, int iQtIntervaloFinal);

	void consultarAcessoIncidenciaLojaDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal,  int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal, int iQtIntervaloInicial, int iQtIntervaloFinal);

	void registrarLoginSessaoDB(int iCdAreaRegistro, int iNrLinha, int iIdTipoRelacionamento);
	void registrarLogoutSessaoDB(void);
	
	void consultarTempoSessaoDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iIdTecnologia, int iQtIntervaloInicial, int iQtIntervaloFinal);
	void consultarTempoSessaoLojaDB(list < CRelacionamento > & lstRelac, int iIdTipoCarteira, int iIdTipoLinha, int iIdUFOperadora, int iIdSegmentacao, int iIdGrupoOperadora, int iIdCanal, int iIdTecnologia, int iIdPessoaDePara, int iIdTerminal, int iQtIntervaloInicial, int iQtIntervaloFinal);

	void registrarIPDB(void);
	void consultarIPDB(void);
	void setaQteTotalVOLTAV(char *,int iIdCanal, int iIdTipoLinha, int iIdTipoRelacioamento, int iTotal);

	void setaNmLojaTerminal(char *cNmLoja, char *cNmTerminal);

	void reiniciaValores( int iValor);
	void MontaPesquisaLiteral(char * ,int , int, int ,int ,int ,int ,int ,int ,int ,int, int , int, int ,char *,char *);
};
#endif		// !defined( CRELACIONAMENTO )

