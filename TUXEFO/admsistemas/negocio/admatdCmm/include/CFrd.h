#ifndef CFeriadoH
#define CFeriadoH

#include <tuxfw.h>
#include "../include/CFrdItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )
#define FO_FRD_NACIONAL         "1"
#define FO_FRD_ESTADUAL         "2"
#define FO_FRD_MUNICIPAL        "3"
#define FO_FRD_PONTE_NACIONAL   "4"
#define FO_FRD_PONTE_ESTADUAL   "5"
#define FO_FRD_PONTE_MUNICIPAL  "6"

class CFeriado : public CFeriadoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CFeriado();
		~CFeriado();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidFeriado );
		//Recupera todos os registros
		int ListAll( void );
		int List( char* cdtDia,
				  char* cdsFeriado,
		          char* cidTipoFeriado,
				  char* cinFeriadoMovel,
				  char* cinRelatorio,
				  char* cidUF,
				  char* cidMunicipio );

        int ListPag(   char* cdtDia,
				    char* cdsFeriado,
		            char* cidTipoFeriado,
				    char* cinFeriadoMovel,
					char* cinRelatorio,
				    char* cidUF,
				    char* cidMunicipio,
					char* cAno,
                    int iPagina,
                    int &iQtdRegistros );

		int List( char* cdtDia,
				  char* cdsFeriado,
		          char* cidTipoFeriado,
				  char* cinFeriadoMovel,
				  char* cinRelatorio,
				  char* cidUF,
				  char* cidMunicipio,
				  char* cAno );


		//Relacao de feriado nao ligado a municipio e ou uf
        int ListFeriadoPag( char* cdtDia,
						   char* cdsFeriado,
					       char* cidTipoFeriado,
				           char* cinFeriadoMovel,
					       char* cinRelatorio,
					       char* cAno,
                           int iPagina,
                           int &iQtdeRegistros );

		int ListFeriado( char* cdtDia,
						 char* cdsFeriado,
				         char* cidTipoFeriado,
						 char* cinFeriadoMovel,
					     char* cinRelatorio,
						 char* cAno
						 );

		//Insere um registro
		int Insert( char* cidTipoFeriado,
					char* cidNomeFeriado,
				    char* cdsNomeFeriado,
					char* cinFeriadoMovel,
					char* cdtDia,
					char* cinRelatorio,
					char* cidUsuarioAlteracao );

		int Insert( char* cidTipoFeriado,
					char* cidNomeFeriado,
				    char* cdsNomeFeriado,
					char* cinFeriadoMovel,
					char* cdtDia,
					char* cinRelatorio,
					char* cidUsuarioAlteracao,
					char* cidUF,
					char* cidMunicipio,
					char* cidFeriadoRet );
		//Liga um feriado a um municipio
		int InsertMnc( 
			char* cidMunicipio,
			char* cidFeriado,
			char* cidUsuarioAlteracao );
		//Liga um feriado a uma UF
		int InsertUF( 
			char* cidUF,
			char* cidFeriado,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
					char* cidFeriado,
					char* cidTipoFeriado,
					char* cidNomeFeriado,
				    char* cdsNomeFeriado,
					char* cinFeriadoMovel,
					char* cdtDia,
					char* cinRelatorio,
					char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidFeriado );
		//Apaga os municipios e as ufs de um feriado
		int ApagaRelacoes( char* cidFeriado );
		//Lista relacionados
		int RelacaoFrdMnc( char* cidFeriado 
                          ,char* cidUF );
		//Lista relacionados
        int RelacaoFrdMncParPag( char* cdtDia,
							    char* cdsFeriado,
					            char* cidTipoFeriado,
							    char* cinFeriadoMovel,
					            char* cinRelatorio,
							    char* cidMunicipio,
					            char* cAno,
                                int iPagina,
                                int &iQtdeRegistros );

		int RelacaoFrdMncPar( char* cdtDia,
							  char* cdsFeriado,
					          char* cidTipoFeriado,
							  char* cinFeriadoMovel,
					          char* cinRelatorio,
							  char* cidMunicipio,
						      char* cAno );

		//Lista existentes
		int ExistemFrdMnc( char* cidFeriado 
                          ,char* cidUF );
		//Lista relacionados
		int RelacaoFrdUF( char* cidUF );
		//Lista relacionados
        int RelacaoFrdUFParPag( char* cdtDia,
							   char* cdsFeriado,
					           char* cidTipoFeriado,
							   char* cinFeriadoMovel,
					           char* cinRelatorio,
							   char* cidUF,
					           char* cAno,
                               int iPagina,
                               int &iQtdeRegistros );

		int RelacaoFrdUFPar( char* cdtDia,
							 char* cdsFeriado,
					         char* cidTipoFeriado,
							 char* cinFeriadoMovel,
					         char* cinRelatorio,
							 char* cidUF,
						     char* cAno );

		//Lista existentes
		int ExistemFrdUF( char* cidUF );
		//Dada uma data, calcula iDias uteis no futuro, somente feriados nacionais
		int DiasUteis( char* cDias, char* cDataRet );
		//Dada uma data, calcula iDias uteis no futuro, somente feriados estaduais, por sigla de UF
		int DiasUteisUF( char* cDias, char* cUF, char* cDataRet );
		//Dada uma data, calcula iDias uteis no futuro, somente feriados estaduais, por ID de UF
		int DiasUteisUFId( char* cDias, char* cidUF, char* cDataRet );
		//Dado um DDD, procura por uma UF
		int DDDToUF( char* cDDDin, char* cUFout );
		//Dado um DDD e um numerico, indicando o numero de dias, retorna uma data util
		char* DiaUtil( char* cDDD, char* cDias );
		char* DiaUtil( int iDDD, int iDias );
		//Procura um feriado na lista interna
		int FindFeriado( char* cidFeriado );
		//Retorna a relacao de Feridos por UF
		void RelacaoFeriadoUF( char* cidFeriado, XMLGen*xml_g);
		void RelacaoFeriadoMunicipio( char* cidMunicipio, char* cidFeriado, XMLGen*xml_g);
		//Copia feriados de um ano para o outro
		int CopiaCalendario( char* cAnoBase
		                    ,char* cAnoCopia
		                    ,char* cidUsuarioAlteracao
		                   );	
		int ListaPonte( char* cAnoBase );

		char* dataAtual( void );
		
		void RangeAnos( int &iAnoInicial, int& iAnoFinal );
};

#endif

