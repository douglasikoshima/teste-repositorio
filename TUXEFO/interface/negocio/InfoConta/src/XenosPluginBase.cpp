#include <XenosPluginBase.h>
#include <ImagemConta.h>
#define MASK_CARACTER_LEN 36
#define MASK_TOKEN_LEN    256

bool XenosPluginBase::setInputFile(CFileXenos *file)
{
	// Copiar o arquivo passado para o input do xenos

	return false;
}

bool XenosPluginBase::getOutputFile(CFileXenos &file)
{
	return false;
}

string XenosPluginBase::readFile(char*pathFile)
{
	tuxfw_getlogger()->debug("XenosPluginBase::readFile()");
	string str = "";
	CFile file;
	file.setPath(pathFile);
	char buffer[255];
	int openfile = file.open("r");
	if(openfile == 0)
	{
		tuxfw_getlogger()->debug("Problemas ao Abrir arquivo");
		return str;
	}
	while(file.readLine(buffer,255) != 0)
	{	
		str.append(buffer);
	}
	file.close();
	tuxfw_getlogger()->debug("Total lido %d",str.length());
	return str;
}

std::string XenosPluginBase::decodeHTMLXenos(char*buffer,char*pathImage,char*css,int type,char*pathImage2,int fase)
{
	int iBoletos = 0;
	int indexCode = 0;
	int indexPage = 0;
	int pos = 0;
	int qtdTraco = 0;

	string traco = "";
	string cssStyle = css;
	string html(buffer);
	string sbuffer = "";
	string rbuffer = "";
	string divLinhaPagina = "\r\n<!--- PAG. : ";
	string divNumPagina = "";
	string divLinha = "-->\r\n</DIV>\n\r<DIV ID=Id CLASS=\"quebraLinha\">";
	string divEndLinha = "-->\r\n</DIV>\n\r";
	string comment = "<!-- ESPACAO NAO UTILIZADO -->";
	string imageHeader = "<DIV class=\"naoQuebraLinha\">\n<DIV STYLE=\"position:absolute;z-index:1;\"><img src=\"";
	char url[512+1];
	memset(&url,0,sizeof(url));
	
	// Montar a imagem lendo o parâmetro de servidor
	if(!ImagemConta::getURLImagem(url))
		 throw new TuxBasicSvcException("11E9825","Erro ao ler parametro de configuração");

	tuxfw_getlogger()->debug("url = %s",url);

	imageHeader.append(url);
	if(fase == 3)
		imageHeader.append("vivo_pe.jpg\"></img></DIV>");
	else
		imageHeader.append("vivo_pe.gif\"></img></DIV>");


	// Primeira Substituição referente ao código de barras
	while((indexCode = html.find("<w",indexCode)) != -1 || (indexCode = html.find("<W",indexCode)) != -1 ||
			(indexCode = html.find("<n",indexCode)) != -1 || (indexCode = html.find("<N",indexCode)) != -1)
	{
		iBoletos++;
		sbuffer = html.substr(indexCode,112);
		sbuffer = sbuffer.substr(1,110);
		// substitui as imagens
		for(int k=0;k<sbuffer.length();k++)
		{
			if(sbuffer[k] == 'w')
			{
				rbuffer.append("<img src=\"");
				rbuffer.append(pathImage);
				rbuffer.append("3.gif\">");
			}
			else
			if(sbuffer[k] == 'n')
			{
				rbuffer.append("<img src=\"");
				rbuffer.append(pathImage);
				rbuffer.append("1.gif\">");
			}
			else
			if(sbuffer[k] == 'W')
			{
				rbuffer.append("<img src=\"");
				rbuffer.append(pathImage);
				rbuffer.append("4.gif\">");
			}
			else
			if(sbuffer[k] == 'N')
			{				
				rbuffer.append("<img src=\"");
				rbuffer.append(pathImage);
				rbuffer.append("2.gif\">");
			}
		}
		html.erase(indexCode,112);
		html.insert(indexCode,rbuffer);
		indexCode+=112;
	}
		
	// Segunda Substituição	
	while((pos = html.find("</nobr>",pos))!=-1)
	{
		indexPage++;
		char cindexPage[32];	

		if(indexPage == 1)
		{
			sprintf(cindexPage,"%d",indexPage);
			divNumPagina = cindexPage;
			html.erase(pos,7);
			html.insert(pos,divLinhaPagina);
			html.insert(pos+divLinhaPagina.length(),divNumPagina);
			html.insert(pos+divLinhaPagina.length()+divNumPagina.length(),divLinha);
			pos+=divLinhaPagina.length()+divNumPagina.length()+divLinha.length();
		}
		else
		if(indexPage == 2 && type == DOCUMENT_TYPE_NUM_B)
		{
			// implementado para adeguar HTML para FASE3
			if(fase != 3)
			{
			html.erase(pos,7);
			html.insert(pos,comment);
			pos+=comment.length();
			}
		}
		else
		{
			int auxpos = 0;
			sprintf(cindexPage,"%d",indexPage-1);
			divNumPagina = cindexPage;
			html.erase(pos,7);	
			if((auxpos = html.find("</nobr>",pos+7))==-1)
			{
				html.insert(pos,divLinhaPagina);
				html.insert(pos+divLinhaPagina.length(),divNumPagina);
				html.insert(pos+divLinhaPagina.length()+divNumPagina.length(),divEndLinha);
				pos+=divLinhaPagina.length()+divNumPagina.length()+divEndLinha.length();				
			}
			else
			{
				html.insert(pos,divLinhaPagina);
				html.insert(pos+divLinhaPagina.length(),divNumPagina);
				html.insert(pos+divLinhaPagina.length()+divNumPagina.length(),divLinha);
				pos+=divLinhaPagina.length()+divNumPagina.length()+divLinha.length();
			}
		}
	}

	if(indexPage == 2 && type == DOCUMENT_TYPE_NUM_B)
	{		
		int pag2 = 0;
		if((pag2 = html.find(comment,pag2))!=-1)
		{
			html.insert(pag2+comment.length(),"</DIV>");
		}
	}

	m_util.replaceAllString(html,">2\252<","><");
	m_util.replaceAllString(html,">VIA<","><");

	// novo trecho
	if(type == DOCUMENT_TYPE_NUM_R)
		qtdTraco = 98;
	else if(type == DOCUMENT_TYPE_NUM_B)
		qtdTraco = 92;
		
	for(int i = 0; i < qtdTraco ; i++)
		traco.append("_ ");   

	string blackImage = "";
	blackImage.append(pathImage);
	blackImage.append("black.gif");
	m_util.replaceAllString(html,"images/black.gif",blackImage);
	m_util.replaceAllString(html,"/opt/xenosd2e5.4/d2e/IDC/projects/HTML/FASE2/output/images/","../imagens/xenos/");
	m_util.replaceAllString(html,pathImage2,"../imagens/xenos/");

	// Retirar as sequencias de Y com no mínimo 3 repetições
	m_util.replaceAll(html,"YYY","");

	// Substuir na fase 3

	if(fase == 3)
	{
		// Substituir toda sequencia de no mínimo 30 caracteres ou mais de "_" ou " " pela string traco
	m_util.replaceAll(html,"______________________________",traco);
	m_util.replaceAll(html,"                              ",traco);
		if(type == DOCUMENT_TYPE_NUM_R)
		{
			// Reimpressao de boleto
        m_util.replaceAllString(html,"<SPAN CLASS=\"ft3\" style=\"top:196.7px;left:31.9px\">", "<SPAN CLASS=\"ft3\" style=\"top:190px;left:31.9px\">");

			// Imagens R31, R32, R33, R34
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:16.2px;left:566.7px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:8px;left:580px;height:240.0px;width:25.0px;\">");
		m_util.replaceAllString(html,"R31.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "R31.png\" width=\"240\" height=\"25\" border=0 alt=\"image\">");
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:205.1px;left:39.3px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:0;top:205px;left:30px;height:850px;width:870px;\">");
		m_util.replaceAllString(html,"R32.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "R32.png\" width=\"740\" height=\"687\" border=0 alt=\"image\">");
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:583.0px;left:41.0px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:720px;left:55px;height:110px;width:44px;\">");
		m_util.replaceAllString(html,"R33.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "R33.png\" width=\"110\" height=\"44\" border=0 alt=\"image\">");
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:733.8px;left:39.3px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:580px;left:30px;height:760px;width:80px;\">");
		m_util.replaceAllString(html,"R34.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "R34.png\" width=\"740\" height=\"80\" border=0 alt=\"image\">");
		// outra substituição
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:709.6px;left:45.9px;height:17.5px;width:37.2px;\">","<DIV STYLE=\"position:absolute;z-index:1;top:580px;left:30px;height:760px;width:80px;\">");
		m_util.replaceAllString(html,"R34.png\" width=\"37.2\" height=\"17.5\" border=0 alt=\"image\">","R34.png\" width=\"740\" height=\"80\" border=0 alt=\"image\">");
		m_util.replaceAllString(html,"R35.png","R35.gif");
		}
		else if(type == DOCUMENT_TYPE_NUM_B)
		{
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:16.2px;left:566.7px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:8px;left:563px;height:240.0px;width:25.0px;\">");
		m_util.replaceAllString(html,"B31.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "B31.png\" width=\"240\" height=\"25\" border=0 alt=\"image\">");            
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:205.7px;left:39.3px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:0;top:205px;left:30px;height:850px;width:870px;\"> ");
		m_util.replaceAllString(html,"B32.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "B32.png\" width=\"740\" height=\"687\" border=0 alt=\"image\">");
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:582.7px;left:39.3px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:720px;left:55px;height:110px;width:44px;\">");
		m_util.replaceAllString(html,"B33.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "B33.png\" width=\"110\" height=\"44\" border=0 alt=\"image\">");
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:733.6px;left:39.3px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:0px;left:0px;height:0px;width:0px;\"> ");
		m_util.replaceAllString(html,"B34.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "B34.png\" width=\"0\" height=\"0\" border=0 alt=\"image\">");
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:472.6px;left:41.0px;height:17.5px;width:37.2px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:472.6px;left:41.0px;height:750px;width:50px;\">");		
			for(int g=0;g<100;g++)
			{
				string strFind = "";
				char number[3];
				strFind.append("B");
				if(g < 10) 
					strFind.append("0");
				sprintf(number,"%d",g);
				strFind.append(number);
				strFind.append(".png\" width=\"37.2\" height=\"17.5\" border=0 alt=\"image\">");
			m_util.replaceAllString(html,strFind, "B35.png\" width=\"710\" height=\"50\" border=0 alt=\"image\">");
			}
		}	
		else if(type == DOCUMENT_TYPE_NUM_U)
		{				
		m_util.replaceAllString(html,"<DIV STYLE=\"position:absolute;z-index:1;top:16.2px;left:566.7px;height:12.3px;width:42.4px;\">", "<DIV STYLE=\"position:absolute;z-index:1;top:8px;left:563px;height:240.0px;width:25.0px;\">");
		m_util.replaceAllString(html,"U31.png\" width=\"42.4\" height=\"12.3\" border=0 alt=\"image\">", "U31.png\" width=\"240\" height=\"25\" border=0 alt=\"image\">");		
		}	
	}


	// Retirar sublinhado do link www.vivo.com.br
	// <img src="images/black.gif" width="73.24" height="1.00">
	pos = 0;
	while((pos = html.find("<img src=\"../imagens/xenos/black.gif\" width=\"73.24\" height=\"1.00\">",pos))!=-1)
	{		
		html.erase(pos,66);
		pos++;
	}	
	pos = 0;
	while((pos = html.find("<img src=\"../imagens/xenos/black.gif\" width=\"93.49\" height=\"1.00\">",pos))!=-1)
	{		
		html.erase(pos,66);
		pos++;
	}

	// Retirar a sequencia SP00413-0001-78-20060519-000.003.288
	html = replace(html);


	// retornar uma substring de apenas <BODY>...</BODY>
	int posBodyStart = 0;
	int posBodyEnd = 0;
	posBodyStart = html.find("<BODY background=\"WHITE\">",posBodyStart);
	posBodyEnd = html.find("</BODY>",posBodyEnd);
	html = html.substr(posBodyStart+25,posBodyEnd-1);

	// Contatenar o css stylesheet com o arquivo lido
	string header ="";
	header.append(".quebraLinha{position: relative;height:1000px;overflow: visible;}\n");
	header.append(".naoQuebraLinha{position: relative;height:1000px;overflow: visible;}\n");
	header.append(".naoImprimir{visibility:visible;POSITION: relative;display: inline;height:150px;}\n");
	header.append("</STYLE>\n");
	header.append("<STYLE TYPE=\"text/css\" media=\"print\" >\n");
	header.append(".quebraLinha{page-break-before: always;position: relative;height:800px;overflow: visible;}\n");
	header.append(".naoQuebraLinha{position: relative;height:800px;overflow: visible;}\n");
	header.append(".naoImprimir{visibility:hidden;POSITION: relative; display: inline;height:150px;}\n");
	header.append("</STYLE>\n");
	header.append("\n\n<!-- divMark -->\n\n;");
	cssStyle.append(header);
	
	pos = 0;
	cssStyle.append(html);
	html = cssStyle;

	// Inserir o header	
	pos = 0;
	while((pos = html.find("<!-- divMark -->",pos))!=-1)
	{
		html.erase(pos,16);
		html.insert(pos,imageHeader);
		pos+=imageHeader.length();		
	}

	// remover espaços em branco entre as tags
	pos = 0;
	while((pos = html.find("</BODY></HTML>",pos))!=-1)
	{
		html.erase(pos,14);
		pos++;
	}

	return html;
}


