# Android IconicLabelView
An expandable label view with icon

## Demo
![](/screencast.gif)

## Usage
XML attrs coming soon ...

```xml
<com.rajasharan.widget.IconicLabelView
    android:id="@+id/label"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="20dp"
    android:layout_marginLeft="20dp"
    />
```

Setup via code

```java
IconicLabelView label = (IconicLabelView) findViewById(R.id.label);
label.setIcon(getResources().getDrawable(R.mipmap.ic_launcher))
     .setBackground(Color.argb(90, 128, 128, 128))
     .setCornerRadius(6.0f)
     .setTextColor(Color.argb(255, 200, 0, 0))
     .setTextSize(16)
     .setTexts("Short Text", "Very Long Text");
```

## [License](/LICENSE)
    The MIT License (MIT)
