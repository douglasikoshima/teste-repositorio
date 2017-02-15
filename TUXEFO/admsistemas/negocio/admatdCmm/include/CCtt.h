#ifndef CContatoH
#define CContatoH

#include <tuxfw.h>
#include "../include/CCttItr.h"
#include "../include/CSafePointer.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CContato : public CContatoItr
{
    private:
		int InserirRaiz();
		int InserirNode();
		char pzcPath[2048+1];
		char pzcidNome[21+1];

	public:
		CContato();
		~CContato();
		//converte DD/MM/YYYY para YYYYMMDD
		void strDateToYYYYMMDD( char* pzcDestino, char* pzcDate );
		int ListContato( char* cidContato );
		int ListId( char* cidContato,char* cidTipoArvore );
		int ListId( char* cidContato,char* cLevel,char* cidTipoArvore );
		int ListAll( void );
		int ListAll( char* cidContato, char* cidTipoArvore=0 );
		// ListAll( char* cidContato, int pageNumber, char* cidTipoArvore )
        // difere de ListAll( char* cidContato, char* cidTipoArvore ) apenas
		// em relação ao retorno paginado. Jun/2007 - Cassio
		int ListAll( char* cidContato, int pageNumber, char* cidTipoArvore=0 );
		//
		int ListAllSkill( char* cidContato, char* cidGrupo, char* cidSkill );
		int ListAllGrupo( char* cidContato, char* cidGrupo );
		int ListAllGrupoSkill( char* cidContato, char* cidSkill );
		int ListFiltro( char* cidGrupo
					  ,char* cidUFOperadora
					  ,char* cidTipoLinha
					  ,char* cidTipoCarteira
					  ,char* cidSegmentacao );

		int TemFolha( char* cidContato );
		void GetXml( char* cNomeTag, XMLGen*xml );

        int Insert( char* cidContatoPai,
			        char* cidNomeContato,
				    char* cnmContato,
			        char* cinDisponibilidade,
				    char* cinFolha,
				    char* cidUser,
                    char* cidTipoArvore );

		int InsertCopia( char* cidContatoPai,
						 char* cidNomeContato,
						 char* cinDisponibilidade,
						 char* cidUser,
                         char* cidTipoArvore,
                         char* cdsTipoProcesso );

		int Update( char* cidContato,
					char* cidNomeContato,
					char* cnmContato,
					char* cinDisponibilidade,
					char* cinFolha,
					char* cidUser );

		int Delete( char* cidContato
				  ,char* cidUser );

		int ContidoNoFiltro( char* cidContato );

		char* getPath( char* cidContato, char* cidContatoPai );
		char* getPath( char* cidContato );
		char* getPath() { return pzcPath; };
		void  atualizaPath( char* cidContato );
		int ativaInativaArvore( char* cidContato, char* cinHabilitado );
		int ativaInativaArvore( char* cidContato, char *cidUser, char* cinHabilitado );
        void ObterTipoProcesso( const char *idContatoParam, char *dsTipoProcessoParam );
        void HabilitarContato( const char *idContatoParam,const char* cidUser );

/*		
		int ListAllFiltrado( char* cidTipoRelacionamento,
                             char* cidSegmentacao,
                             char* cidTipoCarteira,
                             char* cidGrupoAbertura,
                             char* cidGrupoTratamento,
                             char* cidGrupoRetorno,
                             char* cidTipoLinha,
                             char* cidFechamento,
                             char* cidDisponivel,
                             char* cidOperadora,
                             char* cdtInicio,
                             char* cdtFim );

		int ListAllFiltrado( char* cidContato, 
                             char* cidTipoRelacionamento,
                             char* cidSegmentacao,
                             char* cidTipoCarteira,
                             char* cidGrupoAbertura,
                             char* cidGrupoTratamento,
                             char* cidGrupoRetorno,
                             char* cidTipoLinha,
                             char* cidFechamento,
                             char* cidDisponivel,
                             char* cidOperadora,
                             char* cdtInicio,
                             char* cdtFim );
*/
		int ListAllFiltrado( char* cidContato, 
                             CSafePointer* osfidTipoRelacionamento,
                             CSafePointer* osfidSegmentacao,
                             CSafePointer* osfidTipoCarteira,
                             CSafePointer* osfidGrupoAbertura,
                             CSafePointer* osfidGrupoTratamento,
                             CSafePointer* osfidGrupoRetorno,
                             CSafePointer* osfidTipoLinha,
                             CSafePointer* osfidFechamento,
                             CSafePointer* osfidDisponivel,
                             CSafePointer* osfidOperadora,
                             char* cdtInicio,
                             char* cdtFim );
                             
		int  TemArvoreBaixa( char* cidContato );
		int  TemGrupoAssociado( char* cidContato, char* cidTipoSequencia );
		int  TemProcessoEmAndamento( char* cidContato );
		int  TemNomeIgualNoMesmoNivel( char* cidContatoPai, char* cidNomeContato, char* cinFolha );
		int  TemNomeIgualNoMesmoNivel( char* cidContatoPai, char* cidContato, char* cidNomeContato, char* cinFolha );
		void ApagaNomesNaoUtilizados( void );

		int   ProcuraNome( char* cnmContato );
		char* getIdNome( void );
		void  setIdNome( char* cidNome );
		int   TemGrupoRetorno( char* cidContato, char* cidTipoRetornoContato );
		int   TemRegionais( char* cidContato );
		int   TemTipoLinha( char* cidContato );
		int   TemTipoRelacionamento( char* cidContato );
		int   TemSegmentacao( char* cidContato );
		int   TemTipoCarteira( char* cidContato );
		void  LimpaNomeContato( void );
//Metodos abaixo foram criados pelo MArcelo Nunez
      void GetXmlWF( char* cNomeTag, char *cidTpRelacionamento, int idContatoAux, XMLGen*xml );

      int ListaSemContato( 
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                         );

      int ListaSemGrupo( 
                           char *idContatoParam, 
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                       );

      int ListaComGrupo( 
                           char *idGrupoParam ,
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                        );

      int ListaComContato( 
                           char *idContatoParam, 
                           char *idGrupoParam ,
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                          );
      
      void TpRetorno( char *idContatoParam, 
                      int idTpRetornoParam, 
                      char * Saida );

      void URLInf( char *idContatoParam, char * Saida );
};

#endif

