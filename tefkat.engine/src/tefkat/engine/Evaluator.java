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
 *
 */

package tefkat.engine;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;

import tefkat.data.DataMap;
import tefkat.model.*;
import tefkat.model.internal.ModelUtils;


/**
 * Evaluator implements an expression evaluator for the QVTModel.
 * It requires that all Vars referenced in the Expression have been
 * supplied with (ground) bindings in the supplied context. 
 * 
 * @author lawley
 *
 */
class Evaluator {

    private static final String NULL_TYPE = "null";

    private static final class IdentityFunction implements Function {
        final public Object call(Object[] params) {
            return params[0];
        }
    }

    private static final class CollectFunction implements Function {
        final public Object call(Object[] params) {
            return Arrays.asList(params);
        }
    }

    private static final class AppendFunction implements Function {
        final public Object call(Object[] params) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < params.length; i++) {
                sb.append(params[i]);
            }
            return sb.toString();
        }
    }

    private static final class ElementAt implements Function {
        public Object call(Object[] params) {
            List list = (List) params[0];
            Number index = (Number) params[1];
            return list.get(index.intValue());
        }
    }

    private static final class SubList implements Function {
        public Object call(Object[] params) {
            List list = (List) params[0];
            Number start = (Number) params[1];
            if (params.length > 2) {
                Number end = (Number) params[2];
                return list.subList(start.intValue(), end.intValue());
            } else {
                return list.subList(start.intValue(), list.size());
            }
        }
    }

    private static final class JoinStrings implements Function {
        /**
         * concatenate a list of Strings interspersed with a separator
         * @params[0]   separator
         * @params[1..n]    the list of strings
         */
        final public Object call(Object[] params) {
            String separator = String.valueOf(params[0]);
            StringBuffer b = new StringBuffer();
            if (params.length == 2 && params[1] instanceof Collection) {
                for (Iterator itr = ((Collection) params[1]).iterator(); itr.hasNext(); ) {
                    b.append(itr.next());
                    if (itr.hasNext()) {
                        b.append(separator);
                    }
                }
            } else if (params.length > 1) {
                b.append(params[1]);
                for (int i = 2; i < params.length; i++) {
                    b.append(separator).append(params[i]);
                }
            }
            return b.toString();
        }
    }

    private static final class SplitString implements Function {
        final public Object call(Object[] params) {
            String string = String.valueOf(params[0]);
            String regex = String.valueOf(params[1]);
            return Arrays.asList(string.split(regex));
        }
    }

    private static final class StripSuffix implements Function {
        final public Object call(Object[] params) {
            String str = (String) params[0];
            String suffix = (String) params[1];
            String result = str;
            if (str.endsWith(suffix)) {
                result = str.substring(0, str.length() - suffix.length());
            }
            return result;
        }
    }

    private static final class CastInt implements Function {
        final public Object call(Object[] params) {
            return Integer.decode(String.valueOf(params[0]));
        }
    }

    private static final class CastLong implements Function {
        final public Object call(Object[] params) {
            return Long.decode(String.valueOf(params[0]));
        }
    }

    private static final class CastFloat implements Function {
        final public Object call(Object[] params) {
            return Float.valueOf(String.valueOf(params[0]));
        }
    }

    private static final class CastDouble implements Function {
        final public Object call(Object[] params) {
            return Double.valueOf(String.valueOf(params[0]));
        }
    }

    private static final class Add implements Function {
        final public Object call(Object[] params) {
            Number lhs = (Number) params[0];
            Number rhs = (Number) params[1];
            if (lhs instanceof Float || rhs instanceof Float || lhs instanceof Double || rhs instanceof Double) {
                double lval = lhs.doubleValue();
                double rval = rhs.doubleValue();
                double result = lval + rval;
                if (result < Float.MAX_VALUE && result > Float.MIN_VALUE) {
                    return new Float(result);
                } else {
                    return new Double(result);
                }
            } else {
                long lval = lhs.longValue();
                long rval = rhs.longValue();
                long result = lval + rval;
                if (result < Integer.MAX_VALUE && result > Integer.MIN_VALUE) {
                    return new Integer((int) result);
                } else {
                    return new Long(result);
                }
            }
        }
    }

    private static final class Subtract implements Function {
        final public Object call(Object[] params) {
            Number lhs = (Number) params[0];
            Number rhs = (Number) params[1];
            if (lhs instanceof Float || rhs instanceof Float || lhs instanceof Double || rhs instanceof Double) {
                double lval = lhs.doubleValue();
                double rval = rhs.doubleValue();
                return new Double(lval - rval);
            } else {
                long lval = lhs.longValue();
                long rval = rhs.longValue();
                return new Long(lval - rval);
            }
        }
    }

    private static final class Multiply implements Function {
        final public Object call(Object[] params) {
            Number lhs = (Number) params[0];
            Number rhs = (Number) params[1];
            if (lhs instanceof Float || rhs instanceof Float || lhs instanceof Double || rhs instanceof Double) {
                double lval = lhs.doubleValue();
                double rval = rhs.doubleValue();
                return new Double(lval * rval);
            } else {
                long lval = lhs.longValue();
                long rval = rhs.longValue();
                return new Long(lval * rval);
            }
        }
    }

    private static final class Divide implements Function {
        final public Object call(Object[] params) {
            Number lhs = (Number) params[0];
            Number rhs = (Number) params[1];
            if (lhs instanceof Float || rhs instanceof Float || lhs instanceof Double || rhs instanceof Double) {
                double lval = lhs.doubleValue();
                double rval = rhs.doubleValue();
                return new Double(lval / rval);
            } else {
                long lval = lhs.longValue();
                long rval = rhs.longValue();
                return new Long(lval / rval);
            }
        }
    }

    /**
     * Key for Node instance in the funcMap (yes, it's a hack)
     */
    private final static String CONTEXT_KEY = " context ";
    private final Map funcMap = new HashMap();

    private final RuleEvaluator ruleEval;

    Evaluator(RuleEvaluator evaluator) {
    	ruleEval = evaluator;
        initFunctionMap();
    }

    private void initFunctionMap() {
        addFunction("identity", new IdentityFunction());
        addFunction("collect", new CollectFunction());
        addFunction("append", new AppendFunction());
        addFunction("elementAt", new ElementAt());
        addFunction("subList", new SubList());
        addFunction("join", new JoinStrings());
        addFunction("split", new SplitString());
        addFunction("stripSuffix", new StripSuffix());
        addFunction("int", new CastInt());
        addFunction("long", new CastLong());
        addFunction("float", new CastFloat());
        addFunction("double", new CastDouble());
        addFunction("+", new Add());
        addFunction("-", new Subtract());
        addFunction("*", new Multiply());
        addFunction("/", new Divide());
        
        addFunction("funmap", new Function() {
            public Object call(Object[] params) throws ResolutionException {
                final Binding context = (Binding) funcMap.get(CONTEXT_KEY);
                final Collection list = (Collection) params[0];
                final String feature = (String) params[1];
                final List result = new ArrayList();
                
                for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
                    Object obj = itr.next();
                    result.add(fetchFeature(context, feature, obj));
                }

                return result;
            }
        });
        
        // FIXME rename this function to dataMap or something (see tefkat.g)
        addFunction("map", new Function() {
            final public Object call(Object[] params) throws ResolutionException {
                DataMap dataMap = (DataMap) params[0];
                String key = String.valueOf(params[1]);
                Object result = dataMap.getValue().get(key);
                if (result instanceof Expression) {
                    Binding context = (Binding) funcMap.get(CONTEXT_KEY);
                    try {
                        List vals = eval(context, (Expression) result);
                        if (vals.size() == 1) {
                            result = vals.get(0);
                        } else {
                            result = vals;
                        }
                    } catch (ResolutionException e) {
                        throw new ResolutionException(null, "Map expression '" + result + "' evaluation failed", e);
                    } catch (NotGroundException e) {
                        throw new ResolutionException(null, "Map expression '" + result + "' should not contain variable(s)", e);
                    }
                }
                return result;
            }
        });
    }

    void addFunction(String name, Function function) {
        if (funcMap.containsKey(name)) {
            throw new IllegalArgumentException("A Function with named " + name + " is already registered.");
        }
        funcMap.put(name, function);
    }

    /**
     * Evaluate expr given the bindings in node.
     * 
     * @param node  The Term context for the evaluation (supplies initial bindings)
     * @param expr  The Expression to evaluate
     * @return  A List of the values of the Expression. VarUse of an unbound Var evaluates to a WrappedVar.
     * @throws ResolutionException
     */
    List eval(Node node, Expression expr)
        throws ResolutionException, NotGroundException {
        
        try {
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_ENTER);
            List result = doEval(node.getBindings(), expr);
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_EXIT);
            return result;
        } catch (ResolutionException e) {
            e.setNode(node);
            throw e;
        } catch (NotGroundException e) {
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_DELAY);
            throw e;
        }
    }

    private List eval(Binding context, Expression expr)
    throws ResolutionException, NotGroundException {
    
        try {
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_ENTER);
            List result = doEval(context, expr);
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_EXIT);
            return result;
        } catch (NotGroundException e) {
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_DELAY);
            throw e;
        }
    }
    
    List expand(WrappedVar wVar) throws NotGroundException {
        if (null == wVar.getExtent()) {
            throw new NotGroundException("Unsupported mode: unbound extent for" + wVar);
        }
//        AbstractVar var = wVar.getVar();
//        EClassifier type = wVar.getType();
//        System.out.println("Expanding " + wVar);   // TODO delete
//        System.out.println("\t" + wVar.getExtent().getObjectsByClass(wVar.getType(), wVar.isExact()));
        return wVar.getExtent().getObjectsByClass(wVar.getType(), wVar.isExact());
    }
    
    private List doEval(Binding context, Expression expr)
        throws ResolutionException, NotGroundException {

        List values;

        if (expr instanceof SimpleExpr) {
            values = new ArrayList();
            values.add(evalSimpleExpr(expr));
        } else if (expr instanceof EnumConstant) {
            values = evalEnumExpr(context, expr);
        } else if (expr instanceof VarUse) {
            values = new ArrayList();
            AbstractVar var = ((VarUse) expr).getVar();
            Object value = context.lookup(var);
            if (null == value) {
                values.add(new WrappedVar(var));
            } else {
                values.add(value);
            }
        } else if (expr instanceof FeatureExpr) {
            values = evalFeatureExpr(context, expr);
        } else if (expr instanceof FunctionExpr) {
            values = evalFunctionExpr(context, expr);
        } else if (expr instanceof InstanceRef) {
            values = new ArrayList();
            InstanceRef ref = (InstanceRef) expr;
            values.add(ref.getObject());
        } else if (expr instanceof CollectionExpr) {
            values = evalCollectionExpr(context, expr);
        } else {
            throw new ResolutionException(
                null,
                expr.eClass().getName() + " Expressions are not yet supported: " + expr);
        }

        return values;
    }

    private List evalCollectionExpr(Binding context, Expression expr) throws ResolutionException, NotGroundException {
        // FIXME - Yikes, the semantiocs of these is complicated
        //
        // Should X = [1, Y.foo] where Y.foo returns the collection [2, 3]
        // bind X to 1, 2, and 3 or to [1, 2, 3] or to [1, 2] and [1, 3] ?
        //
        // What about X = [1, Y.foo{}]?
        //
        // For the moment, we'll make X = [expr_1, ..., expr_k] equivalent
        // to X = union(expr_1, ..., expr_k) i.e., for the example above,
        // X would bind to 1, then 2, then 3.
        //
        
        CollectionExpr collection = (CollectionExpr) expr;
        List args = collection.getArg();
        Function f = (Function) funcMap.get("collect");
        
        Expander expander = new Expander(f, context, args, true);
        List results = expander.getResults();
        
        return results;
    }

    private List evalFunctionExpr(Binding context, Expression expr) throws ResolutionException, NotGroundException {
        List values;
        FunctionExpr funcExpr = (FunctionExpr) expr;
        String op = funcExpr.getFunction();
        List args = funcExpr.getArg();
        Function f = (Function) funcMap.get(op);
        try {
            if (null != f) {
                funcMap.put(CONTEXT_KEY, context);
                if (args.size() > 0) {
                    Expander expander = new Expander(f, context, args, false);
                    values = expander.getResults();
                } else {
                    values = new ArrayList();
                    values.add(f.call(args.toArray()));
                }
                funcMap.remove(CONTEXT_KEY);
            } else if ("collect".equals(op)) {
                f = (Function) funcMap.get("collect");
                Expander expander = new Expander(f, context, args, true);
                values = expander.getResults();
            } else {
                throw new ResolutionException(null, "Unknown function: " + op);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ResolutionException(null, "Badly typed parameter(s) to function: " + op, e);
        } catch (RuntimeException e) {
            throw new ResolutionException(null, "Function evaluation failed: " + op, e);
        }
        return values;
    }

    private List evalFeatureExpr(Binding context, Expression expr)
    throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        FeatureExpr featExpr = (FeatureExpr) expr;
        Collection featureNames = eval(context, featExpr.getFeature());
        List args = featExpr.getArg();
        List objs = eval(context, (Expression) args.get(0));
        Binding featureContext = null;
        
        for (final Iterator fItr = featureNames.iterator(); fItr.hasNext(); ) {
            Object fObj = fItr.next();
            if (fObj instanceof WrappedVar) {
                AbstractVar var = ((WrappedVar) fObj).getVar();
                throw new NotGroundException(
                    "Unsupported mode (unbound '" + var.getName() + "') for FeatureExpr: " + var.getName() + "." + featExpr.getFeature());
            } else if (fObj instanceof BindingPair) {
                if (null == featureContext) {
                    featureContext = (Binding) fObj;
                } else {
                    featureContext = new Binding(featureContext);
                    featureContext.composeLeft((Binding) fObj);
                }
                fObj = ((BindingPair) fObj).getValue();
            }

            if (fObj instanceof EStructuralFeature) {
                // TODO FIXME this is a HACK
                fObj = ((EStructuralFeature) fObj).getName();
            } else if (!(fObj instanceof String)) {
                throw new ResolutionException(null, "The Feature Expression " + featExpr + " must evaluate to a feature name of type String, not " + fObj.getClass());
            }
            String featureName = (String) fObj;
            
            // Expand a typed variable if possible
            //
            AbstractVar var = null;
            if (objs.size() == 1 && objs.get(0) instanceof WrappedVar) {
                WrappedVar wVar = (WrappedVar) objs.get(0);
                objs = expand(wVar);
                var = wVar.getVar();
            }
            
            for (final Iterator objItr = objs.iterator(); objItr.hasNext(); ) {
                Object obj = objItr.next();
                
                if (obj instanceof BindingPair) {
                    if (null == featureContext) {
                        featureContext = (Binding) obj;
                    } else {
                        featureContext = new Binding(featureContext);
                        featureContext.composeLeft((Binding) obj);
                    }
                    obj = ((BindingPair) obj).getValue();
                }
//                    System.err.println("TGT OBJ: " + obj + "\t" + obj.getClass());

                // If we're transforming a Transformation, then we will bind to
                // AbstractVars so we need to wrap our AbstractVars to so that we
                // can distinguish unbound _variables_ from _objects_.

                if (featExpr.isOperation()) {
                    Collection valuesObject = callOperation(context, featureName, obj, args.subList(1, args.size()), featExpr.isCollect());
                    if (null != var) {
                        for (final Iterator itr = valuesObject.iterator(); itr.hasNext(); ) {
                            Object val = itr.next();
                            Binding unifier = new BindingPair(featureContext, val);
                            unifier.add(var, obj);
                            values.add(unifier);
                        }
                    } else if (null != featureContext) {
                        for (final Iterator itr = valuesObject.iterator(); itr.hasNext(); ) {
                            Object val = itr.next();
                            Binding unifier = new BindingPair(featureContext, val);
                            values.add(unifier);
                        }
                    } else {
                        values.addAll(valuesObject);
                    }
                } else {
                    Object valuesObject = fetchFeature(context, featureName, obj);
                    
                    if (null != valuesObject && valuesObject.getClass().isArray()) {
                        valuesObject = wrapArray(valuesObject);
                    }
                    
                    if (valuesObject instanceof Collection) {
                        if (featExpr.isCollect()) {
                            if (null != var) {
                                Binding unifier = new BindingPair(featureContext, valuesObject);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != featureContext) {
                                Binding unifier = new BindingPair(featureContext, valuesObject);
                                values.add(unifier);
                            } else {
                                values.add(valuesObject);
                            }
                        } else {
                            if (null != var) {
                                for (final Iterator itr = ((Collection) valuesObject).iterator(); itr.hasNext(); ) {
                                    Object val = itr.next();
                                    Binding unifier = new BindingPair(featureContext, val);
                                    unifier.add(var, obj);
                                    values.add(unifier);
                                }
                            } else if (null != featureContext) {
                                for (final Iterator itr = ((Collection) valuesObject).iterator(); itr.hasNext(); ) {
                                    Object val = itr.next();
                                    Binding unifier = new BindingPair(featureContext, val);
                                    values.add(unifier);
                                }
                            } else {
                                values.addAll((Collection) valuesObject);
                            }
                        }
                    } else if (null != valuesObject) {
                        if (featExpr.isCollect()) {
                            List l = new ArrayList(1);
                            l.add(valuesObject);
                            if (null != var) {
                                Binding unifier = new BindingPair(featureContext, l);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != featureContext) {
                                Binding unifier = new BindingPair(featureContext, l);
                                values.add(unifier);
                            } else {
                                values.add(l);
                            }
                        } else {
                            if (null != var) {
                                Binding unifier = new BindingPair(featureContext, valuesObject);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != featureContext) {
                                Binding unifier = new BindingPair(featureContext, valuesObject);
                                values.add(unifier);
                            } else {
                                values.add(valuesObject);
                            }
                        }
                    }
                }
            }
        }
        return values;
    }

    /**
     * Turn an array of things into a List of things, boxing primitive types as required.
     * 
     * @param valuesObject
     * @return
     */
    private Object wrapArray(Object valuesObject) {
        if (valuesObject instanceof Object[]) {
            valuesObject = Arrays.asList((Object[]) valuesObject);
        } else if (valuesObject.getClass().isArray()) {
            final int length = Array.getLength(valuesObject);
            List l = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                l.add(Array.get(valuesObject, i));
            }
            valuesObject = l;
        }
        return valuesObject;
    }

    private Object evalSimpleExpr(Expression expr) {
        Object value;

        if (expr instanceof StringConstant) {
            value = ((SimpleExpr) expr).getRepresentation();
        } else if (expr instanceof BooleanConstant) {
            value = Boolean.valueOf(((SimpleExpr) expr).getRepresentation());
        } else if (expr instanceof IntConstant) {
            value = Integer.valueOf(((SimpleExpr) expr).getRepresentation());
        } else if (expr instanceof RealConstant) {
            value = Double.valueOf(((SimpleExpr) expr).getRepresentation());
        } else {
            throw new IllegalArgumentException(
                "Unsupported expression type: " + expr);
        }

        return value;
    }
    
    private List evalEnumExpr(Binding context, Expression expr)
    throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        List args = ((EnumConstant) expr).getArg();
        Expression enumExpr = (Expression) args.get(0);
        Expression literalExpr = (Expression) args.get(1);
        List enumObjs = eval(context, enumExpr);
        List literalObjs = eval(context, literalExpr);

        for (final Iterator eItr = enumObjs.iterator(); eItr.hasNext(); ) {
            Object eObj = eItr.next();
            EEnum enumeration = null;
            if (eObj instanceof String) {
                eObj = ModelUtils.findClassifierByName(ruleEval.nameMap, (String) eObj);
            }
            if (eObj instanceof EEnum) {
                enumeration = (EEnum) eObj;
            }
            if (null != enumeration) {
                for (final Iterator lItr = literalObjs.iterator(); lItr.hasNext(); ) {
                    Object lObj = lItr.next();
                    if (lObj instanceof String) {
                        EEnumLiteral eLit = enumeration.getEEnumLiteral((String) lObj);
                        if (null != eLit) {
                            values.add(eLit.getInstance());
                        }
                    }
                }
            }
        }
        
        return values;
    }

    /**
     * @param context
     * @param args
     * @return
     * @throws ResolutionException
     * @throws NotGroundException
     */
    
