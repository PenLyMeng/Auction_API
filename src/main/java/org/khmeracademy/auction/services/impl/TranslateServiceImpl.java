package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Translate;
import org.khmeracademy.auction.repositories.TranslateRepository;
import org.khmeracademy.auction.services.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateServiceImpl implements TranslateService{
	@Autowired
	private TranslateRepository translateRepository;

	@Override
	public ArrayList<Translate> findAllTranslate() {
		return translateRepository.findAllTranslate();
	}

	@Override
	public Translate findTranslateByBaseWord(String base_word) {
		return translateRepository.findTranslateByBaseWord(base_word);
	}

	@Override
	public boolean addTranslate(Translate t) {
		return translateRepository.addTranslate(t);
	}

	@Override
	public boolean updateTranslate(Translate t) {
		return translateRepository.updateTranslate(t);
	}

	@Override
	public boolean deleteTranslate(int id) {
		return translateRepository.deleteTranslate(id);
	}
	
	
}
