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
 *
 */

header {
package tefkat.model.parser;
}

{
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
 
import tefkat.model.parser.TefkatToken;
}

/**
 * Lexer for an SQL-like QVT language.
 *
 * @author michael lawley
 * @date 1 April 2003
 */
class TefkatLexer extends Lexer;

/*
 * Need to worry about overlapping prefixes in lex tokens and
 * set k to 1 greater than the longest common prefix.
 *
 * Need to worry about testLiterals in case the ID rule should
 * check for parser literals (eg "RULE")
 */

options {
        k=3;
        testLiterals=true;
        charVocabulary='\3'..'\377';
}
{

        {
            tokenObjectClass = TefkatToken.class;
        }
    
        public void setTokenObjectClass(String cl) {
            return;
        }
        
        int offset = 0;
        public void consume() throws CharStreamException {
            super.consume();
            if (inputState.guessing == 0) {
                offset++;
            }
        }

        protected Token makeToken(int t) {
            Token tok = super.makeToken(t);
            if (tok instanceof TefkatToken) {
                TefkatToken tefTok = (TefkatToken) tok;
                tefTok.setOffset(offset);
            }
            return tok;
        }

        protected String unicode(String hexSeq) {
            int codePoint = Integer.parseInt(hexSeq, 16);
            return new String(Character.toChars(codePoint));
        }
}

protected
UNDERSCORE
options {
    paraphrase = "an underscore";
}        :       '_'        ;

protected
ESCAPEDWS        :       '\\'
                (        ' '        { $setText(" ");        }
                |        '\n'        { $setText("\n");        }
                |        '\r'        { $setText("\r");        }
                |        '\t'        { $setText("\t");        }
                )
        ;
protected
ALPHA        :       ( 'a'..'z' | 'A'..'Z' | ESCAPEDWS )
        ;
protected
DIGIT        :       ( '0'..'9' )
        ;
protected
HEX          :       ( '0'..'9' | 'a'..'f' | 'A'..'F' )
        ;
protected
HEX_FOUR returns [String s = null]
             :       HEX HEX HEX HEX { s = $getText; }
        ;
protected
SCHEME       :       ('a'..'z' | 'A'..'Z') ('a'..'z' | 'A'..'Z' | '0'..'9' | '+' | '-' | '.')* ':'
        ;

protected
ID
options {
    paraphrase = "an identifier";
}        :       ALPHA
                ( ALPHA | DIGIT | UNDERSCORE )*
        ;
protected
ANON_ID
options {
    paraphrase = "an anonymous identifier";
}        :       UNDERSCORE
                ( ALPHA | DIGIT | UNDERSCORE )+
        ;

protected
URITOK
options {
    paraphrase = "an absolute URI (must start with a scheme followed by a colon; see RFC 2396)";
}        :       SCHEME
                ('-' | '#' | ':' | ';' | '?' | '@' | '&' | '=' | '+' | '$' | ',' | '/' | '.' | '_' | '~' | '*' | '\'' | '(' | ')' | '%' | '0'..'9' | 'a'..'z' | 'A'..'Z')+
        ;
protected
BOOLEAN
        :       "TRUE"
        |        "FALSE"
        ;

/*
 * This works around an ANTLR bug where a valid ID such as "hile"
 * would be rejected since the lexer would try to match a URITOK.
 */
ID_OR_IMPORT_OR_OBJREF
        :       (
                        (URITOK) => URITOK { $setType(URITOK); }
                |
                        (BOOLEAN) => BOOLEAN { $setType(BOOLEAN); }
                |
                        ANON_ID { $setType(ANON_ID); }
                |
                        UNDERSCORE { $setType(UNDERSCORE); }
                |
                        ID { $setType(ID); }
                )
        ;
        
FQID
options {
        paraphrase = "a fully qualified identifier";
}       :       ("::" ID)+
        ;

STRING
options {
    paraphrase = "a double-quoted string";
}        :       '"' ( ESCAPE | ~('"' | '\\') )* '"'
        ;

protected
ESCAPE
{String h1;}    :       '\\'
                (        'n'        { $setText("\n"); }
                |        'r'        { $setText("\r"); }
                |        't'        { $setText("\t"); }
                |        '"'        { $setText("\""); }
                |        '\\'       { $setText("\\"); }
                |        'u'! h1=HEX_FOUR
                                    { $setText(unicode(h1)); }
                )
        ;


protected
INT
options {
    paraphrase = "an integer";
}        :       (DIGIT)+
        ;
protected
REAL
options {
    paraphrase = "a real";
}        :       INT PERIOD (DIGIT)+
        ;

REAL_OR_INT
        :       (INT PERIOD) => REAL { $setType(REAL); }
        |        INT { $setType(INT); }
        ;

CARET
options {
    paraphrase = "a caret symbol '^'";
}        :       '^'       ;
AT
options {
    paraphrase = "an @ symbol";
}        :       '@'        ;
LANGLE
options {
    paraphrase = "a left angle bracket '<'";
}        :       '<'        ;
RANGLE
options {
    paraphrase = "a right angle bracket '>'";
}        :       '>'        ;
LBRACK
options {
    paraphrase = "a left bracket '('";
}        :       '('        ;
RBRACK
options {
    paraphrase = "a right bracket ')'";
}        :       ')'        ;
LBRACE
options {
    paraphrase = "a left brace '{'";
}        :       '{'        ;
RBRACE
options {
    paraphrase = "a right brace '}'";
}        :       '}'        ;
LSQUARE
options {
    paraphrase = "a left square bracket '['";
}        :       '['        ;
RSQUARE
options {
    paraphrase = "a right square bracket ']'";
}        :       ']'        ;
COMMA
options {
    paraphrase = "a comma";
}        :       ','        ;
PERIOD
options {
    paraphrase = "a period";
}        :       '.'        ;
ARROW
options {
    paraphrase = "an arrow";
}        :       "->"        ;
COLON
options {
    paraphrase = "a colon";
}        :       ':'        ;
SEMI
options {
    paraphrase = "a semicolon";
}        :       ';'        ;
ASSIGN
options {
    paraphrase = "an '='";
}        :       '='        ;
RELOP
options {
    paraphrase = "a relational operator";
}        :       ( "!=" | "<=" | ">=" )        ;
ADDOP
options {
    paraphrase = "a '+' or '-'";
}        :       ( '+' | '-' )        ;
MULOP
options {
    paraphrase = "a '/' or '*'";
}        :       {LA(2) != '/' && LA(2) != '*'}? ( '/' | '*' )        ;
BANG
options {
    paraphrase = "an exclamation mark (!)";
}        :       '!'        ;
DOLLAR
options {
    paraphrase = "a dollar sign ($)";
}        :       '$'        ;
HASH
options {
    paraphrase = "a hash/pound sign (#)";
}        :       '#'        ;

