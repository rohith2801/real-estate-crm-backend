package org.tihor.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.tihor.entity.CustomerEntity;
import org.tihor.enums.SearchOperationType;
import org.tihor.model.request.FilterRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer specification.
 */
public class CustomerSpecification implements Specification<CustomerEntity> {
    /**
     * The List.
     */
    private final List<FilterRequest> list;

    /**
     * Instantiates a new Customer specification.
     *
     * @param list the list
     */
    public CustomerSpecification(final List<FilterRequest> list) {
        this.list = list;
    }

    /**
     * To predicate predicate.
     *
     * @param root    the root
     * @param query   the query
     * @param builder the builder
     * @return the predicate
     */
    @Override
    public Predicate toPredicate(
            final Root<CustomerEntity> root,
            final CriteriaQuery<?> query,
            final CriteriaBuilder builder
    ) {
        List<Predicate> predicates = toPredicateList(root, builder);

        assert query != null;
        query.groupBy(root.get("id"));

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    /**
     * To predicate list list.
     *
     * @param root    the root
     * @param builder the builder
     * @return the list
     */
    private List<Predicate> toPredicateList(final Root<CustomerEntity> root, final CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (FilterRequest criteria : list) {
            Expression<String> exp = prepareExpression(root,  criteria.getKey());

            if (criteria.getOperationType().equals(SearchOperationType.GREATER_THAN)) {
                predicates.add(builder.greaterThan(exp, criteria.getValue().toString()));
            } else if (criteria.getOperationType().equals(SearchOperationType.LESS_THAN)) {
                predicates.add(builder.lessThan(exp, criteria.getValue().toString()));
            } else if (criteria.getOperationType().equals(SearchOperationType.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(exp, criteria.getValue().toString()));
            } else if (criteria.getOperationType().equals(SearchOperationType.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(exp, criteria.getValue().toString()));
            } else if (criteria.getOperationType().equals(SearchOperationType.NOT_EQUAL)) {
                predicates.add(builder.notEqual(exp, criteria.getValue()));
            } else if (criteria.getOperationType().equals(SearchOperationType.EQUAL)) {
                predicates.add(builder.equal(exp, criteria.getValue()));
            } else if (criteria.getOperationType().equals(SearchOperationType.MATCH)) {
                predicates.add(builder.like(builder.lower(exp),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperationType().equals(SearchOperationType.MATCH_START)) {
                predicates.add(builder.like(builder.lower(exp),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperationType().equals(SearchOperationType.MATCH_END)) {
                predicates.add(builder.like(builder.lower(exp),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } /* else if (criteria.getOperationType().equals(SearchOperationType.IN)) {
                predicates.add(builder.in((Expression<Object>) exp).value(criteria.getValue()));
            } else if (criteria.getOperationType().equals(SearchOperationType.NOT_IN)) {
                predicates.add(builder.not((Expression<Boolean>) exp).in(criteria.getValue()));
            }*/
        }

        return predicates;
    }

    /**
     * Prepare expression expression.
     *
     * @param root the root
     * @param key  the key
     * @return the expression
     */
    private Expression<String> prepareExpression(final Root<?> root, final String key) {
        return root.get(key);
    }
}
