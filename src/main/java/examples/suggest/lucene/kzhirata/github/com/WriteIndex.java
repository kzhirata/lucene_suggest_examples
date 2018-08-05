package examples.suggest.lucene.kzhirata.github.com;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class WriteIndex {

    public void createIndex() throws Exception {
        //インデックスの登録先となるディレクトリのパス
    	Path path = Paths.get("index");

        Directory dir = null;
        Analyzer analyzer = null;
        IndexWriter writer = null;

        try {
            dir = FSDirectory.open(path);
            analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(OpenMode.CREATE);
            writer = new IndexWriter(dir, config);

            Document doc = null;
            doc = new Document();
            doc.add(new TextField("txt1", "ab cd ef", Field.Store.YES));
            doc.add(new StringField("str1", "gh ij kl", Field.Store.YES));
            writer.addDocument(doc);
            doc = new Document();
            doc.add(new TextField("txt1", "abc def ghi", Field.Store.YES));
            doc.add(new StringField("str1", "jkl mno pqr", Field.Store.YES));
            writer.addDocument(doc);
            doc = new Document();
            doc.add(new TextField("txt1", "abcd efgh ijkl", Field.Store.YES));
            doc.add(new StringField("str1", "mnop qrst uvwx", Field.Store.YES));
            writer.addDocument(doc);
            writer.commit();
            //登録したインデックスの確認
            //checkIndex(writer);
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (analyzer != null) {
                analyzer.close();
            }
            if (dir != null) {
                dir.close();
            }
        }
    }

}
