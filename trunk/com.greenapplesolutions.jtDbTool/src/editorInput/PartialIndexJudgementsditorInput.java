package editorInput;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.greenapplesolutions.jtdbtool.domain.Judgement;

public class PartialIndexJudgementsditorInput implements IEditorInput {

	private String name;
	private List<Judgement> judgements;
	public static final String ID = "com.greenapplesolutions.jtDbTool.partialIndexJudgementEditor";
	public PartialIndexJudgementsditorInput(String name) {
		this.name = name;
		
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
