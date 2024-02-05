package taxproject.taxpayingservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayingservice.entity.TaxCreditInvoiceCompany;

@Repository
public interface TaxCreditInvoiceCompanyRepo extends JpaRepository<TaxCreditInvoiceCompany,Integer> {
}
