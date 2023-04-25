package sit.int221.sas.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.sas.dto.AllAnnouncement;
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
    public List<AllAnnouncement> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        return announcements.stream().map(c -> modelMapper.map(c, AllAnnouncement.class)).collect(Collectors.toList());
    }
}
