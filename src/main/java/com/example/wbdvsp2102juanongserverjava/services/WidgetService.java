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

    }

    public Widget findWidgetById(Integer wid) throws IllegalArgumentException {
        if (repository.findById(wid).isPresent()) {
            return repository.findById(wid).get();
        }
        throw new IllegalArgumentException("Could not find widget with given ID");
    }

    public Widget createWidget(String tid, Widget widget) {
        // The save function invokes an SQL insert statement
        return repository.save(widget);
    }

    public Integer deleteWidget(Integer wid) throws IllegalArgumentException {
        repository.deleteById(wid);
        return 1;
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

        if (widget.getOrdered() != null) {
            originalWidget.setOrdered(widget.getOrdered());
        }

        repository.save(originalWidget);

        return 1;
    }

}
