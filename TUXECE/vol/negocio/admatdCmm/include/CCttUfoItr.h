#ifndef CContatoUfoperadoraItrH
#define CContatoUfoperadoraItrH

#include <stdlib.h>
#include <string.h>


struct STContatoUfoperadoraRegistro
{
	char cidContatoUFOperadora[21+1];
	char cidContato[21+1];
	char cidUFOperadora[21+1];
	char cdsUFOperadora[265+1];
	char cdtInicioVigencia[12+1];
	char cdtFimVigencia[12+1];
	char cinDisponibilidade[21+1];
	char csgUF[255+1];
};

class CContatoUfoperadoraItr
{
public:
	CContatoUfoperadoraItr();
	~CContatoUfoperadoraItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoUfoperadoraRegistro* Registro( void );
	STContatoUfoperadoraRegistro* Registro(int nPosicao);

private:
	STContatoUfoperadoraRegistro* stcrContatoUfoperadora;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoUFOperadora,
			char* cidContato,
			char* cidUFOperadora,
			char* cdsUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* csgUF );

	void ZeraContatoUfoperadora( void );

};

#endif
