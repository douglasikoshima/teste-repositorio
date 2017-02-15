#ifndef CFluxoFaseGrupoH
#define CFluxoFaseGrupoH

#include <tuxfw.h>
#include "../include/CFluxoFaseGrupoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CFluxoFaseGrupo : public CFluxoFaseGrupoItr
{
    private:
		int InserirRaiz();
		int InserirNode();
		
	public:
		CFluxoFaseGrupo();
		~CFluxoFaseGrupo();

		int ListId( char* cidFluxoFaseGrupo );
		int ListAll( void );
		int ListIdContato( char* cidContato );

		int Insert(  char* cidGrupo 
					,char* cidContato 
					,char* cidTipoLinha 
					,char* cidTipoPessoa
					,char* cidTipoRelacionamento
					,char* cidTipoCarteira
				    ,char* cidSegmentacao
				    ,char* cidTipoFechamentoContato
				    ,char* cidUser );

		int Update(  char* cidFluxoFaseGrupo 
				    ,char* cidGrupo 
					,char* cidContato 
					,char* cidTipoLinha 
					,char* cidTipoPessoa
					,char* cidTipoRelacionamento
					,char* cidTipoCarteira
				    ,char* cidSegmentacao
				    ,char* cidTipoFechamentoContato
				    ,char* cidUser );

		void Copia( char* cidContatoOrigem, 
			    	char* cidContatoDestino,
					char* cidUser );
};

#endif
