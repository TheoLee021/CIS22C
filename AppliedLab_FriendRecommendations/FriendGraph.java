package Week10_AppliedLab5;

/**
 * FriendGraph.java
 * @author Theo Lee
 * CIS 22C, Applied Lab 5
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendGraph {
    private Graph friendGraph;
    private ArrayList<String> userNames;

    /**
     * Constructs a FriendGraph with a specified number of vertices.
     *
     * @param numVtx the number of vertices in the graph
     * @throws IllegalArgumentException if the number of vertices is less than or equal to 0
     */
    public FriendGraph(int numVtx) throws IllegalArgumentException {
        if (numVtx <= 0) {
            throw new IllegalArgumentException("Number of vertices must be greater than 0.");
        }
        this.friendGraph = new Graph(numVtx);
        this.userNames = new ArrayList<>(numVtx);
        for (int i = 0; i < numVtx; i++) {
            userNames.add("");
        }
    }

    /**
     * Loads user data from a specified file and populates the graph.
     *
     * @param fileName the name of the file to load user data from
     * @throws IOException if the file cannot be found or read
     */
    public void loadUsers(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("File not found.");
        }
        Scanner sc = new Scanner(file);

        int graphSize = sc.nextInt();
        sc.nextLine();

        while (sc.hasNextLine()) {
            int userId = Integer.parseInt(sc.nextLine().trim());
            String userName = sc.nextLine().trim();
            userNames.set(userId - 1, userName);

            String[] friendIds = sc.nextLine().split(",");
            
            for (String friendIdStr : friendIds) {
                if (!friendIdStr.trim().isEmpty()) {
                    int friendId = Integer.parseInt(friendIdStr.trim());
                    friendGraph.addDirectedEdge(userId, friendId);
                }
            }
        }
        sc.close();
    }
    
    /**
     * Recommends friends for a given user based on the graph's connections.
     *
     * @param userId the user ID for which to recommend friends
     * @return a list of recommended friend IDs
     */
    public ArrayList<Integer> recommendFriends(int userId) {
        friendGraph.BFS(userId);
        ArrayList<Integer> recommendations = new ArrayList<>();
        for (int i = 1; i <= friendGraph.getNumVertices(); i++) {
            if (friendGraph.getDistance(i) >= 2 && !isFriend(userId, i)) {
                recommendations.add(i);
            }
        }
        return recommendations;
    }
    
    /**
     * Checks if two users are already friends in the graph.
     *
     * @param userId the user ID to check
     * @param potentialFriend the ID of the potential friend
     * @return true if they are friends, false otherwise
     */
    private boolean isFriend(int userId, int potentialFriend) {
        LinkedList<Integer> friendList = friendGraph.getAdjacencyList(userId);
        friendList.positionIterator();
        while (!friendList.offEnd()) {
            if (friendList.getIterator().equals(potentialFriend)) {
                return true;
            }
            friendList.advanceIterator();
        }
        return false;
    }
    
    /**
     * Adds a friendship (directed edge) between two users in the graph.
     *
     * @param userId the first user's ID
     * @param friendId the second user's ID
     */
    public void addFriendship(int userId, int friendId) {
    	friendGraph.addDirectedEdge(userId, friendId);
    }
	
    /**
     * The main method to run the Friend Recommender application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Friend Recommender!");

        FriendGraph fg = null;
        while (fg == null) {
        	System.out.print("Enter the name of a file: ");
        	String fileName = input.nextLine();

	        try {
	            Scanner fileScanner = new Scanner(new File(fileName));
				int numVtx = fileScanner.nextInt();
	            fileScanner.close();
	            
	            fg = new FriendGraph(numVtx);
	            fg.loadUsers(fileName);
	        } catch (IOException err) {
	            System.out.println("Invalid file name");
	        }
        }
        
        System.out.println("Enter your user number below:");
        for (int i = 0; i < fg.userNames.size(); i++) {
            if (!fg.userNames.get(i).equals("")) {
                System.out.println((i + 1) + ". " + fg.userNames.get(i));
            }
        }

        System.out.print("\nEnter your choice: ");
        int userId = input.nextInt();
        //input.next();

        while (true) {
            System.out.println("\nHere are your current friends:");
            LinkedList<Integer> friendList = fg.friendGraph.getAdjacencyList(userId);
            if (friendList != null) {
                friendList.positionIterator();
                while (!friendList.offEnd()) {
                    Integer friendId = friendList.getIterator();
                    System.out.println(friendId + ". " + fg.userNames.get(friendId - 1));
                    friendList.advanceIterator();
                }
            } else {
                System.out.println("You currently have no friends.");
            }
            System.out.println("\nHere are your recommended friends:");
            ArrayList<Integer> recommendations = fg.recommendFriends(userId);
            if (recommendations.isEmpty()) {
            	System.out.println("Sorry! We don't have any recommendations for you at this time.");
            	break;
            }
            else {
	            for (Integer recId : recommendations) {
	                System.out.println(recId + ". " + fg.userNames.get(recId - 1));
	            }
	
	            System.out.print("\nEnter the number of a friend to add or -1 to quit: ");
	            System.out.println("Enter your choice:");
	            int choice = input.nextInt();
	            if (choice == -1) {
	                break;
	            }
	            else {
	            	fg.addFriendship(userId, choice);
	            }
            }
        }
        input.close();
        System.out.println("\nGoodbye!");
    }
}
