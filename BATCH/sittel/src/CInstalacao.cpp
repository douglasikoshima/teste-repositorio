/*
 * CInstalacao.cpp
 *
 *  Created on: 10/06/2013
 *      Author: Jones Randis
 */

#include "garbage_collector.h"

#include "CInstalacao.h"
#include "CFilaProcessum.h"
#include "CLog.h"
#include "CUtil.h"
#include "CZip.h"
#include "CPessoaLinhaHistorico.h"
#include "CPessoaLinha.h"
#include "CLinhaEndereco.h"
#include "CEnderecoLinha.h"
#include "CPessoaEndereco.h"
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

CInstalacao::CInstalacao(CConfig& config)
: config(config)
{
	dtrp = dirp.createDocumentType((Tstr)0, (Tstr)0, (Tstr)0);
	dtrp->markToDelete();
	dtrp->resetNode(0);

	docrefp = dirp.createDocument(Tstr(XMLNS_INSTALACAO), Tstr("ins:instalacoes"), *dtrp);
	docrefp->markToDelete();

	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());
	elref.setAttributeNS(Tstr(XMLNS), Tstr("xmlns:ins"), Tstr(XMLNS_INSTALACAO));
}

CInstalacao::~CInstalacao() {

}

void CInstalacao::loadSchema()
{LOG_FUNC

	xsd = config.path_xsd + DIR_SEP + XSD_INSTALACAO;
	LOG_INFO("loadSchema: " << xsd);
	CXmlImpl::loadSchema(xsd);
}

