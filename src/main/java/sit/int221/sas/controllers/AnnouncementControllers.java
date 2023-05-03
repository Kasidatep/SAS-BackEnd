package sit.int221.sas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.sas.dto.AllAnnouncementDto;
import sit.int221.sas.dto.AnnouncementDetailDto;
import sit.int221.sas.dto.CreateAnnouncementDto;
import sit.int221.sas.dto.CreateAnnouncementReturnDto;
import sit.int221.sas.entities.Announcement;
import sit.int221.sas.services.AnnouncementService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/announcements")
public class AnnouncementControllers {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("")
    public List<AllAnnouncementDto> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{id}")
    public AnnouncementDetailDto getAnnouncementById(@PathVariable Integer id) {
        return announcementService.getAnnouncementById(id);
    }

    @GetMapping("/test/{id}")
    public Announcement getAnnouncementById2(@PathVariable Integer id) {
        return announcementService.getAnnouncementById2(id);
    }

    @PostMapping("")
    public CreateAnnouncementReturnDto addAnnouncement(@RequestBody CreateAnnouncementDto announcement) {
        return announcementService.addAnnouncement(announcement);
    }

    @PutMapping("/{id}")
    public CreateAnnouncementReturnDto updateAnnouncement(
            @PathVariable Integer id,
            @RequestBody CreateAnnouncementDto announcement) {
        return announcementService.updateAnnouncement(id, announcement);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
    }
}