# UpdateCheckerLib
一个简单小巧的Android检查更新库，通过爬网页的方式查询应用在市场上的最新版本信息
目前支持Google Play,酷安网和豌豆荚的检查。

# 缺陷与优点
* 缺陷：只能获取到最新版本名称（`versionName`），无法获取到`versionCode`
* 优点：无需其他配置，一行搞定

# Gradle
项目级build.gradle添加：
```grooxy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

将下面这行依赖添加到 应用 级build.gradle，随后Sync即可完成

`compile 'kh.android:UpdateCheckerLib:1.2'`

# 添加权限
检查更新需要添加网络权限，如果没有，需要添加：
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
# 检查更新
--------
同步异步更新：
```java
UpdateChecker.check(UpdateChecker.Market 市场，String 包名)
```
返回UpdateInfo:
```java
getChangeLog() //获取更新日志，注：获取到的是html
getMarket() //获取市场
getPackageName() //获取包名
getVersionName() //获取最新版本名称
```
# Proguard
使用了Jsoup解析HTML，需要在proguard-rules.pro加入：
```
-keep class org.jsoup.**
```
# Licence 
--------
```
Copyright (C) 2017 Trumeet

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
