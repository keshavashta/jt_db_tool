
package indexer;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.Fields;
import com.greenapplesolutions.dbloader.domain.Judgement;
import com.greenapplesolutions.lawsearch.config.LuceneConfig;
import java.io.*;
import java.util.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;
import org.jasypt.util.text.BasicTextEncryptor;

public class CaseIndexer
{

    public CaseIndexer()
    {
        config = LuceneConfig.INSTANCE();
    }

   
    public void indexJudgements(List judgements)
    {
        try
        {
            IndexWriter documentWriter = config.getWriter();
            List documents = new ArrayList();
            for(Iterator iterator = judgements.iterator(); iterator.hasNext(); documents.clear())
            {
                Judgement judgement = (Judgement)iterator.next();
                Document doc = new Document();
                doc.add(new Field(Fields.DocumentType, Judgement.DocumentType, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED_NO_NORMS));
                doc.add(new Field(Fields.Court, judgement.Court, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
                doc.add(new Field(Fields.UCourt, judgement.Court, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field(Fields.Judges, judgement.Judges, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
                doc.add(new Field(Fields.UJudge, judgement.Judges, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field(Fields.Advocates, judgement.Advocates, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
                doc.add(new Field(Fields.UAdvocate, judgement.Advocates, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field(Fields.Appellant, judgement.Appellant, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
                doc.add(new Field(Fields.UAppellant, judgement.Appellant, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field(Fields.Parties, judgement.Parties, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add((new NumericField(Fields.CaseDate, org.apache.lucene.document.Field.Store.YES, true)).setLongValue(judgement.CaseDate.getTime()));
                doc.add(new Field(Fields.CaseNumber, judgement.CaseNumber, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field(Fields.FullText, judgement.FullText, org.apache.lucene.document.Field.Store.NO, org.apache.lucene.document.Field.Index.ANALYZED));
                doc.add(new Field(Fields.CFullText, encryptText(judgement.FullText), org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
                doc.add(new Field(Fields.Respondant, judgement.Respondant, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
                doc.add(new Field(Fields.URespondant, judgement.Respondant, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field(Fields.Subject, judgement.Subject, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
//                doc.add((new NumericField(Fields.Keycode, 1, org.apache.lucene.document.Field.Store.YES, true)).setLongValue(judgement.Keycode));
                doc.add((new NumericField(Fields.Bench, org.apache.lucene.document.Field.Store.YES, true)).setIntValue(judgement.Bench));
                Document actDoc;
               
                Document citationDoc;
                for(Iterator iterator2 = judgement.Citations.iterator(); iterator2.hasNext(); documents.add(citationDoc))
                {
                    Citation citation = (Citation)iterator2.next();
                    citationDoc = new Document();
                    citationDoc.add(new Field(Fields.DocumentType, Citation.DocumentType, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED_NO_NORMS));
                    citationDoc.add(new Field(Fields.Journal, citation.Journal, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED));
                    citationDoc.add(new Field(Fields.UJournal, citation.Journal, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                    citationDoc.add(new Field(Fields.Volume, citation.Volume, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.ANALYZED_NO_NORMS));
                    citationDoc.add(new Field(Fields.UVolume, citation.Volume, org.apache.lucene.document.Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED_NO_NORMS));
                    citationDoc.add((new NumericField(Fields.Year, 1, org.apache.lucene.document.Field.Store.YES, true)).setLongValue(citation.Year));
                    citationDoc.add((new NumericField(Fields.Page, 1, org.apache.lucene.document.Field.Store.YES, true)).setLongValue(citation.Page));
//                    citationDoc.add((new NumericField(Fields.Keycode, 1, org.apache.lucene.document.Field.Store.YES, true)).setLongValue(judgement.Keycode));
                }

                documents.add(doc);
                documentWriter.addDocuments(documents);
            }

            documentWriter.commit();
        }
        catch(CorruptIndexException e)
        {
            e.printStackTrace();
        }
        catch(LockObtainFailedException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void optimize()
    {
        try
        {
            LuceneConfig.INSTANCE().getWriter().optimize();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

   

    private String encryptText(String plainText)
    {
        String userPassword = config.getPassword();
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(userPassword);
        String myEncryptedText = textEncryptor.encrypt(plainText);
        return myEncryptedText;
    }

    private LuceneConfig config;
}
