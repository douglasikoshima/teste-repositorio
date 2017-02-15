#ifndef CBaixaMensagemH
#define CBaixaMensagemH

#include <tuxfw.h>
#include "../include/CBxaListarMsgItr.h"

#ifndef strlennull
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )
#endif

class CBaixaMensagem : public CBaixaMensagemItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CBaixaMensagem();
		~CBaixaMensagem();

		//Retorna um lista de Tipo de mensagens
      void GetXmlTipoComunicacao( char* cNomeTagTipo, XMLGen*xml );
      
      int ListaMsgBaixaWF( char *idFormaRetParam,
                          char *idMsgBaixaParam,
                          char *dsMsgBaixaParam
                        );
      
      int ListTpComunicWF( char *idBaixaParam );
      
      void GetXmlTpComunicWF( char* cNomeTagMen, 
                             char* cNomeTagTipo, 
                             char* cNomeTagMsg, 
                             XMLGen*xml 
                           );
      
      int ListTpComunicWFSel( char *idBaixaParam, int idTpComunicParam );


};

#endif

