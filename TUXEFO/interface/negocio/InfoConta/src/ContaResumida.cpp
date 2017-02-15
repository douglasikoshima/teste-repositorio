#include "ContaResumida.h"
#include <Util.h>

ContaResumida::ContaResumida()
{
	this->m_operation = OPERACAO_CONTA_RESUMIDA;
	m_service.getLog()->setDsProcesso("Conta Resumida");
}

ContaResumida::~ContaResumida()
{

}

int ContaResumida::execute(DOMNode* dnode, XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ContaResumida::execute()");
	Util util;
	ImagemConta imagemConta;
	bool existConta = false;
	bool cache = false;
	int retcode = 0;

	char*canal =  m_mem.getTag(dnode,XML_TAG_INPUT_CANAL,0);
	char*conta =  m_mem.getTag(dnode,XML_TAG_INPUT_CONTA,0);
	char*data =  m_mem.getTag(dnode,XML_TAG_INPUT_DATA,0);
	char*retorno =  m_mem.getTag(dnode,XML_TAG_INPUT_RETORNO,0);
	char*areaRegistro =  m_mem.getTag(dnode,XML_TAG_INPUT_COD_AREA,0);
	char*linha =  m_mem.getTag(dnode,XML_TAG_INPUT_NUM_LINE,0);
	int cdAreaRegistro = 0;
	int nrLinha = 0;

	try{
		cdAreaRegistro = atoi(areaRegistro);
		nrLinha = atoi(linha);
	}catch(...){
		tuxfw_getlogger()->debug("Erro ao converter ddd e linha para inteiro");
	}

	stImagemConta imgConta;
	memset(&imgConta,0,sizeof(stImagemConta));
	strcpy(imgConta.idContaSistemaOrigem,conta);
	strcpy(imgConta.dtFimCiclo,data);
	strcpy(imgConta.sgTipoImagem,DOCUMENT_TYPE_B);
	if(retorno!=NULL && strcmp(retorno,"XML")==0)
		imgConta.inXml = 1;
	else
		imgConta.inXml = 0;

	stCommonFields field;
	memset(&field,0,sizeof(field));
	field.canal = canal;
	field.cDocType = DOCUMENT_TYPE_B;
	field.conta = conta;
	field.data = data;
	field.docType = DOCUMENT_TYPE_NUM_B;
	field.out = "";
	field.retorno = retorno;
	field.accessNbr="";

	// Verificação de imagem no disco, para melhorar a performance
	try
	{
			existConta = imagemConta.selectImagemConta(imgConta);
	}
	catch(TuxBasicOraException tboe)
	{
		tuxfw_getlogger()->debug("erro de oracle");
	}

	// O caminho da imagem foi gravado na base
	if(existConta)
	{
		// Imagem já foi processado pelo xenos
		if(imgConta.inProcessado == 1)
		{
			tuxfw_getlogger()->debug("Existe a imagem, e já foi processada pelo xenos %s",imgConta.nmImagem);
		if(this->gerarSaidaCache(imgConta.nmImagem,imgConta.inXml,xmlgen) == 1)
				return 1;			
		} 
		else // imagem ainda não foi processada pelo xenos
		{
			tuxfw_getlogger()->debug("A imagem ainda não foi processada pelo xenos");
			tuxfw_getlogger()->debug("NMPATHDETALHADA = %s",imgConta.nmPathDetalhada);
			tuxfw_getlogger()->debug("NMPATHRESUMIDA = %s",imgConta.nmPathResumida);
			tuxfw_getlogger()->debug("NMPATHBOLETO = %s",imgConta.nmPathBoleto);			
			char nomeImagemResumida[255];	// Nome da imagem final a ser gravada
			memset(&nomeImagemResumida,0,255);
			
			// Invocar método que recupera imagem de saída do xenos aplicando correções no HTML
			if(this->getImagemXenosSemEspera(field,imgConta.nmPathResumida))
			{
				// última substituição na imagem
				if( (imagemConta.consultarTipoPessoa(nrLinha, cdAreaRegistro, 2)) == 2)
				{
					tuxfw_getlogger()->debug("Tipo pessoa jurídica, substitui imagem B32.png por B32PJ.png");
					util.replaceAllString(field.out,"B32.png","B32PJ.png");
				}
				// Recuperar o nome da imagem
				this->getNomeArquivo(nomeImagemResumida,imgConta.nmPathResumida);
				// método gera saída XML e comprime arquivo criado no diretório de armazenamento
				this->gerarSaidaHTMLFile(field.out,xmlgen,nomeImagemResumida);				

				// gravamos o path de saída da imagem comprimida na base
				strcpy(imgConta.nmImagem,nomeImagemResumida);
				// sucesso, gravamos a imagem finalizada
				if(imagemConta.callProcessamento(1))
		{
			return 1;
		}
				else
				{
					return ERROR_GRAVAR_IMAGEM;
				}
			} 
			else // retornar um erro, porque a imagem ainda não foi processada pelo xenos
			{
				return ERROR_AGUARDANDO_XENOS;
			}

		}
	}

	retcode = this->exec(field,xmlgen);

	if(retcode == ERROR_DATA_NAO_ENCONTRADA)
		return 1;
	else
	if(retcode != 1)
		return retcode;


	try
	{
		strcpy(imgConta.nmPathResumida,field.outputPathXenos);
		imagemConta.callProcessamento(0);
		return ERROR_AGUARDANDO_XENOS;
	}
	catch(TuxBasicOraException tboe)
	{
		tuxfw_getlogger()->debug("erro de oracle ao salvar dados de imagem");
		return 99999; // tratar erro quando não conseguir gravar imagem
	}

	return retcode;
}


