package com.cash.app.loan.repository;

import com.cash.app.loan.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LoanRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    public Page<Loan> getLoansByPage(Integer page, Integer size, Long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> cq = cb.createQuery(Loan.class);
        //
        Root<Loan> root = cq.from(Loan.class);
        //
        List<Predicate> predicates = new ArrayList<>();
        if (userId != null) {
            predicates.add(cb.equal(root.get("userId"), String.valueOf(userId)));
        }
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        //
        TypedQuery<Loan> query = entityManager.createQuery(cq);
        if (page != null && size != null) {
            int total = query.getResultList().size();
            query.setMaxResults(size);
            query.setFirstResult((page - 1) * size);
            List<Loan> loans = query.getResultList();
            return new PageImpl<>(loans, PageRequest.of((page - 1), size), total);
        } else {
            List<Loan> loans = query.getResultList();
            return new PageImpl<>(loans);
        }
    }
}
