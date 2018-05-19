# TaxiHepler
打车比价项目（第六届重庆市雏鹰计划项目 ），Android客户端。

Table of Contents
=================

* [TaxiHepler](#taxihepler)
  * [1\.概要](#1%E6%A6%82%E8%A6%81)
    * [1\.1 项目介绍](#11-%E9%A1%B9%E7%9B%AE%E4%BB%8B%E7%BB%8D)
    * [1\.2 客户端功能介绍](#12-%E5%AE%A2%E6%88%B7%E7%AB%AF%E5%8A%9F%E8%83%BD%E4%BB%8B%E7%BB%8D)
  * [2\. 功能展示](#2-%E5%8A%9F%E8%83%BD%E5%B1%95%E7%A4%BA)
    * [登陆](#%E7%99%BB%E9%99%86)
    * [定位&amp;上下车选择&amp;价格比较](#%E5%AE%9A%E4%BD%8D%E4%B8%8A%E4%B8%8B%E8%BD%A6%E9%80%89%E6%8B%A9%E4%BB%B7%E6%A0%BC%E6%AF%94%E8%BE%83)
    * [侧边栏设计&amp;模拟钱包充值](#%E4%BE%A7%E8%BE%B9%E6%A0%8F%E8%AE%BE%E8%AE%A1%E6%A8%A1%E6%8B%9F%E9%92%B1%E5%8C%85%E5%85%85%E5%80%BC)
    * [等待接单&amp;司机接单](#%E7%AD%89%E5%BE%85%E6%8E%A5%E5%8D%95%E5%8F%B8%E6%9C%BA%E6%8E%A5%E5%8D%95)
  * [3 开发环境及技术支持](#3-%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%E5%8F%8A%E6%8A%80%E6%9C%AF%E6%94%AF%E6%8C%81)
    * [3\.1  开发环境及运行平台](#31--%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%E5%8F%8A%E8%BF%90%E8%A1%8C%E5%B9%B3%E5%8F%B0)
      * [3\.1\.1 开发环境](#311-%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83)
      * [3\.1\.2 运行环境](#312-%E8%BF%90%E8%A1%8C%E7%8E%AF%E5%A2%83)
    * [3\.2 技术支持](#32-%E6%8A%80%E6%9C%AF%E6%94%AF%E6%8C%81)
  * [4 最后](#4-%E6%9C%80%E5%90%8E)

Created by [gh-md-toc](https://github.com/ekalinin/github-markdown-toc.go)

## 1.概要

### 1.1 项目介绍

本项目以在线打车网络平台繁多，但价格参差不齐为背景。旨在开发出能够比较各在线平台价格的App，提供更实惠经济的价格给用户。

- 项目背景：在线打车价格不一
- 针对对象：使用在线打车平台的所有用户
- 项目目的：给用户提供更经济实惠的打车方式
- 项目组成：Android客户端
- 职责担任：本项目中担任Android开方成员

### 1.2 客户端功能介绍

1. 登陆注册
2. 实时定位
3. 查询附近车辆信息
4. 选择上下车地点
   1. 拖动地图层界面进行选择
   2. 文字输入方式选择
5. 各平台价格比对
6. 模拟钱包充值
7. 历史订单查看
8. 约车结束评价

## 2. 功能展示

### 登陆

​    <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E7%99%BB%E9%99%86.jpg" title="登陆" width="250px" alt="登陆">  <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81.jpg" title="电话号码"  width="250px" alt="电话号码">

### 定位&上下车选择&价格比较

​    <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E5%AE%9E%E6%97%B6%E5%AE%9A%E4%BD%8D.jpg" title="实时定位"  width="250px" alt="实时定位">  <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E5%9C%B0%E5%9D%80%E9%80%89%E6%8B%A9.jpg" title="地址选择"  width="250px" alt="地址选择">  <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E4%BB%B7%E6%A0%BC%E6%AF%94%E8%BE%83.jpg" title="价格比价"  width="250px" alt="价格比较">

### 侧边栏设计&模拟钱包充值

 <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E4%BE%A7%E8%BE%B9%E6%A0%8F.jpg" title="侧边栏"  width="250px" alt="侧边栏">  <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E6%A8%A1%E6%8B%9F%E9%92%B1%E5%8C%85%E5%85%85%E5%80%BC.jpg" title="模拟钱包充值"  width="250px" >  <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E5%8E%86%E5%8F%B2%E8%AE%A2%E5%8D%95.jpg" title="历史订单"  width="250px">

### 等待接单&司机接单

  <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E6%A8%A1%E6%8B%9F%E7%AD%89%E5%BE%85.jpg" title="等待接单" width="250px" alt="等待接单">  <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E5%8F%B8%E6%9C%BA%E6%8E%A5%E5%8D%95.jpg" title="司机接单"  width="250px">


## 3 开发环境及技术支持

### 3.1  开发环境及运行平台

#### 3.1.1 开发环境

- Android Studio 3.0.1
- JDK 1.8,java语言开发

#### 3.1.2 运行环境

- Android平台系列手机
- minSDK>=15

### 3.2 技术支持

1. 界面设计
   - 遵从Google Material Design设计。
   - 参考“神州专车”App设计
2. 网络数据交互
   - OkHttp3：网络请求的优秀开源框架
   - Retrofit2+RxJava：简化网络请求API与主子线程调度
3. 本地数据存储
   - SharedPreference：Android 自带简单本地存储API。
4. 代码解耦
   - MVP设计模式：业务分为3个层次，M-Model，V-View，P-presnter，通过p进行中转达到解耦。
   - Dagger2：依赖注入框架，减少模块之间的依赖。
5. 使用到的API
   - [神州专车API](http://developer.10101111.com/resourceCenter/doc?r=guide/content#api%E6%A8%A1%E5%BC%8F%E6%8E%A5%E5%85%A5%E6%B5%81%E7%A8%8B)
   - 滴滴打车，未提供正式接入。本项目采用其提供的计价方式进行计价。如：
   - <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E6%BB%B4%E6%BB%B4%E4%B8%93%E8%BD%A6%E8%AE%A1%E4%BB%B7.jpg" title="专车"  width="250px"> <img src="https://github.com/zzbb1199/TaxiHelper/blob/master/pic/%E6%BB%B4%E6%BB%B4%E5%BF%AB%E8%BD%A6%E8%AE%A1%E4%BB%B7.jpg" title="快车"  width="250px" > 

## 4 最后

**本项目为实验项目，所有功能并未上线，无法做到真正网约车，最终结果仅供参考。**