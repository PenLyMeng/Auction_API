package org.khmeracademy.auction.services;

import java.util.ArrayList;


import org.khmeracademy.auction.entities.Translate;

public interface TranslateService {
	public ArrayList<Translate> findAllTranslate();	
	public Translate findTranslateByBaseWord(String base_word);	
	public boolean addTranslate(Translate t);
	public boolean updateTranslate(Translate t);	
	public boolean deleteTranslate(int id);
}
