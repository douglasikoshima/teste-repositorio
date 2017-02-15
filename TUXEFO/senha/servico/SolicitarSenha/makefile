#Compile Tuxedo shared objects
#This makefile generate a shared object file
.SILENT:

#Variable Section
SO=SolicitarSenha
PC=CSolicitarSenha.pcpp
CPP=SolicitarSenha.cpp
CUSTOBJ=
FWDIR=../../../commons/tuxfw
COMMONSDIR=../../../commons

#Fix Section
PCEXT=pcpp
TUXINCDIR=$(TUXDIR)/include
export CFLAGS=-L$(ORACLE_HOME)/lib32 -L$(PLIBPATH) -L$(FWDIR)/lib
export CC='xlC -qrtti'
ORAINCDIR=-I$(ORACLE_HOME)/precomp/public
ORALIBDIR=-L$(ORACLE_HOME)/lib32	
XERCES_CLIB=-lxerces-c
AUXOBJ=$(CUSTOBJ)
FWLIB=-ltuxfw
CC=$(SACCPP) $(PINCLUDE) $(PLIBPATH) -G
SRC2OBJ=$(CC) -c
OBJ2BIN=$(CC) -o
PROC=proc include=$(TUXINCDIR) include=$(PWD)/include/ code=cpp cpp_suffix=cpp parse=none
SRC=$(CPP) $(PC:.$(PCEXT)=.cpp)
OBJ=$(CPP:.cpp=.o) $(PC:$(PCEXT)=o)
CPPL=`echo $(OBJ)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("lib/%s ",v[i]);next}'`
OBJS=$(CPPL) $(AUXOBJ)
PCCLR=`echo $(PC)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("src/%s ",v[i]);next}'`

all: $(SO)

$(SRC: = src/):src/$(SRC)
$(SRC:.cpp=.o):$(SRC)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) src/$(@:.o=.cpp) -o lib/$(@:.cpp=.o) $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR) -I$(FWDIR)/include -Iinclude  -I$(COMMONSDIR)

$(PC:.$(PCEXT)=.cpp): $(@:.cpp=.$(PCEXT))
	echo Making $(@:.cpp=.$(PCEXT)) - $@
	$(PROC) src/$(@:.cpp=.$(PCEXT))

$(SO): $(SRC:.cpp=.o)
	echo Construindo o Servico: lib/$(SO).so
	echo Compilados...........: $(CPPL)
	$(CC) -o lib/lib$(SO).so $(OBJS) $(FWLIB) $(XERCES_CLIB)
	strip lib/lib$(SO).so
	cp lib/lib$(SO).so $(TUXDIR_DES)/Senha/lib

install:
	echo Instalando Senha/lib/lib$(SO).so para -- $(TUXDIR_DES)/lib
	if ! [ -d $(TUXDIR_DES)/Senha/lib ]; then mkdir -p $(TUXDIR_DES)/Senha/lib; fi
	rm -f $(TUXDIR_DES)/Senha/lib/lib$(SO).so
	cp lib/lib$(SO).so $(TUXDIR_DES)/Senha/lib

clean:
	echo Limpando todos os Objetos e Binarios de $(SO)
	rm -f lib/*.o
	rm -f lib/*.so
	rm -f src/*.lis





