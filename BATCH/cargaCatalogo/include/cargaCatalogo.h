/*
 * cargaCatalogo.h
 *
 *  Created on: 13/11/2012
 *      Author: Jones Randis
 */

#ifndef CARGACATALOGO_H_
#define CARGACATALOGO_H_

#include <exception>
#include <string>
#include <memory>
#include <vector>
#include <ostream>
#include <ctime>
using namespace std;

#define DATAFILE_EXT 			".txt"
#define LOGFILE_EXT 			".log"
#define BADFILE_EXT 			".bad"
#define OUTFILE_EXT 			".out"

#define ID_USUARIO_ALTERACAO	800

#define EX_SUCC			0
#define EX_FAIL			1
#define EX_WARN			2
#define EX_FTL			3
#define ENDORASTR(varstr) varstr.arr[varstr.len]=0;

class CCargaCatalogo
{
public:
	CCargaCatalogo(string& configFile);
	virtual ~CCargaCatalogo();

	static ostream& log(ostream& out = cout) {
		time_t t = time(0);
		struct tm* ptm = localtime( &t );
		char buff[24];
		strftime(buff, sizeof(buff), "%Y-%m-%d %H:%M:%S -- ", ptm);
	    return (out << buff);
	}

	void Run();

private:
	class CException : public exception
	{
	public:
		CException() : emsg("CCargaCatalogo Exception!") {}
		CException(string emsg) : emsg(emsg) {}
		virtual const char* what() const throw() { return emsg.c_str(); }
		virtual ~CException() throw() {}
	protected:
		string emsg;
	}
	Exception,
	ExceptionConfigFile,
	ExceptionConfigParam,
	ExceptionParamDatabase,
	ExceptionParamControlFile,
	ExceptionPathDir,
	ExceptionControlFile,
	ExceptionSqlldrExec,
	ExceptionDatabaseConnect,
	ExceptionDatabaseDisonnect,
	ExceptionDatabaseCreateTable,
	ExceptionDatabaseSqlError,
	ExceptionIdSistemaOrigem,
	ExceptionRenameOut;

	struct CParamConf
	{
		auto_ptr<string> usr_db;
		auto_ptr<string> pwd_db;
		auto_ptr<string> inst_db;
		auto_ptr<string> path;
		auto_ptr<string> control_file;

		bool verify() {
			if ( !usr_db.get() ) return false;
			if ( !pwd_db.get() ) return false;
			if ( !inst_db.get() ) return false;
			if ( !path.get() ) return false;
			if ( !control_file.get() ) return false;
			return true;
		}
	}
	ParamConf;

	string conn_str;
	vector<string> data_files;
	vector<string>::iterator it_vec_str;
	bool connected;

	void GetParamConf(string& configFile);
	bool OpenDir();
	void DBConnect();
	void DBDisconnect();
	void DBCreateTable();
	void DBLoadTable();
	void LoadFiles();
};


#endif /* CARGACATALOGO_H_ */
