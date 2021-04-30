import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.commons.io.IOUtils;
import org.json.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class NlpProcess {

    private final static int SENTENCE_SIZE = 50;
    private final static int N_SETENCE = 3;
    private final static int LIMIT = 500;
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

    public static String [][] resetArray(String [][] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                arr[i][j] = "";
            }
        }

        return arr;
    }

    private static String [][] joinChunks(String [][] tokens, String [][] chunks)
    {
        String joinedCHunks[][] = new String[N_SETENCE][SENTENCE_SIZE];
        int count = 0;

        resetArray(joinedCHunks);

        for (int i = 0; i < tokens.length; i++)
        {
            count = 0;
            for (int j = 0; j < tokens[i].length; j++)
            {
                if (count < tokens[i].length)
                {
                    if (chunks[i][count].charAt(0) == 'B')
                    {
                        joinedCHunks[i][j] = tokens[i][count] + " ";
                        count++;
                    }
                    else if (chunks[i][count].charAt(0) == 'O')
                    {
                        count++;
                    }
                    while (count != tokens[i].length && chunks[i][count].charAt(0) == 'I')
                    {
                        joinedCHunks[i][j] += tokens[i][count] + " ";
                        count++;
                    }
                }
            }
        }

        return joinedCHunks;
    }
    public static void main(String args[]) throws IOException {

        Charset charset = Charset.forName("UTF-8");

        /*
        str is the input file converted to a string
        str2 is a processed version of str that we will then use in the following steps
        */
        String str = readFile("myText", charset);

        String str2 = str.replace("(", "( ").replace(")", " )").replaceAll("[“”]", "\"").replace("\""," \" ");
        //System.out.println(str2);
        //InputStreamFactory isf = new MarkableFileInputStreamFactory(new File("myText"));

        /*
        Calling Setence Detector
        */

        String [] detectedSetence = sentenceDetect(str2);

        /*
        Calling Tokenizer
        */

        String [][] tokenized = tokenizer(detectedSetence);
        System.out.println(Arrays.deepToString(tokenized));
        /*
        Calling Pos Tagger
        */

        String [][] tagged = postTagger(tokenized);
        //System.out.println(Arrays.deepToString(tagged));

        //System.out.println(Arrays.deepToString(tag_words));

        /*
        Calling the Chunker
        */

        String [][] chunked = chunker(tokenized, tagged);
        //System.out.println(Arrays.deepToString(chunked));

        /*
        Calling the function that joins the chunked Strings
        */

        String [][] joinedChunks = joinChunks(tokenized, chunked);
        System.out.println(Arrays.deepToString(joinedChunks));

        /*
        TO DO

        Possibly change some multidimensional Arrays to Lists so the size just grows as we want instead of having a pre-made size
        */

        try {
            // url containing the word to be indexed
            String url = "https://api.conceptnet.io/c/en/" + "microsoft" +  "?offset=0&limit=" + LIMIT;

            // open HttpURLConnection
            HttpURLConnection hp = (HttpURLConnection) new URL(url).openConnection();
            // set to request method to get
            // not required since default
            hp.setRequestMethod("GET");
            // get the inputstream in the json format
            hp.setRequestProperty("Accept", "application/json");
            // get inputstream from httpurlconnection
            InputStream is = hp.getInputStream();
            // get text from inputstream using IOUtils
            String jsonText = IOUtils.toString(is, Charset.forName("UTF-8"));
            // get json object from the json String
            JSONObject json = new JSONObject(jsonText);
            // get the edges array from the JSONObject which contains all
            // content
            JSONArray edges = json.getJSONArray("edges");
            // goes through the edges array

            for (int i = 0; i < edges.length(); i++)
            {
                JSONObject obj = edges.getJSONObject(i);
                JSONObject start = obj.getJSONObject("start");
                JSONObject end = obj.getJSONObject("end");
                JSONObject rel = obj.getJSONObject("rel");
                Object label_start = start.get("label");
                Object label_end = end.get("label");
                Object label_rel = rel.get("label");

                if (!label_rel.toString().equals("ExternalURL"))
                {
                    if(label_rel.toString().equals("IsA"))
                    {
                        System.out.println(label_start + " IS " + label_end);
                    }
                    else
                    {
                        System.out.println(label_start + " IS " + label_end + " RELATION " + label_rel);
                    }
                }

                // convert the first object of the json array into a jsonobject
                // once it is a jsonobject you can use getString or getJSONArray
                // to continue in getting info
                //System.out.println(obj);
            }
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /*/
        TO DO

         Entender como chegar ao ID do JSON

         
        */
    }
}