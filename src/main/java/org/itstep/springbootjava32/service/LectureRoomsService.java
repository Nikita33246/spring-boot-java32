package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.LectureRooms;
import org.itstep.springbootjava32.repository.LectureRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureRoomsService {

    private final LectureRoomsRepository lectureRoomRepository;

    @Autowired
    public LectureRoomsService(LectureRoomsRepository lectureRoomRepository) {
        this.lectureRoomRepository = lectureRoomRepository;
    }

    public List<LectureRooms> getAllLectureRooms() {
        return lectureRoomRepository.findAll();
    }

    public LectureRooms getLectureRoomById(Long id) {
        return lectureRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lecture room not found"));
    }

    public List<LectureRooms> getRoomsByBuilding(String building) {
        return lectureRoomRepository.findByBuilding(building);
    }

    public List<LectureRooms> getRoomsByNamePart(String namePart) {
        return lectureRoomRepository.findRoomsByNamePart(namePart);
    }

    public LectureRooms addLectureRoom(LectureRooms lectureRoom) {
        return lectureRoomRepository.save(lectureRoom);
    }

    public LectureRooms updateLectureRoom(Long id, LectureRooms updatedLectureRoom) {
        LectureRooms existingLectureRoom = getLectureRoomById(id);
        existingLectureRoom.setBuilding(updatedLectureRoom.getBuilding());
        existingLectureRoom.setName(updatedLectureRoom.getName());
        return lectureRoomRepository.save(existingLectureRoom);
    }

    public void deleteLectureRoom(Long id) {
        lectureRoomRepository.deleteById(id);
    }
}

