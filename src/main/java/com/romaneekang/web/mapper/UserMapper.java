package com.romaneekang.web.mapper;

import com.romaneekang.web.pojo.User;

/**
* @author William
* @description 针对表【tb_user】的数据库操作Mapper
* @createDate 2023-09-19 02:08:49
* @Entity com.romaneekang.web.pojo.User
*/
public interface UserMapper {
    /**
     * 根据用户名查询用户对象
     * @param username 用户名
     * @return  用户对象 user
     */
    public User selectAllByUsernameUser(String username);

    /**
     * 向用户表中添加数据
     * @param user 用户对象
     * @return  0-失败 >0成功
     */
    public int insertToUserTable(User user);
}