int ContaResumida::gerarSaidaXML(char*buffer,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ContaResumida::gerarSaidaXML");
	//tuxfw_getlogger()->debug("buffer passado = %s",buffer);
	DOMNode*domNode = m_mem.createDOMNode(buffer);
	if(domNode == NULL)
	{
		return -8;
	}

	DOMNode*domNodeCHAMADAS = NULL;
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->createTag("ContaDetalhadaBean");
	xmlgen->addProp("class","br.com.vivo.sce.beans.pospago.ContaDetalhadaBean");	
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");
	xmlgen->createTag("contaSP");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.conta.ContaBean");
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");
	xmlgen->createTag("chamadasLongaDistancia");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.conta.ChamadasLongaDistanciaBean");
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");
	xmlgen->addItem("foneParser","");
	int total = 0;
	char ctotal[16];
	for(int i=0; (domNodeCHAMADAS = tuxhp.walkDOM(domNode,"CHAMADAS",i)) != NULL;i++)
	{
		DOMNode*domNodeCHAMADA = NULL;
		int totalHeader = 0;
		for(int j=0; (domNodeCHAMADA = tuxhp.walkDOM(domNodeCHAMADAS,"CHAMADA",j)) != NULL;j++)
		{
			if(j>0)total++;
		}
	}
	sprintf(ctotal,"%d",total);
	xmlgen->createTag("chamadas");
	xmlgen->addProp("total",ctotal);
	xmlgen->addProp("class","java.util.ArrayList");	
	for(int i=0; (domNodeCHAMADAS = tuxhp.walkDOM(domNode,"CHAMADAS",i)) != NULL;i++)
	{
		DOMNode*domNodeCHAMADA = NULL;
		int totalHeader = 0;
		bool header3 = false;
		for(int j=0; (domNodeCHAMADA = tuxhp.walkDOM(domNodeCHAMADAS,"CHAMADA",j)) != NULL;j++)
		{							
			// O primeiro é o cabeçalho
			if(j == 0)
			{
				// verifica quantos cabeçalhos
				header3 = false;
				for(int k=1;k<10;k++)
				{
					char CABLAN[20];
					sprintf(CABLAN,"CABLAN.%d",k);
					char*valorCABLAN = m_mem.getTag(domNodeCHAMADA,CABLAN,0);
					if(valorCABLAN != NULL)
					{
						totalHeader++;
						if(k == 4 && strcmp(valorCABLAN,"Núm.Origem")==0)
							header3=true;
					}
				}
			}
			else
			{
				if(totalHeader > 0)
				{
					xmlgen->createTag("ChamadaLongaDistanciaBean");
					xmlgen->addProp("class","br.com.vivo.sce.beans.sp.conta.ChamadaLongaDistanciaBean");
				}
				for(int k=1;k<=totalHeader;k++)
				{
					char LANC[20];
					sprintf(LANC,"LANC.%d",k);
					char*valorLANC = m_mem.getTag(domNodeCHAMADA,LANC,0);
					switch(k)
					{
						case 1: xmlgen->addItem("sequencial",valorLANC); break;
						case 2: xmlgen->addItem("dataLigacao",valorLANC); break;
						case 3: xmlgen->addItem("horaInicio",valorLANC); break;
						case 4: {
									if(header3)
										xmlgen->addItem("numeroChamado",valorLANC);
									else
										xmlgen->addItem("destino",valorLANC);
								}
							break;
						case 5: {
									if(totalHeader == 8)
									{
										if(strcmp(valorLANC,"ADICIONAL")!=0)										
											xmlgen->addItem("numeroChamado",valorLANC);									
										else
											xmlgen->addItem("numeroChamado","");
									}
									if(header3)
										xmlgen->addItem("destino",valorLANC);
								}break;
						case 6: {
									if(totalHeader == 8)
										xmlgen->addItem("tarifa",valorLANC);
									else
									if(!header3)
										xmlgen->addItem("numeroChamado",valorLANC); 
								}
							break;
						case 7: (totalHeader == 8)?xmlgen->addItem("duracao",valorLANC):xmlgen->addItem("tarifa",valorLANC); break;
						case 8: (totalHeader == 8)?xmlgen->addItem("valor",valorLANC):xmlgen->addItem("duracao",valorLANC); break;
						case 9: xmlgen->addItem("valor",valorLANC); break;
						default:break;
					}
				}
				if(totalHeader > 0)
					xmlgen->closeTag();
			}			
		}		
	}
	xmlgen->closeTag(); // chamadas
	xmlgen->closeTag(); // chamadasLongaDistancia
	xmlgen->closeTag(); // contaSP
	xmlgen->closeTag(); // ChamadasLongaDistanciaBean
	xmlgen->closeTag(); // ContaDetalhadaBean
	xmlgen->closeTag(); // DADOS
	xmlgen->closeTag(); // ARG
	return 1;
}


