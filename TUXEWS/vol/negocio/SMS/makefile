#Compile Tuxedo shared objects
#This makefile generate a shared object file
.SILENT:

#Variable Section
SO=SMS
PC=CSms.pcpp
CPP=
CUSTOBJ=
COMMONS_INC=../commons/include

#Fix Section
#include ../defineNegocio

PCEXT=pcpp
TUXINCDIR=$(TUXDIR)/include
export CFLAGS=-L$(ORALIBDIR) -L$(TUXDIR_DES)/lib
export CC='xlC -qrtti'
ORAINCDIR=-I$(ORACLE_HOME)/precomp/public
XERCES_CLIB=-lxerces-c
AUXOBJ=$(CUSTOBJ)
FWLIB=-ltuxfw
CC=$(SACCPP) $(PINCLUDE) $(PLIBPATH) -G
SRC2OBJ=$(CC) -c -g
OBJ2BIN=$(CC) -g -o
PROC=proc include=$(TUXINCDIR) include=./include code=cpp cpp_suffix=cpp parse=none
SRC=$(CPP) $(PC:.$(PCEXT)=.cpp)
OBJSAUX=*.o
PCAUX=`echo $(PC:.$(PCEXT)=.cpp)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("src/%s ",v[i]);next}'`
OBJS=`echo $(OBJSAUX)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("lib/%s ",v[i]);next}'`


all: $(SO)

$(SRC:.cpp=.o): $(PC:.$(PCEXT)=.cpp) 
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) src/$(@:.o=.cpp) -o lib/$(@:.cpp=.o) $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR) -I$(COMMONS_INC) -I$(FWDIR)/include -Iinclude

$(PC:.$(PCEXT)=.cpp): $(@:.cpp=.$(PCEXT))
	echo Making $(@:.cpp=.$(PCEXT)) - $@
	$(PROC) src/$(@:.cpp=.$(PCEXT))

$(SO): $(SRC:.cpp=.o)
	echo Construindo o Servico: lib/lib$(SO).so
	$(CC) -o lib/lib$(SO).so $(OBJS:.cpp=.o) $(FWLIB) $(XERCES_CLIB)

install:
	echo "Install: " $(TUXDIR_DES)/lib/lib$(SO).so
	rm -f $(TUXDIR_DES)/lib/lib$(SO).so
	cp lib/lib$(SO).so $(TUXDIR_DES)/lib
	if ! [ -d $(TUXDIR_DES)/include/$(SO) ]; then mkdir $(TUXDIR_DES)/include/$(SO); fi
	if [ -f  include/*.h* ]; then cp include/*.h* $(TUXDIR_DES)/include/$(SO); fi

clean:
	echo Limpando todos os Objetos e Binarios de $(SO)
	rm -f lib/lib$(SO).so
	rm -f $(OBJS)
	rm -f $(PCAUX);
	rm -f $(PCAUX:.cpp=.lis);
	rm -f $(PCAUX:.cpp=.o);
