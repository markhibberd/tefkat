/*
 * Created on 28/01/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dstc.tefkat.plugin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

/**
 * @author lawley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TefkatPartitionScanner extends RuleBasedPartitionScanner {

    public static final String MULTILINE_COMMENT = "__tefkat_multiline_comment";
    
    public static final String[] TEFKAT_PARTITION_TYPES = { MULTILINE_COMMENT };

    public TefkatPartitionScanner() {
        IToken comment = new Token(MULTILINE_COMMENT);

        List rules = new ArrayList();

        // Add rule for single line comments
        rules.add(new EndOfLineRule("//", Token.UNDEFINED));

        // Add rule for strings
        rules.add(new SingleLineRule("\"", "\"", Token.UNDEFINED, '\\'));

        // Add rules for multi-line comments
        rules.add(new MultiLineRule("/*", "*/", comment));

        IPredicateRule[] ruleArray = new IPredicateRule[rules.size()];
        rules.toArray(ruleArray);
        setPredicateRules(ruleArray);
    }

}
