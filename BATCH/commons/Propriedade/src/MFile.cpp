/* MFile.cpp: Classe para acesso a arquivo
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../include/MFile.h"

MFile::MFile()
{
	strcpy(m_szPath," ");
	strcpy(m_szLine," ");;
	m_iStatus=-1;
	m_arq=NULL; 
}
/*
MFile::~MFile()
{
	strcpy(m_szPath," ");
	strcpy(m_szLine," ");;
	m_iStatus=0;
	m_arq=NULL; 

}
*/
int MFile::getStatus()
{
	return m_iStatus;
}

int MFile::abrir()
{
 int ret=0;
  if(m_iStatus!=-1)
  {
	
	if((m_arq=fopen(m_szPath,"r")))	
	{
		m_iStatus=1;
		ret=1;
	}
    
  }
  return ret;
}


int MFile::getLine(char *psRet)
{
int ret=0;
char szLine[1525]="";
size_t tamSzLine = sizeof(szLine);
if(!feof(m_arq))
	{
		fgets(szLine,tamSzLine,m_arq);
		if (strlen(szLine) > tamSzLine && szLine[tamSzLine-1] == '\n')
			szLine[tamSzLine-1]='\0';
		strcpy(psRet,szLine);
		m_iStatus=2;
		ret=1;
	}
return ret;
}



int MFile::setPath(char * nPath)
{
	if(strcpy(m_szPath,nPath)) m_iStatus=0;
	return 0;
}

char* MFile::getPath() {
	return m_szPath;
}

int MFile::fechar() 
{
	return fclose(m_arq);
}

int MFile::removeFile()
{
	if(remove(m_szPath) == -1)
		return 0;
	else
		return 1;
}
