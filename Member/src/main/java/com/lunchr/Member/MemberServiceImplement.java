package com.lunchr.Member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Component
public class MemberServiceImplement implements MemberService {

    private final JdbcTemplate jdbcTemplate;

    MemberServiceImplement(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //    这里要返回一个Member数组， 怎么会报错呢
    public List<Member> getAllMember() {
        String sql = "SELECT * FROM Member";
        List<Member> a = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Member>(Member.class));
        return a;


    }

    @Override
    public int create(String name) {
        return jdbcTemplate.update("insert into MEMBER(NAME) values(?)", name);

    }

    @Override
    public int deleteByName(String name) {
        return jdbcTemplate.update("delete from MEMBER where NAME = ?", name);
    }

    @Override
    public int chooseMember(String name) {
        return jdbcTemplate.update("update MEMBER set state = 1 where name = ?", name);

    }
    @Override
    public int cancelMember(String name) {
        return jdbcTemplate.update("update MEMBER set state = 0 where name = ?", name);

    }

}
