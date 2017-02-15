#include "../include/CFile.h"

CFile::CFile()
{
	this->m_openned = false;
	this->m_file = NULL;
}
CFile::CFile(FILE*file)
{
	this->m_file = file;
}
CFile::~CFile()
{
	if(this->m_openned && this->m_file !=NULL)
	{
		this->close();
	}
}
int CFile::write(char* buffer)
{
	if(buffer != NULL)
	return fputs(buffer,this->m_file);	
	return 0;
}

int CFile::writew(wchar_t *buffer)
{
	if(buffer != NULL)
	return fputws(buffer,this->m_file);	
	return 0;
}

FILE* CFile::getFile()
{
	return this->m_file;
}


int CFile::readLine(char* buffer, int length)
{
	int ret=0;
	if(!feof(m_file))
	{
		fgets(buffer,length,m_file);
		ret=1;
	}
	return ret;
}


int CFile::append(char*buffer)
{
	return fputs(buffer,this->m_file);
}

int CFile::open(char*modo)
{
	strcpy(m_modo,modo);
	this->m_file = fopen(this->m_path,modo);
	if(this->m_file)
	{
		this->m_openned = true;
		return 1;
	}
	else
	{
		this->m_openned = false;
		return 0;
	}
}

int CFile::setPath(char*path)
{
	try
	{
		strcpy(m_path,path);
	}
	catch(...)
	{
		return 0;
	}
	return 1;
}

char* CFile::getPath()
{
	return this->m_path;
}

int CFile::close()
{
	if(fclose(this->m_file) == EOF )
	{
		return 0;
	}
	else
	{
		this->m_openned = false;
		return 1;
	}
}

int CFile::removeFile()
{
	if(remove(this->m_path) == -1)
		return 0;
	else
		return 1;
}

long CFile::getSize()
{
	long lSize = 0;
	fseek (this->m_file,0,SEEK_END);
	lSize = ftell (this->m_file);
	rewind (this->m_file);
	return lSize;
}