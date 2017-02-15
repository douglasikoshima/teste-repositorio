#ifndef CFluxoFaseGrupoItrH
#define CFluxoFaseGrupoItrH

#include <stdlib.h>
#include <string.h>

struct STFluxoFaseGrupoRegistro
{
	char cidFluxoFaseGrupo[21+1];
	char cidGrupo[21+1];
	char cidContato[21+1];
	char cidTipoLinha[21+1];
	char cidTipoPessoa[21+1];
	char cidTipoRelacionamento[21+1];
	char cidTipoCarteira[21+1];
	char cidSegmentacao[21+1];
	char cidTipoFechamentoContato[21+1];
};

class CFluxoFaseGrupoItr
{
public:
	CFluxoFaseGrupoItr();
	~CFluxoFaseGrupoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STFluxoFaseGrupoRegistro* Registro( void );
	STFluxoFaseGrupoRegistro* Registro(int nPosicao);

	void Add( char* cidFluxoFaseGrupo, 
		      char* cidGrupo, 
			  char* cidContato, 
			  char* cidTipoLinha, 
			  char* cidTipoPessoa,
			  char* cidTipoRelacionamento,
			  char* cidTipoCarteira,
			  char* cidSegmentacao,
			  char* cidTipoFechamentoContato );

private:
	STFluxoFaseGrupoRegistro* stcrFluxoFaseGrupo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void ZeraFluxoFaseGrupo( void );

};

#endif