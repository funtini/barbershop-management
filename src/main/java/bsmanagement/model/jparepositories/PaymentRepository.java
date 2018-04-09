package bsmanagement.model.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.PaymentMethod;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentMethod,String> {

}
