#ifndef CSequenciaItrH
#define CSequenciaItrH

#include <stdlib.h>
#include <string.h>


struct STSequenciaRegistro
{
	char cidSequencia[21+1];
	char cidContatoGrupo[21+1];
	char cidTipoSequencia[21+1];
	char csqOrdem[21+1];
	char cidGrupo[21+1];
};

class CSequenciaItr
{
public:
	CSequenciaItr();
	~CSequenciaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSequenciaRegistro* Registro( void );
	STSequenciaRegistro* Registro(int nPosicao);

private:
	STSequenciaRegistro* stcrSequencia;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidSequencia,
			char* cidContatoGrupo,
			char* cidTipoSequencia,
			char* csqOrdem );
	void Add( 
			char* cidSequencia,
			char* cidContatoGrupo,
			char* cidTipoSequencia,
			char* csqOrdem,
			char* cidGrupo );

	void ZeraSequencia( void );

};

#endif
