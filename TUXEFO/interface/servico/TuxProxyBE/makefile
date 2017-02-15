#Compile Tuxedo shared objects
#This makefile generate a shared object file
.SILENT:
include $(HOME)/TUXEFO/modulos.env
LIBINTER=$(TUXDIR_DES)/$(INTERFACE_PTH)/lib
#Variable Section
SO=TuxProxyBE
PC=
CPP=TuxProxyBE.cpp
CUSTOBJ=
CUSTLIB=-lCreatorPlugInBE -lPlugInATLYS -lPlugInPPS -lPlugInNGIN -lPlugInBE -lInterfaceCommons -lVolNegocio

#Fix Section
include ../defineService

all: $(SO)

$(SRC:.cpp=.o): src/$(SRC)
	echo Making  $(@:.o=.cpp)
	@-mkdir lib 2> /dev/null
	$(SRC2OBJ) src/$(@:.o=.cpp) -o lib/$(@:.cpp=.o) $(ORAINCDIR) $(ORALIBDIR) $(INCDIRS) 

$(PC:.$(PCEXT)=.cpp): $(@:.cpp=.$(PCEXT))
	echo Making $(@:.cpp=.$(PCEXT)) - $@
	$(PROC) include=../commons/include src/$(@:.cpp=.$(PCEXT))	

$(SO): $(SRC:.cpp=.o)
	echo Construindo o Servico: lib/$(SO).so
	$(CC) -o lib/lib$(SO).so $(OBJS:.cpp=.o) $(FWLIB) $(XERCES_CLIB) $(CUSTLIB) -L$(TUXDIR_DES)/interface/lib

install:
	rm -f $(LIBINTER)/lib$(SO).so
	if ! [ -d $(LIBINTER) ]; then mkdir $(TUXDIR_DES)/$(INTERFACE_PTH); mkdir $(LIBINTER); fi
	cp lib/lib$(SO).so $(LIBINTER)

clean:
	echo Limpando todos os Objetos e Binarios de $(SO)
	rm -f lib/lib$(SO).so
	rm -f lib/*.o
