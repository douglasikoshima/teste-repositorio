#ifndef CRespostaItrH
#define CRespostaItrH

#include <stdlib.h>
#include <string.h>

struct STRespostaRegistro
{
	char cidPesquisaSatisfacao[21+1];
	char cnmPesquisaSatisfacao[255+1];
	char cidResposta[21+1];
	char cidPergunta[21+1];
	char cdsResposta[256+1];
	char cdsScriptResposta[256+1];
	char csqApresentacao[21+1];
	char cinEncerramento[21+1];
	char cinDisponibilidade[21+1];
	char cidPerguntaSalto[21+1];
	char cinAtivo[21+1];
	int  isqApresentacao;
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

	void Add( char* cidResposta, 
		      char* cidPergunta, 
			  char* cdsResposta, 
			  char* cdsScriptResposta, 
			  char* csqApresentacao,
			  char* cinEncerramento,
			  char* cinDisponibilidade,
			  char* cidPerguntaSalto,
			  char* cinAtivo );

	void Add( char* cidResposta, 
		      int   isqApresentacao );

private:
	STRespostaRegistro* stcrResposta;
	int _iQuantidade;
	int _iPosicao;

protected:

	void ZeraResposta( void );

};

#endif