#ifndef CCampanhaQuestionarioH
#define CCampanhaQuestionarioH

#include <tuxfw.h>

#include "CCampanhaQuestionarioItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#define ULOG tuxfw_getlogger()->debug

class CCampanhaQuestionario : public CCampanhaQuestionarioItr
{
    private:
		int InserirRaiz();
		int InserirNode();
		
	public:
		CCampanhaQuestionario();
		~CCampanhaQuestionario();
		
		int ListId( char* cid );
		int ListIdCanalCampanha( char* cidCanalCampanha );
		int ListAll( void );
		
		int Insert( char* cidCanalCampanha, 
					char* cidPergunta,
			        char* cidUser );
			        
		int Insert( char* cidCanalCampanha, 
					char* cidPergunta,
					char* cinAtivo,
			        char* cidUser );

		int Update( char* cidCampanhaQuestionario, 
					char* cidCanalCampanha, 
					char* cidPergunta,
			        char* cidUser );
			        
		int Remove( char* cidCampanhaQuestionario, 
					char* cidUser );
			        
		void GetXml( char* cNomeTag, XMLGen*xml );

		int	ExistePergunta(	DOMNode*dnode,
							char* cdsScriptPergunta,int op,char *p );
		int ExistePerguntaEnCanal( char* cidCanalCampanha, char *cidPergunta);
};

#endif
