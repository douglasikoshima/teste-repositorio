APPNAME=gestorAutorizado

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_DAT=$(COMPILE_DIR)/data

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/cfg
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
PROC_DIR=$(EXEC_DIR)/proc

all:  build

clean:
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -r -f $(EXEC_CFG)
	rm -r -f $(EXEC_BIN)
	
build:	gestorAutorizado $(APPNAME)

uninstall:
	 rm -r -f $(EXEC_DIR)
	 
install:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(DATA_DIR) ] ; then mkdir $(DATA_DIR); fi
	if [ ! -d $(PROC_DIR) ] ; then mkdir $(PROC_DIR); fi
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_SRC)/preload_gestorAutorizado.sh $(EXEC_BIN)/preload_gestorAutorizado.sh
	cp -f $(COMPILE_SRC)/truncAutorizadoGestorPJ.sql $(EXEC_BIN)/truncAutorizadoGestorPJ.sql
	cp -f $(COMPILE_CFG)/gestorAutorizado.ctl $(EXEC_BIN)/gestorAutorizado.ctl
	cp -f $(COMPILE_CFG)/gestorAutorizado.par $(EXEC_BIN)/gestorAutorizado.par
	cp -f gestorAutorizado.sh $(EXEC_DIR)/gestorAutorizado.sh
	chmod 777 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 777 $(EXEC_BIN)/preload_gestorAutorizado.sh
	chmod 777 $(EXEC_DIR)/gestorAutorizado.sh
	#cp -f $(COMPILE_DAT)/*.txt $(DATA_DIR)/

gestorAutorizado:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(DATA_DIR) ] ; then mkdir $(DATA_DIR); fi
	if [ ! -d $(PROC_DIR) ] ; then mkdir $(PROC_DIR); fi
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_DIR)/src/preload_gestorAutorizado.sh $(EXEC_BIN)/preload_gestorAutorizado.sh
	cp -f $(COMPILE_SRC)/truncAutorizadoGestorPJ.sql $(EXEC_BIN)/truncAutorizadoGestorPJ.sql
	cp -f gestorAutorizado.sh $(EXEC_DIR)/gestorAutorizado.sh
	cp -f $(COMPILE_DIR)/cfg/gestorAutorizado.ctl $(EXEC_BIN)/gestorAutorizado.ctl
	cp -f $(COMPILE_DIR)/cfg/gestorAutorizado.par $(EXEC_BIN)/gestorAutorizado.par
	chmod 777 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 777 $(EXEC_BIN)/preload_gestorAutorizado.sh
	chmod 777 $(EXEC_DIR)/gestorAutorizado.sh