package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.api.dto.ProductSearchRequest;
import com.mftplus.ecommerce.model.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductSearchRepository {

    private final EntityManager entityManager;

    public ProductSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> findAllByCriteria(ProductSearchRequest request){

        int pageNumber = request.getPageNumber();
        int pageSize = 10;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();


        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        //select from product
        Root<Product> root = criteriaQuery.from(Product.class);

        //name
        if (request.getName() != null) {
            Predicate namePredicate = criteriaBuilder
                    .like(root.get("name"), "%" + request.getName() + "%");
            predicates.add(namePredicate);
        }

        //categories
        if (request.getCategoryId() != null){
            Integer categoryId = request.getCategoryId();


                Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
                Root<Product> subQueryProduct = subquery.from(Product.class);
                Join<Category, Product> subQueryCategory = subQueryProduct.join("categories");

                subquery.select(subQueryProduct.get("id")).where(
                        criteriaBuilder.equal(subQueryCategory.get("id"), categoryId));

                predicates.add(criteriaBuilder.in(root.get("id")).value(subquery));

        }

        //brands
        if (request.getBrandName() != null){
            Join<Product, Brand> brandJoin = root.join("brand");

            Predicate brandPredicate = criteriaBuilder
                    .like(brandJoin.get("name"), "%" + request.getBrandName() + "%");
            predicates.add(brandPredicate);
        }

        //price between
        if (request.getMinPrice() != null && request.getMaxPrice() != null){
            Integer minPrice = request.getMinPrice();
            Integer maxPrice = request.getMaxPrice();

            Predicate pricePredicate = criteriaBuilder
                    .between(root.get("price"),minPrice,maxPrice);
            predicates.add(pricePredicate);
        }

        //enable off percent
        if (request.isEnableOff()){

            Predicate offPercentPredicate = criteriaBuilder
                    .greaterThan(root.get("offPercent"),0);
            predicates.add(offPercentPredicate);
        }

        //deleted false
        Predicate deletedFalse = criteriaBuilder
                .equal(root.get("deleted"),false);
        predicates.add(deletedFalse);


        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);

        //for paging
        int firstResult = (pageNumber - 1) * pageSize;
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(pageSize);


        return typedQuery.getResultList();
    }
}
