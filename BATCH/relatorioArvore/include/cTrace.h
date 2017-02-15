#define    ERROR_LEVEL    1


class cTrace
{
private:
    int TraceOn;

public:
    cTrace( int debugOn );
    void Trace( const char* buffer, ... );
    void Trace( int level, const char* buffer, ... );
};