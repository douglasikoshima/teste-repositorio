//---------------------------------------------------------------------------
//                         (c) Consórcio Indra/PT-SI.
//                            xxxxxxxxxxxxxxxxxxxxxxx
//                                xxxxxxxxxxxxxx
//-----------------------------------------------------------------------------
// Los contenidos de este fichero son propiedad de Telefónica Consórcio Indra/PT-SI. 
// titular del copyright. Este fichero solo podra ser copiado, distribuido y utilizado, 
// en su totalidad o en parte, con el permiso escrito de Consórcio Indra/PT-SI 
// o de acuerdo con los terminos y condiciones establecidas en el acuerdo/contrato bajo 
// el que se suministra.
//---------------------------------------------------------------------------
//*  Modulo                   : LSTDADOSINI, LSTVIGSERV
//*  Fichero                  : Linha
//*  Tipo                     : .hpp
//*  Autor                    : Aldebaran
//*  Fecha primera version    : 
//*  Version actual           : 
//*//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Implementar os negocios referentes as linhas telefonicas 
//*//---------------------------------------------------------------------------
//*  Dependencias:
//*
//*  Servico.hpp
//*//---------------------------------------------------------------------------
//*  Consideraciones de portabilidad:
//*
//*  
////---------------------------------------------------------------------------

#include <Pessoa/Pessoa.hpp>
#include <Servico/Servico.hpp>
#include <Ponto/Ponto.hpp>
#include <tuxfw.h>

#ifndef CLINHA
#define CLINHA

#include <list>
using namespace std;

struct stLinhaIntermed{
	char idLinhaSistemaOrigem[255+1];
	char sgSistemaOrigem[255+1];
	char idSistemaOrigem[21+1];
	char sgTipoLinha[255+1];
	char idTipoLinha[21+1];
};

#define  NO_ERROR	      0


#define  ERR_LINHA_EXPIRADA  1

//---------------------------------------------------------------------------
//*
//*  Clase: CLinha
//*
//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Implementar as regras de negócio relativas as linhas telefonicas
//*
//---------------------------------------------------------------------------
//*  Relaciones con otras clases:
//*
//*  Depende diretamente da classe CServico, pois esta informa todos os 
//*  servicos disponiveis para uma determinada linha
//*
//---------------------------------------------------------------------------
//*  Instrucciones de uso:
//*
//*  
//*
//---------------------------------------------------------------------------
//*  Parte publica:                  
//*
//*  CLinha()              - construtor (nao virtual)
//*  CLinha()              - destrutor (virtual)
//*
//*  propriedades:
//*  
//*  getCdAreaRegistro     - informa o codigo de area da qual a linha faz parte
//*  getNrLinha()          - informa o numero da linha telefonica em questao
//*  getIdTipoLinha        - informa o id do tipo da linha
//*  getDsTipoLinha()      - informa a descricao do tipo da linha
//*  
//*  
//*  setCdAreaRegistro()   - altera o codigo de area da qual a linha faz parte
//*  setNrLinha()          - altera o numero da linha telefonica em questao
//*  setIdTipoLinha()      - altera o id do tipo da linha
//*  setDsTipoLinha()      - altgera a descricao do tipo da linha
//*
//*	 operacoes de negocio
//*  consultarTipoLinha()         - carrega os valores para o tipo de linha
//*  consultarVigServicosLinha()  - informa os servicos habilitados para 
//*                                 determinada linha
//*  validarLinha()				  - valida a existencia da linha
//*  consultarLinhasDisp()        - consulta todas as linhas existentes para um 
//*                                 determinado cliente
//*
//---------------------------------------------------------------------------
//*  Parte protegida:                
//*
//*  
//*
//---------------------------------------------------------------------------
//*  Parte privada:                  
//*       
//*  
//*  m_iCdAreaRegistro        - membro que armazena o codigo de area da linha
//*  m_iNrLinha               - membro que armazena o numero da linha
//*  m_iIdTipoLinha           - membro que armazena o id do tipo da linha
//*  m_cDsTipoLinha           - membro que armazena a descricao do tipo da
//*                             linha
//*
//*	 metodos de acesso a banco de dados
//*  consultarTipoLinhaDB()   - faz a consulta para obter as informacoes do 
//*                             tipo da linha
//*  validarLinhaDB()         - valida a existencia da linha
//*  consultarLinhasDispDB()  - consulta todas as linhas existentes para um 
//*                             determinado cliente
//*
//---------------------------------------------------------------------------

class CLinha  
{

public:

	enum TipoLinha
	{
		TP_POS = 1,
		TP_PRE = 2
	};

    CLinha();
	//CLinha(CPessoa* pPessoa);
    virtual ~CLinha();

	// Métodos de acesso aos atributos
 	// Getters