//    
//    The change below and the expandParams functions appear to be addressing a different
//    albeit related problem to the rest of the changes to Tree, TreeListener, *Resolver etc
//    Those changes are dealing with delays propagating out of sub-Trees -- see the FIXME below
    
    void evalAll(Node node, Binding context, List args, Function function) throws ResolutionException, NotGroundException {
        try {
            new Expander(function, context, args, false);
        } catch (ResolutionException e) {
            e.setNode(node);
            throw e;
        }
    }
    
    private Map methodCache = new HashMap();
    
    private Method resolveMethod(Object instance, String name, Object[] params) {
        Map methodCache = getMethodCache(instance, name);
        Method method = null;
        
        // Deal with zero-arity method first
        if (null == params || params.length == 0) {
            Class cls = instance.getClass();
            method = (Method) methodCache.get(NULL_TYPE);
            if (null == method) {
                try {
                    Method[] ms = cls.getMethods();
                    for (int i = 0; null == method && i < ms.length; i++) {
                        if (name.equals(ms[i].getName()) && ms[i].getParameterTypes().length == 0) {
                            method = ms[i];
                        }
                    }
                } catch (SecurityException e) {
                }
            }
            if (null != method) {
                // Cache the result
                methodCache.put(NULL_TYPE, method);
            }
            return method;
        }
        
        Class[] rawTypes = new Class[params.length];
        boolean hasBoxedTypes = false;
        for (int j = 0; j < params.length; j++) {
            Class type = params[j].getClass();
            rawTypes[j] = type;
            if (Number.class.isAssignableFrom(type)) {
                hasBoxedTypes = true;
            }
        }
        
        method = (Method) methodCache.get(Arrays.asList(rawTypes));
        
        if (null == method) {
            Class[] unboxedTypes = null;
            if (hasBoxedTypes) {
                unboxedTypes = new Class[params.length];
                for (int j = 0; j < params.length; j++) {
                    Class type = params[j].getClass();
                    rawTypes[j] = type;
                    if (Number.class.isAssignableFrom(type)) {
                        try {
                            Field typeField = type.getField("TYPE");
                            unboxedTypes[j] = (Class) typeField.get(type);
                        } catch (SecurityException e) {
                        } catch (NoSuchFieldException e) {
                        } catch (IllegalArgumentException e) {
                        } catch (IllegalAccessException e) {
                        }
                    } else {
                        unboxedTypes[j] = type;
                    }
                }
            }
            method = resolveMethod(instance, name, rawTypes, unboxedTypes);
            
            if (null != method) {
                // Cache the result
                methodCache.put(Arrays.asList(rawTypes), method);
            }
        }

        return method;
    }
    
    /**
     * Beware, this will find the first method (they are in an arbitrary order) that matches subject to
     * auto-unboxing...eg, the choice between foo(int) and foo(Integer) is arbitrary
     * 
     * @param instance
     * @param name
     * @param types
     * @param unboxedTypes may be null, but otherwise the same length as types
     * @return
     */
    private Method resolveMethod(Object instance, String name, Class[] types, Class[] unboxedTypes) {
        Method method = null;
        Class cls = instance.getClass();
        
        Method[] ms = cls.getMethods();
        for (int i = 0; null == method && i < ms.length; i++) {
            if (name.equals(ms[i].getName())) {
                Class[] parameterTypes = ms[i].getParameterTypes();
                if (parameterTypes.length != types.length) {
                    continue;
                }
                method = ms[i];
                for (int j = 0; j < parameterTypes.length; j++) {
                    if (!parameterTypes[j].isAssignableFrom(types[j]) &&
                        (null == unboxedTypes || !parameterTypes[j].isAssignableFrom(unboxedTypes[j]))) {
                        method = null;
                        break;
                    }
                }
            }
        }

        return method;
    }
    
    private Map getMethodCache(Object instance, String name) {
        Map namesToTypes = (Map) methodCache.get(instance.getClass());
        Map typesToMethods;
        if (null != namesToTypes) {
            typesToMethods = (Map) namesToTypes.get(name);
            if (null == typesToMethods) {
                typesToMethods = new HashMap();
                namesToTypes.put(name, typesToMethods);
            }
        } else {
            namesToTypes = new HashMap();
            typesToMethods = new HashMap();
            methodCache.put(instance.getClass(), namesToTypes);
            namesToTypes.put(name, typesToMethods);
        }
        return typesToMethods;
    }

    private void warnNoMethod(Object instance, String methodName) {
        String message = "No such operation: ";
        if (instance instanceof EObject) {
            message += ModelUtils.getFullyQualifiedName(((EObject) instance).eClass()) + "." + methodName;
        } else {
            message += ModelUtils.getFullyQualifiedName(instance.getClass()) + "." + methodName;
        }
//        System.err.println(message);
        ruleEval.fireWarning(message);
    }
    
    private List callOperation(Binding context, final String operationName, final Object instance, List args, boolean collect)
    throws ResolutionException, NotGroundException {
        Function methodCall = new Function() {
            public Object call(Object[] params) throws ResolutionException {
                Object result = null;
                
                // In the general case the param types could all be different and
                // thus invoke different methods so we cannot cache/lift this
                // method resolution call
                Method method = resolveMethod(instance, operationName, params);

                if (null == method) {
                    warnNoMethod(instance, operationName);
                    result = Collections.EMPTY_LIST;
                } else {
                    try {
                        result = method.invoke(instance, params);
                    } catch (SecurityException e) {
                        ruleEval.fireWarning(e);
                    } catch (IllegalArgumentException e) {
                        ruleEval.fireWarning(e);
                    } catch (IllegalAccessException e) {
                        ruleEval.fireWarning(e);
                    } catch (InvocationTargetException e) {
                        ruleEval.fireWarning(e);
                    } catch (Exception e) {
                        e.printStackTrace();
//                        ruleEval.fireError(e);
                        throw new ResolutionException(null, "Operation invocation "+ operationName + " failed", e);
                    }
                    if (null == result) {
                        result = Collections.EMPTY_LIST;
                    }
                }
                return result;
            }
            
        };

        List results;

        if (args.size() > 0) {
            Expander expander = new Expander(methodCall, context, args, collect);
            results = expander.getResults();
        } else {
            results = new ArrayList();
            Object result = methodCall.call(null);
            if (null != result) {
                if (!collect && result instanceof Collection) { 
                    results.addAll((Collection) result);
                } else {
                    results.add(result);
                }
            }
        }
        // System.out.println("\t" + operationName + "(...) = " + results);   // TODO delete
        
        return results;
    }
    
