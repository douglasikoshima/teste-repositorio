include ../../defines

DIR=servidor
SERVER=Intermed
SRCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/messages
CONFIGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/config

#EXTDEP=lista de bibliotecas utilizadas pelo servidor
#Commons=DOMTreeReporter PessoaAbstract TuxHelperClever
#Negocio=Defines Util Linha
#Servico=LstLinha
EXTDEP=-lDOMTreeReporter -lGet -lPessoaAbstract -lTuxHelperClever -lLojista -lVenda -lUtil -lAssunto -lSenha -lEndereco -lRelacionamento -lProcesso -lPessoa -lServico -lPonto -lLinha -lParametro -lAcessoRapido -lConta -lLstArvoreCli -lLstDadosIni -lLstLembretFS -lLstServicos -lLstSessao -lLstVigServ -lValLinha -lLstExtratoPO -lLstHistRelac -lLstEndereco -lLstUltAcesso -lLstProcessos -lLstTipoEnd -lLstAcessoRap -lIncAcessoRap -lExcAcessoRap -lLstPessoa -lLstQtdFavor -lLstProfile -lLstEmail -lEmail -lLstDoc -lDocumento -lLstLinha


all: $(LIBDIR)/$(SERVER)

# Link

$(LIBDIR)/$(SERVER): $(OBJ)
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(TUXDIR)/bin/buildserver \
	-o $(LIBDIR)/$(SERVER) \
	-r Oracle_XA \
	-f "$(PLIBPATH) $(DESLIBDIRS) " \
	-f "$(OBJ) $(EXTDEP)" \
	-f "-lxalan-c -lxerces-c -ltuxfw" \
	-s "LSTLINHA"
	if [ -f $(LIBDIR)/$(SERVER).txt ]; then rm $(LIBDIR)/$(SERVER).txt; fi
	echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 


install:
	if [ -f $(TUXDIR_DES)/bin/$(SERVER) ]; then rm -f $(TUXDIR_DES)/bin/$(SERVER); fi
	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/bin
	cp $(LIBDIR)/$(SERVER).txt $(TUXDIR_DES)/bin
	if [ -f $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SERVER); fi
	if [ -f $(MSGDIR)/*.h* ]; then cp -f $(MSGDIR)/*.h* $(TUXDIR_DES)/messages; fi
	if [ -f $(MSGDIR)/*.fml ]; then cp -f $(MSGDIR)/*.fml $(TUXDIR_DES)/messages; fi
	if [ -f $(MSGDIR)/*.xsd ]; then cp -f $(MSGDIR)/*.xsd $(TUXDIR_DES)/messages; fi
	chmod ug+rwx $(TUXDIR_DES)/bin/$(SERVER)
	if ! [ -d $(TUXDIR_DES)/config/ ]; then mkdir $(TUXDIR_DES)/config/; mkdir $(TUXDIR_DES)/config/env; mkdir $(TUXDIR_DES)/config/env/app; fi
	if ! [ -d $(TUXDIR_DES)/config/env ]; then mkdir $(TUXDIR_DES)/config/env; mkdir $(TUXDIR_DES)/config/env/app; fi
	if ! [ -d $(TUXDIR_DES)/config/xml ]; then mkdir $(TUXDIR_DES)/config/xml; mkdir $(TUXDIR_DES)/config/xml/app; fi
	cp -f  $(CONFIGDIR)/$(SERVER).env  $(TUXDIR_DES)/config/env/app
	cp -f  $(CONFIGDIR)/$(SERVER).xml  $(TUXDIR_DES)/config/xml/app

clean:
	rm -f $(LIBDIR)/$(SERVER)
	rm -f $(OBJ)
