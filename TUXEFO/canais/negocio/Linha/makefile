include ../../defines

DIR=negocio
SUBDIR=Linha
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
INCPROC=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#PCPP=lista de fontes a ser precompilado pelo PRO*C
PCPP=Linha.pcpp

#CPP=lista de fontes a ser compilado
SRC=$(CPP) $(PCPP:.pcpp=.cpp)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/Linha.o

all: $(LIBDIR)/lib$(SUBDIR)Canais.so

#FML
#messages/d.fml.h:messages/d.fml
#        cd messages; MKFLDHDR d.fml; cd ..

# Pre compile
$(SRCDIR)/Linha.cpp:$(SRCDIR)/Linha.pcpp $(INCDIR)/Linha.hpp makefile
	$(PROC) include=$(INCPROC) $*.pcpp

# Compile
lib/Linha.o:$(SRCDIR)/Linha.cpp $(INCDIR)/Linha.hpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/Linha.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR)Canais.so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR)Canais.so $(OBJ) -lxml2
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	if [ -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR)Canais.so ]; then rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR)Canais.so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR)Canais.so $(TUXDIR_DES)/Canais/lib	


install:
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	if [ -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR)Canais.so ]; then rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR)Canais.so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR)Canais.so $(TUXDIR_DES)/Canais/lib	

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); rm -f $(PCPP:.pcpp=.lis); cd ..

install_h:
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi