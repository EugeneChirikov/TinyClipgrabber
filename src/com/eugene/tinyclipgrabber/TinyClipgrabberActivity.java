package com.eugene.tinyclipgrabber;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.widget.TextView;
import java.io.FileOutputStream;

public class TinyClipgrabberActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        CharSequence pasteData = "";
        Context context = getApplicationContext();
        if (!clipboard.hasPrimaryClip())
        {
        	Toast.makeText(context, "Clipboard is empty", Toast.LENGTH_LONG).show();
        	return;
        }
        if (!clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
        {
        	Toast.makeText(context, "Clipboard doesn't have plain text", Toast.LENGTH_LONG).show();
        	return;
        }
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
        pasteData = item.getText();
        if (pasteData == null)
        {
        	Toast.makeText(context, "Text is null", Toast.LENGTH_LONG).show();
        	return;
        }
//        output to screen
        TextView view = (TextView)findViewById(R.id.mTextView);
        view.setText(pasteData);
//        output to file
        FileOutputStream ofstream;
        try {
        	ofstream = openFileOutput("clipgrabber.txt", Context.MODE_WORLD_READABLE);
        	ofstream.write(pasteData.toString().getBytes());
        	ofstream.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }    
}