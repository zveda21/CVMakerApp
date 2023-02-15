package org.example.response;

import org.example.model.Language;

import java.util.List;

public class LanguageResponse {
    private List<Language> languageList;

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }
}
