#ifndef CBaixaUfoperadoraH
#define CBaixaUfoperadoraH

#include <tuxfw.h>
#include "../include/CBxaUfoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CBaixaUfoperadora : public CBaixaUfoperadoraItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CBaixaUfoperadora();
		~CBaixaUfoperadora();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidBaixaUFOperadora );
		//Recupera todos os registros de um certo contato
		int ListIdBaixa( char* cidBaixa );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidBaixa,
			char* cidUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidBaixaUFOperadora,
			char* cidBaixa,
			char* cidUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* cidUsuarioAlteracao );
		//Apaga um registro
		int Delete( char* cidBaixaUFOperadora );
		//Apaga um ou mais registros
		int EraseFlh( char* cidBaixa );
		//Apaga um ou mais registros
		int EraseUfo( char* cidUFOperadora );
		//UFOperadoras relacionadas
		int Relacionadas( char* cidBaixa );
		//UFOperadoras nao relacionadas
		int NaoRelacionadas( char* cidBaixa );
		//Retorna AdmOperadorasVO
		int Relacao( char* cidBaixa, XMLGen*xml );
		//Procura por um item na lista
		int Find( char* cidBaixa, char* cidUFOperadora );
		//Nao faz nada alem de adionar os dados ao iterator
	    void Adicionar( char* cidBaixaUFOperadora,
		                char* cidBaixa,
		    		    char* cidUFOperadora );
};

#endif

