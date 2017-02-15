/**
 * Copyright 2003 Sun Microsystems, Inc. All Rights Reserved
 * Patents Pending.Use is subject to license terms.
 */
/**
 * PROPRIETARY/CONFIDENTIAL. Use of this product is subject to
 * license terms. Copyright 2001 Sun Microsystems, Inc.
 * Some preexisting portions Copyright 2001 Netscape Communications Corp.
 * All rights reserved.
 */
/*
 * Copyright (c) 2001 Sun Microsystems, Inc.
 * Some preexisting portions Copyright (c) 2001 Netscape Communications Corp.
 * All rights reserved.
 *
 * Use of this product is subject to license terms.
 * Federal Acquisitions: Commercial Software -- Government Users Subject
 * to Standard License Terms and Conditions.
 */

/*
 * iPlanet Directory Server 4.x plugin API backwards compatible header file.
 */
#ifndef _SLAPIPLUGINCOMPAT4
#define _SLAPIPLUGINCOMPAT4

#ifdef __cplusplus
extern "C" {
#endif


/******* Deprecated (obsolete) macros ****************************************/
/*
 * The SLAPI_BIND_FAIL_OR_ANONYMOUS bind function return code is deprecated.
 * Use SLAPI_BIND_FAIL or SLAPI_BIND_ANONYMOUS instead.
 */
#define SLAPI_BIND_FAIL_OR_ANONYMOUS	1    /* back end should send result */


/******* Deprecated (obsolete) pblock arg identifiers ************************/
#define SLAPI_CONN_AUTHTYPE    		144	/* use SLAPI_CONN_AUTHTYPE */
#define SLAPI_REQUESTOR_ISUPDATEDN	SLAPI_IS_REPLICATED_OPERATION
#define SLAPI_CONN_CLIENTIP			145
#define SLAPI_CONN_SERVERIP			146

/******* Deprecated (obsolete) functions *************************************/
/*
 * Please use the new functions in slapi-plugin.h instead of the ones
 * below that are marked SLAPI_DEPRECATED.
 */
#define SLAPI_DEPRECATED


/*
 *
 * The following functions that deal with bervals are deprecated
 * and their use is not recommended.  For each deprecated function, you
 * will find in slapi-plugin.h a corresponding function with a _sv
 * extension that uses Slapi_Values instead of bervals.  The new
 * functions are more efficient than the old ones, and some of the old
 * functions are much less efficient than they were previously.
 */
SLAPI_DEPRECATED int slapi_entry_attr_merge( Slapi_Entry *e, const char *type,
		struct berval **vals );
SLAPI_DEPRECATED int slapi_entry_add_values( Slapi_Entry *e, const char *type,
		struct berval **vals );
SLAPI_DEPRECATED int slapi_entry_delete_values( Slapi_Entry *e,
		const char *type, struct berval **vals );
SLAPI_DEPRECATED int slapi_entry_attr_replace( Slapi_Entry *e,
		const char *type, struct berval **vals );
SLAPI_DEPRECATED int slapi_attr_get_values( Slapi_Attr *attr,
		struct berval ***vals );
SLAPI_DEPRECATED int slapi_attr_get_oid( const Slapi_Attr *attr, char **oid );
SLAPI_DEPRECATED int slapi_filter_get_type( Slapi_Filter *f, char **type );
SLAPI_DEPRECATED int slapi_pw_find( struct berval **vals, struct berval *v );
SLAPI_DEPRECATED int slapi_call_syntax_values2keys( void *vpi,
		struct berval **vals, struct berval ***ivals, int ftype );
SLAPI_DEPRECATED int slapi_call_syntax_assertion2keys_ava( void *vpi,
		struct berval *val, struct berval ***ivals, int ftype );
SLAPI_DEPRECATED int slapi_call_syntax_assertion2keys_sub( void *vpi,
		char *initial, char **any, char *final, struct berval ***ivals );

/* slapi_entry_attr_hasvalue() deprecated in favour of slapi_entry_attr_has_syntax_value()
 * which does respect the syntax of type (former assumes a cis ascii string) */
SLAPI_DEPRECATED int slapi_entry_attr_hasvalue(const Slapi_Entry *e,
		const char *type, const char *value);

/*
 * The following "internal operation" calls are deprecated.  The new internal
 * operation functions that are defined in slapi-plugin.h take a Slapi_PBlock
 * for extensibility and support the new plugin configuration capabilities
 * as well.
 */
SLAPI_DEPRECATED int slapi_search_internal_callback( const char *ibase,
		int scope, const char *ifstr, char **attrs, int attrsonly,
		void *callback_data, LDAPControl **controls,
		plugin_result_callback prc, plugin_search_entry_callback psec,
		plugin_referral_entry_callback prec );
SLAPI_DEPRECATED int slapi_seq_callback( const char *ibase, int type,
		char *attrname, char *val, char **attrs, int attrsonly,
		void *callback_data, LDAPControl **controls,
		plugin_result_callback res_callback,
		plugin_search_entry_callback srch_callback,
		plugin_referral_entry_callback ref_callback);
SLAPI_DEPRECATED Slapi_PBlock *slapi_search_internal( const char *base,
		int scope, const char *filter, LDAPControl **controls, char **attrs,
		int attrsonly );
SLAPI_DEPRECATED Slapi_PBlock *slapi_modify_internal( const char *dn,
		LDAPMod **mods, LDAPControl **controls, int dummy );
SLAPI_DEPRECATED Slapi_PBlock *slapi_add_internal( const char * dn,
		LDAPMod **attrs, LDAPControl **controls, int dummy );
SLAPI_DEPRECATED Slapi_PBlock *slapi_add_entry_internal( Slapi_Entry *e,
		LDAPControl **controls, int dummy );
SLAPI_DEPRECATED Slapi_PBlock *slapi_delete_internal( const char * dn,
		LDAPControl **controls, int dummy );
SLAPI_DEPRECATED Slapi_PBlock *slapi_modrdn_internal( const char * olddn,
		const char * newrdn, int deloldrdn, LDAPControl **controls, int dummy);
SLAPI_DEPRECATED Slapi_PBlock *slapi_rename_internal( const char *iodn,
		const char *inewrdn, const char *inewsuperior, int deloldrdn,
		LDAPControl **controls, int dummy);

/*
 * These following three functions are not multi-thread (MT) safe (they return
 * a pointer to unprotected data).  Use the new functions from slapi-plugin.h
 * that end in _copy() instead, e.g., use slapi_get_supported_controls_copy()
 * instead of slapi_get_supported_controls().
 */
SLAPI_DEPRECATED int slapi_get_supported_controls( char ***ctrloidsp,
	unsigned long **ctrlopsp );
SLAPI_DEPRECATED char **slapi_get_supported_extended_ops( void );
SLAPI_DEPRECATED char **slapi_get_supported_saslmechanisms( void );


#ifdef __cplusplus
}
#endif

#endif /* _SLAPIPLUGINCOMPAT4 */