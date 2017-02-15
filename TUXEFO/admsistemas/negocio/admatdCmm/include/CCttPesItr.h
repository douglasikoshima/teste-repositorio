#ifndef CPesquisaSatisfacaoUFItrH
#define CPesquisaSatisfacaoUFItrH

#include <stdlib.h>
#include <string.h>


struct STPesquisaSatisfacaoUFRegistro
{
	char cidPesquisaSatisfacaoUF[21+1];
	char cidContato[21+1];
	char cidUFOperadora[21+1];
	char cdsUFOperadora[255+1];
	char cidTipoPessoa[21+1];
	char csgTipoPessoa[255+1];
	char cdsTipoPessoa[255+1];
	char cidPesquisaSatisfacao[21+1];
	char cdsPesquisaSatisfacao[255+1];
};

class CPesquisaSatisfacaoUFItr
{
public:
	CPesquisaSatisfacaoUFItr();
	~CPesquisaSatisfacaoUFItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPesquisaSatisfacaoUFRegistro* Registro( void );
	STPesquisaSatisfacaoUFRegistro* Registro(int nPosicao);

private:
	STPesquisaSatisfacaoUFRegistro* stcrPesquisaSatisfacaoUF;
	int _iQuantidade;
	int _iPosicao;

protected:

	void AddUFOperadora( char* cidContato
						,char* cidUFOperadora
						,char* cdsUFOperadora );

	void AddPesquisaSatisfacao( char* cidContato
					           ,char* cidPesquisaSatisfacao
					           ,char* cdsPesquisaSatisfacao );

	void AddTipoPessoa( char* cidContato
					   ,char* cidTipoPessoa
					   ,char* csgTipoPessoa
					   ,char* cdsTipoPessoa );

	void Add( char* cidPesquisaSatisfacaoUF,
			  char* cidContato,
			  char* cidUFOperadora,
			  char* cdsUFOperadora,
			  char* cidTipoPessoa,
			  char* csgTipoPessoa,
			  char* cdsTipoPessoa,
			  char* cidPesquisaSatisfacao,
			  char* cdsPesquisaSatisfacao );

	void ZeraPesquisaSatisfacaoUF( void );

};

#endif
