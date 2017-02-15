#ifndef CContatoTipoRetornoRelacoesH
#define CContatoTipoRetornoRelacoesH

#include <tuxfw.h>
#include "../include/CContatoTipoRetornoRelacoesItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoTipoRetornoRelacoes : public CContatoTipoRetornoRelacoesItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoTipoRetornoRelacoes();
		~CContatoTipoRetornoRelacoes();

		//Insere um registro
		int InsertCanal( 
					char* cidContatoTipoRetorno,
					char* cidCanal,
					char* cidUsuarioAlteracao 
			      );
		int InsertTpRelacionamento( 
					char* cidContatoTipoRetorno,
					char* cidTipoRelacionamento,
					char* cidUsuarioAlteracao 
			      );
		int InsertSegmentacao( 
					char* cidContatoTipoRetorno,
					char* cidSegmentacao,
					char* cidUsuarioAlteracao 
			      );
		int InsertProcedencia( 
					char* cidContatoTipoRetorno,
					char* cidProcedencia,
					char* cidUsuarioAlteracao 
			      );
		int InsertTipoLinha( 
					char* cidContatoTipoRetorno,
					char* cidTipoLinha,
					char* cidUsuarioAlteracao 
			      );
		int InsertTipoCarteira( 
					char* cidContatoTipoRetorno,
					char* cidTipoCarteira,
					char* cidUsuarioAlteracao 
			      );
		int InsertTipoPessoa( 
					char* cidContatoTipoRetorno,
					char* cidTipoPessoa,
					char* cidUsuarioAlteracao 
			      );
		int InsertRegional( 
					char* cidContatoTipoRetorno,
					char* cidRegional,
					char* cidUsuarioAlteracao 
			      );
		//Recupera todos os registros de um certo contato
		int ListIdContatoCanal( char* cidContato );
		int ListIdContatoTpRelacionamento( char* cidContato );
		int ListIdContatoSegmentacao( char* cidContato );
		int ListIdContatoProcedencia( char* cidContato );
		int ListIdContatoTipoLinha( char* cidContato );
		int ListIdContatoTipoCarteira( char* cidContato );
		int ListIdContatoTipoPessoa( char* cidContato );
		int ListIdContatoRegional( char* cidContato );

		//Lista as variaveis de um ContatoTipoRetorno
		int ListCanal( char* cidTipoContato );
		int ListTpRelacionamento( char* cidTipoContato );
		int ListSegmentacao( char* cidTipoContato );
		int ListProcedencia( char* cidTipoContato );
		int ListTipoLinha( char* cidTipoContato );
		int ListTipoCarteira( char* cidTipoContato );
		int ListTipoPessoa( char* cidTipoContato );
		int ListTipoGrupoAbertura( char* cidTipoContato );
		
		//Lista as variaveis de um grupo
		int ListGrupoCanal( char* cidGrupo );
		int ListGrupoTpRelacionamento( char* cidGrupo );
		int ListGrupoSegmentacao( char* cidGrupo );
		int ListGrupoProcedencia( char* cidGrupo );
		int ListGrupoTipoLinha( char* cidGrupo );
		int ListGrupoTipoCarteira( char* cidGrupo );
		int ListGrupoTipoPessoa( char* cidGrupo );
		int ListGrupoAbertura( char* cidGrupo );
		//Retorna as sequencias de um contato grupo
		int ListTipoSequencia( char* cidGrupo, char* cidContato );

		//Recupera todos os grupos de um contato
		int ListGrupo( char* cidContato );
		//Lista os tipos de tiporetornocontato de um contato
		int ListContatoTipoRetorno( char* cidContato );
		
		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag
		           , char* cidContatoTipoRetornoRelacao
		           , char* cidContatoTipoRetorno
		           , char* cidRelacao
		           , XMLGen*xml );

		void GetXml( char* cNomeTag
		           , char* cidContatoRelacao
		           , char* cdsContatoRelacao
		           , XMLGen*xml );
		void GetXmlSigla( char* cNomeTag
		           , char* cidContatoRelacao
		           , char* sgdContatoRelacao
		           , char* cdsContatoRelacao
		           , XMLGen*xml );
};

#endif

