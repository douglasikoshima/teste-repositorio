#Compile Tuxedo shared objects
#This makefile generate a shared object file
.SILENT:

#Variable Section
SO=CnsRetorno
PC=CnsCanalEntrada.pcpp \
   CnsCanalEntradaSel.pcpp \
   CnsGrupo.pcpp \
   CnsGrupoSel.pcpp \
   CnsPessoa.pcpp \
   CnsPessoaSel.pcpp \
   CnsProcedencia.pcpp \
   CnsProcedenciaSel.pcpp \
   CnsRelacionamento.pcpp \
   CnsRelacionamentoSel.pcpp \
   CnsSegmentacao.pcpp \
   CnsSegmentacaoSel.pcpp \
   CnsTipoCarteira.pcpp \
   CnsTipoCarteiraSel.pcpp \
   CnsTipoLinha.pcpp \
   CnsTipoUfOperadoraSel.pcpp \
   CnsTipoUfOperadora.pcpp \
   CnsTipoLinhaSel.pcpp \
   CnsTipoRetorno.pcpp
CPP=svcmain.cpp
CUSTOBJ=
FWDIR=../../../commons/tuxfw
COMMONSDIR=../../../commons

#include $(HOME)/TUXEFO/modulos.env
#LIBATD=$(TUXDIR_DES)/$(ATENDIMENTO_PTH)/lib

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
	$(SRC2OBJ) src/$(@:.o=.cpp) -o lib/$(@:.cpp=.o) $(ORAINCDIR) $(ORALIBDIR) -I$(TUXINCDIR) -I$(FWDIR)/include -Iinclude -I$(COMMONSDIR)

$(PC:.$(PCEXT)=.cpp): $(@:.cpp=.$(PCEXT))
	echo Making $(@:.cpp=.$(PCEXT)) - $@
	$(PROC) src/$(@:.cpp=.$(PCEXT))

$(SO): $(SRC:.cpp=.o)
	echo Build Service: lib/$(SO).so
	echo Objects......: $(CPPL)
	$(CC) -o lib/lib$(SO).so $(OBJS) $(FWLIB) $(XERCES_CLIB)
	echo Now Improve Release of lib/lib$(SO).so
	strip lib/lib$(SO).so
	if [ -f $(TUXDIR_DES)/lib/lib$(SO).so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SO).so; fi
	rm -f $(TUXDIR_DES)/lib$(SO).so
	cp -p lib/lib$(SO).so $(TUXDIR_DES)/lib


install: 
	echo Instalando lib/lib$(SO).so
	if [ -f $(TUXDIR_DES)/lib/lib$(SO).so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SO).so; fi
	rm -f $(TUXDIR_DES)/lib$(SO).so
	cp -p lib/lib$(SO).so $(TUXDIR_DES)/lib

clean:
	echo Clean All Objects And Binarys of $(SO)
	cd src; rm -f $(PC:.pcpp=.cpp); cd ..
	rm -f lib/*.o
	rm -f lib/*.so
	rm -f src/*.lis
