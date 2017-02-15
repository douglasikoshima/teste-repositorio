// Campanha.h: interface for the CCampanha class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __CCampanha__
#define __CCampanha__
#include <Util/Util.hpp>
#include <vector>
#include <list>
using namespace std;


class CCampanha
{

public: 
	CCampanha();
   ~CCampanha();

	// Operação de Negocio (Interface)
	static void consultarCampanhas(char*ddd,char*idTipoLinha,char*idSegmentacao,char*nrLinha,XMLGen* xml_g);
	static void consultarCampanha(char* iIdCampanha,XMLGen* xml_g);
	static void strNullCpy(char*destino,char*origem);
	
private:
  
	// Dados de Negocio
	char m_idCampanha[256];
	char m_nmCampanha[256];
	char m_dsCampanha[256];
	char m_dsRegulamento[256];
	char m_inExibeTermo[256];
	char m_cdSGP[256];
	char m_idContato[256];
	char m_nmHistoricoRelac[256];
	char m_dtInicio[25];
	char m_dtTermino[25];	

};


#endif // CCampanha