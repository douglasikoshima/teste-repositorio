// TuxHelperClever.h: interface for the CTuxHelperClever class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_TUX_HELPER_CLEVER__
#define __C_TUX_HELPER_CLEVER__

#include <tuxfw.h>
#include "MemoryManager.h"

class CTuxHelperClever
{

public:
	
	CTuxHelperClever();
	CTuxHelperClever(bool b_enableRelease);

	bool getRelease();
	void setRelease(bool b_enableRelease);
	
	bool getTranslateSpecialChars();
	void setTranslateSpecialChars(bool b_enableTranslate);

	char*    walkTree(DOMNode* node, char* Tag, int item);
	char*    walkAttr(DOMNode* node, char* Tag, char* Prop, int item);
	char*    getAttrValue(DOMNode* node, char* pattr);
	char*    getNodeAsString(DOMNode* node);

	DOMNode* walkDOM(DOMNode* node, char* ptag);
	DOMNode* walkDOM(DOMNode* node, char* ptag, int index);

private:
	bool           b_Release;
	bool           b_Translate;
	TuxHelper      tuxhp;
	CMemoryManager mem;

	char* translateSpecialChars(char** value);

};


#endif //__C_TUX_HELPER_CLEVER__