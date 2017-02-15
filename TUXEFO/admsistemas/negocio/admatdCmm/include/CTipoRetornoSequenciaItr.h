#ifndef CTipoRetornoSequenciaItrH
#define CTipoRetornoSequenciaItrH

#include <stdlib.h>
#include <string.h>


struct STTipoRetornoSequenciaRegistro
{
	char cidTipoRetornoSequencia[21+1];
	char cidContatoTipoRetorno[21+1];
	char cidSequencia[21+1];
	char cidContato[21+1];
	char cidTipoSequencia[21+1];
	char cidGrupo[21+1];
	char cidTipoRetornoContato[21+1];
};

class CTipoRetornoSequenciaItr
{
public:
	CTipoRetornoSequenciaItr();
	~CTipoRetornoSequenciaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoRetornoSequenciaRegistro* Registro( void );
	STTipoRetornoSequenciaRegistro* Registro(int nPosicao);

private:
	STTipoRetornoSequenciaRegistro* stcrTipoRetornoSequencia;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoRetornoSequencia,
			char* cidContatoTipoRetorno,
			char* cidSequencia );
	void Add( 
			char* cidTipoRetornoSequencia,
			char* cidContatoTipoRetorno,
			char* cidSequencia,
			char* cidContato,
			char* cidTipoSequencia,
			char* cidGrupo,
			char* cidTipoRetornoContato );

	void ZeraTipoRetornoSequencia( void );

};

#endif
