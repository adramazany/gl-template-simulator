package dp.gl.gltemplatesimulator.service;

import dp.gl.gltemplatesimulator.dto.ArticleDTO;
import dp.gl.gltemplatesimulator.model.Account;
import dp.gl.gltemplatesimulator.model.Article;
import dp.gl.gltemplatesimulator.model.Costcenter;
import dp.gl.gltemplatesimulator.repository.AccountRepository;
import dp.gl.gltemplatesimulator.repository.CostcenterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CostcenterRepository costcenterRepository;

    public ArticleDTO convertArticle2DTO(Article article){
        ArticleDTO result = new ArticleDTO();
        BeanUtils.copyProperties(article,result);

        Account account =  accountRepository.findById(article.getF_account_leaf()).get();
        if(account!=null){
            result.setAccount_name(account.getAccount_name());
            result.setAccount_no(account.getAccount_no());
//            result.setFull_account_no(account.getFull_account_no());
            result.setFull_account_no(account.getFullAccountNo());
        }

        result.setCostcenter1(getCostcenterName(article.getF_costcenter1()));
        result.setCostcenter2(getCostcenterName(article.getF_costcenter2()));
        result.setCostcenter3(getCostcenterName(article.getF_costcenter3()));

        return result;
    }

    private String getCostcenterName(Integer costcenterId){
        if(costcenterId!=null){
            Costcenter costcenter = costcenterRepository.findById(costcenterId).get();
            if(costcenter!=null){
                return costcenter.getName();
            }
        }
        return null;
    }

}
