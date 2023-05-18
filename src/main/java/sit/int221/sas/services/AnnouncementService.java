package sit.int221.sas.services;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sas.dto.*;
import sit.int221.sas.entities.Announcement;
import sit.int221.sas.entities.Category;
import sit.int221.sas.repositories.AnnouncementRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    public List<AllAnnouncementDto> getAllAnnouncements(String mode) {
        Sort sort = Sort.by("id").descending();
        if(Objects.equals(mode, "admin")){
            return announcementRepository.findAll(sort).stream().map(c -> modelMapper.map(c, AllAnnouncementDto.class)).collect(Collectors.toList());
        } else if(Objects.equals(mode, "active")){
            return announcementRepository.findActive(ZonedDateTime.now()).stream().map(c -> modelMapper.map(c, AllAnnouncementDto.class)).collect(Collectors.toList());
        } else if (Objects.equals(mode, "close")) {
            return announcementRepository.findClosed(ZonedDateTime.now()).stream().map(c -> modelMapper.map(c, AllAnnouncementDto.class)).collect(Collectors.toList());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Mode "+ mode+ " not define.");
        }
    }

    public AnnouncementDetailDto getAnnouncementById(Integer id, boolean count) {
        if(count){
            return modelMapper.map(addAnnouncementCount(id), AnnouncementDetailDto.class);
        }else{
            Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Announcement id " + id +" does not exist"));
            return modelMapper.map(announcement, AnnouncementDetailDto.class);
        }

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
        newAnnouncement.setViewCount(0);
       try {
            return modelMapper.map(announcementRepository.saveAndFlush(newAnnouncement),CreateAnnouncementReturnDto.class);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public void deleteAnnouncement(Integer id) {
        if(announcementRepository.existsById(id)){
            try {
                announcementRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
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
            try {
            return modelMapper.map(announcementRepository.saveAndFlush(updateAnnouncement),CreateAnnouncementReturnDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public PageDto<AllAnnouncementDto> getAllAnnouncementByPage(String mode, String page, String size, String category) {
        if(category.isBlank()) category = String.valueOf(0);
        Integer categoryId = Integer.valueOf(category);
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by("id").descending());
        ZonedDateTime currentTime = ZonedDateTime.now();
        if(Objects.equals(mode, "admin")){
            if(categoryId==0){
                return toPageDTO(announcementRepository.findAll(pageable), AllAnnouncementDto.class, modelMapper);
            }else{
                return toPageDTO(announcementRepository.findAllByCategoryId(categoryId, pageable), AllAnnouncementDto.class, modelMapper);            }
        } else if(Objects.equals(mode, "active")){
            if(categoryId==0){
                return toPageDTO(announcementRepository.findActive(currentTime, pageable), AllAnnouncementDto.class, modelMapper);
            }else{
                return toPageDTO(announcementRepository.findActive(currentTime, categoryId, pageable), AllAnnouncementDto.class, modelMapper);
            }
        } else if(Objects.equals(mode, "close")){
            if(categoryId==0){
                return toPageDTO(announcementRepository.findClosed(currentTime, pageable), AllAnnouncementDto.class, modelMapper);
            }else{
                return toPageDTO(announcementRepository.findClosed(currentTime, categoryId, pageable), AllAnnouncementDto.class, modelMapper);
            }
        }else{
//            return toPageDTO(announcementRepository.findAll(pageable), AllAnnouncementDto.class, modelMapper);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mode "+ mode + " not provide.");
        }
    }

    public Page<Announcement> getAllAnnouncementByPageTest(String mode, String page, String size) {
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), Sort.by("id").descending());
        return announcementRepository.findAll(pageable);
    }

    public Announcement addAnnouncementCount(Integer id) {
        if(announcementRepository.existsById(id)){
            Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"The announcement is not found"));;
            announcement.setViewCount(announcement.getViewCount()+1);
            return announcementRepository.saveAndFlush(announcement);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The announcement is not found");
        }
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
