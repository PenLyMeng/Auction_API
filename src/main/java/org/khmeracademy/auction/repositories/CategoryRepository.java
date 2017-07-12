package org.khmeracademy.auction.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.auction.entities.Category;
import org.khmeracademy.auction.entities.CategoryInputUpdate;
import org.khmeracademy.auction.filtering.CategoryFilter;
import org.khmeracademy.auction.filtering.SupplierFilter;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {
	//READ
	String R_CATEGORIES="SELECT * FROM auc_category WHERE status != '2' AND parent_id IN (NULL,0)";
	String R_ALL_CATEGORIES="SELECT * FROM auc_category WHERE status != '2'"
			+ "AND LOWER(category_name) LIKE LOWER('%' || #{filter.categoryName} || '%')" + "ORDER BY category_id DESC "
			+ "LIMIT #{pagination.limit} " + "OFFSET #{pagination.offset} ";
	String R_CATEGORY_ByID="SELECT c.* , p.category_name parent_name FROM auc_category c LEFT JOIN auc_category p ON c.parent_id = p.category_id WHERE c.category_id = #{category_id}";
	
	String R_SUB_CATEGORIES = "SELECT * FROM auc_category WHERE status !='2' AND parent_id = #{category_id}";
	
	String R_CATEGORY_ByNAME="SELECT * FROM auc_category WHERE category_name = #{category_name}";
	String R_CATEGORY_ByPARENT_CATEGORY=
					"	SELECT	"+
					"	c.category_id,	"+
					"	c.category_name,	"+
					"	c.category_description,	"+
					"	c.parent_id,	"+
					"	c.status	"+
					"	FROM auc_category c INNER JOIN auc_category P	"+
					"	ON c.parent_id = p.category_id	"+
					"	where p.category_name = #{parent_name}";


	String R_CATEGORY_ByAnyFIELD="SELECT * FROM auc_category WHERE #{str_search} IN (category_id::TEXT, category_name, category_description, parent_id::TEXT, status::TEXT)";
	
	String C_CATEGORY= " INSERT INTO "
			+ " auc_category( "
			+ "		category_name,"
			+ "		category_description,"
			+ "		parent_id, "
			+ "		status) "
			+ " VALUES( "
			+ " 	#{category_name}, "
			+ "		#{category_description}, "
			+ "		#{parent_id}, "
			+ "		#{status})"; 
	String U_CATEGORY= "UPDATE auc_category "
			+ " SET "
			+ " 	category_name = #{category_name}, "
			+ " 	category_description = #{category_description}, "
			+ " 	parent_id = #{parent_id}, "
			+ " 	status = #{status} "
			+ " WHERE "
			+ "		category_id = #{category_id}";
	String D_CATEGORY="UPDATE auc_category SET status = '2' WHERE category_id = #{category_id}";
	
	@Select(R_ALL_CATEGORIES)	
	public ArrayList<CategoryInputUpdate> findAllCategories(@Param("filter") CategoryFilter filter, @Param("pagination") Pagination pagination);
	
	@Select(R_CATEGORIES)
	@Results(value={
			@Result(property="category_id", column="category_id"),
			@Result(property="parentCategory.category_id", column="parent_id"),
			@Result(property="subCategories", javaType=List.class, column="category_id",
					many=@Many(select="findAllSubCategories"))
	})
	public ArrayList<Category> findMainCategories();
	
	@Select(R_SUB_CATEGORIES)
	public ArrayList<Category> findAllSubCategories(int category_id);
	
	@Select(R_CATEGORY_ByNAME)
	public ArrayList<Category> findCategoryByName(String category_name); 
	
	@Select(R_CATEGORY_ByPARENT_CATEGORY)
	public ArrayList<Category> findCategoryByParentCategory(String parent_name); 
 
	@Select(R_CATEGORY_ByAnyFIELD)
	public ArrayList<Category> findCategoryByAnyField(String str_search);
	
	@Insert(C_CATEGORY)
	public boolean addCategory(CategoryInputUpdate c);
	
	@Update(U_CATEGORY)
	public boolean updateCategory(CategoryInputUpdate c); 
	
	@Delete(D_CATEGORY)
	public boolean deleteCategory(int category_id);
	
	@Select(R_CATEGORY_ByID)
	public CategoryInputUpdate findCategoryById(int category_id);
	
	@Select("SELECT COUNT(category_id) FROM auc_category WHERE LOWER(category_name) LIKE LOWER('%' || #{filter.categoryName} || '%')")
	public Long count(@Param("filter") CategoryFilter filter);
}
