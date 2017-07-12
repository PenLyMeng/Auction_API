package org.khmeracademy.auction.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.Translate;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateRepository {
	final String FIND_ALL_TRANSLATE="SELECT * FROM auc_translate";
	@Select(FIND_ALL_TRANSLATE)
	public ArrayList<Translate> findAllTranslate();
	
	final String FIND_TRANSLATE_BY_BASE_WORD="SELECT * FROM auc_translate WHERE base_word = #{base_word} ";
	@Select(FIND_TRANSLATE_BY_BASE_WORD)
	public Translate findTranslateByBaseWord(String base_word);
	
	final String ADD_TRANSLATE=
			" INSERT INTO auc_translate(language,base_word,translate) "+ 
			" VALUES(#{language},#{base_word},#{translate}) ";
	@Insert(ADD_TRANSLATE)
	public boolean addTranslate(Translate t);
	
	final String UPDATE_TRANSLATE=
			" UPDATE auc_translate "+
			" SET language=#{language}, "+
			" base_word=#{base_word}, "+
			" translate=#{translate} "+
			" WHERE id = #{id}";
	@Update(UPDATE_TRANSLATE)
	public boolean updateTranslate(Translate t);
	
	final String DELETE_TRANSLATE="DELETE FROM auc_translate WHERE id = #{id}";	
	@Delete(DELETE_TRANSLATE)
	public boolean deleteTranslate(int id);


}
