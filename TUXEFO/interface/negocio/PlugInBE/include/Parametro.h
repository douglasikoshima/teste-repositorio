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
//*  Modulo                   : 
//*  Fichero                  : Parametro
//*  Tipo                     : .hpp
//*  Autor                    : Jones Randis Dalberto
//*  Fecha primera version    : 
//*  Version actual           : 
//*//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Controle de parametros para o VOL
//*//---------------------------------------------------------------------------
//*  Dependencias:
//*
//*
//*//---------------------------------------------------------------------------
//*  Consideraciones de portabilidad:
//*
//*  
////---------------------------------------------------------------------------

#ifndef TXPB_PARAMETRO_H
#define TXPB_PARAMETRO_H 1

//---------------------------------------------------------------------------
//*
//*  Clase: CParametro
//*
//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Controle de parametros para o VOL
//*
//---------------------------------------------------------------------------
//*  Relaciones con otras clases:
//*
//*
//---------------------------------------------------------------------------
//*  Instrucciones de uso:
//*
//*
//---------------------------------------------------------------------------
//*  Parte publica:                  
//*
//*  CParametro                    - construtor (nao virtual)
//*  ~CParametro                   - destrutor (nao virtual)
//*
//*
//*  Propriedades:
//*
//*  getChave                      - retorna o nome da chave
//*  getValor                      - retorna o valor da chave
//*  getConsulta                   - retorna o valor de uma chave consultada no banco de dados
//*
//*  setChave                      - armazena o nome da chave
//*  setValor                      - armazena o valor de uma chave
//*  setConsulta                   - armazena o valor de uma chave consultada no banco de dados
//*
//*
//*  Operacoes:
//*
//*  consultar                     - consulta o valor de uma chave no banco de dados
//*  incluir                       - insere uma chave/valor no banco de dados
//*  alterar                       - altera uma chave/valor existente no banco de dados
//*  excluir                       - excluit uma chave/valor existente no banco de dados
//*
//---------------------------------------------------------------------------
//*  Parte protegida:                
//*
//*
//---------------------------------------------------------------------------
//*  Parte privada:                  
//*       
//*  mvc_chave                     - membro que armazena a chave
//*  mvc_valor                     - membro que armazena o valor da chave
//*  mvc_consulta                  - membro que armazena o valor de uma chave consultada no banco de dados
//*  
//---------------------------------------------------------------------------

#define ORA_NO_DATA_FOUND 1403 

#include <vector>
#include <list>
using namespace std;

class CParametro
{
public:
	CParametro(void);
	virtual ~CParametro(void);
	
	void setChave(char*);
	void setValor(char*);
	void setConsulta(char*);
	void setCdAreaRegistro(int);
	void setInAtivo(int);
	void setInAdicionarServico(int);
	
	char* getChave(void);
	char* getValor(void);
	char* getConsulta(void);
	int	  getCdAreaRegistro(void);
	int	  getInAtivo(void);
	int   getInAdicionarServico(void);

	void consultar(void);
	void incluir(void);
	void alterar(void);
	void excluir(void);
	void consultarContatoFuncionalidade(void);
	void consultarNomeServico(void);
	void consultarInAtivoSvcName(void);
	void consultarServicosInvertidos(list< CParametro > & lstParametro);
	int  consultarNomeServicoTorpedoEmpresas(char *, char *);
	void consultarNomeServicoLegado(char *cServicoLegado, int iCdAreaRegistro, char *cServicoFO, char *cOperacao, char *cSgSistemaOrigem, char*linha);
	void consultarNomeServicoFO(char *cServicoFO, int iCdAreaRegistro, char *cServicoLegado, char *cOperacao, char *cSgSistemaOrigem);	
	static int  getNrInSid(char* pcLinha, char* pc_idCanal, char* pc_usuario, char* pc_operacao);
	
	void getSistemaOrigem(char *idUser, char *sgSistemaOrigem);
private:
	char mvc_chave[256];
	char mvc_valor[256];
	char mvc_consulta[256];
	int	 mvi_cdAreaRegistro;
	int  mvi_inAtivo;
	int  mvi_inAdicionarServico;
};

#endif

