#ifndef CContatoInformacaoH
#define CContatoInformacaoH

#include <tuxfw.h>
#include "../include/CCttInfItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoInformacao : public CContatoInformacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoInformacao();
		~CContatoInformacao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXmlWorkflow( char* cNomeTag, XMLGen*xml );
		void GetXmlUFOperadora( char* cNomeTag, XMLGen*xml );
		void GetXmlTipoLinha( char* cNomeTag, XMLGen*xml );
		void GetXmlTipoCliente( char* cNomeTag, XMLGen*xml );
		//Recupera os varios registros de um contato 
		int ListId( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( char* cidContato,
					char* cidUFOperadora,
					char* cidTipoLinha,
					char* cidTipoCliente,
					char* cnmURLContatoInformacao,
					char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( char* cidContatoInformacao,
					char* cidContato,
					char* cidUFOperadora,
					char* cidTipoLinha,
					char* cidTipoCliente,
					char* cnmURLContatoInformacao,
					char* cidUsuarioAlteracao );
		//Apaga um registro
		int Delete( char* cidContatoInformacao );
		//Apaga todos os registros de um determinado contato
		int DeletePorContato(  char* cidContato );
		//Recupera uma lista de UFOperadora relacionadas
		int ListUFOperadoraR( char* cidContato );
		//Recupera uma lista de UFOperadora nao relacionadas
		int ListUFOperadoraNR( char* cidContato );
		//Recupera uma lista de TipoLinha relacionadas
		int ListTipoLinhaR( char* cidContato );
		//Recupera uma lista de TipoLinha nao relacionadas
		int ListTipoLinhaNR( char* cidContato );
		//Recupera uma lista de TipoCliente relacionadas
		int ListTipoClienteR( char* cidContato );
		//Recupera uma lista de TipoCliente nao relacionadas
		int ListTipoClienteNR( char* cidContato );
		//Procura pelas 3 variaveis na lista em memoria
		int FindPar( char* cidUFOperadora
					,char* cidTipoLinha
					,char* cidTipoCliente );
		//Retorna a relacao de informacoes, por contato
		int Relacao(char* cidContato,XMLGen*xml_g);
		
};

#endif

