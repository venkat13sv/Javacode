import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     *
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     *
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     *
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     *
     * @param an
     *            int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }

}

public class PartBSolution {

    public static Queue<SiteStats> sites = new LinkedList<SiteStats>();


    // Main method to list top n visited sites
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {
        // WRITE CODE HERE
		sortQueue(sites);
		 while(sites.isEmpty()== false)
        {
            System.out.print(sites.peek() + " ");
            sites.poll();
        }

    }

    // Method to find the website in the queue and increment the visited count by 1, adding new node in case website is not found
    public static void updateCount(String url) {
        boolean flag=false;
        int size2=sites.size();
        for(int i = 0; i < size2 ; i++)
        {
			       SiteStats temp=sites.peek();
			       sites.poll();
		         if(temp.getUrl().equals(url))
             {
			            temp.setNumVisits(temp.getNumVisits()+1);
			            flag=true;
			            sites.add(temp);
			            break;
		         }
		         sites.add(temp);
		   }
       if(!flag)
        sites.add(new SiteStats(url,1));
      }
        // WRITE CODE HERE


	public static int minIndex(Queue<SiteStats> list,
                                     int sortIndex)
    {
    int min_index = -1;
   // int min_value = Integer.MAX_VALUE;
   int min_value = 0;
    int s = list.size();
    for (int i = 0; i < s; i++)
    {
        SiteStats current = list.peek();

        // This is dequeue() in Java STL
        list.poll();

        // we add the condition i <= sortIndex
        // because we don't want to traverse
        // on the sorted part of the queue,
        // which is the right part.
        if (current.getNumVisits() >= min_value && i <= sortIndex)
        {
            min_index = i;
            min_value = current.getNumVisits();
        }
        list.add(current);
    }
    return min_index;
	}

    // Moves given minimum element
    // to rear of queue
    public static void insertMinToRear(Queue<SiteStats> list,
                                             int min_index)
     {
        SiteStats min_value = null;
        int s = list.size();
        for (int i = 0; i < s; i++)
        {
        SiteStats current = list.peek();
        list.poll();
        if (i != min_index)
            list.add(current);
        else
            min_value = current;
        }
        list.add(min_value);
    }

    public static void sortQueue(Queue<SiteStats> list)
    {
        for(int i = 1; i <= list.size(); i++)
        {
            int min_index = minIndex(list,list.size() - i);
            insertMinToRear(list, min_index);
        }
     }

    public static void main(String[] args) {
        String[] visitedSites = { "www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com", "www.google.co.in", "www.youtube.com",
                "www.facebook.com", "www.upgrad.com", "www.facebook.com", "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com",
                "www.netflix.com", "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com", "www.amazon.com",
                "www.microsoft.com", "www.upgrad.com" };

        for (String url : visitedSites) {
            updateCount(url);
        }
        listTopVisitedSites(sites, 5);

    }

}
