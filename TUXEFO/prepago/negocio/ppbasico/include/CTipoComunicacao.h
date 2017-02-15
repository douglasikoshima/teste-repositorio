#ifndef CTipoComunicacaoH
#define CTipoComunicacaoH

#include <tuxfw.h>
#include <CTipoComunicacaoItr.h>


class CTipoComunicacao : public CTipoComunicacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoComunicacao();
		~CTipoComunicacao();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Mota um xml para listas combos
		void getXmlLista( char* cNomeTag, XMLGen*xml );
		void getXmlCombo( char* cNomeTag
					     ,char* cidTipoComunicacao
						 ,char* csgTipoComunicacao
						 ,char* cdsTipoComunicacao
						 ,XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoComunicacao );
		//Recupera todos os registros
		int ListAll( void );
		//Recupera todos os registros que sao telefones
		int ListTipoTelefones( void );
		//Procura por uma sigla no iterator (NAO EFETUAR PESQUISA NO BANCO)
		int FindSigla( char* csgTipoComunicacao );
};

#endif

