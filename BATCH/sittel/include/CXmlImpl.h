/*
 * CXmlImpl.h
 *
 *  Created on: 19/07/2013
 *      Author: Jones Randis
 */

#ifndef CXMLIMPL_H_
#define CXMLIMPL_H_

#include <string>

extern "C" {
#include <xml.h>
#include <xmlotn.h>
#include <xmlerr.h>
}

#include <xml.hpp>
#include <xmlotn.hpp>
#include <xmlctx.hpp>

#include "config.h"

namespace batch {

class CXmlImpl {
protected:
	TCtx xmlctxp;
	TCtx xsdctxp;
	Factory<TCtx, Tnode> fp;
	DOMImplementation<Tnode> di;
	DOMImplRef<TCtx, Tnode> dirp;
	DocumentTypeRef<Tnode>* dtrp;
	DocumentRef<Tnode>* docrefp;
	SchemaValidator<Tnode>* schval;

public:
	CXmlImpl();
	virtual ~CXmlImpl();

	void write(const std::string& file);
	bool hasChildNodes();
	uint32_t getChildLength();
	void loadSchema(const std::string& uri);
	bool validate(ElementRef<Tnode>& elem_ref);

	bool validate();
};

} /* namespace batch */
#endif /* CXMLIMPL_H_ */
