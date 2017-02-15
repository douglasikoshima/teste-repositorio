/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1 $
 * @CVS     $Author: rrusso $ - $Date: 2007/01/08 18:47:56 $
 * @ID      $Id: tuxfwhelper.h,v 1.1 2007/01/08 18:47:56 rrusso Exp $
 **/

#ifndef TuxFW_TuxHelper
#define TuxFW_TuxHelper

//namespace tuxfw
//{

/*****************************************************************************
	Tuxedo helper functions
*****************************************************************************/
//##ModelId=41079D290261
class TuxHelper
{
public:
	/*!
	 *	walkTree - walk XML tree to find TagName
	 */
	//##ModelId=41079D290271
	char*walkTree(DOMNode*node,char*Tag,int item);
	/*!
	 *	walkAttr - walk XML tree to find Tag and Attribute Names
	 */
	//##ModelId=41079D290275
	char*walkAttr(DOMNode*node,char*Tag,char*Prop,int item);
	/*!
	 *	walkDOM - walk XML tree to find Tag
	 */
	//##ModelId=41079D290280
	DOMNode*walkDOM(DOMNode*node,char*ptag);
	/*!
	 *	walkDOM - walk XML tree to find the Nth Tag;
	 */
	//##ModelId=41079D290283
	DOMNode*walkDOM( DOMNode* node, char* ptag, int index );
	/*!
	 *	getNodeName - return Node Name
	 */
	//##ModelId=41079D290287
	char*getNodeName(DOMNode*node);
	/*!
	 *	getTextContent - return Node Text Content
	 */
	//##ModelId=41079D290291
	char*getTextContent(DOMNode*node);
	/*!
	 *	getAttrValue - return Attribute value from Node
	 */
	//##ModelId=41079D290293
	char*getAttrValue(DOMNode*node,char*pattr);
	/*!
	 *	translateSubsystemCode - translate a specified subsys code in current name
	 */
	//##ModelId=41079D290296
	int translateSubsystemCode(char*,char*);
	/*!
	 *	translateSubsystemCode - translate a specified subsys code in current name
	 */
	//##ModelId=41079D2902A0
	int translateSubsystemCode(int,char*);

	//
	char* getNodeAsString(DOMNode*node);
	/*
	 * ATENÇÂO !!!
	 * Este método DEVE ser usado para desalocar qualquer memória obtida através 
	 * das funções walktree, getNodeName,getAttrValue, getTextContent, walkAttr 
	 */
	void release(  char ** buf );
protected:
	
	//##ModelId=41079D2902A3
	DOMNode*walkDOM(DOMNode *node,char *ptag,int *item,int itemno);
private:
	//##ModelId=41079D2902AF
	int initializeXMLPparsing();
	//##ModelId=41079D2902B0
	int terminateXMLParsing();
};




#include <xercesc/sax/ErrorHandler.hpp>

XERCES_CPP_NAMESPACE_USE

class TuxFW_DOMReporter : public ErrorHandler
{
public:
    // -----------------------------------------------------------------------
    //  Constructors and Destructor
    // -----------------------------------------------------------------------
    TuxFW_DOMReporter() :
       fSawErrors(false)
    {
    }

    ~TuxFW_DOMReporter()
    {
    }


    // -----------------------------------------------------------------------
    //  Implementation of the error handler interface
    // -----------------------------------------------------------------------
    void warning(const SAXParseException& toCatch);
    void error(const SAXParseException& toCatch);
    void fatalError(const SAXParseException& toCatch);
    void resetErrors();

    // -----------------------------------------------------------------------
    //  Getter methods
    // -----------------------------------------------------------------------
    bool getSawErrors() const;

    // -----------------------------------------------------------------------
    //  Private data members
    //
    //  fSawErrors
    //      This is set if we get any errors, and is queryable via a getter
    //      method. Its used by the main code to suppress output if there are
    //      errors.
    // -----------------------------------------------------------------------
    bool    fSawErrors;
};

inline bool TuxFW_DOMReporter::getSawErrors() const
{
    return fSawErrors;
}

// ---------------------------------------------------------------------------
//  This is a simple class that lets us do easy (though not terribly efficient)
//  trancoding of XMLCh data to local code page for display.
// ---------------------------------------------------------------------------
class TuxFW_StrX
{
public :
    // -----------------------------------------------------------------------
    //  Constructors and Destructor
    // -----------------------------------------------------------------------
    TuxFW_StrX(const XMLCh* const toTranscode)
    {
        // Call the private transcoding method
        fLocalForm = XMLString::transcode(toTranscode);
    }

    ~TuxFW_StrX()
    {
        XMLString::release(&fLocalForm);
    }


    // -----------------------------------------------------------------------
    //  Getter methods
    // -----------------------------------------------------------------------
    const char* localForm() const
    {
        return fLocalForm;
    }

private :
    // -----------------------------------------------------------------------
    //  Private data members
    //
    //  fLocalForm
    //      This is the local code page form of the string.
    // -----------------------------------------------------------------------
    char*   fLocalForm;
};

#endif
