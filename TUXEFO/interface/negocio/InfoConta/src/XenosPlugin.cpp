#include <XenosPlugin.h>

XenosPlugin::XenosPlugin()
{
	this->m_cbuffer = NULL;
	strcpy(this->m_conta,"1");
}

XenosPlugin::~XenosPlugin()
{

}
/**
 * Método novo criado para ler a imagem do diretório de saída do xenos sem aguardar
 * Esse método vai checar se existe uma imagem na saída do diretório do xenos
 * Caso não exista a imagem, será retornada uma mensagem de erro.
 */
string XenosPlugin::getImageXenos(char* pathOutput,int typeRetorno, int typeImagem, int fase)
{
	tuxfw_getlogger()->debug("XenosPlugin::getImageXenos");
	char pathImage[255];		// caminho de algumas imagens para atender a nova versão do xenos
	string html = "";			// string que armazena o arquivo html
	string css = "";			// string que armazena o arquivo css
	int chmodResult = 0;		// Retorno do chmod do arquivo
	string decodedString = "";  // retorno

	mode_t _mode = S_IRWXU | S_IRWXG | S_IRWXO;
	memset(&m_pathHTML,0,SIZE_FILE_PATH);
	memset(&m_pathCSS,0,SIZE_FILE_PATH);
	memset(&m_pathOK,0,SIZE_FILE_PATH);
	memset(&pathImage,0,SIZE_FILE_PATH);

	sprintf(m_pathHTML,"%s.html",pathOutput);
	tuxfw_getlogger()->debug("m_pathHTML %s",m_pathHTML);
	sprintf(m_pathCSS,"%s.css",pathOutput);
	tuxfw_getlogger()->debug("m_pathCSS %s",m_pathCSS);
	sprintf(m_pathOK,"%s.ok",pathOutput);
	tuxfw_getlogger()->debug("m_pathOK %s",m_pathOK);

	char*cPATH_OUTPUT_XENOS_HTML = NULL;						// caminho de output do diretório da fila de conversão HTML
	char*cPATH_OUTPUT_XENOS_XML = NULL;							// caminho de output do diretório da fila de conversão XML
	char*cPATH_OUTPUT_IMAGE = NULL;								// caminho das imagens do xenos

	// Foi instalada uma nova versão do Xenos, essa versão inclui mais um diretório de filas
	// para data de ciclo igual ou superior à dd-mm-yyyy (billDates)
	// Inverte data e retira a mascara para comparar
	if(fase == 2)
	{
		cPATH_OUTPUT_XENOS_HTML = tuxgetenv("PATH_OUTPUT_XENOS_HTML");
		cPATH_OUTPUT_XENOS_XML = tuxgetenv("PATH_OUTPUT_XENOS_XML");
		cPATH_OUTPUT_IMAGE = tuxgetenv("PATH_OUTPUT_IMAGE");
	}
	else
	{
		cPATH_OUTPUT_XENOS_HTML = tuxgetenv("PATH_OUTPUT_XENOS_HTML_FASE3");
		cPATH_OUTPUT_XENOS_XML = tuxgetenv("PATH_OUTPUT_XENOS_XML_FASE3");
		cPATH_OUTPUT_IMAGE = tuxgetenv("PATH_OUTPUT_IMAGE_FASE3");
	}
	// adiciona no memorymanager para desalocar string
	m_mem.addChar(cPATH_OUTPUT_XENOS_HTML);
	m_mem.addChar(cPATH_OUTPUT_XENOS_XML);
	m_mem.addChar(cPATH_OUTPUT_IMAGE);
			
	// Montando caminho de imagem para substituir no método decodeHTMLXenos
	// Dependendo da FASE do xenos algumas mudanças entraram em paralelo, porque
	// existe apresentação de fatura de até 6 meses.
	size_t found;
	string str = pathOutput;
	found=str.find_last_of("/");
	string path = str.substr(0,found);
	string pathOutputXenosHTML = (typeRetorno == TIPO_RETORNO_HTML)?cPATH_OUTPUT_XENOS_HTML:cPATH_OUTPUT_XENOS_XML;
	int length = pathOutputXenosHTML.length();
	path = path.substr(length,path.length());
	sprintf(pathImage,"%s%s/images/",cPATH_OUTPUT_IMAGE,(char*)path.c_str());
	tuxfw_getlogger()->debug("pathImage %s",pathImage);	
	
	chmodResult = chmod(m_pathOK, _mode);
	if( (access(m_pathOK, R_OK)) == -1)
	{
		tuxfw_getlogger()->debug("Não encontrado arquivo .ok");
		return NULL;
	}

	tuxfw_getlogger()->debug("Encontrou arquivo .ok");

	if(typeRetorno == TIPO_RETORNO_HTML)
	{
		tuxfw_getlogger()->debug("carregando arquivo HTML");
		chmodResult = chmod(m_pathHTML, _mode);
		html = this->readFile(m_pathHTML);
		tuxfw_getlogger()->debug("carregando arquivo CSS");
		chmodResult = chmod(m_pathCSS, _mode);
		css = this->readFile(m_pathCSS);
		if(css.length() == 0)
		{
			tuxfw_getlogger()->debug("Arquivo CSS vazio...");
			return NULL;
		}
		tuxfw_getlogger()->debug("decodificando HTML...");
		decodedString = this->decodeHTMLXenos((char*)html.c_str(),PATH_IMAGE,(char*)css.c_str(),typeImagem,pathImage,fase);
		tuxfw_getlogger()->debug("escrevendo saída...");
		return decodedString;	
	}
	else if(typeRetorno == TIPO_RETORNO_XML)
	{
		tuxfw_getlogger()->debug("carregando arquivo XML");
		char pathXML[256];
		memset(&pathXML,0,256);
		sprintf(pathXML,"%s.xml",pathOutput);
		tuxfw_getlogger()->debug("carregando arquivo XML m_pathXML = %s",pathXML);
		chmodResult = chmod(pathXML, _mode);
		decodedString = this->readFile(pathXML);
		if(decodedString.length() == 0)
		{
			tuxfw_getlogger()->debug("Arquivo XML vazio...");
			return NULL;
		}
		return decodedString;	
	}
	return decodedString;
}

