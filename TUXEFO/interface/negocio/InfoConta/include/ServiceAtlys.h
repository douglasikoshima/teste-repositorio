#ifndef SERVICEATLYS_H_HEADER_INCLUDED_BD901657
#define SERVICEATLYS_H_HEADER_INCLUDED_BD901657
#include "RemoteCallApi.h"
#include "CFileXenos.h"

struct stGetBillImage{
	char*acctNbr;	
	char*cycleCd;
	char*cycleEndDt;
	char*startKey;
	char*fileFormat;
	char*documentType;
	char*afpFile;
	char*accessNbr;
	bool areMore;
};

class ServiceAtlys : public RemoteCallApi
{
  public:
    // Faz a chamada a essa API remota
    DOMNode* inputGetBillDates(char*acctNbr);

    // Faz a chamada a essa API remota
    DOMNode* inputGetBillImage(CFileXenos *file,struct stGetBillImage*stImage,bool areMore=false);

	// Recupera os telefones da cnota
	DOMNode* inputSearchSubscriptionByAccountNumber(char*acctNbr);

  private:
	  // atributos
	TuxHelper tuxhp;
	CMemoryManager m_mem;

};



#endif /* SERVICEATLYS_H_HEADER_INCLUDED_BD901657 */
