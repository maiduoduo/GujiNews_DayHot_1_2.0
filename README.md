# GujiSmartNews

### 咕唧新闻平台V1.8.2

---

- **功能大纲**
> 旨在做一款多类型阅读体验优美的新闻资讯的平台
> 首页精选分类性的新闻专区，包含图文、音视频、图文、导航入口、新闻直击阅读
> 可以随时随地加入新闻猜猜猜，专区内置咕唧币，更多权限，更多玩法
> 专门视频专区，一次看个够
> 个人中心，美观的界面，多类型，丰富的功能设计。设置分享，想看就看。

- **2.0新增的分区功能细节**
> **闪屏页**：加入时间倒计时
> **引导页**：视频引导
> **首页**：新闻分类，进入详情，快速回到顶部，沉浸式状态栏，加入多类型新闻类型
> **广场**：菜单引导，热议卡片列表，新闻热点直击，更多热文详情，横向滚动式热点，堆叠横向滚动式原创精品，精选视频列表
> **视频**：推荐，小视频，生活，电影
> - 推荐
>  - [X] 推荐：引导推荐热文，垂直滚动消息播报
>  - [X] 横向滚动新闻条
>  - [X] 新闻视频列表
>  - [X] 加入点赞、踩、评论、分享菜单栏
>  - [ ] 点赞、踩、评论菜单栏具体功能未实现
>  - [ ] 分享弹出分享弹窗，只展示分享渠道，未具体实现真正外部分享
>  
> - 小视频
>  - [X] 小视频列表预览
>  - [X] 详情视频
>  - [X] 上下滑动切换小视频
>  - [X] 支持暂停
>  - [X] 支持点赞的♥效果
>  - [ ] 未实现评论功能
>  
> - 生活/电影
>  - [X] 电影的分类菜单：年份，类型，国家地区，最新/热门/好评
>  - [X] 电影详情
>  - [ ] 未实现电影预告预览，电影播放功能
>  

>  
> **版本更新**：加入版本校验与下载安装
> **新闻详情评论**

**2.0版本**
#### 版权声明（严肃）

---

<font color=#0099ff size=3 face="黑体">1.开源只为共同学习，请勿商用。问题或者指正请EMAIL:maiduoduo0@163.com</font>
<font color=#ff9900 size=3 face="黑体">2.数据：来源于EPET/凤凰新闻/百思不得姐/等平台</font>
<font color=#0A0f99 size=3 face="黑体">3.框架：部分来自开源的比较火，应用广泛的框架</font>
<font color=#fa000f size=3 face="黑体"> **4.本项目重在学习使用，如您有学习参照的兴趣，请拿到源码后，请勿作任何形式的商用，我们都要有一个侵权意识。尊重开源方及其他平台的商业价值性，产品权力法律保护性。** </font>
<font color=#A0AA0f size=3 face="黑体"> 5.希望能够带给您一些学习上的帮助，有任何问题及想法可以issue </font>



### 注意

> 目前兼容Android5.0+，基於5.0以下可能會出現图片处理或者ANR。

### 下载Demo,安装看看

