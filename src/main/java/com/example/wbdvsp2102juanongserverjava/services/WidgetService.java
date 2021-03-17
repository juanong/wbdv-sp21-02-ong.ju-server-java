package com.example.wbdvsp2102juanongserverjava.services;
import com.example.wbdvsp2102juanongserverjava.models.Widget;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
This class will store all of the CRUD operations for the Widget class. Eventually, this will be implemented
with a database
 */
@Service // Now, this class is accessible through Autowire
public class WidgetService {
    private List<Widget> widgets = new ArrayList<Widget>();
    {
        Widget w1 = new Widget( 123L, "ABC123", "HEADING", 1, "Widgets for Topic ABC123");
        Widget w2 = new Widget( 234L, "ABC123", "PARAGRAPH", 1, "Lorem Ipsum");
        Widget w3 = new Widget( 345L, "ABC234", "HEADING", 1, "Widgets for Topic ABC234");
        Widget w4 = new Widget( 456L, "ABC234", "PARAGRAPH", 1, "Lorem Ipsum");
        Widget w5 = new Widget( 567L, "ABC234", "PARAGRAPH", 1, "Lorem Ipsum");

        // Add to our list of widgets
        widgets.add(w1);
        widgets.add(w2);
        widgets.add(w3);
        widgets.add(w4);
        widgets.add(w5);
    }
    /*
    IMPLEMENT ALL CRUD OPERATIONS
     */

    // Retrieve all widgets
    public List<Widget> findAllWidgets() {
        return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        List<Widget> widgetsForTopic = new ArrayList<Widget>();

        for (Widget widget: widgets) {
            if (widget.getTopicId().equals(topicId)) {
                widgetsForTopic.add(widget);
            }
        }
        return widgetsForTopic;
    }

    public Widget findWidgetById(Long wid) throws IllegalArgumentException {
        for (Widget widget: widgets) {
            if (widget.getId().equals(wid)) {
                return widget;
            }
        }
        throw new IllegalArgumentException("Cannot find widget with given ID");
    }

    public Widget createWidget(String tid, Widget widget) {
        widget.setTopicId(tid);
        widget.setId((new Date()).getTime());

        widgets.add(widget);
        return widget;
    }

    public Integer deleteWidget(Long wid) throws IllegalArgumentException {
        int index = -1;
        for (int i=0; i<widgets.size(); i++) {
            if(widgets.get(i).getId().equals(wid)) {
                index = i;
                widgets.remove(index);
                return 1;
            }
        }
        return 0;
    }

}
