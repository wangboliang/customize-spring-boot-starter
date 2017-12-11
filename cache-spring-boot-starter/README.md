# spring cache
### 注解说明<br>
``@CachePut`` 这个注解可以确保方法被执行,同时方法的返回值也被记录到缓存中<br>
``@Cacheable`` 当重复使用相同参数调用方法的时候,方法本身不会被调用执行,即方法本身被略过了,取而代之的是方法的结果直接从缓存中找到并返回了.<br>
``@CacheEvict`` 标记要清空缓存的方法,当这个方法被调用后,即会清空缓存.<br>
``@Caching`` 组合以上3种(@CachePut,@Cacheable,@CacheEvict)注解同时使用.<br>
### 注解区别
那么``@CachePut``和``@Cacheable``区别是什么?<br>
- 被``@CachePut``注解的方法无论缓存中有没有,一定会被执行,就是有也会执行,没有也会执行;然后执行完后的返回值会按照规则存储到缓存中 (关键字:一定会执行)
- 被``@Cacheable``注解的方法,如果缓存中没有,那么会被执行,然后返回的值会被存储到缓存中,如果缓存中有了,那么这个方法就不会被执行,直接从缓存中获取然后返回.(关键字:可能会执行)
### 使用场景
- ``@Cacheable`` QUERY使用，比如：
```java
@Autowired
private UserDao userDao;
@Cacheable( key = "'list'")
public List<User> list() {
    return userDao.list();
}
@Cacheable( key = "#id")
public User get(Long id) {
    return userDao.get();
}
```
- ``@CachePut`` INSERT UPDATE时使用，比如：
```java
@Autowired
private UserDao userDao;
/**
 * 这个方法一定会被执行,如果返回值!=null,那么会以user.id所对应的值为key,user为value,存放到缓存中
 * 意义 : 既然这个实体更新了,不管有没有缓存存在,也应该更新这个缓存,如果有那么更新后同步了数据,避免脏数据,如果没有那就是提前存放到缓存中,那么下次被@Cacheable注解的方法,就直接从缓存中获取了
 */
@CachePut( key = "#user.id",condition = "#result != null")
public User update ( User user ) {
    if ( ! userDao.updateById( user ) ) {
        return null;
    }
    return user;
}
/**
 * 这个方法一定会被执行,如果返回值!=null,那么会以user.id所对应的值为key,user为value,存放到缓存中
 * 意义 : 新增了实体,那么这个新增的实体就提前存放到缓存中,那么下次被@Cacheable注解的方法,就直接从缓存中获取了
 */
@CachePut( key = "#user.id",condition = "#result != null")
public User save ( User user ) {
    if ( ! userDao.insert( user ) ) {
        return null;
    }
    return user;
}
```
- ``CacheEvict`` DELETE 时使用，比如：
```java
@Autowired
private UserDao userDao;
/**
 * 这个方法执行后,会对缓存中key为listPage和参数id的缓存进删除
 */
@Caching(evict = {
    @CacheEvict(key = "'listPage'"), @CacheEvict(key = "#id")
})
public boolean delete ( Long id ) {
    return userDao.delete( user );
}
```