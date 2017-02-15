#ifndef CPerguntaItrH
#define CPerguntaItrH

#include <stdlib.h>
#include <string.h>

struct STPerguntaRegistro
{
	char cidPergunta[21+1];
	char cidTipoApresentacaoPergunta[21+1];
	char cdsPergunta[2000+1];
	char cdsScriptPergunta[2000+1];
	char csqApresentacao[21+1];
	char cinEncerramento[21+1];
	char cinDisponibilidade[21+1];
	char cinObrigatoria[21+1];
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

	void Add( char* cidPergunta, 
		      char* cidTipoApresentacaoPergunta, 
			  char* cdsPergunta, 
			  char* cdsScriptPergunta, 
			  char* csqApresentacao,
			  char* cinEncerramento,
			  char* cinDisponibilidade,
			  char* cinObrigatoria );

	void ZeraPergunta( void );

};

#endif