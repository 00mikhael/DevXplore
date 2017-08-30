package com.example.gravity.devxplore.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gravity.devxplore.R;

import java.util.ArrayList;

/**
 * Created by gravity on 8/28/17.
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> languages;
    private LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, ArrayList<String> languages) {
        this.context = applicationContext;
        this.languages = languages;
        inflater = (LayoutInflater.from(applicationContext));
    }

    public void addLanguage(String language) {
        for (int i = 0; i < languages.size(); i++) {
            if (language.equals(languages.get(i))) {
                return;
            }
        }
        languages.add(language);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return languages.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_item_spinner, null);
        View languageColor = view.findViewById(R.id.language_color);
        TextView language = (TextView) view.findViewById(R.id.language_text);
        language.setText(languages.get(i));
        languageColor.setBackgroundResource(getColor(i));
        return view;
    }

    private int getColor(int position) {
        String language = languages.get(position);
        int langColorId;
        switch (language) {
            case "java":
                langColorId = R.color.Java;
                break;
            case "javaScript":
                langColorId = R.color.JavaScript;
                break;
            case "python":
                langColorId = R.color.Python;
                break;
            case "kotlin":
                langColorId = R.color.Kotlin;
                break;
            case "html":
                langColorId = R.color.HTML;
                break;
            case "css":
                langColorId = R.color.CSS;
                break;
            case "c":
                langColorId = R.color.C;
                break;
            case "c#":
                langColorId = R.color.CSharp;
                break;
            case "c++":
                langColorId = R.color.CPP;
                break;
            case "objective-c":
                langColorId = R.color.ObjectiveC;
                break;
            case "vue":
                langColorId = R.color.Vue;
                break;
            case "coffeescript":
                langColorId = R.color.CoffeeScript;
                break;
            case "go":
                langColorId = R.color.Go;
                break;
            case "swift":
                langColorId = R.color.Swift;
                break;
            case "f#":
                langColorId = R.color.FSharp;
                break;
            case "perl":
                langColorId = R.color.Perl;
                break;
            case "scala":
                langColorId = R.color.Scala;
                break;
            case "shell":
                langColorId = R.color.Shell;
                break;
            case "php":
                langColorId = R.color.PHP;
                break;
            case "ruby":
                langColorId = R.color.Ruby;
                break;
            default:
                langColorId = R.color.colorAccent;
                break;
        }
        return langColorId;
    }
}
