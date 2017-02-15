#ifndef CContatoFiltroH
#define CContatoFiltroH

#include <tuxfw.h>
#include "../include/CCttFtrItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoFiltro : public CContatoFiltroItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoFiltro();
		~CContatoFiltro();
		
//Geral
		int Relacao(char* cidContato,XMLGen*xml_g);

//TipoLinha
		int ListAllTipoLinha( void );
		int ListIdTipoLinha( char* cidTipoLinha );
		//Apaga todas as relacoes com TipoLinha
		int DeleteTipoLinhaIdContato( char* cidContato );
		//Retorna uma relacao de TipoLinha
		int RelacaoTipoLinha( char* cidContato );
		//Retorna uma relacao de TipoLinha nao relacionados
		int ExistentesTipoLinha( char* cidContato );
		//Relaciona TipoLinha com contato
		int RelacionaTipoLinha( char* cidContato
                               ,char* cidTipoLinha
			                   ,char* cidUsuarioAlteracao );
		//Monta o xml padrao para TipoLinha
		void GetXmlTipoLinha( char* cNomeTag, XMLGen*xml );

//TipoRelacionamento
		int ListAllTipoRelacionamento( void );
		int ListIdTipoRelacionamento( char* cidTipoRelacionamento );
		//Apaga todas as relacoes com TipoRelacionamento
		int DeleteTipoRelacionamentoIdContato( char* cidContato );
		//Retorna uma relacao de TipoRelacionamento
		int RelacaoTipoRelacionamento( char* cidContato );
		//Retorna uma relacao de TipoRelacionamento nao relacionados
		int ExistentesTipoRelacionamento( char* cidContato );
		//Relaciona TipoRelacionamento com contato
		int RelacionaTipoRelacionamento( char* cidContato
                                		,char* cidTipoRelacionamento
			                    		,char* cidUsuarioAlteracao );
		//Monta o xml padrao para TipoRelacionamento
		void GetXmlTipoRelacionamento( char* cNomeTag, XMLGen*xml );
		void GetXmlTipoRelacionamento2( char* cNomeTag, XMLGen*xml );

//Segmentacao
		int ListAllSegmentacao( void );
		//Apaga todas as relacoes com Segmentacao
		int DeleteSegmentacaoIdContato( char* cidContato );
		//Retorna uma relacao de Segmentacao
		int RelacaoSegmentacao( char* cidContato );
		//Retorna uma relacao de Segmentacao nao relacionados
		int ExistentesSegmentacao( char* cidContato );
		//Relaciona Segmentacao com contato
		int RelacionaSegmentacao( char* cidContato
                                 ,char* cidSegmentacao
			                     ,char* cidUsuarioAlteracao );
		//Monta o xml padrao para Segmentacao
		void GetXmlSegmentacao( char* cNomeTag, XMLGen*xml );

//TipoCarteira
		int ListAllTipoCarteira( void );
		//Apaga todas as relacoes com TipoCarteira
		int DeleteTipoCarteiraIdContato( char* cidContato );
		//Retorna uma relacao de TipoCarteira
		int RelacaoTipoCarteira( char* cidContato );
		//Retorna uma relacao de TipoCarteira nao relacionados
		int ExistentesTipoCarteira( char* cidContato );
		//Relaciona TipoCarteira com contato
		int RelacionaTipoCarteira( char* cidContato
                                  ,char* cidTipoCarteira
			                      ,char* cidUsuarioAlteracao );
		//Monta o xml padrao para TipoCarteira
		void GetXmlTipoCarteira( char* cNomeTag, XMLGen*xml );

//Grupo
		#define CTTFTR_ABERTURA     "1"
		#define CTTFTR_TRATAMENTO   "2"
		#define CTTFTR_RETORNO      "3"
		int ListGrupoPoridTipoSequencia( char* cidTipoDocumento );
		int ListGrupoAbertura( void );
		int ListGrupoTratamento( void );
		int ListGrupoRetorno( void );
		//Monta o xml padrao para Grupo
		void GetXmlGrupo( char* cNomeTag, XMLGen*xml );

//Canal
		int ListAllCanal( void );
		void GetXmlCanal( char* cNomeTag, XMLGen*xml );

//Tipo Fechamento
		int ListTipoFechamento( void );
		void GetXmlTipoFechamento( char* cNomeTag, XMLGen*xml );
		
//Tipo Procedencia
		int ListTipoProcedencia( void );
		void GetXmlTipoProcedencia( char* cNomeTag, XMLGen*xml );

};

#endif

