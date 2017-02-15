#include <CRelatorioCampanha.h>
#include <ctype.h>

/* Apoio. */
char* CRelatorioCampanha::trim(char *pStr)
{
	int rInd;

	if (pStr == NULL)
		return NULL;
	rInd = strlen(pStr) - 1;

	if (!isspace(pStr[rInd]))
		return pStr;

	while ( isspace(pStr[rInd]) && rInd >= 0)
		rInd--;
	pStr[rInd + 1] = '\0';
	return pStr;
}
//--------------------------------------------------------------------------------------------------------------
int CRelatorioCampanha::CheckVar(char *Campo, int indice) 
{
  return (int) ( Campo == NULL ); 
}

