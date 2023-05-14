package sit.int221.sas.controllers;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sit.int221.sas.dto.*;
import sit.int221.sas.entities.Announcement;
import sit.int221.sas.services.AnnouncementService;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/announcements")
public class AnnouncementControllers {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("")
    public List<AllAnnouncementDto> getAllAnnouncements(@RequestParam(defaultValue = "admin") String mode) {
        return announcementService.getAllAnnouncements(mode);
    }

    @GetMapping("/{id}")
    public AnnouncementDetailDto getAnnouncementById(@PathVariable Integer id) {
        return announcementService.getAnnouncementById(id);
    }


    @PutMapping("/{id}")
    public CreateAnnouncementReturnDto updateAnnouncement(
            @PathVariable Integer id,
            @Valid @RequestBody CreateAnnouncementDto announcement, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException((MethodParameter) null, bindingResult);
        }else{
            return announcementService.updateAnnouncement(id, announcement);
        }

    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
    }

    @GetMapping("/pages")
    public PageDto<AllAnnouncementDto> getAllAnnouncementByPage(
            @RequestParam(defaultValue = "admin") String mode,
            @RequestParam(defaultValue = "0") String page,
            @RequestParam(defaultValue = "5") String size,
            @RequestParam(defaultValue = "0") String category
    ) {
        return announcementService.getAllAnnouncementByPage(mode, page, size, category);
    }

    @GetMapping("/page-test")
    public Page<Announcement> getAllAnnouncementByPageTest(
            @RequestParam String mode,
            @RequestParam(defaultValue = "0") String page,
            @RequestParam(defaultValue = "5") String size
    ) {
        return announcementService.getAllAnnouncementByPageTest(mode, page, size);
    }

    @PostMapping("")
    public CreateAnnouncementReturnDto addAnnouncement(@Valid @RequestBody CreateAnnouncementDto announcement, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException((MethodParameter) null, bindingResult);
        }
        return announcementService.addAnnouncement(announcement);
    }
}