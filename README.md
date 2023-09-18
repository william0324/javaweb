使用servlet mybatis mysql实现用户登录注册

登录

流程说明：
1.用户填写用户名密码，提交到LoginServlet
2.在LoginServlet中使用MyBatisi查询数据库，验证用户名密码是否正确
3.如果正确，响应“登录成功”，如果错误，响应“登录失败”

注册

流程说明：
1.用户填写用户名、密码等信息，点击注册按钮，
提交到RegisterServlet
2.在RegisterServlet中使用MyBatis保存数据
3.保存前，需要判断用户名是否已经存在：根据用户名查询数据库