 	int			getCdAreaRegistro();
 	int			getNrLinha();
 	int			getIdTipoLinha();
 	char*		getDsTipoLinha();
	CPonto*		getPonto();
	int         getQtFavoritos();
	int         getIdUF();
	CPessoa*    getPessoa();
	int 		getIdSistemaOrigem(void);
	char*		getSgSistOrig(void);
	int 		getIdUFOperadora(void);
	int 		getIdLinhaTelefonica(void);
	int 		getIdProcedencia(void);
	char*		getSgEstadoLinha(void);
	char*		getSgEstadoLinhaOriginal(void);
	char*		getDsEstadoLinha(void);	
	int         getIdSegmentacao();
	int         getDigitoVerificador();
	int			getIdConta();
	char*		getSgUF();

 	// Setters
 	void setCdAreaRegistro(int value);
 	void setNrLinha(int value);
 	void setIdTipoLinha(int value);
 	void setDsTipoLinha(char *value);
    void setQtFavoritos(int value);
	void setIdUF(int value);
	void setIdSistemaOrigem(int);
	void setSgSistOrig(char *);
	void setIdUFOperadora(int);
	void setIdLinhaTelefonica(int);
	void setIdProcedencia(int);
	void setSgEstadoLinha(char *value);
	void setSgEstadoLinhaOriginal(char *value);
	void setDsEstadoLinha(char *value);
	void setIdSegmentacao(int value);
	void setDigitoVerificador(int value);
	void setIdConta(int value);
	void setSgUF(char *value);
	void setPlanoControle(int value);

	void setIdGrupoOperadora(int value);
	int  getIdGrupoOperadora ();


	void setIdEstadoLinha(int value);
	int  getIdEstadoLinha();
	int  getPlanoControle();

	// Operação de Negocio (Interface)
	void consultarTipoLinha(void);
	
	char* obterFraseSecreta(int iIdCanal, int iIdPessoaUsuario, int iIdTerminal);
	void  consultarVigServicosLinha( list< CServico > & listaServico );
	void  excluirFraseSecreta(int idCanal, int idPessoaUsuario);
	int  validarLinha(void);	
	static void consultarLinhasDisp( int iIdPessoa, 
                                     list< CLinha > & listaLinha );
	void consultarServicos( list< CServico > & listaServico );
	void consultarExtratoPontos();
	void consultarQtdFavoritos();
	int  obterDadosLinhaPessoaSessao( char  * = NULL, int iIdCanal=0);
	void consultarDadosLinhaSessao();


	void  registrarContato(int iIdTerminal, char *cUsuario);
	int   alteraEmailServiceRouter();
	
	void  enviarMensagem(char *cdMsg, char *cUsuario);

	void  consultarIdConta();

	bool verificarClienteUsuarioIguais(void);

	int  consultarIdUF();
	char* consultarObsRecarga();	
	void VerificaTabelaRolloutDDD();
	void VerificaTabelaRolloutTipoLinha();
	void getMsgParametrizada(char *, char *);
	void consultarDadosHibrido(char *cIdLinhaSistemaOrigem, char *cSgSistemaOrigem);
	void consultarIdGrupoOperadora(int iCdAreaRegistro);

	void consultarDadosLinhaIntermed(char*ddd,char*linha,stLinhaIntermed *dadosLinha);
	void consultarBannersLinha(char*ddd,char*linha,char*tipoRelacionamento,XMLGen*xml);
	void consultarProcessoCancelamento(char*ddd,char*linha,XMLGen*xml);
	void consultarPlanoControle();
	

private:

    CPessoa*	m_oPessoa;
	CPonto*		m_oPonto;

 	int   m_iCdAreaRegistro;
 	int   m_iNrLinha;
 	int   m_iIdTipoLinha;
	int   m_iIdProcencia;
	int   m_iQtFavoritos;
	int   m_iIdUF;
	int   m_iIdSistemaOrigem;
	char  m_cSgSistOrig[255+1];
	int   m_iIdUFOperadora;
	int	  m_iIdLinhaTelefonica;
	int   m_iIdSegmentacao;
	int   m_iDigitoVerificador;
	char  m_cDsTipoLinha[256];
	char  m_cSgEstadoLinha[256];
	char  m_cDsEstadoLinha[256];
	int	  m_iIdConta;
	char  m_cSgUF[256];
	char  m_cSgEstadoLinhaOriginal[256];
	int   m_idGrupoOperadora;	
	int	  m_idEstadoLinha;
	int	  m_iPlanoControle;
	
	// metodos de acesso a banco de dados
 	void consultarTipoLinhaDB(void);
	int validarLinhaDB(void);

	static void consultarLinhasDispDB( int iIdPessoa, 
                                       list< CLinha > & listaLinha );
	void consultarQtdFavoritosDB();
	int  consultarDadosLinhaSessaoDB();
	int  consultarProcedenciaLinha();
	void consultarIdContaDB();
	bool verificarClienteUsuarioIguaisDB(void);

	//remote call
	void remoteMensagem(XMLGen &xmlInput, char *cServico, char *cUsuario);

};
#endif		// !defined( CLINHA )

