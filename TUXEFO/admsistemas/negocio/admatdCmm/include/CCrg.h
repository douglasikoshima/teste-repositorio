#ifndef CCargoH
#define CCargoH

#include <tuxfw.h>
#include "../include/CCrgItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'
#define oraToStr(bstr,vchar)   if(!bstr) strncpy(bstr, (const char *)vchar.arr, vchar.len)
#define strToOra(vchar,bstr)   vchar.len = strlen(bstr);strncpy((char *)vchar.arr,bstr,vchar.len);vchar.arr[vchar.len] = 0


class CCargo : public CCargoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CCargo();
		~CCargo();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidCargo );
		//Recupera todos os registros
		int ListAll( void );
		//Lista pesquisa
		int List(
			char* cidCargo,
			char* cnmCargo,
			char* cidUsuarioAlteracao );
		//Lista todos por Id Nivel
        int ListCargoPorIdNivel( char* cidNivelCargo );

		//Insere um registro
		int Insert(
			char* cnmCargo,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update(
			char* cidCargo,
			char* cnmCargo,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidCargo );
		//Lista relacionados e existentes
		int RelacaoCA( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoCA( char* cidAtribuicao );
		//Lista existentes
		int ExistemCA( char* cidAtribuicao );
		//Lista relacionados e existentes
		int RelacaoNC( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoNC( char* cidNivel );
		//Lista existentes
		int ExistemNC( char* cidNivel );
		//Insere e lista o novo cargo inserido
		int InsertList( char* cnmCargo, char* cidUsuarioAlteracao );
};

#endif

