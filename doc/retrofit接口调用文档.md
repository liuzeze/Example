#介绍

retrofit 官方网址 :[http://square.github.io/retrofit/](http://square.github.io/retrofit/ "ETWET")


**1.get请求**
 
   参数直接拼接再url上的方式,需要再地址上用{id}拼接,再参数上用@path注解传入

    @GET("group/{id}/users")
	Call<List<User>> groupList(@Path("id") int groupId);

   如果get请求需要参数传递,一般会将参数拼接到url上在?之后拼接,例如:www.baidu.com/group/{id}/users?sort=zhangshan 

    @GET("group/{id}/users")
	Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

   上面如果请求参数较多可以使用@queryMap注解传入map集合

    @GET("group/{id}/users")
	Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options)`

**2.post请求**

  单字段上传方式:需要@Field("字段名) 字段值

	@FormUrlEncoded
    @POST(LPUrl.CHANGE_WORK_DETAIL_URl)
    Flowable<T> getChangeWorkData(@Field("sessUserId") String sessUserId,@Field("username") String username);

   多字段map方式上传 用到了@FieldMap

	 @FormUrlEncoded
    @POST(LPUrl.LOGIN_URl)
    Flowable<LoginBean> login(@FieldMap Map<String, String> pramsMap);

以实体的方式去请求 用到了@body 后面的参数可以用okhttp的FormBody  也可以新建自己的实体对象,对象里的字段要跟接口需要的字段一样

	// 实体的方式提交
    @POST(LPUrl.CHANGE_WORK_URl)
    Flowable<MemberInfor> getMemInfor(@Body FormBody body);

    //自定义实体的方式提交
    @POST(LPUrl.CHANGE_WORK_URl)
    Flowable<MemberInfor> getMemInfor(@Body UserBean bean);

多表单多文件上传 用到@Multipart  @Part 多用于文件上传  RequestBody   MultipartBody 
	
	@Multipart
	@POST("user/photo")
	Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);


**3.其他常用注解** 

 @Heads  @head   添加单个请求头 或者多个请求头


	@Headers("Cache-Control: max-age=640000")
	@GET("widget/list")
	Call<List<Widget>> widgetList();
	@Headers({
	    "Accept: application/vnd.github.v3.full+json",
	    "User-Agent: Retrofit-Sample-App"
	})

	@GET("users/{username}")
	Call<User> getUser(@Path("username") String username);
 	
@Streaming   大文件下载

@URL 重新设置的本次请求接口地址不依赖于baseurl


    Tips1
    使用@Field时记得添加@FormUrlEncoded
    Tips2
    若需要重新定义接口地址，可以使用@Url，将地址以参数的形式传入即可。如