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
//*  Modulo                   : LSTSERVICOS, LSTVIGSERV
//*  Fichero                  : Servico
//*  Tipo                     : .hpp
//*  Autor                    : Aldebaran
//*  Fecha primera version    : 
//*  Version actual           : 
//*//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Implementar os negocios referentes aos servicos disponiveis as linhas 
//*  telefonicas ou ao sistema
//*//---------------------------------------------------------------------------
//*  Dependencias:
//*
//*  
//*//---------------------------------------------------------------------------
//*  Consideraciones de portabilidad:
//*
//*  
////---------------------------------------------------------------------------

#ifndef CSERVICO
#define CSERVICO

#include <list>
using namespace std;

//---------------------------------------------------------------------------
//*
//*  Clase: CServico
//*
//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Implementar as regras de negócio relativas aos servicos de linha 
//*  telefonica
//*
//---------------------------------------------------------------------------
//*  Relaciones con otras clases:
//*
//*  
//*
//---------------------------------------------------------------------------
//*  Instrucciones de uso:
//*
//*  
//*
//---------------------------------------------------------------------------
//*  Parte publica:                  
//*
//*  CServico()                   - construtor (nao virtual)
//*  ~CServico()                  - destrutor (virtual)
//*  operator(=)                  - sobrecarga do operador = 
//*
//*	 propriedades:
//*  getIdServico()               - informa o id do servico
//*  getSgServico()               - informa a sigla do servico
//*  getNmServico()               - informa o numero do servico
//*  getDtVigenciaInicio()        - informa a data do inicio da vigencia do servico
//*  getDtVigenciaFinal()         - informa a data do fim da vigencia do servico
//*
//*  setIdServico()               - altera o id do servico
//*  setSgServico()               - altera a sigla do servico
//*  setNmServico()               - altera o numero do servico
//*  setDtVigenciaInicio()        - altera a data do inicio da vigencia do servico
//*  setDtVigenciaFinal()         - altera a data do fim da vigencia do servico
//*
//*	 operacoes de negocio         
//*  consultarServicosLinha()       - consulta a lista de todos os servicos existentes e quais ativos para linha corrente
//*  consultarVigServicosLinha()  - consulta a lista de vigencia dos servicos 
//*	                                habilitados para uma determinada linha
//*  
//---------------------------------------------------------------------------
//*  Parte protegida:                
//*
//*
//---------------------------------------------------------------------------
//*  Parte privada:                  
//*       
//*  m_iIdServico                    - membro que armazena o id do servico
//*  m_cSgServico                    - membro que armazena a sigla do servico
//*  m_cNmServico                    - membro que armazena o numero do servico
//*  m_cDtVigenciaInicio             - membro que armazena a data de inicio da 
//*                                    vigencia do servico
//*  m_cDtVigenciaFinal              - membro que armazena a data do fim da 
//*                                    vigencia do servico
//*  
//*	 metodos de acesso a banco de dados
//*  consultarDadosServicosLinhaDB   - consulta o banco e monta a lista de todos 
//*                                    os servicos existentes e quais estão habilitados para linha corrente
//*  carregarDadosVigServicosLinhaDB - consulta o banco e monta a lista de vigencia 
//*                                    dos servicos habilitados para uma determinada linha
//*
//---------------------------------------------------------------------------

class CServico  {

public:
    CServico();
    CServico( const CServico & value );
    virtual ~CServico();

    CServico &	operator =	( const CServico & value );
    bool	operator ==	( const CServico & s) ;

	// Métodos de acesso aos atributos
 	// Getters
 	int  getIdServico();
 	char *getSgServico();
 	char *getNmServico();
	char *getDtVigenciaInicio();
	char *getDtVigenciaFinal();
	int  getInHabilitado();
 	char *getIdServicoSistemaOrigem();
	char *getServicoAtlys();

 	// Setters
 	void setIdServico(int value);
 	void setSgServico(char *value);
 	void setNmServico(char *value);
	void setDtVigenciaInicio(char *value);
	void setDtVigenciaFinal(char *value);
	void setInHabilitado(int value);
 	void setIdServicoSistemaOrigem(char *value);
	void setServicoAtlys(char*value);

	// Operação de Negocio (Interface)
	static void consultarServicosLinha(int iCdAreaRegistro, int iNrLinha, list< CServico > & listaServico );
	static void consultarVigServicosLinha( int iCdAreaRegistro, int iNrLinha, list< CServico > & listaServico );
	void consultarVigenciaInvertida( int iCdAreaRegistro, int iNrLinha, char* cDsServico);

private:

 	int  m_iIdServico;
	char m_cSgServico[ 256 ];
	char m_cNmServico[ 256 ];
	char m_cDtVigenciaInicio[ 32 ];
	char m_cDtVigenciaFinal[ 32 ];
	int  m_iInHabilitado;
	char m_cIdServicoSistemaOrigem[ 256 ];
	char m_servicoAtlys[256];

	// Métodos de acesso a banco de dados
	static void consultarDadosServicosLinhaDB( int iCdAreaRegistro,
                                               int iNrLinha,
										  	   list< CServico > & listaServico );
	static void carregarDadosVigServicosLinhaDB( int iCdAreaRegistro,
                                                 int iNrLinha,
                                                 list< CServico > & listaServico );
	void consultarVigenciaInvertidaDB( int iCdAreaRegistro, int iNrLinha, char* cDsServico);

};
#endif		// !defined( CSERVICO )

