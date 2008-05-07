package tde.ui.old.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;

import tde.ui.old.model.primitives.Child;
import tde.ui.old.model.primitives.Parent;

public class TDEDomainModel implements Parent {
    private List<TDEProject> projects = new ArrayList<TDEProject>();

    public TDEDomainModel(IProject[] workspace) {
        for (IProject p : workspace) projects.add(new TDEProject(this, p));
    }

    public void aggregateChildren(List<Child> children) {
        for (TDEProject p : projects) if (p.isTefkatProject()) children.add(p);
    }

    public String name() {
        return "TDE Domain Model";
    }
}
