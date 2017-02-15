#ifndef CLinhaBaseItrH
#define CLinhaBaseItrH

#include <stdlib.h>
#include <string.h>


struct STLinhaBaseRegistro
{
	char cidLinhaBase[21+1];
	char cidAreaRegistro[21+1];
	char cnrLinha[21+1];
	char cnrMim[255+1];
	char cnrDigitoLinha[21+1];
	char cidEstadoLinha[21+1];
	char csqSincronismoEstado[21+1];
	char ctsSincronismoEstado[21+1];
	char cdtEstadoLinha[21+1];
	char cdsMotivoEstado[255+1];
//Expansao	
	char cidPessoa[21+1];
	char cidTipoPessoa[21+1];
	char cidLinhaTelefonica[21+1];
};

class CLinhaBaseItr
{
public:
	CLinhaBaseItr();
	~CLinhaBaseItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STLinhaBaseRegistro* Registro( void );
	STLinhaBaseRegistro* Registro(int nPosicao);

private:
	STLinhaBaseRegistro* stcrLinhaBase;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidLinhaBase,
			char* cidAreaRegistro,
			char* cnrLinha,
			char* cnrMim,
			char* cnrDigitoLinha,
			char* cidEstadoLinha,
			char* csqSincronismoEstado,
			char* ctsSincronismoEstado,
			char* cdtEstadoLinha,
			char* cdsMotivoEstado );

	void Add( 
			char* cidPessoa,
			char* cidTipoPessoa,
			char* cnrDigitoLinha,
			char* cidLinhaTelefonica );
	void ZeraLinhaBase( void );

};

#endif
