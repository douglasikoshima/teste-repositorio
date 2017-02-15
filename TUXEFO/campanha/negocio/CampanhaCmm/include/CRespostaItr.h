#ifndef CRespostaItrH
#define CRespostaItrH

#include <stdlib.h>
#include <string.h>

struct STRespostaRegistro
{
	char cidResposta[21+1];
	char cidPergunta[21+1];
	char cdsResposta[2000+1];
	char cdsScriptResposta[2000+1];
	char csqApresentacao[21+1];
	char cinEncerramento[21+1];
	char cinDisponibilidade[21+1];
};

class CRespostaItr
{
public:
	CRespostaItr();
	~CRespostaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STRespostaRegistro* Registro( void );
	STRespostaRegistro* Registro(int nPosicao);

private:
	STRespostaRegistro* stcrResposta;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidResposta, 
		      char* cidPergunta, 
			  char* cdsResposta, 
			  char* cdsScriptResposta, 
			  char* csqApresentacao,
			  char* cinEncerramento,
			  char* cinDisponibilidade );

	void ZeraResposta( void );

};

#endif