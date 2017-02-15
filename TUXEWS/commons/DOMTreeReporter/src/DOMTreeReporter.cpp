// ---------------------------------------------------------------------------
//  Includes
// ---------------------------------------------------------------------------
#include <xercesc/util/XercesDefs.hpp>
#include <xercesc/sax/SAXParseException.hpp>
#include <DOMTreeReporter/DOMTreeReporter.hpp>

#if defined(XERCES_NEW_IOSTREAMS)
#include <iostream>
#else
#include <iostream.h>
#endif

inline XERCES_STD_QUALIFIER ostream& operator<<(XERCES_STD_QUALIFIER ostream& target, const StrX& toDump)
{
    target << toDump.localForm();
    return target;
}

void DOMTreeReporter::warning(const SAXParseException&)
{
    //
    // Ignore all warnings.
    //
}

void DOMTreeReporter::error(const SAXParseException& toCatch)
{
    fSawErrors = true;
    XERCES_STD_QUALIFIER cerr << "Error at file \"" << StrX(toCatch.getSystemId())
		 << "\", line " << toCatch.getLineNumber()
		 << ", column " << toCatch.getColumnNumber()
         << "\n   Message: " << StrX(toCatch.getMessage()) << XERCES_STD_QUALIFIER endl;
}

void DOMTreeReporter::fatalError(const SAXParseException& toCatch)
{
    fSawErrors = true;
    XERCES_STD_QUALIFIER cerr << "Fatal Error at file \"" << StrX(toCatch.getSystemId())
		 << "\", line " << toCatch.getLineNumber()
		 << ", column " << toCatch.getColumnNumber()
         << "\n   Message: " << StrX(toCatch.getMessage()) << XERCES_STD_QUALIFIER endl;
}

void DOMTreeReporter::resetErrors()
{
    fSawErrors = false;
}


