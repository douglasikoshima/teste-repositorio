#ifndef CCampanhaQuestionarioItrH
#define CCampanhaQuestionarioItrH

#include <stdlib.h>
#include <string.h>

struct STCampanhaQuestionarioRegistro
{
	char cidCampanhaQuestionario[21+1];
	char cidCanalCampanha[21+1];
	char cidPergunta[21+1];
	char cidUser[21+1];
	char cdtUltimaAlteracao[21+1];
	char cinAtivo[21+1];
};

class CCampanhaQuestionarioItr
{
public:
	CCampanhaQuestionarioItr();
	~CCampanhaQuestionarioItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCampanhaQuestionarioRegistro* Registro( void );
	STCampanhaQuestionarioRegistro* Registro(int nPosicao);

private:
	STCampanhaQuestionarioRegistro* stcrCampanhaQuestionario;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidCampanhaQuestionario, 
			  char* cidCanalCampanha, 
			  char* cidPergunta,
	          char* cidUser,
	          char* cdtUltimaAlteracao );

	void Add( char* cidCampanhaQuestionario, 
			  char* cidCanalCampanha, 
			  char* cidPergunta,
			  char* cinAtivo );

	void ZeraCampanhaQuestionario( void );

};

#endif