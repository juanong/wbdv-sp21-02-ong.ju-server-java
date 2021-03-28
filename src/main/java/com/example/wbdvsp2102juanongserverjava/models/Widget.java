package com.example.wbdvsp2102juanongserverjava.models;

import javax.persistence.*;

/*
This class represents a widget that will be specific to a parent Topic
 */
@Entity // Annotate this an Entity to give it to mySQL
@Table(name="widgets")
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;
    private String topicId;
    private Integer widgetOrder;
    private Integer size;
    private String text;
    private Integer width;
    private Integer height;
    private String cssClass;
    private String style;
    private String value;
    private String src;

    // Constructor with optional name parameter
/*    public Widget(String name, Integer id, String topicId, String type, Integer size, String text) {
        this.name = name;
        this.id = id;
        this.topicId = topicId;
        this.type = type;
        this.size = size;
        this.text = text;
    }

    public Widget(Integer id, String topicId, String type, Integer size, String text) {
        this.id = id;
        this.topicId = topicId;
        this.type = type;
        this.size = size;
        this.text = text;
    }

    public Widget(String type, Integer size, String text) {
        this.type = type;
        this.size = size;
        this.text = text;
    }

    public Widget() {}*/

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSrc() {return src;}

    public void setSrc(String src) {this.src = src;}

    public Integer getWidgetOrder() {return widgetOrder;}

    public void setWidgetOrder(Integer widgetOrder) {this.widgetOrder = widgetOrder;}

    public Integer getWidth() {return width;}

    public void setWidth(Integer width) {this.width = width;}

    public Integer getHeight() {return height;}

    public void setHeight(Integer height) {this.height = height;}

    public String getCssClass() {return cssClass;}

    public void setCssClass(String cssClass) {this.cssClass = cssClass;}

    public String getStyle() {return style;}

    public void setStyle(String style) {this.style = style;}

    public String getValue() {return value;}

    public void setValue(String value) {this.value = value;}

}