int ContaResumida::gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen)
{
	XMLGen gen;
	int length = 0;
	this->gerarSaidaXML(buffer,&gen);
	Util::compressFile(gen.retrieveXML(&length),m_xenos.getPathFile());
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->addItem("PATH",m_xenos.getPathFile());
	xmlgen->closeTag();
	xmlgen->closeTag();	
	return 1;
}

int ContaResumida::gerarSaidaHTML(string out,XMLGen* xmlgen)
{
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->createTag("outputGetBillImage");		
	xmlgen->addCData((char*)out.c_str());
	xmlgen->closeTag();
	xmlgen->closeTag();
	xmlgen->closeTag();
	return 1;
}

int ContaResumida::gerarSaidaHTMLFile(string out,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ContaResumida::gerarSaidaHTMLFile");
	Util::compressFile((char*)out.c_str(),m_xenos.getPathFile());
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");	
	xmlgen->addItem("PATH",m_xenos.getPathFile());
	xmlgen->closeTag();
	xmlgen->closeTag();	

	return 1;
}

int ContaResumida::gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*nomeArquivo)
{
	tuxfw_getlogger()->debug("ContaResumida::gerarSaidaHTMLFile");
	char path[255+1];
	memset(&path,0,256);
	sprintf(path,"%s/%s.html.gz",tuxgetenv("PATH_XENOS_COMMON"),nomeArquivo);

	Util::compressFile((char*)out.c_str(),path);
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");	
	xmlgen->addItem("PATH",path);
	xmlgen->closeTag();
	xmlgen->closeTag();	

	return 1;
}

int ContaResumida::gerarSaidaCache(char*buffer,int tipo,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ContaResumida::gerarSaidaCache");
	CFile file;
	FILE*f = NULL;
	char path[300];
	char* extensao = (tipo)?"xml.gz":"html.gz";
	sprintf(path,"%s/%s.%s",tuxgetenv("PATH_XENOS_COMMON"),buffer,extensao);
	file.setPath(path);
	file.open("r+");
	f = file.getFile();
	file.close();
	if(f != NULL)
	{
		xmlgen->createTag("ARG");
		xmlgen->createTag("DADOS");	
		xmlgen->addItem("PATH",path);
		xmlgen->closeTag();
		xmlgen->closeTag();	
		return 1;
	}
	return 0;
}