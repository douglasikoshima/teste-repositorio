#ifndef IMPRESSAOBOLETO_H_HEADER_INCLUDED_BD900019
#define IMPRESSAOBOLETO_H_HEADER_INCLUDED_BD900019
#include "ServiceBase.h"
#include "ImagemConta.h"

class ImpressaoBoleto : public ServiceBase
{
public:
	ImpressaoBoleto();
	~ImpressaoBoleto();
	// executar a regra de negócio
    virtual int execute(DOMNode* dnode, XMLGen* xmlgen);
	int gerarSaidaXML(char*buffer,XMLGen* xmlgen);
	int gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen);
	int gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen,char*nomeArquivo);
	int gerarSaidaHTML(string out,XMLGen* xmlgen);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen);
	int gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*nomeArquivo);
	int gerarSaidaCache(char*buffer,int tipo,XMLGen* xmlgen);
public:
	// atributos
	char m_sgUF[255+1];
};



#endif /* IMPRESSAOBOLETO_H_HEADER_INCLUDED_BD900019 */
