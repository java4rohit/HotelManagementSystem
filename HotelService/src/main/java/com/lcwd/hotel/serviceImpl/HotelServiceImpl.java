package com.lcwd.hotel.serviceImpl;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exception.ResourceNotFoundException;
import com.lcwd.hotel.repository.HotelRepository;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HotelServiceImpl implements HotelService {


    @Autowired
    HotelRepository hotelRepository;

    @Override
    public List<Hotel> creates(List<Hotel> hotels) {
        List<Hotel> saveHotel = hotelRepository.saveAll(hotels);
        return saveHotel;
    }

    @Override
    public Hotel create(Hotel hotel) {
        Hotel saveHotel = hotelRepository.save(hotel);
        return saveHotel;
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String hotelId) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        return optionalHotel.orElseThrow(() -> new ResourceNotFoundException("hotel with given Id is not found on Server !! : " + hotelId));
    }

    @Override
    public String delete(String userId) {
        return null;
    }

    @Override
    public String update(String hotelId) {
        return null;
    }


}
