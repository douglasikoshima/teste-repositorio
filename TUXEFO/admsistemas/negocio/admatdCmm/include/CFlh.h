/* charls */
#ifndef CContatoFolhaH
#define CContatoFolhaH

#include <tuxfw.h>
#include "../include/CFlhItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoFolha : public CContatoFolhaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoFolha();
		~CContatoFolha();

		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXmlSMP( XMLGen*xml );
		int ListId( char* cidContato );
		int ListAll( void );
		int TemTipoFechamento( char* cidContato, char* cidFase );

		int Insert( 
					  char* cidContato
					 ,char* cidPagina
					 ,char* cidTipoRetorno
					 ,char* cidTipoFechamento
					 ,char* cidTipoProcesso
					 ,char* cqtHorasPrazo
					 ,char* cinFechamentoImediato
					 ,char* cvlPeso
					 ,char* cinProcessoTecnico
			         ,char* cidUsuarioAlteracao
                     ,char* cqtHorasPrazoAnatel
                     ,char* cInSmsPrm
                     ,char* cDsSmsPrm
                     ,char* cInRelacionamentoPrm
                     ,char* cInProtocoloPrm
                     ,char* cInexibeProtocoloPrm
                     ,char* cDsContatoCanaisPrm
                     ,char* cDsMsgExcecaoPrm
					 ,char* cInCancelamentoPrm
					 ,char* cIdClassificacaoSmsPrm
                     ,char* cInAberturaContato
                     ,char* cSgRegraEncaminhamentoPrm
                     ,char* cSgFluxoAtendimentoPrm
			      );
		int Update( 
			          char* cidContato
			         ,char* cidPagina
			         ,char* cidTipoRetorno
			         ,char* cidTipoFechamento
			         ,char* cidTipoProcesso
			         ,char* cqtHorasPrazo
			         ,char* cinFechamentoImediato
			         ,char* cvlPeso
			         ,char* cinProcessoTecnico
	                 ,char* cidUsuarioAlteracao
                     ,char* cqtHorasPrazoAnatel
                     ,char* cInSmsPrm
                     ,char* cDsSmsPrm
                     ,char* cInRelacionamentoPrm
                     ,char* cInProtocoloPrm
                     ,char* cDsContatoCanaisPrm
                     ,char* cDsMsgExcecaoPrm
					 ,char* cInCancelamentoPrm
                     ,char* idClassificacaoSmsPrm
                     ,char* cSgRegraEncaminhamentoPrm
                     ,char* cSgFluxoAtendimentoPrm
                  );
		int Delete( char* cidContato );
		void GetIdClassificacaoSMS( char * sidContato, char * sidClassificacaoSMS );
};

#endif

