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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;

import tefkat.data.DataMap;
import tefkat.model.*;
import tefkat.model.internal.Util;


/**
 * Evaluator implements an expression evaluator for the QVTModel.
 * It requires that all Vars referenced in the Expression have been
 * supplied with (ground) bindings in the supplied context. 
 * 
 * @author lawley
 *
 */
class Evaluator {

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
        funcMap.put("identity", new IdentityFunction());

        funcMap.put("collect", new CollectFunction());
        
        funcMap.put("funmap", new Function() {
            public Object call(Object[] params) throws ResolutionException {
                final Node node = (Node) funcMap.get(CONTEXT_KEY);
                final Collection list = (Collection) params[0];
                final String feature = (String) params[1];
                final List result = new ArrayList();
                
                for (final Iterator itr = list.iterator(); itr.hasNext(); ) {
                    Object obj = itr.next();
                    result.add(fetchFeature(node, feature, obj));
                }

                return result;
            }
        });

        funcMap.put("append", new AppendFunction());
        
        funcMap.put("elementAt", new ElementAt());
        
        funcMap.put("subList", new SubList());
        
        funcMap.put("join", new JoinStrings());
        
        funcMap.put("split", new SplitString());
        
        funcMap.put("stripSuffix", new StripSuffix());

        funcMap.put("int", new CastInt());

        funcMap.put("long", new CastLong());

        funcMap.put("float", new CastFloat());

        funcMap.put("double", new CastDouble());

        funcMap.put("+", new Add());

        funcMap.put("-", new Subtract());

        funcMap.put("*", new Multiply());

        funcMap.put("/", new Divide());
        