void CInstalacao::write(SFilaProcessum& filaprocessum, std::string& file)
{LOG_FUNC

	std::stringstream filename;
	filename << "INSTALACAO_";
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

void CInstalacao::append(SInstalacao& instalacao)
{
	ElementRef<Tnode> elref(*docrefp, docrefp->getDocumentElement());

	// instalacao
	ElementRef<Tnode> elemInstalacao(elref, docrefp->createElement( Tstr("instalacao") ));
	elref.appendChild(elemInstalacao);

	// numeroTerminal
	ElementRef<Tnode> elemNumeroTerminal(elemInstalacao, docrefp->createElement( Tstr("numeroTerminal") ));
	elemInstalacao.appendChild(elemNumeroTerminal);
	TextRef<Tnode> textNumeroTerminal(elemNumeroTerminal, docrefp->createTextNode(0));
	elemNumeroTerminal.appendChild(textNumeroTerminal);
	textNumeroTerminal.appendData( Tstr(instalacao.numeroTerminal.c_str()) );

	// tipoEndereco
	ElementRef<Tnode> elemTipoEndereco(elemInstalacao, docrefp->createElement( Tstr("tipoEndereco") ));
	elemInstalacao.appendChild(elemTipoEndereco);
	TextRef<Tnode> textTipoEndereco(elemTipoEndereco, docrefp->createTextNode(0));
	elemTipoEndereco.appendChild(textTipoEndereco);
	textTipoEndereco.appendData( Tstr(instalacao.tipoEndereco.c_str()) );

	// endereco
	ElementRef<Tnode> elemEndereco(elemInstalacao, docrefp->createElement( Tstr("endereco") ));
	elemInstalacao.appendChild(elemEndereco);
	TextRef<Tnode> textEndereco(elemEndereco, docrefp->createTextNode(0));
	elemEndereco.appendChild(textEndereco);
	xmlescapechars(instalacao.endereco);
	textEndereco.appendData( Tstr(instalacao.endereco.c_str()) );

	// bairro
	ElementRef<Tnode> elemBairro(elemInstalacao, docrefp->createElement( Tstr("bairro") ));
	elemInstalacao.appendChild(elemBairro);
	TextRef<Tnode> textBairro(elemBairro, docrefp->createTextNode(0));
	elemBairro.appendChild(textBairro);
	xmlescapechars(instalacao.bairro);
	textBairro.appendData( Tstr(instalacao.bairro.c_str()) );

	// municipio
	ElementRef<Tnode> elemMunicipio(elemInstalacao, docrefp->createElement( Tstr("municipio") ));
	elemInstalacao.appendChild(elemMunicipio);
	TextRef<Tnode> textMunicipio(elemMunicipio, docrefp->createTextNode(0));
	elemMunicipio.appendChild(textMunicipio);
	xmlescapechars(instalacao.municipio);
	textMunicipio.appendData( Tstr(instalacao.municipio.c_str()) );

	// uf
	ElementRef<Tnode> elemUf(elemInstalacao, docrefp->createElement( Tstr("uf") ));
	elemInstalacao.appendChild(elemUf);
	TextRef<Tnode> textUf(elemUf, docrefp->createTextNode(0));
	elemUf.appendChild(textUf);
	textUf.appendData( Tstr(instalacao.uf.c_str()) );

	// cep
	ElementRef<Tnode> elemCep(elemInstalacao, docrefp->createElement( Tstr("cep") ));
	elemInstalacao.appendChild(elemCep);
	TextRef<Tnode> textCep(elemCep, docrefp->createTextNode(0));
	elemCep.appendChild(textCep);
	textCep.appendData( Tstr(instalacao.cep.c_str()) );

	// dataInicio
	ElementRef<Tnode> elemDataInicio(elemInstalacao, docrefp->createElement( Tstr("dataInicio") ));
	elemInstalacao.appendChild(elemDataInicio);
	TextRef<Tnode> textDataInicio(elemDataInicio, docrefp->createTextNode(0));
	elemDataInicio.appendChild(textDataInicio);
	textDataInicio.appendData( Tstr(instalacao.dataInicio.c_str()) );

	// dataFim
	if ( !instalacao.dataFim.empty() ) {
		ElementRef<Tnode> elemDataFim(elemInstalacao, docrefp->createElement( Tstr("dataFim") ));
		elemInstalacao.appendChild(elemDataFim);
		TextRef<Tnode> textDataFim(elemDataFim, docrefp->createTextNode(0));
		elemDataFim.appendChild(textDataFim);
		textDataFim.appendData( Tstr(instalacao.dataFim.c_str()) );
	}
}

void CInstalacao::append(SPessoaLinha& pessoalinha, SLinhaEndereco& linhaendereco)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	if ( !pessoalinha.cdarearegistro.isNull() && !pessoalinha.nrlinha.isNull() ) {
		std::ostringstream numeroTerminal;
		numeroTerminal << PREFIXO_CDAREAREGISTRO;
		numeroTerminal << static_cast<int64_t>(pessoalinha.cdarearegistro);
		numeroTerminal << static_cast<int64_t>(pessoalinha.nrlinha);
		sInstalacao.numeroTerminal = numeroTerminal.str();
	}
    else
    {
		std::ostringstream numeroTerminal;
        numeroTerminal << "0000000000000";
        sInstalacao.numeroTerminal = numeroTerminal.str();
    }

	// tipoEndereco
	sInstalacao.tipoEndereco = "2";

	// endereco
	std::ostringstream endereco;
    
    //if ( !linhaendereco.nmtipologradouro.empty() )
    //    endereco << linhaendereco.nmtipologradouro << " ";
    endereco << linhaendereco.nmlogradouro;
    //endereco << " " << linhaendereco.nrendereco;
    //endereco << " " << linhaendereco.dsenderecocomplemento;
    sInstalacao.endereco = endereco.str();

	// bairro
	sInstalacao.bairro = linhaendereco.nmbairro;

	// municipio
	sInstalacao.municipio = linhaendereco.nmmunicipio;

	// uf
    sInstalacao.uf = linhaendereco.sguf;

	// cep
    if ( linhaendereco.nrcep.length() != 8 ) 
       sInstalacao.cep = "00000000";
    else
       sInstalacao.cep = linhaendereco.nrcep;

	// dataInicio
	//if ( !linhaendereco.dtcadastro.isNull() ) sInstalacao.dataInicio = linhaendereco.dtcadastro.toText(ORA_DATE_FORMAT);
    if (pessoalinha.dthabilitacao.isNull())
       sInstalacao.dataInicio = "01011900";
    else
	   sInstalacao.dataInicio = pessoalinha.dthabilitacao.toText(ORA_DATE_FORMAT);

	// dataFim
	//if ( !linhaendereco.dtexpiracao.isNull() ) sInstalacao.dataFim = linhaendereco.dtexpiracao.toText(ORA_DATE_FORMAT);
	if ( !pessoalinha.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoalinha.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

void CInstalacao::append(SPessoaLinhaHistorico& pessoalinha, SLinhaEndereco& linhaendereco)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	if ( !pessoalinha.cdarearegistro.isNull() && !pessoalinha.nrlinha.isNull() ) {
		std::ostringstream numeroTerminal;
		numeroTerminal << PREFIXO_CDAREAREGISTRO;
		numeroTerminal << static_cast<int64_t>(pessoalinha.cdarearegistro);
		numeroTerminal << static_cast<int64_t>(pessoalinha.nrlinha);
		sInstalacao.numeroTerminal = numeroTerminal.str();
	}
    else
    {
		std::ostringstream numeroTerminal;
        numeroTerminal << "0000000000000";
        sInstalacao.numeroTerminal = numeroTerminal.str();
    }

	// tipoEndereco
	sInstalacao.tipoEndereco = "2";

	// endereco
	std::ostringstream endereco;
    // if ( !linhaendereco.nmtipologradouro.empty() )
        // endereco << linhaendereco.nmtipologradouro << " ";
    endereco << linhaendereco.nmlogradouro;
    // endereco << " " << linhaendereco.nrendereco;
    // endereco << " " << linhaendereco.dsenderecocomplemento;
    sInstalacao.endereco = endereco.str();

	// bairro
	sInstalacao.bairro = linhaendereco.nmbairro;

	// municipio
	sInstalacao.municipio = linhaendereco.nmmunicipio;

	// uf
    sInstalacao.uf = linhaendereco.sguf;

	// cep
    if ( linhaendereco.nrcep.length() != 8 ) 
       sInstalacao.cep = "00000000";
    else
       sInstalacao.cep = linhaendereco.nrcep;

	// dataInicio
	//if ( !linhaendereco.dtcadastro.isNull() ) sInstalacao.dataInicio = linhaendereco.dtcadastro.toText(ORA_DATE_FORMAT);
    if (pessoalinha.dthabilitacao.isNull())
       sInstalacao.dataInicio = "01011900";
    else
       sInstalacao.dataInicio = pessoalinha.dthabilitacao.toText(ORA_DATE_FORMAT);

	// dataFim
	//if ( !linhaendereco.dtexpiracao.isNull() ) sInstalacao.dataFim = linhaendereco.dtexpiracao.toText(ORA_DATE_FORMAT);
	if ( !pessoalinha.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoalinha.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

void CInstalacao::append(SPessoaLinha& pessoalinha, SPessoaEndereco& pessoaendereco)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	if ( !pessoalinha.cdarearegistro.isNull() && !pessoalinha.nrlinha.isNull() ) {
		std::ostringstream numeroTerminal;
		numeroTerminal << PREFIXO_CDAREAREGISTRO;
		numeroTerminal << static_cast<int64_t>(pessoalinha.cdarearegistro);
		numeroTerminal << static_cast<int64_t>(pessoalinha.nrlinha);
		sInstalacao.numeroTerminal = numeroTerminal.str();
	}
    else
    {
		std::ostringstream numeroTerminal;
		numeroTerminal << "0000000000000";
		sInstalacao.numeroTerminal = numeroTerminal.str();
    }

	// tipoEndereco
	sInstalacao.tipoEndereco = "1";

	// endereco
	std::ostringstream endereco;
    // if ( !pessoaendereco.nmtipologradouro.empty() )
        // endereco << pessoaendereco.nmtipologradouro << " ";
    endereco << pessoaendereco.nmlogradouro;
    // endereco << " " << pessoaendereco.nrendereco;
    // endereco << " " << pessoaendereco.dsenderecocomplemento;
    sInstalacao.endereco = endereco.str();

	// bairro
	sInstalacao.bairro = pessoaendereco.nmbairro;

	// municipio
	sInstalacao.municipio = pessoaendereco.nmmunicipio;

	// uf
    sInstalacao.uf = pessoaendereco.sguf;

	// cep
    if ( pessoaendereco.nrcep.length() != 8 ) 
       sInstalacao.cep = "00000000";
    else
       sInstalacao.cep = pessoaendereco.nrcep;

	// dataInicio
	//if ( !pessoaendereco.dtcadastro.isNull() ) sInstalacao.dataInicio = pessoaendereco.dtcadastro.toText(ORA_DATE_FORMAT);
	sInstalacao.dataInicio = pessoalinha.dthabilitacao.toText(ORA_DATE_FORMAT);

	// dataFim
	//if ( !pessoaendereco.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoaendereco.dtexpiracao.toText(ORA_DATE_FORMAT);
	if ( !pessoalinha.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoalinha.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

void CInstalacao::append(SPessoaLinhaHistorico& pessoalinha, SPessoaEndereco& pessoaendereco)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	if ( !pessoalinha.cdarearegistro.isNull() && !pessoalinha.nrlinha.isNull() ) {
		std::ostringstream numeroTerminal;
		numeroTerminal << PREFIXO_CDAREAREGISTRO;
		numeroTerminal << static_cast<int64_t>(pessoalinha.cdarearegistro);
		numeroTerminal << static_cast<int64_t>(pessoalinha.nrlinha);
		sInstalacao.numeroTerminal = numeroTerminal.str();
	}
    else
    {
		std::ostringstream numeroTerminal;
		numeroTerminal << "0000000000000";
		sInstalacao.numeroTerminal = numeroTerminal.str();
    }

	// tipoEndereco
	sInstalacao.tipoEndereco = "1";

	// endereco
	std::ostringstream endereco;
    // if ( !pessoaendereco.nmtipologradouro.empty() )
        // endereco << pessoaendereco.nmtipologradouro << " ";
    endereco << pessoaendereco.nmlogradouro;
    // endereco << " " << pessoaendereco.nrendereco;
    // endereco << " " << pessoaendereco.dsenderecocomplemento;
    sInstalacao.endereco = endereco.str();

	// bairro
	sInstalacao.bairro = pessoaendereco.nmbairro;

	// municipio
	sInstalacao.municipio = pessoaendereco.nmmunicipio;

	// uf
    sInstalacao.uf = pessoaendereco.sguf;

	// cep
    if ( pessoaendereco.nrcep.length() != 8 ) 
       sInstalacao.cep = "00000000";
    else
       sInstalacao.cep = pessoaendereco.nrcep;

	// dataInicio
	//if ( !pessoaendereco.dtcadastro.isNull() ) sInstalacao.dataInicio = pessoaendereco.dtcadastro.toText(ORA_DATE_FORMAT);
    if (pessoalinha.dthabilitacao.isNull())
       sInstalacao.dataInicio = "01011900";
    else
       sInstalacao.dataInicio = pessoalinha.dthabilitacao.toText(ORA_DATE_FORMAT);

	// dataFim
	//if ( !pessoaendereco.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoaendereco.dtexpiracao.toText(ORA_DATE_FORMAT);
	if ( !pessoalinha.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoalinha.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

void CInstalacao::append(SPessoaLinha& pessoalinha)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	if ( !pessoalinha.cdarearegistro.isNull() && !pessoalinha.nrlinha.isNull() ) {
		std::ostringstream numeroTerminal;
		numeroTerminal << PREFIXO_CDAREAREGISTRO;
		numeroTerminal << static_cast<int64_t>(pessoalinha.cdarearegistro);
		numeroTerminal << static_cast<int64_t>(pessoalinha.nrlinha);
		sInstalacao.numeroTerminal = numeroTerminal.str();
	}
    else
    {
		std::ostringstream numeroTerminal;
		numeroTerminal << "0000000000000";
		sInstalacao.numeroTerminal = numeroTerminal.str();
    }

	// tipoEndereco
	sInstalacao.tipoEndereco = "2";

	// INDISPONIVEL
	std::ostringstream indisponivel;
	indisponivel << "INDISPON";
	indisponivel << char(205);
	indisponivel << "VEL";

	// endereco
	sInstalacao.endereco = indisponivel.str();

	// bairro
	sInstalacao.bairro = indisponivel.str();

	// municipio
	sInstalacao.municipio = indisponivel.str();

	// uf
	sInstalacao.uf = indisponivel.str();

	// cep
    sInstalacao.cep = "00000000";

	// dataInicio
    if (pessoalinha.dthabilitacao.isNull())
       sInstalacao.dataInicio = "01011900";
    else
       sInstalacao.dataInicio = pessoalinha.dthabilitacao.toText(ORA_DATE_FORMAT);

	// dataFim
	if ( !pessoalinha.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoalinha.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

void CInstalacao::append(SPessoaLinhaHistorico& pessoalinha)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	if ( !pessoalinha.cdarearegistro.isNull() && !pessoalinha.nrlinha.isNull() ) {
		std::ostringstream numeroTerminal;
		numeroTerminal << PREFIXO_CDAREAREGISTRO;
		numeroTerminal << static_cast<int64_t>(pessoalinha.cdarearegistro);
		numeroTerminal << static_cast<int64_t>(pessoalinha.nrlinha);
		sInstalacao.numeroTerminal = numeroTerminal.str();
	}
    else
    {
		std::ostringstream numeroTerminal;
		numeroTerminal << "0000000000000";
		sInstalacao.numeroTerminal = numeroTerminal.str();
    }

	// tipoEndereco
	sInstalacao.tipoEndereco = "2";

	// INDISPONIVEL
	std::ostringstream indisponivel;
	indisponivel << "INDISPON";
	indisponivel << char(205);
	indisponivel << "VEL";

	// endereco
	sInstalacao.endereco = indisponivel.str();

	// bairro
	sInstalacao.bairro = indisponivel.str();

	// municipio
	sInstalacao.municipio = indisponivel.str();

	// uf
	sInstalacao.uf = indisponivel.str();

	// cep
    sInstalacao.cep = "00000000";

	// dataInicio
    if (pessoalinha.dthabilitacao.isNull())
       sInstalacao.dataInicio = "01011900";
    else
       sInstalacao.dataInicio = pessoalinha.dthabilitacao.toText(ORA_DATE_FORMAT);

	// dataFim
	if ( !pessoalinha.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoalinha.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

void CInstalacao::append(SPessoaEndereco& pessoaendereco)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	//sInstalacao.numeroTerminal;

    sInstalacao.numeroTerminal = "0000000000000";
    
	// tipoEndereco
	sInstalacao.tipoEndereco = "1";

	// endereco
	std::ostringstream endereco;
    // if ( !pessoaendereco.nmtipologradouro.empty() )
        // endereco << pessoaendereco.nmtipologradouro << " ";
    endereco << pessoaendereco.nmlogradouro;
    // endereco << " " << pessoaendereco.nrendereco;
    // endereco << " " << pessoaendereco.dsenderecocomplemento;
    sInstalacao.endereco = endereco.str();

	// bairro
	sInstalacao.bairro = pessoaendereco.nmbairro;

	// municipio
	sInstalacao.municipio = pessoaendereco.nmmunicipio;

	// uf
    sInstalacao.uf = pessoaendereco.sguf;

	// cep
    if ( pessoaendereco.nrcep.length() != 8 ) 
       sInstalacao.cep = "00000000";
    else
       sInstalacao.cep = pessoaendereco.nrcep;

	// dataInicio
	//if ( !pessoaendereco.dtcadastro.isNull() ) sInstalacao.dataInicio = pessoaendereco.dtcadastro.toText(ORA_DATE_FORMAT);
    if ( pessoaendereco.dtcadastro.isNull()) 
       sInstalacao.dataInicio = "01011900";
    else
       sInstalacao.dataInicio = pessoaendereco.dtcadastro.toText(ORA_DATE_FORMAT);

	// dataFim
	if ( !pessoaendereco.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoaendereco.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

void CInstalacao::append(SEnderecoLinha& enderecolinha, SPessoaEndereco& pessoaendereco)
{
	SInstalacao sInstalacao;

	// numeroTerminal
	if ( !enderecolinha.cdarearegistro.isNull() && !enderecolinha.nrlinha.isNull() ) {
		std::ostringstream numeroTerminal;
		numeroTerminal << PREFIXO_CDAREAREGISTRO;
		numeroTerminal << static_cast<int64_t>(enderecolinha.cdarearegistro);
		numeroTerminal << static_cast<int64_t>(enderecolinha.nrlinha);
		sInstalacao.numeroTerminal = numeroTerminal.str();
	}
    else
    {
		std::ostringstream numeroTerminal;
		numeroTerminal << "0000000000000";
		sInstalacao.numeroTerminal = numeroTerminal.str();
    }

	// tipoEndereco
	sInstalacao.tipoEndereco = "2";

	// endereco
	std::ostringstream endereco;
    // if ( !pessoaendereco.nmtipologradouro.empty() )
        // endereco << pessoaendereco.nmtipologradouro << " ";
    endereco << pessoaendereco.nmlogradouro;
    // endereco << " " << pessoaendereco.nrendereco;
    // endereco << " " << pessoaendereco.dsenderecocomplemento;
    sInstalacao.endereco = endereco.str();

	// bairro
	sInstalacao.bairro = pessoaendereco.nmbairro;

	// municipio
	sInstalacao.municipio = pessoaendereco.nmmunicipio;

	// uf
    sInstalacao.uf = pessoaendereco.sguf;

	// cep
    if ( pessoaendereco.nrcep.length() != 8 ) 
       sInstalacao.cep = "00000000";
    else
       sInstalacao.cep = pessoaendereco.nrcep;

	// dataInicio
	//if ( !pessoaendereco.dtcadastro.isNull() ) sInstalacao.dataInicio = pessoaendereco.dtcadastro.toText(ORA_DATE_FORMAT);
    if ( enderecolinha.dthabilitacao.isNull()) 
       sInstalacao.dataInicio = "01011900";
    else
       sInstalacao.dataInicio = enderecolinha.dthabilitacao.toText(ORA_DATE_FORMAT);

	// dataFim
	//if ( !pessoaendereco.dtexpiracao.isNull() ) sInstalacao.dataFim = pessoaendereco.dtexpiracao.toText(ORA_DATE_FORMAT);
	if ( !enderecolinha.dtexpiracao.isNull() ) sInstalacao.dataFim = enderecolinha.dtexpiracao.toText(ORA_DATE_FORMAT);

	append(sInstalacao);
}

} /* namespace batch */
