#ifndef CTipoSubMotivoCampanhaItrH
#define CTipoSubMotivoCampanhaItrH

#include <stdlib.h>
#include <string.h>

struct STTipoSubMotivoCampanhaRegistro
{
	char cidTipoSubMotivoCampanha[21+1];
	char csgTipoSubMotivoCampanha[256+1];
	char cdsTipoSubMotivoCampanha[256+1];
	char cinDisponibilidade[21+1];
	char cidPessoaUsuarioInclusao[21+1];
	char cidPessoaUsuarioAlteracao[21+1];
	char cdtInclusao[12+1];
	char cdtAlteracao[12+1];
	char cinAtivo[12+1];
};

class CTipoSubMotivoCampanhaItr
{
public:
	CTipoSubMotivoCampanhaItr();
	~CTipoSubMotivoCampanhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoSubMotivoCampanhaRegistro* Registro( void );
	STTipoSubMotivoCampanhaRegistro* Registro(int nPosicao);

private:
	STTipoSubMotivoCampanhaRegistro* stcrTipoSubMotivoCampanha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidTipoSubMotivoCampanha, 
		      char* csgTipoSubMotivoCampanha, 
			  char* cdsTipoSubMotivoCampanha, 
			  char* cinDisponibilidade, 
			  char* cidPessoaUsuarioInclusao,
			  char* cidPessoaUsuarioAlteracao,
			  char* cdtInclusao,
			  char* cdtAlteracao,
			  char* cinAtivo );

	void ZeraTipoSubMotivoCampanha( void );

};

#endif