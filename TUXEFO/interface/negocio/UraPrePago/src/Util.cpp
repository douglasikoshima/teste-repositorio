// Util.cpp: implementation of the Util class.
//
//////////////////////////////////////////////////////////////////////

#include "Util.h"
#include <string>

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

Util::Util()
{

}

Util::~Util()
{

}

void Util::strnullcpy(char*str,char*buffer)
{
	if(buffer!=NULL)
		strcpy(str,buffer);
}

void Util::strnullncpy(char*str,char*buffer,int length)
{
	if(buffer!=NULL)
		strncpy(str,buffer,length);
}

int Util::checkLength(char*str,int length)
{
	if(str != NULL && strlen(str) > length)
		return 1;
	return 0;
}

void Util::strtrimcpy(char*str,char*buffer)
{
	strnullcpy(str,buffer);
	alltrim(str);
}

void Util::alltrim(char *pszString)
{
    ltrim(pszString);
    rtrim(pszString);
}


void Util::ltrim(char *pszString)
{
    register unsigned int iPos;
    unsigned int iLen;
    char *pszTmp, *pszAlocado;

    iLen=strlen(pszString);

    if((pszAlocado = (char *)malloc(iLen + 1)) == NULL)
        return;

    strcpy(pszAlocado, pszString);

    pszTmp=pszAlocado;


    for(iPos=0; iPos < iLen; iPos++)
        if(pszString[iPos] == 0x20)
            pszTmp++;
        else
            break;

    strcpy(pszString, pszTmp);

    free(pszAlocado);
}

void Util::rtrim(char *pszString)
{
    register int iPos;

    for(iPos=strlen(pszString)-1; iPos >= 0; iPos--)
        if(pszString[iPos] == 0x20)
            pszString[iPos] = 0x00;
        else
            break;
}

int Util::isNull(char*str)
{
	if(str == NULL)
		return 1;
	else
	if(!strcmp(str,"") || !strcmp(str," "))
		return 1;

	return 0;
}

void Util::formatDate(char*data,char*formateDate)
{
	int length = strlen(data);
	if(data != NULL && strlen(data) == 8)
	{
		for(int i=0; i< length; i++ )		
		   if ( !isdigit(data[i]) )
				return;
		sprintf(formateDate,"%c%c/%c%c/%c%c%c%c",data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]);
		formateDate[11] = 0;
	}
}