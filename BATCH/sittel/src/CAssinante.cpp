/*
 * CAssinante.cpp
 *
 *  Created on: 11/06/2013
 *      Author: Jones Randis
 */

#include "garbage_collector.h"

#include "CAssinante.h"
#include "CFilaProcessum.h"
#include "CLog.h"
#include "CException.h"
#include "CConfig.h"
#include "CUtil.h"
#include "CZip.h"

#include <map>
#include <algorithm>
#include <utility>
#include <functional>
#include <climits>
#include <cstring>
#include <cerrno>

#include <occiCommon.h>
#include <occi.h>

extern "C" {
#include <unistd.h>
#include <libgen.h>
}

namespace batch {

CAssinante::CAssinante(CConfig& config)
: config(config)
{
	dtrp = dirp.createDocumentType((Tstr)0, (Tstr)0, (Tstr)0);
	dtrp->markToDelete();
	dtrp->resetNode(0);

	docrefp = dirp.createDocument(Tstr(XMLNS_ASSINANTE), Tstr("ass:assinantes"), *dtrp);
	docrefp->markToDelete();

	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());
	elref.setAttributeNS(Tstr(XMLNS), Tstr("xmlns:ass"), Tstr(XMLNS_ASSINANTE));
}

CAssinante::~CAssinante() {

}

void CAssinante::loadSchema()
{LOG_FUNC

	xsd = config.path_xsd + DIR_SEP + XSD_ASSINANTE;
	LOG_INFO("loadSchema: " << xsd);
	CXmlImpl::loadSchema(xsd);
}

void CAssinante::write(SFilaProcessum& filaprocessum, std::string& file)
{LOG_FUNC

	std::stringstream filename;
	filename << "ASSINANTE_";
	filename << int64_t(filaprocessum.numerosolicitacao);
	filename << "_" << PROVEDOR << "_" << now("%Y%m%d_%H%M%S");

	std::string xmlfile = filename.str() + EXT_XML;

	if (config.zip_xml) {
		LOG_INFO("BufferOSource: " << xmlfile)
		DOMWriter<Tnode>* dwp = fp.createDOMWriter(DOMWrCXml);
		//dwp->setOutputEncoding( Tstr(XML_ENCODING) );
		dwp->setIndentStep(2);
		dwp->setIndentLevel(0);
		oratext buff[XML_BUFFER_SIZE]={0};
		BufferOSource osrcp(buff, sizeof(buff));
		ubig_ora len = dwp->writeNode(&osrcp, *docrefp);

		//LOG_INFO("writeNode: " << (char*)buff);

		std::string zipfile = filename.str() + EXT_ZIP;
		std::string tmpfile = config.path_tmp + DIR_SEP + zipfile;

		CZip cZip(tmpfile);
		cZip.create();
		cZip.add(xmlfile, buff, len);
		cZip.close();

		std::string datafile = config.path + DIR_SEP + zipfile;
		movefile(tmpfile, datafile);
		file = zipfile;
	}
	else {
		LOG_INFO("FileOSource: " << xmlfile)
		std::string tmpfile = config.path_tmp + DIR_SEP + xmlfile;
		CXmlImpl::write(tmpfile);

		std::string datafile = config.path + DIR_SEP + xmlfile;
		movefile(tmpfile, datafile);
		file = xmlfile;
	}
}

void CAssinante::append(SAssinante& assinante)
{
	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());

	// assinante
	ElementRef<Tnode> elemAssinante(elref, docrefp->createElement( Tstr("assinante") ));
	elref.appendChild(elemAssinante);

	// cpfCnpj
	if ( !assinante.cpfCnpj.empty() ) {
		ElementRef<Tnode> elemCpfCnpj(elemAssinante, docrefp->createElement( Tstr("cpfCnpj") ));
		elemAssinante.appendChild(elemCpfCnpj);
		TextRef<Tnode> textCpfCnpj(elemCpfCnpj, docrefp->createTextNode(0));
		elemCpfCnpj.appendChild(textCpfCnpj);
		textCpfCnpj.appendData( Tstr(assinante.cpfCnpj.c_str()) );
	}

	// documento
	if ( !assinante.documento.empty() ) {
		ElementRef<Tnode> elemDocumento(elemAssinante, docrefp->createElement( Tstr("documento") ));
		elemAssinante.appendChild(elemDocumento);
		TextRef<Tnode> textDocumento(elemDocumento, docrefp->createTextNode(0));
		elemDocumento.appendChild(textDocumento);
		textDocumento.appendData( Tstr(assinante.documento.c_str()) );
	}

	// nome
	ElementRef<Tnode> elemNome(elemAssinante, docrefp->createElement( Tstr("nome") ));
	elemAssinante.appendChild(elemNome);
	TextRef<Tnode> textNome(elemNome, docrefp->createTextNode(0));
	elemNome.appendChild(textNome);
	xmlescapechars(assinante.nome);
	textNome.appendData( Tstr(assinante.nome.c_str()) );

	// tipo
	ElementRef<Tnode> elemTipo(elemAssinante, docrefp->createElement( Tstr("tipo") ));
	elemAssinante.appendChild(elemTipo);
	TextRef<Tnode> textTipo(elemTipo, docrefp->createTextNode(0));
	elemTipo.appendChild(textTipo);
	textTipo.appendData( Tstr(assinante.tipo.c_str()) );
}

void CAssinante::append(TvecPessoaDocumento& vpessoadoc)
{
	std::map<oracle::occi::Number, SAssinante> mapAssinante;
	oracle::occi::Number _idpessoa;

	for(int i=0; i < vpessoadoc.size(); i++) {
		SPessoaDocumento& sPessoaDocumento = vpessoadoc.at(i);

		if (sPessoaDocumento.idpessoa != _idpessoa) {
			_idpessoa = sPessoaDocumento.idpessoa;

			if (sPessoaDocumento.sgtipopessoa == "PF")
				mapAssinante[_idpessoa].tipo = "1";
			else if (sPessoaDocumento.sgtipopessoa == "PJ")
				mapAssinante[_idpessoa].tipo = "2";

			mapAssinante[_idpessoa].nome = sPessoaDocumento.nmpessoa;
		}

		if (sPessoaDocumento.sgclassificacao == "RG") {
			std::ostringstream documento;
			documento << sPessoaDocumento.sgclassificacao;
			documento << " " << sPessoaDocumento.nrdocumento;
			documento << " " << sPessoaDocumento.sgorgaoexpedidor;
			documento << " " << sPessoaDocumento.sguf;
			mapAssinante[_idpessoa].documento = documento.str();
		}
		else if (sPessoaDocumento.sgclassificacao == "CPF") {
			mapAssinante[_idpessoa].cpfCnpj = sPessoaDocumento.nrdocumento;
		}
		else if (sPessoaDocumento.sgclassificacao == "CNPJ") {
			mapAssinante[_idpessoa].cpfCnpj = sPessoaDocumento.nrdocumento;
		}
	}

	std::map<oracle::occi::Number, SAssinante>::iterator it;
	for(it=mapAssinante.begin(); it != mapAssinante.end(); it++) {
		append(it->second);
	}
}

} /* namespace batch */
