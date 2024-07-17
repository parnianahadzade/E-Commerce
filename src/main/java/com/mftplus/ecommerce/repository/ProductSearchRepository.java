package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.api.dto.SearchRequest;
import com.mftplus.ecommerce.model.entity.Brand;
import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.model.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductSearchRepository {

    // TODO: 7/17/2024 deleted false

    // TODO: 7/18/2024 paging added but needs recheck 
    private final EntityManager entityManager;

    public ProductSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> findAllByCriteria(SearchRequest request){
        int pageNumber = 1;
        int pageSize = 10;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = criteriaBuilder
                .createQuery(Long.class);
        countQuery.select(criteriaBuilder
                .count(countQuery.from(Product.class)));
        Long count = entityManager.createQuery(countQuery)
                .getSingleResult();

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
            String brandName = request.getBrandName();

            Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
            Root<Product> subQueryProduct = subquery.from(Product.class);
            Join<Brand, Product> subQueryBrand = subQueryProduct.join("brand");

            subquery.select(subQueryProduct.get("id")).where(
                    criteriaBuilder.equal(subQueryBrand.get("name"), brandName));

            predicates.add(criteriaBuilder.in(root.get("id")).value(subquery));
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


        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);

        while (pageNumber < count.intValue()) {
            typedQuery.setFirstResult(pageNumber - 1);
            typedQuery.setMaxResults(pageSize);
            System.out.println("Current page: " + typedQuery.getResultList());
            pageNumber += pageSize;
        }

        return typedQuery.getResultList();
    }
}
