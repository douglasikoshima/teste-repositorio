/* SplitLine.h: Facilidades de tratamento de strings
*/
 
#ifndef SPLITLINEH
#define  SPLITLINEH

#include <string.h>

class SplitLine
{
private:
	char szLine[1525];
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

#endif
