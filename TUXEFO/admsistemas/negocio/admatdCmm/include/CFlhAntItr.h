#ifndef CIndicadorContatoFolhaItrH
#define CIndicadorContatoFolhaItrH

#include <stdlib.h>
#include <string.h>


struct STIndicadorContatoFolhaRegistro
{
	char cidIndicadorContatoFolha[21+1];
	char cidContato[21+1];
	char cidIndicadorAnatel[21+1];
	char csgIndicador[255+1];
	char cdsIndicador[255+1];
};

class CIndicadorContatoFolhaItr
{
public:
	CIndicadorContatoFolhaItr();
	~CIndicadorContatoFolhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STIndicadorContatoFolhaRegistro* Registro( void );
	STIndicadorContatoFolhaRegistro* Registro(int nPosicao);

private:
	STIndicadorContatoFolhaRegistro* stcrIndicadorContatoFolha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidIndicadorContatoFolha
			 ,char* cidContato
			 ,char* cidIndicadorAnatel
			 ,char* csgIndicador
			 ,char* cdsIndicador );

	void ZeraIndicadorContatoFolha( void );

};

#endif
