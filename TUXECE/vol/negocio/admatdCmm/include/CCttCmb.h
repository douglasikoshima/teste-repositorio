/* charls */
#ifndef CContatoCombosH
#define CContatoCombosH

#include <tuxfw.h>
#include "../include/CCttCmbItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoCombos : public CContatoCombosItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoCombos();
		~CContatoCombos();
		
//Geral
		int Relacao(XMLGen*xml_g);

//TipoFechamento
		//Lista todos os tipos de fechamentos
		int ListaTipoFechamento( void );
		//Monta o xml padrao para TipoFechamento
		void GetXmlTipoFechamento( char* cNomeTag, XMLGen*xml );
//TipoProcesso
		//Lista todos os tipos de processos
		int ListaTipoProcesso( void );
		//Monta o xml padrao para TipoProcesso
		void GetXmlTipoProcesso( char* cNomeTag, XMLGen*xml );

//ContatoFolhaMensagenAviso
		//Lista todas as mensagens de aviso
		int ListaMensagemAviso( void );
		//Monta o xml padrao para ContatoFolhaMensagenAviso
		void GetXmlContatoFolhaMensagenAviso( char* cNomeTag, XMLGen*xml );

//TipoRetornoContato
		//Lista todos os tipos de retornos
		int ListaTipoRetorno( void );
		//Monta o xml padrao para TipoRetornoContato
		void GetXmlTipoRetornoContato( char* cNomeTag, XMLGen*xml );
//PrazoAtendimento
		//Lista todos os tipos de retornos
		int ListaPrazoAtendimento(  void  );
		//Monta o xml padrao para PrazoAtendimento
		void GetXmlPrazoAtendimento( char* cNomeTag, XMLGen*xml );
//PrazoAnatel
		//Lista todos os tipos de retornos
		int ListaPrazoAnatel( void );
		//Monta o xml padrao para PrazoAtendimento
		void GetXmlPrazoAnatel( char* cNomeTag, XMLGen*xml );

//PesoPrioridade
		//Lista todos os tipos de retornos
		int ListaPesoPrioridade( void );
		//Monta o xml padrao para TipoRetornoContato
		void GetXmlPesoPrioridade( char* cNomeTag, XMLGen*xml );
};

#endif

