#include <string.h>
#include <stdio.h>
#include "Util.h"

void openTuxProxyFile(char *xml, int sizexml, char *pcFile, char *pcFind, char *pcReplace)
{
	int  iLineLen  = 0;
	int  iFileLen  = 0;
	int  iXmlInLen = 0;

	char *pcXmlIn;
	char cLine[32 * 1024];

	FILE *f;

	printf("\n\nabrindo arquivo: %s", pcFile);

	f = fopen(pcFile, "rt");

	if(f)
	{
		// tamanho máximo para o arquivo
		iXmlInLen = sizexml;

		pcXmlIn = xml;
		pcXmlIn[0] = '\0';

		while(!feof(f))
		{
			if(!fgets(cLine, sizeof(cLine) - 1, f))
				break;

			//printf("\ncLine: %s", cLine);

			iLineLen = strlen(cLine);

			if( (iLineLen + iFileLen) < iXmlInLen - 1)
				strcat(pcXmlIn, cLine);
			else
			{
				fclose(f);
				return;
			}

			iFileLen += iLineLen;
		}
	}

	// não pode ser nada complexo
	// somente para trocar número do telefone
	// fora isso não vamos validar nada
	if(strlen(pcFind) == strlen(pcReplace))
	{
		int iLen = strlen(pcXmlIn);

		for(int i = 0; i < iLen; i++)
		{
			if(pcXmlIn[i] == pcFind[0])
			{
				int iOk = 1;
				int iFindLen = strlen(pcFind);

				for(int j = 0; j < iFindLen; j++)
				{
					if(pcXmlIn[i + j] != pcFind[j])
					{
						iOk = 0;
						break;
					}
				}

				if(iOk)
					strncpy(&pcXmlIn[i], pcReplace, iFindLen);

			}
		}
	}

	fclose(f);
}