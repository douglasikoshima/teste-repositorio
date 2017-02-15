#ifndef CDominioItrH
#define CDominioItrH

#include <stdlib.h>
#include <string.h>


struct STDominioRegistro
{
	char cidDominio[21+1];
	char cidTabelaDominio[21+1];
	char cidUFOperadora[21+1];
	char cidTipoLinha[21+1];
	char cnmDominio[255+1];
	char cinDisponibilidade[21+1];

	char csgTipoLinha[255+1];
	char cdsTipoLinha[255+1];
	char cdsUFOperadora[255+1];
	char cnmTabelaDominio[255+1];
};

class CDominioItr
{
public:
	CDominioItr();
	~CDominioItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STDominioRegistro* Registro( void );
	STDominioRegistro* Registro(int nPosicao);
	void Add(STDominioRegistro* stcrDominio);

private:
	STDominioRegistro* stcrDominio;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidDominio,
			char* cidTabelaDominio,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cnmDominio,
			char* cinDisponibilidade );

	void Add(
		char* cidDominio,
		char* cnmDominio,
		char* cinDisponibilidade,
		char* cidTabelaDominio,
		char* cnmTabelaDominio,
		char* cidUFOperadora,
		char* cdsUFOperadora,
		char* cidTipoLinha,
		char* csgTipoLinha,
		char* cdsTipoLinha);

	void ZeraDominio( void );

};

#endif
