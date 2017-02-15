#include "../include/cRelatArvore.h"


cTrace::cTrace( int debug )
{
    TraceOn = debug;
}



void cTrace::Trace( const char* buffer, ... )
{
    if ( TraceOn )
    {
	    struct tm* ptr;
	    time_t tm;
        char sData[21];

        tm = time(NULL);
        ptr = localtime(&tm);

        strftime( sData,20,"%d-%m-%Y %H:%M:%S",ptr );
        printf( "%s - ", sData );
    
        va_list args;
        va_start( args, buffer );
        vprintf( buffer, args );
        printf( "\n" );
        va_end( args );
    }

}




void cTrace::Trace( int level, const char* buffer, ... )
{

    if ( level == ERROR_LEVEL )
    {
	    struct tm* ptr;
	    time_t tm;
        char sData[32];

        tm = time(NULL);
        ptr = localtime(&tm);

        strftime( sData,20,"%d-%m-%Y %H:%M:%S",ptr );
        printf( "*** ERROR: %s - ", sData );
    
        va_list args;
        va_start( args, buffer );
        vprintf( buffer, args );
        printf( "\n" );
        va_end( args );
    }

}
