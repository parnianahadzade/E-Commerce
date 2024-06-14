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

    private final EntityManager entityManager;

    public ProductSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> findAllByCriteria(SearchRequest request){

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
        if (request.getCategoryNames() != null){
            List<String> categoryNames = request.getCategoryNames();

            for(String categoryName : categoryNames) {

                Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
                Root<Product> subQueryProduct = subquery.from(Product.class);
                Join<Category, Product> subQueryCategory = subQueryProduct.join("categories");

                subquery.select(subQueryProduct.get("id")).where(
                        criteriaBuilder.equal(subQueryCategory.get("name"), categoryName));

                predicates.add(criteriaBuilder.in(root.get("id")).value(subquery));
            }
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




//        if (request.getCategoryId() != null) {
//            Predicate categoryPredicate = criteriaBuilder
//                    .lt(root.get("categories"), request.getCategoryId());
//            predicates.add(categoryPredicate);
//        }

//        if (request.getColorId() != null) {
//            Predicate colorPredicate = criteriaBuilder
//                    .like(root.get("color"), "%" + request.getColorId() + "%");
//            predicates.add(colorPredicate);
//        }

//        if (request.getBrandId() != null) {
//            Predicate brandPredicate = criteriaBuilder
//                    .like(root.get("brand"), "%" + request.getBrandId() + "%");
//            predicates.add(brandPredicate);
//        }

//        if (request.getPrice() != null) {
//            Predicate pricePredicate = criteriaBuilder
//                    .like(root.get("price"), "%" + request.getPrice() + "%");
//            predicates.add(pricePredicate);
//        }

//        if (request.getOffPercent() != null) {
//            Predicate offPercentPredicate = criteriaBuilder
//                    .like(root.get("offPercent"), "%" + request.getOffPercent() + "%");
//            predicates.add(offPercentPredicate);
//        }

        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }
}
