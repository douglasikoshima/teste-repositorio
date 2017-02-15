// CLojista.hpp: interface for the CLojista class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_LOJISTA__
#define __C_LOJISTA__

#include <tuxfw/tuxfw.h>

#include <list>
using namespace std;


#define ERR_NRTERMINAL_EXISTENTE				1
#define ERR_EXISTE_OUTRO_TERMIMNAL_RECARGA		2
#define ERR_IP_EXISTENTE						3
#define NO_ERROR								0
#define ERR_EXISTE_HISTORICO_RECARGA			4
#define ERR_TERMINAL_CONFIGURADO_RECARGA		5
#define ERR_EXISTE_HISTORICO_ATENDIMENTO		6

class CVenda;

class CLojista: public CPessoaAbstract
{

public:

	CLojista();
	~CLojista();

	// Métodos de acesso aos atributos
 	// Getters
	int     getIdTerminal() const;
	int     getIdUFOperadora() const;
	int     getIdUFOperadoraLinha() const;
	char   *getCdLojaOperadoraCartao(char *cCdLojaOperadoraCartao) const;
	char   *getNrTerminal(char *cNrTerminal) const;
	char   *getNrIpTerminal(char *cNrIpTerminal) const;
	char   *getSgUF(char *cSgUF) const;
	char   *getDsCor(char *dsCor) const;
	int     getIdGrupoOperadora() const;
	int     getIdGrupoOperadoraLinha() const;
	int		getInSitefAtivoRecarga() const;
	int		getInSitefAtivoPagamento() const;
	int		getIdUF();
	char   *getNmAreaRegistro();
	char   *getNmMunicipio();
	char   *getNmLoja();
	char   *getNmTerminal();
	int		getInLiberadoRecarga();
	int		getInLiberadoPagamento();
	char   *getCdSitefSenha();
	int     getIdCor();
	char   *getNmBairro();
	char   *getNmTipoLogradouro();	
	char   *getNrEndereco();
	char   *getDsEnderecoComplemento();
	char   *getNrCep();
	char   *getNmLogradouro();
	char   *getNmUF();
	char   *getNmTituloLogradouro();
	int		getIdPessoa();
	char   *getDsOperadora();
	long	getIdPessoaDeParaLoja();

	

	CVenda *getVenda();

