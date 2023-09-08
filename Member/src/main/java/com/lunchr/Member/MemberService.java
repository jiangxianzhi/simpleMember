package com.lunchr.Member;

import java.util.List;

public interface MemberService {

    /**
     * 新增一个用户
     *
     * @param name
     */
    int create(String name);

    /**
     * 查询所有用户
     *
     */
    List<Member> getAllMember();
//    这里返回类型为什么会影响，后续呢？

    /**
     * 根据name删除用户
     *
     * @param name
     */
    int deleteByName(String name);

    /**
     * 根据name改变 state
     *
     * @param name
     */

    int chooseMember(String name);
    /**
     * 根据name改变 state
     *
     * @param name
     */

    int cancelMember(String name);

}
