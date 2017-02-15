#ifndef CContatoH
#define CContatoH

#include <tuxfw.h>
#include "CCttItrWF.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContato : public CContatoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContato();
		~CContato();
      void GetXmlWF( char* cNomeTag, char *cidTpRelacionamento, int idContatoAux, XMLGen*xml );

      int ListaSemContato( 
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                         );

      int ListaSemGrupo( 
                           char *idContatoParam, 
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                       );

      int ListaComGrupo( 
                           char *idGrupoParam ,
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                        );

      int ListaComContato( 
                           char *idContatoParam, 
                           char *idGrupoParam ,
                           char* cidUFOperadora ,
                           char* cidTipoLinha ,
                           char* cidTipoCarteira ,
                           char* cidSegmentacao 
                          );
      
      void TpRetorno( char *idContatoParam, 
                      int idTpRetornoParam, 
                      char * Saida );

      void URLInf( char *idContatoParam, char * Saida );

};

#endif

