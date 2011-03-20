// $ANTLR : "tefkat.g" -> "TefkatParser.java"$

package tefkat.model.parser;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
        
/*
 * Copyright (c) 2003- michael lawley and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License version 2.1 as published by the Free Software Foundation
 * which accompanies this distribution, and is available by writing to
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contributors:
 *     michael lawley
 *
 */
        
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import antlr.debug.MessageEvent;
import antlr.debug.MessageListener;

import tefkat.data.*;
import tefkat.model.*;
import tefkat.model.internal.ModelUtils;

/**
 * Parser for an SQL-like QVT language.
 *
 * @author michael lawley
 * @date 1 April 2003
 */
public class TefkatParser extends antlr.LLkParser       implements TefkatLexerTokenTypes
 {

    // Provide a convenient "token" that captures all forms of comment
    // This is useful for passing to a filter
    // e.g., filter.hide(TefkatParser.COMMENT)
    public static final BitSet COMMENT = new BitSet();
    static {
            COMMENT.add(SL_COMMENT);
            COMMENT.add(ML_COMMENT);
    }
    
    private static final List EMPTY_PARAMS = new ArrayList();
    
    /**
     * Stores a map of names to Tracking instances.
     */
    public Map trackingMap;
    {
            trackingMap = new HashMap();
            // Initialise the set of known types (for trackings) to include various built-in EDataTypes
            trackingMap.put("boolean", EcorePackage.eINSTANCE.getEBoolean());
            trackingMap.put("string", EcorePackage.eINSTANCE.getEString());
            trackingMap.put("int", EcorePackage.eINSTANCE.getEInt());
            trackingMap.put("long", EcorePackage.eINSTANCE.getELong());
            trackingMap.put("float", EcorePackage.eINSTANCE.getEFloat());
            trackingMap.put("double", EcorePackage.eINSTANCE.getEDouble());
            trackingMap.put("any", EcorePackage.eINSTANCE.getEJavaObject());
            trackingMap.put("object", EcorePackage.eINSTANCE.getEObject());
            trackingMap.put("::boolean", EcorePackage.eINSTANCE.getEBoolean());
            trackingMap.put("::string", EcorePackage.eINSTANCE.getEString());
            trackingMap.put("::int", EcorePackage.eINSTANCE.getEInt());
            trackingMap.put("::long", EcorePackage.eINSTANCE.getELong());
            trackingMap.put("::float", EcorePackage.eINSTANCE.getEFloat());
            trackingMap.put("::double", EcorePackage.eINSTANCE.getEDouble());
            trackingMap.put("::any", EcorePackage.eINSTANCE.getEJavaObject());
            trackingMap.put("::object", EcorePackage.eINSTANCE.getEObject());
    }

    /**
     * Stores a map of names to PatternDefn instances.
     */
    private Map patMap = new HashMap();

    /**
     * Stores a map of PatternUse instances to PatternDefn names
     * for resolving after the complete parse.
     */
    private Map patUseMap = new HashMap();

    /**
     * Stores a map of names to TRule instances.
     */
    private Map ruleMap = new HashMap();

    /**
     * Stores a map of (rule) names to a List of TRule instances representing
     * the set of TRules that extend the rule with the given name.
     */
    private Map extendsMap = new HashMap();

    /**
     * Stores a map of (rule) names to a List Var instances representing
     * those explicitly exposed for extending/superseding.
     */
    private Map publicVarMap = new HashMap();

    /**
     * Stores a map of (rule) names to a List of Lists of Var instances
     * representing the set of Vars that extend the Vars of the rule
     * with the given name.
     */
    private Map extendsVarMap = new HashMap();

    /**
     * Stores a map of (rule) names to a List of TRule instances representing
     * the set of TRules that supersede the rule with the given name.
     */
    private Map supersedesMap = new HashMap();

    /**
     * Stores a map of (rule) names to a List of Lists of Var instances
     * representing the set of Vars that supersede the Vars of the rule
     * with the given name.
     */
    private Map supersedesVarMap = new HashMap();
    
    /**
     * Flag to indicate whether or not to preserve comments.
     * Defaults to false.
     */
    private boolean preserveComments = false;
    
    /**
     * A package to put any class decls in
     */
    private EPackage ePackage = null;
    
    /**
     *
     */
    public void setPreserveComments(boolean preserve) {
        preserveComments = preserve;
    }
    
    /**
     * Updates the supplied map to include trule in the List of items
     * associated with name.
     */
    private void store(Map map, String name, TRule trule) {
        List l = (List) map.get(name);
        if (l == null) {
            l = new ArrayList();
            map.put(name, l);
        }
        l.add(trule);
    }
    
    /**
     * Updates the supplied map to include vars in the List of items
     * associated with name.
     */
    private void store(Map map, String name, List vars) {
        List l = (List) map.get(name);
        if (l == null) {
            l = new ArrayList();
            map.put(name, l);
        }
        l.add(vars);
    }
    
    private void definePackage(PatternScope t, String namespace, String uriString) throws antlr.SemanticException {
        final List packages = new ArrayList(1);
        final ResourceSet rs = t.eResource().getResourceSet();
        final EPackage.Registry registry = rs.getPackageRegistry();
        final EPackage pkg = registry.getEPackage(uriString);
        if (null != pkg) {
            packages.add(pkg);
        } else {
        	final Resource res;
        	try {
                res = rs.getResource(URI.createURI(uriString), true);
        	} catch (Exception e) {
	            throw new antlr.SemanticException("Unable to load model from URI: " + uriString, getFilename(), getMarkLine(), getMarkColumn());
        	}
        	if (null == res) {
	            throw new antlr.SemanticException("Unable to load model from URI: " + uriString, getFilename(), getMarkLine(), getMarkColumn());
        	}
        	
        	if (false) {	// Strict checking
        	    boolean found = false;
        	
        	    for (Object o: res.getContents()) {
        		    if (o instanceof EPackage) {
        			    EPackage p = (EPackage) o;
        			    if (uriString.equals(p.getNsURI())) {
                            packages.add(p);
//        				    registry.put(uriString, p);
        				    found = true;
        				    break;
        			    }
        		    }
        	    }
        	    if (!found) {
	                throw new antlr.SemanticException("Unable to find EPackage with NsURI: " + uriString, getFilename(), getMarkLine(), getMarkColumn());
        	    }
        	} else {
        	    for (Object o: res.getContents()) {
        		    if (o instanceof EPackage) {
        			    EPackage p = (EPackage) o;
                        packages.add(p);
        		    }
        	    }
        	}
        }
        ModelUtils.buildPackageNameMaps(packages, trackingMap, namespace);
    }
    
    /**
     * Return the named Var in VarScope scope or null if it's not found.
     */
    private Var getVarInScope(VarScope scope, String name) {
        Var var;
        if (null == name || name.charAt(0) == '_') {
            return null;
        }
        List vars = scope.getVars();
        for (Iterator itr = vars.iterator(); itr.hasNext(); ) {
            var = (Var) itr.next();
            if (name.equals(var.getName())) {
                singletonVars.remove(var);
                return var;
            }
        }
        if (scope instanceof TRule) {
            return getVarInScope(((TRule) scope).getTransformation(), name);
        } else if (scope instanceof PatternDefn) {
            return getVarInScope(((PatternDefn) scope).getPatternScope(), name);
        }
        return null;
    }
    
    /**
     * Add a new Var called name to the VarScope scope and
     * return it.
     */
    private Map singletonVars = new HashMap();
    private Var declareVar(VarScope scope, String name, int line, int column) throws antlr.SemanticException {
        Var var = TefkatFactory.eINSTANCE.createVar();
        
        if (null != name) {
            if (name.charAt(0) == '_') {
                List vars = scope.getVars();
                for (final Iterator itr = vars.iterator(); itr.hasNext(); ) {
                    Var v = (Var) itr.next();
                    if (name.equals(v.getName())) {
                        reportWarning("Anonynous variable name, " + name + ", occurs multiple times in " + scope, line, column);
                    }
                }
            } else {
                int[] location = {line, column};
                singletonVars.put(var, location);
            }
        }
        
        var.setName(name);
        var.setScope(scope);

        return var;
    }
    
    private Map mapMap = new HashMap();
    
    private void annotate(VarScope obj, Token tok) {
        if (preserveComments) {
            antlr.CommonHiddenStreamToken t = (antlr.CommonHiddenStreamToken) tok;
            t = t.getHiddenBefore();
            if (t != null) {
                List comments = obj.getComments();
                while (null != t) {
                    comments.add(0, t.getText());
                    t = t.getHiddenBefore();
                }
            }
        }
    }
    
    private Var getVar(TRule trule, String varName)
    throws antlr.SemanticException {
        List vars = trule.getVars();
        for (Iterator itr = vars.iterator(); itr.hasNext(); ) {
            Var var = (Var) itr.next();
            if (varName.equals(var.getName())) {
                return var;
            }
        }
        throw new antlr.SemanticException("Couldn't resolve var name: " + varName + " in rule " + trule.getName());
    }

    /**
     * For each PatternUse, resolve the PatternDefn name
     */
    private void resolvePatternReferences(PatternScope scope) throws SemanticException {
        List defns = scope.getPatternDefn();
        for (final Iterator itr = defns.iterator(); itr.hasNext(); ) {
            PatternDefn def = (PatternDefn) itr.next();
            patMap.put(def.getName(), def);
        }
    	
        for (Iterator itr = patUseMap.keySet().iterator(); itr.hasNext(); ) {
            PatternUse pu = (PatternUse) itr.next();
            String pname = (String) patUseMap.get(pu);
            PatternDefn pd = (PatternDefn) patMap.get(pname);
            if (null == pd) {
                if (!pname.startsWith("println/")) {
                    EObject container = pu.eContainer();
                    while (!(null == container || container instanceof VarScope)) {
                        container = container.eContainer();
                    }
                    reportError("Use of undefined pattern: " + pname + " in " + container, -1, -1);
                    throw new antlr.SemanticException("Reference to unknown pattern: " + pname + " in " + container, getFilename(), -1, -1);
                }
            } else {
                pu.setDefn(pd);
            }
        }
    }
    
    // Error/Warning support infrastructure
    
    List messageListeners = new ArrayList();
    List parserListeners = new ArrayList();
    
    public void addMessageListener(MessageListener l) {
            messageListeners.add(l);
    }
    
    public void removeMessageListener(MessageListener l) {
            messageListeners.remove(l);
    }
    
    public void addParserListener(ParserListener l) {
            parserListeners.add(l);
    }
    
    public void removeParserListener(ParserListener l) {
            parserListeners.remove(l);
    }
    
    protected void fireReportError(MessageEvent e) {
            for (Iterator itr = messageListeners.iterator(); itr.hasNext(); ) {
                ((MessageListener) itr.next()).reportError(e);
            }
    }
    
    protected void fireReportWarning(MessageEvent e) {
            for (Iterator itr = messageListeners.iterator(); itr.hasNext(); ) {
                ((MessageListener) itr.next()).reportWarning(e);
            }
    }
    
    protected void fireMatched(ParserEvent e) {
            for (Iterator itr = parserListeners.iterator(); itr.hasNext(); ) {
                ((ParserListener) itr.next()).matched(e);
            }
    }

    public void reportError(String str) {
    	System.err.println("Deprecated reportError(String) called");
    	reportError(str, -1, -1); //getCharIndex(), getNextCharIndex());
    }

    public void reportError(String str, int startChar, int endChar) {
            MessageEvent event = new TefkatMessageEvent(this, MessageEvent.ERROR, str, getMarkLine(), getMarkColumn(), startChar, endChar);
            fireReportError(event);
    }
    
    public void reportWarning(String str) {
            MessageEvent event = new TefkatMessageEvent(this, MessageEvent.WARNING, str, getMarkLine(), getMarkColumn());
            fireReportWarning(event);
    }
    
    public void reportWarning(String str, int line, int col) {
            MessageEvent event = new TefkatMessageEvent(this, MessageEvent.WARNING, str, line, col);
            fireReportWarning(event);
    }
    
    public void reportMatch(EObject obj, int startChar, int endChar) {
            ParserEvent event = new ParserEvent(obj, startChar, endChar);
            fireMatched(event);
    }
    
    public void reportError(RecognitionException e) {
        System.err.println("Deprecated reportError(RecognitionException) called");
        e.printStackTrace();
//        new Exception().printStackTrace();
        int endChar = -1;
        try {
        	endChar = getStartChar();
        } catch (TokenStreamException tse) {
        	// ignore
        }
        reportError(e, startChar, endChar);
    }
    
    public void reportError(RecognitionException e, int startChar, int endChar) {
        MessageEvent event = new TefkatMessageEvent(this, MessageEvent.ERROR, getMessage(e), e.getLine(), e.getColumn(), startChar, endChar);
        fireReportError(event);
    }
    
    public void reportWarning(RecognitionException e) {
            MessageEvent event = new TefkatMessageEvent(this, MessageEvent.ERROR, getMessage(e), e.getLine(), e.getColumn());
            fireReportWarning(event);
    }
    
    private String getMessage(Throwable t) {
            String msg = t.getMessage();
            for (t = t.getCause(); t != null; t = t.getCause()) {
            msg = msg.concat(":> ").concat(t.getMessage());
        }
        return msg;
    }
    
    int line = -1;
    int col = -1;
    int startChar = -1;
    private void setMark(Token tok) {
            line = tok.getLine();
            col = tok.getColumn();
            startChar = getStartChar(tok);
    }
    private int getMarkLine() {
            return line;
    }
    private int getMarkColumn() {
            return col;
    }

/*    
    private int getCharIndex() throws TokenStreamException {
        int i = 0;
        Token tok = LT(i);
        while (WS == tok.getType() && tok.getType() >= Token.MIN_USER_TYPE) {
            i++;
            tok = LT(i);
        }
        return getStartChar(tok);
    }
    
    private int getNextCharIndex() throws TokenStreamException {
        int i = 1;
        Token tok = LT(i);
        while (WS == tok.getType() && tok.getType() >= Token.MIN_USER_TYPE) {
            i++;
            tok = LT(i);
        }
        return getStartChar(tok);
    }
*/
    
    private int getStartChar() throws TokenStreamException {
    	return getStartChar(LT(0));
    }
    
    private int getEndChar() throws TokenStreamException {
    	return getEndChar(LT(0));
    }
    
    private int getStartChar(Token tok) {
        if (tok instanceof TefkatToken) {
            return ((TefkatToken) tok).getOffset() - tok.getText().length();
        } else {
            return -1;
        }
    }
    
    private int getEndChar(Token tok) {
        if (tok instanceof TefkatToken) {
            return ((TefkatToken) tok).getOffset();
        } else {
            return -1;
        }
    }
    
    private Resource resource = null;
    private List srcExtents, tgtExtents;
    
    public void setResource(Resource resource) {
        this.resource = resource;
    }

protected TefkatParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TefkatParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected TefkatParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TefkatParser(TokenStream lexer) {
  this(lexer,1);
}

public TefkatParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final Query  query() throws RecognitionException, TokenStreamException {
		Query q = null;;
		
		Token  tok = null;
		
		Var srcExtent = null;
		SourceTerm stmt = null;
		
		
		tok = LT(1);
		match(LITERAL_QUERY);
		if ( inputState.guessing==0 ) {
			
						q = TefkatFactory.eINSTANCE.createQuery();
			
			resource.getContents().add(q);
						annotate(q, tok);
			
		}
		{
		_loop86:
		do {
			switch ( LA(1)) {
			case LITERAL_NAMESPACE:
			{
				namespaceDecl(q);
				break;
			}
			case LITERAL_PATTERN:
			{
				patternDefn(q, srcExtent);
				break;
			}
			default:
			{
				break _loop86;
			}
			}
		} while (true);
		}
		stmt=queryTerm(q, srcExtent);
		if ( inputState.guessing==0 ) {
			
				q.setTerm(stmt);
			
		}
		return q;
	}
	
	public final void namespaceDecl(
		PatternScope t
	) throws RecognitionException, TokenStreamException {
		
		Token  id = null;
		Token  uri = null;
		
		String name = null;
		
		
		match(LITERAL_NAMESPACE);
		{
		switch ( LA(1)) {
		case ID:
		{
			id = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				name = id.getText();
			}
			break;
		}
		case URITOK:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		uri = LT(1);
		match(URITOK);
		if ( inputState.guessing==0 ) {
			
				        setMark(uri);
			
			String uriStr = uri.getText();
			NamespaceDeclaration nsd = TefkatFactory.eINSTANCE.createNamespaceDeclaration();
			nsd.setPrefix(name);
			nsd.setURI(uri.getText());
			t.getNamespaceDeclarations().add(nsd);
			
			definePackage(t, name, uriStr);
			
		}
	}
	
	public final void patternDefn(
		PatternScope t, Var srcExtent
	) throws RecognitionException, TokenStreamException {
		
		Token  pat = null;
		Token  forall = null;
		Token  where = null;
		Token  semi = null;
		
		PatternDefn pd = null;
		String name;
		String fullName = null;
		AndTerm conjunct = null;
		int sChar = -1, eChar = -1;
		int sConjChar = -1;
		
		
		pat = LT(1);
		match(LITERAL_PATTERN);
		if ( inputState.guessing==0 ) {
			
			sChar = getStartChar(pat);
			pd = TefkatFactory.eINSTANCE.createPatternDefn();
			annotate(pd, pat);
			pd.setSource(true);
			pd.setPatternScope(t);
			conjunct = TefkatFactory.eINSTANCE.createAndTerm();
			// conjunct.setContext(srcExtent);
			pd.setTerm(conjunct);
			
		}
		name=pname();
		formals(pd);
		if ( inputState.guessing==0 ) {
			
			// mark all Vars from the formals as parameter vars
			pd.getParameterVar().addAll(pd.getVars());
			fullName = name + "/" + pd.getParameterVar().size();
			pd.setName(fullName);
			
		}
		{
		switch ( LA(1)) {
		case LITERAL_FORALL:
		{
			forall = LT(1);
			match(LITERAL_FORALL);
			ranges(pd, srcExtent, conjunct.getTerm(), false);
			if ( inputState.guessing==0 ) {
				
				sConjChar = getStartChar(forall);
				
			}
			break;
		}
		case SEMI:
		case LITERAL_WHERE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_WHERE:
		{
			where = LT(1);
			match(LITERAL_WHERE);
			conjunct(pd, conjunct.getTerm());
			if ( inputState.guessing==0 ) {
				
				if (-1 == sConjChar) {
				sConjChar = getStartChar(where);
				}
				
			}
			break;
		}
		case SEMI:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		semi = LT(1);
		match(SEMI);
		if ( inputState.guessing==0 ) {
			
			eChar = getEndChar(semi);
			reportMatch(conjunct, sConjChar, eChar);
			reportMatch(pd, sChar, eChar);
			
			if (patMap.containsKey(fullName)) {
			throw new antlr.SemanticException("Duplicate definition of pattern '" + fullName + "' found.", getFilename(), getMarkLine(), getMarkColumn());
			}
			patMap.put(fullName, pd);
			
		}
	}
	
	public final SourceTerm  queryTerm(
		Query q, Var srcExtent
	) throws RecognitionException, TokenStreamException {
		SourceTerm result = null;
		
		
			AndTerm container = TefkatFactory.eINSTANCE.createAndTerm();
			List terms = container.getTerm();
			List params = null;
		
		
		{
		switch ( LA(1)) {
		case ID:
		{
			params=vardecls(q);
			if ( inputState.guessing==0 ) {
				
					q.getParameterVar().addAll(params);
					
			}
			break;
		}
		case COLON:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(COLON);
		conjunct(q, terms);
		if ( inputState.guessing==0 ) {
			
				resolvePatternReferences(q);
				if (1 == terms.size()) {
					result = (SourceTerm) terms.remove(0);
				} else {
					result = container;
				}
				result.setContext(srcExtent);
			
		}
		match(SEMI);
		return result;
	}
	
	public final SourceTerm  queryStatement(
		Query q, Var srcExtent
	) throws RecognitionException, TokenStreamException {
		SourceTerm result = null;
		
		
		{
		switch ( LA(1)) {
		case LITERAL_NAMESPACE:
		{
			namespaceDecl(q);
			if ( inputState.guessing==0 ) {
				
					System.out.println("Loaded namespace");
				
			}
			break;
		}
		case LITERAL_PATTERN:
		{
			patternDefn(q, srcExtent);
			break;
		}
		case ID:
		case COLON:
		{
			result=queryTerm(q, srcExtent);
			break;
		}
		case EOF:
		{
			match(Token.EOF_TYPE);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final List  vardecls(
		VarScope vs
	) throws RecognitionException, TokenStreamException {
		List vars = new ArrayList();
		
		
		Var var;
		
		
		var=vardecl(vs);
		if ( inputState.guessing==0 ) {
			
			vars.add(var);
			
		}
		{
		_loop256:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				var=vardecl(vs);
				if ( inputState.guessing==0 ) {
					
					vars.add(var);
					
				}
			}
			else {
				break _loop256;
			}
			
		} while (true);
		}
		return vars;
	}
	
	public final void conjunct(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		
		
		Term term;
		
		
		try { // for error handling
			term=disjunct(scope, terms);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				term = null;
				
			} else {
				throw ex;
			}
		}
		if ( inputState.guessing==0 ) {
			
			if (null != term) {
			terms.add(term);
			}
			
		}
		{
		_loop162:
		do {
			if ((LA(1)==LITERAL_AND)) {
				match(LITERAL_AND);
				try { // for error handling
					term=disjunct(scope, terms);
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						
						reportError(ex);
						term = null;
						
					} else {
						throw ex;
					}
				}
				if ( inputState.guessing==0 ) {
					
					if (null != term) {
					terms.add(term);
					}
					
				}
			}
			else {
				break _loop162;
			}
			
		} while (true);
		}
	}
	
	public final Transformation  transformation() throws RecognitionException, TokenStreamException {
		Transformation t = null;;
		
		Token  tok = null;
		Token  name = null;
		Token  colon = null;
		
		Var srcExtent = null, tgtExtent = null;
		int sChar = -1, eChar = -1;
		
		
		try {      // for error handling
			tok = LT(1);
			match(LITERAL_TRANSFORMATION);
			if ( inputState.guessing==0 ) {
				
				t = TefkatFactory.eINSTANCE.createTransformation();
				//                    org.eclipse.emf.ecore.xml.type.AnyType any = 
				//                            XMLTypeFactory.eINSTANCE.createAnyType();
				//                    resource.getEObjectToExtensionMap().put(t, any);
				annotate(t, tok);
				
			}
			name = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
				t.setName(name.getText());
				resource.getContents().add(t);
				
				final String pkgURI = String.valueOf(resource.getURI());
				ePackage = EcoreFactory.eINSTANCE.createEPackage();
				ePackage.setNsURI(pkgURI);
				ePackage.setName(t.getName());
				resource.getContents().add(0, ePackage);
				final EPackage.Registry registry = resource.getResourceSet().getPackageRegistry();
				registry.put(pkgURI, ePackage);
				sChar = getStartChar();
				
			}
			{
			switch ( LA(1)) {
			case LBRACK:
			{
				formals(t);
				if ( inputState.guessing==0 ) {
					
					List vars = t.getVars();
					if (vars.size() != 2) {
					throw new antlr.SemanticException("Bracket syntax requires exactly one source extent and one target extent.  Use colon syntax for multiple source or target extents", getFilename(), getMarkLine(), getMarkColumn());
					}
					srcExtent = (Var) vars.get(0);
					tgtExtent = (Var) vars.get(1);
					srcExtents = Collections.singletonList(srcExtent);
					tgtExtents = Collections.singletonList(tgtExtent);
					reportWarning("bracket syntax is deprecated", getMarkLine(), getMarkColumn());
					
				}
				break;
			}
			case COLON:
			{
				colon = LT(1);
				match(COLON);
				if ( inputState.guessing==0 ) {
					
					sChar = getEndChar(colon);
					
				}
				srcExtents=vardecls(t);
				if ( inputState.guessing==0 ) {
					
					srcExtent = (Var) srcExtents.get(0);
					
				}
				match(ARROW);
				tgtExtents=vardecls(t);
				if ( inputState.guessing==0 ) {
					
					tgtExtent = (Var) tgtExtents.get(0);
					
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LITERAL_EXTENDS:
			{
				match(LITERAL_EXTENDS);
				transformationExtends(t);
				break;
			}
			case EOF:
			case LITERAL_CLASS:
			case LITERAL_MAP:
			case LITERAL_IMPORT:
			case LITERAL_NAMESPACE:
			case LITERAL_PATTERN:
			case LITERAL_TEMPLATE:
			case LITERAL_ABSTRACT:
			case LITERAL_RULE:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
					eChar = getStartChar();
				
			}
			{
			_loop95:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					body(t, srcExtent, tgtExtent);
					if ( inputState.guessing==0 ) {
						
						if (!singletonVars.isEmpty()) {
						for (final Iterator itr = singletonVars.entrySet().iterator(); itr.hasNext(); ) {
						Map.Entry entry = (Map.Entry) itr.next();
						Var svar = (Var) entry.getKey();
						int[] location = (int[]) entry.getValue();
						reportWarning("non-anonynous variable, " + svar + ", is only referenced once in " + svar.getScope(), location[0], location[1]);
						}
						}
						singletonVars.clear();
						
					}
				}
				else {
					break _loop95;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
				// Resolve the extends associations
				for (Iterator itr = extendsMap.entrySet().iterator(); itr.hasNext(); ) {
				Map.Entry entry = (Map.Entry) itr.next();
				String rname = (String) entry.getKey();
				TRule trule = (TRule) ruleMap.get(rname);
				if (null == trule) {
				throw new antlr.SemanticException("Extends reference to unknown rule: " + rname, getFilename(), -1, -1);
				}
				List rules = (List) entry.getValue();
				for (Iterator ruleItr = rules.iterator(); ruleItr.hasNext(); ) {
				TRule trule2 =(TRule) ruleItr.next();
				trule2.getExtended().add(trule);
				}
				}
				// Resolve the supersedes associations
				for (Iterator itr = supersedesMap.entrySet().iterator(); itr.hasNext(); ) {
				Map.Entry entry = (Map.Entry) itr.next();
				String rname = (String) entry.getKey();
				TRule trule = (TRule) ruleMap.get(rname);
				if (null == trule) {
				throw new antlr.SemanticException("Overrides reference to unknown rule: " + rname, getFilename(), -1, -1);
				}
				List rules = (List) entry.getValue();
				for (Iterator ruleItr = rules.iterator(); ruleItr.hasNext(); ) {
				TRule trule2 =(TRule) ruleItr.next();
				trule2.getSuperseded().add(trule);
				}
				}
				for (Iterator itr = ruleMap.keySet().iterator(); itr.hasNext(); ) {
				String rname = (String) itr.next();
				TRule trule = (TRule) ruleMap.get(rname);
				
				List vars = (List) publicVarMap.get(trule);
				// Handle var extends
				List xvlistlist = (List) extendsVarMap.get(rname);
				if (xvlistlist != null) {
				for (Iterator xvItr = xvlistlist.iterator(); xvItr.hasNext(); ) {
				List xvList = (List) xvItr.next();
				if (vars.size() != xvList.size()) {
				reportError("Size mismatch: " + vars + " and " + xvList, sChar, eChar);
				} else {
				for (int i = 0; i < vars.size(); i++) {
				Var v = (Var) vars.get(i);
				Var xv = (Var) xvList.get(i);
				v.getExtender().add(xv);
				}
				}
				}
				}
				// Handle var supersedes
				List svlistlist = (List) supersedesVarMap.get(rname);
				if (svlistlist != null) {
				for (Iterator svItr = svlistlist.iterator(); svItr.hasNext(); ) {
				List svList = (List) svItr.next();
				if (vars.size() != svList.size()) {
				reportError("Size mismatch: " + vars + " and " + svList, sChar, eChar);
				} else {
				for (int i = 0; i < vars.size(); i++) {
				Var v = (Var) vars.get(i);
				Var sv = (Var) svList.get(i);
				v.getSuperseder().add(sv);
				}
				}
				}
				}
				}
				
				resolvePatternReferences(t);
				
			}
			match(Token.EOF_TYPE);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				
			} else {
				throw ex;
			}
		}
		return t;
	}
	
	public final void formals(
		VarScope vs
	) throws RecognitionException, TokenStreamException {
		
		
		match(LBRACK);
		{
		switch ( LA(1)) {
		case ID:
		{
			vardecls(vs);
			break;
		}
		case RBRACK:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RBRACK);
	}
	
	public final void transformationExtends(
		Transformation t
	) throws RecognitionException, TokenStreamException {
		
		Token  uri = null;
		
		List vars = null;
		
		
		uri = LT(1);
		match(URITOK);
		match(LBRACK);
		vars=vardecls(t);
		match(RBRACK);
		if ( inputState.guessing==0 ) {
			
			
		}
		{
		switch ( LA(1)) {
		case COMMA:
		{
			match(COMMA);
			transformationExtends(t);
			break;
		}
		case EOF:
		case LITERAL_CLASS:
		case LITERAL_MAP:
		case LITERAL_IMPORT:
		case LITERAL_NAMESPACE:
		case LITERAL_PATTERN:
		case LITERAL_TEMPLATE:
		case LITERAL_ABSTRACT:
		case LITERAL_RULE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final void body(
		Transformation t, Var srcExtent, Var tgtExtent
	) throws RecognitionException, TokenStreamException {
		
		
		int sChar = -1, eChar = -1;
		
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					sChar = getStartChar();
				
			}
			{
			switch ( LA(1)) {
			case LITERAL_IMPORT:
			{
				importDecl(t);
				break;
			}
			case LITERAL_NAMESPACE:
			{
				namespaceDecl(t);
				break;
			}
			case LITERAL_CLASS:
			{
				classDecl(t);
				break;
			}
			case LITERAL_MAP:
			{
				map();
				break;
			}
			case LITERAL_PATTERN:
			{
				patternDefn(t, srcExtent);
				break;
			}
			case LITERAL_TEMPLATE:
			{
				templateDefn(t, tgtExtent);
				break;
			}
			case LITERAL_ABSTRACT:
			case LITERAL_RULE:
			{
				trule(t, srcExtent, tgtExtent);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				//            reportError(ex, sChar, getEndChar());
				reportError(ex, getStartChar(LT(1)), getEndChar(LT(1)));
				// Skip forward to next major construct to continue parsing
				int tok = LA(1);
				System.err.println(LT(1));
				while (tok != LITERAL_IMPORT &&
				tok != LITERAL_NAMESPACE &&
				tok != LITERAL_CLASS &&
				tok != LITERAL_MAP &&
				tok != LITERAL_PATTERN &&
				tok != LITERAL_TEMPLATE &&
				tok != LITERAL_ABSTRACT &&
				tok != LITERAL_RULE &&
				tok != EOF) {
				consume();
				tok = LA(1);
				System.err.println(LT(1));
				}
				
			} else {
				throw ex;
			}
		}
	}
	
	public final void importDecl(
		Transformation t
	) throws RecognitionException, TokenStreamException {
		
		Token  uri = null;
		
		match(LITERAL_IMPORT);
		uri = LT(1);
		match(URITOK);
		if ( inputState.guessing==0 ) {
			
				        setMark(uri);
				        
			String uriStr = uri.getText();
			NamespaceDeclaration nsd = TefkatFactory.eINSTANCE.createNamespaceDeclaration();
			nsd.setURI(uri.getText());
			t.getNamespaceDeclarations().add(nsd);
			
			definePackage(t, null, uriStr);
			reportWarning("IMPORT is deprecated, use NAMESPACE instead", getMarkLine(), getMarkColumn());
			
		}
	}
	
	public final void classDecl(
		Transformation t
	) throws RecognitionException, TokenStreamException {
		
		Token  c = null;
		Token  id = null;
		Token  ref = null;
		Token  fsemi = null;
		Token  semi = null;
		
		final Resource res = t.eResource();
		EClass eClass = null;
		EClassifier type = null;
		boolean multiValued = false;
		int csChar = -1, ceChar = -1;
		int fsChar = -1, feChar = -1;
		
		
		c = LT(1);
		match(LITERAL_CLASS);
		id = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			csChar = getStartChar(c);
			final String name = id.getText();
			if (trackingMap.containsKey(name)) {
				// re-use existing class so that we can do forward declarations
				// TODO consider requiring that eClass be empty at this point to avoid
				// inadvertant re-use
				//
				eClass = (EClass) trackingMap.get(name);
			if (eClass.getEStructuralFeatures().size() > 0) {
			throw new antlr.SemanticException("Attempting to re-define existing class: " + name, getFilename(), getMarkLine(), getMarkColumn());
			}
			} else {
			eClass = EcoreFactory.eINSTANCE.createEClass();
			eClass.setName(name);
			ePackage.getEClassifiers().add(eClass);
			trackingMap.put(name, eClass);
			}
			
		}
		{
		switch ( LA(1)) {
		case LITERAL_EXTENDS:
		{
			match(LITERAL_EXTENDS);
			superClasses(eClass);
			break;
		}
		case LBRACE:
		case SEMI:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LBRACE:
		{
			match(LBRACE);
			{
			_loop107:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					if ( inputState.guessing==0 ) {
						fsChar = getStartChar(); multiValued = false;
					}
					type=simpleTypeLiteral(true);
					{
					switch ( LA(1)) {
					case LBRACE:
					{
						match(LBRACE);
						match(RBRACE);
						if ( inputState.guessing==0 ) {
							multiValued = true;
						}
						break;
					}
					case ID:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					ref = LT(1);
					match(ID);
					fsemi = LT(1);
					match(SEMI);
					if ( inputState.guessing==0 ) {
						
						// TODO syntax for containment references
						// Note that string -> EString, int -> EInt, etc
						EStructuralFeature eFeature;
						if (type instanceof EClass) {
						eFeature = EcoreFactory.eINSTANCE.createEReference();
						} else {
						eFeature = EcoreFactory.eINSTANCE.createEAttribute();
						}
						eFeature.setName(ref.getText());
						eFeature.setEType(type);
						if (multiValued) {
						eFeature.setUpperBound(-1);
						eFeature.setUnique(false);
						}
						eClass.getEStructuralFeatures().add(eFeature);
						
						feChar = getEndChar(ref);
						reportMatch(eFeature, fsChar, feChar);
						
					}
				}
				else {
					break _loop107;
				}
				
			} while (true);
			}
			match(RBRACE);
			break;
		}
		case SEMI:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		semi = LT(1);
		match(SEMI);
		if ( inputState.guessing==0 ) {
			
			ceChar = getEndChar(semi);
			reportMatch(eClass, csChar, ceChar);
			
		}
	}
	
	public final void map() throws RecognitionException, TokenStreamException {
		
		Token  map = null;
		Token  id = null;
		Token  semi = null;
		
		DataMap dataMap = null;
		int sChar = -1, eChar = -1;
		
		
		map = LT(1);
		match(LITERAL_MAP);
		id = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			sChar = getStartChar(map);
			String mapName = id.getText();
			
			if (mapMap.containsKey(mapName)) {
			throw new antlr.SemanticException("Duplicate MAP name: " + mapName, getFilename(), getMarkLine(), getMarkColumn());
			}
			
			dataMap = DataFactory.eINSTANCE.createDataMap();
			dataMap.setKey(mapName);
			resource.getContents().add(dataMap);
			
			mapMap.put(mapName, dataMap);
			
		}
		match(LBRACE);
		{
		switch ( LA(1)) {
		case STRING:
		case INT:
		case REAL:
		case LANGLE:
		case LSQUARE:
		case ADDOP:
		case LITERAL_true:
		case LITERAL_false:
		{
			map_pairs(dataMap.getValue());
			if ( inputState.guessing==0 ) {
				
				
			}
			break;
		}
		case RBRACE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RBRACE);
		semi = LT(1);
		match(SEMI);
		if ( inputState.guessing==0 ) {
			
			eChar = getEndChar(semi);
			reportMatch(dataMap, sChar, eChar);
			
		}
	}
	
	public final void templateDefn(
		Transformation t, Var tgtExtent
	) throws RecognitionException, TokenStreamException {
		
		Token  pat = null;
		Token  semi = null;
		
		PatternDefn pd = null;
		String name;
		AndTerm conjunct = null;
		int sChar = -1, eChar = -1;
		int sConjChar = -1;
		
		
		pat = LT(1);
		match(LITERAL_TEMPLATE);
		if ( inputState.guessing==0 ) {
			
			sChar = getStartChar(pat);
			pd = TefkatFactory.eINSTANCE.createPatternDefn();
			annotate(pd, pat);
			pd.setSource(false);
			pd.setPatternScope(t);
			conjunct = TefkatFactory.eINSTANCE.createAndTerm();
			// conjunct.setContext(tgtExtent);
			pd.setTerm(conjunct);
			
		}
		name=pname();
		if ( inputState.guessing==0 ) {
			
			pd.setName(name);
			
		}
		formals(pd);
		if ( inputState.guessing==0 ) {
			
			// mark all Vars from the formals as parameter vars
			pd.getParameterVar().addAll(pd.getVars());
			String fullName = name + "/" + pd.getParameterVar().size();
			pd.setName(fullName);
			if (patMap.containsKey(fullName)) {
			throw new antlr.SemanticException("Duplicate definition of pattern '" + fullName + "' found.", getFilename(), getMarkLine(), getMarkColumn());
			}
			patMap.put(fullName, pd);
			
		}
		if ( inputState.guessing==0 ) {
			sConjChar = getStartChar();
		}
		targetClauses(pd, tgtExtent, conjunct.getTerm(), Collections.EMPTY_LIST);
		semi = LT(1);
		match(SEMI);
		if ( inputState.guessing==0 ) {
			
			eChar = getEndChar(semi);
			reportMatch(conjunct, sConjChar, eChar);
			reportMatch(pd, sChar, eChar);
			
		}
	}
	
	public final void trule(
		Transformation t, Var srcExtent, Var tgtExtent
	) throws RecognitionException, TokenStreamException {
		
		Token  abs = null;
		Token  tok = null;
		Token  forall = null;
		Token  where = null;
		Token  semi = null;
		
		TRule trule = null;
		String name;
		List params = null;
		AndTerm conjunct = null;
		boolean isAbstract = false;
		int sChar = -1, eChar = -1;
		int sConjChar = -1, eConjChar = -1;
		
		
		{
		switch ( LA(1)) {
		case LITERAL_ABSTRACT:
		{
			abs = LT(1);
			match(LITERAL_ABSTRACT);
			if ( inputState.guessing==0 ) {
				isAbstract = true; sChar = getStartChar(abs);
			}
			break;
		}
		case LITERAL_RULE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		tok = LT(1);
		match(LITERAL_RULE);
		if ( inputState.guessing==0 ) {
			
			if (-1 == sChar) {
			sChar = getStartChar(tok);
			}
			trule = TefkatFactory.eINSTANCE.createTRule();
			annotate(trule, tok);
			trule.setTransformation(t);
			conjunct = TefkatFactory.eINSTANCE.createAndTerm();
			conjunct.setContext(srcExtent);
			trule.setSrc(conjunct);
			trule.setAbstract(isAbstract);
			
		}
		name=rname();
		if ( inputState.guessing==0 ) {
			
			trule.setName(name);
			ruleMap.put(name, trule);
			
		}
		{
		switch ( LA(1)) {
		case LBRACK:
		{
			formals(trule);
			if ( inputState.guessing==0 ) {
				
				publicVarMap.put(trule, new ArrayList(trule.getVars()));
				
			}
			break;
		}
		case SEMI:
		case LITERAL_EXTENDS:
		case LITERAL_FORALL:
		case LITERAL_WHERE:
		case LITERAL_MAKE:
		case LITERAL_SET:
		case LITERAL_OVERRIDES:
		case LITERAL_SUPERSEDES:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		_loop130:
		do {
			if ((LA(1)==LITERAL_EXTENDS||LA(1)==LITERAL_OVERRIDES||LA(1)==LITERAL_SUPERSEDES)) {
				relatedRules(trule);
			}
			else {
				break _loop130;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case LITERAL_FORALL:
		{
			forall = LT(1);
			match(LITERAL_FORALL);
			params=ranges(trule, srcExtent, conjunct.getTerm(), false);
			if ( inputState.guessing==0 ) {
				
					sConjChar = getStartChar(forall);
				
			}
			break;
		}
		case SEMI:
		case LITERAL_WHERE:
		case LITERAL_MAKE:
		case LITERAL_SET:
		case LITERAL_LINKING:
		{
			if ( inputState.guessing==0 ) {
				params = EMPTY_PARAMS;
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_WHERE:
		{
			where = LT(1);
			match(LITERAL_WHERE);
			conjunct(trule, conjunct.getTerm());
			if ( inputState.guessing==0 ) {
				
				if (-1 == sConjChar) {
				sConjChar = getStartChar(where);
				}
				
			}
			break;
		}
		case SEMI:
		case LITERAL_MAKE:
		case LITERAL_SET:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			eConjChar = getEndChar();
		}
		targetClauses(trule, tgtExtent, trule.getTgt(), params);
		semi = LT(1);
		match(SEMI);
		if ( inputState.guessing==0 ) {
			
			eChar = getEndChar(semi);
			if (-1 != sConjChar) {
			if (-1 == eConjChar) {
			eConjChar = eChar;
			}
			reportMatch(conjunct, sConjChar, eConjChar);
			}
			reportMatch(trule, sChar, eChar);
			
		}
	}
	
	public final void superClasses(
		EClass eClass
	) throws RecognitionException, TokenStreamException {
		
		
		EClassifier superEClass;
		
		
		superEClass=simpleTypeLiteral(true);
		if ( inputState.guessing==0 ) {
			
			if (!(superEClass instanceof EClass)) {
			String type = (superEClass instanceof EDataType) ? "an EDataType" :
			(superEClass instanceof EEnum) ? "an EEnum" :
			"a " + superEClass.getClass().getName();
			throw new antlr.SemanticException("Expected an EClass: " + superEClass + ", found " + type, getFilename(), getMarkLine(), getMarkColumn());
			}
			eClass.getESuperTypes().add((EClass) superEClass);
			
		}
		{
		switch ( LA(1)) {
		case COMMA:
		{
			match(COMMA);
			superClasses(eClass);
			break;
		}
		case LBRACE:
		case SEMI:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final EClassifier  simpleTypeLiteral(
		boolean resolve
	) throws RecognitionException, TokenStreamException {
		EClassifier type = null;
		
		Token  id1 = null;
		Token  id2 = null;
		Token  caret = null;
		Token  qual = null;
		Token  fqid = null;
		
		EObject obj;
		String name = "";
		int sChar = -1;
		int eChar = -1;
		
		
		switch ( LA(1)) {
		case ID:
		{
			id1 = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				name = id1.getText(); setMark(id1); sChar = getStartChar(id1); eChar = getEndChar(id1);
			}
			{
			switch ( LA(1)) {
			case CARET:
			{
				match(CARET);
				id2 = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					name += '^' + id2.getText(); eChar = getEndChar(id2);
				}
				break;
			}
			case UNDERSCORE:
			case ID:
			case ANON_ID:
			case AT:
			case LBRACE:
			case COMMA:
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
				type = ModelUtils.findClassifierByName(trackingMap, name);
				if (resolve && null == type) {
				reportError(sChar+","+eChar+"\t"+"Cannot resolve type: " + name, sChar, eChar);
				}
				
			}
			break;
		}
		case FQID:
		case CARET:
		{
			{
			switch ( LA(1)) {
			case CARET:
			{
				caret = LT(1);
				match(CARET);
				qual = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					name = qual.getText() + '^'; setMark(qual); sChar = getStartChar(caret);
				}
				break;
			}
			case FQID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			fqid = LT(1);
			match(FQID);
			if ( inputState.guessing==0 ) {
				
				name += fqid.getText();
				type = ModelUtils.findClassifierByName(trackingMap, name);
				if (resolve && null == type) {
					if (-1 == sChar) {
						sChar = getStartChar(fqid);
					}
				reportError("Cannot resolve fully qualified type: " + name, sChar, getEndChar(fqid));
				}
				
			}
			break;
		}
		case LANGLE:
		{
			obj=objectlit();
			if ( inputState.guessing==0 ) {
				
				type = (EClassifier) obj;
				// objectlit throws an exception rather than return null
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return type;
	}
	
	public final void map_pairs(
		EMap dataMap
	) throws RecognitionException, TokenStreamException {
		
		
		map_pair(dataMap);
		{
		_loop114:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				map_pair(dataMap);
			}
			else {
				break _loop114;
			}
			
		} while (true);
		}
	}
	
	public final void map_pair(
		EMap dataMap
	) throws RecognitionException, TokenStreamException {
		
		
		Object keyObj, valueObj;
		
		
		keyObj=map_value();
		match(COLON);
		valueObj=literal(null, null);
		if ( inputState.guessing==0 ) {
			
			dataMap.put(keyObj, valueObj);
			
		}
	}
	
	public final Object  map_value() throws RecognitionException, TokenStreamException {
		Object obj = null;
		
		
		Expression expr;
		
		
		expr=literal(null, null);
		if ( inputState.guessing==0 ) {
			
			if (expr instanceof SimpleExpr) {
			obj = ((SimpleExpr) expr).getRepresentation();
			} else {
			throw new antlr.SemanticException("Map values must be simple literals, not: " + expr, getFilename(), getMarkLine(), getMarkColumn());
			}
			
		}
		return obj;
	}
	
	public final Expression  literal(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Expression expr = null;
		
		
		String s = null;
		EObject obj = null;
		
		
		switch ( LA(1)) {
		case STRING:
		{
			s=stringlit();
			if ( inputState.guessing==0 ) {
				
				expr = TefkatFactory.eINSTANCE.createStringConstant();
				((SimpleExpr) expr).setRepresentation(s);
				
			}
			break;
		}
		case INT:
		case REAL:
		case ADDOP:
		{
			expr=numberlit();
			break;
		}
		case LITERAL_true:
		case LITERAL_false:
		{
			s=booleanlit();
			if ( inputState.guessing==0 ) {
				
				expr = TefkatFactory.eINSTANCE.createBooleanConstant();
				((SimpleExpr) expr).setRepresentation(s);
				
			}
			break;
		}
		case LANGLE:
		{
			obj=objectlit();
			if ( inputState.guessing==0 ) {
				
				InstanceRef ref = TefkatFactory.eINSTANCE.createInstanceRef();
				ref.setObject(obj);
				expr = ref;
				
			}
			break;
		}
		case LSQUARE:
		{
			expr=collectionlit(scope, terms);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return expr;
	}
	
	public final String  tname() throws RecognitionException, TokenStreamException {
		String name = null;
		
		Token  id = null;
		
		id = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			name = id.getText();
			setMark(id);
			
		}
		return name;
	}
	
	public final String  pname() throws RecognitionException, TokenStreamException {
		String name = null;
		
		Token  id = null;
		
		id = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			name = id.getText();
			
		}
		return name;
	}
	
	public final List  ranges(
		VarScope scope, Var context, List terms, boolean isExactly
	) throws RecognitionException, TokenStreamException {
		List params = new ArrayList();
		
		
		MofInstance range;
		Var var = null;
		
		
		range=range(scope, context, isExactly, terms);
		if ( inputState.guessing==0 ) {
			
			if (null != range) {
			terms.add(range);
			params.add(range.getInstance());
			var = ((VarUse) range.getInstance()).getVar();
			
			Var extVar = range.getContext();
			if (null != extVar && tgtExtents.contains(extVar)) {
			reportWarning("Querying a target extent, '" + scope + "', is not supported.", getMarkLine(), getMarkColumn());
			}
			}
			
		}
		{
		switch ( LA(1)) {
		case LBRACE:
		{
			objectBody(scope, var, isExactly, terms, null);
			break;
		}
		case COMMA:
		case SEMI:
		case LITERAL_WHERE:
		case LITERAL_MAKE:
		case LITERAL_SET:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		_loop150:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				range=range(scope, context, isExactly, terms);
				if ( inputState.guessing==0 ) {
					
					if (null != range) {
					terms.add(range);
					params.add(range.getInstance());
					var = ((VarUse) range.getInstance()).getVar();
					
					Var extVar = range.getContext();
					if (null != extVar && tgtExtents.contains(extVar)) {
					reportWarning("Querying a target extent, '" + scope + "', is not supported.", getMarkLine(), getMarkColumn());
					}
					}
					
				}
				{
				switch ( LA(1)) {
				case LBRACE:
				{
					objectBody(scope, var, isExactly, terms, null);
					break;
				}
				case COMMA:
				case SEMI:
				case LITERAL_WHERE:
				case LITERAL_MAKE:
				case LITERAL_SET:
				case LITERAL_LINKING:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
			}
			else {
				break _loop150;
			}
			
		} while (true);
		}
		return params;
	}
	
	public final void targetClauses(
		VarScope scope, Var tgtExtent, List terms, List params
	) throws RecognitionException, TokenStreamException {
		
		
		
		
		{
		switch ( LA(1)) {
		case LITERAL_MAKE:
		{
			match(LITERAL_MAKE);
			making(scope, tgtExtent, terms, params);
			break;
		}
		case SEMI:
		case LITERAL_SET:
		case LITERAL_ENDIF:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_SET:
		{
			match(LITERAL_SET);
			settings(scope, tgtExtent, terms, params);
			break;
		}
		case SEMI:
		case LITERAL_ENDIF:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_LINKING:
		{
			trackingUses(scope, terms);
			break;
		}
		case SEMI:
		case LITERAL_ENDIF:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final String  rname() throws RecognitionException, TokenStreamException {
		String name = null;
		
		Token  id = null;
		
		id = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			name = id.getText();
			
		}
		return name;
	}
	
	public final void relatedRules(
		TRule trule
	) throws RecognitionException, TokenStreamException {
		
		
		boolean xflag = false, sflag = false;
		
		
		{
		switch ( LA(1)) {
		case LITERAL_EXTENDS:
		{
			match(LITERAL_EXTENDS);
			if ( inputState.guessing==0 ) {
				xflag = true;
			}
			break;
		}
		case LITERAL_OVERRIDES:
		{
			match(LITERAL_OVERRIDES);
			if ( inputState.guessing==0 ) {
				sflag = true;
			}
			break;
		}
		case LITERAL_SUPERSEDES:
		{
			match(LITERAL_SUPERSEDES);
			if ( inputState.guessing==0 ) {
				throw new antlr.SemanticException("The keyword 'SUPERSEDES' has been deprecated.", getFilename(), getMarkLine(), getMarkColumn());
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		related(trule, xflag, sflag);
		{
		_loop141:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				related(trule, xflag, sflag);
			}
			else {
				break _loop141;
			}
			
		} while (true);
		}
	}
	
	public final void making(
		VarScope scope, Var tgtExtent, List tgts, List params
	) throws RecognitionException, TokenStreamException {
		
		
		make(scope, tgtExtent, tgts, params);
		{
		_loop196:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				make(scope, tgtExtent, tgts, params);
			}
			else {
				break _loop196;
			}
			
		} while (true);
		}
	}
	
	public final void settings(
		VarScope scope, Var tgtExtent, List tgts, List params
	) throws RecognitionException, TokenStreamException {
		
		
		try { // for error handling
			setting(scope, tgtExtent, tgts, params);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				
			} else {
				throw ex;
			}
		}
		{
		_loop210:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				try { // for error handling
					setting(scope, tgtExtent, tgts, params);
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						
						reportError(ex);
						
					} else {
						throw ex;
					}
				}
			}
			else {
				break _loop210;
			}
			
		} while (true);
		}
	}
	
	public final void trackingUses(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		
		
		TrackingUse use;
		
		
		try { // for error handling
			use=trackingUse(scope, terms);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				use = null;
				
			} else {
				throw ex;
			}
		}
		if ( inputState.guessing==0 ) {
			
			if (null != use) {
			terms.add(use);
			}
			
		}
		{
		_loop218:
		do {
			if ((LA(1)==LITERAL_LINKING)) {
				try { // for error handling
					use=trackingUse(scope, terms);
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						
						reportError(ex);
						use = null;
						
					} else {
						throw ex;
					}
				}
				if ( inputState.guessing==0 ) {
					
					if (null != use) {
					terms.add(use);
					}
					
				}
			}
			else {
				break _loop218;
			}
			
		} while (true);
		}
	}
	
	public final void related(
		TRule trule, boolean xflag, boolean sflag
	) throws RecognitionException, TokenStreamException {
		
		
		String rname, vname;
		List vars = new ArrayList();
		
		
		rname=rname();
		if ( inputState.guessing==0 ) {
			
			if (xflag) { store(extendsMap, rname, trule); }
			if (sflag) { store(supersedesMap, rname, trule); }
			
		}
		{
		switch ( LA(1)) {
		case LBRACK:
		{
			match(LBRACK);
			vname=vname();
			if ( inputState.guessing==0 ) {
				
				vars.add(getVar(trule, vname));
				
			}
			{
			_loop145:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					vname=vname();
					if ( inputState.guessing==0 ) {
						
						vars.add(getVar(trule, vname));
						
					}
				}
				else {
					break _loop145;
				}
				
			} while (true);
			}
			match(RBRACK);
			if ( inputState.guessing==0 ) {
				
				if (xflag) { store(extendsVarMap, rname, vars); }
				if (sflag) { store(supersedesVarMap, rname, vars); }
				
			}
			break;
		}
		case COMMA:
		case SEMI:
		case LITERAL_EXTENDS:
		case LITERAL_FORALL:
		case LITERAL_WHERE:
		case LITERAL_MAKE:
		case LITERAL_SET:
		case LITERAL_OVERRIDES:
		case LITERAL_SUPERSEDES:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
	}
	
	public final String  vname() throws RecognitionException, TokenStreamException {
		String name = null;
		
		Token  id = null;
		Token  anon_id = null;
		Token  under = null;
		
		switch ( LA(1)) {
		case ID:
		{
			id = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
				name = id.getText();
				setMark(id);
				
			}
			break;
		}
		case ANON_ID:
		{
			anon_id = LT(1);
			match(ANON_ID);
			if ( inputState.guessing==0 ) {
				
				name = anon_id.getText();
				setMark(anon_id);
				
			}
			break;
		}
		case UNDERSCORE:
		{
			under = LT(1);
			match(UNDERSCORE);
			if ( inputState.guessing==0 ) {
				
				setMark(under);
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return name;
	}
	
	public final MofInstance  range(
		VarScope scope, Var outerContext, boolean isExactly, List terms
	) throws RecognitionException, TokenStreamException {
		MofInstance i = null;
		
		
		Var context;
		Expression type;
		String name;
		Var var = null;
		int sChar = -1, eChar = -1;
		
		
		if ( inputState.guessing==0 ) {
			sChar = getStartChar(LT(1));
		}
		{
		switch ( LA(1)) {
		case LITERAL_EXACT:
		{
			match(LITERAL_EXACT);
			if ( inputState.guessing==0 ) {
				isExactly = true;
			}
			break;
		}
		case LITERAL_DYNAMIC:
		{
			match(LITERAL_DYNAMIC);
			if ( inputState.guessing==0 ) {
				isExactly = false;
			}
			break;
		}
		case UNDERSCORE:
		case ID:
		case FQID:
		case CARET:
		case LANGLE:
		case DOLLAR:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		type=typeName(scope, terms);
		context=context(scope);
		name=vname();
		if ( inputState.guessing==0 ) {
			
			var = getVarInScope(scope, name);
			if (null == var) {
			var = declareVar(scope, name, getMarkLine(), getMarkColumn());
			}
			VarUse vu = TefkatFactory.eINSTANCE.createVarUse();
			vu.setVar(var);
			i = TefkatFactory.eINSTANCE.createMofInstance();
			i.setTypeName(type);
			i.setInstance(vu);
			i.setExact(isExactly);
			i.setContext(null == context ? outerContext : context);
			
			eChar = getEndChar();
			reportMatch(i, sChar, eChar);
			
		}
		return i;
	}
	
	public final void objectBody(
		VarScope scope, Var var, boolean isExactly, List terms, List params
	) throws RecognitionException, TokenStreamException {
		
		
		Expression feature;
		int sChar = -1;
		
		
		match(LBRACE);
		{
		_loop273:
		do {
			if ((LA(1)==ID||LA(1)==DOLLAR)) {
				if ( inputState.guessing==0 ) {
					sChar = getStartChar(LT(1));
				}
				feature=feature(scope, terms);
				match(COLON);
				{
				boolean synPredMatched272 = false;
				if (((LA(1)==LSQUARE))) {
					int _m272 = mark();
					synPredMatched272 = true;
					inputState.guessing++;
					try {
						{
						match(LSQUARE);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched272 = false;
					}
					rewind(_m272);
inputState.guessing--;
				}
				if ( synPredMatched272 ) {
					match(LSQUARE);
					featureVals(scope, var, isExactly, terms, params, feature, sChar);
					match(RSQUARE);
				}
				else if ((_tokenSet_2.member(LA(1)))) {
					featureVal(scope, var, isExactly, terms, params, feature, sChar);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				match(SEMI);
			}
			else {
				break _loop273;
			}
			
		} while (true);
		}
		match(RBRACE);
	}
	
	public final Expression  typeName(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Expression expr = null;
		
		
		String name = null;
		EClassifier type = null;
		
		
		switch ( LA(1)) {
		case DOLLAR:
		{
			match(DOLLAR);
			expr=factor(scope, terms);
			break;
		}
		case UNDERSCORE:
		{
			match(UNDERSCORE);
			if ( inputState.guessing==0 ) {
				name = "_";
			}
			if ( inputState.guessing==0 ) {
				
				StringConstant sc = TefkatFactory.eINSTANCE.createStringConstant();
				sc.setRepresentation(name);
				expr = sc;
				
			}
			break;
		}
		case ID:
		case FQID:
		case CARET:
		case LANGLE:
		{
			type=simpleTypeLiteral(true);
			if ( inputState.guessing==0 ) {
				
				InstanceRef ref = TefkatFactory.eINSTANCE.createInstanceRef();
				ref.setObject(type);
				expr = ref;
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return expr;
	}
	
	public final Var  context(
		VarScope scope
	) throws RecognitionException, TokenStreamException {
		Var context = null;;
		
		
		String name;
		
		
		{
		switch ( LA(1)) {
		case AT:
		{
			match(AT);
			name=vname();
			if ( inputState.guessing==0 ) {
				
				Var var = null;
				if (scope instanceof TRule) {
				var = getVarInScope(((TRule) scope).getTransformation(), name);
				} else if (scope instanceof PatternDefn) {
				var = getVarInScope(((PatternDefn) scope).getPatternScope(), name);
				}
				if (null == var || !(var instanceof Var)) {
				throw new antlr.SemanticException("No extent named: " + name, getFilename(), getMarkLine(), getMarkColumn());
				}
				//term.setContext((Var) var);
				context = (Var) var;
				
			}
			break;
		}
		case UNDERSCORE:
		case ID:
		case ANON_ID:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return context;
	}
	
	public final Expression  factor(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Expression expr = null;
		
		
		String vname = null;
		
		
		try {      // for error handling
			{
			if ((_tokenSet_3.member(LA(1)))) {
				expr=literal(scope, terms);
			}
			else if ((LA(1)==LBRACK)) {
				match(LBRACK);
				expr=expr(scope, terms);
				match(RBRACK);
			}
			else {
				boolean synPredMatched237 = false;
				if (((_tokenSet_4.member(LA(1))))) {
					int _m237 = mark();
					synPredMatched237 = true;
					inputState.guessing++;
					try {
						{
						{
						switch ( LA(1)) {
						case UNDERSCORE:
						case ID:
						case ANON_ID:
						{
							vname();
							break;
						}
						case LANGLE:
						{
							match(LANGLE);
							match(URITOK);
							match(RANGLE);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						{
						switch ( LA(1)) {
						case PERIOD:
						{
							match(PERIOD);
							break;
						}
						case ARROW:
						{
							match(ARROW);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						}
					}
					catch (RecognitionException pe) {
						synPredMatched237 = false;
					}
					rewind(_m237);
inputState.guessing--;
				}
				if ( synPredMatched237 ) {
					expr=path(scope, terms);
				}
				else {
					boolean synPredMatched239 = false;
					if (((LA(1)==ID))) {
						int _m239 = mark();
						synPredMatched239 = true;
						inputState.guessing++;
						try {
							{
							fname();
							match(LBRACK);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched239 = false;
						}
						rewind(_m239);
inputState.guessing--;
					}
					if ( synPredMatched239 ) {
						expr=functionCall(scope, terms);
					}
					else if ((LA(1)==UNDERSCORE||LA(1)==ID||LA(1)==ANON_ID)) {
						vname=vname();
						if ( inputState.guessing==0 ) {
							
							Var var = getVarInScope(scope, vname);
							if (var == null) {
							var = declareVar(scope, vname, getMarkLine(), getMarkColumn());
							}
							VarUse vu = TefkatFactory.eINSTANCE.createVarUse();
							vu.setVar(var);
							expr = vu;
							
						}
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}}
					}
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						
						reportError(ex);
						
					} else {
						throw ex;
					}
				}
				return expr;
			}
			
	public final EObject  objectlit() throws RecognitionException, TokenStreamException {
		EObject obj = null;
		
		Token  langle = null;
		Token  tok = null;
		Token  rangle = null;
		
		
		
		langle = LT(1);
		match(LANGLE);
		tok = LT(1);
		match(URITOK);
		rangle = LT(1);
		match(RANGLE);
		if ( inputState.guessing==0 ) {
			
			URI uri = URI.createURI(tok.getText());
			try {
			obj = resource.getResourceSet().getEObject(uri, true);
			if (null == obj) {
			reportError("Could not resolve instance reference: " + tok.getText(), getStartChar(langle), getEndChar(rangle));
			}
			} catch (Exception e) {
			reportError("Could not resolve instance reference: " + tok.getText() + " (" + e.getMessage() + ")", getStartChar(langle), getEndChar(rangle));
			}
			
		}
		return obj;
	}
	
	public final Term  disjunct(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Term term = null;
		
		
		OrTerm oTerm = TefkatFactory.eINSTANCE.createOrTerm();
		List oTerms = new ArrayList();
		Term rTerm = null;
		int sChar = -1, eChar = -1, sDisjChar = -1;
		
		
		if ( inputState.guessing==0 ) {
			sChar = getStartChar(LT(1));
		}
		try { // for error handling
			rTerm=relation(scope, oTerms);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				term = null;
				
			} else {
				throw ex;
			}
		}
		if ( inputState.guessing==0 ) {
			
				// If rTerm != null && oTerms.size() > 0, then we need an AndTerm
			if (rTerm instanceof AndTerm) {
			List conjunct = ((AndTerm) rTerm).getTerm();
			conjunct.addAll(oTerms);
			if (conjunct.size() > 1) {
			rTerm.setCompoundTerm(oTerm);
			} else if (conjunct.size() == 1) {
				oTerm.getTerm().addAll(conjunct);
			}
			} else {
			AndTerm aTerm = TefkatFactory.eINSTANCE.createAndTerm();
			List conjunct = aTerm.getTerm();
			conjunct.addAll(oTerms);
			if (null != rTerm) {
			conjunct.add(rTerm);
			}
			if (conjunct.size() > 1) {
			aTerm.setCompoundTerm(oTerm);
			reportMatch(aTerm, sChar, getEndChar());
			} else if (conjunct.size() == 1) {
				oTerm.getTerm().addAll(conjunct);
			}
			}
			oTerms.clear();
			
		}
		{
		_loop165:
		do {
			if ((LA(1)==LITERAL_OR)) {
				match(LITERAL_OR);
				if ( inputState.guessing==0 ) {
					sDisjChar = getStartChar(LT(1));
				}
				try { // for error handling
					rTerm=relation(scope, oTerms);
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						
						reportError(ex);
						rTerm = null;
						
					} else {
						throw ex;
					}
				}
				if ( inputState.guessing==0 ) {
					
						// If rTerm != null && oTerms.size() > 0, then we need an AndTerm
					if (rTerm instanceof AndTerm) {
					List conjunct = ((AndTerm) rTerm).getTerm();
					conjunct.addAll(oTerms);
					if (conjunct.size() > 1) {
					rTerm.setCompoundTerm(oTerm);
					} else if (conjunct.size() == 1) {
						oTerm.getTerm().addAll(conjunct);
					}
					} else {
					AndTerm aTerm = TefkatFactory.eINSTANCE.createAndTerm();
					List conjunct = aTerm.getTerm();
					conjunct.addAll(oTerms);
					if (null != rTerm) {
					conjunct.add(rTerm);
					}
					if (conjunct.size() > 1) {
					aTerm.setCompoundTerm(oTerm);
					reportMatch(aTerm, sDisjChar, getEndChar());
					} else if (conjunct.size() == 1) {
						oTerm.getTerm().addAll(conjunct);
					}
					}
					oTerms.clear();
					
				}
			}
			else {
				break _loop165;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			
			if (oTerm.getTerm().size() > 1) {
			eChar = getEndChar();
			reportMatch(oTerm, sChar, eChar);
			term = oTerm;
			} else {
			terms.addAll(oTerm.getTerm());
			}
			
		}
		return term;
	}
	
	public final Term  relation(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Term term = null;
		
		Token  lbrack = null;
		Token  b = null;
		Token  assop = null;
		
		AndTerm aTerm = null;
		Expression lhs, rhs, path;
		Var var = null;
		String operator = null;
		Token relop = null;
		int sChar = -1, eChar = -1;
		
		
		if ( inputState.guessing==0 ) {
			
				sChar = getStartChar(LT(1));
			
		}
		{
		switch ( LA(1)) {
		case BANG:
		case LITERAL_NOT:
		{
			{
			switch ( LA(1)) {
			case BANG:
			{
				match(BANG);
				break;
			}
			case LITERAL_NOT:
			{
				match(LITERAL_NOT);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			term=relation(scope, terms);
			if ( inputState.guessing==0 ) {
				
				NotTerm nterm = TefkatFactory.eINSTANCE.createNotTerm();
				if (null != term) {
				nterm.getTerm().add(term);
				}
				term = nterm;
				
			}
			break;
		}
		case LITERAL_IF:
		{
			term=s_ifthenelse(scope);
			break;
		}
		case BOOLEAN:
		{
			b = LT(1);
			match(BOOLEAN);
			if ( inputState.guessing==0 ) {
				
				if ("TRUE".equals(b.getText())) {
				term = TefkatFactory.eINSTANCE.createAndTerm();
				} else {
				term = TefkatFactory.eINSTANCE.createOrTerm();
				}
				
			}
			break;
		}
		case LITERAL_UNDEF:
		{
			match(LITERAL_UNDEF);
			lhs=factor(scope, terms);
			if ( inputState.guessing==0 ) {
				
				if (scope instanceof TRule || scope instanceof PatternDefn) {
				var = (Var) TefkatFactory.eINSTANCE.createVar();
				} else {
				throw new antlr.SemanticException("Invalid scope for UNDEF; must be a PatternDefn or TRule, not: " + scope, getFilename(), getMarkLine(), getMarkColumn());
				}
				var.setScope(scope);
				VarUse vu = TefkatFactory.eINSTANCE.createVarUse();
				vu.setVar(var);
				Condition condition = TefkatFactory.eINSTANCE.createCondition();
				condition.setRelation("=");
				List args = condition.getArg();
				if (null != lhs) {
				args.add(lhs);
				}
				args.add(vu);
				NotTerm nTerm = TefkatFactory.eINSTANCE.createNotTerm();
				nTerm.getTerm().add(condition);
				term = nTerm;
				
			}
			break;
		}
		default:
			if ((LA(1)==LBRACK)) {
				lbrack = LT(1);
				match(LBRACK);
				if ( inputState.guessing==0 ) {
					
					aTerm = TefkatFactory.eINSTANCE.createAndTerm();
					term = aTerm;
					
				}
				conjunct(scope, aTerm.getTerm());
				match(RBRACK);
			}
			else {
				boolean synPredMatched182 = false;
				if (((_tokenSet_5.member(LA(1))))) {
					int _m182 = mark();
					synPredMatched182 = true;
					inputState.guessing++;
					try {
						{
						{
						switch ( LA(1)) {
						case LITERAL_EXACT:
						{
							match(LITERAL_EXACT);
							break;
						}
						case LITERAL_DYNAMIC:
						{
							match(LITERAL_DYNAMIC);
							break;
						}
						case UNDERSCORE:
						case ID:
						case FQID:
						case CARET:
						case DOLLAR:
						{
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						{
						switch ( LA(1)) {
						case DOLLAR:
						{
							match(DOLLAR);
							break;
						}
						case UNDERSCORE:
						{
							match(UNDERSCORE);
							break;
						}
						case FQID:
						case CARET:
						{
							{
							switch ( LA(1)) {
							case CARET:
							{
								match(CARET);
								match(ID);
								break;
							}
							case FQID:
							{
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							match(FQID);
							break;
						}
						case ID:
						{
							match(ID);
							{
							switch ( LA(1)) {
							case CARET:
							{
								match(CARET);
								match(ID);
								break;
							}
							case UNDERSCORE:
							case ID:
							case ANON_ID:
							case AT:
							{
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						{
						switch ( LA(1)) {
						case AT:
						{
							match(AT);
							match(ID);
							break;
						}
						case UNDERSCORE:
						case ID:
						case ANON_ID:
						{
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						vname();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched182 = false;
					}
					rewind(_m182);
inputState.guessing--;
				}
				if ( synPredMatched182 ) {
					term=range(scope, null, false, terms);
					if ( inputState.guessing==0 ) {
						
						MofInstance inst = (MofInstance) term;
						// NPE triggred here if range[] has failed to match
						var = ((VarUse) inst.getInstance()).getVar();
						Var extVar = inst.getContext();
						if (null != extVar && tgtExtents.contains(extVar)) {
						reportWarning("Querying a target extent, '" + scope + "', is not supported.", getMarkLine(), getMarkColumn());
						}
						
					}
					{
					switch ( LA(1)) {
					case LBRACE:
					{
						objectBody(scope, var, false, terms, null);
						break;
					}
					case RBRACK:
					case SEMI:
					case LITERAL_MAKE:
					case LITERAL_SET:
					case LITERAL_AND:
					case LITERAL_OR:
					case LITERAL_ENDIF:
					case LITERAL_THEN:
					case LITERAL_ELSEIF:
					case LITERAL_ELSE:
					case LITERAL_LINKING:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					boolean synPredMatched186 = false;
					if (((LA(1)==ID))) {
						int _m186 = mark();
						synPredMatched186 = true;
						inputState.guessing++;
						try {
							{
							pname();
							match(LBRACK);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched186 = false;
						}
						rewind(_m186);
inputState.guessing--;
					}
					if ( synPredMatched186 ) {
						term=patternUse(scope, terms);
					}
					else {
						boolean synPredMatched188 = false;
						if (((LA(1)==ID))) {
							int _m188 = mark();
							synPredMatched188 = true;
							inputState.guessing++;
							try {
								{
								tname();
								match(LITERAL_LINKS);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched188 = false;
							}
							rewind(_m188);
inputState.guessing--;
						}
						if ( synPredMatched188 ) {
							term=links(scope, terms);
						}
						else if ((_tokenSet_6.member(LA(1)))) {
							lhs=expr(scope, terms);
							{
							switch ( LA(1)) {
							case LITERAL_BEFORE:
							{
								match(LITERAL_BEFORE);
								rhs=expr(scope, terms);
								match(LITERAL_IN);
								path=path(scope, terms);
								if ( inputState.guessing==0 ) {
									
									FeatureExpr fExpr = (FeatureExpr) path;
									MofOrder order = TefkatFactory.eINSTANCE.createMofOrder();
									order.setLesser(lhs);
									order.setGreater(rhs);
									order.setInstance((Expression) fExpr.getArg().get(0));
									order.setFeature(fExpr.getFeature());
									
									term = order;
									
								}
								break;
							}
							case LANGLE:
							case RANGLE:
							case ASSIGN:
							case RELOP:
							{
								{
								switch ( LA(1)) {
								case ASSIGN:
								{
									assop = LT(1);
									match(ASSIGN);
									if ( inputState.guessing==0 ) {
										
										operator = assop.getText();
										
									}
									break;
								}
								case LANGLE:
								case RANGLE:
								case RELOP:
								{
									relop=relop();
									if ( inputState.guessing==0 ) {
										
										operator = relop.getText();
										
									}
									break;
								}
								default:
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
								}
								}
								rhs=expr(scope, terms);
								if ( inputState.guessing==0 ) {
									
									Condition cond = TefkatFactory.eINSTANCE.createCondition();
									cond.setRelation(operator);
									List args = cond.getArg();
									if (null != lhs) {
									args.add(lhs);
									}
									if (null != rhs) {
									args.add(rhs);
									}
									
									term = cond;
									
								}
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
						}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}}}}
					}
					if ( inputState.guessing==0 ) {
						eChar = getEndChar(); reportMatch(term, sChar, eChar);
					}
					return term;
				}
				
	public final IfTerm  s_ifthenelse(
		VarScope scope
	) throws RecognitionException, TokenStreamException {
		IfTerm term = null;
		
		Token  start = null;
		Token  end = null;
		
		int sChar = -1, eChar = -1;
		
		
		start = LT(1);
		match(LITERAL_IF);
		if ( inputState.guessing==0 ) {
			
				        sChar = getStartChar(start);
				
		}
		term=s_ite(scope);
		end = LT(1);
		match(LITERAL_ENDIF);
		if ( inputState.guessing==0 ) {
			
			eChar = getEndChar(end);
			reportMatch(term, sChar, eChar);
			
		}
		return term;
	}
	
	public final IfTerm  s_ite(
		VarScope scope
	) throws RecognitionException, TokenStreamException {
		IfTerm term = null;
		
		Token  elseif = null;
		
		Term condTerm = null, thenTerm = null, elseTerm = null;
		List termList = new ArrayList();
		int sChar = -1, eChar = -1;
		
		
		conjunct(scope, termList);
		if ( inputState.guessing==0 ) {
			
			if (termList.size() > 1) {
			condTerm = TefkatFactory.eINSTANCE.createAndTerm();
			((AndTerm) condTerm).getTerm().addAll(termList);
			} else if (termList.size() == 1) {
			condTerm = (Term) termList.get(0);
			}
			termList.clear();
			
		}
		{
		switch ( LA(1)) {
		case LITERAL_THEN:
		{
			match(LITERAL_THEN);
			conjunct(scope, termList);
			if ( inputState.guessing==0 ) {
				
				if (termList.size() > 1) {
				thenTerm = TefkatFactory.eINSTANCE.createAndTerm();
				((AndTerm) thenTerm).getTerm().addAll(termList);
				} else if (termList.size() == 1) {
				thenTerm = (Term) termList.get(0);
				}
				termList.clear();
				
			}
			break;
		}
		case LITERAL_ENDIF:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		{
			if ( inputState.guessing==0 ) {
				
				// No THEN term is equivalent to THEN TRUE
				// We use an empty AndTerm to implement TRUE
				thenTerm = TefkatFactory.eINSTANCE.createAndTerm();
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_ELSEIF:
		{
			elseif = LT(1);
			match(LITERAL_ELSEIF);
			if ( inputState.guessing==0 ) {
				
					    sChar = getStartChar(elseif);
				
			}
			elseTerm=s_ite(scope);
			if ( inputState.guessing==0 ) {
				
				eChar = getEndChar();
				reportMatch(term, sChar, eChar);
				
			}
			break;
		}
		case LITERAL_ELSE:
		{
			match(LITERAL_ELSE);
			conjunct(scope, termList);
			if ( inputState.guessing==0 ) {
				
				if (termList.size() > 1) {
				elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
				((AndTerm) elseTerm).getTerm().addAll(termList);
				} else if (termList.size() == 1) {
				elseTerm = (Term) termList.get(0);
				}
				
			}
			break;
		}
		case LITERAL_ENDIF:
		{
			if ( inputState.guessing==0 ) {
				
				// No ELSE term is equivalent to ELSE TRUE
				// We use an empty AndTerm to implement TRUE
				elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
			term = TefkatFactory.eINSTANCE.createIfTerm();
			List args = term.getTerm();
			args.add(condTerm);
			args.add(thenTerm);
			args.add(elseTerm);
			
		}
		return term;
	}
	
	public final IfTerm  t_ifthenelse(
		VarScope scope, Var tgtExtent, List params
	) throws RecognitionException, TokenStreamException {
		IfTerm term = null;
		
		Token  start = null;
		Token  end = null;
		
		int sChar = -1, eChar = -1;
		
		
		start = LT(1);
		match(LITERAL_IF);
		if ( inputState.guessing==0 ) {
			
				sChar = getStartChar(start);
			
		}
		term=t_ite(scope, tgtExtent, params);
		end = LT(1);
		match(LITERAL_ENDIF);
		if ( inputState.guessing==0 ) {
			
			eChar = getEndChar(end);
			reportMatch(term, sChar, eChar);
			
		}
		return term;
	}
	
	public final IfTerm  t_ite(
		VarScope scope, Var tgtExtent, List params
	) throws RecognitionException, TokenStreamException {
		IfTerm term = null;
		
		Token  elseif = null;
		
		Term condTerm = null, thenTerm = null, elseTerm = null;
		List termList = new ArrayList();
		int sChar = -1, eChar = -1;
		
		
		conjunct(scope, termList);
		if ( inputState.guessing==0 ) {
			
			if (termList.size() > 1) {
			condTerm = TefkatFactory.eINSTANCE.createAndTerm();
			((AndTerm) condTerm).getTerm().addAll(termList);
			} else if (termList.size() == 1) {
			condTerm = (Term) termList.get(0);
			}
			termList.clear();
			
		}
		{
		switch ( LA(1)) {
		case LITERAL_THEN:
		{
			match(LITERAL_THEN);
			targetClauses(scope, tgtExtent, termList, params);
			if ( inputState.guessing==0 ) {
				
				if (termList.size() > 1) {
				thenTerm = TefkatFactory.eINSTANCE.createAndTerm();
				((AndTerm) thenTerm).getTerm().addAll(termList);
				} else if (termList.size() == 1) {
				thenTerm = (Term) termList.get(0);
				}
				termList.clear();
				
			}
			break;
		}
		case LITERAL_ENDIF:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		{
			if ( inputState.guessing==0 ) {
				
				SimpleExpr bool = TefkatFactory.eINSTANCE.createBooleanConstant();
				bool.setRepresentation("TRUE");
				Condition cond = TefkatFactory.eINSTANCE.createCondition();
				cond.setRelation("boolean");
				List args = cond.getArg();
				args.add(bool);
				thenTerm = cond;
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case LITERAL_ELSEIF:
		{
			elseif = LT(1);
			match(LITERAL_ELSEIF);
			if ( inputState.guessing==0 ) {
				
					sChar = getStartChar(elseif);
				
			}
			elseTerm=t_ite(scope, tgtExtent, params);
			if ( inputState.guessing==0 ) {
				
				eChar = getStartChar();
				reportMatch(term, sChar, eChar);
				
			}
			break;
		}
		case LITERAL_ELSE:
		{
			match(LITERAL_ELSE);
			targetClauses(scope, tgtExtent, termList, params);
			if ( inputState.guessing==0 ) {
				
				if (termList.size() > 1) {
				elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
				((AndTerm) elseTerm).getTerm().addAll(termList);
				} else if (termList.size() == 1) {
				elseTerm = (Term) termList.get(0);
				}
				
			}
			break;
		}
		case LITERAL_ENDIF:
		{
			if ( inputState.guessing==0 ) {
				
				// No ELSE term is equivalent to ELSE TRUE
				// We use an empty AndTerm to implement TRUE
				elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
			// tests for null are required in case we're continuing after
			// a parse error
			term = TefkatFactory.eINSTANCE.createIfTerm();
			List args = term.getTerm();
			if (null != condTerm) {
			args.add(condTerm);
			}
			if (null != thenTerm) {
			args.add(thenTerm);
			}
			if (null != elseTerm) {
			args.add(elseTerm);
			}
			
		}
		return term;
	}
	
	public final PatternUse  patternUse(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		PatternUse use = null;
		
		
		String name;
		List params;
		
		
		name=pname();
		params=actuals(scope, terms);
		if ( inputState.guessing==0 ) {
			
			use = TefkatFactory.eINSTANCE.createPatternUse();
			use.getArg().addAll(params);
			patUseMap.put(use, name + "/" + params.size());
			
		}
		return use;
	}
	
	public final TrackingUse  links(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		TrackingUse use = null;
		
		
		String tname;
		Map featureMap = null;
		
		
		tname=tname();
		match(LITERAL_LINKS);
		if ( inputState.guessing==0 ) {
			
			use = TefkatFactory.eINSTANCE.createTrackingUse();
			use.setTrackingName(tname);
			EClassifier tracking = ModelUtils.findClassifierByName(trackingMap, tname);
			if (null == tracking) {
			reportWarning("Undefined tracking class: " + tname, getMarkLine(), getMarkColumn());
			} else if (!(tracking instanceof EClass)) {
			String type = (tracking instanceof EDataType) ? "an EDataType" :
			(tracking instanceof EEnum) ? "an EEnum" :
			"a " + tracking.getClass().getName();
			reportWarning("Expected an EClass: " + tname + ", found " + type, getMarkLine(), getMarkColumn());
			} else {
			use.setTracking((EClass) tracking);
			}
			featureMap = use.getFeatures().map();
			
		}
		featureMaps(scope, featureMap, terms);
		return use;
	}
	
	public final Expression  expr(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Expression expr;
		
		Token  op = null;
		
		Expression rhs = null;
		
		
		expr=term(scope, terms);
		{
		switch ( LA(1)) {
		case ADDOP:
		{
			op = LT(1);
			match(ADDOP);
			rhs=expr(scope, terms);
			if ( inputState.guessing==0 ) {
				
				FunctionExpr fexpr = TefkatFactory.eINSTANCE.createFunctionExpr();
				fexpr.setFunction(op.getText());
				List args = fexpr.getArg();
				if (null != expr) {
				args.add(expr);
				}
				if (rhs != null) {
				args.add(rhs);
				}
				expr = fexpr;
				
			}
			break;
		}
		case LANGLE:
		case RANGLE:
		case RBRACK:
		case RSQUARE:
		case COMMA:
		case SEMI:
		case ASSIGN:
		case RELOP:
		case LITERAL_MAKE:
		case LITERAL_SET:
		case LITERAL_AND:
		case LITERAL_OR:
		case LITERAL_ENDIF:
		case LITERAL_THEN:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		case LITERAL_BEFORE:
		case LITERAL_IN:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return expr;
	}
	
	public final Expression  path(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Expression expr = null;
		
		
		String vname;
		EObject obj;
		FeatureExpr fe = null;
		Expression feature;
		List args = null, params = null;
		
		
		{
		switch ( LA(1)) {
		case UNDERSCORE:
		case ID:
		case ANON_ID:
		{
			vname=vname();
			if ( inputState.guessing==0 ) {
				
				expr = TefkatFactory.eINSTANCE.createVarUse();
				Var var = getVarInScope(scope, vname);
				if (null == var) {
				var = declareVar(scope, vname, getMarkLine(), getMarkColumn());
				}
				((VarUse) expr).setVar(var);
				
			}
			break;
		}
		case LANGLE:
		{
			obj=objectlit();
			if ( inputState.guessing==0 ) {
				
				InstanceRef ref = TefkatFactory.eINSTANCE.createInstanceRef();
				ref.setObject(obj);
				expr = ref;
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		int _cnt251=0;
		_loop251:
		do {
			if ((LA(1)==PERIOD||LA(1)==ARROW)) {
				{
				switch ( LA(1)) {
				case PERIOD:
				{
					match(PERIOD);
					break;
				}
				case ARROW:
				{
					match(ARROW);
					if ( inputState.guessing==0 ) {
						
						// FIXME use funmap() instead
						
						// Expr->Feature is short for Expr.$member.Feature
						// where Expr must be a collection
						SimpleExpr sc = TefkatFactory.eINSTANCE.createStringConstant();
						sc.setRepresentation("$member");
						
						fe = TefkatFactory.eINSTANCE.createFeatureExpr();
						fe.setFeature(sc);
						
						args = fe.getArg();
						args.add(expr);
						expr = fe;
						
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				feature=feature(scope, terms);
				if ( inputState.guessing==0 ) {
					
					fe = TefkatFactory.eINSTANCE.createFeatureExpr();
					fe.setFeature(feature);
					
					args = fe.getArg();
					args.add(expr);
					expr = fe;
					
				}
				{
				if ((LA(1)==LBRACK)) {
					params=actuals(scope, terms);
					if ( inputState.guessing==0 ) {
						
						fe.setOperation(true);
						args.addAll(params);
						
					}
				}
				else if ((_tokenSet_7.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				if ((LA(1)==LBRACE)) {
					match(LBRACE);
					match(RBRACE);
					if ( inputState.guessing==0 ) {
						
						fe.setCollect(true);
						
					}
				}
				else if ((_tokenSet_7.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else {
				if ( _cnt251>=1 ) { break _loop251; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt251++;
		} while (true);
		}
		return expr;
	}
	
	public final Token  relop() throws RecognitionException, TokenStreamException {
		Token relop = null;
		
		Token  tok1 = null;
		Token  tok2 = null;
		Token  tok3 = null;
		
		switch ( LA(1)) {
		case RELOP:
		{
			tok1 = LT(1);
			match(RELOP);
			if ( inputState.guessing==0 ) {
				relop = tok1;
			}
			break;
		}
		case LANGLE:
		{
			tok2 = LT(1);
			match(LANGLE);
			if ( inputState.guessing==0 ) {
				relop = tok2;
			}
			break;
		}
		case RANGLE:
		{
			tok3 = LT(1);
			match(RANGLE);
			if ( inputState.guessing==0 ) {
				relop = tok3;
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return relop;
	}
	
	public final void make(
		VarScope scope, Var tgtExtent, List tgts, List params
	) throws RecognitionException, TokenStreamException {
		
		
			Term term;
		
		
		boolean synPredMatched199 = false;
		if (((LA(1)==ID))) {
			int _m199 = mark();
			synPredMatched199 = true;
			inputState.guessing++;
			try {
				{
				pname();
				match(LBRACK);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched199 = false;
			}
			rewind(_m199);
inputState.guessing--;
		}
		if ( synPredMatched199 ) {
			templateUse(scope, tgtExtent, tgts);
		}
		else {
			boolean synPredMatched201 = false;
			if (((LA(1)==LITERAL_IF))) {
				int _m201 = mark();
				synPredMatched201 = true;
				inputState.guessing++;
				try {
					{
					match(LITERAL_IF);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched201 = false;
				}
				rewind(_m201);
inputState.guessing--;
			}
			if ( synPredMatched201 ) {
				term=t_ifthenelse(scope, tgtExtent, params);
				if ( inputState.guessing==0 ) {
					
					tgts.add(term);
					
				}
			}
			else if ((_tokenSet_5.member(LA(1)))) {
				makeObject(scope, tgtExtent, true, tgts, params);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		
	public final void templateUse(
		VarScope scope, Var outerContext, List tgts
	) throws RecognitionException, TokenStreamException {
		
		
		PatternUse useTerm;
		
		
		useTerm=patternUse(scope, tgts);
		if ( inputState.guessing==0 ) {
			
			if (null != useTerm) {
			tgts.add(useTerm);
			}
			
		}
	}
	
	public final Var  makeObject(
		VarScope scope, Var tgtExtent, boolean isExactly, List tgts, List params
	) throws RecognitionException, TokenStreamException {
		Var targetVar = null;
		
		
		MofInstance makeTerm;
		int sChar = -1, eChar = -1;
		
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				sChar = getStartChar();
			}
			makeTerm=range(scope, tgtExtent, isExactly, tgts);
			if ( inputState.guessing==0 ) {
				
				if (null != makeTerm) {
				tgts.add(makeTerm);
				targetVar = ((VarUse) makeTerm.getInstance()).getVar();
				}
				
			}
			{
			if (((LA(1)==LITERAL_FROM))&&(null != params)) {
				params=unique(scope, tgtExtent, tgts, targetVar);
			}
			else if ((_tokenSet_8.member(LA(1)))) {
				if ( inputState.guessing==0 ) {
					
						if (Collections.EMPTY_LIST == params) {
					reportWarning("No default FROM clauses in TEMPLATEs", getMarkLine(), getMarkColumn());
						} else if (null != params) {
					Injection injection = TefkatFactory.eINSTANCE.createInjection();
					
					String varName = null;
					if (null != targetVar) {        // Skip this if we're continuing after a parse error
					varName = targetVar.getName();
					if (null == varName) {
					varName = String.valueOf(targetVar.hashCode());
					}
					}
					injection.setName(scope.getName() + " " + varName);
					
					List sources = injection.getSources();
					for (Iterator itr = params.iterator(); itr.hasNext(); ) {
					Expression expr = (Expression) itr.next();
					sources.add(expr.copy());
					}
					VarUse varUse = TefkatFactory.eINSTANCE.createVarUse();
					varUse.setVar(targetVar);
					injection.setTarget(varUse);
					tgts.add(injection);
					
					eChar = getEndChar();
					reportMatch(injection, sChar, eChar);
					}
					
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			switch ( LA(1)) {
			case LBRACE:
			{
				objectBody(scope, targetVar, isExactly, tgts, params);
				break;
			}
			case RSQUARE:
			case COMMA:
			case SEMI:
			case LITERAL_SET:
			case LITERAL_ENDIF:
			case LITERAL_ELSEIF:
			case LITERAL_ELSE:
			case LITERAL_LINKING:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				
			} else {
				throw ex;
			}
		}
		return targetVar;
	}
	
	public final List  unique(
		VarScope scope, Var outerContext, List tgts, Var targetVar
	) throws RecognitionException, TokenStreamException {
		List params;
		
		Token  from = null;
		
		String name;
		int sChar = -1, eChar = -1;
		
		
		from = LT(1);
		match(LITERAL_FROM);
		if ( inputState.guessing==0 ) {
			
				        sChar = getStartChar(from);
				
		}
		name=uname();
		params=actuals(scope, tgts);
		if ( inputState.guessing==0 ) {
			
			Injection injection = TefkatFactory.eINSTANCE.createInjection();
			injection.setName(name);
			injection.getSources().addAll(params);
			VarUse varUse = TefkatFactory.eINSTANCE.createVarUse();
			varUse.setVar(targetVar);
			injection.setTarget(varUse);
			tgts.add(injection);
			
			eChar = getEndChar();
			reportMatch(injection, sChar, eChar);
			
		}
		return params;
	}
	
	public final String  uname() throws RecognitionException, TokenStreamException {
		String name = null;
		
		Token  id = null;
		
		id = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			name = id.getText();
			
		}
		return name;
	}
	
	public final List  actuals(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		List list = null;
		
		
		match(LBRACK);
		{
		switch ( LA(1)) {
		case RBRACK:
		{
			match(RBRACK);
			if ( inputState.guessing==0 ) {
				list = new ArrayList();
			}
			break;
		}
		case UNDERSCORE:
		case ID:
		case ANON_ID:
		case STRING:
		case INT:
		case REAL:
		case LANGLE:
		case LBRACK:
		case LSQUARE:
		case ADDOP:
		case LITERAL_true:
		case LITERAL_false:
		{
			list=exprs(scope, terms);
			match(RBRACK);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return list;
	}
	
	public final void setting(
		VarScope scope, Var tgtExtent, List tgts, List params
	) throws RecognitionException, TokenStreamException {
		
		Token  b = null;
		
		Term term = null;
		
		
		switch ( LA(1)) {
		case UNDERSCORE:
		case ID:
		case ANON_ID:
		case STRING:
		case INT:
		case REAL:
		case LANGLE:
		case LBRACK:
		case LSQUARE:
		case ADDOP:
		case LITERAL_true:
		case LITERAL_false:
		{
			setting_stmt(scope, tgts);
			break;
		}
		case BOOLEAN:
		{
			b = LT(1);
			match(BOOLEAN);
			if ( inputState.guessing==0 ) {
				
				if ("TRUE".equals(b.getText())) {
					term = TefkatFactory.eINSTANCE.createAndTerm();
				} else {
					// An empty OR represents FALSE but OR is not valid
					// on the target side, so we cheat and wrap it in an AndTerm
					term = TefkatFactory.eINSTANCE.createAndTerm();
					((AndTerm) term).getTerm().add(TefkatFactory.eINSTANCE.createOrTerm());
				}
				tgts.add(term);
				
			}
			break;
		}
		case LITERAL_IF:
		{
			term=t_ifthenelse(scope, tgtExtent, params);
			if ( inputState.guessing==0 ) {
				
				tgts.add(term);
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
	}
	
/**
 * Only allow stmts of form Path = Expr or Path() or Path() BEFORE Expr IN Path in clause
 */
	public final void setting_stmt(
		VarScope scope, List tgts
	) throws RecognitionException, TokenStreamException {
		
		Token  assop = null;
		
		Expression lhs, rhs, path;
		Term term = null;
		int sChar = -1, eChar = -1;
		
		
		if ( inputState.guessing==0 ) {
			sChar = getStartChar(LT(1));
		}
		lhs=expr(scope, tgts);
		{
		switch ( LA(1)) {
		case LITERAL_BEFORE:
		{
			match(LITERAL_BEFORE);
			rhs=expr(scope, tgts);
			match(LITERAL_IN);
			path=path(scope, tgts);
			if ( inputState.guessing==0 ) {
				
				FeatureExpr fExpr = (FeatureExpr) path;
				MofOrder order = TefkatFactory.eINSTANCE.createMofOrder();
				order.setLesser(lhs);
				order.setGreater(rhs);
				order.setInstance((Expression) fExpr.getArg().get(0));
				order.setFeature(fExpr.getFeature());
				
				term = order;
				
			}
			break;
		}
		case ASSIGN:
		{
			assop = LT(1);
			match(ASSIGN);
			if ( inputState.guessing==0 ) {
				
				if (lhs instanceof FeatureExpr) {
				if (((FeatureExpr) lhs).isOperation()) {
				throw new antlr.SemanticException("Cannot assign to an operation: " + lhs, getFilename(), assop.getLine(), assop.getColumn());
				}
				} else {
				throw new antlr.SemanticException("LHS of an assignment must be a path: " + lhs, getFilename(), assop.getLine(), assop.getColumn());
				}
				
			}
			rhs=expr(scope, tgts);
			if ( inputState.guessing==0 ) {
				
				Condition cond = TefkatFactory.eINSTANCE.createCondition();
				cond.setRelation("=");
				if (null != lhs) {
				cond.getArg().add(lhs);
				}
				if (null != rhs) {
				cond.getArg().add(rhs);
				}
				
				term = cond;
				
			}
			break;
		}
		case COMMA:
		case SEMI:
		case LITERAL_ENDIF:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		case LITERAL_LINKING:
		{
			if ( inputState.guessing==0 ) {
				
				if (((lhs instanceof FeatureExpr) && ((FeatureExpr) lhs).isOperation()) ||
				((lhs instanceof FunctionExpr) && "min_cardinality".equals(((FunctionExpr) lhs).getFunction()))) {
				Condition cond = TefkatFactory.eINSTANCE.createCondition();
				cond.setRelation("boolean");
				if (null != lhs) {
				cond.getArg().add(lhs);
				}
				
				term = cond;
				} else {
				throw new antlr.SemanticException("Expected an operation call: " + lhs, getFilename(), getMarkLine(), getMarkColumn());
				}
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
			if (null != term) {
			tgts.add(term);
			
			eChar = getEndChar();
			reportMatch(term, sChar, eChar);
			}
			
		}
	}
	
	public final TrackingUse  trackingUse(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		TrackingUse use = null;
		
		Token  linking = null;
		
		String tname;
		Map featureMap = null;
		int sChar = -1, eChar = -1;
		
		
		linking = LT(1);
		match(LITERAL_LINKING);
		tname=tname();
		match(LITERAL_WITH);
		if ( inputState.guessing==0 ) {
			
				sChar = getStartChar(linking);
			use = TefkatFactory.eINSTANCE.createTrackingUse();
			use.setTrackingName(tname);
			EClassifier tracking = ModelUtils.findClassifierByName(trackingMap, tname);
			if (null == tracking) {
			reportWarning("Undefined tracking class: " + tname, getMarkLine(), getMarkColumn());
			} else if (!(tracking instanceof EClass)) {
			String type = (tracking instanceof EDataType) ? "an EDataType" :
			(tracking instanceof EEnum) ? "an EEnum" :
			"a " + tracking.getClass().getName();
			reportWarning("Expected an EClass: " + tname + ", found " + type, getMarkLine(), getMarkColumn());
			} else {
			use.setTracking((EClass) tracking);
			}
			featureMap = use.getFeatures().map();
			
		}
		featureMaps(scope, featureMap, terms);
		if ( inputState.guessing==0 ) {
			eChar = getEndChar(); reportMatch(use, sChar, eChar);
		}
		return use;
	}
	
	public final void featureMaps(
		VarScope scope, Map featureMap, List terms
	) throws RecognitionException, TokenStreamException {
		
		
		
		
		try { // for error handling
			featureMap(scope, featureMap, terms);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				
			} else {
				throw ex;
			}
		}
		{
		_loop222:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				try { // for error handling
					featureMap(scope, featureMap, terms);
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						
						reportError(ex);
						
					} else {
						throw ex;
					}
				}
			}
			else {
				break _loop222;
			}
			
		} while (true);
		}
	}
	
	public final void featureMap(
		VarScope scope, Map featureMap, List terms
	) throws RecognitionException, TokenStreamException {
		
		
		String fname;
		Expression expr;
		
		
		fname=fname();
		match(ASSIGN);
		expr=expr(scope, terms);
		if ( inputState.guessing==0 ) {
			
			featureMap.put(fname, expr);
			
		}
	}
	
	public final String  fname() throws RecognitionException, TokenStreamException {
		String name = null;
		
		Token  id = null;
		
		id = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			name = id.getText();
			setMark(id);
			
		}
		return name;
	}
	
	public final List  exprs(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		List list = new ArrayList();
		
		
		Expression expr;
		
		
		try { // for error handling
			expr=expr(scope, terms);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				
				reportError(ex);
				expr = null;
				
			} else {
				throw ex;
			}
		}
		if ( inputState.guessing==0 ) {
			
			if (null != expr) {
			list.add(expr);
			}
			
		}
		{
		_loop227:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				try { // for error handling
					expr=expr(scope, terms);
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						
						reportError(ex);
						expr = null;
						
					} else {
						throw ex;
					}
				}
				if ( inputState.guessing==0 ) {
					
					if (null != expr) {
					list.add(expr);
					}
					
				}
			}
			else {
				break _loop227;
			}
			
		} while (true);
		}
		return list;
	}
	
	public final Expression  term(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Expression expr;
		
		Token  op = null;
		
		Expression rhs = null;
		
		
		expr=factor(scope, terms);
		{
		switch ( LA(1)) {
		case MULOP:
		{
			op = LT(1);
			match(MULOP);
			rhs=term(scope, terms);
			if ( inputState.guessing==0 ) {
				
				FunctionExpr fexpr = TefkatFactory.eINSTANCE.createFunctionExpr();
				fexpr.setFunction(op.getText());
				List args = fexpr.getArg();
				if (null != expr) {
				args.add(expr);
				}
				if (rhs != null) {
				args.add(rhs);
				}
				expr = fexpr;
				
			}
			break;
		}
		case LANGLE:
		case RANGLE:
		case RBRACK:
		case RSQUARE:
		case COMMA:
		case SEMI:
		case ASSIGN:
		case RELOP:
		case ADDOP:
		case LITERAL_MAKE:
		case LITERAL_SET:
		case LITERAL_AND:
		case LITERAL_OR:
		case LITERAL_ENDIF:
		case LITERAL_THEN:
		case LITERAL_ELSEIF:
		case LITERAL_ELSE:
		case LITERAL_BEFORE:
		case LITERAL_IN:
		case LITERAL_LINKING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return expr;
	}
	
	public final FunctionExpr  functionCall(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		FunctionExpr expr = null;
		
		
		String fname;
		List args;
		
		
		fname=fname();
		args=actuals(scope, terms);
		if ( inputState.guessing==0 ) {
			
			expr = TefkatFactory.eINSTANCE.createFunctionExpr();
			expr.setFunction(fname);
			if ("map".equals(fname)) {
			if (args.size() != 2) {
			throw new antlr.SemanticException("Function 'map' requires two arguments", getFilename(), getMarkLine(), getMarkColumn());
			}
			if (!(args.get(0) instanceof StringConstant)) {
			throw new antlr.SemanticException("First arg to 'map' must be a String literal", getFilename(), getMarkLine(), getMarkColumn());
			}
			String mapName = ((StringConstant) args.get(0)).getRepresentation();
			DataMap dataMap = (DataMap) mapMap.get(mapName);
			if (null == dataMap) {
			throw new antlr.SemanticException("Unknown map name", getFilename(), getMarkLine(), getMarkColumn());
			}
			InstanceRef ref = TefkatFactory.eINSTANCE.createInstanceRef();
			ref.setObject(dataMap);
			args.set(0, ref);
			}
			List list = expr.getArg();
			list.addAll(args);
			
		}
		return expr;
	}
	
	public final Expression  feature(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		Expression expr = null;
		
		Token  id = null;
		Token  d = null;
		
		{
		switch ( LA(1)) {
		case ID:
		{
			id = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
				SimpleExpr sc = TefkatFactory.eINSTANCE.createStringConstant();
				sc.setRepresentation(id.getText());
				expr = sc;
				
				setMark(id);
				
			}
			break;
		}
		case DOLLAR:
		{
			d = LT(1);
			match(DOLLAR);
			expr=factor(scope, terms);
			if ( inputState.guessing==0 ) {
				
				setMark(d);
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return expr;
	}
	
	public final Var  vardecl(
		VarScope vs
	) throws RecognitionException, TokenStreamException {
		Var var = null;
		
		Token  name = null;
		
		name = LT(1);
		match(ID);
		if ( inputState.guessing==0 ) {
			
			var = TefkatFactory.eINSTANCE.createVar();
			var.setName(name.getText());
			var.setScope(vs);
			
		}
		return var;
	}
	
	public final String  stringlit() throws RecognitionException, TokenStreamException {
		String value = null;
		
		Token  s = null;
		
		s = LT(1);
		match(STRING);
		if ( inputState.guessing==0 ) {
			
			value = s.getText().substring(1, s.getText().length()-1);
			
		}
		return value;
	}
	
	public final Expression  numberlit() throws RecognitionException, TokenStreamException {
		Expression expr = null;
		
		Token  a = null;
		Token  i = null;
		Token  r = null;
		
		{
		switch ( LA(1)) {
		case ADDOP:
		{
			a = LT(1);
			match(ADDOP);
			break;
		}
		case INT:
		case REAL:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case INT:
		{
			i = LT(1);
			match(INT);
			if ( inputState.guessing==0 ) {
				
				expr = TefkatFactory.eINSTANCE.createIntConstant();
				((SimpleExpr) expr).setRepresentation((a != null ? a.getText() : "") + i.getText());
				
			}
			break;
		}
		case REAL:
		{
			r = LT(1);
			match(REAL);
			if ( inputState.guessing==0 ) {
				
				expr = TefkatFactory.eINSTANCE.createRealConstant();
				((SimpleExpr) expr).setRepresentation((a != null ? a.getText() : "") + r.getText());
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return expr;
	}
	
	public final String  booleanlit() throws RecognitionException, TokenStreamException {
		String value = null;
		
		
		{
		switch ( LA(1)) {
		case LITERAL_true:
		{
			match(LITERAL_true);
			if ( inputState.guessing==0 ) {
				return "true";
			}
			break;
		}
		case LITERAL_false:
		{
			match(LITERAL_false);
			if ( inputState.guessing==0 ) {
				return "false";
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return value;
	}
	
	public final CollectionExpr  collectionlit(
		VarScope scope, List terms
	) throws RecognitionException, TokenStreamException {
		CollectionExpr collection = null;
		
		
		List exprs;
		
		
		match(LSQUARE);
		if ( inputState.guessing==0 ) {
			
			collection = TefkatFactory.eINSTANCE.createCollectionExpr();
			
		}
		{
		switch ( LA(1)) {
		case UNDERSCORE:
		case ID:
		case ANON_ID:
		case STRING:
		case INT:
		case REAL:
		case LANGLE:
		case LBRACK:
		case LSQUARE:
		case ADDOP:
		case LITERAL_true:
		case LITERAL_false:
		{
			exprs=exprs(scope, terms);
			if ( inputState.guessing==0 ) {
				
				collection.getArg().addAll(exprs);
				
			}
			break;
		}
		case RSQUARE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RSQUARE);
		return collection;
	}
	
	public final void featureVals(
		VarScope scope, Var var, boolean isExactly, List terms, List params, Expression feature, int sChar
	) throws RecognitionException, TokenStreamException {
		
		
		featureVal(scope, var, isExactly, terms, params, feature, sChar);
		{
		_loop276:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				featureVal(scope, var, isExactly, terms, params, feature.copy(), sChar);
			}
			else {
				break _loop276;
			}
			
		} while (true);
		}
	}
	
	public final void featureVal(
		VarScope scope, Var var, boolean isExactly, List terms, List params, Expression feature, int sChar
	) throws RecognitionException, TokenStreamException {
		
		
		Expression expr = null;
		Var objVar;
		int eChar = -1;
		
		
		{
		boolean synPredMatched283 = false;
		if (((_tokenSet_5.member(LA(1))))) {
			int _m283 = mark();
			synPredMatched283 = true;
			inputState.guessing++;
			try {
				{
				{
				switch ( LA(1)) {
				case LITERAL_EXACT:
				{
					match(LITERAL_EXACT);
					break;
				}
				case LITERAL_DYNAMIC:
				{
					match(LITERAL_DYNAMIC);
					break;
				}
				case UNDERSCORE:
				case ID:
				case FQID:
				case CARET:
				case DOLLAR:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case DOLLAR:
				{
					match(DOLLAR);
					break;
				}
				case UNDERSCORE:
				{
					match(UNDERSCORE);
					break;
				}
				case CARET:
				{
					match(CARET);
					break;
				}
				case FQID:
				{
					match(FQID);
					break;
				}
				case ID:
				{
					match(ID);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case AT:
				{
					match(AT);
					match(ID);
					break;
				}
				case UNDERSCORE:
				case ID:
				case ANON_ID:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				vname();
				}
			}
			catch (RecognitionException pe) {
				synPredMatched283 = false;
			}
			rewind(_m283);
inputState.guessing--;
		}
		if ( synPredMatched283 ) {
			objVar=makeObject(scope, null, isExactly, terms, params);
			if ( inputState.guessing==0 ) {
				
				VarUse varUse = TefkatFactory.eINSTANCE.createVarUse();
				varUse.setVar(objVar);
				expr = varUse;
				
			}
		}
		else if ((_tokenSet_6.member(LA(1)))) {
			expr=expr(scope, terms);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		if ( inputState.guessing==0 ) {
			
			VarUse varUse = TefkatFactory.eINSTANCE.createVarUse();
			varUse.setVar(var);
			
			FeatureExpr fe = TefkatFactory.eINSTANCE.createFeatureExpr();
			fe.setFeature(feature);
			fe.getArg().add(varUse);
			
			Condition cond = TefkatFactory.eINSTANCE.createCondition();
			cond.setRelation("=");
			List args = cond.getArg();
			args.add(fe);
			args.add(expr);
			
			terms.add(cond);
			
			eChar = getEndChar();
			reportMatch(cond, sChar, eChar);
			
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"an underscore",
		"ESCAPEDWS",
		"ALPHA",
		"DIGIT",
		"HEX",
		"HEX_FOUR",
		"SCHEME",
		"an identifier",
		"an anonymous identifier",
		"an absolute URI (must start with a scheme followed by a colon; see RFC 2396)",
		"BOOLEAN",
		"ID_OR_IMPORT_OR_OBJREF",
		"a fully qualified identifier",
		"a double-quoted string",
		"ESCAPE",
		"an integer",
		"a real",
		"REAL_OR_INT",
		"a caret symbol '^'",
		"an @ symbol",
		"a left angle bracket '<'",
		"a right angle bracket '>'",
		"a left bracket '('",
		"a right bracket ')'",
		"a left brace '{'",
		"a right brace '}'",
		"a left square bracket '['",
		"a right square bracket ']'",
		"a comma",
		"a period",
		"an arrow",
		"a colon",
		"a semicolon",
		"an '='",
		"a relational operator",
		"a '+' or '-'",
		"a '/' or '*'",
		"an exclamation mark (!)",
		"a dollar sign ($)",
		"a hash/pound sign (#)",
		"whitespace",
		"a single-line comment",
		"a multi-line comment",
		"\"QUERY\"",
		"\"TRANSFORMATION\"",
		"\"EXTENDS\"",
		"\"CLASS\"",
		"\"MAP\"",
		"\"IMPORT\"",
		"\"NAMESPACE\"",
		"\"PATTERN\"",
		"\"FORALL\"",
		"\"WHERE\"",
		"\"TEMPLATE\"",
		"\"ABSTRACT\"",
		"\"RULE\"",
		"\"MAKE\"",
		"\"SET\"",
		"\"OVERRIDES\"",
		"\"SUPERSEDES\"",
		"\"EXACT\"",
		"\"DYNAMIC\"",
		"\"AND\"",
		"\"OR\"",
		"\"IF\"",
		"\"ENDIF\"",
		"\"THEN\"",
		"\"ELSEIF\"",
		"\"ELSE\"",
		"\"NOT\"",
		"\"LINKS\"",
		"\"UNDEF\"",
		"\"BEFORE\"",
		"\"IN\"",
		"\"FROM\"",
		"\"LINKING\"",
		"\"WITH\"",
		"\"true\"",
		"\"false\""
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 1043709213643112448L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 21039104L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 4948965922832L, 393219L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 550848036864L, 393216L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 16783376L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 4398067550224L, 3L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 550915151888L, 393216L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 3458766711224801296L, 45548L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 2305843084644057088L, 33184L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	
	}
