package sit.int221.sas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.sas.dto.AllAnnouncementDto;
import sit.int221.sas.dto.AnnouncementDetailDto;
import sit.int221.sas.services.AnnouncementService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/announcements")
public class AnnouncementControllers {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("")
    public List<AllAnnouncementDto> getAllAnnouncements(){
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{id}")
    public AnnouncementDetailDto getAnnouncementById(@PathVariable Integer id){
        return announcementService.getAnnouncementById(id);
    }


}
