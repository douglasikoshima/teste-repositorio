#include <tuxfw.h>
#include <TuxHelperClever/TuxHelperClever.h>

CTuxHelperClever::CTuxHelperClever()
{
	this->b_Release = true;
}

CTuxHelperClever::CTuxHelperClever(bool b_enableRelease)
{
	this->b_Release = b_enableRelease;
}

char* CTuxHelperClever::walkTree(DOMNode* node, char* Tag,int item)
{
	char *pc_ret = tuxhp.walkTree(node, Tag, item);

	if(this->b_Release)
		mem.Add(pc_ret, RELEASE_XML_STRING);

	return pc_ret;
}


char* CTuxHelperClever::walkAttr(DOMNode* node, char* Tag, char* Prop, int item)
{
	char *pc_ret = tuxhp.walkAttr(node, Tag, Prop, item);

	if(this->b_Release)
		mem.Add(pc_ret, RELEASE_XML_STRING);

	return pc_ret;
}

char* CTuxHelperClever::getAttrValue(DOMNode* node, char* pattr)
{
	char *pc_ret = tuxhp.getAttrValue(node, pattr);

	if(this->b_Release)
		mem.Add(pc_ret, RELEASE_FREE);

	return pc_ret;
}


char* CTuxHelperClever::getNodeAsString(DOMNode* node)
{
	char *pc_ret = tuxhp.getNodeAsString(node);

	if(this->b_Release)
		mem.Add(pc_ret, RELEASE_FREE);

	return pc_ret;
}

DOMNode* CTuxHelperClever::walkDOM(DOMNode* node, char* ptag)
{
	return tuxhp.walkDOM(node, ptag);
}

DOMNode* CTuxHelperClever::walkDOM(DOMNode* node, char* ptag, int index)
{
	return tuxhp.walkDOM(node, ptag, index);
}