 	// Setters
	void  setIdTerminal(int value);
	void  setIdUFOperadora(int value);
	void  setIdUFOperadoraLinha(int value);
	void  setCdLojaOperadoraCartao(char *value);
	void  setNrTerminal(char *value);
	void  setNrIpTerminal(char *value);
	void  setSgUF(char *value);
	void  setIdGrupoOperadora(int value);
	void  setIdGrupoOperadoraLinha(int value);
	void  setDsCor(char*value);
	void  setInSitefAtivoRecarga(int value);
	void  setInSitefAtivoPagamento(int value);
	void  setIdUF(int value);
	void  setNmAreaRegistro(char *value);
	void  setNmMunicipio(char *value);
	void  setNmLoja(char *value);
	void  setNmTerminal(char *value);
	void  setInLiberadoRecarga(int value);		
	void  setInLiberadoPagamento(int value);
	void  setCdSitefSenha(char *value);
	void  setIdCor(int value);
	void  setNmBairro(char *value);
	void  setNmTipoLogradouro(char *value);
	void  setNmTituloLogradouro(char *value);
	void  setDsEnderecoComplemento(char *value);
	void  setNrCep(char *value);
	void  setNmLogradouro(char *value);
	void  setNmUF(char *value);
	void  setNrEndereco(char *value);
	void  setIdPessoa (int value);	
	void  setDsOperadora (char *value);
	void  setIdPessoaDeParaLoja(long value);
	void  registrarLog (int iIdUser, char *cNmMunicipio, char *cDsAcao);
	// Operação de Negocio (Interface)
	void  consultarDados();
	void  consultarRegional(int cdAreaRegistro);
	void  constularLojas(list<CLojista> &listaLoja);
	void  consultarLojasRel(list<CLojista> &listaLojista, int iIdUFOperadora );
	void  consultarTerminais(list<CLojista> &listaTerminais, int iIdPessoaDePara );
	void  consultarIdTerminal(char *cNrTerminal);
	void  consultarTerminal(char*cNrTerminal,char*ddd);
	void  consultarSiglaUF(char*ddd,char*uf);
	void  consultarListaUF(XMLGen* xml_g);
	void  consultarListaMunicipio(XMLGen* xml_g, int iIdUF);
	void  consultarListaLojas(XMLGen* xml_g, int iIdUF, char *cNmMunicipio);
	void  consultarListaTerminais(XMLGen* xml_g, int iIdUF, int iIdTerminal, int iIdPessoaDePara, char *cNmMunicipio, int iInLiberadoRecarga, int iInLiberadoPagamento, int iNrPagina );
	void  alterarEndereco(int iIdPessoa, char *cNmMunicipio, char *cNmLocalidade, char *cNmBairro, char *cNmTipoLogradouro, char *cNmTituloLogradouro, char *cNmLogradouro, char *cNrEndereco, char *cDsEnderecoComplemento, char *cNrCep, int iIdUF);
	void  alterarLoja(int iIdPessoa, char *cNmPessoa);	
	void  alterarTerminal(int iIdTerminal, int iIdPessoaDePara, int iIdUFOperadora, char *cNrTerminal, char *cNrIpTerminal, char *cCdSitefSenha, char *cdLojaOperadoraCartao, int  iLiberadoRecarga, int  iLiberadoPagamento, int iIdCor);	 	 
	void  incluirTerminal(long iIdPessoaDePara, char *cNrTerminal, char *cNrIpTerminal, char *cCdSitefSenha, char *cdLojaOperadoraCartao, int  iLiberadoRecarga, int  iLiberadoPagamento, int iIdCor, int iIdUFOperadora);	 	 
	void  incluirLoja( char *cNmPessoa, char *cNmMunicipio, char *cNmLocalidade, char *cNmBairro, char *cNmTipoLogradouro, char *cNmTituloLogradouro, char *cNmLogradouro, char *cNrEndereco, char *cDsEnderecoComplemento, char *cNrCep, int iIdUF);
	int   excluirTerminal(int iIdTerminal, int iIdPessoaDePara); 
	int   checaDadosTerminal();	
	void  listaDadosLojaTerminal (int iIdTerminal );
	void  resetSenhaTerminal (int iIdTerminal );
	void  PesquisaNomeLoja (char *cNmPessoa, XMLGen* xml_g);
	void  consultarOperadora (XMLGen* xml_g, int iIdUF);
	void  consultarTerminaisLoja(XMLGen* xml_g, int iIdPessoaDePara);
	void  consultarEnderecoLoja( XMLGen* xml_g, int iIdPessoaDePara);
	int   consultarMensagensSitef(XMLGen *xmlgen);

	//void  consultarTerminais(list<CLojista> &listaTerminais, int iIdPessoaDePara);
	
	


private:

	CVenda *m_oVenda;

	// Dados de Negocio
	int  m_iIdTerminal;
	int  m_iIdUFOperadora;
	int  m_iIdUFOperadoraLinha;
	char m_cCdLojaOperadoraCartao[21];
	char m_cNrTerminal[9];
	char m_cNrIpTerminal[16];
	char m_cSgUF[10];
	char m_dsCor[255+1];
	int  m_iIdGrupoOperadora;
	int  m_iIdGrupoOperadoraLinha;
	int	 m_inSitefAtivoRecarga;
	int	 m_inSitefAtivoPagamento;
	int  m_iIdUF;
	char m_cNmAreaRegistro[80];
	char m_cNmMunicipio[100];
	char m_cNmLoja[256];
	char m_cNmTerminal[256];
	char m_cCdSitefSenha[20];
	int  m_iLiberadoRecarga;
	int  m_iLiberadoPagamento;
	int  m_iIdCor;
	char m_cDsOperadora[50];
	int  m_iIdPessoa;
	char m_cNmUF[20];	
	char m_cNmTituloLogradouro[20];
	char m_cNmBairro[60];
	char m_cNmTipoLogradouro[30];
	char m_cNrCep[15];
	char m_cDsEnderecoComplemento[30];
	char m_cNmLogradouro[30];
	char m_cNrEndereco[30];	
	char m_NmTituloLogradouro[30];
	long m_iIdPessoaDeParaLoja;


		
    // Métodos de acesso a banco de dados
	void  consultarDadosDB();
	void  consultarDadosIPDB();
	void  consultarDadosRegionalDB(int cdAreaRegistro);
	void  consultarLojasDB(list<CLojista> &listaLojista);
	void  consultarLojasRelDB(list<CLojista> &listaLojista, int iIdUFOperadora);
	void  consultarTerminaisDB(list<CLojista> &listaLojista, int iIdPessoaDePara);
};

#endif // __C_LOJISTA__