        // FIXME rename this function to dataMap or something (see tefkat.g)
        funcMap.put("map", new Function() {
            final public Object call(Object[] params) {
                DataMap dataMap = (DataMap) params[0];
                String key = String.valueOf(params[1]);
                Object result = dataMap.getValue().get(key);
                if (result instanceof Expression) {
                    Node node = (Node) funcMap.get(CONTEXT_KEY);
                    try {
                        List vals = eval(node, (Expression) result);
                        if (vals.size() == 1) {
                            result = vals.get(0);
                        } else {
                            result = vals;
                        }
                    } catch (ResolutionException e) {
                        throw new RuntimeException("Map expression '" + result + "' evaluation failed", e);
                    } catch (NotGroundException e) {
                        throw new RuntimeException("Map expression '" + result + "' should not contain variable(s)", e);
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
            List result = doEval(node, expr);
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_EXIT);
            return result;
        } catch (NotGroundException e) {
            ExtentUtil.highlightNode(expr, ExtentUtil.TERM_DELAY);
            throw e;
        }
    }
    
    List expand(WrappedVar wVar, Expression expr) throws NotGroundException {
        if (null == wVar.getExtent()) {
            throw new NotGroundException("Unsupported mode: unbound extent for" + wVar);
        }
//        AbstractVar var = wVar.getVar();
//        EClassifier type = wVar.getType();
//        System.out.println("Expanding " + wVar);   // TODO delete
//        System.out.println("\t" + wVar.getExtent().getObjectsByClass(wVar.getType(), wVar.isExact()));
        return wVar.getExtent().getObjectsByClass(wVar.getType(), wVar.isExact());
    }
    
    private List doEval(Node node, Expression expr)
        throws ResolutionException, NotGroundException {

        List values;

        if (expr instanceof SimpleExpr) {
            values = new ArrayList();
            values.add(evalSimpleExpr(expr));
        } else if (expr instanceof EnumConstant) {
            values = evalEnumExpr(node, expr);
        } else if (expr instanceof VarUse) {
            values = new ArrayList();
            AbstractVar var = ((VarUse) expr).getVar();
            Object value = node.lookup(var);
            if (null == value) {
                values.add(new WrappedVar(var));
            } else {
                values.add(value);
            }
        } else if (expr instanceof FeatureExpr) {
            values = evalFeatureExpr(node, expr);
        } else if (expr instanceof FunctionExpr) {
            values = evalFunctionExpr(node, expr);
        } else if (expr instanceof InstanceRef) {
            values = new ArrayList();
            InstanceRef ref = (InstanceRef) expr;
            values.add(ref.getObject());
        } else if (expr instanceof CollectionExpr) {
            values = evalCollectionExpr(node, expr);
        } else {
            throw new ResolutionException(
                node,
                expr.eClass().getName() + " Expressions are not yet supported: " + expr);
        }

        return values;
    }

    private List evalCollectionExpr(Node node, Expression expr) throws ResolutionException, NotGroundException {
        // FIXME - Yikes, the semantiocs of these is complicated
        //
        // Should X = [1, Y.foo] where Y.foo returns the collection [2, 3]
        // bind X to 1, 2, and 3 or to [1, 2, 3] or to [1, 2] and [1, 3] ?
        //
        // For the moment, we'll make X = [expr_1, ..., expr_k] equivalent
        // to X = union(expr_1, ..., expr_k) i.e., for the example above,
        // X would bind to 1, then 2, then 3.
        //
        
        CollectionExpr collection = (CollectionExpr) expr;
        List args = collection.getArg();
        Function identity = (Function) funcMap.get("collect");
        List values = new ArrayList(args.size());
        List items = new ArrayList(args.size());
        
        for (final Iterator itr = args.iterator(); itr.hasNext(); ) {
            Expression elementExpr = (Expression) itr.next();
            List elementVals = eval(node, elementExpr);
//            System.out.println(elementVals);
            items.add(elementVals);
        }
        accumulate(identity, items, values);
        
        return values;
    }

    private List evalFunctionExpr(Node node, Expression expr) throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        FunctionExpr funcExpr = (FunctionExpr) expr;
        String op = funcExpr.getFunction();
        List args = funcExpr.getArg();
        List params = evalAll(node, args);  // FIXME - handle BindingPairs etc
        Function f = (Function) funcMap.get(op);
        try {
            if (null != f) {
                funcMap.put(CONTEXT_KEY, node);
                if (params.size() > 0) {
                    accumulate(f, params, values);
                } else {
                    values.add(f.call(params.toArray()));
                }
            } else if ("collect".equals(op)) {
                values.addAll(params);
            } else {
                throw new ResolutionException(node, "Unknown function: " + op);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ResolutionException(node, "Badly typed parameter(s) to function: " + op, e);
        } catch (RuntimeException e) {
            throw new ResolutionException(node, "Function evaluation failed: " + op, e);
        }
        return values;
    }

    private List evalFeatureExpr(Node node, Expression expr)
    throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        FeatureExpr featExpr = (FeatureExpr) expr;
        Collection featureNames = eval(node, featExpr.getFeature());
        List args = featExpr.getArg();
        List objs = eval(node, (Expression) args.get(0));
        Binding context = null;
        
        for (final Iterator fItr = featureNames.iterator(); fItr.hasNext(); ) {
            Object fObj = fItr.next();
            if (fObj instanceof WrappedVar) {
                AbstractVar var = ((WrappedVar) fObj).getVar();
                throw new NotGroundException(
                    "Unsupported mode (unbound '" + var.getName() + "') for FeatureExpr: " + var.getName() + "." + featExpr.getFeature());
            } else if (fObj instanceof BindingPair) {
                if (null == context) {
                    context = (Binding) fObj;
                } else {
                    context = new Binding(context);
                    context.composeLeft((Binding) fObj);
                }
                fObj = ((BindingPair) fObj).getValue();
            }

            if (fObj instanceof EStructuralFeature) {
                // TODO FIXME this is a HACK
                fObj = ((EStructuralFeature) fObj).getName();
            } else if (!(fObj instanceof String)) {
                throw new ResolutionException(node, "The Feature Expression " + featExpr + " must evaluate to a feature name of type String, not " + fObj.getClass());
            }
            String featureName = (String) fObj;
            
            // Expand a typed variable if possible
            //
            AbstractVar var = null;
            if (objs.size() == 1 && objs.get(0) instanceof WrappedVar) {
                WrappedVar wVar = (WrappedVar) objs.get(0);
                objs = expand(wVar, expr);
                var = wVar.getVar();
            }
            
            for (final Iterator objItr = objs.iterator(); objItr.hasNext(); ) {
                Object obj = objItr.next();
                
                if (obj instanceof BindingPair) {
                    if (null == context) {
                        context = (Binding) obj;
                    } else {
                        context = new Binding(context);
                        context.composeLeft((Binding) obj);
                    }
                    obj = ((BindingPair) obj).getValue();
                }
//                    System.err.println("TGT OBJ: " + obj + "\t" + obj.getClass());

                // If we're transforming a Transformation, then we will bind to
                // AbstractVars so we need to wrap our AbstractVars to so that we
                // can distinguish unbound _variables_ from _objects_.

                if (featExpr.isOperation()) {
                    Collection valuesObject = callOperation(node, featureName, obj, args.subList(1, args.size()), featExpr.isCollect());
                    if (null != var) {
                        for (final Iterator itr = valuesObject.iterator(); itr.hasNext(); ) {
                            Object val = itr.next();
                            Binding unifier = new BindingPair(context, val);
                            unifier.add(var, obj);
                            values.add(unifier);
                        }
                    } else if (null != context) {
                        for (final Iterator itr = valuesObject.iterator(); itr.hasNext(); ) {
                            Object val = itr.next();
                            Binding unifier = new BindingPair(context, val);
                            values.add(unifier);
                        }
                    } else {
                        values.addAll(valuesObject);
                    }
                } else {
                    Object valuesObject = fetchFeature(node, featureName, obj);
                    
                    if (null != valuesObject && valuesObject.getClass().isArray()) {
                        valuesObject = wrapArray(valuesObject);
                    }
                    
                    if (valuesObject instanceof Collection) {
                        if (featExpr.isCollect()) {
                            if (null != var) {
                                Binding unifier = new BindingPair(context, valuesObject);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != context) {
                                Binding unifier = new BindingPair(context, valuesObject);
                                values.add(unifier);
                            } else {
                                values.add(valuesObject);
                            }
                        } else {
                            if (null != var) {
                                for (final Iterator itr = ((Collection) valuesObject).iterator(); itr.hasNext(); ) {
                                    Object val = itr.next();
                                    Binding unifier = new BindingPair(context, val);
                                    unifier.add(var, obj);
                                    values.add(unifier);
                                }
                            } else if (null != context) {
                                for (final Iterator itr = ((Collection) valuesObject).iterator(); itr.hasNext(); ) {
                                    Object val = itr.next();
                                    Binding unifier = new BindingPair(context, val);
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
                                Binding unifier = new BindingPair(context, l);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != context) {
                                Binding unifier = new BindingPair(context, l);
                                values.add(unifier);
                            } else {
                                values.add(l);
                            }
                        } else {
                            if (null != var) {
                                Binding unifier = new BindingPair(context, valuesObject);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != context) {
                                Binding unifier = new BindingPair(context, valuesObject);
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
    
    private List evalEnumExpr(Node node, Expression expr)
    throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        List args = ((EnumConstant) expr).getArg();
        Expression enumExpr = (Expression) args.get(0);
        Expression literalExpr = (Expression) args.get(1);
        List enumObjs = eval(node, enumExpr);
        List literalObjs = eval(node, literalExpr);

        for (final Iterator eItr = enumObjs.iterator(); eItr.hasNext(); ) {
            Object eObj = eItr.next();
            EEnum enumeration = null;
            if (eObj instanceof String) {
                eObj = Util.findClassifierByName(ruleEval.nameMap, (String) eObj);
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
     * @param node
     * @param args
     * @return
     * @throws ResolutionException
     * @throws NotGroundException
     */
    private List evalAll(Node node, List args) throws ResolutionException, NotGroundException {
        List results = new ArrayList(args.size());
        Iterator itr = args.iterator();
        while (itr.hasNext()) {
            Expression arg = (Expression) itr.next();
            List vals = eval(node, arg);
            if (vals.size() == 1 && vals.get(0) instanceof WrappedVar) {
                AbstractVar var = ((WrappedVar) vals.get(0)).getVar();
                throw new NotGroundException(var.getName() + " is not bound.");
            }
            results.add(vals);
        }
        return results;
    }
    
    private Map methodCache = new HashMap();
    
    private Method resolveMethod(Object instance, String name, Object[] params) {
        Method method = null;
        
        Class[] rawTypes = new Class[params.length];
        boolean hasBoxedTypes = false;
        for (int j = 0; j < params.length; j++) {
            Class type = params[j].getClass();
            rawTypes[j] = type;
            if (Number.class.isAssignableFrom(type)) {
                hasBoxedTypes = true;
            }
        }
        
        Map namesToTypes = (Map) methodCache.get(instance.getClass());
        Map typesToMethods;
        if (null != namesToTypes) {
            typesToMethods = (Map) namesToTypes.get(name);
            if (null != typesToMethods) {
                method = (Method) typesToMethods.get(Arrays.asList(rawTypes));
            } else {
                typesToMethods = new HashMap();
                namesToTypes.put(name, typesToMethods);
            }
        } else {
            namesToTypes = new HashMap();
            typesToMethods = new HashMap();
            methodCache.put(instance.getClass(), namesToTypes);
            namesToTypes.put(name, typesToMethods);
        }
        
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
                typesToMethods.put(Arrays.asList(rawTypes), method);
            }
        }

        return method;
    }
    
    /**
     * Beware, this will find the first method (they are in an arbitrary order) that matches subject to
     * auto-unboxing...eg, teh choice between foo(int) and foo(Integer) is arbitrary
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

    private void invokeOperation(Object instance, String methodName, List ls, int i, Object[] params, Collection results, boolean collect)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Collection l = (Collection) ls.get(i);
        if (i < ls.size()-1) {
            for (final Iterator itr = l.iterator(); itr.hasNext(); ) {
                params[i] = itr.next();
                invokeOperation(instance, methodName, ls, i+1, params, results, collect);
            }
        } else {
            for (final Iterator itr = l.iterator(); itr.hasNext(); ) {
                params[i] = itr.next();
                
                Method method = resolveMethod(instance, methodName, params);

                if (null == method) {
                    warnNoMethod(instance, methodName);
                } else {
                    Object result = method.invoke(instance, params);
                    if (null != result) {
                        if (!collect && result instanceof Collection) { 
                            results.addAll((Collection) result);
                        } else {
                            results.add(result);
                        }
                    }
                }
            }
        }
    }

    private void warnNoMethod(Object instance, String methodName) {
        String message = "No such operation: ";
        if (instance instanceof EObject) {
            message += Util.getFullyQualifiedName(((EObject) instance).eClass()) + "." + methodName;
        } else {
            message += Util.getFullyQualifiedName(instance.getClass()) + "." + methodName;
        }
//        System.err.println(message);
        ruleEval.fireWarning(message);
    }
    
    private List callOperation(Node node, String operationName, Object instance, List args, boolean collect)
    throws ResolutionException, NotGroundException {
        List results = new ArrayList();

        // TODO check EMF reflective calling of operations
        // for now, use Java reflection :(

        try {
            List params = evalAll(node, args);
            if (params.size() > 0) {
                try {
                    invokeOperation(instance, operationName, params, 0, new Object[params.size()], results, collect);
                } catch (Exception e) {
                    e.printStackTrace();
                    ruleEval.fireWarning(e);
                    throw new ResolutionException(node, "Operation invocation "+ operationName + " failed", e);
                }
            } else {
                Object[] paramArray = params.toArray();

                Method method = resolveMethod(instance, operationName, paramArray);
                if (null == method) {
                    warnNoMethod(instance, operationName);
                } else {
                    Object result = method.invoke(instance, paramArray);
                    if (null != result) {
                        if (!collect && result instanceof Collection) { 
                            results.addAll((Collection) result);
                        } else {
                            results.add(result);
                        }
                    }
                }
            }
            // System.out.println("\t" + operationName + "(...) = " + results);   // TODO delete
        } catch (SecurityException e) {
            e.printStackTrace();
            ruleEval.fireWarning(e);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//            ruleEval.fireWarning(e);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
//            System.err.println(method);        // TODO delete
//            System.err.println("  " + args);
            ruleEval.fireWarning(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            ruleEval.fireWarning(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            ruleEval.fireWarning(e);
        }
        
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

    Object fetchFeature(Node node, String featureName, Object obj) throws ResolutionException {
        Object valuesObject = null;
        try {
            if (obj instanceof EObject) {
                EObject instance = (EObject) obj;
                try {
                    EStructuralFeature eFeature = AbstractResolver.getFeature(node, instance.eClass(), featureName);
                    valuesObject = instance.eGet(eFeature);

                    if (valuesObject != null || instance.eIsSet(eFeature) || !eFeature.isRequired()) {
                        ExtentUtil.highlightEdge(instance, valuesObject, ExtentUtil.FEATURE_LOOKUP);
                    } else {
                        ruleEval.fireWarning(Util.getFullyQualifiedName(eFeature) + " is not set and no default value");
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
                throw new ResolutionException(node, "Could not find a source of values for '" + featureName + "' in '" + obj + "'", e);
            }
        } catch (ResolutionException e) {
            ruleEval.fireWarning(e);
        }

        return valuesObject;
    }
    
    private void accumulate(Function f, List ls, Collection results) throws ResolutionException {
        accumulate(f, ls, 0, new Object[ls.size()], results);
    }
    
    private void accumulate(Function f, List ls, int i, Object[] params, Collection results) throws ResolutionException {
        Collection l = (Collection) ls.get(i);
        if (i < ls.size()-1) {
            for (final Iterator itr = l.iterator(); itr.hasNext(); ) {
                params[i] = itr.next();
                accumulate(f, ls, i+1, params, results);
            }
        } else {
            for (final Iterator itr = l.iterator(); itr.hasNext(); ) {
                params[i] = itr.next();
                results.add(f.call(params));
            }
        }
    }   

}
