include ../../defines

DIR=negocio
SUBDIR=Senha
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#PCPP=lista de fontes a ser precompilado pelo PRO*C
PCPP=Senha.pcpp

#CPP=lista de fontes a ser compilado
SRC=$(CPP) $(PCPP:.pcpp=.cpp)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/Senha.o

all: $(LIBDIR)/lib$(SUBDIR).so

#FML
#messages/d.fml.h:messages/d.fml
#        cd messages; MKFLDHDR d.fml; cd ..

# Pre compile
$(SRCDIR)/Senha.cpp:$(SRCDIR)/Senha.pcpp $(INCDIR)/Senha.hpp makefile
	$(PROC) include=$(INCDIR) $*.pcpp

# Compile
lib/Senha.o:$(SRCDIR)/Senha.cpp $(INCDIR)/Senha.hpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/Senha.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2

install:
	if [ -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f $(MSGDIR)/*.h* ]; then cp -f $(MSGDIR)/*.h* $(TUXDIR_DES)/messages; fi
	if [ -f $(MSGDIR)/*.fml ]; then cp -f $(MSGDIR)/*.fml $(TUXDIR_DES)/messages; fi
	if [ -f $(MSGDIR)/*.xsd ]; then cp -f $(MSGDIR)/*.xsd $(TUXDIR_DES)/messages; fi

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); rm -f $(PCPP:.pcpp=.lis); cd ..

install_h:
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi
