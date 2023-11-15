package taxproject.taxpayingservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayingservice.entity.TaxCreditInvoicePerson;
@Repository
public interface TaxCreditInvoicePersonRepo extends JpaRepository<TaxCreditInvoicePerson,Integer> {
}
