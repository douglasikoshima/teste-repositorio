#ifndef CCSafePointerH
#define CCSafePointerH

#include <tuxfw.h>
#include <stdlib.h>
#include <string.h>

#define SP_TAMANHO_BLOCO 10

/*******************************************************************************
 * Estrutura referente ao processo de desmembramento de nomes
 *******************************************************************************/
#define LEN_NOME          255
#define LEN_EOS             1
#define LEN_NOME_PRIMEIRO LEN_NOME
#define LEN_NOME_MEIO     LEN_NOME
#define LEN_NOME_FIM      LEN_NOME

typedef struct {
    char szNomeCompleto[LEN_NOME + LEN_EOS];
    char szNomePrimeiro[LEN_NOME_PRIMEIRO + LEN_EOS];
    char szNomeMeio[LEN_NOME_MEIO + LEN_EOS];
    char szNomeFim[LEN_NOME_FIM + LEN_EOS];
} TDesmembraNome;

class CSafePointer : public TuxHelper
{
	public:
		CSafePointer();
		~CSafePointer();
		char* getTag( DOMNode* pdnNode, char* pcNomeTag );
		char* getTag( DOMNode* pdnNode, char* pcNomeTag, int nNivel );
		void DesmembraNome(TDesmembraNome *ptDesmembraNome);
		void NormalizaNome(char *pszString);
		void RetiraMultiplosEspacos(char *pszString);
		void alltrim(char *pszString);
		void rtrim(char *pszString);
		void ltrim(char *pszString);

	private:
		char** pcaPointers;
		int _iQuantidadeBlocos;
		int _iPonteirosUtilizados;

		void alocaBloco( void );
		void desalocaTudo( void );
};

#endif