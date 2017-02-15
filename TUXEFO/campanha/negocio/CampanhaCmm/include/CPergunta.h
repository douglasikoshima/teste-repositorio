#ifndef CPerguntaH
#define CPerguntaH

#include <tuxfw.h>

#include "CPerguntaItr.h"


class CPergunta : public CPerguntaItr

{
    private:
		int InserirRaiz();
		int InserirNode();
    
	public:
		CPergunta();
		~CPergunta();
		int ListId( char* cid );
		int ListAll( void );
		int ListIdCanalCampanha( char* cid );

		int Insert( char* cidTipoApresentacaoPergunta, 
					char* cdsPergunta, 
					char* cdsScriptPergunta, 
					char* csqApresentacao,
					char* cinEncerramento,
					char* cinDisponibilidade,
					char* cinObrigatoria );

		int Update( char* cidPergunta, 
				    char* cidTipoApresentacaoPergunta, 
					char* cdsPergunta, 
					char* cdsScriptPergunta, 
					char* csqApresentacao,
					char* cinEncerramento,
					char* cinDisponibilidade,
					char* cinObrigatoria );

		int Update( char* cidPergunta, 
							   char* cidTipoApresentacaoPergunta, 
							   char* cdsPergunta, 
							   char* cdsScriptPergunta, 
							   char* cinEncerramento,
							   char* cinDisponibilidade,
							   char* cinObrigatoria );

		int UpdateSQApresentacao( char* cidPergunta,
								  char* csqApresentacao);
		
		int GetMaxSQAPresentacao(char* cidCanal);
								  	
				    

		void GetXml( char* cNomeTag, XMLGen*xml );
};
#endif

