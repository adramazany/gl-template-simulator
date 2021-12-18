package dp.gl.gltemplatesimulator.repository;

import dp.gl.gltemplatesimulator.model.Account;
import dp.gl.gltemplatesimulator.model.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {

//    Account findByFull__account__no(String full_account_no);
    Account findByFullAccountNo(String full_account_no);

}
