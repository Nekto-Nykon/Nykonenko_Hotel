package nykon.pisspringboot.Controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nykon.pisspringboot.Controller.Request.CreateRoomData;
import nykon.pisspringboot.Controller.Request.Dates;
import nykon.pisspringboot.Controller.Request.RoomAllParamData;
import nykon.pisspringboot.Controller.Request.UpdateRoom;
import nykon.pisspringboot.Model.ComfortLevel;
import nykon.pisspringboot.Model.Room;
import nykon.pisspringboot.Service.RoomService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController{
    private final RoomService roomService;
    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms(){

        return ResponseEntity.ok(roomService.getAllRooms());
    }
    @PostMapping("/all-with-date")
    public ResponseEntity<List<Room>> getAllAvailable(@RequestBody Dates dates){
        return ResponseEntity.ok(roomService.getAllAvailableInDate(dates.getCheckIn(), dates.getCheckOut()));
    }
    @PostMapping("create-room")
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomData roomData){
        return ResponseEntity.ok(roomService.createRoom(roomData.getName(), roomData.getComfortLevel(), roomData.getAdultCapacity(),roomData.getChildCapacity(),roomData.getPrice()));
    }

   @PostMapping("/all-with-param")
   public ResponseEntity<List<Room>> getAllAvailable
           (@RequestBody RoomAllParamData roomAllParamData){
    return ResponseEntity.ok(roomService.getAllAvailable(roomAllParamData.getCheckin(), roomAllParamData.getCheckOut(), roomAllParamData.getComfortLevel(), roomAllParamData.getAdultCapacity(), roomAllParamData.getChildCapacity()));
    }
    @PatchMapping("/update-price")
    public ResponseEntity<Room> updatePrice(@RequestBody UpdateRoom updateRoomData){
        return ResponseEntity.ok(roomService.updateRoomPrice(updateRoomData.getIdRoom(), updateRoomData.getPrice()));
    }

}
