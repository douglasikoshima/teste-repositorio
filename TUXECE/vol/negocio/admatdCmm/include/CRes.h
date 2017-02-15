#ifndef CRespostaH
#define CRespostaH

#include <tuxfw.h>
#include "../include/CResItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#define FO_COMBO_BOX	"CBO"
#define FO_CHECK_BOX	"CHK"
#define FO_MEMO			"MEM"
#define FO_RADIO_BOTTON	"RAD"
#define FO_TEXTO		"TXT"
#define FO_LIST_BOX		"LSB"

class CResposta : public CRespostaItr
{
    private:
		int InserirRaiz();
		int InserirNode();
		
	public:
		CResposta();
		~CResposta();
		int Mover( char* cidResposta
				  ,int   inMoveUp
				  ,char* cLogUser );
		int ListId( char* cidResposta );
		int ListAll( void );
		int ListIdPergunta( char* cidPergunta );

		int Insert(  char* cidPergunta 
					,char* cdsResposta 
					,char* cdsScriptResposta 
					,char* cinEncerramento
					,char* cinDisponibilidade
				    ,char* cidPerguntaSalto
				    ,char* cinAtivo
				    ,char* cidUser );

		int Update(  char* cidResposta 
				    ,char* cidPergunta 
					,char* cdsResposta 
					,char* cdsScriptResposta 
					,char* cinEncerramento
					,char* cinDisponibilidade
				    ,char* cidPerguntaSalto
				    ,char* cinAtivo
				    ,char* cidUser );

		int Delete( char* cidResposta );
		int DeleteIdPergunta( char* cidPergunta );
		int VerifcaTipoPergunta( char* cidPergunta, char* csgTipoPergunta );
		
		void GetXml( char* cNomeTag, XMLGen*xml );
};

#endif
