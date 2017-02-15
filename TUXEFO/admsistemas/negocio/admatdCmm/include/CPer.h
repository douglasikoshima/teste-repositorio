#ifndef CPerguntaH
#define CPerguntaH

#include <tuxfw.h>
#include "../include/CPerItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPergunta : public CPerguntaItr
{
    private:
		int InserirRaiz();
		int InserirNode();
		CResposta oReposta;
    
	public:
		CPergunta();
		~CPergunta();
		int Mover( char* cidPergunta
			      ,int   inMoveUp
				  ,char* cLogUser );
		int ListId( char* idPegunta );
		int ListAll( void );
		int ListIdPesquisa( char* cidPesquisa );

		int Insert( char* cidQuestionario,
		            char* cidTipoApresentacaoPergunta, 
					char* cdsPergunta, 
					char* cdsScriptPergunta, 
					char* cinEncerramento,
					char* cinDisponibilidade,
					char* cinObrigatoria,
					char* cidUser );

		int Update( char* cidQuestionario,
		            char* cidPergunta, 
				    char* cidTipoApresentacaoPergunta, 
					char* cdsPergunta, 
					char* cdsScriptPergunta, 
					char* cinEncerramento,
					char* cinDisponibilidade,
					char* cinObrigatoria,
					char* cidUser );

		void GetXml( char* cNomeTag, XMLGen*xml );
		
		int Delete( char* cidPergunta );
		int PerguntaTextoMemo( char* cidPergunta, char* cidTipoPergunta );
		int TemResposta( char* cidPergunta );
};

#endif
