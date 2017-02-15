/*
 * CAssinanteTerminal.cpp
 *
 *  Created on: 11/06/2013
 *      Author: Jones Randis
 */

#include "garbage_collector.h"

#include "CAssinanteTerminal.h"
#include "CFilaProcessum.h"
#include "CPessoaLinhaHistorico.h"
#include "CPessoaLinha.h"
#include "CLog.h"
#include "CUtil.h"
#include "CZip.h"
#include "CException.h"
#include "CConfig.h"

#include <map>
#include <algorithm>
#include <utility>
#include <functional>
#include <climits>
#include <cstring>
#include <cerrno>

extern "C" {
#include <unistd.h>
#include <libgen.h>
}

namespace batch {

CAssinanteTerminal::CAssinanteTerminal(CConfig& config)
: config(config)
{
	dtrp = dirp.createDocumentType((Tstr)0, (Tstr)0, (Tstr)0);
	dtrp->markToDelete();
	dtrp->resetNode(0);

	docrefp = dirp.createDocument(Tstr(XMLNS_ASSINANTE_TERMINAL), Tstr("ass:assinantesTerminais"), *dtrp);
	docrefp->markToDelete();

	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());
	elref.setAttributeNS(Tstr(XMLNS), Tstr("xmlns:ass"), Tstr(XMLNS_ASSINANTE_TERMINAL));
}

CAssinanteTerminal::~CAssinanteTerminal() {

}

void CAssinanteTerminal::loadSchema()
{LOG_FUNC

	xsd = config.path_xsd + DIR_SEP + XSD_ASSINANTE_TERMINAL;
	LOG_INFO("loadSchema: " << xsd);
	CXmlImpl::loadSchema(xsd);
}

void CAssinanteTerminal::write(SFilaProcessum& filaprocessum, std::string& file)
{LOG_FUNC

	std::stringstream filename;
	filename << "ASSINANTE-TERMINAL_";
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

void CAssinanteTerminal::append(SAssinanteTerminal& assinanteterm)
{
	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());

	// assinante
	ElementRef<Tnode> elemAssinante(elref, docrefp->createElement( Tstr("assinanteTerminal") ));
	elref.appendChild(elemAssinante);

	// cpfCnpj
	if ( !assinanteterm.cpfCnpj.empty() ) {
		ElementRef<Tnode> elemCpfCnpj(elemAssinante, docrefp->createElement( Tstr("cpfCnpj")) );
		elemAssinante.appendChild(elemCpfCnpj);
		TextRef<Tnode> textCpfCnpj(elemCpfCnpj, docrefp->createTextNode(0));
		elemCpfCnpj.appendChild(textCpfCnpj);
		textCpfCnpj.appendData( Tstr(assinanteterm.cpfCnpj.c_str()) );
	}

	// documento
	if ( !assinanteterm.documento.empty() ) {
		ElementRef<Tnode> elemDocumento(elemAssinante, docrefp->createElement( Tstr("documento")) );
		elemAssinante.appendChild(elemDocumento);
		TextRef<Tnode> textDocumento(elemDocumento, docrefp->createTextNode(0));
		elemDocumento.appendChild(textDocumento);
		textDocumento.appendData( Tstr(assinanteterm.documento.c_str()) );
	}

	// tipo
	ElementRef<Tnode> elemTipo(elemAssinante, docrefp->createElement( Tstr("tipo")) );
	elemAssinante.appendChild(elemTipo);
	TextRef<Tnode> textTipo(elemTipo, docrefp->createTextNode(0));
	elemTipo.appendChild(textTipo);
	textTipo.appendData( Tstr(assinanteterm.tipo.c_str()) );

	// numero
	ElementRef<Tnode> elemNumero(elemAssinante, docrefp->createElement( Tstr("numero")) );
	elemAssinante.appendChild(elemNumero);
	TextRef<Tnode> textNumero(elemNumero, docrefp->createTextNode(0));
	elemNumero.appendChild(textNumero);
	textNumero.appendData( Tstr(assinanteterm.numero.c_str()) );

	// dataInicio
	ElementRef<Tnode> elemDataInicio(elemAssinante, docrefp->createElement( Tstr("dataInicio")) );
	elemAssinante.appendChild(elemDataInicio);
	TextRef<Tnode> textDataInicio(elemDataInicio, docrefp->createTextNode(0));
	elemDataInicio.appendChild(textDataInicio);
	textDataInicio.appendData( Tstr(assinanteterm.dataInicio.c_str()) );

	// dataFim
	if ( !assinanteterm.dataFim.empty() ) {
		ElementRef<Tnode> elemDataFim(elemAssinante, docrefp->createElement( Tstr("dataFim")) );
		elemAssinante.appendChild(elemDataFim);
		TextRef<Tnode> textDataFim(elemDataFim, docrefp->createTextNode(0));
		elemDataFim.appendChild(textDataFim);
		textDataFim.appendData( Tstr(assinanteterm.dataFim.c_str()) );
	}
}

