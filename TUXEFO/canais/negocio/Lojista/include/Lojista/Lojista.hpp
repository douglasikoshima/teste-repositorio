// CLojista.hpp: interface for the CLojista class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_LOJISTA__
#define __C_LOJISTA__


#include <tuxfw.h>

#include <list>
using namespace std;


#define ERR_NRTERMINAL_EXISTENTE				1
#define ERR_EXISTE_OUTRO_TERMIMNAL_RECARGA		2
#define ERR_IP_EXISTENTE						3
#define NO_ERROR								0
#define ERR_EXISTE_HISTORICO_RECARGA			4
#define ERR_TERMINAL_CONFIGURADO_RECARGA		5
#define ERR_EXISTE_HISTORICO_ATENDIMENTO		6

#define NO_DATA_FOUND							1403

#define ASSERT_STR(value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			if (value == NULL) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "INEXISTENTE")); \
			if (!*value) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_VAZIO")); \
		}




#define ASSERT_INT(output, value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			ASSERT_STR(value, tag) \
			if ((output = atoi(value)) < 0) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_INVALIDO")); \
		}




class CLojista
{

public:

	CLojista();
	~CLojista();

	// Métodos de acesso aos atributos
 	// Getters
	int     getIdTerminal() const;
	int     getIdUFOperadora() const;	
	char   *getCdLojaOperadoraCartao(char *cCdLojaOperadoraCartao) const;
	char   *getNrTerminal(char *cNrTerminal) const;
	char   *getNrIpTerminal(char *cNrIpTerminal) const;	
	char   *getNmLoja();
	int		getInLiberadoRecarga();
	int		getInLiberadoPagamento();
	int     getIdCor();
	int		getIdPessoa();
	long	getIdPessoaDePara ();
	char   *getNmPessoa() ;


 	// Setters
	void  setIdTerminal(int value);
	void  setIdUFOperadora(int value);
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
	void  setNmMunicipio(char *value);
	void  setNmLoja(char *value);
	void  setNmTerminal(char *value);
	void  setInLiberadoRecarga(int value);	
	void  setInLiberadoPagamento(int value);	
	void  setCdSitefSenha(char *value);
	void  setIdPessoa (int value);	
	void  setIdPessoaDePara (long value);
	void  setNmPessoa(char *value);
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
	void  listaDadosLojaTerminal (int iIdTerminal,  XMLGen* xml_g );
	void  resetSenhaTerminal (int iIdTerminal );
	void  PesquisaNomeLoja (char *cNmPessoa, XMLGen* xml_g);
	void consultarOperadora ( XMLGen* xml_g, int iIdUF);
	void  consultarTerminaisLoja(XMLGen* xml_g, int iIdPessoaDePara);
	void  consultarEnderecoLoja( XMLGen* xml_g, int iIdPessoaDePara);
	char* rtrim(char *pStr);
	char* ltrim(char *pStr);
	char* trim (char *pStr);




private:

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
	long m_iIdPessoaDePara;
	char m_cNmPessoa[256];

    // Métodos de acesso a banco de dados
	void  consultarDadosDB();
	void  consultarTerminaisDB(list<CLojista> &listaLojista, int iIdPessoaDePara);
};




#endif // __C_LOJISTA__