[下载地址](https://s.beta.myapp.com/myapp/rdmexp/exp/file2/2019/09/19/comcnewsgujismart_1.2.0_403e7e90-4880-5667-af34-aec3014ab4b0.apk)

<br/>

#### 运行截图
###  咕唧新闻平台LOGO 
![image](img/20/logo20.png)
	
 
 <br>
	

![image](img/20/2_a.jpg)
![image](img/20/2_b.jpg)
![image](img/20/2_c.jpg)

![image](img/20/2_d.jpg)
![image](img/20/2_e.jpg)
![image](img/20/2_f.jpg)

![image](img/20/2_g.jpg)
![image](img/20/2_h.jpg)
![image](img/20/2_i.jpg)

![image](img/20/2_j.jpg)
![image](img/20/2_k.jpg)
![image](img/20/2_m.jpg)

![image](img/20/2_m.jpg)
![image](img/20/2_o.jpg)
![image](img/20/2_p.jpg)

![image](img/20/2_q.jpg)
![image](img/20/2_r.jpg)
![image](img/20/2_s.jpg)

![image](img/20/2_t.jpg)
![image](img/20/2_u.jpg)
![image](img/20/2_v.jpg)

![image](img/20/2_w.jpg)
![image](img/20/2_x.jpg)
![image](img/20/2_y.jpg)

![image](img/20/2_z.jpg)
![image](img/20/2_aa.jpg)
![image](img/20/2_bb.jpg)

![image](img/20/2_.ccjpg)
![image](img/20/2_dd.jpg)
![image](img/20/2_ee.jpg)

![image](img/20/2_ff.jpg)
![image](img/20/2_gg.jpg)
![image](img/20/2_hh.jpg)

![image](img/20/2_ii.jpg)
![image](img/20/2_jj.jpg)
![image](img/20/2_kk.jpg)

![image](img/20/2_mm.jpg)
![image](img/20/2_nn.jpg)
![image](img/20/2_pp.jpg)

![image](img/20/2_qq.jpg)
![image](img/20/2_rr.jpg)






<br/>

###  咕唧新闻平台V1.2.2

---

##### 1.0目前已完成的或者正在完成功能模块有：

   ####  启动页
	
		-广告：动态、静态。目前支持GIF形式动态广告和静态图文广告
		-静态图闻
		-应用宣传页
		

   ####  主页
	
		-底部导航搭建  SlidingTabLayout + ViewPager +Fragment
		-推荐、要闻、视频、娱乐、体育等  TabLayout + Fragment + ViewPager
		-多类型流畅列表展示  RecyclerView + BaseQuickAdapter + BaseViewHolder
		-列表数据的响应事件
		-级联界面
		-事件冲突处理
		-新闻直击阅读
		-频道定制
		-等（详见图片）


   ####  要闻
	
		-要闻 新闻预测，猜猜猜，等待揭晓。赢取咕唧币。
		-我的要闻
		-下拉刷新
		-加载更多
		
		
	

   ####  视频
	
		-推荐
		-搞笑
		-萌宠
		-美食
		-军事
		等
		-利用JZVDPlayer进行扩展视频播控
		-界面切换，视频播放停止，播放管理释放。
		-列表滑动距离范围，Item不可见播控停止，释放资源。
		-小窗模式
	
   ####  关注
	
		-头像、基本信息
		-折叠开窗列表联动效果
		-设置
		-好友、影响力、访客
		-个人小窝
		-我的钱包
		-我的订单
		-我的服务
		-等
		
   ####  新闻精选
   ####  新闻详情
   
   
	 
#### 技术要点(感谢项目中用到的开源框架开发者或开源机构)

		-沉浸式状态栏
		-圆角banner
		-多布局列表、头部、尾部
		-自定义下拉刷新布局。SwipeToLayout
		-类某团多页导航菜单，左右滑动，引导圆点
		-上下新闻条目切换条 -ViewFlipper
		-圆角图片
		-新闻详情：TextView加载HTML5内容，HtmlImageLoader+HtmlTagHandler+HtmlText等解析HTML文本和图片进行展示
		-新闻精选：CoordinatorLayout+AppBarLayout+Toolbar+NestedScrollView实现标题栏的伸缩效果
		-JZVDPlayer扩展实现视频界面播控，资源状态释放
		-SwipeToLoadLayout 可定制化刷新布局框架。
		-注入框架ButterKnife(黄油刀节省了人工时间成本)，快捷键快速注入View,事件监听等
		-retrofit2+rxjava+mvp等结合对业务及网络数据交换进行开发
		-FlycoTabLayout_Lib 导航页签快速集成，搭配Viewpager+Fragemnt
		-glide 图片高效配置及展示
		-Fresco FaceBook开源图片框架
		-等
		-其中使用的三方开源框架（后期会细化整理所有项目使用到的开源框架）
				

####  其他板块
		-分享app
		-拉起QQ分享
		-设置
		等

####  后续要完成

		- 新闻不同板块
		- 频道动态定制与首页联动
		- 视频详情
		- 我的要闻
		- 要闻详情预测
		- 视频、新闻类型条目的更多功能
		- 关注详情
		- 用户小窝
		- 消息推送  消息详情
		等，敬请期待

####  其中使用的三方开源框架（这里部分列举，后期会细化整理所有项目使用到的开源框架）
		  -BaseQuickAdapter
		  -Glide
		  -Fresco
		  -butterknife
		  -Retrofit2.0+rxandroid+rxjava+okhttp3
		  -FlycoTabLayout_Lib
		  -glide+fresco
		  -com.youth.banner:banner
		  -SwipeToLoadLayout
		  -jiaozivideoplayer
		  -xrecyclerview		  
		  等
		  -感谢开源，感谢项目中的开源方
		  
		  
		  		 	
#### 环境
		 -AndroidStudio3.2
			-Android Studio 3.2
			Build #AI-181.5540.7.32.5014246, built on September 18, 2018
			JRE: 1.8.0_152-release-1136-b06 amd64
			JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
			Windows 10 10.0
		-SVN
		_gradle构建：gradle-4.6-all.zip
		
  
#### 声明

	# 此项目只作为平时开发练习及构思项目，请勿做其他任何商用，
	# 项目部分数据来源于 **SOHUNEWS Epet**
	# 如果项目中引入框架或者数据上有侵范行为，请联系我删除。Email:maiduoduo0@163.com
	
	# 感谢开源及开发性环境，才能使技术越走越远，内容越来越丰富，越来越规范。
	
	
	

###  咕唧新闻平台LOGO 
![image](img/app_news_logo.png)
	
 #### 个人设计，请勿他用
 
 <br>
	
	
	

### 咕唧新闻平台运行效果展示

![image](img/a.jpg)
![image](img/b.jpg)
![image](img/c.jpg)

![image](img/d.jpg)
![image](img/e.jpg)
![image](img/f.jpg)

![image](img/g.jpg)
![image](img/h.jpg)
![image](img/i.jpg)

![image](img/j.jpg)
![image](img/k.jpg)
![image](img/l.jpg)

![image](img/m.jpg)
![image](img/n.jpg)
![image](img/o.jpg)
![image](img/p.jpg)

![image](img/q.jpg)
![image](img/r.jpg)
![image](img/s.jpg)
![image](img/t.jpg)
![image](img/u.jpg)
![image](img/v.jpg)
![image](img/w.jpg)
![image](img/x.jpg)
![image](img/y.jpg)
![image](img/z.jpg)
![image](img/aa.jpg)
![image](img/bb.jpg)
![image](img/cc.jpg)
![image](img/dd.jpg)
![image](img/ee.jpg)
![image](img/ff.jpg)




## 初衷
          
		  - Android开发平时接触的东西很多，也很杂乱，通过这种方式能够将这些联系起来
		  - 学习之用，也是对这种类型应用的探索与热衷实现途径，学习好的点，用自己及网络世界的帮助，以此为纽带，好记性不如烂笔头。
		  - 项目写的可能不太好。一千个人眼中有一千个哈姆雷特，请老铁们多多指正。
		  - 不想当厨师的码农不是一个合格的老司机。
		  - 希望在日后静好岁月也好，波澜狂暴也好，坚持自我本心，热爱及热衷自己想要做的事情，追逐十七岁你最直击心底的那个信念
		  - 在座的一直发财，一直暴富，成为各个领域的大牛。




#### 关于LICENSE

	- 咕唧新闻项目只作为平时开发练习及构思交流项目，请勿做其他任何商用。
	- 咕唧新闻UI设计在参考主流类型平台结合自我设计+框架搭建+数据格式+网络库模式+MVP+业务+功能实现均为个人业余时间实现。
	- 归属解释权于dingcl,联系邮箱：maiduoduo0@163.com
	- 若想要转载等请联系我授权。转载请注明出处。互相学习与探讨。有任何问题请联系邮箱。


Copyright (C) dingcl, nanjCodeTeam,Inc. Open source codes for study only. Do not use for commercial purpose.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.