#ifndef TXPB_CALL_CIRCLE_H
#define TXPB_CALL_CIRCLE_H 1

#include <tuxfw.h>

#include <list>

using namespace std;

class CallCircle
{
    char m_cAccNbr[15];
	char m_cEffDt[15];
	char m_cExprDt[15];

public:
	CallCircle();
	virtual ~CallCircle();

	// Setters
	void setAccNbr(char* );
	void setEffDt(char* );
	void setExprDt(char* );

	// Getters
	char* getAccNbr();
	char* getEffDt();
	char* getExprDt();

	// Sobrecarga de operadores
	CallCircle& operator= ( const CallCircle &);
	bool        operator==( const CallCircle &);

	//Parsing
	// Metodo de Parsing para o XML de Retorno de GetSubscriptionInfo
	static void getCallCircleInPrimarySvc(TuxHelper* tuxhp, DOMNode* outputDOMNodeGetSubscriptionInfo,char* pcSvcName, char* pcSeqNbr, char* pcListTypeInd, char* pcMethodInd);
	// Metodo de Parsing para o XML de Retorno de GetCallingCircle
    static void getListCallCircle(TuxHelper* tuxhp, DOMNode* outputDOMNodeGetCallingCircle, list<CallCircle>& lstCallCircle);
	
};


#endif