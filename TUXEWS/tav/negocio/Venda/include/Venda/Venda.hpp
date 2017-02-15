// CVenda.hpp: interface for the CVenda class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_VENDA__
#define __C_VENDA__

#include <list>
using namespace std;

class CPessoaAbstract;

class CVenda
{
private:
	void  iniciaValores();

public:
	
	CVenda();
	CVenda(CPessoaAbstract *p);
	~CVenda();

	// Métodos de acesso aos atributos
 	// Getters
	int    getIdVenda();
	char  *getDataInicial(char *cDataInicial);
	char  *getDataFinal(char *cDataFinal);
	char  *getDataVenda(char *cDataVenda);
	char  *getBandeira(char *cBandeira);
	char  *getNrNsu(char *cNrNsu);
	char  *getNrCartao(char *cNrCartao);
	char  *getValor(char *Valor);
	char  *getNrSAP(char *cNrSAP);
	int    getIdTipo();
	char  *getTipo(char *cTipo);
	unsigned int getSitefNumDoc();
	char  *getDsTipoServico(char *cDsTipoServico);
	char  *getDsLoja(char *cDsLoja);
	char  *getNrTerminal(char *cNrTerminal);
	int    getCdAreaRegistro();
	int    getNrLinha();
	int    getInEstadoVenda();
	char   *getDsMsgErroVenda(char *cDsMsgErroVenda);
	char   *getNrOrdem(char *cNrOrdem);
	char   *getDtProcVenda(char *value);
	int    getNsuAutorizador();
	char  *getNsuHost();
	char  *getNsuHostAutorizador();
	void registrarProcessoSitef(char*idSitefVenda,char*operacao);
	void alterarStatusVenda(char*idSitefVenda,char*mensagemErro,char*idStatusVenda);


 	// Setters
	void   setIdVenda(int value);
	void   setDataInicial(char *value);
	void   setDataFinal(char *value);
	void   setDataVenda(char *value);
	void   setBandeira(char *value);
	void   setNrNsu(char *value);
	void   setNrCartao(char *value);
	void   setValor(char *value);
	void   setNrSAP(char *value);
	void   setIdTipo(int value);
	void   setTipo(char *value);
	void   setSitefNumDoc(unsigned int value);
	void   setDsTipoServico(char *value);
	void   setDsLoja(char *value);
	void   setNrTerminal(char *value);
	void   setCdAreaRegistro(int value);
	void   setNrLinha(int value);
	void   setInEstadoVenda(int value);
	void   setDsMsgErroVenda(char *value);
	void   setNrOrdem(char *value);
	void   setDtProcVenda(char *value);
	void   setNsuAutorizador(int value);
	void   setNsuHost(char *value);
	void   setNsuHostAutorizador(char*nsu);
	
	// Operação de Negocio (Interface)
	void   inserir();
	void   consultarVendas(list<CVenda> &listaVendas);
	void   consultarSitefNumDoc();
	void   consultarFinanceiro(list<CVenda> &listaVendas, int idPessoaDePara, int idTipoLinha, int idTipoRecarga, int idUFOperadora, int idGrupoOperadora, int idStatusVenda, int iIdTerminal);
	void   alteraEstadoVenda();

private:

	CPessoaAbstract *m_pPessoaAbstract;

	// Dados de Negocio
	int   m_iIdVenda;
	char  m_cDataInicial[32];
	char  m_cDataFinal[32];
	char  m_cDataVenda[32];
	char  m_cBandeira[16];
	char  m_cNrNsu[7];
	char  m_cNrCartao[21];
	char  m_cValor[32];
	char  m_cNrSAP[11];
	int   m_iIdTipo;
	char  m_cTipo[12];
	unsigned int m_iNumDoc;
	char  m_cDsTipoServico[20];
	char  m_cDsLoja[256];
	int	  m_iIdPessoaDePara;
	char  m_cNrTerminal[8+1];
	int   m_iCdAreaRegistro;
	int	  m_iNrLinha;
	int   m_iInEstadoVenda;
	char  m_cDsMsgErroVenda[500];
	char  m_cNrOrdem[21];
	int   m_nsuAutorizador;
	char  m_dtProcVenda[30];
	char  m_nsuHostAutorizador[16];
	char  m_cNsuHost[17];

    // Métodos de acesso a banco de dados
	void  inserirDB();
	void  carregarDadosVendasDB(list<CVenda> &listaVendas);
	void  carregarSitefNumDoc();
	void  carregarDadosFinanceiroDB(list<CVenda> &listaVendas, int idPessoaDePara, int idTipoLinha, int idTipoRecarga, int idUFOperadora, int idGrupoOperadora, int idStatusVenda, int iIdTerminal);
	void  alteraEstadoVendaDB();
};

#endif // __C_VENDA__
