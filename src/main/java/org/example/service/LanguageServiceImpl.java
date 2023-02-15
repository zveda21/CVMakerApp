package org.example.service;

import org.example.model.Language;
import org.example.repository.LanguageRepositoryImpl;
import org.example.request.LanguageRequest;
import org.example.response.LanguageResponse;

import java.util.List;

public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepositoryImpl languageRepository;

    public LanguageServiceImpl(LanguageRepositoryImpl languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void addLanguage(LanguageRequest request, int userId) {
        String name = request.getName();
        Language language = new Language();
        language.setName(name);
        language.setUserId(userId);
        languageRepository.create(language);
    }

    @Override
    public LanguageResponse getAllLanguages(int userId) {
        LanguageResponse languageResponse = new LanguageResponse();
        List<Language> languageList = languageRepository.getAllLanguagesList(userId);
        languageResponse.setLanguageList(languageList);
        return languageResponse;
    }
}
