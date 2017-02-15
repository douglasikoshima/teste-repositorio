#Compile Tuxedo negocio
include ../../defines

DIR=negocio
SUBDIR=CadInSid
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
INCPROC=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#PCPP=lista de fontes a ser precompilado pelo PRO*C
PCPP=CadInSid.pcpp

#CPP=lista de fontes a ser compilado
SRC=$(CPP) $(PCPP:.pcpp=.cpp)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/CadInSid.o

all: $(LIBDIR)/lib$(SUBDIR).so

#FML
#messages/d.fml.h:messages/d.fml
#        cd messages; MKFLDHDR d.fml; cd ..

# Pre compile
$(SRCDIR)/CadInSid.cpp:$(SRCDIR)/CadInSid.pcpp $(INCDIR)/CadInSid.hpp makefile
	$(PROC) include=$(INCPROC) $*.pcpp

# Compile
lib/CadInSid.o:$(SRCDIR)/CadInSid.cpp $(INCDIR)/CadInSid.hpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/CadInSid.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2

install:
	if [ -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/Canais/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); rm -f $(PCPP:.pcpp=.lis); cd ..

install_h:
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi
