#ifndef CRespostaH
#define CRespostaH

#include <tuxfw.h>

#include "CRespostaItr.h"


class CResposta : public CRespostaItr
{
    private:
		int InserirRaiz();
		int InserirNode();
		
	public:
		CResposta();
		~CResposta();
		int ListId( char* cid );
		int ListAll( void );
		int ListIdPergunta( char* cid );

		int Insert( char* cidPergunta, 
					char* cdsResposta, 
					char* cdsScriptResposta, 
					char* csqApresentacao,
					char* cinEncerramento,
					char* cinDisponibilidade );

		int Update( char* cidResposta, 
				    char* cidPergunta, 
					char* cdsResposta, 
					char* cdsScriptResposta, 
					char* csqApresentacao,
					char* cinEncerramento,
					char* cinDisponibilidade );

		void GetXml( char* cNomeTag, XMLGen*xml );
};
#endif

