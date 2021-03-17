package com.example.wbdvsp2102juanongserverjava.models;

/*
This class represents a widget that will be specific to a parent Topic
 */
public class Widget {
    private String name;
    private Long id;
    // For this assignment, only use two types: heading and paragraph
    private String type;
    private String topicId;
    private int widgetOrder;
    private Integer size;
    private String text;
    private Integer width;
    private Integer height;
    private String cssClass;
    private String style;
    private String value;

    // Constructor with optional name parameter
    public Widget(String name, Long id, String topicId, String type, Integer size, String text) {
        this.name = name;
        this.id = id;
        this.topicId = topicId;
        this.type = type;
        this.size = size;
        this.text = text;
    }

    public Widget(Long id, String topicId, String type, Integer size, String text) {
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

    public Widget() {}

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
