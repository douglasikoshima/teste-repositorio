/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2011/04/06 23:54:50 $
 * @ID      $Id: tuxfwxmlgen.h,v 1.1.4.1 2011/04/06 23:54:50 a5114878 Exp $
 **/

#ifndef XMLGEN2
#define XMLGEN2



#include<xercesc/parsers/XercesDOMParser.hpp>
#include<xercesc/dom/DOM.hpp>
#include<xercesc/dom/DOMWriter.hpp>

#include<xercesc/framework/MemBufFormatTarget.hpp>
#include<xercesc/framework/MemBufInputSource.hpp>

using XERCES_CPP_NAMESPACE::DOMImplementation;
using XERCES_CPP_NAMESPACE::DOMDocument;
using XERCES_CPP_NAMESPACE::DOMNode;
using XERCES_CPP_NAMESPACE::DOMWriter;
using XERCES_CPP_NAMESPACE::DOMElement;
using XERCES_CPP_NAMESPACE::DOMText;
using XERCES_CPP_NAMESPACE::DOMComment;
using XERCES_CPP_NAMESPACE::DOMCDATASection;

using XERCES_CPP_NAMESPACE::XMLPlatformUtils;
using XERCES_CPP_NAMESPACE::XMLException;
using XERCES_CPP_NAMESPACE::DOMNodeList;
using XERCES_CPP_NAMESPACE::XercesDOMParser;

using namespace XERCES_CPP_NAMESPACE;


//
// Determina o tamanho do buffer de substituição que
// conterá o valor de um campo apos a tradução dos
// caracteres especiais.
#define TUXFW_XMLGEN_FIELDSIZE	24576
#define TUXFW_XMLGEN_MSGSIZE	245760
//#define TUXFW_XMLGEN_MAXVALUESIZE 245  // teste

struct tagXMLNODE;

typedef struct tagXMLNODE XMLNODE,*PXMLNODE;

struct tagXMLNODE
{
	char*tagName;
//	int elm;
	PXMLNODE next;
};

enum SpaceMode
{
	SPACE,
	TAB
};

class XMLGen
{
public:

	XMLGen(SpaceMode spc =SPACE);
	~XMLGen();
	int setRoot(char *root);			
	int clearAndDestroy();
	int createTag(char *tnm);			
	int closeTag();
	int addItem(char *tag,char *val);	
	int addItem(char *tag,int val);		
	int addProp(char *pn,char *pv);		
	int addComment(char *pcmt);		
	char*retrieveXML(int *len);		
    char XMLFlags;
    int lenAcc;
	int aggregateXML(char *_xml);
	int addCData(char*val);

private:

	int checkSpecialChars(char* value);
	char* translateSpecialChars(char* value);
	char*retrieveString(char *text,int len);	
	int accum(char *node);

	int cleanAccum();
	char*readAccum();
	char*retrieveAccum();
	int closeAll();
	PXMLNODE pxml;
	int flags;
	char substField[TUXFW_XMLGEN_FIELDSIZE];
	char msgout[TUXFW_XMLGEN_MSGSIZE];
	char *pcurpos;
	long limite;
protected:
	int t_op;
	void append( char* value );		// acrescesta valores no XML de saida
};

//}
#endif
