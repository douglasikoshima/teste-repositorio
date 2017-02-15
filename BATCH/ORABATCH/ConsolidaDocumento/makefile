
APPNAME=ConsolidaDocumento

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)/cfg
EXEC_LOG=$(EXEC_DIR)/log
EXEC_SQL=$(EXEC_DIR)/sql
EXEC_CTL=$(EXEC_DIR)/ctl
all:

clean:
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -f $(EXEC_CFG)/$(APPNAME).cfg
	rm -f $(EXEC_SQL)/$(APPNAME).sql
	rm -f $(EXEC_SQL)/consolida_cnpj.sql
	rm -f $(EXEC_SQL)/consulta_parametro.sql
	rm -f $(EXEC_SQL)/contaRegistros.sql
	rm -f $(EXEC_SQL)/nrDocumentos.sql

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_SQL) ] ; then mkdir $(EXEC_SQL); fi
	if [ ! -d $(EXEC_CTL) ] ; then mkdir $(EXEC_CTL); fi

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)
	cp -pf $(COMPILE_SQL)/$(APPNAME).sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/consolida_cnpj.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/consulta_parametro.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/contaRegistros.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/nrDocumentos.sql $(EXEC_SQL)

	# seta permissoes
	chmod ug=rwx,o=rwx $(EXEC_DIR)/$(APPNAME).sh
	chmod ug=rw,o=rw $(EXEC_CFG)/$(APPNAME).cfg
	chmod ug=rw,o=rw $(EXEC_SQL)/$(APPNAME).sql
	chmod ug=rw,o=rw $(EXEC_SQL)/consolida_cnpj.sql
	chmod ug=rw,o=rw $(EXEC_SQL)/consulta_parametro.sql
	chmod ug=rw,o=rw $(EXEC_SQL)/contaRegistros.sql
	chmod ug=rw,o=rw $(EXEC_SQL)/nrDocumentos.sql
	chmod ug=rwx,o=rx $(EXEC_SQL)
	chmod ug=rwx,o=rwx $(EXEC_LOG)
	chmod ug=rwx,o=rwx $(EXEC_CTL)

uninstall:
	rm -r -f $(EXEC_DIR)