//    private Object[] coerceTypes(Method meth, Object[] params) {
//        System.err.println(meth.isAccessible());
//        System.err.println(meth.getModifiers());
//        System.err.println(meth.getName());
//        
//        Class[] types = meth.getParameterTypes();
//        System.err.println(meth);
//        System.err.println(Arrays.asList(params));
//        System.err.println(Arrays.asList(types));
//        for (int i = 0; i < types.length; i++) {
//            System.err.println(types[i].getName() + "\t" + params[i].getClass().getName() + "\t" + params[i]);
//            if (!types[i].isInstance(params[i])) {
//                System.err.println("Type mismatch: " + types[i]);
//                if (int.class.equals(types[i])) {
//                    Integer theInt = Integer.valueOf(String.valueOf(params[i]));
//                    params[i] = theInt;
//                }
//            }
//        }
//        
//        return null;
//    }

    Object fetchFeature(Binding context, String featureName, Object obj) {
        Object valuesObject = null;
        try {
            if (obj instanceof EObject) {
                EObject instance = (EObject) obj;
                try {
                    EStructuralFeature eFeature = AbstractResolver.getFeature(null, instance.eClass(), featureName);
                    valuesObject = instance.eGet(eFeature);

                    if (valuesObject != null || instance.eIsSet(eFeature) || !eFeature.isRequired()) {
                        ExtentUtil.highlightEdge(instance, valuesObject, ExtentUtil.FEATURE_LOOKUP);
                    } else {
                        ruleEval.fireWarning(ModelUtils.getFullyQualifiedName(eFeature) + " is not set and no default value");
                    }
                    return valuesObject;    // This was a valid feature - don't want to fall through
                } catch (ResolutionException e) {
                    // EFeature not found, so try other ways to get a value for featureName
                }
            }
            if (obj instanceof FeatureMap.Entry) {
                FeatureMap.Entry entry = (FeatureMap.Entry) obj;
                EStructuralFeature eFeature = entry.getEStructuralFeature();
                if (eFeature.getName().equals(featureName)) {
                    valuesObject = entry.getValue();
                    return valuesObject;    // This was a valid feature - don't want to fall through
                }
            }

            String methName = "get" + featureName.substring(0, 1).toUpperCase() + featureName.substring(1, featureName.length());
            try {
                try {
                    valuesObject = obj.getClass().getMethod(methName, null).invoke(obj, null);
                } catch (NoSuchMethodException e) {
                    if (null == valuesObject) {
                        valuesObject = obj.getClass().getField(featureName).get(obj);
                    }
                }
            } catch (Exception e) {
                throw new ResolutionException(null, "Could not find a source of values for '" + featureName + "' in '" + obj + "'", e);
            }
        } catch (ResolutionException e) {
            ruleEval.fireWarning(e);
        }

        return valuesObject;
    }
    