int XenosPlugin::setInputFilePermission(char*inputFile)
{
	tuxfw_getlogger()->debug("XenosPlugin::setInputFilePermission");
	
	// Colocar permissão no arquivo 666
	mode_t _mode = S_IRWXU | S_IRWXG | S_IRWXO;
	int chmodResult = chmod(inputFile, _mode);
	
	return chmodResult;
}

string XenosPlugin::getImage(CFileXenos *fTmp,int typeRetorno,int typeImagem,int fase)
{
	tuxfw_getlogger()->debug("XenosPlugin::getImage");
	memset(&m_pathAFP,0,SIZE_FILE_PATH);
	memset(&m_pathHTML,0,SIZE_FILE_PATH);
	memset(&m_pathCSS,0,SIZE_FILE_PATH);
	memset(&m_pathOK,0,SIZE_FILE_PATH);

	char pathImage[255];		// caminho de algumas imagens para atender a nova versão do xenos
	char *fileName = "";		// nome do arquivo a ser decodificado
	CFile fileOutput;			// arquivo de output - apenas para DEBUG
	string decodedString = "";	// string após o decodeHTML
	int chmodResult = 0;		// Retorno do chmod do arquivo
	int timeout = 40;			// tempo máximo para aguardar o decode do arquivo do xenos
	int tentativas = 0;			// quantidade de tentativas
	int status = 0;				// status do arquivo de ok
	string html = "";			// string que armazena o arquivo html
	string css = "";			// string que armazena o arquivo css
	char TYPE_LOWER_CASE[2];	// tipo de imagem 
	char nomeTipoImagem[256];
	char *pathInputImage = "";
	char *pathOutputImage = "";
	char *extensaoArquivo = "";

	char*cPATH_INPUT_XENOS_HTML = NULL;							// caminho de input do diretório da fila de conversão HTML
	char*cPATH_OUTPUT_XENOS_HTML = NULL;						// caminho de output do diretório da fila de conversão HTML
	char*cPATH_INPUT_XENOS_XML = NULL;							// caminho de input do diretório da fila de conversão XML
	char*cPATH_OUTPUT_XENOS_XML = NULL;							// caminho de output do diretório da fila de conversão XML
	char*cPATH_XENOS_COMMON = tuxgetenv("PATH_XENOS_COMMON");	// caminho dos arquivos comuns armazenados para cache
	char*cPATH_XENOS_TEMP = tuxgetenv("PATH_XENOS_TEMP");		// caminho dos arquivos temporários
	char*cPATH_OUTPUT_IMAGE = NULL;								// caminho das imagens do xenos

	// Foi instalada uma nova versão do Xenos, essa versão inclui mais um diretório de filas
	// para data de ciclo igual ou superior à dd-mm-yyyy (billDates)
	// Inverte data e retira a mascara para comparar
	if(fase == 2)
	{
		cPATH_INPUT_XENOS_HTML = tuxgetenv("PATH_INPUT_XENOS_HTML"); 
		cPATH_OUTPUT_XENOS_HTML = tuxgetenv("PATH_OUTPUT_XENOS_HTML");
		cPATH_INPUT_XENOS_XML = tuxgetenv("PATH_INPUT_XENOS_XML");
		cPATH_OUTPUT_XENOS_XML = tuxgetenv("PATH_OUTPUT_XENOS_XML");
		cPATH_OUTPUT_IMAGE = tuxgetenv("PATH_OUTPUT_IMAGE");
	}
	else
	{
		cPATH_INPUT_XENOS_HTML = tuxgetenv("PATH_INPUT_XENOS_HTML_FASE3"); 
		cPATH_OUTPUT_XENOS_HTML = tuxgetenv("PATH_OUTPUT_XENOS_HTML_FASE3");
		cPATH_INPUT_XENOS_XML = tuxgetenv("PATH_INPUT_XENOS_XML_FASE3");
		cPATH_OUTPUT_XENOS_XML = tuxgetenv("PATH_OUTPUT_XENOS_XML_FASE3");
		cPATH_OUTPUT_IMAGE = tuxgetenv("PATH_OUTPUT_IMAGE_FASE3");
	}

	// adiciona no memorymanager para desalocar string
	m_mem.addChar(cPATH_INPUT_XENOS_HTML);
	m_mem.addChar(cPATH_OUTPUT_XENOS_HTML);
	m_mem.addChar(cPATH_INPUT_XENOS_XML);
	m_mem.addChar(cPATH_OUTPUT_XENOS_XML);
	m_mem.addChar(cPATH_XENOS_COMMON);
	m_mem.addChar(cPATH_XENOS_TEMP);

	switch(typeImagem)
	{
		case DOCUMENT_TYPE_NUM_U: strcpy(TYPE_LOWER_CASE,"u"); strcpy(nomeTipoImagem,"detalh"); break;
		case DOCUMENT_TYPE_NUM_B: strcpy(TYPE_LOWER_CASE,"b"); strcpy(nomeTipoImagem,"resumo"); break;
		case DOCUMENT_TYPE_NUM_R: strcpy(TYPE_LOWER_CASE,"r"); strcpy(nomeTipoImagem,"boleto"); break;
	}

	// recuperar o nome do arquivo criado
	fileName = fTmp->getNameSimple();
	pathInputImage = (typeRetorno == TIPO_RETORNO_HTML)?cPATH_INPUT_XENOS_HTML:cPATH_INPUT_XENOS_XML;
	pathOutputImage = (typeRetorno == TIPO_RETORNO_HTML)?cPATH_OUTPUT_XENOS_HTML:cPATH_OUTPUT_XENOS_XML;
	extensaoArquivo = (typeRetorno == TIPO_RETORNO_HTML)?"html":"xml";

	// /online_%s/%s_%s/input_%d%s/%s_%d%s/%s.afp
	sprintf(m_pathAFP,"%s/online/%s_%s/input_%d%s/%s.afp",pathInputImage,nomeTipoImagem,TYPE_LOWER_CASE,getRandNumber(),TYPE_LOWER_CASE,fileName);
	tuxfw_getlogger()->debug("m_pathAFP %s",m_pathAFP);
	// /online_%s/%s_%s/input_%d%s/%s_%d%s/%s_%d%s/%s.html
	sprintf(m_pathHTML,"%s/online/%s_%s/output_%d%s/%s.%s",pathOutputImage,nomeTipoImagem,TYPE_LOWER_CASE,getRandNumber(),TYPE_LOWER_CASE,fileName,extensaoArquivo);
	tuxfw_getlogger()->debug("m_pathHTML %s",m_pathHTML);
	// /online_%s/%s_%s/input_%d%s/%s_%d%s/%s_%d%s/%s.css
	sprintf(m_pathCSS,"%s/online/%s_%s/output_%d%s/%s.css",pathOutputImage,nomeTipoImagem,TYPE_LOWER_CASE,getRandNumber(),TYPE_LOWER_CASE,fileName);
	tuxfw_getlogger()->debug("m_pathCSS %s",m_pathCSS);
	// /online_%s/%s_%s/input_%d%s/%s_%d%s/%s_%d%s/%s.ok
	sprintf(m_pathOK,"%s/online/%s_%s/output_%d%s/%s.ok",pathOutputImage,nomeTipoImagem,TYPE_LOWER_CASE,getRandNumber(),TYPE_LOWER_CASE,fileName);
	tuxfw_getlogger()->debug("m_pathOK %s",m_pathOK);	
	// imagens
	sprintf(pathImage,"%s/online/%s_%s/output_%d%s/images/",cPATH_OUTPUT_IMAGE,nomeTipoImagem,TYPE_LOWER_CASE,getRandNumber(),TYPE_LOWER_CASE);
	tuxfw_getlogger()->debug("pathImage %s",pathImage);	


	// Colocar permissão no arquivo 666
	mode_t _mode = S_IRWXU | S_IRWXG | S_IRWXO;
	chmodResult = chmod(m_pathAFP, _mode);
	if(chmodResult == 0)
	{
		//return NULL;
	}

	// recuperar o arquivo .ok
	CFile cssFile;
	cssFile.setPath(m_pathOK);	
	do
	{
		tuxfw_getlogger()->debug("procurando arquivo :%s",m_pathOK);
		sleep(1);
		chmodResult = chmod(m_pathOK, _mode);
		// Talves não precise colocar a segunda igualdade
		// O serviço não consegue ler o arquivo m_pathHTML mesmo
		// quando está acessível. Parece que pode ter alguma coisa haver com
		// o log do framework.
		if( (access(m_pathOK, R_OK)) != -1)
		{
			status = 1;
		}
	}
	while(status == 0 && ++tentativas <= timeout);

	if(status == 1)
	{
		char pathOutput[255];			
		char* extensao = (typeRetorno == TIPO_RETORNO_HTML)?"html.gz":"xml.gz";
		sprintf(pathOutput,"%s/%s.%s",cPATH_XENOS_COMMON,fileName,extensao);
		strcpy(this->m_pathTempFile,pathOutput);
		this->setNameTempFile(fileName);
		if(typeRetorno == TIPO_RETORNO_HTML)
		{			
			tuxfw_getlogger()->debug("carregando arquivo HTML");
			chmodResult = chmod(m_pathHTML, _mode);
			html = this->readFile(m_pathHTML);
			tuxfw_getlogger()->debug("carregando arquivo CSS");
			chmodResult = chmod(m_pathCSS, _mode);
			css = waitToReadFile(m_pathCSS,2);
			if(css.length() == 0)
			{
				tuxfw_getlogger()->debug("Arquivo CSS vazio...");
				return NULL;
			}
			tuxfw_getlogger()->debug("decodificando HTML...");
			decodedString = this->decodeHTMLXenos((char*)html.c_str(),PATH_IMAGE,(char*)css.c_str(),typeImagem,pathImage,fase);
			tuxfw_getlogger()->debug("escrevendo saída...");
			return decodedString;			
		}
		else if(typeRetorno == TIPO_RETORNO_XML)
		{
			tuxfw_getlogger()->debug("carregando arquivo XML");
			chmodResult = chmod(m_pathHTML, _mode);
			decodedString = waitToReadFile(m_pathHTML,2);
			if(decodedString.length() == 0)
			{
				tuxfw_getlogger()->debug("Arquivo XML vazio...");
				return NULL;
			}
			return decodedString;	
		}
	}
	else
	{
		tuxfw_getlogger()->debug("Arquivo AFP não decodificado...");
	}	
	return NULL;
}