WS
options {
    paraphrase = "whitespace";
}        :       (        ' '
                |        '\t'
                |        '\n' { newline(); }
                |        '\r'
                )+ // { $setType(Token.SKIP); }
                ;

SL_COMMENT
options {
    paraphrase = "a single-line comment";
}        :       "//" (~'\n')* '\n'! { newline(); }
        ;

ML_COMMENT
options {
    paraphrase = "a multi-line comment";
}
    :    "/*"
         (  { LA(2)!='/' }? '*'
         | '\n' { newline(); }
         | ~('*'|'\n')
         )*
         "*/"
    ;

{        
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
}

/**
 * Parser for an SQL-like QVT language.
 *
 * @author michael lawley
 * @date 1 April 2003
 */
class TefkatParser extends Parser;
options {
    defaultErrorHandler = false;
}
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
    
    private void definePackage(Transformation t, String namespace, String uriString) throws antlr.SemanticException {
        final List packages = new ArrayList(1);
        final ResourceSet rs = t.eResource().getResourceSet();
        final EPackage.Registry registry = rs.getPackageRegistry();
        final EPackage pkg = registry.getEPackage(uriString);
        if (null != pkg) {
            packages.add(pkg);
        } else {
        	final Resource res = rs.getResource(URI.createURI(uriString), true);
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
}

query returns [Query q = null;] {
        Var srcExtent = null;
        SourceTerm stmt = null;
}
	:	tok:"QUERY" {
			q = TefkatFactory.eINSTANCE.createQuery();
// doesn't need to go in a resource...
//            resource.getContents().add(q);
			annotate(q, tok);
        }
//        (name:ID SEMI {
//        	q.setName(name.getText());
//        })?
        
        (stmt = queryStatement[q, srcExtent] {
        	q.setTerm(stmt);
        })?
	;

queryStatement[Query q, Var srcExtent] returns [SourceTerm result = null] {
	AndTerm container = TefkatFactory.eINSTANCE.createAndTerm();
	List terms = container.getTerm();
}
	:
    (patternDefn[q, srcExtent])*
	(
        conjunct[q, terms]
        {
          	resolvePatternReferences(q);
        	if (1 == terms.size()) {
        		result = (SourceTerm) terms.remove(0);
        	} else {
        		result = container;
        	}
        	result.setContext(srcExtent);
        }
        SEMI
    )?
;

transformation returns [Transformation t = null;] {
        Var srcExtent = null, tgtExtent = null;
        int sChar = -1, eChar = -1;
}
        :       tok:"TRANSFORMATION" {
                    t = TefkatFactory.eINSTANCE.createTransformation();
//                    org.eclipse.emf.ecore.xml.type.AnyType any = 
//                            XMLTypeFactory.eINSTANCE.createAnyType();
//                    resource.getEObjectToExtensionMap().put(t, any);
                    annotate(t, tok);
                }
                name:ID {
                    t.setName(name.getText());
                    resource.getContents().add(t);

                    final String pkgURI = String.valueOf(resource.getURI());
                    ePackage = EcoreFactory.eINSTANCE.createEPackage();
                    ePackage.setNsURI(pkgURI);
                    ePackage.setName(t.getName());
                    resource.getContents().add(0, ePackage);
                    final EPackage.Registry registry = resource.getResourceSet().getPackageRegistry();
                    registry.put(pkgURI, ePackage);
                        sChar = getStartChar(LT(0));
                }
                (
                    // for backwards compatability
                    formals[t] {
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
                |
                    colon:COLON {
                        sChar = getEndChar(colon);
                    }
                    srcExtents = vardecls[t] {
                        srcExtent = (Var) srcExtents.get(0);
                    }
                    ARROW
                    tgtExtents = vardecls[t] {
                            tgtExtent = (Var) tgtExtents.get(0);
                    }
                )
                ("EXTENDS" transformationExtends[t])? {
                	eChar = getStartChar(LT(0));
                }
                (
                    body[t, srcExtent, tgtExtent] {
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
                )* {
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
                EOF
        ;
        exception
        catch [RecognitionException ex] {
            reportError(ex);
        }

/*
    Need to allow equation of Extent Vars
    
    TRANSFORMATION foo: a -> b
    EXTENDS uri (a = in, c = out),
            uri2 (out = x, b = y)

    or

    TRANSFORMATION foo: a -> b
    EXTENDS uri (a, c),
            uri2 (c, b)
*/
transformationExtends[Transformation t] {
        List vars = null;
}       :       uri: URITOK
                LBRACK vars = vardecls[t] RBRACK {
                }
                (COMMA transformationExtends[t])?
        ;

formals[VarScope vs]
        :       LBRACK 
                ( vardecls[vs] )?
                RBRACK
        ;

body[Transformation t, Var srcExtent, Var tgtExtent] {
        int sChar = -1, eChar = -1;
}
        :   {
        	sChar = getStartChar(LT(0));
        }       
        (   importDecl[t]
        |   namespaceDecl[t]
        |   classDecl[t]
        |   map
        |   patternDefn[t, srcExtent]
        |   templateDefn[t, tgtExtent]
        |   trule[t, srcExtent, tgtExtent]
        )
        ;
        exception
        catch [RecognitionException ex] {
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
        }

/*
 * Poor-man's HUTN for defining local classes for use as tracking relations.
 */

classDecl[Transformation t] {
        final Resource res = t.eResource();
        EClass eClass = null;
        EClassifier type = null;
        boolean multiValued = false;
        int csChar = -1, ceChar = -1;
        int fsChar = -1, feChar = -1;
}
        :       c:"CLASS" id:ID {
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
                ( "EXTENDS" superClasses[eClass] )?
                (
                    LBRACE
                    (
                        { fsChar = getStartChar(); multiValued = false; }
                        type = simpleTypeLiteral[true]
                        (LBRACE RBRACE { multiValued = true; })?
                        ref:ID fsemi:SEMI {
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
                    )*
                    RBRACE
                )?
                semi:SEMI
                {
                    ceChar = getEndChar(semi);
                    reportMatch(eClass, csChar, ceChar);
                }
        ;

superClasses[EClass eClass] {
        EClassifier superEClass;
}
        :       superEClass = simpleTypeLiteral[true] {
                    if (!(superEClass instanceof EClass)) {
                        String type = (superEClass instanceof EDataType) ? "an EDataType" :
                                      (superEClass instanceof EEnum) ? "an EEnum" :
                                      "a " + superEClass.getClass().getName();
                        throw new antlr.SemanticException("Expected an EClass: " + superEClass + ", found " + type, getFilename(), getMarkLine(), getMarkColumn());
                    }
                    eClass.getESuperTypes().add((EClass) superEClass);
                }
                ( COMMA superClasses[eClass] )?
        ;

map {
        DataMap dataMap = null;
        int sChar = -1, eChar = -1;
}
        :       map:"MAP"
                id:ID {
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
                LBRACE
                (
                    map_pairs[dataMap.getValue()] {
                    }
                )?
                RBRACE
                semi:SEMI
                {
                    eChar = getEndChar(semi);
                    reportMatch(dataMap, sChar, eChar);
                }
        ;

map_pairs[EMap dataMap]
        :       map_pair[dataMap]
                (
                    COMMA
                    map_pair[dataMap]
                )*
        ;

map_pair[EMap dataMap] {
        Object keyObj, valueObj;
}
        :       keyObj = map_value
                COLON
                valueObj = literal[null, null] {
                    dataMap.put(keyObj, valueObj);
                }
        ;

map_value returns [Object obj = null] {
        Expression expr;
}
        :       expr = literal[null, null] {
                    if (expr instanceof SimpleExpr) {
                        obj = ((SimpleExpr) expr).getRepresentation();
                    } else {
                        throw new antlr.SemanticException("Map values must be simple literals, not: " + expr, getFilename(), getMarkLine(), getMarkColumn());
                    }
                }
        ;

importDecl[Transformation t]
        :       "IMPORT" uri:URITOK {
        	        setMark(uri);
        	        
                    String uriStr = uri.getText();
                    NamespaceDeclaration nsd = TefkatFactory.eINSTANCE.createNamespaceDeclaration();
                    nsd.setURI(uri.getText());
                    t.getNamespaceDeclarations().add(nsd);

                    definePackage(t, null, uriStr);
                    reportWarning("IMPORT is deprecated, use NAMESPACE instead", getMarkLine(), getMarkColumn());
                }
        ;

namespaceDecl[Transformation t] {
        String name = null;
}
        :       "NAMESPACE" (id:ID {name = id.getText();})? uri:URITOK {
        	        setMark(uri);

                    String uriStr = uri.getText();
                    NamespaceDeclaration nsd = TefkatFactory.eINSTANCE.createNamespaceDeclaration();
                    nsd.setPrefix(name);
                    nsd.setURI(uri.getText());
                    t.getNamespaceDeclarations().add(nsd);

                    definePackage(t, name, uriStr);
                }
        ;


tname returns [String name = null]
        :       id: ID {
                    name = id.getText();
                    setMark(id);
                }
        ;

patternDefn[PatternScope t, Var srcExtent] {
        PatternDefn pd = null;
        String name;
        String fullName = null;
        AndTerm conjunct = null;
        int sChar = -1, eChar = -1;
        int sConjChar = -1;
}
        :       pat:"PATTERN" {
                    sChar = getStartChar(pat);
                    pd = TefkatFactory.eINSTANCE.createPatternDefn();
                    annotate(pd, pat);
                    pd.setSource(true);
                    pd.setPatternScope(t);
                    conjunct = TefkatFactory.eINSTANCE.createAndTerm();
                    // conjunct.setContext(srcExtent);
                    pd.setTerm(conjunct);
                }
                name = pname
                formals[pd] {
                    // mark all Vars from the formals as parameter vars
                    pd.getParameterVar().addAll(pd.getVars());
                    fullName = name + "/" + pd.getParameterVar().size();
                    pd.setName(fullName);
                }
                ( forall:"FORALL" ranges[pd, srcExtent, conjunct.getTerm(), false] {
                    sConjChar = getStartChar(forall);
                })?
                ( where:"WHERE" conjunct[pd, conjunct.getTerm()] {
                    if (-1 == sConjChar) {
                        sConjChar = getStartChar(where);
                    }
                })?
                semi:SEMI
                {
                    eChar = getEndChar(semi);
                    reportMatch(conjunct, sConjChar, eChar);
                    reportMatch(pd, sChar, eChar);
                    
                    if (patMap.containsKey(fullName)) {
                        throw new antlr.SemanticException("Duplicate definition of pattern '" + fullName + "' found.", getFilename(), getMarkLine(), getMarkColumn());
                    }
                    patMap.put(fullName, pd);
                }
        ;

pname returns [String name = null]
        :       id: ID {
                    name = id.getText();
                }
        ;

templateDefn[Transformation t, Var tgtExtent] {
        PatternDefn pd = null;
        String name;
        AndTerm conjunct = null;
        int sChar = -1, eChar = -1;
        int sConjChar = -1;
}
        :       pat:"TEMPLATE" {
                    sChar = getStartChar(pat);
                    pd = TefkatFactory.eINSTANCE.createPatternDefn();
                    annotate(pd, pat);
                    pd.setSource(false);
                    pd.setPatternScope(t);
                    conjunct = TefkatFactory.eINSTANCE.createAndTerm();
                    // conjunct.setContext(tgtExtent);
                    pd.setTerm(conjunct);
                }
                name = pname {
                    pd.setName(name);
                }
                formals[pd] {
                    // mark all Vars from the formals as parameter vars
                    pd.getParameterVar().addAll(pd.getVars());
                    String fullName = name + "/" + pd.getParameterVar().size();
                    pd.setName(fullName);
                    if (patMap.containsKey(fullName)) {
                        throw new antlr.SemanticException("Duplicate definition of pattern '" + fullName + "' found.", getFilename(), getMarkLine(), getMarkColumn());
                    }
                    patMap.put(fullName, pd);
                }
                { sConjChar = getStartChar(); }
                targetClauses[pd, tgtExtent, conjunct.getTerm(), Collections.EMPTY_LIST]
                semi:SEMI
                {
                    eChar = getEndChar(semi);
                    reportMatch(conjunct, sConjChar, eChar);
                    reportMatch(pd, sChar, eChar);
                }
        ;

trule[Transformation t, Var srcExtent, Var tgtExtent] {
        TRule trule = null;
        String name;
        List params = null;
        AndTerm conjunct = null;
        boolean isAbstract = false;
        int sChar = -1, eChar = -1;
        int sConjChar = -1, eConjChar = -1;
}
        :       (abs:"ABSTRACT" { isAbstract = true; sChar = getStartChar(abs); })?
                tok:"RULE" {
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
                name = rname {
                    trule.setName(name);
                    ruleMap.put(name, trule);
                }
                (
                    formals[trule] {
                        publicVarMap.put(trule, new ArrayList(trule.getVars()));
                    }
                )?
                ( relatedRules[trule] )*
                (
                    forall:"FORALL" params = ranges[trule, srcExtent, conjunct.getTerm(), false] {
                    	sConjChar = getStartChar(forall);
                    }
                |
                    { params = EMPTY_PARAMS; }
                )
                ( where:"WHERE" conjunct[trule, conjunct.getTerm()] {
                    if (-1 == sConjChar) {
                        sConjChar = getStartChar(where);
                    }
                })?
                { eConjChar = getStartChar(); }
                targetClauses[trule, tgtExtent, trule.getTgt(), params]
                semi:SEMI
                {
                    eChar = getStartChar(semi);
                    if (-1 != sConjChar) {
                        if (-1 == eConjChar) {
                                eConjChar = eChar;
                        }
                        reportMatch(conjunct, sConjChar, eConjChar);
                    }
                    reportMatch(trule, sChar, eChar);
                }
        ;

targetClauses[VarScope scope, Var tgtExtent, List terms, List params] {
}
        :
        ( "MAKE" making[scope, tgtExtent, terms, params] )?
        ( "SET" settings[scope, tgtExtent, terms, params] )?
        ( trackingUses[scope, terms] )?
        ;

rname returns [String name = null]
        :       id: ID {
                    name = id.getText();
                }
        ;

relatedRules[TRule trule] {
        boolean xflag = false, sflag = false;
}
        :
        (        "EXTENDS" { xflag = true; }
        |        "OVERRIDES" { sflag = true; }
        |        "SUPERSEDES" { throw new antlr.SemanticException("The keyword 'SUPERSEDES' has been deprecated.", getFilename(), getMarkLine(), getMarkColumn()); }
        )
        related[trule, xflag, sflag]
        (
                COMMA related[trule, xflag, sflag]
        )*
        ;

related[TRule trule, boolean xflag, boolean sflag] {
        String rname, vname;
        List vars = new ArrayList();
}
        :
        rname = rname {
            if (xflag) { store(extendsMap, rname, trule); }
            if (sflag) { store(supersedesMap, rname, trule); }
        }
        (
            LBRACK
            vname = vname {
                vars.add(getVar(trule, vname));
            }
                (
                    COMMA vname = vname {
                        vars.add(getVar(trule, vname));
                    }
                )*
                RBRACK {
                    if (xflag) { store(extendsVarMap, rname, vars); }
                    if (sflag) { store(supersedesVarMap, rname, vars); }
                }
        )?
        ;

/*
 * Returns a List of VarUse objects.
 * Only called from source-side contexts
 */
ranges[VarScope scope, Var context, List terms, boolean isExactly] returns [List params = new ArrayList()] {
        MofInstance range;
        Var var = null;
}
        :       range = range[scope, context, isExactly, terms] {
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
                (objectBody[scope, var, isExactly, terms, null])?
                (
                    COMMA
                    range = range[scope, context, isExactly, terms] {
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
                    (objectBody[scope, var, isExactly, terms, null])?
                )*
        ;

range[VarScope scope, Var outerContext, boolean isExactly, List terms] returns [MofInstance i = null] {
        Var context;
        Expression type;
        String name;
        Var var = null;
        int sChar = -1, eChar = -1;
}
        :       { sChar = getStartChar(); }
                (
                    "EXACT" { isExactly = true; }
                |
                    "DYNAMIC" { isExactly = false; }
                )?
                type = typeName[scope, terms]
                context = context[scope]
                name = vname {
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

                    eChar = getStartChar();
                    reportMatch(i, sChar, eChar);
                }
        ;
//        exception
//        catch [RecognitionException ex] {
//            reportError(ex);
//        }

context[VarScope scope] returns [Var context = null;] {
        String name;
}
        :
        (
            AT
            name = vname {
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
        )?
        ;

typeName[VarScope scope, List terms] returns [Expression expr = null] {
        String name = null;
        EClassifier type = null;
}
        :
                DOLLAR expr = factor[scope, terms]
        |
                UNDERSCORE { name = "_"; } {
                    StringConstant sc = TefkatFactory.eINSTANCE.createStringConstant();
                    sc.setRepresentation(name);
                    expr = sc;
                }
        |
                type = simpleTypeLiteral[true] {
                    InstanceRef ref = TefkatFactory.eINSTANCE.createInstanceRef();
                    ref.setObject(type);
                    expr = ref;
                }
        ;

simpleTypeLiteral[boolean resolve] returns [EClassifier type = null] {
        EObject obj;
        String name = "";
        int sChar = -1;
        int eChar = -1;
}
        :       id1: ID { name = id1.getText(); setMark(id1); sChar = getStartChar(id1); eChar = getEndChar(id1); }
                (CARET id2: ID { name += '^' + id2.getText(); eChar = getEndChar(id2); })?
                {
                    type = ModelUtils.findClassifierByName(trackingMap, name);
                    if (resolve && null == type) {
                        reportError(sChar+","+eChar+"\t"+"Cannot resolve type: " + name, sChar, eChar);
                    }
                }
        |
                (caret:CARET qual: ID { name = qual.getText() + '^'; setMark(qual); sChar = getStartChar(caret); })?
                fqid: FQID {
                    name += fqid.getText();
                    type = ModelUtils.findClassifierByName(trackingMap, name);
                    if (resolve && null == type) {
                    	if (-1 == sChar) {
                    		sChar = getStartChar(fqid);
                    	}
                        reportError("Cannot resolve fully qualified type: " + name, sChar, getEndChar(fqid));
                    }
                }
        |
                obj = objectlit {
                    type = (EClassifier) obj;
                    // objectlit throws an exception rather than return null
                }
        ;

vname returns [String name = null]
        :
                id: ID {
                    name = id.getText();
                    setMark(id);
                }
        |
                anon_id: ANON_ID {
                    name = anon_id.getText();
                    setMark(anon_id);
                }
        |
                under: UNDERSCORE {
                    setMark(under);
                }
        ;

conjunct[VarScope scope, List terms] {
        Term term;
}
        :       term = d1:disjunct[scope, terms] {
                    if (null != term) {
                        terms.add(term);
                    }
                }
                (
                    "AND" term = d2:disjunct[scope, terms] {
                        if (null != term) {
                            terms.add(term);
                        }
                    }
                )*
        ;
        exception [d1]
        catch [RecognitionException ex] {
            reportError(ex);
            term = null;
        }
        exception [d2]
        catch [RecognitionException ex] {
            reportError(ex);
            term = null;
        }

disjunct[VarScope scope, List terms] returns [Term term] {
        OrTerm oTerm = null;
        List oTerms = new ArrayList();
        Term rTerm;
        int sChar = -1, eChar = -1;
}
        :       { sChar = getStartChar(); }
                term = r1:relation[scope, oTerms]
                (
                    "OR" rTerm = r2:relation[scope, oTerms] {
                        if (null == oTerm) {
                            // first time here
                            oTerm = TefkatFactory.eINSTANCE.createOrTerm();

                            if (term instanceof AndTerm) {
                                ((AndTerm) term).getTerm().addAll(oTerms);
                                term.setCompoundTerm(oTerm);
                            } else if (null != term) {
                                AndTerm aTerm = TefkatFactory.eINSTANCE.createAndTerm();
                                aTerm.getTerm().addAll(oTerms);
                                aTerm.getTerm().add(term);
                                aTerm.setCompoundTerm(oTerm);
                            }
                            // else there was an error parsing the first term 
                            // but continue anyway

                            term = oTerm;
                        }
                        if (rTerm instanceof AndTerm) {
                            ((AndTerm) rTerm).getTerm().addAll(oTerms);
                            rTerm.setCompoundTerm(oTerm);
                        } else {
                            AndTerm aTerm = TefkatFactory.eINSTANCE.createAndTerm();
                            aTerm.getTerm().addAll(oTerms);
                            if (null != rTerm) {
                                aTerm.getTerm().add(rTerm);
                            }
                            aTerm.setCompoundTerm(oTerm);
                        }
                        oTerms.clear();
                    }
                )*
                {
                    if (oTerm != null) {
                        eChar = getStartChar();
                        reportMatch(oTerm, sChar, eChar);
                    } else {
                        terms.addAll(oTerms);
                    }
                }
        ;
        exception [r1]
        catch [RecognitionException ex] {
            reportError(ex);
            term = null;
        }
        exception [r2]
        catch [RecognitionException ex] {
            reportError(ex);
            rTerm = null;
        }

s_ifthenelse[VarScope scope] returns [IfTerm term = null] {
        int sChar = -1, eChar = -1;
}
        :       start:"IF" {
        	        sChar = getStartChar(start);
        	    }
                term = s_ite[scope]
                end:"ENDIF" {
                    eChar = getEndChar(end);
                    reportMatch(term, sChar, eChar);
                }
        ;

s_ite[VarScope scope] returns [IfTerm term = null] {
        Term condTerm = null, thenTerm = null, elseTerm = null;
        List termList = new ArrayList();
        int sChar = -1, eChar = -1;
}
        :
                conjunct[scope, termList] {
                    if (termList.size() > 1) {
                        condTerm = TefkatFactory.eINSTANCE.createAndTerm();
                        ((AndTerm) condTerm).getTerm().addAll(termList);
                    } else if (termList.size() == 1) {
                        condTerm = (Term) termList.get(0);
                    }
                    termList.clear();
                }
                (
                "THEN"
                conjunct[scope, termList] {
                    if (termList.size() > 1) {
                        thenTerm = TefkatFactory.eINSTANCE.createAndTerm();
                        ((AndTerm) thenTerm).getTerm().addAll(termList);
                    } else if (termList.size() == 1) {
                        thenTerm = (Term) termList.get(0);
                    }
                    termList.clear();
                }
                | {
                    // No THEN term is equivalent to THEN TRUE
                    // We use an empty AndTerm to implement TRUE
                    thenTerm = TefkatFactory.eINSTANCE.createAndTerm();
                }
                )
                (
                elseif:"ELSEIF" {
                	sChar = getStartChar(elseif);
                }
                elseTerm = s_ite[scope] {
                    eChar = getStartChar();
                    reportMatch(term, sChar, eChar);
                }
                |
                "ELSE"
                conjunct[scope, termList] {
                    if (termList.size() > 1) {
                        elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
                        ((AndTerm) elseTerm).getTerm().addAll(termList);
                    } else if (termList.size() == 1) {
                        elseTerm = (Term) termList.get(0);
                    }
                }
                | {
                    // No ELSE term is equivalent to ELSE TRUE
                    // We use an empty AndTerm to implement TRUE
                    elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
                }
                )
                {
                    term = TefkatFactory.eINSTANCE.createIfTerm();
                    List args = term.getTerm();
                    args.add(condTerm);
                    args.add(thenTerm);
                    args.add(elseTerm);
                }
        ;

t_ifthenelse[VarScope scope, Var tgtExtent, List params] returns [IfTerm term = null] {
        int sChar = -1, eChar = -1;
}
        :       start:"IF" {
                	sChar = getStartChar(start);
                }
                term = t_ite[scope, tgtExtent, params]
                end:"ENDIF" {
                    eChar = getEndChar(end);
                    reportMatch(term, sChar, eChar);
                }
        ;

t_ite[VarScope scope, Var tgtExtent, List params] returns [IfTerm term = null] {
        Term condTerm = null, thenTerm = null, elseTerm = null;
        List termList = new ArrayList();
        int sChar = -1, eChar = -1;
}
        :       conjunct[scope, termList] {
                    if (termList.size() > 1) {
                        condTerm = TefkatFactory.eINSTANCE.createAndTerm();
                        ((AndTerm) condTerm).getTerm().addAll(termList);
                    } else if (termList.size() == 1) {
                        condTerm = (Term) termList.get(0);
                    }
                    termList.clear();
                }
                (
                "THEN"
                targetClauses[scope, tgtExtent, termList, params] {
                    if (termList.size() > 1) {
                        thenTerm = TefkatFactory.eINSTANCE.createAndTerm();
                        ((AndTerm) thenTerm).getTerm().addAll(termList);
                    } else if (termList.size() == 1) {
                        thenTerm = (Term) termList.get(0);
                    }
                    termList.clear();
                }
                | {
                    SimpleExpr bool = TefkatFactory.eINSTANCE.createBooleanConstant();
                    bool.setRepresentation("TRUE");
                    Condition cond = TefkatFactory.eINSTANCE.createCondition();
                    cond.setRelation("boolean");
                    List args = cond.getArg();
                    args.add(bool);
                    thenTerm = cond;
                }
                )
                (
                elseif:"ELSEIF" {
                	sChar = getStartChar(elseif);
                }
                elseTerm = t_ite[scope, tgtExtent, params] {
                    eChar = getStartChar();
                    reportMatch(term, sChar, eChar);
                }
                |
                "ELSE"
                targetClauses[scope, tgtExtent, termList, params] {
                    if (termList.size() > 1) {
                        elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
                        ((AndTerm) elseTerm).getTerm().addAll(termList);
                    } else if (termList.size() == 1) {
                        elseTerm = (Term) termList.get(0);
                    }
                }
                | {
                    // No ELSE term is equivalent to ELSE TRUE
                    // We use an empty AndTerm to implement TRUE
                    elseTerm = TefkatFactory.eINSTANCE.createAndTerm();
                }
                )
                {
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
        ;


relation[VarScope scope, List terms] returns [Term term = null] {
        AndTerm aTerm = null;
        Expression lhs, rhs, path;
        Var var = null;
        String operator = null;
        Token relop = null;
        int sChar = -1, eChar = -1;
}
        :
        (       lbrack:LBRACK {
        	        sChar = getStartChar(lbrack);
                    aTerm = TefkatFactory.eINSTANCE.createAndTerm();
                    term = aTerm;
                }
                conjunct[scope, aTerm.getTerm()]
                RBRACK
        |       (("EXACT"|"DYNAMIC")? (DOLLAR|UNDERSCORE|(CARET ID)? FQID|ID (CARET ID)?) (AT ID)? vname) =>
//        | //(range) =>
                term = range[scope, null, false, terms] {
                    MofInstance inst = (MofInstance) term;
                    // NPE triggred here if range[] has failed to match
                    var = ((VarUse) inst.getInstance()).getVar();
                    Var extVar = inst.getContext();
                    if (null != extVar && tgtExtents.contains(extVar)) {
                        reportWarning("Querying a target extent, '" + scope + "', is not supported.", getMarkLine(), getMarkColumn());
                    }
                }
                (objectBody[scope, var, false, terms, null])?
        |       (BANG | "NOT") term = relation[scope, terms] {
                    NotTerm nterm = TefkatFactory.eINSTANCE.createNotTerm();
                    if (null != term) {
                        nterm.getTerm().add(term);
                    }
                    term = nterm;
                }
        |       (pname LBRACK) => term = patternUse[scope, terms]
        |       (tname "LINKS") => term = links[scope, terms]
        |       ("IF") => term = s_ifthenelse[scope]
        |       b:BOOLEAN {
                    if ("TRUE".equals(b.getText())) {
                        term = TefkatFactory.eINSTANCE.createAndTerm();
                    } else {
                        term = TefkatFactory.eINSTANCE.createOrTerm();
                    }
                }
        |       "UNDEF" lhs = factor[scope, terms] {
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
        |       lhs = expr[scope, terms]
                (
                    "BEFORE"
                    rhs = expr[scope, terms]
                    "IN"
                    path = path[scope, terms] {
                        FeatureExpr fExpr = (FeatureExpr) path;
                        MofOrder order = TefkatFactory.eINSTANCE.createMofOrder();
                        order.setLesser(lhs);
                        order.setGreater(rhs);
                        order.setInstance((Expression) fExpr.getArg().get(0));
                        order.setFeature(fExpr.getFeature());
                        
                        term = order;
                    }
                |
                    ( assop: ASSIGN {
                        operator = assop.getText();
                    }
                    | relop = relop {
                        operator = relop.getText();
                    }
                    )
                    rhs = expr[scope, terms] {
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
                )
        )       { eChar = getStartChar(); reportMatch(term, sChar, eChar); }
        ;
//        exception
//        catch [RecognitionException ex] {
//            reportError(ex);
//        }

relop returns [Token relop = null]
        :       tok1:RELOP {relop = tok1;}
        |       tok2:LANGLE {relop = tok2;}
        |       tok3:RANGLE {relop = tok3;}
        ;

making[VarScope scope, Var tgtExtent, List tgts, List params]
        :       make[scope, tgtExtent, tgts, params]
                (
                    COMMA make[scope, tgtExtent, tgts, params]
                )*
        ;

make[VarScope scope, Var tgtExtent, List tgts, List params] {
	Term term;
}
        :       (pname LBRACK) => templateUse[scope, tgtExtent, tgts]
        |       ("IF") => term = t_ifthenelse[scope, tgtExtent, params] {
                    tgts.add(term);
                }
        |       makeObject[scope, tgtExtent, true, tgts, params]
        ;

// ((null == params) => source side) => no unique and no Injection
// EMPTY_LIST == params => TEMPLATE => unique required
makeObject[VarScope scope, Var tgtExtent, boolean isExactly, List tgts, List params] returns [Var targetVar = null] {
        MofInstance makeTerm;
        int sChar = -1, eChar = -1;
}
        :       { sChar = getStartChar(); }
                makeTerm = range[scope, tgtExtent, isExactly, tgts] {
                    if (null != makeTerm) {
                        tgts.add(makeTerm);
                        targetVar = ((VarUse) makeTerm.getInstance()).getVar();
                    }
                }
                (
                    // a FROM clause is only valid on "target" side
                    {null != params}? params = unique[scope, tgtExtent, tgts, targetVar]
                |
                    // Changed to make FROM optional in TEMPLATEs - need to fix "bug" in target MofInstance

                    // YES, this really should be an IDENTITY (==/!=) test and not an equals() test!
                    {
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

                            eChar = getStartChar();
                            reportMatch(injection, sChar, eChar);
                        }
                    }
                )
                (objectBody[scope, targetVar, isExactly, tgts, params])?
                exception
                catch [RecognitionException ex] {
                    reportError(ex);
                }
        ;

templateUse[VarScope scope, Var outerContext, List tgts] {
        PatternUse useTerm;
}
        :       useTerm = patternUse[scope, tgts] {
                    if (null != useTerm) {
                        tgts.add(useTerm);
                    }
                }
        ;


unique[VarScope scope, Var outerContext, List tgts, Var targetVar] returns [List params] {
        String name;
        int sChar = -1, eChar = -1;
}
        :       from:"FROM" {
        	        sChar = getStartChar(from);
        	    }
                name = uname
                params = actuals[scope, tgts] {
                    Injection injection = TefkatFactory.eINSTANCE.createInjection();
                    injection.setName(name);
                    injection.getSources().addAll(params);
                    VarUse varUse = TefkatFactory.eINSTANCE.createVarUse();
                    varUse.setVar(targetVar);
                    injection.setTarget(varUse);
                    tgts.add(injection);

                    eChar = getStartChar();
                    reportMatch(injection, sChar, eChar);
                }
        ;

uname returns [String name = null]
        :       id: ID {
                    name = id.getText();
                }
        ;

settings[VarScope scope, Var tgtExtent, List tgts, List params]
        :       s1:setting[scope, tgtExtent, tgts, params]
                (
                    COMMA s2:setting[scope, tgtExtent, tgts, params]
                )*
        ;
        exception [s1]
        catch [RecognitionException ex] {
            reportError(ex);
        }
        exception [s2]
        catch [RecognitionException ex] {
            reportError(ex);
        }

setting[VarScope scope, Var tgtExtent, List tgts, List params] {
        Term term = null;
}
        :       setting_stmt[scope, tgts]
        |       b:BOOLEAN {
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
        |       ("IF") => term = t_ifthenelse[scope, tgtExtent, params] {
                    tgts.add(term);
                }
        ;

/**
 * Only allow stmts of form Path = Expr or Path() or Path() BEFORE Expr IN Path in clause
 */
setting_stmt[VarScope scope, List tgts] {
        Expression lhs, rhs, path;
        Term term = null;
        int sChar = -1, eChar = -1;
}
        :       { sChar = getStartChar(); }
                lhs = expr[scope, tgts]
                (
                    "BEFORE"
                    rhs = expr[scope, tgts]
                    "IN"
                    path = path[scope, tgts] {
                        FeatureExpr fExpr = (FeatureExpr) path;
                        MofOrder order = TefkatFactory.eINSTANCE.createMofOrder();
                        order.setLesser(lhs);
                        order.setGreater(rhs);
                        order.setInstance((Expression) fExpr.getArg().get(0));
                        order.setFeature(fExpr.getFeature());
                        
                        term = order;
                    }
                |
                    assop: ASSIGN {
                        if (lhs instanceof FeatureExpr) {
                            if (((FeatureExpr) lhs).isOperation()) {
                                throw new antlr.SemanticException("Cannot assign to an operation: " + lhs, getFilename(), assop.getLine(), assop.getColumn());
                            }
                        } else {
                            throw new antlr.SemanticException("LHS of an assignment must be a path: " + lhs, getFilename(), assop.getLine(), assop.getColumn());
                        }
                    }
                    rhs = expr[scope, tgts] {
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
                |
                    {
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
                )
                {
                    if (null != term) {
                        tgts.add(term);

                        eChar = getStartChar();
                        reportMatch(term, sChar, eChar);
                    }
                }
        ;
//        exception
//        catch [RecognitionException ex] {
//            reportError(ex);
//        }

trackingUses[VarScope scope, List terms] {
        TrackingUse use;
}
        :       use = t1:trackingUse[scope, terms] {
                    if (null != use) {
                        terms.add(use);
                    }
                }
                ( use = t2:trackingUse[scope, terms] {
                    if (null != use) {
                        terms.add(use);
                    }
                }
                )*
        ;
        exception [t1]
        catch [RecognitionException ex] {
            reportError(ex);
            use = null;
        }
        exception [t2]
        catch [RecognitionException ex] {
            reportError(ex);
            use = null;
        }

trackingUse[VarScope scope, List terms] returns [TrackingUse use = null] {
        String tname;
        Map featureMap = null;
        int sChar = -1, eChar = -1;
}
        :       linking:"LINKING" tname = tname "WITH" {
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
                featureMaps[scope, featureMap, terms]
                { eChar = getStartChar(); reportMatch(use, sChar, eChar); }
                ;

featureMaps[VarScope scope, Map featureMap, List terms] {
}
        :       f1:featureMap[scope, featureMap, terms]
                ( COMMA f2:featureMap[scope, featureMap, terms] )*
        ;
        exception [f1]
        catch [RecognitionException ex] {
            reportError(ex);
        }
        exception [f2]
        catch [RecognitionException ex] {
            reportError(ex);
        }

featureMap[VarScope scope, Map featureMap, List terms] {
        String fname;
        Expression expr;
}
        :       fname = fname
                ASSIGN
                expr = expr[scope, terms] {
                    featureMap.put(fname, expr);
                }
        ;

patternUse[VarScope scope, List terms] returns [PatternUse use = null] {
        String name;
        List params;
}
        :       name = pname
                params = actuals[scope, terms] {
                    use = TefkatFactory.eINSTANCE.createPatternUse();
                    use.getArg().addAll(params);
                    patUseMap.put(use, name + "/" + params.size());
                }
        ;
        
exprs[VarScope scope, List terms] returns [List list = new ArrayList()] {
        Expression expr;
}
        :       expr = e1:expr[scope, terms] {
                    if (null != expr) {
                        list.add(expr);
                    }
                }
                (
                    COMMA expr = e2:expr[scope, terms] {
                        if (null != expr) {
                            list.add(expr);
                        }
                    }
                )*
        ;
        exception [e1]
        catch [RecognitionException ex] {
            reportError(ex);
            expr = null;
        }
        exception [e2]
        catch [RecognitionException ex] {
            reportError(ex);
            expr = null;
        }

expr[VarScope scope, List terms] returns [Expression expr] {
        Expression rhs = null;
}
        :       expr = term[scope, terms]
                ( op: ADDOP rhs = expr[scope, terms] {
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
                )?
        ;

term[VarScope scope, List terms] returns [Expression expr] {
        Expression rhs = null;
}
        :       expr = factor[scope, terms]
                ( op: MULOP rhs = term[scope, terms] {
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
                )?
        ;

factor[VarScope scope, List terms] returns [Expression expr = null] {
        String vname = null;
}
        :
        (       expr = literal[scope, terms]
        |       LBRACK expr = expr[scope, terms] RBRACK
        |       ((vname | LANGLE URITOK RANGLE) (PERIOD|ARROW)) => expr = path[scope, terms]
        |       (fname LBRACK) => expr = functionCall[scope, terms]
//        |        (ID (ID | DOLLAR)) => expr = objectlit
        |       vname = vname {
                    Var var = getVarInScope(scope, vname);
                    if (var == null) {
                        var = declareVar(scope, vname, getMarkLine(), getMarkColumn());
                    }
                    VarUse vu = TefkatFactory.eINSTANCE.createVarUse();
                    vu.setVar(var);
                    expr = vu;
                }
        )
        ;
        exception
        catch [RecognitionException ex] {
            reportError(ex);
        }

functionCall[VarScope scope, List terms] returns [FunctionExpr expr = null] {
        String fname;
        List args;
}
        :       fname = fname
                args = actuals[scope, terms] {
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
        ;

fname returns [String name = null]
        :       id: ID {
                    name = id.getText();
                    setMark(id);
                }
        ;

actuals[VarScope scope, List terms] returns [List list = null]
        :       LBRACK
                (
                    RBRACK { list = new ArrayList(); }
                |
                    list = exprs[scope, terms]
                    RBRACK
                )
        ;

links[VarScope scope, List terms] returns [TrackingUse use = null] {
        String tname;
        Map featureMap = null;
}
        :       tname = tname "LINKS" {
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
                featureMaps[scope, featureMap, terms]
        ;

path[VarScope scope, List terms] returns [Expression expr = null] {
        String vname;
        EObject obj;
        FeatureExpr fe = null;
        Expression feature;
        List args = null, params = null;
}
        :       (
                    vname = vname {
                        expr = TefkatFactory.eINSTANCE.createVarUse();
                        Var var = getVarInScope(scope, vname);
                        if (null == var) {
                            var = declareVar(scope, vname, getMarkLine(), getMarkColumn());
                        }
                        ((VarUse) expr).setVar(var);
                    }
                |
                    obj = objectlit {
                        InstanceRef ref = TefkatFactory.eINSTANCE.createInstanceRef();
                        ref.setObject(obj);
                        expr = ref;
                    }
                )
                (
                    (   PERIOD
                    |   ARROW {
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
                    )
                    feature = feature[scope, terms] {
                        fe = TefkatFactory.eINSTANCE.createFeatureExpr();
                        fe.setFeature(feature);

                        args = fe.getArg();
                        args.add(expr);
                        expr = fe;
                    }
                    (
                        params = actuals[scope, terms] {
                            fe.setOperation(true);
                            args.addAll(params);
                        }
                    )?
                    (    // TODO revisit syntax
                        LBRACE RBRACE {
                            fe.setCollect(true);
                        }
                    )?
                )+
        ;

feature[VarScope scope, List terms] returns [Expression expr = null]
        :       (
                    id:ID {
                        SimpleExpr sc = TefkatFactory.eINSTANCE.createStringConstant();
                        sc.setRepresentation(id.getText());
                        expr = sc;

                        setMark(id);
                    }
                |
                    d:DOLLAR expr = factor[scope, terms] {
                        setMark(d);
                    }
                )
        ;

vardecls[VarScope vs] returns [List vars = new ArrayList()] {
        Var var;
}
        :       var = vardecl[vs] {
                    vars.add(var);
                }
                (
                    COMMA var = vardecl[vs] {
                        vars.add(var);
                    }
                )*
        ;

vardecl[VarScope vs]  returns [Var var = null]
        :       name:ID {
                    var = TefkatFactory.eINSTANCE.createVar();
                    var.setName(name.getText());
                    var.setScope(vs);
                }
        ;

/*
NOT YET IMPLEMENTED -- need a decent syntax
*/
//enumlit[VarScope scope, List terms] returns [EnumConstant enumeration = null] {
//        // EClassifier eClassifier;
//        Expression typeExpr;
//        Expression literalExpr;
//}
//        :       HASH
//                // eClassifier = simpleTypeLiteral
//                typeExpr = typeName[scope, terms]
//                PERIOD
//                literalExpr = literal[scope, terms] {
////                id: ID {
////                        if (!(eClassifier instanceof EEnum)) {
////                                throw new antlr.SemanticException("Expected an EEnum: " + eClassifier + ", but got a EClass or EDataType", getFilename(), getMarkLine(), getMarkColumn());
////                        }
////                        EEnum eenum = (EEnum) eClassifier;
////                        EEnumLiteral literal = eenum.getEEnumLiteral(id.getText());
////                        Resource res = literal.eResource();
////                        String uriFrag = res.getURIFragment(literal);
//                        enumeration = TefkatFactory.eINSTANCE.createEnumConstant();
//                        List args = enumeration.getArg();
//                        args.add(typeExpr);
//                        args.add(literalExpr);
////                        enumeration.setRepresentation(res.getURI() + uriFrag);
////                        literal.eResource().getResourceSet().getEObject(URI.createURI(enumeration.getRepresentation()), false);
////                        ref = TefkatFactory.eINSTANCE.createInstanceRef();
////                        ref.setObject(literal);
//                }
//        ;

literal[VarScope scope, List terms] returns [Expression expr = null] {
        String s = null;
        EObject obj = null;
}
        :       s = stringlit {
                    expr = TefkatFactory.eINSTANCE.createStringConstant();
                    ((SimpleExpr) expr).setRepresentation(s);
                }
        |        expr = numberlit
        |        s = booleanlit {
                    expr = TefkatFactory.eINSTANCE.createBooleanConstant();
                    ((SimpleExpr) expr).setRepresentation(s);
                }
        |        obj = objectlit {
                    InstanceRef ref = TefkatFactory.eINSTANCE.createInstanceRef();
                    ref.setObject(obj);
                    expr = ref;
                }
//        |        expr = enumlit[scope, terms]
        |        expr = collectionlit[scope, terms]
        ;

objectlit returns [EObject obj = null] {
}        :      langle:LANGLE
                tok:URITOK
                rangle:RANGLE {
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
        ;

stringlit returns [String value = null]
        :       s: STRING {
                    value = s.getText().substring(1, s.getText().length()-1);
                }
        ;

numberlit returns [Expression expr = null]
        :       (a:ADDOP)?
                (
                    i: INT {
                        expr = TefkatFactory.eINSTANCE.createIntConstant();
                        ((SimpleExpr) expr).setRepresentation((a != null ? a.getText() : "") + i.getText());
                    }
                |
                    r: REAL {
                        expr = TefkatFactory.eINSTANCE.createRealConstant();
                        ((SimpleExpr) expr).setRepresentation((a != null ? a.getText() : "") + r.getText());
                    }
                )
        ;
        
booleanlit returns [String value = null]
        :       (        "true" { return "true"; }
                |        "false" { return "false"; }
                )
        ;

collectionlit[VarScope scope, List terms] returns [CollectionExpr collection = null] {
        List exprs;
}
        :       LSQUARE {
                    collection = TefkatFactory.eINSTANCE.createCollectionExpr();
                }
                (exprs = exprs[scope, terms] {
                    collection.getArg().addAll(exprs);
                })?
                RSQUARE
        ;

objectBody[VarScope scope, Var var, boolean isExactly, List terms, List params] {
        Expression feature;
}
        :       LBRACE
                (
                    feature = feature[scope, terms]
                    COLON
                    (
                        (LSQUARE) =>        // Need to match collection Exprs specially so that we generate individual featureExprs
                        LSQUARE
                        featureVals[scope, var, isExactly, terms, params, feature]
                        RSQUARE
                    |
                        featureVal[scope, var, isExactly, terms, params, feature]
                    )
                    SEMI
                )*
                RBRACE
        ;

featureVals[VarScope scope, Var var, boolean isExactly, List terms, List params, Expression feature]
        :       featureVal[scope, var, isExactly, terms, params, feature]
                (
                    COMMA
                    featureVal[scope, var, isExactly, terms, params, feature.copy()]
                )*
        ;

featureVal[VarScope scope, Var var, boolean isExactly, List terms, List params, Expression feature] {
        Expression expr = null;
        Var objVar;
        int sChar = -1, eChar = -1;
}
        :   { sChar = getStartChar(); }
            (
                (("EXACT"|"DYNAMIC")? (DOLLAR|UNDERSCORE|CARET|FQID|ID) (AT ID)? vname) =>
                objVar = makeObject[scope, null, isExactly, terms, params] {
                    VarUse varUse = TefkatFactory.eINSTANCE.createVarUse();
                    varUse.setVar(objVar);
                    expr = varUse;
                }
            |
                expr = expr[scope, terms]
            )
            {
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
                
                eChar = getStartChar();
                reportMatch(cond, sChar, eChar);
            }
        ;


