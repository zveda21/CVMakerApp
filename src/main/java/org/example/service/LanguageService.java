package org.example.service;

import org.example.request.LanguageRequest;
import org.example.response.LanguageResponse;

public interface LanguageService {

    void addLanguage(LanguageRequest request, int userId);

    LanguageResponse getAllLanguages(int userId);

}
