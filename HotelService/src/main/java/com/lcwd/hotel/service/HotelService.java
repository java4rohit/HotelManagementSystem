package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface HotelService {

   List<Hotel> creates(List<Hotel> hotels);

   Hotel create(Hotel hotels);
   List<Hotel> getAll();
   Hotel get(String hotelId);
   String delete(String hotelId);
   String update(String hotelId);
}
