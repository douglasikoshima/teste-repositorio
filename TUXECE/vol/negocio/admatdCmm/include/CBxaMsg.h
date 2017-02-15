#ifndef CBaixaMensagemH
#define CBaixaMensagemH

#include <tuxfw.h>
#include "../include/CBxaMsgItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CBaixaMensagem : public CBaixaMensagemItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CBaixaMensagem();
		~CBaixaMensagem();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTagMen
		            ,char* cNomeTagTipo
					,char* cNomeTagMsg
		            ,XMLGen*xml );
		//Retorna um lista de Tipo de mensagens
		void GetXmlTipoComunicacao( char* cNomeTagTipo, 
		                            XMLGen*xml );
		//Retorna um lista de mensagens baixa
		void GetXmlMensagemBaixa( char* cNomeTagTipo, 
		                          XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidBaixaMensagem );
		//Recupera todos os registros
		int ListAll( void );
		//Recupera todos os registros ligados a uma baixa
		int ListIdBaixa( char* cidBaixa );
		//REcupera todos os tipos de comunicacao
		int ListTipoComunicacao( void );
		//REcupera todos as Mensagens Baixa
		int ListMensagemBaixa( void );
		//Insere um registro
		int Insert( char* cidBaixa
			       ,char* cidTipoComunicacao
			       ,char* cidMensagem
			       ,char* cdsMensagem
			       ,char* cidUsuarioAlteracao );

		//Atualiza um registro
		int Update( char* cidBaixaMensagem
			       ,char* cidBaixa
			       ,char* cidTipoComunicacao
			       ,char* cidMensagem
			       ,char* cdsMensagem
			       ,char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidBaixaMensagem );
		//Apaga um ou mais registros
		int EraseBxa( char* cidBaixa );


//Codigos abaixo de autoria de Marcelo Nunez
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

