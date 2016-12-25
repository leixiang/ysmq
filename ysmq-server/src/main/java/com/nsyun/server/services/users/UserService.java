package com.nsyun.server.services.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.nsyun.server.domain.users.User;
import com.nsyun.server.repository.users.UserRepository;

@Service
public class UserService
{
  private final Logger log;

  @Autowired
  private UserRepository userRepository;

  public UserService()
  {
    this.log = LoggerFactory.getLogger(UserService.class);
  }
   
  

  /**
   * 根据会员ID查询会员信息
   * @param mobile
   * @return
   */
  public User findUserByMobile(String mobile)
  {
    return userRepository.findUserByMobile(mobile);
  }

    /**
     * 根据会员ID查询会员信息
     * @param id
     * @return
     */
    public User findUserById(String id)
    {
        return userRepository.findUserById(id);
    }
  /**
   * 根据会员名查询会员信息
   * @param username
   * @return
   */
  public Integer findByUsername(String username)
  {
    return  userRepository.findByUsername(username);
  }

    /**
     * 获取最新一条会员ID
     * @return
     */
    public Integer findTopById()
    {
        return  userRepository.findTopById();
    }

    /**
     * 获取最新一条会员账户ID
     * @return
     */
    public Integer findTopAccountById()
    {
        return  userRepository.findTopAccountById();
    }

  /**
   * 根据会员名和会员ID查询会员信息
   * @param username
   * @return
   */
  public Integer findByUsername(String username,String id)
  {
    return  userRepository.findByUsername(username,id);
  }

  /**
   * 根据手机号查询会员信息
   * @param mobile
   * @return
   */
  public Integer findByMobile(String mobile)
  {
    return userRepository.findByMobile(mobile);
  }

  /**
   * 根据手机号查询会员信息
   * @param mobile
   * @return
   */
  public Integer findByMobile(String mobile,String id)
  {
    return userRepository.findByMobile(mobile,id);
  }

  /**
   *
   * @Title: UserInfoService
   * @Description: 新增会员信息
   * @param: @param user
   * @param: @return
   * @param: @throws Exception
   * @return: boolean
   * @throws
   */
  @Transactional
  public void saveUser(User user) throws Exception {

      try {


          userRepository.save(user);

      } catch (Exception e) {

          throw new RuntimeException(e.getMessage());
      }
  }

  /**
   *
   * @Title: UserInfoService
   * @Description: 更新会员信息
   * @param: @param user
   * @param: @return
   * @param: @throws Exception
   * @return: boolean
   * @throws
   */
  @Transactional
  public void updateUser(User user)  throws Exception {
      try {

        User userModify = userRepository.findOne(user.getId());

        if(userModify != null) {

          userRepository.save(user);

        }

      } catch (Exception e) {
          throw new RuntimeException(e.getMessage());
      }
  }

  /**
   * 创建会员分页
   * @param user
   * @param pageable
   * @return
   * @throws Exception
   */
  public Page<User> queryUserByPage(User user, Pageable pageable) throws Exception {
      try {
          return userRepository.findAll(new Specification<User>() {
              @Override
              public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                  List<Predicate> preList = new ArrayList<Predicate>();
                  if (!StringUtils.isEmpty(user.getUsername())) {
                      preList.add(cb.like(root.get("username").as(String.class),"%" +  user.getUsername()+ "%"));
                  }

                  if (!StringUtils.isEmpty(user.getMobile())) {
                      preList.add(cb.like(root.get("mobile").as(String.class), "%" + user.getMobile() + "%"));
                  }

                  return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
              }
          },  pageable);
      } catch (Exception e) {

        throw new RuntimeException(e.getMessage());

      }
  }

  /**
   * 根据会员ID删除会员信息
   * @param id
   * @return
   * @throws Exception
   */
  public boolean delete(Long id) throws Exception {
      User user;
        try
        {
          user = this.userRepository.findUserById(id + "");

          if (user != null)
            this.userRepository.delete(id);
          }
        catch (Exception e)
        {
          throw new Exception(e.getMessage());
        }

      return true;
  }

    /**
     *
     * @Title: UserService
     * @Description: 查询店铺(不分页)
     * @param: @return
     * @param: @throws Exception
     * @return: List<User>
     * @throws
     */
    public List<User> findUser() throws Exception {
        try {
            return userRepository.findUser();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public User findUserByUserName(String username) throws Exception {
        return userRepository.findByUsernameCaseInsensitive(username);
    }
    
    
}