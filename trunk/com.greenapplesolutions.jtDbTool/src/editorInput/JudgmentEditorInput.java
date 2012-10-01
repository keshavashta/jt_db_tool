package editorInput;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.greenapplesolutions.dbloader.domain.Judgement;

public class JudgmentEditorInput implements IEditorInput {

	private String name;
	private List<Judgement> judgements;

	public JudgmentEditorInput(String name, List<Judgement> judgements) {
		this.name = name;
		this.judgements = judgements;
	}

	public List<Judgement> getJudgements() {
		return this.judgements;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		return "";
	}

}
