package sit.int221.sas.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int221.sas.dto.AllAnnouncementDto;
import sit.int221.sas.dto.AnnouncementDetailDto;
import sit.int221.sas.entities.Announcement;
import sit.int221.sas.repositories.AnnouncementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<AllAnnouncementDto> getAllAnnouncements() {
        Sort sort = Sort.by("id").descending();
        List<Announcement> announcements = announcementRepository.findAll(sort);
        return announcements.stream().map(c -> modelMapper.map(c, AllAnnouncementDto.class)).collect(Collectors.toList());
    }

    public AnnouncementDetailDto getAnnouncementById(Integer id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Announcement ID " + id + " DO NOT EXIST !!"));
        return modelMapper.map(announcement, AnnouncementDetailDto.class);
    }
}
