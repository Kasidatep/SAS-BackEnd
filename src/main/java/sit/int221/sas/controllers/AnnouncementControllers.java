package sit.int221.sas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.sas.dto.AllAnnouncement;
import sit.int221.sas.services.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementControllers {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("")
    public List<AllAnnouncement> getAllAnnouncements(){
        return announcementService.getAllAnnouncements();
    }
}
