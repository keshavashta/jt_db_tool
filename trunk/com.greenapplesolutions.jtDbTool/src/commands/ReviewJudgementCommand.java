package commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.greenapplesolutions.dbloader.domain.Judgement;

import editorInput.JudgmentEditorInput;
import editors.JudgmentsEditor;

import readWriteDatabase.ReadJudgement;

public class ReviewJudgementCommand implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ReadJudgement ins = new ReadJudgement("jt_sc", "localhost", "root", "");
		if (ins.connectToDatabse()) {
			List<Judgement> judgements = ins.getJudgements();
			IEditorInput input = null;
			input = new JudgmentEditorInput("Review Judgements", judgements);

			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().openEditor(input, JudgmentsEditor.ID);

			} catch (PartInitException e3) {
				e3.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}