# spring cache
### 注解说明<br>
``@CachePut`` 这个注解可以确保方法被执行,同时方法的返回值也被记录到缓存中<br>
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CachePut {

    /**
     * 缓存的名称，必须指定至少一个
     */
    @AliasFor("cacheNames")
    String[] value() default {};

    /**
     * 缓存的名称，数组格式，指定多个
     */
    @AliasFor("value")
    String[] cacheNames() default {};

    /**
     * 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     */
    String key() default "";

    /**
     * 默认的Key生成策略
     */
    String keyGenerator() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.CacheManager} to use to
     * create a default {@link org.springframework.cache.interceptor.CacheResolver} if none
     * is set already.
     * <p>Mutually exclusive with the {@link #cacheResolver}  attribute.
     * @see org.springframework.cache.interceptor.SimpleCacheResolver
     * @see CacheConfig#cacheManager
     */
    String cacheManager() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.interceptor.CacheResolver}
     * to use.
     * @see CacheConfig#cacheResolver
     */
    String cacheResolver() default "";

    /**
     * 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存
     */
    String condition() default "";

    /**
     * 用于否决缓存更新的，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了
     */
    String unless() default "";

}
```
``@Cacheable`` 当重复使用相同参数调用方法的时候,方法本身不会被调用执行,即方法本身被略过了,取而代之的是方法的结果直接从缓存中找到并返回了.<br>
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cacheable {

    /**
     * 缓存的名称，必须指定至少一个
     */
    @AliasFor("cacheNames")
    String[] value() default {};

    /**
     * 缓存的名称，数组格式，指定多个
     */
    @AliasFor("value")
    String[] cacheNames() default {};

    /**
     * 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     */
    String key() default "";

    /**
     * 默认的Key生成策略
     */
    String keyGenerator() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.CacheManager} to use to
     * create a default {@link org.springframework.cache.interceptor.CacheResolver} if none
     * is set already.
     * <p>Mutually exclusive with the {@link #cacheResolver}  attribute.
     * @see org.springframework.cache.interceptor.SimpleCacheResolver
     * @see CacheConfig#cacheManager
     */
    String cacheManager() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.interceptor.CacheResolver}
     * to use.
     * @see CacheConfig#cacheResolver
     */
    String cacheResolver() default "";

    /**
     * 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存
     */
    String condition() default "";

    /**
     * 用于否决缓存更新的，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了
     */
    String unless() default "";

    /**
     * Synchronize the invocation of the underlying method if several threads are
     * attempting to load a value for the same key. The synchronization leads to
     * a couple of limitations:
     * <ol>
     * <li>{@link #unless()} is not supported</li>
     * <li>Only one cache may be specified</li>
     * <li>No other cache-related operation can be combined</li>
     * </ol>
     * This is effectively a hint and the actual cache provider that you are
     * using may not support it in a synchronized fashion. Check your provider
     * documentation for more details on the actual semantics.
     * @since 4.3
     * @see org.springframework.cache.Cache#get(Object, Callable)
     */
    boolean sync() default false;

}
```
``@CacheEvict`` 标记要清空缓存的方法,当这个方法被调用后,即会清空缓存.<br>
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheEvict {

    /**
     * 缓存的名称，必须指定至少一个
     */
    @AliasFor("cacheNames")
    String[] value() default {};

    /**
     * 缓存的名称，数组格式，指定多个
     */
    @AliasFor("value")
    String[] cacheNames() default {};

    /**
     * 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     */
    String key() default "";

    /**
     * 默认的Key生成策略
     */
    String keyGenerator() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.CacheManager} to use to
     * create a default {@link org.springframework.cache.interceptor.CacheResolver} if none
     * is set already.
     * <p>Mutually exclusive with the {@link #cacheResolver}  attribute.
     * @see org.springframework.cache.interceptor.SimpleCacheResolver
     * @see CacheConfig#cacheManager
     */
    String cacheManager() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.interceptor.CacheResolver}
     * to use.
     * @see CacheConfig#cacheResolver
     */
    String cacheResolver() default "";

    /**
     * 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才清空缓存
     */
    String condition() default "";

    /**
     * 是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存
     */
    boolean allEntries() default false;

    /**
     * 是否在方法执行前就清空，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，
     * 缺省情况下，如果方法执行抛出异常，则不会清空缓存
     */
    boolean beforeInvocation() default false;

}
```
``@Caching`` 组合以上3种(@CachePut,@Cacheable,@CacheEvict)注解同时使用.<br>
### 注解区别
那么``@CachePut``和``@Cacheable``区别是什么?<br>
- 被``@CachePut``注解的方法无论缓存中有没有,一定会被执行,就是有也会执行,没有也会执行;然后执行完后的返回值会按照规则存储到缓存中 (关键字:一定会执行)
- 被``@Cacheable``注解的方法,如果缓存中没有,那么会被执行,然后返回的值会被存储到缓存中,如果缓存中有了,那么这个方法就不会被执行,直接从缓存中获取然后返回.(关键字:可能会执行)
### 使用场景
- ``@Cacheable`` QUERY使用，比如：
```java
@Cacheable(value = "list",key = "#param.toString()")
public List<User> list(Param param) {
    return mapper.list();
}

@Cacheable(value = "user",key = "#id")
public User query(Long id) {
    return mapper.query();
}
```
- ``@CachePut`` INSERT UPDATE时使用，比如：
```java
/**
 * 这个方法一定会被执行,如果返回值!=null,那么会以user.id所对应的值为key,user为value,存放到缓存中,并且清除list方法的缓存
 * 意义 : 既然这个实体更新了,不管有没有缓存存在,也应该更新这个缓存,如果有那么更新后同步了数据,避免脏数据,如果没有那就是提前存放到缓存中,那么下次被@Cacheable注解的方法,就直接从缓存中获取了
 */
@Caching(evict = {@CacheEvict(value = "list",allEntries = true, condition = "#result != null")},
            put = @CachePut(value = "user",key = "#user.id", condition = "#result != null"))
public User update(User user) {
    if (!mapper.updateById(user)) {
        return null;
    }
    return user;
}
/**
 * 这个方法一定会被执行,如果返回值!=null,那么会以user.id所对应的值为key,user为value,存放到缓存中,并且清除list方法的缓存
 * 意义 : 新增了实体,那么这个新增的实体就提前存放到缓存中,那么下次被@Cacheable注解的方法,就直接从缓存中获取了
 */
@Caching(evict = {@CacheEvict(value = "list",allEntries = true, condition = "#result != null")},
            put = @CachePut(value = "user",key = "#user.id", condition = "#result != null"))
public User save(User user) {
    if (!mapper.insert(user)) {
        return null;
    }
    return user;
}
```
- ``@CacheEvict`` DELETE 时使用，比如：
```java
/**
 * 这个方法执行后,会对参数id的缓存进行删除,并且清除list方法的缓存
 */
@Caching(evict = {@CacheEvict(value = "list",allEntries = true), @CacheEvict(value = "user",key = "#id")})
public boolean delete(Long id) {
    return mapper.delete(user);
}
```