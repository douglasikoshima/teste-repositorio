#Compile Tuxedo shared objects
#This makefile generate a shared object file
.SILENT:
include $(HOME)/TUXEFO/modulos.env

#Variable Section
SO=InfoConta
PC=ImagemConta.pcpp RemoteLog.pcpp
CPP=RemoteCallApi.cpp CFile.cpp CFileXenos.cpp ContaDetalhada.cpp ContaDetalhadaLocal.cpp ContaResumida.cpp ImpressaoBoleto.cpp MemoryManager.cpp OperationNotFoundException.cpp ServiceAtlys.cpp ServiceBase.cpp ServiceRouter.cpp Util.cpp XenosPlugin.cpp XenosPluginBase.cpp XMLParseException.cpp
CUSTOBJ=
COMMONS_INC=../commons/include
LIBINTER=$(TUXDIR_DES)/$(INTERFACE_PTH)/lib

#Fix Section
include ../defineNegocio

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
	echo "Install: " $(LIBINTER)/lib$(SO).so
	if ! [ -d $(LIBINTER) ]; then mkdir $(TUXDIR_DES)/$(INTERFACE_PTH); mkdir $(LIBINTER); fi
	rm -f $(LIBINTER)/lib$(SO).so
	cp lib/lib$(SO).so $(LIBINTER)

clean:
	echo Limpando todos os Objetos e Binarios de $(SO)
	rm -f lib/lib$(SO).so
	rm -f $(OBJS)
	rm -f $(PCAUX);
	rm -f $(PCAUX:.cpp=.lis);
	rm -f $(PCAUX:.cpp=.o);
