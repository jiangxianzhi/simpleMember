package com.lunchr.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/Member")
public class MemberResfulApi {
    //    url区分大小写，域名不区分
    @Autowired
    private MemberServiceImplement memberServiceImplement;

    @GetMapping("/getAllMember")
    public List<Member> getALlMember() {
        return memberServiceImplement.getAllMember();
    }



    //    url:http://localhost:8080/createMember?name=abc
    @GetMapping("/createMember")
    public int createMember(String name) {
        if (name != null) {
//输入不能为空
            return memberServiceImplement.create(name);

        } else {
            return 2;
        }

    }

    @GetMapping("/deleteMember")
    public  int deleteMember(String name){
        if(name!=null){
            return memberServiceImplement.deleteByName(name);
        }else {
            return 0;
        }
    }
    @GetMapping("/chooseMember")
    public int chooseMember(String name){
        if(name!=null){
            return memberServiceImplement.chooseMember(name);
        }else {
            return 0;
        }
    }

    @GetMapping("/cancelMember")
    public int cancelMember(String name){
        if(name!=null){
            return memberServiceImplement.cancelMember(name);
        }else {
            return 0;
        }
    }

}
