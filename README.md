# Android IconicLabelView
An expandable label view with icon

## Demo
![](/screencast.gif)

## Usage

```xml
<com.rajasharan.widget.IconicLabelView
    android:id="@+id/label"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="20dp"
    android:layout_marginLeft="20dp"
    app:label_icon="@drawable/ic_launcher"
    app:bg_color="#5A808080"
    app:corner_radius="4.0"
    app:text_color="#C80000"
    app:text_size="14sp"
    app:text1="@string/short_text"
    app:text2="@string/long_text"
    />
```

Or easily setup via code

```java
IconicLabelView label = (IconicLabelView) findViewById(R.id.label);
label.setIcon(getResources().getDrawable(R.mipmap.ic_launcher))
     .setBackground(Color.argb(90, 128, 128, 128))
     .setCornerRadius(6.0f)
     .setTextColor(Color.argb(255, 200, 0, 0))
     .setTextSize(16)
     .setTexts("Short Text", "Very Long Text");
```

## Useful Learning Resources
* [Meaning of ascent, descent, baseline etc.](http://stackoverflow.com/questions/27631736/meaning-of-top-ascent-baseline-descent-bottom-and-leading-in-androids-font)
* [Precise Android Text Drawing](http://www.slideshare.net/rtc1/intro-todrawingtextandroid)

## [License](/LICENSE)
    The MIT License (MIT)
