#ifndef CBaixaFiltroH
#define CBaixaFiltroH

#include <tuxfw.h>
#include "../include/CBxaFtrItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CBaixaFiltro : public CBaixaFiltroItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CBaixaFiltro();
		~CBaixaFiltro();
		
//Geral
		int Relacao(char* cidBaixa,XMLGen*xml_g);

//TipoLinha
		int ListAllTipoLinha( void );
		int ListIdTipoLinha( char* cidTipoLinha );
		//Apaga todas as relacoes com TipoLinha
		int DeleteTipoLinhaIdBaixa( char* cidBaixa );
		//Retorna uma relacao de TipoLinha
		int RelacaoTipoLinha( char* cidBaixa );
		//Retorna uma relacao de TipoLinha nao relacionados
		int ExistentesTipoLinha( char* cidBaixa );
		//Relaciona TipoLinha com contato
		int RelacionaTipoLinha( char* cidBaixa
                               ,char* cidTipoLinha
			                   ,char* cidUsuarioAlteracao );
		//Monta o xml padrao para TipoLinha
		void GetXmlTipoLinha( char* cNomeTag, XMLGen*xml );

//TipoRelacionamento
		int ListAllTipoRelacionamento( void );
		int ListIdTipoRelacionamento( char* cidTipoRelacionamento );
		//Apaga todas as relacoes com TipoRelacionamento
		int DeleteTipoRelacionamentoIdBaixa( char* cidBaixa );
		//Retorna uma relacao de TipoRelacionamento
		int RelacaoTipoRelacionamento( char* cidBaixa );
		//Retorna uma relacao de TipoRelacionamento nao relacionados
		int ExistentesTipoRelacionamento( char* cidBaixa );
		//Relaciona TipoRelacionamento com contato
		int RelacionaTipoRelacionamento( char* cidBaixa
                                		,char* cidTipoRelacionamento
			                    		,char* cidUsuarioAlteracao );
		//Monta o xml padrao para TipoRelacionamento
		void GetXmlTipoRelacionamento( char* cNomeTag, XMLGen*xml );
		void GetXmlTipoRelacionamento2( char* cNomeTag, XMLGen*xml );

//Segmentacao
		int ListAllSegmentacao( void );
		//Apaga todas as relacoes com Segmentacao
		int DeleteSegmentacaoIdBaixa( char* cidBaixa );
		//Retorna uma relacao de Segmentacao
		int RelacaoSegmentacao( char* cidBaixa );
		//Retorna uma relacao de Segmentacao nao relacionados
		int ExistentesSegmentacao( char* cidBaixa );
		//Relaciona Segmentacao com contato
		int RelacionaSegmentacao( char* cidBaixa
                                 ,char* cidSegmentacao
			                     ,char* cidUsuarioAlteracao );
		//Monta o xml padrao para Segmentacao
		void GetXmlSegmentacao( char* cNomeTag, XMLGen*xml );

//TipoCarteira
		int ListAllTipoCarteira( void );
		//Apaga todas as relacoes com TipoCarteira
		int DeleteTipoCarteiraIdBaixa( char* cidBaixa );
		//Retorna uma relacao de TipoCarteira
		int RelacaoTipoCarteira( char* cidBaixa );
		//Retorna uma relacao de TipoCarteira nao relacionados
		int ExistentesTipoCarteira( char* cidBaixa );
		//Relaciona TipoCarteira com contato
		int RelacionaTipoCarteira( char* cidBaixa
                                  ,char* cidTipoCarteira
			                      ,char* cidUsuarioAlteracao );
		//Monta o xml padrao para TipoCarteira
		void GetXmlTipoCarteira( char* cNomeTag, XMLGen*xml );
	
};

#endif

