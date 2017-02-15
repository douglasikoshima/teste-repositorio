#ifndef CSubCampanhaHistoricoH
#define CSubCampanhaHistoricoH

#include <tuxfw.h>
#include "CSubCampanhaHistoricoItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CSubCampanhaHistorico : public CSubCampanhaHistoricoItr
{

    private:
		int InserirRaiz();
		int InserirNode();		

	public:
		CSubCampanhaHistorico();
		~CSubCampanhaHistorico();
		int ListId( char* cidSubCampanhaHistorico );
		int ListAll( void );
		int ListNomeSubCampanha( char* cnmSubCampanha );

		int Insert( char* cidSubCampanhaFixa,
					char* cdsScriptSubCampanha,
					char* cinClienteTelefonica,
					char* cqtMaximaAgenda,
					char* cdtInicio,
					char* cdtTermino,
					char* csqVersao,
					char* cinReincidente,
					char* cidTipoCampanha,
					char* sqApresentacao,
					char* cidUser,
					char* cdsNmSubCampanha);
		//Insere sem sqVersao (tem o parametro mas nao considera, para poder sobrecarregar
		int Insert( char* cidSubCampanhaFixa,
					char* cdsScriptSubCampanha,
					char* cinClienteTelefonica,
					char* cqtMaximaAgenda,
					char* cdtInicio,
					char* cdtTermino,
					char* csqVersao,
					char* cinReincidente,
					char* cidTipoCampanha,
					char* sqApresentacao,//Nao utilizado
					char* cidUser,
					char* cdsNmSubCampanha,
					char* cinDisponibilidade);

		//Insere uma nova subcampanha historico, que sera uma copia de uma já existente
		//Ou seja cria uma nova versao
		int Duplicar( char* cidSubCampanhaHistoricoOrigem, 
		              char* cidUser );

		int Update( char* cidSubCampanhaHistorico, 
				    char* cidSubCampanhaFixa, 
					char* cdsScriptSubCampanha, 
					char* cinClienteTelefonica, 
					char* cqtMaximaAgenda,
					char* cdtInicio,
					char* cdtTermino,
					char* csqVersao,
					char* cinReincidente,
					char* cidTipoCampanha,					
					char* sqApresentacao,
					char* cidUser,
					char* cdsNmSubCampanha);	

		int Update(	char* cidSubCampanhaHistorico,
					char* sqApresentacao,
					char* cidUser);

		int UpdateNome(	char* cdsNmSubCampanha,
					    char* cidSubCampanhaFixa,
					    char* cidUser);

		void GetXml( char* cNomeTag, XMLGen*xml );

		void AtualizaSubCampanhaAtual( char* cidSubCampanhaFixa, 
			                           char* cidSubCampanhaHistorico,
									   char* cidUser );

		void AtualizaSubCampanhaFidelizacao( char* cidSubCampanhaHistorico,
									         char* cidUser );

};



#endif

