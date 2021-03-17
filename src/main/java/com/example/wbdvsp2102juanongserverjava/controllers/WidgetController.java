package com.example.wbdvsp2102juanongserverjava.controllers;

import com.example.wbdvsp2102juanongserverjava.models.Widget;
import com.example.wbdvsp2102juanongserverjava.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

// Annotate as a REST controller, which lets all the content within this class available through HTTP requests
@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
    @Autowired // Create an instance of the widget service along with a dependency
    WidgetService service;

    // If an HTTP request comes in with this format, execute this function
    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets () {
        return service.findAllWidgets();
    }

    // The URL here needs to include the specific topicId
    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
            // Pass the path variable to the function
            @PathVariable("tid") String topicId
    ) {
        return service.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets/wid")
    public Widget findWidgetById(Long wid) {
        return service.findWidgetById(wid);
    }

    @PostMapping("api/topics/{tid}/widgets")
    public Widget createWidget(
            @PathVariable("tid") String tid,
            // The widget to add is going to be given to us from the fetch
            @RequestBody Widget widget)
    {
        return service.createWidget(tid, widget);
    }

    @DeleteMapping("api/widgets/{wid}")
    public Integer deleteWidget(
            @PathVariable("wid") Long wid)
    {
                return service.deleteWidget(wid);
    }

}