void CAssinanteTerminal::append(TvecPessoaDocumento& vpessoadoc, SPessoaLinhaHistorico& pessoalinha)
{
	SPessoaLinha pessoalinhahist;
	pessoalinhahist.cdorigem = pessoalinha.cdorigem;
	pessoalinhahist.cdarearegistro = pessoalinha.cdarearegistro;
	pessoalinhahist.nrlinha = pessoalinha.nrlinha;
	pessoalinhahist.dthabilitacao = pessoalinha.dthabilitacao;
	pessoalinhahist.dtexpiracao = pessoalinha.dtexpiracao;
	append(vpessoadoc, pessoalinhahist);
}

void CAssinanteTerminal::append(TvecPessoaDocumento& vpessoadoc, SPessoaLinha& pessoalinha)
{
	std::map<oracle::occi::Number, SAssinanteTerminal> mapAssinanteTerminal;
	oracle::occi::Number _idpessoa;

	for(int i=0; i < vpessoadoc.size(); i++) {
		SPessoaDocumento& sPessoaDocumento = vpessoadoc.at(i);

		if (sPessoaDocumento.idpessoa != _idpessoa) {
			_idpessoa = sPessoaDocumento.idpessoa;

			// tipo
			if ( pessoalinha.cdorigem == "MOVEL" )
				mapAssinanteTerminal[_idpessoa].tipo = "M";
			else if ( pessoalinha.cdorigem == "FIXO" )
				mapAssinanteTerminal[_idpessoa].tipo = "F";

			// numero;
			if ( !pessoalinha.cdarearegistro.isNull() && !pessoalinha.nrlinha.isNull() ) {
				std::ostringstream numero;
				numero << PREFIXO_CDAREAREGISTRO;
				numero << static_cast<int64_t>(pessoalinha.cdarearegistro);
				numero << static_cast<int64_t>(pessoalinha.nrlinha);
				mapAssinanteTerminal[_idpessoa].numero = numero.str();
			}
            else
            {
				std::ostringstream numero;
				numero << "0000000000000";
				mapAssinanteTerminal[_idpessoa].numero = numero.str();
            }

			// dataInicio
			if ( !pessoalinha.dthabilitacao.isNull() )
				mapAssinanteTerminal[_idpessoa].dataInicio = pessoalinha.dthabilitacao.toText(ORA_DATE_FORMAT);

			// dataFim
			if ( !pessoalinha.dtexpiracao.isNull() )
				mapAssinanteTerminal[_idpessoa].dataFim = pessoalinha.dtexpiracao.toText(ORA_DATE_FORMAT);
		}

		// documento
		// cpfCnpj
		if (sPessoaDocumento.sgclassificacao == "RG") {
			std::ostringstream documento;
			documento << sPessoaDocumento.sgclassificacao;
			documento << " " << sPessoaDocumento.nrdocumento;
			documento << " " << sPessoaDocumento.sgorgaoexpedidor;
			documento << " " << sPessoaDocumento.sguf;
			mapAssinanteTerminal[_idpessoa].documento = documento.str();
		}
		else if (sPessoaDocumento.sgclassificacao == "CPF") {
			mapAssinanteTerminal[_idpessoa].cpfCnpj = sPessoaDocumento.nrdocumento;
		}
		else if (sPessoaDocumento.sgclassificacao == "CNPJ") {
			mapAssinanteTerminal[_idpessoa].cpfCnpj = sPessoaDocumento.nrdocumento;
		}
	}

	std::map<oracle::occi::Number, SAssinanteTerminal>::iterator it;
	for(it=mapAssinanteTerminal.begin(); it != mapAssinanteTerminal.end(); it++) {
		append(it->second);
	}
}


} /* namespace batch */
