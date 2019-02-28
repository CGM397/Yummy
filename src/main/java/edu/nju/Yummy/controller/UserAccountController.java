package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.Account;
import edu.nju.Yummy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userAccount")
public class UserAccountController {


    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/showUserAccount", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Account> showUserAccount(@RequestParam String userId){
        return userAccountService.showUserAccount(userId);
    }

    @RequestMapping(value = "/saveUserAccount", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveUserAccount(@RequestBody Account account) {
        return userAccountService.saveUserAccount(account);
    }

    @RequestMapping(value = "/updateUserAccount", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateUserAccount(@RequestBody Account account) {
        return userAccountService.updateUserAccount(account);
    }
}
