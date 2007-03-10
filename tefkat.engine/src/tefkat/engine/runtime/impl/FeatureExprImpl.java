/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tefkat.engine.runtime.impl;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tefkat.engine.runtime.Binding;
import tefkat.engine.runtime.BindingPair;
import tefkat.engine.runtime.Context;
import tefkat.engine.runtime.Expression;
import tefkat.engine.runtime.FeatureExpr;
import tefkat.engine.runtime.Function;
import tefkat.engine.runtime.NotGroundException;
import tefkat.engine.runtime.ResolutionException;
import tefkat.engine.runtime.RuntimePackage;
import tefkat.engine.runtime.StringConstant;
import tefkat.engine.runtime.Var;
import tefkat.engine.runtime.WrappedVar;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tefkat.engine.runtime.impl.FeatureExprImpl#isOperation <em>Operation</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.FeatureExprImpl#isCollect <em>Collect</em>}</li>
 *   <li>{@link tefkat.engine.runtime.impl.FeatureExprImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureExprImpl extends CompoundExprImpl implements FeatureExpr {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright michael lawley 2004";

    /**
     * The default value of the '{@link #isOperation() <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOperation()
     * @generated
     * @ordered
     */
    protected static final boolean OPERATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOperation() <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isOperation()
     * @generated
     * @ordered
     */
    protected boolean operation = OPERATION_EDEFAULT;

    /**
     * The default value of the '{@link #isCollect() <em>Collect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCollect()
     * @generated
     * @ordered
     */
    protected static final boolean COLLECT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCollect() <em>Collect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCollect()
     * @generated
     * @ordered
     */
    protected boolean collect = COLLECT_EDEFAULT;

    /**
     * The cached value of the '{@link #getFeature() <em>Feature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFeature()
     * @generated
     * @ordered
     */
    protected Expression feature = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FeatureExprImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return RuntimePackage.Literals.FEATURE_EXPR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isOperation() {
        return operation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOperation(boolean newOperation) {
        boolean oldOperation = operation;
        operation = newOperation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.FEATURE_EXPR__OPERATION, oldOperation, operation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCollect() {
        return collect;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCollect(boolean newCollect) {
        boolean oldCollect = collect;
        collect = newCollect;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.FEATURE_EXPR__COLLECT, oldCollect, collect));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getFeature() {
        return feature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFeature(Expression newFeature, NotificationChain msgs) {
        Expression oldFeature = feature;
        feature = newFeature;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuntimePackage.FEATURE_EXPR__FEATURE, oldFeature, newFeature);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFeature(Expression newFeature) {
        if (newFeature != feature) {
            NotificationChain msgs = null;
            if (feature != null)
                msgs = ((InternalEObject)feature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.FEATURE_EXPR__FEATURE, null, msgs);
            if (newFeature != null)
                msgs = ((InternalEObject)newFeature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuntimePackage.FEATURE_EXPR__FEATURE, null, msgs);
            msgs = basicSetFeature(newFeature, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.FEATURE_EXPR__FEATURE, newFeature, newFeature));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RuntimePackage.FEATURE_EXPR__FEATURE:
                return basicSetFeature(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RuntimePackage.FEATURE_EXPR__OPERATION:
                return isOperation() ? Boolean.TRUE : Boolean.FALSE;
            case RuntimePackage.FEATURE_EXPR__COLLECT:
                return isCollect() ? Boolean.TRUE : Boolean.FALSE;
            case RuntimePackage.FEATURE_EXPR__FEATURE:
                return getFeature();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case RuntimePackage.FEATURE_EXPR__OPERATION:
                setOperation(((Boolean)newValue).booleanValue());
                return;
            case RuntimePackage.FEATURE_EXPR__COLLECT:
                setCollect(((Boolean)newValue).booleanValue());
                return;
            case RuntimePackage.FEATURE_EXPR__FEATURE:
                setFeature((Expression)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case RuntimePackage.FEATURE_EXPR__OPERATION:
                setOperation(OPERATION_EDEFAULT);
                return;
            case RuntimePackage.FEATURE_EXPR__COLLECT:
                setCollect(COLLECT_EDEFAULT);
                return;
            case RuntimePackage.FEATURE_EXPR__FEATURE:
                setFeature((Expression)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RuntimePackage.FEATURE_EXPR__OPERATION:
                return operation != OPERATION_EDEFAULT;
            case RuntimePackage.FEATURE_EXPR__COLLECT:
                return collect != COLLECT_EDEFAULT;
            case RuntimePackage.FEATURE_EXPR__FEATURE:
                return feature != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        
        List args = getArg();
        if (args.size() >= 1) {
            result.append(args.get(0));
            result.append('.');

            if ((getFeature() instanceof StringConstant)) {
                result.append(((StringConstant) getFeature()).getRepresentation());
            } else {
                result.append('$');
                result.append(getFeature());
            }
            
            if (isOperation()) {
                result.append('(');
                if (args.size() > 1) {
                    result.append(args.get(1));
                }
                for (int i = 2; i < args.size(); i++) {
                    result.append(", ");
                    result.append(args.get(i));
                }
                result.append(')');
            }
            if (isCollect()) {
                result.append("{}");
            }
        } else {
            result.append(super.toString());
        }
        
        return result.toString();
    }

    public List eval(Context context, Binding binding) throws ResolutionException, NotGroundException {
        List values = new ArrayList();
        Collection featureNames = getFeature().eval(context, binding);
        List args = getArg();
        List objs = ((Expression) args.get(0)).eval(context, binding);
        
        for (final Iterator fItr = featureNames.iterator(); fItr.hasNext(); ) {
            Object fObj = fItr.next();
            Binding featureContext = null;

            if (fObj instanceof WrappedVar) {
                Var var = ((WrappedVar) fObj).getVar();
                throw new NotGroundException(
                    "Unsupported mode (unbound '" + var.getName() + "') for FeatureExpr: " + var.getName() + "." + getFeature());
            } else if (fObj instanceof BindingPair) {
                featureContext = (Binding) fObj;
                fObj = ((BindingPair) fObj).getValue();
            }

            if (fObj instanceof EStructuralFeature) {
                // TODO FIXME this is a HACK
                fObj = ((EStructuralFeature) fObj).getName();
            } else if (!(fObj instanceof String)) {
                throw new ResolutionException(null, "The Feature Expression " + this + " must evaluate to a feature name of type String, not " + fObj.getClass());
            }
            String featureName = (String) fObj;
            
            // Expand a typed variable if possible
            //
            Var var = null;
            if (objs.size() == 1 && objs.get(0) instanceof WrappedVar) {
                WrappedVar wVar = (WrappedVar) objs.get(0);
                objs = context.expand(wVar);
                var = wVar.getVar();
            }
            
            for (final Iterator objItr = objs.iterator(); objItr.hasNext(); ) {
                Object obj = objItr.next();
                Binding objectContext = featureContext;
                
                if (obj instanceof BindingPair) {
                    if (null == objectContext) {
                        objectContext = (Binding) obj;
                    } else {
                        objectContext = new Binding(objectContext);
                        objectContext.composeLeft((Binding) obj);
                    }
                    obj = ((BindingPair) obj).getValue();
                }
//                    System.err.println("TGT OBJ: " + obj + "\t" + obj.getClass());

                // If we're transforming a Transformation, then we will bind to
                // AbstractVars so we need to wrap our AbstractVars to so that we
                // can distinguish unbound _variables_ from _objects_.

                if (isOperation()) {
                    Collection valuesObject = callOperation(context, binding, featureName, obj, args.subList(1, args.size()), isCollect());
                    if (null != var) {
                        for (final Iterator itr = valuesObject.iterator(); itr.hasNext(); ) {
                            Object val = itr.next();
                            Binding unifier = new BindingPair(objectContext, val);
                            unifier.add(var, obj);
                            values.add(unifier);
                        }
                    } else if (null != objectContext) {
                        for (final Iterator itr = valuesObject.iterator(); itr.hasNext(); ) {
                            Object val = itr.next();
                            Binding unifier = new BindingPair(objectContext, val);
                            values.add(unifier);
                        }
                    } else {
                        values.addAll(valuesObject);
                    }
                } else {
                    Object valuesObject = context.fetchFeature(featureName, obj);
                    
                    if (null != valuesObject && valuesObject.getClass().isArray()) {
                        valuesObject = wrapArray(valuesObject);
                    }
                    
                    if (valuesObject instanceof Collection) {
                        if (isCollect()) {
                            if (null != var) {
                                Binding unifier = new BindingPair(objectContext, valuesObject);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != objectContext) {
                                Binding unifier = new BindingPair(objectContext, valuesObject);
                                values.add(unifier);
                            } else {
                                values.add(valuesObject);
                            }
                        } else {
                            if (null != var) {
                                for (final Iterator itr = ((Collection) valuesObject).iterator(); itr.hasNext(); ) {
                                    Object val = itr.next();
                                    Binding unifier = new BindingPair(objectContext, val);
                                    unifier.add(var, obj);
                                    values.add(unifier);
                                }
                            } else if (null != objectContext) {
                                for (final Iterator itr = ((Collection) valuesObject).iterator(); itr.hasNext(); ) {
                                    Object val = itr.next();
                                    Binding unifier = new BindingPair(objectContext, val);
                                    values.add(unifier);
                                }
                            } else {
                                values.addAll((Collection) valuesObject);
                            }
                        }
                    } else if (null != valuesObject) {
                        if (isCollect()) {
                            List l = new ArrayList(1);
                            l.add(valuesObject);
                            if (null != var) {
                                Binding unifier = new BindingPair(objectContext, l);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != objectContext) {
                                Binding unifier = new BindingPair(objectContext, l);
                                values.add(unifier);
                            } else {
                                values.add(l);
                            }
                        } else {
                            if (null != var) {
                                Binding unifier = new BindingPair(objectContext, valuesObject);
                                unifier.add(var, obj);
                                values.add(unifier);
                            } else if (null != objectContext) {
                                Binding unifier = new BindingPair(objectContext, valuesObject);
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
    
    private List callOperation(final Context context, Binding binding, final String operationName, final Object instance, List args, boolean collect)
    throws ResolutionException, NotGroundException {
        Function methodCall = new Function() {
            public Object call(Binding unifier, Object[] params) throws ResolutionException {
                Object result = null;
                
                // In the general case the param types could all be different and
                // thus invoke different methods so we cannot cache/lift this
                // method resolution call
                Method method = Context.resolveMethod(instance, operationName, params);

                if (null == method) {
                    warnNoMethod(context, instance, operationName);
                    result = Collections.EMPTY_LIST;
                } else {
                    try {
                        result = method.invoke(instance, params);
                    } catch (SecurityException e) {
                        context.warn(e);
                    } catch (IllegalArgumentException e) {
                        context.warn(e);
                    } catch (IllegalAccessException e) {
                        context.warn(e);
                    } catch (InvocationTargetException e) {
                        context.warn(e);
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
            ExprExpander expander = new ExprExpander(context, methodCall, binding, args, collect);
            results = expander.getResults();
        } else {
            results = new ArrayList();
            Object result = methodCall.call(null, null);
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

    private void warnNoMethod(Context context, Object instance, String methodName) {
        String message = "No such operation: ";
        if (instance instanceof EObject) {
            message += Context.getFullyQualifiedName(((EObject) instance).eClass()) + "." + methodName;
        } else {
            message += Context.getFullyQualifiedName(instance.getClass()) + "." + methodName;
        }
//        System.err.println(message);
        context.warn(message);
    }

    /**
     * Turn an array of things into a List of things, boxing primitive types as required.
     * 
     * @param valuesObject
     * @return
     */
    static private Object wrapArray(Object valuesObject) {
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
    
} //FeatureExprImpl
