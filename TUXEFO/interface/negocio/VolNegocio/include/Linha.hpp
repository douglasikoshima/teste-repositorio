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

#include "Pessoa.hpp"
#include "Servico.hpp"
#include "Ponto.hpp"

#ifndef CLINHA
#define CLINHA

#include <list>
using namespace std;


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

class CLinha  {
public:
    CLinha();
	CLinha(CPessoa* pPessoa);
    virtual ~CLinha();

	// Métodos de acesso aos atributos
 	// Getters

 	int			getCdAreaRegistro();
 	int			getNrLinha();
 	int			getIdTipoLinha();
 	char*		getDsTipoLinha();
	CPonto*		getPonto();

 	// Setters

 	void setCdAreaRegistro(int value);
 	void setNrLinha(int value);
 	void setIdTipoLinha(int value);
 	void setDsTipoLinha(char *value);

	// Operação de Negocio (Interface)
	void consultarTipoLinha(void);
	void consultarVigServicosLinha( list< CServico > & listaServico );
	char* obterLembreteSenha(void);
	void validarLinha(void);	
	static void consultarLinhasDisp( int iIdPessoa, 
                                     list< CLinha > & listaLinha );
	void consultarServicos( list< CServico > & listaServico );
	void consultarExtratoPontos();

private:

    CPessoa*	m_oPessoa;
	CPonto*		m_oPonto;

 	int  m_iCdAreaRegistro;
 	int  m_iNrLinha;
 	int  m_iIdTipoLinha;
	char m_cDsTipoLinha[256];

	// Métodos de acesso a banco de dados
 	void consultarTipoLinhaDB(void);
	void validarLinhaDB(void);
	static void consultarLinhasDispDB( int iIdPessoa, 
                                       list< CLinha > & listaLinha );
};
#endif		// !defined( CLINHA )

