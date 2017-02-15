/* SplitLine.h: Facilidades de tratamento de strings
*/
 
#ifndef SPLITLINEH
#define  SPLITLINEH

class SplitLine{
private:
	char szLine[255];
	char cDiv;
	
public:
	SplitLine();
	/*~SplitLine();*/
	
	int SetDiv(char);
	int SetLine(char *);
	
	int GetAfterDiv(char *);
	int GetBeforeDiv(char *);
	
	int Split(char*,int,int);


};

SplitLine::SplitLine()
{
	strcpy(szLine," ");
	cDiv=' ';
}
/*
SplitLine::~SplitLine()
{
	strcpy(szLine," ");
	cDiv=' ';
}
*/
int SplitLine::SetDiv(char sDiv)
{
	cDiv=sDiv;
	return 1;
}
	
int SplitLine::SetLine(char *pNline){
 
	if(strcpy(szLine,pNline))
		 return 1;
	else return 0;

}	
int SplitLine::GetAfterDiv(char *pAfter)
{
	int ret=0;
	int crtAf=0;
	char szAfter[40];
	int size=strlen(szLine);
	
	if(size>2)
	{
	 int i=0;
	 int istatus=0;
	 while(i<size)
	 {
			
			
			if(istatus)
			{
			 szAfter[crtAf]=szLine[i];
			 crtAf++; 
			}
			if(szLine[i]==cDiv) istatus=1;
	 i++;
	}
	 szAfter[crtAf]='\0';
	 if (szAfter[crtAf-1] == '\n')
		szAfter[crtAf-1] = '\0';
	 strcpy(pAfter,szAfter);
	 ret=1;		
	}
	else
	{

	ret=0;
	}
 return ret;
}

int SplitLine::GetBeforeDiv(char *pBefore)
{
	int ret=0;
	char szBefore[40];
	int size=strlen(szLine);
	if(size>2)
	{
	int i=0;
	while(szLine[i]!=cDiv)
	{
	     szBefore[i]=szLine[i];
		 i++;
	}
	 szBefore[i]='\0';
	 strcpy(pBefore,szBefore);
	 ret=1;		
	
	}
	else
	{
		ret=0;
	}
 return ret;

}
	
int SplitLine::Split(char* pMidle,int iBegin,int iEnd)
{
	int ret=0;
	char szMidle[40];
	int crtLine=0;
	if(strlen(szLine)>2 && iBegin>=0 && strlen(szLine)>=(unsigned)iEnd)
	{
	for(int i=iBegin;i<iEnd;i++)
		{
          szMidle[i]=szLine[crtLine];
		  crtLine++;
		}
	 szMidle[crtLine]='\0';
	 strcpy(pMidle,szMidle);
	 ret=1;		
	}
	else
	{

	ret=0;
	}
 return ret;

}
#endif
