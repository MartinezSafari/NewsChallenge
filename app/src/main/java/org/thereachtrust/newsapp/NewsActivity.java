package org.thereachtrust.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> titles;
    ArrayList<String> links;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView= (ListView) findViewById(R.id.listView);

        titles= new ArrayList<String>();
        links= new ArrayList<String>();

        listView.setOnItemClickListener((parent, view, position, id) -> {

            Uri uri= Uri.parse(links.get(position));
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);

        });

        new ProcessinBackground().execute();
    }
    public InputStream getInputStream(URL url){
        try {
            return url.openConnection().getInputStream();
        }
        catch ( IOException e){
            return null;

        }
    }
    public class ProcessinBackground extends AsyncTask<Integer, Void, Exception>{

        ProgressDialog progressDialog= new ProgressDialog(NewsActivity.this);

        Exception exception=null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... params) {
            try {
                //retrive data from the Xml document
                URL url= new URL("feeds.bbci.co.uk/news/world/asia/rss.xml");

                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                //Extract data from the Xml document
                XmlPullParser xpp= factory.newPullParser();

                xpp.setInput(getInputStream(url),"UTF_8");

                boolean insideItem= false;
                int eventType= xpp.getEventType();

                while (eventType !=XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        if(xpp.getName().equalsIgnoreCase("item")){
                            insideItem= true;
                        }
                        else if(xpp.getName().equalsIgnoreCase("title")){
                            if(insideItem){
                                titles.add(xpp.nextText());
                            }
                        }
                        else if(xpp.getName().equalsIgnoreCase("link")){
                            if(insideItem){
                                links.add(xpp.nextText());
                            }
                        }
                    }
                    else if(eventType == XmlPullParser.END_TAG && xpp.getName()
                            .equalsIgnoreCase("item")){
                        insideItem= false;
                    }
                    eventType= xpp.next();
                }


            }
            catch (MalformedURLException e){
                exception=e;
                e.printStackTrace();

            }
            catch (XmlPullParserException e){
                exception=e;
                e.printStackTrace();
            }
            catch (IOException e){
                exception=e;
                e.printStackTrace();
            }

            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter= new ArrayAdapter<String>
                    (NewsActivity.this, android.R.layout.simple_list_item_1, titles);

            listView.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }
}