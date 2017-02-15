include ../../defines

DIR=servidor
SERVER=sCNAn02
SRCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/messages
CONFIGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/config

#EXTDEP=lista de bibliotecas utilizadas pelo servidor
#commons=DOMTreeReporter
#Negocio=Defines Util
#Servico=FILACOMPSVC, CADASTRAINSID, MSGCADSITEF, STATUSPTOCNT
#EXTDEP=-lTuxHelperClever -lsvcSenha -lVOLESenha -lsvcGestor -lVOLEGestor -lsvcLinha -lVOLELinha -lsvcProtocolos -lVOLEProtocolo
EXTDEP=-lTuxHelperClever -lsvcQuadroAviso -lVOLEQuadroAviso -lsvcMenu -lVOLEMenu -lsvcBanner -lVOLEBanner
all: $(LIBDIR)/$(SERVER)

# Link

$(LIBDIR)/$(SERVER): $(OBJ)
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(TUXDIR)/bin/buildserver \
	-o $(LIBDIR)/$(SERVER) \
	-r Oracle_XA \
	-f "$(PLIBPATH) $(DESLIBDIRS) " \
	-f "$(OBJ) $(EXTDEP)" \
	-f "-lxalan-c -lxerces-c -ltuxfw -L $(TUXDIR_DES)/Canais/lib " \
	-s "QUADROAVISO" \
	-s "BANNER" \
	-s "MENU" 
	if [ -f $(LIBDIR)/$(SERVER).txt ]; then rm $(LIBDIR)/$(SERVER).txt; fi
	echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 

install:
	if ! [ -d $(TUXDIR_DES)/Canais/bin ]; then mkdir -p $(TUXDIR_DES)/Canais/bin; fi
	if [ -f $(TUXDIR_DES)/Canais/bin/$(SERVER) ]; then rm -f $(TUXDIR_DES)/Canais/bin/$(SERVER); fi
	if [ -f $(TUXDIR_DES)/Canais/bin/$(SERVER) ]; then rm -f $(TUXDIR_DES)/Canais/bin/$(SERVER); fi
	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/Canais/bin
	cp $(LIBDIR)/$(SERVER).txt $(TUXDIR_DES)/Canais/bin
	chmod ug+rwx $(TUXDIR_DES)/Canais/bin

clean:
	rm -f $(LIBDIR)/$(SERVER)