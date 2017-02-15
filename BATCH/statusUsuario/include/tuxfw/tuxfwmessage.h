/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1 $
 * @CVS     $Author: rrusso $ - $Date: 2007/01/08 18:47:56 $
 * @ID      $Id: tuxfwmessage.h,v 1.1 2007/01/08 18:47:56 rrusso Exp $
 **/

#if !defined(AFX_TUXFWMESSAGE_H__01AB65C7_A260_49B4_B294_E8F6F9A1F4A5__INCLUDED_)
#define AFX_TUXFWMESSAGE_H__01AB65C7_A260_49B4_B294_E8F6F9A1F4A5__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "tuxfwxmlgen.h"
#include "tuxfwhelper.h"

//namespace tuxfw
//{

#define TUXFW_MAX_MSG_LEN 24576
#define TUXFW_MIN_MSG_LEN  4096

//##ModelId=41079D29006D
class TuxMessage: public TuxHelper 
{
public:
	//##ModelId=41079D29007E
	TuxMessage( DOMNode* pnode );
	//##ModelId=41079D290080
	TuxMessage( char* messageXML );
	//##ModelId=41079D290082
	TuxMessage();
	//##ModelId=41079D290083
	virtual ~TuxMessage();
	//
	//
	// setters
	//##ModelId=41079D290085
	void setService( char* pservice);
	//##ModelId=41079D29008D
	void setUser( char* puser );
	//##ModelId=41079D29008F
	void setUUID( char* puuid );
	//##ModelId=41079D290091
	void setHost( char* phost); 
	//##ModelId=41079D290093
	void setSubSystem( char* psubSystem );
	//##ModelId=41079D29009D
	void setEntity( char* pentity );
	//##ModelId=41079D29009F
	void setCreationTimeStamp( char* pcreationTimeStamp );
	//##ModelId=41079D2900A1
	void setProcessTimeStamp( char* pprocessTimeStamp );
	//##ModelId=41079D2900A3
	void setSequence( char* psequence );
	//##ModelId=41079D2900AC
	void setCorrId( char* pcorrid );
	//##ModelId=41079D2900AE
	void setVersion( char* pversion );
	//##ModelId=41079D2900B0
	void setOperation( char* poperation );
	//##ModelId=41079D2900BC
	void setStatusCode( char* pstatuscode );
	void setStatusText( char* pstatustext );
	//##ModelId=41079D2900BE
	void setMessageBody( char* xmlMsgBody );
	//##ModelId=41079D2900C0
	void setMessageBody( XMLGen* xmlgenObject );
	//
	// getters
	//##ModelId=41079D2900C2
	char* getService();
	//##ModelId=41079D2900CB
	char* getUser();
	//##ModelId=41079D2900CC
	char* getUUID();
	//##ModelId=41079D2900CD
	char* getHost();
	//##ModelId=41079D2900CE
	char* getSubSystem();
	//##ModelId=41079D2900CF
	char* getEntity();
	//##ModelId=41079D2900D0
	char* getCreationTimeStamp();
	//##ModelId=41079D2900D1
	char* getProcessTimeStamp();
	//##ModelId=41079D2900DA
	char* getSequence();
	//##ModelId=41079D2900DB
	char* getCorrId();
	//##ModelId=41079D2900DC
	char* getVersion();
	//##ModelId=41079D2900DD
	char* getOperation();
	//##ModelId=41079D2900DE
	char* getStatusCode();
	char* getStatusText();
	//##ModelId=41079D2900DF
	char* getMessageBody();
	//
	//
	// ############################################
	//                ATENÇÂO !!!!
	// O DOMNode retornado aqui é um deep clone do
	// elemento derivado da criação de TuxMessage
	// é sua responsabilidade libera-lo com a
	// chamada a relase ou memory leaks irão ocorrer
	//
	//
	DOMNode* getMessageBodyDOM();

	//##ModelId=41079D2900E0
	char* getMessageXML();
	//
	//##ModelId=41079D2900EA
	char* getExtraHeaderAttribute(char* pattributeName);

private:
	//##ModelId=41079D2900EF
	DOMNode* inputMessageHandler;
	//##ModelId=41079D2900FB
	XMLGen*  outputMessageHandler;
	//##ModelId=41079D2900FF
	void parseXMLHeader(DOMNode*pnode);
	//##ModelId=41079D290101
	void parseXMLBody(DOMNode*pnode);
	//##ModelId=41079D29010A
	void parseXMLMessage( char* message );
	//
	//
	//##ModelId=41079D29010C
	char* msgHeader;
	//##ModelId=41079D290119
	char* msgBody;
	//
	// DOMNode equivalent to msgBody char string
	DOMNode* bodyNode;
	//##ModelId=41079D29011A
	char* service;
	//##ModelId=41079D29011B
	char* user;
	//##ModelId=41079D290128
	char* uuid;
	//##ModelId=41079D290129
	char* host;
	//##ModelId=41079D29012A
	char* subSystem;
	//##ModelId=41079D290138
	char* corrid;
	//##ModelId=41079D290139
	char* entity;
	//##ModelId=41079D290148
	char* creationTimeStamp;
	//##ModelId=41079D290149
	char* processTimeStamp;
	//##ModelId=41079D29014A
	char* sequence;
	//##ModelId=41079D290157
	char* version;
	//##ModelId=41079D290158
	char* operation;
	//##ModelId=41079D290167
	char* statusCode;
	char* statusText;
	//
	//
	XercesDOMParser* xml_p;
 	MemBufInputSource* mbis;

};

//}
#endif // !defined(AFX_TUXFWMESSAGE_H__01AB65C7_A260_49B4_B294_E8F6F9A1F4A5__INCLUDED_)
