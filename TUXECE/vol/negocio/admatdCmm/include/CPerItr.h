#ifndef CPerguntaItrH
#define CPerguntaItrH

#include <stdlib.h>
#include <string.h>
#include "../include/CRes.h"

struct STPerguntaRegistro
{
	char cidPesquisaSatisfacao[21+1];
	char cnmPesquisaSatisfacao[255+1];
	char cidPergunta[21+1];
	char cidTipoApresentacaoPergunta[21+1];
	char csgTipoApresentacaoPergunta[255+1];
	char cdsTipoApresentacaoPergunta[255+1];
	char cdsPergunta[256+1];
	char cdsScriptPergunta[256+1];
	char csqApresentacao[21+1];
	char cinEncerramento[21+1];
	char cinDisponibilidade[21+1];
	char cinObrigatoria[21+1];
	int  isqApresentacao;
	CResposta* pzResposta;
};

class CPerguntaItr
{
public:
	CPerguntaItr();
	~CPerguntaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPerguntaRegistro* Registro( void );
	STPerguntaRegistro* Registro(int nPosicao);

private:
	STPerguntaRegistro* stcrPergunta;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidPesquisaSatisfacao,
			  char* cnmPesquisaSatisfacao,
			  char* cidPergunta, 
		      char* cidTipoApresentacaoPergunta, 
		      char* csgTipoApresentacaoPergunta, 
		      char* cdsTipoApresentacaoPergunta, 
			  char* cdsPergunta, 
			  char* cdsScriptPergunta, 
			  char* csqApresentacao,
			  char* cinEncerramento,
			  char* cinDisponibilidade,
			  char* cinObrigatoria );

	void Add( char* cidPergunta, 
		      int   isqApresentacao );

	void AddReposta( int   iRegistro,
                     char* cidResposta, 
		             char* cidPergunta, 
			         char* cdsResposta, 
			         char* cdsScriptResposta, 
			         char* csqApresentacao,
			         char* cinEncerramento,
			         char* cinDisponibilidade,
			         char* cidPerguntaSalto,
			         char* cinAtivo );

	void ZeraPergunta( void );

};

#endif