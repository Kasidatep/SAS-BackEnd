package sit.int221.sas.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sas.dto.*;
import sit.int221.sas.entities.Announcement;
import sit.int221.sas.entities.Category;
import sit.int221.sas.repositories.AnnouncementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    public List<AllAnnouncementDto> getAllAnnouncements() {
        Sort sort = Sort.by("id").descending();
        List<Announcement> announcements = announcementRepository.findAll(sort);
        return announcements.stream().map(c -> modelMapper.map(c, AllAnnouncementDto.class)).collect(Collectors.toList());
    }

    public AnnouncementDetailDto getAnnouncementById(Integer id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Announcement id " + id +" does not exist"));
        return modelMapper.map(announcement, AnnouncementDetailDto.class);
    }

    public Announcement getAnnouncementById2(Integer id) {
        return announcementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Announcement id " + id +" does not exist"));
    }

    public CreateAnnouncementReturnDto addAnnouncement(CreateAnnouncementDto announcement) {
        Category category = categoryService.getCategoryById(announcement.getCategoryId());
        Announcement newAnnouncement = new Announcement();
        newAnnouncement.setAnnouncementTitle(announcement.getAnnouncementTitle());
        newAnnouncement.setAnnouncementDescription(announcement.getAnnouncementDescription());
        newAnnouncement.setPublishDate(announcement.getPublishDate());
        newAnnouncement.setCloseDate(announcement.getCloseDate());
        newAnnouncement.setAnnouncementDisplay(announcement.getAnnouncementDisplay());
        newAnnouncement.setCategory(category);
        return modelMapper.map(announcementRepository.saveAndFlush(newAnnouncement),CreateAnnouncementReturnDto.class);
    }

    public void deleteAnnouncement(Integer id) {
        if(announcementRepository.existsById(id)){
            announcementRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The announcement is not found");
        }

    }

    public CreateAnnouncementReturnDto updateAnnouncement(Integer id, CreateAnnouncementDto announcement){
            Announcement updateAnnouncement =  announcementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"The announcement is not found"));
            Category category = categoryService.getCategoryById(announcement.getCategoryId());
            updateAnnouncement.setAnnouncementTitle(announcement.getAnnouncementTitle());
            updateAnnouncement.setAnnouncementDescription(announcement.getAnnouncementDescription());
            updateAnnouncement.setPublishDate(announcement.getPublishDate());
            updateAnnouncement.setCloseDate(announcement.getCloseDate());
            updateAnnouncement.setAnnouncementDisplay(announcement.getAnnouncementDisplay());
            updateAnnouncement.setCategory(category);
            return modelMapper.map(announcementRepository.saveAndFlush(updateAnnouncement),CreateAnnouncementReturnDto.class);
    }

    public PageDto<AllAnnouncementDto> getAllAnnouncementByPage(String mode, String page, String size) {
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), Sort.by("id").descending());
        Page<Announcement> announcementPage = announcementRepository.findAll(pageable);
        return toPageDTO(announcementPage, AllAnnouncementDto.class, modelMapper);
    }

    public Page<Announcement> getAllAnnouncementByPageTest(String mode, String page, String size) {
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), Sort.by("id").descending());
        return announcementRepository.findAll(pageable);
    }


    // Dto Page
    public <S, T> List<T> mapList(List<S> source,  Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass))
                .collect(Collectors.toList());
    }
    public <S, T> PageDto<T> toPageDTO(Page<S> source, Class<T> targetClass,
                                       ModelMapper modelMapper) {
        PageDto<T> page = modelMapper.map(source, PageDto.class);
        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return page;
    }
}
