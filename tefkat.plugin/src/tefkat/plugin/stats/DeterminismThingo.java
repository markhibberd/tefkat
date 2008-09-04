package tefkat.plugin.stats;

import java.util.Iterator;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import tefkat.model.Expression;
import tefkat.model.FeatureExpr;
import tefkat.model.InstanceRef;
import tefkat.model.MofInstance;
import tefkat.model.Transformation;
import tefkat.model.Var;
import tefkat.model.VarUse;
import tefkat.plugin.TefkatModelEditor.TefkatTextEditor;
import tefkat.plugin.dom.TefkatDOM;

public class DeterminismThingo {
    private TefkatTextEditor editor;

    public DeterminismThingo(TefkatTextEditor textEditor) {
        this.editor = textEditor;
    }


    private static boolean enabled = false;

    public static void setEnabled(boolean enabled) {
    	DeterminismThingo.enabled = enabled;

    }

	public void go(Resource res) {
		System.out.println("go");
    	if (!enabled) return;
    	TefkatDOM instance = TefkatDOM.getInstance(editor);

    	for (Iterator<EObject> iterator = res.getAllContents(); iterator.hasNext();) {
    		EObject o = iterator.next();
    		if (o instanceof FeatureExpr) doFeatureyStuff((FeatureExpr) o);
		}
	}

	private void doFeatureyStuff(FeatureExpr feature) {
		System.out.println("*************");
		for (Iterator<EObject> iterator = feature.eAllContents(); iterator.hasNext();) {
			EObject o = iterator.next();
			System.out.println(o.eClass() + ":::" + o.getClass() + "::: " + o);
			if (o instanceof VarUse) doVarUsoStuff((VarUse)o);
		}
	}

	private void doVarUsoStuff(VarUse use) {
		Var var = use.getVar();
		System.out.println("----prevar");
		extenty(var);
		EList<VarUse> usages = var.getUsages();
		System.out.println("----------------");
		usages(usages);

	}

	private void usages(EList<VarUse> usages) {
		for (VarUse use : usages) {
			EObject o = use.eContainer();
			if (o instanceof MofInstance) {
				doMof((MofInstance)o);
				return;
			}
			System.out.println(o.eClass() + ":::" + o.getClass() + "::: " + o);
		}
	}

	private void doMof(MofInstance mof) {
		System.out.println("starting mof");
		System.out.println("context");
		extenty(mof.getContext());

		System.out.println("extent");
		extenty(mof.getExtent());

		System.out.println("type name");
		Expression typeName = mof.getTypeName();
		print(typeName);
		if (typeName instanceof InstanceRef) {
			instanceo((InstanceRef) typeName);
		}

		System.out.println("trule-src");
		print(mof.getTRuleSrc());

		System.out.println("trule trg");
		print(mof.getTRuleTgt());

		System.out.println("instance");
		print(mof.getInstance());

		System.out.println("ending mof");
	}

	private void instanceo(InstanceRef typeName) {
		System.out.println("expression");
		print(typeName.getExpr());
		System.out.println("object");
		print(typeName.getObject());
		System.out.println("-------- THis is the thing");
	}

	private void extenty(Var extent) {
		extenty(extent, true);
	}
	private void extenty(Var extent, boolean doTransformt) {
		print(extent);

		System.out.println("scope");
		print(extent.getScope());
		if (extent.getScope() instanceof Transformation && doTransformt)
			transformy((Transformation) extent.getScope());
		System.out.println("extended");
		for (Iterator iterator = extent.getExtended().iterator(); iterator.hasNext();) {
			EObject type = (EObject) iterator.next();
			print(type);
		}

		System.out.println("extender");
		for (Iterator iterator = extent.getExtender().iterator(); iterator.hasNext();) {
			EObject type = (EObject) iterator.next();
			print(type);
		}

		System.out.println("superseeded");
		for (Iterator iterator = extent.getSuperseded().iterator(); iterator.hasNext();) {
			EObject type = (EObject) iterator.next();
			print(type);
		}

		System.out.println("superseeder");
		for (Iterator iterator = extent.getSuperseder().iterator(); iterator.hasNext();) {
			EObject type = (EObject) iterator.next();
			print(type);
		}

	}

	private void transformy(Transformation scope) {
		System.out.println("transformation))))))))))))))))))))))))))))");
		System.out.println(scope.getNamespaceDeclarations());
		for (Iterator iterator = scope.getVars().iterator(); iterator.hasNext();) {
			Var type = (Var) iterator.next();
			System.out.println(type.getName());
			for (Iterator iterator2 = type.eContents().iterator(); iterator2.hasNext();) {
				EObject next = (EObject) iterator2.next();
				print(next);
			}
			print(type.eContainer());
			print(type.eResource());
			extenty(type, false);
		}
	}

	private void print(Resource resource) {
		System.out.println("resource");
		System.out.println(resource);
		System.out.println(resource.getURI());
		System.out.println(resource.getResourceSet());
	}

	private void print(EObject o) {
		if (o == null) {
			System.out.println("not set");
			return;
		}
		System.out.println(o.getClass() + "---" + o);

	}
}


