/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1 $
 * @CVS     $Author: rrusso $ - $Date: 2006/06/07 22:20:24 $
 * @ID      $Id: tuxfwremoteservice.h,v 1.1 2006/06/07 22:20:24 rrusso Exp $
 **/

#if !defined(AFX_TUXREMOTESERVICE_H__6535FA72_4612_49D8_A069_718A5915B566__INCLUDED_)
#define AFX_TUXREMOTESERVICE_H__6535FA72_4612_49D8_A069_718A5915B566__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "tuxfwmessage.h"
#include "tuxfwxmlgen.h"
#include "tuxfwerrors.h"

//namespace tuxfw
//{

//##ModelId=41079D28038A
class TuxRemoteService  
{
public:
	//##ModelId=41079D28038B
	TuxRemoteService();
	//##ModelId=41079D28038C
	virtual ~TuxRemoteService();
	//
	// setters
	//##ModelId=41079D28038E
	void setServiceName( char* serviceName );
	//##ModelId=41079D280390
	void setInputMessage( TuxMessage* pinputMessage );
	//##ModelId=41079D28039A
	void setOutputMessage( TuxMessage* poutputMessage );
	//
	// getters
	//##ModelId=41079D28039C
	TuxMessage* getInputMessage();
	//##ModelId=41079D28039D
	DOMNode* getInputMessageDOM();
	//##ModelId=41079D28039E
	TuxMessage* getOutputMessage();
	//##ModelId=41079D28039F
	DOMNode* getOutputMessageDOM();
	//##ModelId=41079D2803A9
	char* getServiceName();
	//
	// various methods
	//##ModelId=41079D2803AA
	int remoteCall();

private:
	//
	// Service parameters
	//##ModelId=41079D2803AB
	int timeout;
	//##ModelId=41079D2803AC
	int priority;
	//##ModelId=41079D2803AD
	char* type;
	//##ModelId=41079D2803B9
	char* nomeLogicoServico;
	//##ModelId=41079D2803BA
	char* nomeFisicoServico;
	//##ModelId=41079D2803BB
	int flagsTuxedo;

	//
	//
	//##ModelId=41079D2803C9
	TuxMessage* inputMessage;
	//##ModelId=41079D2803CE
	TuxMessage* outputMessage;
	//##ModelId=41079D2803D8
	char* tpcallwrapper( char* nomeServico, char* sMsgIn, int lFlags );
	//##ModelId=41079D2803DC
    char* tpacallwrapper( char* nomeServico, char* sMsgIn, int lFlags, int delay );
	//##ModelId=41079D290004
	int loadServiceParameters( char* serviceName );
};

//}
#endif // !defined(AFX_TUXREMOTESERVICE_H__6535FA72_4612_49D8_A069_718A5915B566__INCLUDED_)
