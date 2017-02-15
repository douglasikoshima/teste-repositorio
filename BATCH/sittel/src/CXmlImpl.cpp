/*
 * CXmlImpl.cpp
 *
 *  Created on: 19/07/2013
 *      Author: Jones Randis
 */

#include "CXmlImpl.h"
#include "CLog.h"
#include "CException.h"

extern "C" {
#include <unistd.h>
#include <libgen.h>
#include <limits.h>
}

#include <cstring>

namespace batch {

Ctx::encoding xmlenc[] = {
	{Tstr("input_encoding"), Tstr(XML_ENCODING)}
	//,{Tstr("default_input_encoding"), Tstr(XML_ENCODING)}
	//,{Tstr("data_encoding"), Tstr(XML_ENCODING)}
	//,{Tstr("error_language"), Tstr(XML_ENCODING)}
};
Ctx::encodings xmlencs = {1, xmlenc};

Ctx::encoding xsdenc[] = {
	{Tstr("input_encoding"), Tstr(XSD_ENCODING)}
	//,{Tstr("default_input_encoding"), Tstr(XSD_ENCODING)}
	//,{Tstr("data_encoding"), Tstr(XSD_ENCODING)}
	//,{Tstr("error_language"), Tstr(XSD_ENCODING)}
};
Ctx::encodings xsdencs = {1, xsdenc};

CXmlImpl::CXmlImpl()
: xmlctxp(Tstr("XmlImpl"), NULL, NULL, &xmlencs),
  xsdctxp(Tstr("XsdImpl"), NULL, NULL, &xsdencs),
  fp(&xmlctxp),
  di(false),
  dirp(&xmlctxp, &di),
  dtrp(0),
  docrefp(0),
  schval(fp.createSchemaValidator(SchValCXml, &xsdctxp))
{

}

CXmlImpl::~CXmlImpl() {
	schval->unloadSchema(NULL);
}

void CXmlImpl::loadSchema(const std::string& uri)
{
	std::string dn( dirname((char*)uri.c_str()) );
	if (dn.empty() || dn == ".") {
		schval->loadSchema( Tstr(uri.c_str()) );
	}
	else {
		char curr_dir[PATH_MAX]={0};
		if ( !getcwd(curr_dir, sizeof(curr_dir)) ) {
			LOG_ERR("getcwd: " << strerror(errno))
			throw XCurrentDir;
		}

		if ( chdir(dn.c_str()) == -1 ) {
			LOG_ERR("chdir: " << dn << " - " << strerror(errno))
			throw XChangeDir;
		}

		schval->loadSchema( Tstr(uri.c_str()) );

		if ( chdir(curr_dir) == -1 ) {
			LOG_ERR("chdir: " << curr_dir << " - " << strerror(errno))
			throw XChangeDir;
		}
	}
}

bool CXmlImpl::hasChildNodes()
{
	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());
	return elref.hasChildNodes();
}

uint32_t CXmlImpl::getChildLength()
{
	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());
	NodeList<Tnode>* nodeList = elref.getChildNodes();
	NodeListRef<Tnode> nodeListRef(elref, nodeList);
	return nodeListRef.getLength();
}

bool CXmlImpl::validate(ElementRef<Tnode>& elem_ref)
{
	bool retval = false;

	try {
		schval->validate( elem_ref );

		LOG_INFO("validate: Document is valid.")
		retval = true;
	}
	catch(ParserException& e) {
		LOG_ERR("ParserException: code=" << e.getCode())
	}
	return retval;
}

bool CXmlImpl::validate()
{
	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());
	return validate(elref);
}

void CXmlImpl::write(const std::string& file)
{
	DOMWriter<Tnode>* dwp = fp.createDOMWriter(DOMWrCXml);
	//dwp->setOutputEncoding( Tstr(XML_ENCODING) );
	dwp->setIndentStep(2);
	dwp->setIndentLevel(0);
	FileOSource osrcp( Tstr(file.c_str()) );
	dwp->writeNode(&osrcp, *docrefp);
}


} /* namespace batch */
