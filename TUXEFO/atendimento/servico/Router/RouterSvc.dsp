# Microsoft Developer Studio Project File - Name="RouterSvc" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=RouterSvc - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "RouterSvc.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "RouterSvc.mak" CFG="RouterSvc - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "RouterSvc - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "RouterSvc - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "RouterSvc - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "RouterSvc - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "RouterSvc - Win32 Release"
# Name "RouterSvc - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=.\src\AuxDOMImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterAccumImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterAssembleImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterAux.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterBaseLoopImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterClientSupportImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterCodeImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterCommandImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterConstants.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterControl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterCursorImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterCursorImplAux.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterDOMImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterErrorImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterExpressionImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterExternCallImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterFunctionCallImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterLibImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterLibManImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterLogImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterLoopImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterPatrolImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterSQLImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterStepImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterStepImplAux.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterStepStockImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterStreamImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterSvc.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterSvcMan.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterSvcManAux.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterTaskImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterTraceImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterTransformImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterVarNameImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\RouterXMLImpl.cpp
# End Source File
# Begin Source File

SOURCE=.\src\Token.c
# End Source File
# Begin Source File

SOURCE=.\src\TuxSvc.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\RouterClass.h
# End Source File
# Begin Source File

SOURCE=.\include\RouterCommons.h
# End Source File
# Begin Source File

SOURCE=.\include\Token.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=.\src\RouterCursorImplAux.pcpp

!IF  "$(CFG)" == "RouterSvc - Win32 Release"

!ELSEIF  "$(CFG)" == "RouterSvc - Win32 Debug"

# Begin Custom Build
InputPath=.\src\RouterCursorImplAux.pcpp

"RouterCursorImplAux.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp prefetch=0 code=cpp $(InputPath)

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\RouterStepImplAux.pcpp

!IF  "$(CFG)" == "RouterSvc - Win32 Release"

!ELSEIF  "$(CFG)" == "RouterSvc - Win32 Debug"

# Begin Custom Build
InputPath=.\src\RouterStepImplAux.pcpp

"RouterStepImplAux.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp prefetch=0 code=cpp $(InputPath)

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\RouterSvcManAux.pcpp

!IF  "$(CFG)" == "RouterSvc - Win32 Release"

!ELSEIF  "$(CFG)" == "RouterSvc - Win32 Debug"

# Begin Custom Build
InputPath=.\src\RouterSvcManAux.pcpp

"RouterSvcManAux.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc parse=none cpp_suffix=cpp prefetch=0 code=cpp $(InputPath)

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
