package com.greenapplesolutions.jtbbtool.config;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;

public class LuceneConfig
{

    public void setIndexPath(String indexPath)
    {
        this.indexPath = indexPath;
    }

    public void setDirectory(Directory directory)
    {
        this.directory = directory;
    }

    private LuceneConfig()
    {
    }

    public static synchronized LuceneConfig INSTANCE()
    {
        if(_instance == null)
            _instance = new LuceneConfig();
        return _instance;
    }

    public synchronized Analyzer getAnalyzer()
    {
        if(analyzer == null)
            analyzer = new EnglishAnalyzer(getVersion());
        return analyzer;
    }

    public synchronized IndexReader getDictionaryReader()
        throws IOException
    {
        if(dictionaryReader == null)
            dictionaryReader = IndexReader.open(getDictionaryWriter(), false);
        return dictionaryReader;
    }

    public synchronized IndexReader getReader()
        throws IOException
    {
        if(reader == null)
            reader = IndexReader.open(getWriter(), false);
        return reader;
    }

    public synchronized IndexReader reloadReader()
        throws IOException
    {
        reader = IndexReader.open(getWriter(), false);
        return reader;
    }

    public synchronized IndexWriter getDictionaryWriter()
        throws CorruptIndexException, LockObtainFailedException, IOException
    {
        if(dictionaryWriter == null)
        {
            IndexWriterConfig config = new IndexWriterConfig(getVersion(), getAnalyzer());
            config.setOpenMode(org.apache.lucene.index.IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            dictionaryWriter = new IndexWriter(getDictionaryDirectory(), config);
        }
        return dictionaryWriter;
    }

    public synchronized IndexWriter getWriter()
        throws CorruptIndexException, LockObtainFailedException, IOException
    {
        if(writer == null)
        {
            IndexWriterConfig config = new IndexWriterConfig(getVersion(), getAnalyzer());
            config.setOpenMode(org.apache.lucene.index.IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            writer = new IndexWriter(getDirectory(), config);
        }
        return writer;
    }

    public synchronized Directory getDirectory()
        throws IOException
    {
        if(directory == null)
            directory = FSDirectory.open(new File(indexPath));
        return directory;
    }

    public synchronized IndexSearcher getSearcher()
        throws IOException
    {
        if(indexSearcher == null)
            indexSearcher = new IndexSearcher(getReader());
        return indexSearcher;
    }

    public synchronized IndexSearcher getDictionarySearcher()
        throws IOException
    {
        if(dictionarySearcher == null)
            dictionarySearcher = new IndexSearcher(getDictionaryReader());
        return dictionarySearcher;
    }

    public synchronized IndexSearcher reloadSearcher()
        throws IOException
    {
        indexSearcher = new IndexSearcher(reloadReader());
        return indexSearcher;
    }

    public Version getVersion()
    {
        return Version.LUCENE_35;
    }

    public String getPassword()
    {
        return "*Jr2nP5DD!BN@Eg/a2dz-(+X)yWnFJV5cz!q9-r9QdzTzC=C9K39S/JKP-=2td62";
    }

    public Directory getDictionaryDirectory()
        throws IOException
    {
        if(dictionaryDirectory == null)
            dictionaryDirectory = FSDirectory.open(new File(getDictionaryIndexPath()));
        return dictionaryDirectory;
    }

    public String getDictionaryIndexPath()
    {
        return dictionaryIndexPath;
    }

    public void setDictionaryIndexPath(String dictionaryIndexPath)
    {
        this.dictionaryIndexPath = dictionaryIndexPath;
    }

    private IndexWriter writer;
    private IndexReader reader;
    private Analyzer analyzer;
    private String indexPath;
    private Directory directory;
    private IndexSearcher indexSearcher;
    private Directory dictionaryDirectory;
    private String dictionaryIndexPath;
    private IndexSearcher dictionarySearcher;
    private IndexReader dictionaryReader;
    private IndexWriter dictionaryWriter;
    private static LuceneConfig _instance;
}
