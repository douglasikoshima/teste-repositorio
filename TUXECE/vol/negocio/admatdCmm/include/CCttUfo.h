#ifndef CContatoUfoperadoraH
#define CContatoUfoperadoraH

#include <tuxfw.h>
#include "../include/CCttUfoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoUfoperadora : public CContatoUfoperadoraItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoUfoperadora();
		~CContatoUfoperadora();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoUFOperadora );
		//Recupera todos os registros de um certo contato
		int ListIdContato( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidContato,
			char* cidUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidContatoUFOperadora,
			char* cidContato,
			char* cidUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* cidUsuarioAlteracao );
		//Apaga um registro
		int Delete( char* cidContatoUFOperadora );
		//Apaga um ou mais registros
		int EraseFlh( char* cidContato );
		//Apaga um ou mais registros
		int EraseUfo( char* cidUFOperadora );
		//UFOperadoras relacionadas
		int Relacionadas( char* cidContato );
		//UFOperadoras nao relacionadas
		int NaoRelacionadas( char* cidContato );
		//Retorna AdmOperadorasVO
		int Relacao( char* cidContato, XMLGen*xml );
		//Procura por um item na lista
		int Find( char* cidContato, char* cidUFOperadora );
		//Nao faz nada alem de adionar os dados ao iterator
	    void Adicionar( char* cidContatoUFOperadora,
		                char* cidContato,
		    		    char* cidUFOperadora );
};

#endif

