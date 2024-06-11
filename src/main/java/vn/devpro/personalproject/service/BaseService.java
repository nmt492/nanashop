package vn.devpro.personalproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.personalproject.model.BaseModel;
import vn.devpro.personalproject.model.Category;

@Service
public abstract class BaseService<E extends BaseModel> {

	@PersistenceContext
	EntityManager entityManager;
	
	public abstract Class<E> clazz();
	
	//lay 1 ban ghi theo id
	public E getById(int id) {
		return entityManager.find(clazz(), id);
	}
	
	//lay tat ca cac ban ghi trong 1 table
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Table table = clazz().getAnnotation(Table.class);
		return (List<E>) entityManager.createNativeQuery("SELECT * FROM " + table.name(), clazz()).getResultList();
	}
	
	
	//them moi hoac sua 1 ban ghi
	@Transactional
	public E saveOrUpdate(E entity) {
		if (entity.getId() == null || entity.getId() <= 0) { //Add new entity
			entityManager.persist(entity);
			return entity;
		}
		else { //update entity
			return entityManager.merge(entity);
		}
	}
	//xoa mot ban ghi theo entity
	public void delete(E entity) {
		entityManager.remove(entity);
		
	}
	
	//delete theo id
	public void deleteById(int id) {
		E entity = this.getById(id)
;
		delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> executeNativeSql(String sql) {
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<E>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<E> executeNativeSql(String sql, int currentPage, int sizeOfPage) {
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			query.setFirstResult((currentPage-1)*sizeOfPage);// ban ghi dau trang
			query.setMaxResults(sizeOfPage);//so ban ghi tren 1 trang
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<E>();
		}
	}
	
	//get entity
	public E getEntityByNativeSQL(String sql) {
		List<E> list = executeNativeSql(sql);
		if(list.size() > 0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	// Lấy tất cả sản phẩm theo category
    @SuppressWarnings("unchecked")
    public List<E> findByCategory(Category category) {
        try {
            Table table = clazz().getAnnotation(Table.class);
            String sql = "SELECT * FROM " + table.name() + " WHERE category_id = :categoryId";
            Query query = entityManager.createNativeQuery(sql, clazz());
            query.setParameter("categoryId", category.getId());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<E>();
        }
    }

    // Lấy tất cả sản phẩm theo category và phân trang
    @SuppressWarnings("unchecked")
    public List<E> findByCategoryWithPagination(Category category, int currentPage, int sizeOfPage) {
        try {
            Table table = clazz().getAnnotation(Table.class);
            String sql = "SELECT * FROM " + table.name() + " WHERE category_id = :categoryId";
            Query query = entityManager.createNativeQuery(sql, clazz());
            query.setParameter("categoryId", category.getId());
            query.setFirstResult((currentPage - 1) * sizeOfPage);
            query.setMaxResults(sizeOfPage);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<E>();
        }
    }

}