int XenosPlugin::removeFiles()
{
	tuxfw_getlogger()->debug("XenosPlugin::removeFiles()");
	CFile file;
	file.setPath(m_pathHTML); 
	tuxfw_getlogger()->debug("removendo arquivo %s %s",m_pathHTML,((file.removeFile())?"OK":"FALHA"));	
	file.setPath(m_pathCSS); 
	tuxfw_getlogger()->debug("removendo arquivo %s %s",m_pathCSS,((file.removeFile())?"OK":"FALHA"));	
	file.setPath(m_pathOK); 
	tuxfw_getlogger()->debug("removendo arquivo %s %s",m_pathOK,((file.removeFile())?"OK":"FALHA"));	
	return 1;
}

string XenosPlugin::waitToReadFile(char*path,int segundos)
{
	tuxfw_getlogger()->debug("XenosPlugin::waitToReadFile()");
	string buffer = "";
	int k=0;
	for(k=0;k<segundos;k++)
	{
		buffer = this->readFile(path);
		if(buffer.length() > 0)
			break;
		else
			sleep(1);
	}
	tuxfw_getlogger()->debug("Esperou %d segundos para ler o arquivo %s",k,path);
	return buffer;
}

CFileXenos* XenosPlugin::writeBuffer(char*buffer,char*path,int typeFile)
{
	tuxfw_getlogger()->debug("XenosPlugin::writeBuffer()");
	char *bufferChar =(char*)malloc(sizeof(char)*strlen(buffer));
	memset(bufferChar,0,sizeof(char)*strlen(buffer));
	strcpy(bufferChar,buffer);
	CFileXenos*file = new CFileXenos(path,typeFile,0,this->m_conta);
	if(file->open("a+t"))
	{
		tuxfw_getlogger()->debug("XenosPlugin::writeBuffer() - escrevendo no arquivo");
		file->write(bufferChar);
		file->close();
	}
	else
	{
		tuxfw_getlogger()->debug("XenosPlugin::writeBuffer() - não conseguiu abrir o arquivo para gravação path=%s nome=%s",file->getPath(),file->getNameSimple());
		delete file;
		file = NULL;
	}
	free(bufferChar);
	return file;
}

