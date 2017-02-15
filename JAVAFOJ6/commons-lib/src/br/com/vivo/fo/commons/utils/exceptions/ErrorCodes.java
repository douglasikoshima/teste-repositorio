package br.com.vivo.fo.commons.utils.exceptions; 

import java.io.Serializable;
import javax.servlet.http.HttpServletResponse;

public class ErrorCodes implements Serializable{
    
	private static final long serialVersionUID = 5275314736647623708L;

	static public String translateHttpServletResponseErrorCode(int errorCode) {
        switch (errorCode) {
            case HttpServletResponse.SC_ACCEPTED:
                      return("SC_ACCEPTED: Request was accepted for processing, but was not completed.");
            case HttpServletResponse.SC_BAD_GATEWAY:
                      return(": HTTP server received an invalid response from a server it consulted when acting as a proxy or gateway.");
            case HttpServletResponse.SC_BAD_REQUEST:
                      return("SC_BAD_GATEWAY: Request sent by the client was syntactically incorrect.");
            case HttpServletResponse.SC_CONFLICT:
                      return("SC_CONFLICT: Request could not be completed due to a conflict with the current state of the resource.");
            case HttpServletResponse.SC_CONTINUE:
                      return("SC_CONTINUE: Client can continue.");
            case HttpServletResponse.SC_CREATED:
                      return("SC_CREATED: Request succeeded and created a new resource on the server.");
            case HttpServletResponse.SC_EXPECTATION_FAILED:
                      return("SC_EXPECTATION_FAILED: Server could not meet the expectation given in the Expect request header.");
            case HttpServletResponse.SC_FORBIDDEN:
                      return("SC_FORBIDDEN: Server understood the request but refused to fulfill it.");
            //case HttpServletResponse.SC_FOUND:
            //          return("SC_FOUND: Resource reside temporarily under a different URI.");
            case HttpServletResponse.SC_GATEWAY_TIMEOUT:
                      return("SC_GATEWAY_TIMEOUT: Server did not receive a timely response from the upstream server while acting as a gateway or proxy.");
            case HttpServletResponse.SC_GONE:
                      return("SC_GONE: Resource is no longer available at the server and no forwarding address is known.");
            case HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED:
                      return("SC_HTTP_VERSION_NOT_SUPPORTED: Server does not support or refuses to support the HTTP protocol version that was used in the request message.");
            case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
                      return("SC_INTERNAL_SERVER_ERROR: An error inside the HTTP server which prevented it from fulfilling the request.");
            case HttpServletResponse.SC_LENGTH_REQUIRED:
                      return("SC_LENGTH_REQUIRED: Request cannot be handled without a defined Content-Length.");
            case HttpServletResponse.SC_METHOD_NOT_ALLOWED:
                      return("SC_METHOD_NOT_ALLOWED: Method specified in the Request-Line is not allowed for the resource identified by the Request-URI.");
            case HttpServletResponse.SC_MOVED_PERMANENTLY:
                      return("SC_MOVED_PERMANENTLY: Resource has permanently moved to a new location, and that future references should use a new URI with their requests.");
            case HttpServletResponse.SC_MOVED_TEMPORARILY:
                      return("SC_MOVED_TEMPORARILY: Resource has temporarily moved to another location, but that future references should still use the original URI to access the resource.");
            case HttpServletResponse.SC_MULTIPLE_CHOICES:
                      return("SC_MULTIPLE_CHOICES: Requested resource corresponds to any one of a set of representations, each with its own specific location.");
            case HttpServletResponse.SC_NO_CONTENT:
                      return("SC_NO_CONTENT: Request succeeded but that there was no new information to return.");
            case HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION:
                      return("SC_NON_AUTHORITATIVE_INFORMATION: Meta information presented by the client did not originate from the server.");
            case HttpServletResponse.SC_NOT_ACCEPTABLE:
                      return("SC_NOT_ACCEPTABLE: Resource identified by the request is only capable of generating response entities which have content characteristics not acceptable according to the accept headers sent in the request.");
            case HttpServletResponse.SC_NOT_FOUND:
                      return("SC_NOT_FOUND: Requested resource is not available.");
            case HttpServletResponse.SC_NOT_IMPLEMENTED:
                      return("SC_NOT_IMPLEMENTED: HTTP server does not support the functionality needed to fulfill the request.");
            case HttpServletResponse.SC_NOT_MODIFIED:
                      return("SC_NOT_MODIFIED: A conditional GET operation found that the resource was available and not modified.");
            case HttpServletResponse.SC_OK:
                      return("SC_OK: Request succeeded normally.");
            case HttpServletResponse.SC_PARTIAL_CONTENT:
                      return("SC_PARTIAL_CONTENT: Server has fulfilled the partial GET request for the resource.");
            case HttpServletResponse.SC_PAYMENT_REQUIRED:
                      return("SC_PAYMENT_REQUIRED: Reserved for future use.");
            case HttpServletResponse.SC_PRECONDITION_FAILED:
                      return("SC_PRECONDITION_FAILED: Precondition given in one or more of the request-header fields evaluated to false when it was tested on the server.");
            case HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED:
                      return("SC_PROXY_AUTHENTICATION_REQUIRED: Client MUST first authenticate itself with the proxy.");
            case HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE:
                      return("SC_REQUEST_ENTITY_TOO_LARGE: Server is refusing to process the request because the request entity is larger than the server is willing or able to process.");
            case HttpServletResponse.SC_REQUEST_TIMEOUT:
                      return("SC_REQUEST_TIMEOUT: Client did not produce a request within the time that the server was prepared to wait.");
            case HttpServletResponse.SC_REQUEST_URI_TOO_LONG:
                      return("SC_REQUEST_URI_TOO_LONG: Server is refusing to service the request because the Request-URI is longer than the server is willing to interpret.");
            case HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE:
                      return("SC_REQUESTED_RANGE_NOT_SATISFIABLE: Server cannot serve the requested byte range.");
            case HttpServletResponse.SC_RESET_CONTENT:
                      return("SC_RESET_CONTENT: Agent SHOULD reset the document view which caused the request to be sent.");
            case HttpServletResponse.SC_SEE_OTHER:
                      return("SC_SEE_OTHER: Response to the request can be found under a different URI.");
            case HttpServletResponse.SC_SERVICE_UNAVAILABLE:
                      return("SC_SERVICE_UNAVAILABLE: HTTP server is temporarily overloaded, and unable to handle the request.");
            case HttpServletResponse.SC_SWITCHING_PROTOCOLS:
                      return("SC_SWITCHING_PROTOCOLS: Server is switching protocols according to Upgrade header.");
            case HttpServletResponse.SC_TEMPORARY_REDIRECT:
                      return("SC_TEMPORARY_REDIRECT: Requested resource resides temporarily under a different URI.");
            case HttpServletResponse.SC_UNAUTHORIZED:
                      return("SC_UNAUTHORIZED: Request requires HTTP authentication.");
            case HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE:
                      return("SC_UNSUPPORTED_MEDIA_TYPE: Server is refusing to service the request because the entity of the request is in a format not supported by the requested resource for the requested method.");
            case HttpServletResponse.SC_USE_PROXY:
                      return("SC_USE_PROXY: Requested resource MUST be accessed through the proxy given by the Location field.");
            default:
                return "No error message available";
        }
    }
    
} 
