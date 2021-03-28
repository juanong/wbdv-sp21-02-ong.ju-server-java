package com.example.wbdvsp2102juanongserverjava.services;
import com.example.wbdvsp2102juanongserverjava.models.Widget;
import com.example.wbdvsp2102juanongserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
This class will store all of the CRUD operations for the Widget class. Eventually, this will be implemented
with a database
 */
@Service // Now, this class is accessible through Autowire
public class WidgetService {

    // Bring in the Widget repository interface
    @Autowired
    WidgetRepository repository;

    private List<Widget> widgets = new ArrayList<Widget>();
    {
/*        Widget w1 = new Widget( 123L, "ABC123", "HEADING", 1, "Widgets for Topic ABC123");
        Widget w2 = new Widget( 234L, "ABC123", "PARAGRAPH", 1, "Lorem Ipsum");
        Widget w3 = new Widget( 345L, "ABC234", "HEADING", 1, "Widgets for Topic ABC234");
        Widget w4 = new Widget( 456L, "ABC234", "PARAGRAPH", 1, "Lorem Ipsum");
        Widget w5 = new Widget( 567L, "ABC234", "PARAGRAPH", 1, "Lorem Ipsum");

        // Add to our list of widgets
        widgets.add(w1);
        widgets.add(w2);
        widgets.add(w3);
        widgets.add(w4);
        widgets.add(w5);*/
    }
    /*
    IMPLEMENT ALL CRUD OPERATIONS
     */

    // Retrieve all widgets
    public List<Widget> findAllWidgets() {
        // Need to cast the return type as a list instead of default iterable
        return (List<Widget>) repository.findAll();
        //return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {

       return repository.findWidgetsForTopic(topicId);

        /*List<Widget> widgetsForTopic = new ArrayList<Widget>();
        for (Widget widget: widgets) {
            if (widget.getTopicId().equals(topicId)) {
                widgetsForTopic.add(widget);
            }
        }
        return widgetsForTopic;*/
    }

    public Widget findWidgetById(Integer wid) throws IllegalArgumentException {
        for (Widget widget: widgets) {
            if (widget.getId() == wid) {
                return widget;
            }
        }
        throw new IllegalArgumentException("Cannot find widget with given ID");
    }

    public Widget createWidget(String tid, Widget widget) {
        // The save function invokes an SQL insert statement
        return repository.save(widget);
    }

    public Integer deleteWidget(Integer wid) throws IllegalArgumentException {
        repository.deleteById(wid);
        return 1;
/*        int index = -1;
        for (int i=0; i<widgets.size(); i++) {
            if(widgets.get(i).getId().equals(wid)) {
                index = i;
                widgets.remove(index);
                return 1;
            }
        }
        return 0;*/
    }

    public Integer updateWidget(int wid, Widget widget) {

        Widget originalWidget;
        if (repository.findById(wid).isPresent()) {
            originalWidget = repository.findById(wid).get();
        }
        else {
            return -1;
        }

        // Set all the non-null widget parameters
        if (widget.getSize() != null) {
            originalWidget.setSize(widget.getSize());
        }

        if (widget.getText() != null) {
            originalWidget.setText(widget.getText());
        }

        if (widget.getType() != null) {
            originalWidget.setType(widget.getType());
        }

        if (widget.getWidgetOrder() != null) {
            originalWidget.setWidgetOrder(widget.getWidgetOrder());
        }

        if (widget.getCssClass() != null) {
            originalWidget.setCssClass(widget.getCssClass());
        }

        if (widget.getSrc() != null) {
            originalWidget.setSrc(widget.getSrc());
        }

        if (widget.getWidth() != null) {
            originalWidget.setWidth(widget.getWidth());
        }

        if (widget.getHeight() != null) {
            originalWidget.setHeight(widget.getHeight());
        }

        if (widget.getValue() != null) {
            originalWidget.setValue(widget.getValue());
        }

        if (widget.getStyle() != null) {
            originalWidget.setStyle(widget.getStyle());
        }

        repository.save(originalWidget);

        return 1;

/*        int index = -1;
        for (int i=0; i<widgets.size(); i++) {
            if(widgets.get(i).getId().equals(wid)) {
                index = i;
                widgets.set(index, widget);
                return 1;
            }
        }
        return 0;*/
    }

}
