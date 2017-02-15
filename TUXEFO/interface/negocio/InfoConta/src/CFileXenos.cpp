#include "CFileXenos.h"

CFileXenos::CFileXenos(char*path,int type,int extention,char*id)
{
	tuxfw_getlogger()->debug("CFileXenos::CFileXenos");
	strcpy(this->m_path,path);	
	this->m_appended = false;
	this->m_type = type;
	this->m_extension = extention;
	strcpy(this->m_id,"1");
	this->setId(id);
	this->createName();		
}

CFileXenos::~CFileXenos()
{
	tuxfw_getlogger()->debug("CFileXenos::~CFileXenos");
}

bool CFileXenos::isAppended()
{
	return this->m_appended;
}

int CFileXenos::write(char*buffer)
{
	this->m_appended = true;
	return this->append(buffer);
}

int CFileXenos::getType()
{
	return this->m_type;
}

void CFileXenos::createName()
{	
	srand ( time(NULL));
	char type[2];
	int number = rand ()%1000; 
	struct tm *data;
	time_t ltime;
    time( &ltime );
	data = localtime ( &ltime );
	int mes = data->tm_mon;
	int ano = data->tm_year;
	int dia = data->tm_mday;
	int hora = data->tm_hour;
	int minuto = data->tm_min;
	int segundo = data->tm_sec;
	char cano[4+1];
	char cmes[2+1];
	char cdia[2+1];
	char chora[2+1];
	char cminuto[2+1];
	char csegundo[2+1];

	switch(this->m_type)
	{
		case DOCUMENT_TYPE_NUM_B: strcpy(type,DOCUMENT_TYPE_B); 
			break;
		case DOCUMENT_TYPE_NUM_R: strcpy(type,DOCUMENT_TYPE_R); 
			break;
		case DOCUMENT_TYPE_NUM_U: strcpy(type,DOCUMENT_TYPE_U); 
			break;
		default: strcpy(type,"X"); 
			break;
	}

	if(mes+1 < 10)
		sprintf(cmes,"0%d",mes+1);
	else
		sprintf(cmes,"%d",mes+1);

	sprintf(cano,"%d",(ano+1900));

	if(dia < 10)
		sprintf(cdia,"0%d",dia);
	else
		sprintf(cdia,"%d",dia);

	if(hora < 10)
		sprintf(chora,"0%d",hora);
	else
		sprintf(chora,"%d",hora);

	if(minuto < 10)
		sprintf(cminuto,"0%d",minuto);
	else
		sprintf(cminuto,"%d",minuto);

	if(segundo < 10)
		sprintf(csegundo,"0%d",segundo);
	else
		sprintf(csegundo,"%d",segundo);

	try{
		char tmpName[256];
		sprintf(tmpName,"%s%s%s%s%s%s%s%d_%s",cano,cmes,cdia,chora,cminuto,csegundo,m_id,number,type);		
		//sprintf(this->m_name,"%s.afp",m_nameSimple);
		strcpy(m_name,tmpName);
		strcpy(m_nameSimple,tmpName);
		tuxfw_getlogger()->debug("NOME DO CFILEXENOS %d",this->m_extension);
		switch(this->m_extension)
		{
			case INT_FILE_EXTENSION_AFP: strcat(m_name,FILE_EXTENSION_AFP); break;
			case INT_FILE_EXTENSION_HTML: strcat(m_name,FILE_EXTENSION_HTML); break;
			case INT_FILE_EXTENSION_XML: strcat(m_name,FILE_EXTENSION_XML); break;
			default: strcat(m_name,FILE_EXTENSION_AFP);
		}
	}
	catch(...)
	{
		sprintf(this->m_name,"AFP.%s",number);
		tuxfw_getlogger()->debug("Erro ao tentar gerar nome para arquivo");
	}	
	//sprintf(this->m_path,"%s\\%s",this->m_path,this->m_name);
	sprintf(this->m_path,"%s/%s",this->m_path,this->m_name);
}

int CFileXenos::getExtension()
{
	return this->m_extension;
}

char* CFileXenos::getNameSimple()
{
	return this->m_nameSimple;
}

void CFileXenos::setId(char*id)
{
	if(id!=NULL && strlen(id) <= 256)
		strcpy(this->m_id,id);
}
