#include "TuxHelperClever.h"

///-----------------------------------------------------------------------------
CTuxHelperClever::CTuxHelperClever()
{
	this->b_Release = false;
	this->b_Translate = false;
}

CTuxHelperClever::CTuxHelperClever(bool b_enableRelease)
{
	this->b_Release = b_enableRelease;
}

///-----------------------------------------------------------------------------
bool CTuxHelperClever::getRelease()
{
	return this->b_Release;
}

void CTuxHelperClever::setRelease(bool b_enableRelease)
{
	this->b_Release = b_enableRelease;
}

///-----------------------------------------------------------------------------
bool CTuxHelperClever::getTranslateSpecialChars()
{
	return this->b_Translate;
}

void CTuxHelperClever::setTranslateSpecialChars(bool b_enableTranslate)
{
	this->b_Translate = b_enableTranslate;
}

///-----------------------------------------------------------------------------
char* CTuxHelperClever::translateSpecialChars(char** value)
{
	//
	int ret = TUXFWRET_ERROR;
	int inputIndex;
	int outputIndex;
	int movement;
	char* psubst;
	int inputLen;
	char substField[24576];

	//	
	inputLen = strlen( *value );
	psubst = substField;
	outputIndex = 0;
	//
	// loop for each input char and copy into substField
	for ( inputIndex = 0 ; inputIndex < inputLen ; inputIndex++) 
	{
		if ( (*value)[inputIndex] == '&' ) 
		{
			strcpy( psubst , "&amp;");
			movement = 5;
		}
		else if ( (*value)[inputIndex] == '>' ) 
		{
			strcpy(psubst ,"&gt;");
			movement = 4;
		}
		else if ( (*value)[inputIndex] == '<' )  
		{
			strcpy(psubst ,"&lt;");
			movement = 4;
		}
		else if ( (*value)[inputIndex] == '\"' ) 
		{
			strcpy(psubst ,"&quot;");
			movement = 6;
		}
		else if ( (*value)[inputIndex] == '\'' )
		{
			strcpy(psubst ,"&apos;");
			movement = 6;
		}
		else {
			*psubst = (*value)[inputIndex];
			movement = 1;
		}
		//
		// Se a mensagem final for ficar maior que o buffer alocado
		// para o processamento.
		if ( ( movement + outputIndex ) > TUXFW_XMLGEN_FIELDSIZE-1 )
		{
			throw new TuxException(ERR_TUX_XMLGEN_TRSPCCHARS_CD, ERR_TUX_XMLGEN_TRSPCCHARS_MSG );
		} 
		else
		{
			outputIndex = movement + outputIndex;
			//
			// desloca o ponteiro o quanto necessário
			for ( ; movement > 0; movement--) 
			{
				psubst++;
			}
			
		}
	}
	//
	// Garante que a string termina com NULL
	*psubst = '\0';
	
	XMLString::release(value);

	// vamos garantir que a cópia seja feita por XMLString
	// pois o retorno da função será desalocado com XMLString::release

	XMLCh* valTemp = XMLString::transcode(substField);
	
	*value = XMLString::transcode(valTemp);
	
	XMLString::release(&valTemp);

	return *value;

}

///-----------------------------------------------------------------------------
char* CTuxHelperClever::walkTree(DOMNode* node, char* Tag,int item)
{
	char *pc_ret = tuxhp.walkTree(node, Tag, item);

	if(this->getTranslateSpecialChars() && pc_ret != NULL && strlen(pc_ret) > 0)
	{
		this->translateSpecialChars(&pc_ret);		
		tuxfw_getlogger()->debug("pc_ret Translated: %s", pc_ret);
	}

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

