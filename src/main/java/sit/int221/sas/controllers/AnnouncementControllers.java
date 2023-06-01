package sit.int221.sas.controllers;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sit.int221.sas.dto.*;
import sit.int221.sas.services.AnnouncementService;

import java.util.*;

@CrossOrigin(origins = "http://intproj22.sit.kmutt.ac.th")
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
    public AnnouncementDetailDto getAnnouncementById(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "false") boolean count
    ) {
        return announcementService.getAnnouncementById(id,count);
    }

    @GetMapping("/pages")
    public PageDto<AllAnnouncementDto> getAllAnnouncementByPage(
            @RequestParam(defaultValue = "admin") String mode,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "0") Integer category
    ) {
        return announcementService.getAllAnnouncementByPage(mode, page, size, category);
    }

    @PostMapping("")
    public CreateAnnouncementReturnDto addAnnouncement(
            @Valid @RequestBody CreateAnnouncementDto announcement,
            BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException((MethodParameter) null, bindingResult);
        }
        return announcementService.addAnnouncement(announcement);
    }

    @PutMapping("/{id}")
    public CreateAnnouncementReturnDto updateAnnouncement(
            @PathVariable Integer id,
            @Valid @RequestBody CreateAnnouncementDto announcement,
            BindingResult bindingResult) throws MethodArgumentNotValidException {
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
}