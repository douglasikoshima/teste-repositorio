#ifndef CTabConsRelacItrH
#define CTabConsRelacItrH

#include <stdlib.h>
#include <string.h>

enum { TP_UPDATE, TP_INSERT };


struct STTabConsRelacRegistro
{
    char nrDocumento[128];
    char idTipoRelacionamento[40];
};


struct STTabEmpresaRelacRegistro
{
    char nrDocumento[64];
    char idFormulario[64];
    char vlPeso[64];
    char inAtivo[3];
	char intipoassociacao[129];
};

class CTabConsRelacItr
{
public:
    CTabConsRelacItr();
    ~CTabConsRelacItr();

    int Primeiro( void );
    int Proximo( void );
    int Anterior( void );
    int Ultimo( void );
    int Quantidade( void );
    STTabConsRelacRegistro* Registro( void );
    STTabConsRelacRegistro* Registro(int nPosicao);
    /* void Add(const char* cidPessoaConta); */
    void Add(const char* nrDocumentoPrm, const char* idTipoRelacionamentoPrm );

private:
    STTabConsRelacRegistro* stcrTabConsRelac;
    int _iQuantidade;
    int _iPosicao;
    const int tamBlocoItr;

protected:

    void ZeraTabConsRelac( void );

};

class CTabEmpresaRelacItr
{
public:
    CTabEmpresaRelacItr();
    ~CTabEmpresaRelacItr();

    int Primeiro( void );
    int Proximo( void );
    int Anterior( void );
    int Ultimo( void );
    int Quantidade( void );
    STTabEmpresaRelacRegistro* Registro( void );
    STTabEmpresaRelacRegistro* Registro(int nPosicao);
    void Add(const char* nrDocumentoPrm, const char* idFormularioPrm, const char* vlPeso, const char* inAtivo );
    void Add(const char* nrDocumentoPrm, const char* idFormularioPrm, const char* vlPeso, const char* inAtivo, const char * intipoassociacao );

private:
    STTabEmpresaRelacRegistro* stcrTabEmpresaRelac;
    int _iQuantidade;
    int _iPosicao;
    const int tamBlocoItr;

protected:

    void ZeraTabConsRelac( void );

};

#endif
