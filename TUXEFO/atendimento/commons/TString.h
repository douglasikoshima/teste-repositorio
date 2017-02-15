
#ifndef _TStringH_
#define _TStringH_

//------------------------------------------------------------------------------------------------
// Class  : TString 
// Author : Roberto Borges dos Santos
// Date   : 
// Purpose: Basic string.
//
//
//------------------------------------------------------------------------------------------------

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <memory.h>

//------------------------------------------------------------------------------------------------
#define MAX_STR  1024 + 1
//------------------------------------------------------------------------------------------------

class TString
{
  protected:
	//------------------------------------------------------------------------------------------------
	char  buf[MAX_STR]; 
	char  temp[MAX_STR]; 
	int   floatSize; 
	int   floatPrecision; 
	int   size;
	//------------------------------------------------------------------------------------------------
	void  _toFloat(float f)
	{
		sprintf( buf, "%*.*f", floatSize, floatPrecision, f ); 
	}
	//------------------------------------------------------------------------------------------------
  public:
	//------------------------------------------------------------------------------------------------
	TString()	{
		Clear(); 
	}
	//------------------------------------------------------------------------------------------------
	~TString()	{}
	//------------------------------------------------------------------------------------------------
	TString(char *s)
	{
	  Clear(); 
	  strcpy( buf, s );
	  size = strlen( buf );
	}
	//------------------------------------------------------------------------------------------------
	TString(const char *s)
	{
	  Clear(); 
	  strcpy( buf, s );
	  size = strlen( buf );
	}
	//------------------------------------------------------------------------------------------------
	TString(const char s)
	{
	  Clear(); 
	  *buf = s;
	  size = strlen( buf );
	}
	//------------------------------------------------------------------------------------------------
	TString(float s)
	{
	  Clear();
	  _toFloat(s);
	  size = strlen( buf );
	}
	//------------------------------------------------------------------------------------------------
	void Clear()
	{
	  memset( buf,  0, MAX_STR);
	  memset( temp, 0, MAX_STR);
	  floatSize = 9; 
	  floatPrecision = 2; 
	  size = 0;
	}
	//------------------------------------------------------------------------------------------------
	int ToInt()   { return( atoi( buf )); }
	long ToLong()   { return( atol( buf )); }
	//------------------------------------------------------------------------------------------------
	int Lenght()  { return size; }
	//------------------------------------------------------------------------------------------------
	int IsEmpty() { return size == 0; }
	//------------------------------------------------------------------------------------------------
	void Precision(int sizef, int prec=2)  { floatSize = sizef; floatPrecision = prec; }
	//------------------------------------------------------------------------------------------------

	TString SubString( int a, int b )
	{
	  memset( temp, 0, sizeof( temp ));
	  strncpy( temp, &buf[a-1], b );
	  TString ret = temp ;
	  return ret;
	}
	//------------------------------------------------------------------------------------------------
	TString &UpperCase()
	{
	 for (int i = 0; i < size; i++)
	   if ((buf[i] >= 'a') && (buf[i] <= 'z'))
		buf[i] = _toupper(buf[i]);

	 return (*this);
	}
	//------------------------------------------------------------------------------------------------
	TString &LowerCase()
	{
	 for (int i = 0; i < size; i++)
	   if ((buf[i] >= 'a') && (buf[i] <= 'z'))
		buf[i] = _tolower(buf[i]);

	 return (*this);
	}
	//------------------------------------------------------------------------------------------------
	TString &getNumber()
	{
	 int x = 0; 
	 memset( temp, 0, MAX_STR ); 

	 for (int i = 0; i < size; i++)
	 {
	   if ((buf[i] >= '0') && (buf[i] <= '9') || buf[i] == '.')
		temp[x++] = buf[i];
	 }

	 strcpy( buf, temp ); 
	 SetLength(x);

	 return (*this);
	}
	//------------------------------------------------------------------------------------------------
	TString &Trim()
	{
	  char *p;

	  strcpy( temp, buf );

	  if( !(*temp) )
	    return *this;

	  p = temp;
	  p += strlen(temp) - 1;
	  while(*p == ' ' || *p == 9) p--;
	  p++;
	  *p = '\0';
	  strcpy( buf, temp );
	  size=strlen(buf);

	  return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &LTrim()
	{
	  char *p;

	  strcpy( temp, buf );

	  if( !(*temp) )
	    return *this;

	  p = temp;
	  while(*p == ' ' || *p == '0' || *p == 9) p++;
	  strcpy( buf, p );
	  size=strlen(buf);

	  return *this;
	}

	//------------------------------------------------------------------------------------------------
	int Length() { return size; }
	//------------------------------------------------------------------------------------------------
	void SetLength(int len)
	{
	  strcpy( temp, buf );
	  temp[len]=0;
	  size=strlen(temp);
	}
	//------------------------------------------------------------------------------------------------
	char *c_str() { return ( (char *) buf ); }
	//------------------------------------------------------------------------------------------------
	TString &operator=( const char *str )
	{
	   strcpy( buf, str );
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &operator=( const char ch )
	{
	   *buf = ch;
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &operator=( float f )
	{
	   _toFloat(f);
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &operator=( int ch )
	{
	   sprintf(buf, "%d", ch);
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &operator+=( int ch )
	{
	   sprintf(temp, "%d", ch);
	   strcat( buf, temp );
	   size = strlen( buf );
	   return *this;
	}
	TString &operator=( long ch )
	{
	   sprintf(buf, "%ld", ch);
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &operator+=( long ch )
	{
	   sprintf(temp, "%ld", ch);
	   strcat( buf, temp );
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &operator+=( const char *str )
	{
	   strcat( buf, str );
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	TString &operator+=( TString str )
	{
	   strcat( buf, str.c_str() );
	   size = strlen( buf );
	   return *this;
	}
	//------------------------------------------------------------------------------------------------
	int operator==( const char *str )
	{
	   return strcmp( buf, str ) == 0;
	}
	//------------------------------------------------------------------------------------------------
	int operator!=( const char *str )
	{
	   return strcmp( buf, str ) != 0;
	}
	//------------------------------------------------------------------------------------------------
	int operator==( TString str )
	{
	   return strcmp( buf, str.c_str()) == 0 ;
	}
	//------------------------------------------------------------------------------------------------
};


#define MAX_LISTA_OBJ  128

//-----------------------------------------------------------------------------
template <class T>
//-----------------------------------------------------------------------------
class TLista
{
 protected:
   T *items[MAX_LISTA_OBJ];
   char s[32];

 public :

  TLista()
  {
    Count = 0;
    Create();
  }

  ~TLista() { Clear(); }

  int Count;

  void Create()
  {
    for( int i = 0; i < MAX_LISTA_OBJ; i++ )
    {
       items[i] = new T();
    }
    Count = 0;
  }

  void Clear()
  {
   for( int i = 0; i < MAX_LISTA_OBJ; i++ ){
    if ( items[i] != NULL )
    {
	 delete items[i];
	 items[i]=NULL;
    }

   }
   Count = 0;
  }


 void Delete( int indice )
  {
     if ( indice < MAX_LISTA_OBJ )
	delete items[indice];

     for( int i = indice; i < Count; i++ )
     {
       items[i] = items[i+1];
     }

     items[Count]=NULL;
     Count--;
  }


  void Add( T *obj ) {

    items[Count]->Copy(obj);
    Count++;
  }



  T *operator[]( int indice ) {
    return items[indice];
  }


};

#endif // define _TStringH_


