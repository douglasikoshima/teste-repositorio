#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif


/*++
Module Name:
    RouterDOMImpl.cpp

Abstract:
	Implements DOM text extract

Author:
    Ivan Mentone 2004-08-16

Environment:
    Router Core

Revision History:
	2004-08-23 - Ivan Mentone - Retrieve correctly a DOM document

--*/

void MakeDOMSource(DOMNode*node,RouterStreamImpl*prsi)
{
	char*nname;
	char*nval;
	DOMNode*child;
	DOMNamedNodeMap*attr;
	int len;

	nname=XMLString::transcode(node->getNodeName());
	nval=XMLString::transcode(node->getNodeValue());

	if(nname)
		nname=RouterStringImpl::_LTrim(nname);
	if(nval)
		nval=RouterStringImpl::_LTrim(nval);

	switch(node->getNodeType())
	{
	case DOMNode::TEXT_NODE:
		prsi->AppendText(nval);
		break;
	case DOMNode::PROCESSING_INSTRUCTION_NODE:
		prsi->AppendText("<?");
		prsi->AppendText(nval);
		prsi->AppendText("?>");
		break;
	case DOMNode::DOCUMENT_NODE:
		//Disable because this a subnode
		//prsi->AppendText("<?xml version='1.0' encoding='ISO-8859-1' ?>");
		MakeDOMSource(node->getFirstChild(),prsi);
		break;
	case DOMNode::ENTITY_REFERENCE_NODE:
		for(child=node->getFirstChild();child;child=child->getNextSibling())
			MakeDOMSource(child,prsi);
		break;
	case DOMNode::ELEMENT_NODE:
		prsi->AppendText("<");
		prsi->AppendText(nname);
		attr=node->getAttributes();
		len=attr->getLength();
		{
			char*nnm=0L,*nvl=0L;
			for(int i=0;i<len;i++)
			{
				child=attr->item(i);
				nnm=XMLString::transcode(child->getNodeName());
				nvl=XMLString::transcode(child->getNodeValue());
				prsi->AppendText(" ");
				prsi->AppendText(nnm);
				prsi->AppendText("=\"");
				prsi->AppendText(nvl);
				prsi->AppendText("\"");
			}
		}
		if(!(child=node->getFirstChild()))
			prsi->AppendText("/>");
		else
		{
			prsi->AppendText(">");
			while(child)
			{
				MakeDOMSource(child,prsi);
				child=child->getNextSibling();
			}
			prsi->AppendText("</");
			prsi->AppendText(nname);
			prsi->AppendText(">");
		}
		break;
	case DOMNode::CDATA_SECTION_NODE:
		prsi->AppendText("<![CDATA[");
		prsi->AppendText(nval);
		prsi->AppendText("]]>");
		break;
	case DOMNode::COMMENT_NODE:
		prsi->AppendText("<!--");
		prsi->AppendText(nval);
		prsi->AppendText("-->");
		break;
	}
}

RouterDOMImpl::RouterDOMImpl(AuxDOMImpl*padi):
	rsi(0L)
{
	Execute(padi);
}
RouterDOMImpl::~RouterDOMImpl()
{}
char*RouterDOMImpl::GetText()
{return rsi.GetText();}
int RouterDOMImpl::Execute(AuxDOMImpl*padi)
{
	if(!padi||padi->Bad())
		return 0;
	MakeDOMSource(padi->RetrieveDOM(),&rsi);
	return 0;
}
