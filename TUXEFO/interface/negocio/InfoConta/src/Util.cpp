#include "Util.h"

int Util::getTime()
{
	return 0;
}


int Util::getTimeHour()
{
	return 0;
}

char* Util::removeChars(char*buffer,char find)
{
	int key = 0;
	int length = strlen(buffer);
	char *novo = NULL;
	if(length > 0)
		novo = (char*)malloc(sizeof(char)*length);
	memset(novo,0,length);
	for(int i=0;i<length;i++){
		if(buffer[i] != find)
		{
			novo[key++] = buffer[i];
		}
	}
	return novo;
}

ListChar* Util::split(char*buffer,char *splitChar)
{
	ListChar *list = new ListChar();
	char * pch;
	pch = strtok (buffer,splitChar);
	while (pch != NULL)
	{	
		list->add(pch);
		pch = strtok (NULL, splitChar);
	}
	return list;
}

char* Util::formatMonth(char*date)
{
	return NULL;
}

void Util::formataData(char* pcData)
{
	char sData[11];

	memset(sData, '\0', sizeof(sData));

	if ((pcData != NULL) && (strlen(pcData) == 10))
	{
		strncpy(sData, (pcData + 8), 2);
		
		strncat(sData, "/", 1);

		strncat(sData, (pcData + 5), 2);
		
		strncat(sData, "/", 1);
		
		strncat(sData, pcData, 4);
		
		sprintf(pcData, "%s", sData);
	}
}

int Util::compressFile(char *buffer, char *outfilename)
{
	tuxfw_getlogger()->debug("Util::compressFile");
    gzFile outfile = gzopen(outfilename, "wb");
    if (!outfile) return -1;
    int num_read = strlen(buffer);
    unsigned long total_read = num_read, total_wrote = 0;
	if(num_read == 0)
	{
		tuxfw_getlogger()->debug("buffer com 0 bytes de entrada");
		return -2;
	}
    gzwrite(outfile, buffer, num_read);    
    gzclose(outfile);   
	return 1;
}

int Util::decodeBase64(char*buffer,FILE*file)
{
	//tuxfw_getlogger()->debug("Util::decodeBase64()");
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

int Util::decodeTrimBase64(char*buffer,FILE*file)
{
	tuxfw_getlogger()->debug("Util::decodeTrimBase64()");
	string str = buffer;
	int pos = 0;
	while((pos = str.find("\n",pos))!=-1)
	{
		str.erase(pos,1);
		pos++;
	}
	pos = 0;
	while((pos = str.find(" ",pos))!=-1)
	{
		str.erase(pos,1);
		pos++;
	}
	tuxfw_getlogger()->debug("%s",(char*)str.c_str());
	decodeBase64((char*)str.c_str(),file);

	return 1;
}

int Util::fmtInverteData(char*pdate)
{
	tuxfw_getlogger()->debug("Util::fmtInverteData()");
	int len = strlen(pdate);
	char data[10+1];
	int j = 0;
	memset(&data,0,sizeof(data));
	// 2007-05-06
	if(len == 10)
	{
		for(int i=0;i<len;i++)
		{
			if(pdate[i] >= '0' && pdate[i] <='9')
				data[j++] = pdate[i]; 
		}
	}
	return atoi(data);
}

/**
 * Método para substituir string
 * @param string str - string com o texto
 * @param find - token a ser procurado para substituir
 * @param replaced - token para substituir find
 **/
std::string Util::replaceAllString(string &str,string find,string replaced)
{
	int pos = 0;
	for(pos=0;(pos = str.find(find,pos))!=-1;pos++)	
		str.replace(pos,find.length(),replaced);	
	return str;
}

/**
 * Método para substituir sequencias de string
 * @param string str - string com o texto
 * @param find - token a ser procurado para substituir
 * @param replaced - token para substituir find
 **/
std::string Util::replaceAll(string &str,string find,string replaced)
{
	int pos = 0;
	int posInicial = 0;
	int posFim = 0;
	char ch = find.at(0);
	for(pos=0;(pos = str.find(find,pos))!=-1;pos++)
	{
		posInicial = pos;
		posFim = pos;
		int k = 0;
		for(int j=pos;j<str.length();j++)
		{
			if(str.at(j) == ch)
				posFim = ++k;
			else		
				break;
		}
		str.replace(posInicial, posFim, replaced);
	}	
	return str;
}