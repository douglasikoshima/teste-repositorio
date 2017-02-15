#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "atmi.h"
#include "Util.h"
#include "TuxCallerDef.h"
#include "TuxCaller.h"

#include <list>
using namespace std;

#define VIVO_GENERIC_PASSWORD	"vivo"
#define VIVO_APP_PASSWORD	    "vivo"
#define TUX_USRNAME		        "demoapp"
#define TUX_CLTNAME		        "tester"

void createList(char *theInputFile, list <CTuxCallerDef> &callerList)
{	
	CTuxCallerDef objTuxCallerDef;

	char gLine[1024];

	FILE *f;

	f = fopen(theInputFile, "rt");

	if(f)
	{
		printf("\n");

		callerList.clear();

		while(!feof(f))
		{
			if(!fgets(gLine, sizeof(gLine)-1, f))
				break;
			
			gLine[strlen(gLine) - 1] = '\0';

			objTuxCallerDef.setFileName(gLine);

			callerList.push_back(objTuxCallerDef);
		}

	}

}


void createLineList(char *theInputFile, list <long> &lineList)
{	

	char gLine[1024];
	long line;

	FILE *f;

	f = fopen(theInputFile, "rt");

	if(f)
	{
		printf("\n");

		lineList.clear();

		while(!feof(f))
		{
			if(!fgets(gLine, sizeof(gLine)-1, f))
				break;
			
			gLine[strlen(gLine) - 1] = '\0';
			
			line = atol(gLine);

			if(line > 0L)
				lineList.push_back(line);
		}

	}

}


main(int argc, char *argv[])
{

	if(argc < 3) 
	{
		fprintf(stderr, "Usage: \n\tapiLegadoExec <arquivo_api> <arquivo_linha>\n\n");
		exit(1);
	}

	if(argc >= 3)
	{
		char *theInputFile = argv[1];
		char *theInputFileLinha = argv[2];

		printf("\nprocessando arquivo_api: %s", theInputFile);
		printf("\nprocessando arquivo_linha: %s", theInputFileLinha);

		list <CTuxCallerDef> callerList;
		list <CTuxCallerDef>::iterator callerIt;

		CTuxCallerDef        objTuxCallerDef;

		list <long>           lineList;
		
		createList(theInputFile, callerList);
		createLineList(theInputFileLinha, lineList);

		long i_linha;
		char linha[32];

		char xmlIn[64 * 1024];

		while( 0 < lineList.size() )
		{
			i_linha = lineList.front();

			sprintf(linha, "%ld", i_linha);
			printf("\nlinha: %s", linha);

			for(callerIt = callerList.begin(); callerIt != callerList.end(); callerIt++) 
			{
				objTuxCallerDef = *callerIt;

				CTuxCaller caller;				
				char*      xmlOut;

				TPINIT *tpInitInfo;
				
				tpInitInfo = (TPINIT *)tpalloc("TPINIT",0,TPINITNEED(strlen(VIVO_GENERIC_PASSWORD) + 1));

				strcpy(tpInitInfo->usrname, TUX_USRNAME);
				strcpy(tpInitInfo->cltname, TUX_CLTNAME);
				strcpy(tpInitInfo->passwd, VIVO_APP_PASSWORD);
				strcpy((char *)&tpInitInfo->data,VIVO_GENERIC_PASSWORD); 

				// Attach to System/T as a Client Process 
				if (tpinit(tpInitInfo) == -1) 
				{
					fprintf(stderr, "Tpinit failed %i, %s, %i, %s\n", 
							tperrno, tpstrerror(tperrno), 
							tperrordetail(0), tpstrerrordetail(tperrordetail(0), 0));
					exit(1);
				}

				openTuxProxyFile(xmlIn, sizeof(xmlIn), objTuxCallerDef.getFileName(), "0000000000", linha);

				printf("\nxmlIn: \n%s", xmlIn);

				caller.setServiceName("TuxProxyBE");
				caller.setXmlIn(xmlIn);

				if(!caller.callService())
				{
					xmlOut = caller.getXmlOut();
					printf("\nxml out: \n%s", xmlOut);
					delete xmlOut;
				}
				else
				{
					xmlOut = caller.getXmlOut();

					if(xmlOut)
					{
						printf("\nxml out: \n%s", xmlOut);
						delete xmlOut;
					}
					else
					{
						char *error = caller.getError();
						printf("\nerror: %s", error);
						delete error;
					}
				}
				
				//callerList.pop_front();

				tpterm();

				sleep(2);

			} // callerList 

			lineList.pop_front();

		} //lineList

	}
	
	printf("\n\n");
	return 0;
}

