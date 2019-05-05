package com.osce.orm.system.authority;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: AuthorityDao
 * @Description: 用户权限相关的持久化层
 * @Author yangtongbin
 * @Date 2018/3/12 16:59
 */
public interface AuthorityDao {

    /**
     * 根据用户ID查找用户拥有的功能菜单及资源的权限编码集合
     *
     * @param userId 用户id
     * @return
     */
    List<String> findFunctionCodesByUserId(@Param("userId") Long userId);

    /**
     * 超级管理员权限
     *
     * @return
     */
    List<String> findFunctionCodesByRoot();
}
