package modelProvider;

import java.util.ArrayList;
import java.util.List;

import com.greenapplesolutions.dbloader.domain.Judgement;

public class JudgmentsEditorModelProvider {

	public JudgmentsEditorModelProvider(List<Judgement> judgements) {
		this.judgements = judgements;
//		
	}

	private List<Judgement> judgements;

	public List<Judgement> getJudgements() {
		return this.judgements;
	}

	public void setJudgements(List<Judgement> judgements) {
		this.judgements = judgements;
	}

}
