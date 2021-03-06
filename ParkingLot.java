//Time - Park , nextAvailable - O(1) | Unpark and addSpot - O(log(floors*spots))
//Space -  O(floors*spots)

// "static void main" must be defined in a public class.
import java.lang.*;
public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(10,20);
        lot.addSpot(1,1);
        lot.addSpot(2,1);
        lot.addSpot(3,1);
        lot.addSpot(1,2);
        lot.addSpot(2,2);
        lot.addSpot(3,2);
        
        ParkingSpot ps = lot.nextAvailable();
        System.out.println("Next available spot is in floor "+ps.floor+" ; at spot numer "+ps.spot);
        
        lot.park();
        
        ParkingSpot ps2 = lot.nextAvailable();
        System.out.println("Next available spot is in floor "+ps2.floor+" ; at spot numer "+ps2.spot);
        lot.unpark(1,1);
        
        ParkingSpot ps3= lot.nextAvailable();
        System.out.println("Next available spot is in floor "+ps3.floor+" ; at spot numer "+ps3.spot);
        
    }
}

class ParkingLot{
    int maxFloors;
    int spotsPerFloor;
    PriorityQueue<ParkingSpot> q = new PriorityQueue<>((a,b)->{
        if(a.floor==b.floor){
            return a.spot-b.spot;
        }else{
            return a.floor - b.floor;
        }
    });
    
    public ParkingLot(int floors, int spots){
        this.maxFloors = floors;
        this.spotsPerFloor = spots;
    }
    
    public ParkingSpot park(){
        if(q.isEmpty()){
            throw new IllegalStateException("Parking lot is full!");
        }
        System.out.println("Parked! \n");
        return q.remove();
    }
    
    public void unpark(int floor, int spot){
        addSpot(floor,spot);
        System.out.println("Spot freed ! \n");
    }
    
    public ParkingSpot nextAvailable(){
        if(q.isEmpty()){
            throw new IllegalStateException("Parking lot is full!");
        }
        return q.peek();
    }
    
    public void addSpot(int floor,int spot){
        if(floor > maxFloors){
            throw new IllegalStateException("Given floor does not exist");
        }
        
        if(spot>spotsPerFloor){
            throw new IllegalStateException("Given spot does not exist");
        }
        
        ParkingSpot newSpot = new ParkingSpot(floor,spot);
        q.add(newSpot);
    }
}

class ParkingSpot{
    int floor;
    int spot;
    
    public ParkingSpot(int floor,int spot){
        this.floor = floor;
        this.spot = spot;
    }
    private int getFloor(){
        return this.floor;
    }
    
    private int getSpot(){
        return this.spot;
    }
}