bool XenosPluginBase::isNumeric(char*d)
{
    for(; *d; d++ )
       if ( !isdigit(*d) )
            return 0;
    return 1;
}

bool XenosPluginBase::verificarToken(char*token,int *k,int indice)
{
	//tuxfw_getlogger()->debug("XenosPluginBase::verificarToken token=%s, indice=%d\n",token,indice);
	int len = strlen(token);
	bool retorno = false;
	switch(indice)
	{
		case 0: if(len == 7) retorno = true; break;
		case 1: if(len == 4 && isNumeric(token)) retorno = true; break;
		case 2: if(len == 2) retorno = true; break;
		case 3: if(len == 8 && isNumeric(token)) retorno = true; break;
		case 4: if(len == 3 && isNumeric(token)) retorno = true; break;
		case 5: if(len == 3 && isNumeric(token)) retorno = true; break;
		case 6: if(len == 3 && isNumeric(token)) retorno = true; break;
	}
	
	memset(token,0,MASK_TOKEN_LEN);
	*k = 0;

	return retorno;
}

std::string XenosPluginBase::replace(string html)
{
	tuxfw_getlogger()->debug("XenosPluginBase::replace");
	int pos = 0;
	while((pos = html.find("1-",pos))!=-1)
	{	
		char token[MASK_TOKEN_LEN];		
		int indice = 0;
		int k = 0;
		int i = pos;		
		bool mascara = true;
		memset(&token,0,MASK_TOKEN_LEN);
		for(i=pos+2;i<pos+MASK_CARACTER_LEN;i++)
		{			
			try
			{
				char ch = html.at(i);
				if(ch == '-' || ch == '.' || ch == '>')
				{
					if(!verificarToken(token,&k,indice++))
					{
						tuxfw_getlogger()->debug("falha na mascara");
						mascara = false;
					}
				}
				else
					token[k++] = ch;
			}
			catch(out_of_range ofr)
			{
				tuxfw_getlogger()->debug("out of range");
				break;
			}
		}

		if(mascara)
		{
			//tuxfw_getlogger()->debug("apagar pos=%d",pos);
			html.erase(pos,MASK_CARACTER_LEN+2);
		}

		pos++;
	}

	return html;
}