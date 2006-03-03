/*
 *
 *  Copyright (C) DSTC Pty Ltd (ACN 052 372 577) 2004.
 *  Unpublished work.  All Rights Reserved.
 *
 *  The software contained on this media is the property of the
 *  DSTC Pty Ltd.  Use of this software is strictly in accordance
 *  with the license agreement in the accompanying LICENSE.DOC
 *  file.  If your distribution of this software does not contain
 *  a LICENSE.DOC file then you have no rights to use this
 *  software in any manner and should contact DSTC at the address
 *  below to determine an appropriate licensing arrangement.
 *
 *     DSTC Pty Ltd
 *     Level 7, G.P. South
 *     Staff House Road
 *     University of Queensland
 *     St Lucia, 4072
 *     Australia
 *     Tel: +61 7 3365 4310
 *     Fax: +61 7 3365 4311
 *     Email: enquiries@dstc.edu.au
 *
 *  This software is being provided "AS IS" without warranty of
 *  any kind.  In no event shall DSTC Pty Ltd be liable for
 *  damage of any kind arising out of or in connection with
 *  the use or performance of this software.
 *
 *  Project:  com.dstc.tefkat.plugin
 *
 *  File:     TefkatModelRuleBasedScanner.java
 *
 *  History:  Created on 8/07/2004 by lawley
 *
 */
package com.dstc.tefkat.plugin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author lawley
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TefkatModelRuleBasedScanner extends RuleBasedScanner {

    public static final Color MULTILINE_COMMENT_COLOR = new Color(Display.getCurrent(),
            new RGB(0, 80, 0));

    private static Color DEFAULT_TAG_COLOR = new Color(Display.getCurrent(),
            new RGB(0, 0, 0));

    private static final Color KEYWORD_COLOR = new Color(Display.getCurrent(),
            new RGB(0, 0, 160));

    private static final Color COMMENT_COLOR = new Color(Display.getCurrent(),
            new RGB(0, 160, 0));

    private static final Color STRING_COLOR = new Color(Display.getCurrent(),
            new RGB(160, 0, 160));

    private static final Color URI_COLOR = new Color(Display.getCurrent(),
            new RGB(160, 80, 80));

    private static final String[] keywords = {
            "TRANSFORMATION", "IMPORT", "CLASS", "ABSTRACT", "RULE",
            "EXTENDS", "OVERRIDES",
            "PATTERN", "TEMPLATE", "FORALL", "EXACT", "DYNAMIC", "WHERE", "MAKE", "SET", "WITH", "FROM",
            // "REMEMBER", "RECALL",
            "LINKING", "LINKS",
            "TRUE", "FALSE", "true", "false",
            "IF", "THEN", "ELSEIF", "ELSE", "ENDIF",
            "BEFORE", "IN",
            "MAP",
            "AND", "OR", "NOT", "UNDEF"
    };

    public TefkatModelRuleBasedScanner() {
        setDefaultReturnToken(new Token(new TextAttribute(DEFAULT_TAG_COLOR)));

        IToken multiCommentToken = new Token(new TextAttribute(MULTILINE_COMMENT_COLOR));
        IToken keywordToken = new Token(new TextAttribute(KEYWORD_COLOR, null, SWT.BOLD));
        IToken commentToken = new Token(new TextAttribute(COMMENT_COLOR));
        IToken stringToken = new Token(new TextAttribute(STRING_COLOR, null, SWT.BOLD));
        IToken uriToken = new Token(new TextAttribute(URI_COLOR, null, SWT.BOLD));

        WordRule keywordRule = new WordRule(new IWordDetector() {

            public boolean isWordStart(char c) {
                return (c >= 'A' && c <= 'Z');
            }

            public boolean isWordPart(char c) {
                return (c >= 'A' && c <= 'Z');
            }
        });
        for (int i = 0; i < keywords.length; i++) {
            keywordRule.addWord(keywords[i], keywordToken);
        }

        List rules = new ArrayList();
        int i = 0;
        rules.add(new MultiLineRule("/*", "*/", multiCommentToken));
        rules.add(new SingleLineRule("\"", "\"", stringToken, '\\'));
        rules.add(new SingleLineRule("<", ">", uriToken));
        rules.add(new EndOfLineRule("IMPORT", uriToken));
        rules.add(new EndOfLineRule("//", commentToken));
        rules.add(new EndOfLineRule("%", commentToken));
        rules.add(keywordRule);
        rules.add(new SingleLineRule("true", null, keywordToken));
        rules.add(new SingleLineRule("false", null, keywordToken));
        
        IRule[] ruleArray = new IRule[rules.size()];
        rules.toArray(ruleArray);
        setRules(ruleArray);
    }

}
