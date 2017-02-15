#ifndef CPesquisaSatisfacaoUFH
#define CPesquisaSatisfacaoUFH

#include <tuxfw.h>
#include "../include/CCttPesItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPesquisaSatisfacaoUF : public CPesquisaSatisfacaoUFItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CPesquisaSatisfacaoUF();
		~CPesquisaSatisfacaoUF();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXmlUFOperadora( char* cNomeTag, XMLGen*xml );
		void GetXmlPesquisaSatisfacao( char* cNomeTag, XMLGen*xml );
		void GetXmlTipoPessoa( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidPesquisaSatisfacaoUF );
		//Recupera todos os registros
		int ListIdContato( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Recupera todos os registros
		int ListContatoQuestionario( char* cidContato, char* cidPesquisaSatisfacao );
		int ListContatoQuestionario( 
									char* cidContato, 
									char* cidPesquisaSatisfacao, 
									char* cidTipoPessoa );
		//Insere um registro
		int Insert( char* cidContato,
					char* cidUFOperadora,
					char* cidPesquisaSatisfacao,
					char* cidTipoPessoa,
					char* cidUsuarioAlteracao );
		//Copia os dados de um certo idcontato para outro.
		int Insert( char* cidContatoOrigem,
                    char* cidContatoDestino,
					char* cidUsuarioAlteracao );
		void AddExistentes( char* cidContato,
						    char* cidUFOperadora,
						    char* cidPesquisaSatisfacao,
						    char* cidTipoPessoa );
		//Apaga um registro
		int Delete( char* cidPesquisaSatisfacaoUF );
		int Delete( char* cidContato,
					char* cidUFOperadora,
					char* cidPesquisaSatisfacao,
					char* cidTipoPessoa );
		//Apaga todos os registros de um determinado contato
		int DeletePorContato(  char* cidContato );
		//Apaga todos os registros de um determinado contato
		int DeletePorContatoQuestionario( char* cidContato, char* cidPesquisaSatisfacao );
      
      int UFDelete( char * cidContato, char * cidQuestionario, char *cidTipoPessoa );
      
		//Recupera uma lista de UFOperadora relacionadas
		int ListUFOperadoraR( char* cidContato, char* cidPesquisaSatisfacao );
		int ListUFOperadoraR( char* cidContato, char* cidPesquisaSatisfacao, char* cidTipoPessoa );
		//Recupera uma lista de UFOperadora nao relacionadas
		int ListUFOperadoraNR( char* cidContato, char* cidPesquisaSatisfacao );
		int ListUFOperadoraNR( char* cidContato, char* cidPesquisaSatisfacao, char* cidTipoPessoa );
		//Recupera uma lista de PesquisaSatisfacao relacionadas
		int ListPesquisaSatisfacaoR( char* cidContato );
		//Recupera uma lista de PesquisaSatisfacao nao relacionadas
		int ListPesquisaSatisfacaoNR( char* cidContato );
		//Recupera uma lista de PesquisaSatisfacao nao relacionadas
		int ListPesquisaSatisfacao( void );
		//Recupera todos os tipos de pessoa
		int ListTipoPessoaAll( void );
		//Recupera uma lista de TipoPessoa relacionadas
		int ListTipoPessoaR( char* cidContato, char* cidPesquisaSatisfacao );
		//Recupera uma lista de TipoPessoa nao relacionadas
		int ListTipoPessoaNR( char* cidContato, char* cidPesquisaSatisfacao );
		//Procura pelas 2 variaveis na lista em memoria
		int FindPar( char* cidContato
					,char* cidUFOperadora
					,char* cidPesquisaSatisfacao
					,char* cidTipoPessoa );
		//Retorna a relacao de informacoes, por contato
		int Relacao(char* cidContato,char* cidPesquisaSatisfacao,XMLGen*xml_g);
		int Relacao(char* cidContato,char* cidPesquisaSatisfacao, char* cidTipoPessoa,XMLGen*xml_g);
		
};

#endif

