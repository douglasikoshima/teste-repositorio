#ifndef CDominioH
#define CDominioH

#include <tuxfw.h>
#include "../include/CDomItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CDominio : public CDominioItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CDominio();
		~CDominio();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXmlList(char* cNomeTag, XMLGen*xml);
		void GetXmlTabelaDominio(char* cNomeTag, XMLGen*xml);
		void GetUFOperadora(char* cNomeTag, XMLGen*xml);
		void GetTipoLinha(char* cNomeTag, XMLGen*xml);
		void GetXmlListPar(char* cNomeTag, XMLGen*xml);
		//Recupera um registro
		int ListId( char* cidDominio );
		int ListPar(char *cidDominio);
		int ListPar(char *cidDominio,
			char *cnmDominio,
			char *cinDisponibilidade ,
			char *cidUFOperadora,
			char *cidTipoLinha,
			char *cidTabelaDominio);
		// Lista todos os registros com os relacionamentos
		int ListAllById();
		//Recupera todos os registros
		int ListAll( void );
		int ListTabelaDominio(void);
		int ListUFOperadora(void);
		int ListTipoLinha(void);
		//Insere um registro
		int Insert(
					 	 char* cidTabelaDominio
					 	,char* cidUFOperadora
					 	,char* cidTipoLinha
					 	,char* cnmDominio
					 	,char* cinDisponibilidade
					 	,char* cidUser
		);
		//Edita um registro
		int Update(
					 	 char* cidDominio
					 	,char* cidTabelaDominio
					 	,char* cidUFOperadora
					 	,char* cidTipoLinha
					 	,char* cnmDominio
					 	,char* cinDisponibilidade
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidDominio );
};

#endif

