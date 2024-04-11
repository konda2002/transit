import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
	
		TNode FirstTrain= new TNode();
		FirstTrain.location = 0;
		FirstTrain.next=null;

		TNode last1=new TNode();
		last1=FirstTrain;

		for(int i=0;i<trainStations.length;i++){
			TNode t=new TNode(trainStations[i]);
			last1.next=t;
			last1=t;


		}


		TNode FirstBus= new TNode();
		FirstBus.location = 0;
		FirstBus.next=null;
		TNode last2=new TNode();
		last2=FirstBus;

		for(int i=0;i<busStops.length;i++){
			TNode t=new TNode(busStops[i]);
			t.next=null;
			last2.next=t;
			last2=t;


		}


		TNode FirstWalk= new TNode();
		FirstWalk.location=0;
		FirstWalk.next=null;
		TNode last3=new TNode();
		last3=FirstWalk;
		
		for(int i=0;i<locations.length;i++){
			TNode t=new TNode(locations[i]);
			t.next=null;
			last3.next=t;
			last3=t;
		}


TNode ptr1= FirstTrain;
TNode ptr2= FirstBus;

while(ptr1!=null){
	while(ptr2!=null){
		if(ptr1.location==ptr2.location){
			ptr1.down=ptr2;
		}
		ptr2=ptr2.next;
	}
	ptr2 = ptr1.down;
	ptr1=ptr1.next;
}


TNode ptr3=FirstBus;
TNode ptr4=FirstWalk;

while(ptr3!=null){
	while(ptr4!=null){
if(ptr3.location==ptr4.location){
	ptr3.down=ptr4;
}
ptr4=ptr4.next;
	}
	ptr4 = ptr3.down;
	ptr3=ptr3.next;
}





FirstTrain.down=FirstBus;
FirstBus.down=FirstWalk;











		return FirstTrain;
	}
	
	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {
		
		
		
		
		
		
		TNode ptr1=trainZero;
		while(ptr1.next!=null){
			if(ptr1.next.location==station && ptr1.next.next==null){
ptr1.next.down=null;
ptr1.next=null;
break;
			}else if(ptr1.next.location!=station && ptr1.next.next==null){
				return;
			}
			
			if(ptr1.next.location==station){
ptr1.next.down=null;
ptr1.next=ptr1.next.next;
break;

			}


			
			
			
			ptr1=ptr1.next;
		}
		
		
		




	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {
		
		
		
		
		TNode ptr1=new TNode();
		ptr1=trainZero.down;
		
		TNode t=new TNode();
		t.location=busStop;
		t.next=null;
		
		
		
		
		while(ptr1!=null){
			
if(ptr1.location==t.location){
	return;
}
else if(t.location> ptr1.location && ptr1.next==null){
				ptr1.next=t;
				t.next=null;
				break;
			} 
else if(t.location>ptr1.location && t.location<ptr1.next.location){

TNode temp= new TNode();
temp=ptr1.next;
ptr1.next=t;
t.next=temp;
break;

			}



			ptr1=ptr1.next;
		}


		
		TNode ptr3=new TNode();
		ptr3=trainZero.down.down;

		while(ptr3!=null){
			
			if(ptr3.location==t.location){
				
				t.down=ptr3;
			break;
			}
		ptr3=ptr3.next;
		}
	
	
	
	}

	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {
		
		ArrayList<TNode> BestPath= new ArrayList<TNode>();
		TNode ptr1=new TNode();
		ptr1=trainZero;
		
	
		
		while(ptr1!=null ){
		
		
		if(ptr1.next==null){
			BestPath.add(ptr1);
			ptr1=ptr1.down;
			continue;
		}
			if(destination>=ptr1.next.location ){
			BestPath.add(ptr1);
			ptr1=ptr1.next;
continue;
		}

		if(destination<ptr1.next.location){
			BestPath.add(ptr1);
			ptr1=ptr1.down;
			continue;
		}

		if(destination==ptr1.location && ptr1.down!=null){
			BestPath.add(ptr1);
			ptr1=ptr1.down;
			continue;
		} 
		if(destination==ptr1.location && ptr1.down==null){
			BestPath.add(ptr1);
			return BestPath;
		}
		



		}
		return BestPath;


	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		TNode ptr1= new TNode();
		TNode ptr2= new TNode();
		TNode ptr3= new TNode();

		ptr1=trainZero.next;
		ptr2=trainZero.down.next;
		ptr3=trainZero.down.down.next;
		ArrayList<Integer> trains= new ArrayList<Integer>();
while(ptr1!=null){
	trains.add(ptr1.location);
	ptr1=ptr1.next;
}

ArrayList<Integer> bus= new ArrayList<Integer>();


while(ptr2!=null){
bus.add(ptr2.location);
ptr2=ptr2.next;

}
ArrayList<Integer> walk= new ArrayList<Integer>();

while(ptr3!=null){
	walk.add(ptr3.location);
	ptr3=ptr3.next;
}

int[] traindup=new int[trains.size()];

for(int i=0;i<trains.size();i++){
	traindup[i]=trains.get(i);
}


int[] busdup=new int[bus.size()];
for(int i=0;i<bus.size();i++){
busdup[i]=bus.get(i);
}


int[] walkdup= new int[walk.size()];
for(int i=0;i<walk.size();i++){
	walkdup[i]=walk.get(i);
}

		return makeList(traindup,busdup,walkdup);


	}
	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		TNode FirstScooter= new TNode();
		FirstScooter.location = 0;
		FirstScooter.next=null;

		TNode last1=new TNode();
		last1=FirstScooter;

		for(int i=0;i<scooterStops.length;i++){
			TNode t=new TNode(scooterStops[i]);
			last1.next=t;
			last1=t;


		}



		TNode ptr1=trainZero.down;
TNode ptr2=FirstScooter;
TNode temp=new TNode();

while(ptr1!=null){
			while(ptr2!=null){
if(ptr1.location==ptr2.location){
temp=ptr1.down;
ptr1.down=ptr2;
ptr2.down=temp;
break;
}
ptr2=ptr2.next;

			}
			ptr2=FirstScooter;
			ptr1=ptr1.next;
		}
	

TNode ptr3=FirstScooter;
TNode ptr4=trainZero.down.down.down;

while(ptr3!=null){
	while(ptr4!=null){
		if(ptr3.location==ptr4.location){
			ptr3.down=ptr4;
			break;
		}
		ptr4=ptr4.next;
	}
	ptr4=trainZero.down.down.down;
	ptr3=ptr3.next;

}

	
	
	}
	

	
	
	}







	

