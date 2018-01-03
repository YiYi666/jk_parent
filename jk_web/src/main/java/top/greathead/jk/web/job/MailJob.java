package top.greathead.jk.web.job;

import org.springframework.beans.factory.annotation.Autowired;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.ContractService;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.DateFormatUtils;
import top.greathead.jk.utils.MailUtils;

import java.util.Date;
import java.util.List;

public class MailJob {

    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailUtils mailUtils;

    public void sendMail(){
        String now = DateFormatUtils.simpleDateFormat.format(new Date());
        List<Contract> contractList = contractService.findListbyDeliveryPeriod(now);
        for(Contract contract: contractList){
            String createBy = contract.getCreateBy();
            String email = "1092855724@qq.com";
            if(createBy!=null){
                User user = userService.findById(createBy);
                 //email = user.getUserInfo().getEmail();
                System.out.println("邮箱为："+user.getUserInfo().getEmail());
            }
            mailUtils.send("交货日期已到","订单号为"+contract.getContractNo()+"的订单交货日期已到",email);
        }
    }
}