int XenosPlugin::decodeFile(CFileXenos*encodedFile,FILE*decodedFile)
{
	tuxfw_getlogger()->debug("XenosPlugin::decodeFile()");
	char buffer[76+1];
	encodedFile->open("r+b");
	while(encodedFile->readLine(buffer,76+1))
	{
		int retorno = this->decodeBase64(buffer,decodedFile);
	}	
	encodedFile->close();
	return 1;
}

int XenosPlugin::decodeBase64(char*buffer,FILE*file)
{
	//tuxfw_getlogger()->debug("XenosPlugin::decodeBase64()");
	//tuxfw_getlogger()->debug("Buffer passado %d",strlen(buffer));
	char *nova = buffer;
	unsigned int length = 0;
	//tuxfw_getlogger()->debug("Decodificando XMLByte de entrada...");
	XMLByte* out =  Base64::decode ((const XMLByte*)nova,&length);
	free(nova);
	if(out == NULL)
	{
		tuxfw_getlogger()->debug("XMLByte NULA!");
		return NULL;
	}
	//tuxfw_getlogger()->debug("XMLByte decodificada... size = %d ",length);
	//tuxfw_getlogger()->debug("Decode OK");
	if(length == 0)
	{
		return NULL;
	}else
	{
		char ch;
		for(int i=0;i<(int)length;i++)
		{
			try
			{
				ch = (char)out[i];	
				fputc(ch,file);
			}catch(...)
			{
				tuxfw_getlogger()->debug("ERRO");
			}
		}
		XMLString::release(&out);
	}
	return 1;
}

char* XenosPlugin::getPathFile()
{
	return this->m_pathTempFile;
}


char*XenosPlugin::getNameTempFile()
{
	return this->m_nameTempFile;
}


void XenosPlugin::setNameTempFile(char*name)
{
	strcpy(this->m_nameTempFile,name);
}

void XenosPlugin::setConta(char*conta)
{
	if(conta!=NULL)
		strcpy(m_conta,conta);
}

char*XenosPlugin::getConta()
{
	return this->m_conta;
}

int XenosPlugin::getRandNumber()
{
	return this->m_randNumber;
}

void XenosPlugin::setRandNumber(int randNumber)
{
	this->m_randNumber = randNumber;
}

void XenosPlugin::generateRandNumber()
{
	srand ( time(NULL));		// para retornar numero randomico
	int numberRand = rand ()%10; 
	setRandNumber(numberRand);
}