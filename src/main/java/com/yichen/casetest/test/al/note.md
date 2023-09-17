# 不足点
> > 1、浮点数比较需要通过BigDecimal，不能直接进行比较<br/>
> 2、集合初始化不要通过双括号。<br/>
> 双括号初始化（Double Brace Initialization）通过创建一个内部匿名类并生成对应的集合的方式来执行初始化，这可能导致严重的内存泄漏，也会极大地增加对其进行序列化和垃圾收集的成本<br/>
> 3、hashTable 或者 ConcurrentHashMap contains()方法比较的是 value set <br/>
> 4、无参数的random.nextInt()的数值范围为 【-n+1, n-1], 其中0的概率为 1/n 其他的概率为 1/2n <br/>
> 5、@Transactional只在public方法生生效，有点疑惑，印象中protected的好像也行来着 <br/>
> 6、类型转换运算符的优先级要高于二元运算符 <br/>
> 7、访问权限：包、private、protected、public区别呢？？ <br/>
> 8、
> 
> 
> 

# 待看文档：
1、https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html  Formatter

2、https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.25 条件表达式

3、