import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class Test {

    final static int SENTENCE_SIZE = 50;
    final static int N_SETENCE = 3;
    private static String word;
    private static String tag;
    private static String [][] tag_words = new String [N_SETENCE][SENTENCE_SIZE];

    /*
    Inputs a file path and outputs the hole file on a String.

    NOTE: IF FILE TO BIG HEAP CAN OVERFLOW
    */
    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /*
   SETENCE DETECTOR!!!

   Inputs a String and outputs an array of Strings where each index is a corresponding String
    */
    private static String [] sentenceDetect(String str)
    {
        String [] sentences = null;

        try (InputStream modelIn = new FileInputStream("en-sent.bin"))
        {
            SentenceModel model = new SentenceModel(modelIn);

            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

            sentences = sentenceDetector.sentDetect(str);

            //System.out.println(Arrays.toString(sentences));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sentences;
    }

    /*
    TOKENIZOR!!!

    Inputs an array of String, that is the output of the SENTENCE DERECTOR
    Outputs an multidimensional array of Strings
    Each array contains a tokenized Sentence

    */
    private  static String [][] tokenizer(String [] str)
    {
        String allTokens [][] = new String[N_SETENCE][SENTENCE_SIZE];

        try (InputStream modelIn = new FileInputStream("en-token.bin"))
        {
            TokenizerModel model = new TokenizerModel(modelIn);
            Tokenizer tokenizer = new TokenizerME(model);

            for(int i = 0; i < str.length; i++)
            {
                String tokens[] = tokenizer.tokenize(str[i]);
                allTokens [i] = tokens;
            }

            //System.out.println(Arrays.deepToString(allTokens));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allTokens;
    }

    /*
    POS TAGGER
    */

    private static String [][] postTagger(String [][] str)
    {
        String posTag [][] = new String [N_SETENCE][SENTENCE_SIZE];

        try(InputStream modelIn = new FileInputStream("en-pos-maxent.bin"))
        {
            POSModel model = new POSModel(modelIn);

            POSTaggerME tagger = new POSTaggerME(model);

            for(int i = 0; i < str.length; i++)
            {
                if (str[i][i] != null)
                {
                    String tag[] = tagger.tag(str[i]);

                    for(int j = 0; j < str[i].length; j++)
                    {
                        tag_words[i][j] = str[i][j] + " " + tag[j];
                    }
                    posTag[i] = tag;
                }

            }

            //System.out.println(Arrays.deepToString(posTag));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return posTag;
    }

    /*
    CHUNKER
    */
    private static String [][] chunker(String [][] tokens, String [][] tags)
    {
        String chunked [][] = new String[N_SETENCE][SENTENCE_SIZE];

        try (InputStream modelIn = new FileInputStream("en-chunker.bin"))
        {
            ChunkerModel model = new ChunkerModel(modelIn);

            ChunkerME chunker = new ChunkerME(model);

            for(int i = 0; i < tokens.length; i++)
            {
                chunked[i] = chunker.chunk(tokens[i], tags[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chunked;
    }

    public static void main(String args[]) throws IOException {
        /*POSModel model = new POSModelLoader()
                .load(new File("en-pos-perceptron.bin"));
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSTaggerME tagger = new POSTaggerME(model);

        Charset charset = Charset.forName("UTF-8");
        InputStreamFactory isf = new MarkableFileInputStreamFactory(new File("myText"));
        ObjectStream<String> lineStream = new PlainTextByLineStream(isf, charset);

        perfMon.start();
        String line;
        while ((line = lineStream.read()) != null) {

            String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
                    .tokenize(line);
            String[] tags = tagger.tag(whitespaceTokenizerLine);

            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
            System.out.println(sample.toString());

            perfMon.incrementCounter();
        }
        perfMon.stopAndPrintFinalResult();*/

        /*
        ----------------------------------------------------------------------------------------
        ----------------------------------------------------------------------------------------
        ----------------------------------------------------------------------------------------
        ----------------------------------------------------------------------------------------
         */


        Charset charset = Charset.forName("UTF-8");

        /*
        str is the input file converted to a string
        str2 is a processed version of str that we will then use in the following steps
        */
        String str = readFile("myText", charset);

        String str2 = str.replace("(", "( ").replace(")", " )").replaceAll("[“”]", "\"").replace("\""," \" ");
        System.out.println(str2);
        //InputStreamFactory isf = new MarkableFileInputStreamFactory(new File("myText"));

        /*
        Calling Setence Detector
        */

        String [] detectedSetence = sentenceDetect(str2);

        /*
        Calling Tokenizer
        */

        String [][] tokenized = tokenizer(detectedSetence);
        //System.out.println(Arrays.deepToString(tokenized));
        /*
        Calling Pos Tagger
        */

        String [][] tagged = postTagger(tokenized);
        //System.out.println(Arrays.deepToString(tagged));

        System.out.println(Arrays.deepToString(tag_words));

        /*
        Calling the Chunker
        */

        String [][] chunked = chunker(tokenized, tagged);
        System.out.println(Arrays.deepToString(chunked));

        /*
        TO DO

        Possibly change some multidimensional Arrays to Lists so the size just grows as we want instead of having a pre-made size
        */
    }
}

