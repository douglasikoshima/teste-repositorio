#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>

#include "../include/md5.h"
#include "../include/MDFive.h"
#include <tuxfw.h>

CMDFive::CMDFive(void)
{
}

CMDFive::~CMDFive(void)
{
}

/**********************************************************************************************/
char *CMDFive::getMD5(char *pszStringIn)
{
    static char szStringOut[32 + 1];
    register int j;
    char *hexfmt = "%02X";
    unsigned char signature[16];
    struct MD5Context md5c;

    ULOG("pszStringIn[%s]", pszStringIn);

    MD5Init(&md5c);
    MD5Update(&md5c, (unsigned char *) pszStringIn, strlen(pszStringIn));
	MD5Final(signature, &md5c);

    for (j = 0; j < sizeof signature; j++) {
    	sprintf(szStringOut+(j*2), hexfmt, signature[j]);
    }

    ULOG("szStringOut[%s]", szStringOut);
    return szStringOut;
}
