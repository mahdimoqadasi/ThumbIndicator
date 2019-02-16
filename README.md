 # ThumbIndicator
[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=plastic)](https://android-arsenal.com/api?level=19)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![](https://jitpack.io/v/Shashank02051997/FancyWalkthrough-Android.svg)](https://jitpack.io/#Shashank02051997/FancyWalkthrough-Android)

This is a Thumbnail Indicator for android that binds with viewPager with ease.

## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	implementation 'com.github.mahdimoqadasi:ThumbIndicator:1.1.1'
}
```
# Walkthrough
This is an indicator to bind with `ViewPager`.

![demo_image](https://github.com/mahdimoqadasi/ThumbIndicator/blob/master/demo.gif)

## How to use

**add view to your layout file**

```java
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_gravity="center"
    android:background="@color/trans_black"
    tools:context=".MainActivity">

        <android.support.v4.view.ViewPager
            android:id="@+id/vpMain"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintBottom_toTopOf="@id/indicator"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <com.trex.lib.ThumbIndicator
            android:id="@+id/indicator"
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_gravity="center"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent" />
</android.support.constraint.ConstraintLayout>
```

**Then, use it in code like this**

```java
    private void setup() {
        VpAdapter adp = new VpAdapter();
        mVpMain.setAdapter(adp);
        mIndicator.setupWithViewPager(mVpMain, mImgs, 70);
    }
```

## Dependencies

This project use this libraries ~ Thanks to them.

  [Glide](https://github.com/bumptech/glide) ver 4.8.0

## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/mahdimoqadasi/ThumbIndicator/pulls).

Any contributions, large or small, major features, bug fixes, are welcomed and appreciated
but will be thoroughly reviewed.

### Contact - Let's become friend
- [Twitter](https://twitter.com/mahdimoqadasi)
- [Github](https://github.com/mahdimoqadasi)
- [Linkedin](https://www.linkedin.com/in/mahdi-moqadasi-812228176/)
- [Telegram](https://t.me/MahdiMoqadasi)

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2018 Mahdi Moqadasi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
