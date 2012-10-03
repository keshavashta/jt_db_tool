package indexer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.Fields;
import com.greenapplesolutions.dbloader.domain.HeadnoteAndHeld;
import com.greenapplesolutions.dbloader.domain.Judgement;
import com.greenapplesolutions.lawsearch.config.LuceneConfig;

public class CaseIndexer {

	private LuceneConfig config;

	public CaseIndexer() {
		this.config = LuceneConfig.INSTANCE();
	}

	public void indexJudgements(List<Judgement> judgements) {

		try {

			IndexWriter documentWriter = config.getWriter();
			List<Document> documents = new ArrayList<Document>();
			for (Judgement judgement : judgements) {

				Document doc = new Document();

				doc.add(new Field(Fields.DocumentType, Judgement.DocumentType,
						Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

				doc.add(new Field(Fields.Court, judgement.Court,
						Field.Store.YES, Field.Index.ANALYZED));

				// for auto-complete
				doc.add(new Field(Fields.UCourt, judgement.Court,
						Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

				doc.add(new Field(Fields.Judges, judgement.Judges,
						Field.Store.YES, Field.Index.ANALYZED));

				// for auto-complete
				doc.add(new Field(Fields.UJudge, judgement.Judges,
						Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

				doc.add(new Field(Fields.Advocates, judgement.Advocates,
						Field.Store.YES, Field.Index.ANALYZED));

				// for auto complete
				doc.add(new Field(Fields.UAdvocate, judgement.Advocates,
						Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

				doc.add(new Field(Fields.Appellant, judgement.Appellant,
						Field.Store.YES, Field.Index.ANALYZED));

				// for auto complete
				doc.add(new Field(Fields.UAppellant, judgement.Appellant,
						Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

				doc.add(new NumericField(Fields.CaseDate, Field.Store.YES, true)
						.setLongValue(judgement.CaseDate.getTime()));

				doc.add(new Field(Fields.CaseNumber, judgement.CaseNumber,
						Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

				doc.add(new Field(Fields.FullText, judgement.FullText,
						Field.Store.NO, Field.Index.ANALYZED));

				doc.add(new Field(Fields.CFullText, Util
						.encryptText(judgement.FullText), Field.Store.YES,
						Field.Index.ANALYZED));

				// doc.add(new Field(Fields.Headnote, judgement.Headnote,
				// Field.Store.NO, Field.Index.ANALYZED));
				//
				// doc.add(new Field(Fields.CHeadnote, Util
				// .encryptText(judgement.Headnote), Field.Store.YES,
				// Field.Index.NO));

				doc.add(new Field(Fields.Respondant, judgement.Respondant,
						Field.Store.YES, Field.Index.ANALYZED));

				doc.add(new Field(Fields.URespondant, judgement.Respondant,
						Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

				doc.add(new Field(Fields.Subject, "", Field.Store.YES,
						Field.Index.ANALYZED));

				doc.add(new Field(Fields.Keycode, judgement.Keycode,
						Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

				doc.add(new NumericField(Fields.Bench, Field.Store.YES, true)
						.setIntValue(judgement.Bench));
				for (HeadnoteAndHeld hh : judgement.headnotesAndHelds) {
					Document citationDoc = new Document();
					citationDoc.add(new Field(Fields.DocumentType,
							Citation.DocumentType, Field.Store.YES,
							Field.Index.ANALYZED_NO_NORMS));
					citationDoc.add(new Field(Fields.Headnote, hh.Headnote,
							Field.Store.NO, Field.Index.ANALYZED));
					citationDoc.add(new Field(Fields.CHeadnote, Util
							.encryptText(hh.Headnote), Field.Store.YES,
							Field.Index.NO));

					citationDoc.add(new Field(Fields.Held, hh.Held,
							Field.Store.NO, Field.Index.ANALYZED));
					citationDoc.add(new Field(Fields.CHeld, Util
							.encryptText(hh.Held), Field.Store.YES,
							Field.Index.NO));
					citationDoc.add(new Field(Fields.Keycode, hh.Keycode,
							Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

					documents.add(citationDoc);
				}
				for (Citation citation : judgement.Citations) {
					Document citationDoc = new Document();
					citationDoc.add(new Field(Fields.DocumentType,
							Citation.DocumentType, Field.Store.YES,
							Field.Index.ANALYZED_NO_NORMS));
					citationDoc.add(new Field(Fields.Journal, citation.Journal,
							Field.Store.YES, Field.Index.ANALYZED));

					citationDoc.add(new Field(Fields.UJournal,
							citation.Journal, Field.Store.YES,
							Field.Index.NOT_ANALYZED_NO_NORMS));

					citationDoc.add(new Field(Fields.Volume, citation.Volume,
							Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

					citationDoc
							.add(new Field(Fields.UVolume, citation.Volume,
									Field.Store.YES,
									Field.Index.NOT_ANALYZED_NO_NORMS));

					citationDoc.add(new NumericField(Fields.Year, 1,
							Field.Store.YES, true).setLongValue(citation.Year));
					citationDoc.add(new NumericField(Fields.Page, 1,
							Field.Store.YES, true).setLongValue(citation.Page));
					citationDoc.add(new Field(Fields.Keycode, citation.keycode,
							Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

					documents.add(citationDoc);
				}

				documents.add(doc);

				System.out.println("Added document with keycode : "
						+ judgement.Keycode);

			}

			System.out.println("Start committing to index for "
					+ documents.size() + "documents");

			documentWriter.addDocuments(documents);
			documentWriter.commit();

			System.out.println("Finish commit to index ");

			documents.clear();
		} catch (CorruptIndexException e) {
			e.printStackTrace();

		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
