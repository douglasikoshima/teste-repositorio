#ifndef CRespostaProximaPerguntaItrH
#define CRespostaProximaPerguntaItrH

#include <stdlib.h>
#include <string.h>

struct STRespostaProximaPerguntaRegistro
{
  char cidRespPxPerg[21+1];
  char cinAtivo[21+1];
  char cidResposta[21+1];
  char cidPergunta[21+1];
};

class CRespostaProximaPerguntaItr
{
 public:
  CRespostaProximaPerguntaItr();
  ~CRespostaProximaPerguntaItr();
  int Primeiro( void );
  int Proximo( void );
  int Anterior( void );
  int Ultimo( void );
  int Quantidade( void );
  STRespostaProximaPerguntaRegistro* Registro( void );
  STRespostaProximaPerguntaRegistro* Registro(int nPosicao);

 private:
  STRespostaProximaPerguntaRegistro* stcrRespostaProximaPergunta;
  int _iQuantidade;
  int _iPosicao;

 protected:

  void Add( 
	   char* cidRespPxPerg,
	   char* cinAtivo,
	   char* cidResposta,
	   char* cidPergunta );

  void ZeraRespostaProximaPergunta( void );

};

#endif
