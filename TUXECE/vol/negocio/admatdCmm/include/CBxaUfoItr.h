#ifndef CBaixaUfoperadoraItrH
#define CBaixaUfoperadoraItrH

#include <stdlib.h>
#include <string.h>


struct STBaixaUfoperadoraRegistro
{
	char cidBaixaUFOperadora[21+1];
	char cidBaixa[21+1];
	char cidUFOperadora[21+1];
	char cdsUFOperadora[265+1];
	char cdtInicioVigencia[12+1];
	char cdtFimVigencia[12+1];
	char cinDisponibilidade[21+1];
	char csgUF[255+1];
};

class CBaixaUfoperadoraItr
{
public:
	CBaixaUfoperadoraItr();
	~CBaixaUfoperadoraItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STBaixaUfoperadoraRegistro* Registro( void );
	STBaixaUfoperadoraRegistro* Registro(int nPosicao);

private:
	STBaixaUfoperadoraRegistro* stcrBaixaUfoperadora;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidBaixaUFOperadora,
			char* cidBaixa,
			char* cidUFOperadora,
			char* cdsUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* csgUF );

	void ZeraBaixaUfoperadora( void );

};

#endif
