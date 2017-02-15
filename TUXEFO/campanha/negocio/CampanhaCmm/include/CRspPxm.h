#ifndef CRespostaProximaPerguntaH
#define CRespostaProximaPerguntaH

#include <tuxfw.h>

#include "CRspPxmItr.h"


class CRespostaProximaPergunta : public CRespostaProximaPerguntaItr
{
 private:
  int InserirRaiz();
  int InserirNode();

 public:
  CRespostaProximaPergunta();
  ~CRespostaProximaPergunta();

  //Monta o xml padrao desta tabela
  void GetXml( char* cNomeTag, XMLGen*xml );
  //Recupera um registro
  int ListId( char* cidRespPxPerg );
  //Recupera todos os registros
  int ListAll( void );
  //Insere um registro
  int Insert( 
	     char* cidResposta,
	     char* cidPergunta,
	     char* cidUsuarioAlteracao );
  //Atualiza um registro
  int Update( 
	     char* cidRespPxPerg,
	     char* cinAtivo,
	     char* cidResposta,
	     char* cidPergunta,
	     char* cidUsuarioAlteracao );
  //Atualiza um registro ou insere se nao existir
  int UpdIns( char* cidResposta, char* cidPergunta, char* cidUsuarioAlteracao );

  //Apaga um ou mais registros
  int Delete( char* cidRespPxPerg );

  int DelIdRespProximaPergunta(char* cidResposta,char* cidPergunta);
};

#endif

