package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.api.dto.OrderSearchRequest;
import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderSearchRepository {

    private final EntityManager entityManager;

    public OrderSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Order> findAllByCriteria(OrderSearchRequest request) {

        int pageNumber = request.getPageNumber();
        int pageSize = 10;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();


        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);

        List<Predicate> predicates = new ArrayList<>();

        //select from order
        Root<Order> root = criteriaQuery.from(Order.class);

        // Join with User and Person
        Join<Order, User> userJoin = root.join("user");
        Join<User, Person> personJoin = userJoin.join("person");


        // Add predicate for orderStatus
        if (request.getOrderStatus() != null) {
            Predicate orderStatusPredicate =
                    criteriaBuilder.equal(root.get("orderStatus"), OrderStatus.valueOf(request.getOrderStatus()));
            predicates.add(orderStatusPredicate);
        }


        // Add predicate for trackingCode
        if (request.getTrackingCode() != null) {
            Predicate trackingCodePredicate =
                    criteriaBuilder.equal(root.get("trackingCode"), request.getTrackingCode());
            predicates.add(trackingCodePredicate);
        }


        // Add predicate for firstName
        if (request.getFirstName() != null) {
            Predicate firstNamePredicate =
                    criteriaBuilder.like(personJoin.get("firstName"), "%" + request.getFirstName() + "%");
            predicates.add(firstNamePredicate);
        }


        // Add predicate for lastName
        if (request.getLastName() != null) {
            Predicate lastNamePredicate =
                    criteriaBuilder.like(personJoin.get("lastName"), "%" + request.getLastName() + "%");
            predicates.add(lastNamePredicate);
        }


        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);

        //for paging
        int firstResult = (pageNumber - 1) * pageSize;
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(pageSize);


        return typedQuery.getResultList();

    }
}