//    private void accumulate(Function f, List itemsCollections, Collection results, boolean collect) throws ResolutionException {
//        accumulate(f, itemsCollections, 0, new Object[itemsCollections.size()], results, collect);
//    }
//    
//    private void accumulate(Function f, List itemsCollections, int i, Object[] params, Collection results, boolean collect) throws ResolutionException {
//        Collection items = (Collection) itemsCollections.get(i);
//        if (i < itemsCollections.size()-1) {
//            for (final Iterator itr = items.iterator(); itr.hasNext(); ) {
//                params[i] = itr.next();
//                accumulate(f, itemsCollections, i+1, params, results, collect);
//            }
//        } else {
//            for (final Iterator itr = items.iterator(); itr.hasNext(); ) {
//                params[i] = itr.next();
//                Object result = f.call(params);
//                if (null != result) {
//                    if (!collect && result instanceof Collection) { 
//                        results.addAll((Collection) result);
//                    } else {
//                        results.add(result);
//                    }
//                } else {
//                    ruleEval.fireWarning("Function call returned null: " + f + "(" + params + ")");
//                }
//            }
//        }
//    }
    
//    /**
//     * 
//     * @param f Function to call for each set of actual parameter values
//     * @param context outer Binding context for the calls
//     * @param actuals List of Expressions to evaluate to obtain the parameter values
//     * @param params array of actual parameter values
//     * @param i number of valid values in the params array
//     * @param results collection of results from the calls
//     * @param collect true means preserve nesting of result values
//     * @throws NotGroundException
//     * @throws ResolutionException
//     */
//    private void expandParams(Function f, Binding context, List actuals, Object[] params, int i, Collection results, boolean collect)
//    throws NotGroundException, ResolutionException {
//        if (i == params.length) {
//            Object result = f.call(params);
//            if (null != result) {
//                if (!collect && result instanceof Collection) { 
//                    results.addAll((Collection) result);
//                } else {
//                    results.add(result);
//                }
//            } else {
//                ruleEval.fireWarning("Function call returned null: " + f + "(" + params + ")");
//            }
//        } else {
//            List values = eval(context, (Expression) actuals.get(i));
//            for (final Iterator itr = values.iterator(); itr.hasNext(); ) {
//                Object obj = itr.next();
//                Binding newContext;
//                if (obj instanceof BindingPair) {
//                    newContext = null;
//                } else {
//                    newContext = context;
//                }
//                params[i] = obj;
//                expandParams(f, newContext, actuals, params, i+1, results, collect);
//            }
//        }
//    }

    final class Expander {

        final private List results = new ArrayList();
        final private Function f;
        final private List actuals;
        final private Object[] params;
        final private boolean collect;
        
        /**
         * 
         * @param function Function to call for each set of actual parameter values
         * @param context outer Binding context for the calls
         * @param actuals List of Expressions to evaluate to obtain the parameter values
         * @param collect true means preserve nesting of result values
         * @throws NotGroundException
         * @throws ResolutionException
         */
        Expander(Function function, Binding context, List actuals, boolean collect)
        throws NotGroundException, ResolutionException {
            this.f = function;
            this.actuals = actuals;
            this.collect = collect;
            
            params = new Object[actuals.size()];

            expandParams(context, 0);
        }
        
        private void expandParams(Binding context, int i)
        throws NotGroundException, ResolutionException {
            if (i == params.length) {
                Object result = (f instanceof Function2) ? ((Function2) f).call(context, params) : f.call(params);
                if (null != result) {
                    if (!collect && result instanceof Collection) { 
                        results.addAll((Collection) result);
                    } else {
                        results.add(result);
                    }
                } else {
                    ruleEval.fireWarning("Function call returned null: " + f + "(" + params + ")");
                }
            } else {
                List values = eval(context, (Expression) actuals.get(i));
                for (final Iterator itr = values.iterator(); itr.hasNext(); ) {
                    Object obj = itr.next();
                    Binding newContext;
                    if (obj instanceof BindingPair) {
                        newContext = new Binding(context);
                        newContext.composeRight((BindingPair) obj);
                        obj = ((BindingPair) obj).getValue();
                        
                        System.err.println(params[i] + " = " + obj);
                        System.err.println(context);
                        System.err.println(newContext);
                    } else {
                        newContext = context;
                    }
                    params[i] = obj;
                    expandParams(newContext, i+1);
                }
            }
        }
        
        List getResults() {
            return results;
        }
    }
}
